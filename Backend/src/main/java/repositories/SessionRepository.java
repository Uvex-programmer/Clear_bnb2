package repositories;

import models.Session;
import models.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SessionRepository implements SessionRepoInterface{
    private final EntityManager entityManager;

    public SessionRepository(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public Optional<Session> save(Session session) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(session);
                entityManager.getTransaction().commit();
                return Optional.of(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }

        public Optional<User> findById(Integer id) {
            User user = entityManager.find(User.class, id);
            return user != null ? Optional.of(user) : Optional.empty();
        }

        public List<?> findAll() {
            return entityManager.createQuery("from User").getResultList();
        }

}
