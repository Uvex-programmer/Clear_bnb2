package logic;

import DTO.ReviewDTO;
import mapper.ReviewMapper;
import models.Review;
import repositories.PropertyRepository;
import repositories.ReviewRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewLogic {
    ReviewRepository reviewRepository = new ReviewRepository();
    PropertyRepository propertyRepository = new PropertyRepository();
    UserRepository userRepository = new UserRepository();
    ReviewMapper reviewMapper = new ReviewMapper();

    public ArrayList<ReviewDTO> findReviewsOnProperty(Integer id){
        List<Review> reviews = reviewRepository.findAllReviewsByPropertyId(id);
        ArrayList<ReviewDTO> revs = new ArrayList<>();
        for(Review rev : reviews){
            revs.add(reviewMapper.propertyReviewToDTO(Optional.ofNullable(rev)));
        }
        return revs;
    }

    public ArrayList<ReviewDTO> findReviewsOnUser(Integer id){
        List<Review> reviews = reviewRepository.findAllReviewsOnUserId(id);
        ArrayList<ReviewDTO> revs = new ArrayList<>();
        for(Review rev : reviews){
            revs.add(reviewMapper.userReviewToDTO(Optional.ofNullable(rev)));
        }
        return revs;
    }

    public ReviewDTO addPropertyReview(ReviewDTO reviewDTO) {
        var user = userRepository.findById(reviewDTO.getUserId());
        var property = propertyRepository.findById(reviewDTO.getReviewId());
        var review = reviewRepository.save(reviewMapper.propertyReviewDTOtoEntity(reviewDTO, user, property));
        return reviewMapper.propertyReviewToDTO(review);
    }
    public ReviewDTO addUserReview(ReviewDTO reviewDTO) {
        var user = userRepository.findById(reviewDTO.getUserId());
        var userReviewed = userRepository.findById(reviewDTO.getReviewId());
        var review = reviewRepository.save(reviewMapper.userReviewDTOtoEntity(reviewDTO, user, userReviewed));
       return reviewMapper.userReviewToDTO(review);
    }

}
