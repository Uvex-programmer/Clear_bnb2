package repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.User;
import util.PasswordHash;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    final PasswordHash passwordHash = new PasswordHash();
    private final EntityManager entityManager;
    private final Express app;
    private final ObjectMapper mapper;

    public UserRepository(EntityManager entityManager, Express app, ObjectMapper mapper) {
        this.entityManager = entityManager;
        this.app = app;
        this.mapper = mapper;
        this.userMethods();
    }

    public void userMethods() {

        app.post("/api/login-user", (req, res) -> {
            Optional<User> user1 = findByEmail(req.body().get("email").toString());
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
            } else {
                res.json("Wrong Password.").status(401);
            }
        });

        app.post("/api/register-user", (req, res) -> {
            Optional<User> userExists = findByEmail(req.body().get("email").toString());
            if (userExists.isPresent()) {
                System.out.println("User exists!");
                res.json("User already exists.").status(401);
                return;
            }
            User user = req.body(User.class);
            String hashedPass = passwordHash.hash(user.getPassword().toCharArray());
            user.setPassword(hashedPass);

            if (save(user).isPresent()) {
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

    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(entityManager.createNamedQuery("User.findByEmail", User.class)
                    .setParameter("email", email).getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> findById(Integer id) {
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public List findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email" +
                        " AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Optional<User> save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
