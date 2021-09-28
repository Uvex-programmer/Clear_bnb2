package mapper;

import DTO.ReviewDTO;
import models.Review;

import java.util.Optional;

public class ReviewMapper {

    public ReviewDTO propertyReviewToDTO(Optional<Review> review) {
        return new ReviewDTO(review.get().getId(), review.get().getComment(), review.get().getRating(),
                review.get().getUser().getId(), review.get().getUser().getFirstName(), review.get().getProperty().getId());
    }

    public ReviewDTO userReviewToDTO(Optional<Review> review) {
        return new ReviewDTO(review.get().getId(), review.get().getComment(), review.get().getRating(),
                review.get().getUser().getId(), review.get().getUser().getFirstName(), review.get().getReviewUser().getId());
    }
}
