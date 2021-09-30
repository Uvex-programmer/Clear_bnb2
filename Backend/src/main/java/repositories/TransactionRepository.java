package repositories;

import interfaces.TransactionRepoInterface;
import models.Transaction;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements TransactionRepoInterface {
    
    
    private final EntityManager entityManager;
    
    public TransactionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<Transaction> findById(Integer id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        return transaction != null ? Optional.of(transaction) : Optional.empty();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from Transaction").getResultList();
    }
    
    public Optional<Transaction> save(Transaction transaction) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(transaction);
            entityManager.getTransaction().commit();
            return Optional.of(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
