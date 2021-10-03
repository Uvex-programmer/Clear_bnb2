package routes;

import express.Express;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import repositories.MessageRepository;

import java.sql.Timestamp;
import java.util.*;


public class SocketRoutes {

    private final Express app;
    private List<WsContext> clients = new ArrayList<>();
    private Map<String, List<WsContext>> chatRooms =  new HashMap<>();
    private final MessageRepository messageRepository = new MessageRepository();
    public SocketRoutes(Express app) {
        this.app = app;
        this.socketMethods();
    }


    public void addToDatabase(Message msg) {
        messageRepository.save(msg);
    }



    public Boolean isWorker(String cookie) {
        System.out.println("IsWorker");
        System.out.println(cookie);
        if(cookie.equals("111")) {
            return true;
        } else {
            return false;
        }
    }

    public Message connectedMessage(String cookie) {
        System.out.println("Connected");
        Message msg = new Message("Hello! My name is Mary and I work as a customer relations expert. How may I be of service?", new Timestamp(new Date().getTime()),  "support", "");
        if(isWorker(cookie)) {
            msg.setMsg("Welcome for another day of work!");
        } else {
            msg.setMsg("Hello! My name is Mary and I work as a customer relations expert. How may I be of service?");
        }
        return msg;
    }

    public Message onMessageHandler(WsMessageContext ctx) {
        Message msg = ctx.message(Message.class);
        msg.setIs_support(isWorker(ctx.cookie("current-user")));
        if(msg.getChatroom_id() == null) msg.setChatroom_id(ctx.cookie("id"));
        addToDatabase(msg);
        return msg;
    }

    public void createChatRoom(WsContext ctx, Message msg) {
        List<WsContext> participants = new ArrayList<>();
        participants.add(ctx);
        clients.forEach(client -> {
            if(client.cookie("id").equals(msg.getChatroom_id())) {
                participants.add(client);
            }
        });
        chatRooms.put(msg.getChatroom_id(), participants);
    }


    public void socketMethods() {
        app.ws("/websockets/chat", ws -> {
            ws.onConnect(ctx -> {
                clients.add(ctx);
                System.out.println(ctx.cookie("id"));
                ctx.send(connectedMessage(ctx.cookie("current-user")));
            });
            ws.onMessage(ctx -> {
                Message msg = onMessageHandler(ctx);
                if(ctx.cookie("current-user").equals("111") || chatRooms.containsKey(ctx.cookie("id"))) {
                    createChatRoom(ctx, msg);
                    clients.forEach(client -> {
                        client.send(msg);
                    });
                } else {
                    ctx.send(msg);
                }
            });
            ws.onBinaryMessage(ctx -> System.out.println("Message"));
            ws.onClose(ctx -> {
                System.out.println("Closed");
                ctx.send("Goodbye.");
                clients.remove(ctx);
            });
            ws.onError(ctx -> {
                System.out.println("Errored.");
                System.out.println(ctx.error());
                clients.remove(ctx);
            });
        });
    }
}
