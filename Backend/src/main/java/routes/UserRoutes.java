package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Session;
import models.User;
import repositories.SessionRepository;
import repositories.UserRepository;
import util.PasswordHash;

import javax.servlet.http.Cookie;
import java.util.Optional;


public class UserRoutes {
    final PasswordHash passwordHash = new PasswordHash();
    private final Express app;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    
    public UserRoutes(Express app, ObjectMapper mapper, UserRepository userRepository, SessionRepository sessionRepository) {
        this.app = app;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.userMethods();
    }

    public void userMethods() {
        app.post("/api/login-user", (req, res) -> {
            Optional<User> user1 = userRepository.findByEmail(req.body().get("email").toString());
            if (user1.isEmpty()) {
                System.out.println("Rad 56: USER DOESNT EXIST!");
                res.json("User doesnt exist!").status(401);
                return;
            }
            
            if (passwordHash.authenticate(user1.get().getPassword().toCharArray(), req.body().get("password").toString())) {
                Session session = new Session(user1.get().getEmail());
                if (sessionRepository.save(session).isPresent()) {
                    System.out.println("Success!");
                    req.session("current-user", session.getId());
                    res.cookie("min-kaka", "hej").json(user1.get().getEmail()).status(201).redirect("/");
                } else {
                    System.out.println("Save failed.");
                    res.json("Save failed").status(500);
                }

            } else {
                res.json("Wrong Password.").status(401);
            }


        });
        
        app.post("/api/register-user", (req, res) -> {
            Optional<User> userExists = userRepository.findByEmail(req.body().get("email").toString());
            if (userExists.isPresent()) {
                System.out.println("User exists!");
                res.json("User already exists.").status(401);
                return;
            }
            User user = req.body(User.class);
            String hashedPass = passwordHash.hash(user.getPassword().toCharArray());
            user.setPassword(hashedPass);
            
            if (userRepository.save(user).isPresent()) {
                System.out.println("Success!");
                res.json("Success!").status(200);
            } else {
                System.out.println("Save failed.");
                res.json("Save failed").status(500);
            }
            var userLoggedIn = mapper.writeValueAsString(user);
            System.out.println("UNDER HÄR SPARAS SESSION: ");
            req.session("current-user", userLoggedIn);
            res.json(mapper.writeValueAsString(userLoggedIn));
        });
        
        app.get("/api/logout-user", (req, res) -> {
            int session_id = Integer.parseInt(mapper.writeValueAsString(req.session("current-user")));
            System.out.println("sezzion" + session_id);

            Optional <Session> session = sessionRepository.findById(session_id);

            sessionRepository.deleteById(session.get());
            req.session("current-user", null);
            res.json("Ok, logged out");
        });
        
        app.get("/api/whoami", (req, res) -> {
           // res.json(req.session("current-user"));
            res.json("hej");
        });
        
    }
}
