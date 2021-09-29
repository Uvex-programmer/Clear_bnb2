package mapper;

import DTO.ReviewDTO;
import models.Property;
import models.Review;
import models.User;

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

    public Review propertyReviewDTOtoEntity(ReviewDTO reviewDTO, Optional<User> user, Optional<Property> property){
        Review review = new Review();
        review.setProperty(property.get());
        review.setUser(user.get());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        return review;
    }
    public Review userReviewDTOtoEntity(ReviewDTO reviewDTO, Optional<User> user, Optional<User> reviewedUser ){
        Review review = new Review();
        review.setReviewUser(reviewedUser.get());
        review.setUser(user.get());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        return review;
    }
}
