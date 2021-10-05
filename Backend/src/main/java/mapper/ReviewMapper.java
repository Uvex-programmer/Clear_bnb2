package mapper;

import DTO.ReviewDTO;
import models.Property;
import models.Review;
import models.User;

import java.util.Optional;

public class ReviewMapper {

    public ReviewDTO propertyReviewToDTO(Optional<Review> review) {
        var r = review.get();
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(r.getId());
        reviewDTO.setComment(r.getComment());
        reviewDTO.setRating(r.getRating());
        reviewDTO.setUserId(r.getUser().getId());
        reviewDTO.setUsername(r.getUser().getFirstName());
        reviewDTO.setReviewId(r.getProperty().getId());
        return reviewDTO;
    }

    public ReviewDTO userReviewToDTO(Optional<Review> review) {
        var r = review.get();
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(r.getId());
        reviewDTO.setComment(r.getComment());
        reviewDTO.setRating(r.getRating());
        reviewDTO.setUserId(r.getUser().getId());
        reviewDTO.setUsername(r.getUser().getFirstName());
        reviewDTO.setReviewId(r.getReviewUser().getId());
        return reviewDTO;
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
