package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reviews")
@NamedQueries({
        @NamedQuery(name = "Review.findById",
                query = "SELECT r FROM Review r WHERE r.id = :id"),
        @NamedQuery(name = "Review.findAllReviewsByUserId",
                query = "SELECT r FROM Review r WHERE r.user.id = :id"),
        @NamedQuery(name = "Review.findAllReviewsByPropertyId",
                query = "SELECT r.id, r.comment, r.rating, r.user.id, r.user.firstName, r.createdAt FROM Review r WHERE r.property.id = :id"),
        @NamedQuery(name = "Review.findAllReviewsOnUserId",
                query = "SELECT r.id, r.comment, r.rating FROM Review r WHERE r.reviewUser.id = :id")
})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "review_user_id", referencedColumnName = "id")
    private User reviewUser;
    private int rating;
    private String comment;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;
    @JsonBackReference (value="User - Review")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
   
    @Column(name = "created_at")
    private Date createdAt;
    
    public Review() {
    }
    
    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getPropertyId() {
//        return propertyId;
//    }
//
//    public void setPropertyId(int propertyId) {
//        this.propertyId = propertyId;
//    }
    
    
    public Property getProperty() {
        return property;
    }
    
    public void setProperty(Property property) {
        this.property = property;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", property=" + property +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
