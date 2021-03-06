package interfaces;

import models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepoInterface {
    
    
    Optional<?> findById(Integer id);
    
    List<?> findAll();

    List<?> findAllReviewsOnUserId(Integer id);

    List<?> findAllReviewsByPropertyId(Integer id);

    void delete(Integer id);

    Optional<Review> save(Review review);
}
