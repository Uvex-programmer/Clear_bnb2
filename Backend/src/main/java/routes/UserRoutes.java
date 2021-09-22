package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.User;
import repositories.UserRepository;
import util.PasswordHash;

import java.util.Optional;


public class UserRoutes {
    final PasswordHash passwordHash = new PasswordHash();
    private final Express app;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;
    
    public UserRoutes(Express app, ObjectMapper mapper, UserRepository userRepository) {
        this.app = app;
        this.mapper = mapper;
        this.userRepository = userRepository;
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
                //1. Generera Token, antingen här eller i PasswordHash
                //2. sätt token i .session
                //      req.session("current-user", user1.token);
                //2.5. Spara token på usern i databasen!
                //3. Skicka tillbaka token så browser kan sparar i cookie
                //  res.json(mapper.writeValueAsString(userLoggedIn));
                res.json(user1.get().getEmail()).status(201);
                req.session("current-user", user1);
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
            req.session("current-user", userLoggedIn);
            res.json(mapper.writeValueAsString(userLoggedIn));
        });
        
        app.get("/api/logoutUser", (req, res) -> {
            req.session("current-user", null);
            res.json("Ok, logged out");
        });
        
        app.get("/api/whoami", (req, res) -> {   //Control logged in user
            res.json(mapper.writeValueAsString((req.session("current-user"))));
        });
    }
}
