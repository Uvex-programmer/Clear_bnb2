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

        public Optional<Session> findById(Integer id) {
            Session session = entityManager.find(Session.class, id);
            return session != null ? Optional.of(session) : Optional.empty();
        }

        public void deleteById(Session session) {
            entityManager.getTransaction().begin();
            entityManager.remove(session);
            entityManager.getTransaction().commit();
        }

        public List<?> findAll() {
            return entityManager.createQuery("from User").getResultList();
        }

}
