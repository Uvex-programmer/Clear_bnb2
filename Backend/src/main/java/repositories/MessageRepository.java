package repositories;

import io.javalin.websocket.WsContext;
import models.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MessageRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public MessageRepository( ) {

    }

    public Optional<Message> save(Message msg) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(msg);
            entityManager.getTransaction().commit();
            return Optional.of(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Map<String, List<WsContext>> fetchAllChatrooms () {
        List<Message> entireList = (List<Message>) findAll();
        List<String> keys = (List<String>) uniqueOpenThreads();
        Map<String, List<WsContext>> complete = new HashMap<>();

        return complete;
    }


    public List<?> uniqueOpenThreads() {
        List<?> msgs = entityManager.createQuery("select DISTINCT(m.chatroom_id) FROM Message m")
                .getResultList();
        msgs.forEach(System.out::println);
        return msgs;
    }

    public List <?> getMessagesFromChatroomId(String chatroom_id) {
        List<?> msgs = entityManager.createQuery("SELECT m FROM Message m WHERE m.chatroom_id = :chat_id ORDER BY time_sent ASC", Message.class)
                .setParameter("chat_id", chatroom_id)
                .getResultList();
        return msgs;
    }

    public List<?> findAll() {
        return entityManager.createQuery("from Message").getResultList();
    }

}
