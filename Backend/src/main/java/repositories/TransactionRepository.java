package repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Transaction;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TransactionRepository {
    
    
    private final EntityManager entityManager;
    private final Express app;
    private final ObjectMapper mapper;
    
    public TransactionRepository(EntityManager entityManager, Express app, ObjectMapper mapper) {
        this.entityManager = entityManager;
        this.app = app;
        this.mapper = mapper;
    }
    
    public Transaction findById(Integer id) {
        return entityManager.find(Transaction.class, id);
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from User").getResultList();
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
