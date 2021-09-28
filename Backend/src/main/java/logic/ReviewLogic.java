package logic;

import DTO.ReviewDTO;
import mapper.ReviewMapper;
import models.Review;
import repositories.ReviewRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewLogic {
    ReviewRepository reviewRepository = new ReviewRepository();
    ReviewMapper reviewMapper = new ReviewMapper();

    public ArrayList<ReviewDTO> findReviewsOnProperty(Integer id){
        List<Review> reviews = (List<Review>) reviewRepository.findAllReviewsByPropertyId(id);
        ArrayList<ReviewDTO> revs = new ArrayList<>();
        for(Review rev : reviews){
            revs.add(reviewMapper.propertyReviewToDTO(Optional.ofNullable(rev)));
        }
        return revs;
    }

    public ArrayList<ReviewDTO> findReviewsOnUser(Integer id){
        List<Review> reviews = (List<Review>) reviewRepository.findAllReviewsOnUserId(id);
        ArrayList<ReviewDTO> revs = new ArrayList<>();
        for(Review rev : reviews){
            revs.add(reviewMapper.userReviewToDTO(Optional.ofNullable(rev)));
        }
        return revs;
    }

    public ReviewDTO addReview(Review review) {
        var rev = reviewRepository.save(review);
        if(rev.get().getProperty() == null)
            return reviewMapper.userReviewToDTO(rev);
        return reviewMapper.propertyReviewToDTO(rev);
    }

}
