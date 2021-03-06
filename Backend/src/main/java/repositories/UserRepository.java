package repositories;

import interfaces.UserRepoInterface;
import models.BankAccount;
import models.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class UserRepository implements UserRepoInterface {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public UserRepository() {

    }

    public Optional<User> save(User user) {
        try {
            entityManager.getTransaction().begin();
            if (user.getId() == 0) {
                user.addAccount(new BankAccount(10000));
                entityManager.persist(user);
            } else {
                entityManager.merge(user);
            }
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

}
