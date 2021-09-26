package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "reviews")
@NamedQueries({
        @NamedQuery(name = "Review.findById",
                query = "SELECT r.id, r.comment, r.rating, r.user.id, r.user.firstName FROM Review r WHERE r.id = :id"),
        @NamedQuery(name = "Review.findAllReviewsByUserId",
                query = "SELECT r FROM Review r WHERE r.user.id = :id"),
    //    @NamedQuery(name = "Review.findAllReviewsByPropertyId",
      //          query = "SELECT r.id, u.review_user_id, r.rating, r.comment, p.property_id, u.user_id, r.created_at FROM Review r, User u, Property p WHERE p.property.id = :ids"),
        @NamedQuery(name = "Review.findAllReviewsOnUserId",
                query = "SELECT r.id, r.comment, r.rating, r.user.id, r.user.firstName FROM Review r WHERE r.reviewUser.id = :id")
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

    @Transient
    @CreationTimestamp
    private Date createdAt;
//    private java.sql.Timestamp created_at;



    public Review() {
    }

    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public User getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(User reviewUser) {
        this.reviewUser = reviewUser;
    }

    public Date getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = createdAt;
    }

    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
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
    
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", property=" + property +
                ", user=" + user +
                ", created at=" + createdAt +
                '}';
    }
}
