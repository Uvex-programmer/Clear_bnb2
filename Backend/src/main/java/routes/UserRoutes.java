package routes;
import express.Express;
import logic.UserLogic;
import models.User;

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

        app.post("/api/purchase-booking", (req, res) -> {
            System.out.println(req.body());
            res.send("Endpoint received object!").status(201);
          //  res.json(mapper.writeValueAsString(req.cookie("current-user")));
        });
        
        app.get("/api/logout-user", (req, res) -> {
            userLogic.logoutUser(req.cookie("current-user"));
            res.clearCookie("current-user", "/").clearCookie("JSESSIONID", "/");
            res.status(201).json("Successfully Logged out!").redirect("/");
        });

        app.get("/api/get-user/:id", (req, res) -> {
            res.json(userLogic.findUser(Integer.parseInt(req.params("id"))));
        });

        
        app.get("/api/whoami", (req, res) -> {
            res.json(userLogic.whoAmI(req.cookie("current-user")));
        });

    }
}


