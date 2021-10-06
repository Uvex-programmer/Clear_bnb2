package logic;

import DTO.ReviewDTO;
import mapper.ReviewMapper;
import models.Booking;
import models.Property;
import models.Review;
import models.User;
import repositories.BookingRepository;
import repositories.PropertyRepository;
import repositories.ReviewRepository;
import repositories.UserRepository;

import java.util.*;

public class ReviewLogic {
    ReviewRepository reviewRepository = new ReviewRepository();
    PropertyRepository propertyRepository = new PropertyRepository();
    BookingRepository bookingRepository = new BookingRepository();
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
        Optional<User> user = userRepository.findById(reviewDTO.getUserId());
        Optional<Property> property = propertyRepository.findById(reviewDTO.getReviewId());
        Optional<Review> review = reviewRepository.save(reviewMapper.propertyReviewDTOtoEntity(reviewDTO, user, property));
        return reviewMapper.propertyReviewToDTO(review);
    }
    public ReviewDTO addUserReview(ReviewDTO reviewDTO) {
        Optional<User> user = userRepository.findById(reviewDTO.getUserId());
        Optional<User> userReviewed = userRepository.findById(reviewDTO.getReviewId());
        Optional<Review> review = reviewRepository.save(reviewMapper.userReviewDTOtoEntity(reviewDTO, user, userReviewed));
        return reviewMapper.userReviewToDTO(review);
    }

    public Map<String, Boolean> checkCanReviewProperty(Integer propertyId, Integer userId) {
        Map<String, Boolean> isAllowed = new HashMap<>();
        List<Booking> bookings = bookingRepository.findBookingByPropertyId(propertyId, userId);
        if(bookings.size() < 1) {
            isAllowed.put("isAllowed", false);
        }else{
            List<Review> reviews = reviewRepository.findUserReviewsOnProperty(propertyId, userId);
            if(reviews.size() < 1) {
                isAllowed.put("isAllowed", true);
            }else{
                isAllowed.put("isAllowed", false);
            }
        }
        return isAllowed;
    }

    public Map<String, Boolean> checkCanReviewUser(Integer userReviewId, Integer userId) {
        Map<String, Boolean> isAllowed = new HashMap<>();
        var bookings = bookingRepository.findBookingByUser(userReviewId, userId);
        if(bookings.size() < 1) {
            isAllowed.put("isAllowed", false);
        }else{
            List<Review> reviews = reviewRepository.findUserReviewsOnUser(userReviewId, userId);
            if(reviews.size() < 1) {
                isAllowed.put("isAllowed", true);
            }else{
                isAllowed.put("isAllowed", false);
            }
        }
        return isAllowed;
    }

}
