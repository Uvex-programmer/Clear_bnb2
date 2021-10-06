package routes;
import DTO.UserDTO;
import express.Express;
import logic.UserLogic;
import models.Transaction;
import models.User;
import util.CookieCreater;
import util.UUIDCreater;

import javax.servlet.http.Cookie;
import java.util.Optional;
import java.util.UUID;

public class UserRoutes {

    private final Express app;
    UserLogic userLogic = new UserLogic();
    UUIDCreater uuid = new UUIDCreater();
    CookieCreater creator = new CookieCreater();

    public UserRoutes(Express app) {
        this.app = app;
        this.userMethods();
    }

    public void userMethods() {
        app.post("/api/login-user", (req, res) -> {
            UserDTO userDTO = userLogic.loginUser(req.body().get("email").toString(),
                    req.body().get("password").toString());
            if(userDTO != null){
                res.json(userDTO);
                res.cookie(userLogic.setupCookie(userDTO.getEmail(), userDTO.getId()));
                return;
            }
            res.json("Wrong login");
        });
        
        app.post("/api/register-user", (req, res) -> {
            var userCheck = userLogic.checkIfUserExist(req.body().get("email").toString());
            if(userCheck == null) {
                res.json("User already exists.");
                return;
            }
            UserDTO userDTO = userLogic.registerUser(req.body(User.class));
            res.cookie(userLogic.setupCookie(userDTO.getEmail(), userDTO.getId()));
            res.json(userDTO);
           });

        app.get("/api/logout-user", (req, res) -> {
            userLogic.logoutUser(req.cookie("current-user"));
            res.clearCookie("current-user", "/").json("Successfully Logged out!");
        });

        app.get("/api/get-user/:id", (req, res) -> {
            res.json(userLogic.findUser(Integer.parseInt(req.params("id"))));
        });


        app.get("/api/whoami", (req, res) -> {
            Object user = userLogic.whoAmI(req.cookie("current-user"));

            if(user == null) {
                res.cookie(creator.create("id", uuid.getUUID())).send("Not logged in.");
            } else {
                res.json(user);
            }
        });
    }
}


