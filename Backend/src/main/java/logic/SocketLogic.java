package logic;

import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import repositories.MessageRepository;
import routes.Message;


import java.sql.Timestamp;
import java.util.*;

public class SocketLogic {

    public List<WsContext> getClients() { return clients; }
    public void addClients(WsContext client) { this.clients.add(client); }
    public String removeClient(WsContext client, String returnMessage) {
        this.clients.remove(client);
        return returnMessage;
    }
    public Map<String, List<WsContext>> getChatRooms() { return chatRooms; }
    public void setChatRooms(Map<String, List<WsContext>> chatRooms) { this.chatRooms = chatRooms; }

    private List<WsContext> clients = new ArrayList<>();
    private Map<String, List<WsContext>> chatRooms =  new HashMap<>();
    private final MessageRepository messageRepository = new MessageRepository();
    public void addToDatabase(Message msg) {
        messageRepository.save(msg);
    }

    public Boolean isWorker(String cookie) {
        return !(cookie == null) && cookie.equals("111");
    }

    public Message connectedMessage(WsContext ctx, String cookie) {
        addClients(ctx);
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
 }
