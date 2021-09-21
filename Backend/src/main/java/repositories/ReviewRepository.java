package repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Review;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ReviewRepository {
    
    
    private final EntityManager entityManager;
    private final Express app;
    private final ObjectMapper mapper;
    
    public ReviewRepository(EntityManager entityManager, Express app, ObjectMapper mapper) {
        this.entityManager = entityManager;
        this.app = app;
        this.mapper = mapper;
    }
    
    public Review findById(Integer id) {
        return entityManager.find(Review.class, id);
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }
    
    public Optional<Review> save(Review review) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(review);
            entityManager.getTransaction().commit();
            return Optional.of(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
