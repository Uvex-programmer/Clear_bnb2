package repositories;

import models.Transaction;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TransactionRepository {
    
    
    private final EntityManager entityManager;
    
    public TransactionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Transaction findById(Integer id) {
        return entityManager.find(Transaction.class, id);
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
