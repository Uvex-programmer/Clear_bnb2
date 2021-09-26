package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Booking;
import models.Session;
import models.User;
import repositories.SessionRepository;
import repositories.BookingRepository;
import repositories.UserRepository;
import util.PasswordHash;
import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public class UserRoutes {
    final PasswordHash passwordHash = new PasswordHash();
    private final Express app;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BookingRepository bookingRepository;

    
    public UserRoutes(Express app, ObjectMapper mapper, UserRepository userRepository, SessionRepository sessionRepository, BookingRepository bookingRepository) {
        this.app = app;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bookingRepository = bookingRepository;
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
                if(cookie.isPresent()) {
                    res.cookie(cookie.get());
                    res.json(mapper.writeValueAsString(user.get())).status(201);
                } else {
                    res.status(401).json("Sorry, could not log in..");
                }
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
                Optional <Cookie> cookie = setupSession(user.getEmail(), user.getId());
                if(cookie.isPresent()) {
                    res.cookie(cookie.get());
                    res.json(mapper.writeValueAsString(user)).status(201);
            } else {
                res.json("Save failed").status(500);
            }
        }});

        app.post("/api/purchase-booking", (req, res) -> {
            System.out.println(req.body());

            //1. get userId from cookie
            Optional <Integer> userId = sessionRepository.findUserBySessionId(Integer.parseInt(req.cookie("current-user")));
            //2. find user from userId
            User user = userRepository.findById(userId.get()).get();
            //3. find receiver from receiver_id

            Booking booking = req.body(Booking.class);
            booking.addUser(user);
            booking.getTransaction().setUser(user);
            booking.getTransaction().setBooking(booking);
            booking.getTransaction().setBooking(booking);

            bookingRepository.save(booking);
            System.out.println(booking);
            res.json(mapper.writeValueAsString(booking)).status(201);
        });
        
        app.get("/api/logout-user", (req, res) -> {
            String session_id = req.cookie("current-user");
            System.out.println("session_id: " + session_id);
            Optional <Session> session = sessionRepository.findById(Integer.parseInt(session_id));
            if(session.isPresent()) {
                sessionRepository.deleteById(session.get());
            }

            res.clearCookie("current-user", "/").clearCookie("JSESSIONID", "/");
            res.status(201).json("Successfully Logged out!").redirect("/");
        });

        app.get("/api/user-get-properties/:id", (req, res) -> {
         var user = userRepository.findById(Integer.parseInt(req.params("id")));
         List<?> properties = user.get().getProperties();
         res.json(mapper.writeValueAsString(properties));
        });

        app.get("/api/whoami", (req, res) -> {
            res.json(mapper.writeValueAsString(req.cookie("current-user")));
        });

    }
}


