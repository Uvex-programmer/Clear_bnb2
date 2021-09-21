package repositories;

import models.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    
    private final EntityManager entityManager;
    
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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
    
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(entityManager.createNamedQuery("User.findByEmail", User.class)
                    .setParameter("email", email).getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    public Optional<User> findById(Integer id) {
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }
    
    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email" +
                        " AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }
}
