package routes;

import express.Express;
import logic.WorkerLogic;
import models.Worker;
import org.hibernate.jdbc.Work;
import repositories.MessageRepository;
import util.CookieCreater;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;
import util.PasswordHash;

public class WorkerRoutes {
    private final Express app;
    MessageRepository messageRepository = new MessageRepository();
    CookieCreater cookie = new CookieCreater();
    WorkerLogic workerLogic = new WorkerLogic();

    public WorkerRoutes(Express app) {
        this.app = app;
        this.userMethods();
    }

    public void userMethods() {
        app.post("/api/support/register-user", (req, res) -> {
           // res.json(registerUser(req.body(Worker.class)));
            res.status(500).send("Service unavailable.");
        });

        app.post("/api/support/messages", (req, res) -> {
            res.json(messageRepository.getMessagesFromChatroomId(req.body().get("id").toString()));
        });

        app.get("/api/support/all-messages", (req, res) -> {
            res.json(messageRepository.uniqueOpenThreads());
        });

        app.post("/api/support/login", (req, res) -> {
            Optional <Worker> worker = workerLogic.loginHandler(req.body(Worker.class));
            if(worker.isPresent()){
                res.cookie(cookie.create("current-user", worker.get().getPassword()));
                res.cookie("is_support", "true").json(worker);
            } else {
                res.json("Wrong login");
            }
        });
    }
}
