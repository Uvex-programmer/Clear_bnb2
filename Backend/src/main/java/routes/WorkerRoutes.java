package routes;

import express.Express;
import logic.WorkerLogic;
import models.Worker;
import repositories.MessageRepository;
import util.CookieCreator;

import java.util.Optional;

public class WorkerRoutes {
    private final Express app;
    MessageRepository messageRepository = new MessageRepository();
    CookieCreator cookie = new CookieCreator();
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
           Object value = messageRepository.getMessagesFromChatroomId(req.body().get("id").toString());
           if(value != null) {
               res.json(value);
           } else {
               res.send("No");
           }

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
