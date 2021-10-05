package routes;
import DTO.ReviewDTO;
import express.Express;
import logic.ReviewLogic;
import models.Review;
import repositories.ReviewRepository;


public class ReviewRoutes {

    private final Express app;
    ReviewRepository reviewRepository = new ReviewRepository();
    ReviewLogic reviewLogic = new ReviewLogic();

    public ReviewRoutes(Express app) {
        this.app = app;
        this.reviewMethods();
    }

    public void reviewMethods() {

        app.post("/api/add-property-review", (req, res) -> {
            res.json(reviewLogic.addPropertyReview(req.body(ReviewDTO.class)));
        });
        app.post("/api/add-user-review", (req, res) -> {
            res.json(reviewLogic.addUserReview(req.body(ReviewDTO.class)));
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

        app.get("/api/check-permission-review-property/:id/:id2", (req, res) -> {
            res.json(reviewLogic.checkCanReviewProperty(Integer.parseInt(req.params("id")), Integer.parseInt(req.params("id2"))));
        });

        app.get("/api/check-permission-review-user/:id/:id2", (req, res) -> {
            res.json(reviewLogic.checkCanReviewUser(Integer.parseInt(req.params("id")), Integer.parseInt(req.params("id2"))));
        });
    }
}
