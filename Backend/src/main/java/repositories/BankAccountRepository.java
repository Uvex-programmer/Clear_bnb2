package repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.BankAccount;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BankAccountRepository {
    private final EntityManager entityManager;
    private final Express app;
    private final ObjectMapper mapper;
    
    public BankAccountRepository(EntityManager entityManager, Express app, ObjectMapper mapper) {
        this.entityManager = entityManager;
        this.app = app;
        this.mapper = mapper;
    }
    
    public BankAccount findById(Integer id) {
        return entityManager.find(BankAccount.class, id);
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }
    
    public Optional<BankAccount> save(BankAccount account) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
            return Optional.of(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
