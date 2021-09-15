import express.Express;
import models.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final EntityManager entityManager;
    private final Express app;

    public UserRepository(EntityManager entityManager, Express app) {
        this.entityManager = entityManager;
        this.app = app;
    }

    public Optional<User> findById(Integer id) {
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public List findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public Optional<User> findByName(String name) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.firstName = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Optional<User> save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
