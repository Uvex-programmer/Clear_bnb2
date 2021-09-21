package repositories;

import models.Amenity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AmenityRepository implements AmenityRepoInterface {
    private final EntityManager entityManager;
    
    public AmenityRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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
