package repositories;

import models.Address;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AddressRepository implements AddressRepoInterface {
    
    private final EntityManager entityManager;
    
    public AddressRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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
    
    public List findAll() {
        return entityManager.createQuery("from Address").getResultList();
    }
}
