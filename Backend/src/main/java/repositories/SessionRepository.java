package repositories;

import interfaces.SessionRepoInterface;
import models.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class SessionRepository implements SessionRepoInterface {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public SessionRepository( ) {

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
