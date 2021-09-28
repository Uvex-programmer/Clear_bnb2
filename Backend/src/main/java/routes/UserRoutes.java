package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import express.http.Response;
import models.*;
import org.eclipse.jetty.util.ajax.JSON;
import repositories.BookingRepository;
import repositories.PropertyRepository;
import repositories.SessionRepository;
import repositories.UserRepository;
import util.PasswordHash;

import javax.servlet.http.Cookie;
import java.awt.print.Book;
import java.io.InputStream;
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
    private final PropertyRepository propertyRepository = new PropertyRepository();
    private final BookingRepository bookingRepository = new BookingRepository();

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

    public Transaction createTransaction (int price, User giver, User receiver) {
        Transaction transaction = new Transaction(price, giver, receiver);
        return transaction;
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
            BookingDTO bookDTO = req.body(BookingDTO.class);

            Optional<User> receiver = propertyRepository.findByIdReturnUserId(Integer.parseInt(req.body().get("propertyId").toString()));
            System.out.println(receiver);
            Optional<User> giver = userRepository.findById(Integer.parseInt(req.body().get("userId").toString()));
            System.out.println(giver);

            Transaction transaction = createTransaction(bookDTO.getPropertyPrice(), giver.get(), receiver.get());

            Booking booking = new Booking();
            booking.setPropertyId(bookDTO.getPropertyId());
            booking.setStartDate(bookDTO.getStartDate());
            booking.setEndDate(bookDTO.getEndDate());
            booking.setUser(giver.get());
            booking.setTransaction(transaction);
            System.out.println(booking);
            bookingRepository.save(booking);
            res.json(booking).type("application/json");
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

        
        app.get("/api/whoami", (req, res) -> {
            String session_id = req.cookie("current-user");
            if(session_id == null) {
                res.json(req.cookie("current-user"));
                return;
            }
            Optional <Session> session = sessionRepository.findById(Integer.parseInt(session_id));
            Optional <User> user = userRepository.findById(session.get().getUser_id());
           res.json(mapper.writeValueAsString(user));
        });

        app.get("/api/user-get-properties/:id", (req, res) -> {
         var user = userRepository.findById(Integer.parseInt(req.params("id")));
         List<?> properties = user.get().getProperties();
         res.json(mapper.writeValueAsString(properties));
        });

    }
}


