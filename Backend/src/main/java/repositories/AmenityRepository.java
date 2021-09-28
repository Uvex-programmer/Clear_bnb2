package repositories;

import interfaces.AmenityRepoInterface;
import models.Amenity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class AmenityRepository implements AmenityRepoInterface {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public AmenityRepository( ) {

    }
    
    public Optional<Amenity> findById(Integer id) {
        Amenity amenity = entityManager.find(Amenity.class, id);
        return amenity != null ? Optional.of(amenity) : Optional.empty();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from Amenity").getResultList();
    }
    
    public Optional<Amenity> save(Amenity amenity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(amenity);
            entityManager.getTransaction().commit();
            return Optional.of(amenity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
