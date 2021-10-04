package logic;

import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;
import repositories.MessageRepository;
import routes.Message;
import util.CustomerSupport;
import util.Subject;

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

    private Subject subject = new Subject();
    private List<WsContext> clients = new ArrayList<>();
    private Map<String, List<WsContext>> chatRooms =  new HashMap<>();
    private Subject subj = new Subject();
    private final MessageRepository messageRepository = new MessageRepository();
    CustomerSupport supportWorker = new CustomerSupport();
    public void addToDatabase(Message msg) {
        messageRepository.save(msg);
    }

    public Boolean isWorker(String cookie) {
        return !(cookie == null) && cookie.equals("111");
    }

    public Message connectedMessage(WsContext ctx, String cookie) {
        addClients(ctx);
        System.out.println("Connected");
        Message msg = new Message("Hello! My name is Mary and I work as a customer relations expert. How may I be of service?", new Timestamp(new Date().getTime()),  "support", "");
        if(isWorker(cookie)) {
            msg.setMsg("Welcome for another day of work!");
            supportWorker.add(ctx);
            subj.add(supportWorker);
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
        subj.notifyObservers(msg.getChatroom_id());
        return msg;
    }

    public void createChatRoom(WsContext ctx, Message msg) {
        System.out.println("Tjabba robban");
        List<WsContext> participants = new ArrayList<>();
        participants.add(ctx);
        clients.forEach(client -> {
            if(client.cookie("id").equals(msg.getChatroom_id())) {
                participants.add(client);
            }
        });
        chatRooms.put(msg.getChatroom_id(), participants);
    }

    public void tesd(){
    }
 }
