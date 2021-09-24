package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Review;
import repositories.ReviewRepository;

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
        app.get("/api/get-reviews-on-user/:id", (req, res) -> {
            var id = Integer.parseInt(req.params("id"));
            var reviews = reviewRepository.findAllByReviewUserId(44);
            res.json(mapper.writeValueAsString(reviews));
        });
        app.get("/api/get-reviews-made-by-user/:id", (req, res) -> {
            var id = Integer.parseInt(req.params("id"));
            var reviews = reviewRepository.findAllReviewsByUserId(id);
            res.json(mapper.writeValueAsString(reviews));
        });
    }
}
