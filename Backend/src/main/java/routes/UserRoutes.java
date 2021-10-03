package routes;
import express.Express;
import logic.UserLogic;
import models.Transaction;
import models.User;
import util.CookieCreater;
import util.UUIDCreater;

import java.util.UUID;

public class UserRoutes {

    private final Express app;
    UserLogic userLogic = new UserLogic();


    public UserRoutes(Express app) {
        this.app = app;
        this.userMethods();
    }



    public void userMethods() {
        app.post("/api/login-user", (req, res) -> {
            var user = userLogic.loginUser(req.body().get("email").toString(),
                    req.body().get("password").toString());
            if(user != null){
                res.json(user);
                res.cookie(userLogic.setupCookie(user.getEmail(), user.getId()));
                return;
            }
            res.json("Wrong login");
        });
        
        app.post("/api/register-user", (req, res) -> {
            var test = userLogic.checkIfUserExist(req.body().get("email").toString());
            if(test == null) {
                res.json("User already exists.");
                return;
            }
            var user = userLogic.registerUser(req.body(User.class));
            res.cookie(userLogic.setupCookie(user.getEmail(), user.getId()));
            res.json(user);
           });

        app.get("/api/logout-user", (req, res) -> {
            userLogic.logoutUser(req.cookie("current-user"));
            res.clearCookie("current-user", "/").clearCookie("JSESSIONID", "/");
            res.status(201).json("Successfully Logged out!");
        });

        app.get("/api/get-user/:id", (req, res) -> {
            res.json(userLogic.findUser(Integer.parseInt(req.params("id"))));
        });


        app.get("/api/whoami", (req, res) -> {
            UUIDCreater uuid = new UUIDCreater();
            CookieCreater creator = new CookieCreater();

            res.cookie(creator.create("id", uuid.getUUID()));
         //   res.json(userLogic.whoAmI(req.cookie("current-user")));
            res.send(201);
        });
    }
}


