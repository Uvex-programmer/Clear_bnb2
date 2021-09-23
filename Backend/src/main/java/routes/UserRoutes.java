package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import express.http.Response;
import models.Session;
import models.User;
import repositories.SessionRepository;
import repositories.UserRepository;
import util.PasswordHash;

import javax.servlet.http.Cookie;
import java.net.HttpCookie;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
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

    public Optional <Cookie> setupSession(String email, int id) {
         Session session = new Session(email, new Timestamp(System.currentTimeMillis()), id);
        if (sessionRepository.save(session).isPresent()) {
            Cookie cookie = new Cookie("current-user", String.valueOf(session.getId()));
            cookie.setMaxAge(3600);
            cookie.setHttpOnly(true);
            return Optional.of(cookie);

         } else {
        return  Optional.empty();
    }
    }

    public void userMethods() {
        app.post("/api/login-user", (req, res) -> {
            Optional<User> user = userRepository.findByEmail(req.body().get("email").toString());
            if (user.isEmpty()) {
                res.json("User doesnt exist!").status(401);
                return;
            }
            
            if (passwordHash.authenticate(user.get().getPassword().toCharArray(), req.body().get("password").toString())) {
                Optional <Cookie> cookie = setupSession(user.get().getEmail(), user.get().getId());
               // Session session = new Session(user.get().getEmail(), new Timestamp(System.currentTimeMillis()), user.get().getId());
               // if (sessionRepository.save(session).isPresent()) {
                 //   var today = Calendar.getInstance().getTime();;
                   // var expiry = new Date(today.getTime() + 3600 * 1000);
                //res.set("Set-Cookie", cookie);
                if(cookie.isPresent()) {
                    res.cookie(cookie.get()).json(user.get().getEmail()).status(201).redirect("/");
                } else {
                    res.status(401).json("Sorry, could not log in..");
                }
                  //  res.set("Set-Cookie", "current-user=" + session.getId() + "; expires=" + expiry + "; HttpOnly; SameSite=strict");

            } else {
                res.json("Wrong Password.").status(401);
            }
        });
        
        app.post("/api/register-user", (req, res) -> {
            Optional<User> userExists = userRepository.findByEmail(req.body().get("email").toString());
            if (userExists.isPresent()) {
                res.json("User already exists.").status(401);
                return;
            }
            User user = req.body(User.class);
            String hashedPass = passwordHash.hash(user.getPassword().toCharArray());
            user.setPassword(hashedPass);
            
            if (userRepository.save(user).isPresent()) {
                res.json("Success!").status(200);
            } else {
                res.json("Save failed").status(500);
            }
            res.status(201).redirect("/api/login-user");
        });
        
        app.get("/api/logout-user", (req, res) -> {
            String session_id = req.cookie("current-user");
            Optional <Session> session = sessionRepository.findById(Integer.parseInt(session_id));
            if(session.isPresent()) {
                sessionRepository.deleteById(session.get());
            }

            res.clearCookie("current-user", "/api").clearCookie("JSESSIONID", "/");
            res.status(201).json("Successfully Logged out!");
        });

        
        app.get("/api/whoami", (req, res) -> {
            res.json("hej");
        });
    }
}


