package DTO;

public class ReviewDTO {

    private int id;
    private String comment;
    private int rating;
    private int userId;
    private String username;
    private int reviewId;

    public ReviewDTO() {
    }

    public ReviewDTO(int id, String comment, int rating, int userId, String username, int reviewId) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.userId = userId;
        this.username = username;
        this.reviewId = reviewId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
