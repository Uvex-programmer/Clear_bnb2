package repositories;

import interfaces.AddressRepoInterface;
import models.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class AddressRepository implements AddressRepoInterface {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public AddressRepository() {
    }

    public Optional<Address> updateAddress(Address address) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(address);
            entityManager.getTransaction().commit();
            return Optional.of(address);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public Optional<Address> save(Address address) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
            return Optional.of(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public Optional<Address> findById(Integer id) {
        Address address = entityManager.find(Address.class, id);
        return address != null ? Optional.of(address) : Optional.empty();
    }
}
