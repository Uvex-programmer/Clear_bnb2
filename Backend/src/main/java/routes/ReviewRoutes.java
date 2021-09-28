package routes;

import DTO.ReviewDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import logic.ReviewLogic;
import mapper.ReviewMapper;
import models.Review;
import repositories.ReviewRepository;
import java.util.List;

public class ReviewRoutes {

    private final Express app;
    private final ReviewRepository reviewRepository;
    ReviewLogic reviewLogic = new ReviewLogic();
    private final ObjectMapper mapper;

    public ReviewRoutes(Express app, ObjectMapper mapper, ReviewRepository reviewRepository) {
        this.app = app;
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
        this.reviewMethods();
    }

    public void reviewMethods() {

        app.post("/api/add-review", (req, res) -> {
            res.json(reviewLogic.addReview(req.body(Review.class)));
        });

        app.get("/api/get-reviews-on-property/:id", (req, res) -> {
            res.json(reviewLogic.findReviewsOnProperty(Integer.parseInt(req.params("id"))));

        });

        app.get("/api/get-reviews-on-user/:id", (req, res) -> {
            res.json(reviewLogic.findReviewsOnUser(Integer.parseInt(req.params("id"))));
        });

        app.delete("/api/delete-review/:id", (req, res) -> {
            reviewRepository.delete(Integer.parseInt(req.params("id")));
        });
    }
}
