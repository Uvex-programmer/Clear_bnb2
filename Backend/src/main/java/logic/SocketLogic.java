package logic;

import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;
import repositories.MessageRepository;
import models.Message;
import java.sql.Timestamp;
import java.util.*;

public class SocketLogic {
    //private final List<WsContext> clients = new ArrayList<>(); /17. this.clients.remove(client); /45. clients.add(ctx);
    private final MessageRepository messageRepository = new MessageRepository();
    CustomerSupport supportWorker = new CustomerSupport();
    public void addToDatabase(Message msg) {
        messageRepository.save(msg);
    }

    public Boolean isWorker(String cookie) {
        return !(cookie == null) && cookie.equals("111");
    }

    public Message onConnectHandler(WsContext ctx, String cookie) {
        Message msg = new Message("Hello! My name is Mary and I work as a customer relations expert. How may I be of service?", new Timestamp(new Date().getTime()),"support_message", "none", true);
        if(isWorker(cookie)) {
            msg.setMsg("Welcome for another day of work!");
            msg.setPayload(getPayload());
            supportWorkers.add(ctx);
        }
        System.out.println("Connected");
        return msg;
    }

    public List<?> getPayload() {
        return messageRepository.uniqueOpenThreads();
    }

    public Message onMessageHandler(WsMessageContext ctx) {
        Message msg = ctx.message(Message.class);

        if(msg.getChatroom_id() == null){
            msg.setChatroom_id(ctx.cookie("id"));
        }
        addToDatabase(msg);
        return msg;
    }

    public static Boolean isAdmin(String room_id) {
        return room_id != null;
    }

    public static Boolean chatRoomExists(Map<String, List<WsContext>> chatRooms, WsContext ctx) {
        return !chatRooms.containsKey(ctx.cookie("id"));
    }


 }
//       if(isWorker(ctx.cookie("current-user"))) { msg.setIs_support(true);}

     /*public String removeClient(WsContext client, String returnMessage) {
        return returnMessage;
    }*/