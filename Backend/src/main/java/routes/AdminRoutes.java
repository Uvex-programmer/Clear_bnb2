package routes;

import express.Express;
import models.Worker;
import repositories.MessageRepository;
import util.CookieCreater;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;
import util.PasswordHash;

public class AdminRoutes {
    private final Express app;
    PasswordHash passwordHash = new PasswordHash();
    MessageRepository messageRepository = new MessageRepository();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    CookieCreater cookie = new CookieCreater();

    public AdminRoutes(Express app) {
        this.app = app;
        this.userMethods();
    }

    public void userMethods() {
        app.post("/api/support/register-user", (req, res) -> {
            res.json(registerUser(req.body(Worker.class)));
        });

        app.post("/api/support/messages", (req, res) -> {
            res.json(messageRepository.getMessagesFromChatroomId(req.body().get("id").toString()));
        });

        app.get("/api/support/all-messages", (req, res) -> {
            res.json(messageRepository.uniqueOpenThreads());
        });

        app.post("/api/support/login", (req, res) -> {
            Worker worker = req.body(Worker.class);
            if(worker.getEmail().equals("Karin22@hugo.se") && worker.getPassword().equals("111")) { //NcTZUZCJqBib1fCpMh76GgoBG7VklS_yZ6nfs5TscIQ || NcTZUZCJqBib1fCpMh76GgoBG7VklS_yZ6nfs5TscIQ
                res.cookie(cookie.create("current-user", worker.getPassword()));
                res.cookie("is_support", "true").json(worker);
            } else {
                res.json("Wrong login");
            }
        });
    }
    public Worker registerUser (Worker newWorker) {
        String hashedPass = passwordHash.hash(newWorker.getPassword().toCharArray());
        newWorker.setPassword(hashedPass);
        if (save(newWorker).isPresent()){
            Optional <Worker> foundUser = findById(newWorker.getId());
            if(foundUser.isPresent()) {
                return foundUser.get();
            }
        }
        return newWorker;
    }

    public Optional<Worker> findById(Integer id) {
        Worker user = entityManager.find(Worker.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Optional<Worker> save(Worker worker) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(worker);
            entityManager.getTransaction().commit();
            return Optional.of(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
