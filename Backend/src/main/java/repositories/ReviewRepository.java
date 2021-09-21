package repositories;

import models.Review;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ReviewRepository {
    
    
    private final EntityManager entityManager;
    
    public ReviewRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Review findById(Integer id) {
        return entityManager.find(Review.class, id);
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from Review").getResultList();
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
