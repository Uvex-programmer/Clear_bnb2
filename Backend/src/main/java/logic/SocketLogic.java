package logic;

import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import repositories.MessageRepository;
import models.Message;
import java.sql.Timestamp;
import java.util.*;

public class SocketLogic {
    private final MessageRepository messageRepository = new MessageRepository();
    private final Map<String, List<WsContext>> chatRooms = new HashMap<>();
    private final List<WsContext> supportWorkers = new ArrayList<>();


    public void addToDatabase(Message msg) {
        if(msg.getChatroom_id() != null) messageRepository.save(msg);
    }

    public List<?> uniqueRooms() {
        return messageRepository.uniqueRooms();
    }

    public Message onConnectHandler(WsContext ctx, String cookie) {
        Message msg = new Message("Hello! My name is Mary and I work as a customer relations expert. How may I be of service?", new Timestamp(new Date().getTime()),"support_message", "none", true);
        if(isWorker(cookie)) {
            msg.setMsg("Welcome for another day of work!");
            msg.setPayload(uniqueRooms());
            if(!supportWorkers.contains(ctx)) supportWorkers.add(ctx);
        }
        userRefreshedPage(ctx);
        return msg;
    }

    public void userRefreshedPage(WsContext ctx) {
        String userId = ctx.cookie("id");
        List<WsContext> chatRoom = chatRooms.get(userId);
        if(chatRoom != null){
            chatRoom.removeIf(context -> Objects.equals(context.cookie("id"), userId));
            chatRoom.add(ctx);
        }
    }

    public void removeUserFromRoom(WsContext ctx){
        String userId = ctx.cookie("id");
        List<WsContext> chatRoom = chatRooms.get(userId);
        if(chatRoom != null) chatRoom.removeIf(context -> Objects.equals(context.cookie("id"), userId));
    }

    public Message createMessage(WsMessageContext ctx) {
        Message msg = ctx.message(Message.class);
        msg.setIs_support(ctx.cookie("is_support") != null );
        if(msg.getChatroom_id() == null) msg.setChatroom_id(ctx.cookie("id"));

        addToDatabase(msg);
        return msg;
    }


    public HashMap <Message, List<WsContext>> onMessageHandler(WsMessageContext ctx) {
        String chatRoomId = ctx.message(Message.class).getChatroom_id();
        HashMap <Message, List<WsContext>> delivery = new HashMap<>();
        Message msg = createMessage(ctx);

         if (!chatRoomExists(chatRooms, ctx)) msg = createChatRoom(ctx, msg);

        delivery.put(msg, getChatRooms().get(chatRoomId == null ? ctx.cookie("id") : chatRoomId));
        return delivery;
        };


    public Message createChatRoom(WsContext ctx, Message msg) {
        List<WsContext> participants = new ArrayList<>();
        participants.add(ctx);
        participants.add(supportWorkers.get(0));
        chatRooms.put(ctx.cookie("id"), participants);
        msg.setPayload(uniqueRooms());
        return msg;
    }

    public Map<String, List<WsContext>> getChatRooms() {
        return chatRooms;
    }

    public Boolean isWorker(String cookie) {
        return !(cookie == null) && cookie.equals("111");
    }

    public static Boolean chatRoomExists(Map<String, List<WsContext>> chatRooms, WsContext ctx) {
        return chatRooms.containsKey(ctx.cookie("id"));
    }
 }
