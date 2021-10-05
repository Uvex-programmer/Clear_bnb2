package repositories;

import models.Worker;
import util.PasswordHash;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class WorkerRepository {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    PasswordHash passwordHash = new PasswordHash();


    public Optional<Worker> findById(Integer id) {
        Worker user = entityManager.find(Worker.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Worker registerUser (Worker newWorker) {
        String hashedPass = passwordHash.hash(newWorker.getPassword().toCharArray());
        newWorker.setPassword(hashedPass);
        if (save(newWorker).isPresent()){
            Optional <Worker> foundUser = findById(newWorker.getId());
            if(foundUser.isPresent()) {
                return foundUser.get();
            }
        }
        return newWorker;
    }

     public Optional<Worker> save(Worker worker) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(worker);
            entityManager.getTransaction().commit();
            return Optional.of(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
