package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @JsonBackReference(value="User - Transaction")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User sender;
    @JsonBackReference (value="User - ReceivedTransaction")
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;
    @JsonBackReference(value = "Booking - Transaction")
    @OneToOne(mappedBy = "transaction")
    @JoinColumn(name = "bookings_id", referencedColumnName = "id")
    private Booking booking;

    public Transaction() {
    }
    
    public Transaction(double price) {
        this.price = price;
    }

    public Transaction(double price, User user, User receiver) {
        this.price = price;
        this.sender = user;
        this.receiver = receiver;
    }

    public User getUser() {
        return sender;
    }
    
    public void setUser(User user) {
        this.sender = user;
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
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
