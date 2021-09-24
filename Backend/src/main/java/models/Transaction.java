package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @Column(name = "user_id")
//    private int userId;
//    @Column(name = "receiver_id")
//    private int receiverId;
//    @Column(name = "bookings_id", insertable = false, updatable = false)
//    private int bookingId;
    private double price;
    @Transient
    @Column(name = "created_at")
    private Date createdAt;
    @JsonBackReference(value="User - Transaction")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @JsonBackReference (value="User - ReceivedTransaction")
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;
    @ManyToOne
    @JoinColumn(name = "bookings_id", referencedColumnName = "id")
    private Booking booking;
    
    public Transaction() {
    }
    
    public Transaction(double price) {
        this.price = price;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getReceiver() {
        return receiver;
    }
    
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    
    public Booking getBooking() {
        return booking;
    }
    
    public void setBooking(Booking booking) {
        this.booking = booking;
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
//    public int getReceiverId() {
//        return receiverId;
//    }
//
//    public void setReceiverId(int receiverId) {
//        this.receiverId = receiverId;
//    }
//
//    public int getBookingId() {
//        return bookingId;
//    }
//
//    public void setBookingId(int bookingId) {
//        this.bookingId = bookingId;
//    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", receiver=" + receiver +
                ", booking=" + booking +
                '}';
    }
}
