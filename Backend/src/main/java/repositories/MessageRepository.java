package repositories;
import models.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class MessageRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public MessageRepository( ) {
    }

    public List<String> uniqueRooms() {
        List<String> rooms = entityManager.createQuery("select DISTINCT(m.chatroom_id) FROM Message m", String.class).getResultList();
        return rooms.isEmpty() ?  new ArrayList<>() : rooms;
    }

    public List <Message> getMessagesFromChatroomId(String chatroom_id) {
        return entityManager.createQuery("SELECT m FROM Message m WHERE m.chatroom_id = :chat_id ORDER BY time_sent ASC", Message.class)
                .setParameter("chat_id", chatroom_id)
                .getResultList();
    }

    public List<Message> findAll() {
        return entityManager.createQuery("from Message", Message.class).getResultList();
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

}
