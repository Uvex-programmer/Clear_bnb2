package repositories;

import models.BankAccount;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BankAccountRepository implements BankRepoInterface {
    private final EntityManager entityManager;
    
    public BankAccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<BankAccount> findById(Integer id) {
        BankAccount bankAccount = entityManager.find(BankAccount.class, id);
        return bankAccount != null ? Optional.of(bankAccount) : Optional.empty();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from BankAccount").getResultList();
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
