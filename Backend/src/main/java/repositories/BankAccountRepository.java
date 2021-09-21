package repositories;

import models.BankAccount;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BankAccountRepository {
    private final EntityManager entityManager;
    
    public BankAccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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
