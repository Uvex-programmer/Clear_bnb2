package repositories;

import interfaces.BankRepoInterface;
import models.BankAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class BankAccountRepository implements BankRepoInterface {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
   
    
    public BankAccountRepository() {
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
            entityManager.merge(account);
            entityManager.getTransaction().commit();
            return Optional.of(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
