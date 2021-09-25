package repositories;

import models.Review;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ReviewRepository implements ReviewRepoInterface {
    
    
    private final EntityManager entityManager;
    
    public ReviewRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<Review> findById(Integer id) {
        Review review = entityManager.find(Review.class, id);
        return review != null ? Optional.of(review) : Optional.empty();
    }

    public List<?> findAllByReviewUserId(Integer id){
        return entityManager.createNamedQuery("Review.findAllByReviewUserId")
                .setParameter("id", id)
                .getResultList();
    }

    public List<?> findAllReviewsByUserId(Integer id){
        return entityManager.createNamedQuery("Review.findAllReviewsByUserId")
                .setParameter("id", id)
                .getResultList();
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
