package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Review;
import repositories.ReviewRepository;

import java.util.List;

public class ReviewRoutes {

    private final Express app;
    private final ReviewRepository reviewRepository;
    private final ObjectMapper mapper;

    public ReviewRoutes(Express app, ObjectMapper mapper, ReviewRepository reviewRepository) {
        this.app = app;
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
        this.reviewMethods();
    }

    public void reviewMethods() {
        app.post("/api/add-review-user", (req, res) -> {
            Review review = req.body(Review.class);
           // review.addUser(review.getUser());
            reviewRepository.save(review);
            System.out.println(review);
        });
        app.post("/api/add-review-on-property", (req, res) -> {
            Review review = req.body(Review.class);
            review.setUser(review.getUser());
            review.setProperty(review.getProperty());
            // review.addUser(review.getUser());
            reviewRepository.save(review);
            System.out.println(review);
        });
        app.get("/api/get-reviews-on-property/:id", (req, res) -> {
            var id = Integer.parseInt(req.params("id"));
            var reviews = reviewRepository.findAllReviewsByPropertyId(id);
            System.out.println(mapper.writeValueAsString(reviews));
            res.json(mapper.writeValueAsString(reviews) );
        });
        app.get("/api/get-reviews-on-user/:id", (req, res) -> {
            var id = Integer.parseInt(req.params("id"));
            var reviews = reviewRepository.findAllReviewsOnUserId(id);
            System.out.println(mapper.writeValueAsString(reviews));
            res.json(mapper.writeValueAsString(reviews));
        });
        app.get("/api/get-reviews-made-by-user/:id", (req, res) -> {
            var id = Integer.parseInt(req.params("id"));
            var reviews = reviewRepository.findAllReviewsByUserId(id);
            System.out.println(reviews);
            res.json(mapper.writeValueAsString(reviews));
        });
        app.get("/api/delete-review/:id", (req, res) -> {
            var id = Integer.parseInt(req.params("id"));
            reviewRepository.delete(id);
        });
    }
}
