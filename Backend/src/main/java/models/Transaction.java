package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "receiver_id")
    private int receiverId;
    @Column(name = "bookings_id")
    private int bookingId;
    private double price;
    @Column(name = "created_at")
    private Date createdAt;
    @OneToMany
    @JoinColumn(name = "bookings_id", referencedColumnName = "id")
    private List<Transaction> transactions;
    
    public Transaction() {
    }
    
    public Transaction(int id, int userId, int receiverId, int bookingId, double price, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.receiverId = receiverId;
        this.bookingId = bookingId;
        this.price = price;
        this.createdAt = createdAt;
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getReceiverId() {
        return receiverId;
    }
    
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
    
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
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
}
