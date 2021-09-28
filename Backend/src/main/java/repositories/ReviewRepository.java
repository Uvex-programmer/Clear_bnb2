package repositories;

import models.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class ReviewRepository implements ReviewRepoInterface {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public ReviewRepository() {
    }
    
    public Optional<Review> findById(Integer id) {
        Review review = entityManager.find(Review.class, id);
        return review != null ? Optional.of(review) : Optional.empty();
    }

    public List<?> findAllReviewsByPropertyId(Integer id){
        return entityManager.createQuery("from Review WHERE property.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    public List<?> findAllReviewsOnUserId(Integer id){
        return entityManager.createQuery("from Review WHERE reviewUser.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from Review").getResultList();
    }

    public void delete(Integer id){
        try {
            entityManager.getTransaction().begin();
            var review = entityManager.find(Review.class, id);
            entityManager.remove(review);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
