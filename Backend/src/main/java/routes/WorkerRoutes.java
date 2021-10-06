package routes;

import express.Express;
import logic.WorkerLogic;
import models.Message;
import models.Worker;
import repositories.MessageRepository;
import util.CookieCreator;

import java.util.List;
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

        app.post("/api/support/messages", (req, res) -> {
            List<Message> conversation = messageRepository.getMessagesFromChatroomId(req.body().get("id").toString());
           if(conversation != null) {
               res.json(conversation);
           } else {
               res.send("No results found.");
           }
        });

        app.get("/api/support/all-messages", (req, res) -> {
            res.json(messageRepository.uniqueRooms());
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
