package repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Amenity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AmenityRepository {
    
    
    private final EntityManager entityManager;
    private final Express app;
    private final ObjectMapper mapper;
    
    public AmenityRepository(EntityManager entityManager, Express app, ObjectMapper mapper) {
        this.entityManager = entityManager;
        this.app = app;
        this.mapper = mapper;
    }
    
    public Amenity findById(Integer id) {
        return entityManager.find(Amenity.class, id);
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from User").getResultList();
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
