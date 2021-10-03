package models;

import DTO.BookingDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bookings")
@NamedQueries({
        @NamedQuery(name = "Booking.findById",
                query = "SELECT b FROM Booking b WHERE b.id = :id"),
        @NamedQuery(name = "Booking.findAllByUserId",
                query = "SELECT b FROM Booking b WHERE b.buyer.id = :id"),
})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @JsonBackReference(value = "Property - Bookings")
    private Property property;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @JsonBackReference(value = "User - Booking")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User buyer;
    
    @JsonManagedReference(value = "Booking - Transaction")
    @OneToOne(cascade = CascadeType.ALL)
    private Transaction transaction;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    
    public Booking() {
    }
    
    public Booking(Property property, Date startDate, Date endDate) {
        this.property = property;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Booking(Property property, Date startDate, Date endDate, User user, Transaction transaction) {
        this.property = property;
        this.startDate = startDate;
        this.endDate = endDate;
        this.buyer = user;
        this.transaction = transaction;
    }
    
    public Booking(BookingDTO bookDTO) {
    }
    
    
    public Transaction getTransaction() {
        return transaction;
    }
    
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public User getUser() {
        return buyer;
    }
    
    public void setUser(User user) {
        this.buyer = user;
    }
    
    public Property getProperty() {
        return property;
    }
    
    public void setProperty(Property property) {
        this.property = property;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public User getBuyer() {
        return buyer;
    }
    
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", property=" + property +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", buyer=" + buyer +
                ", createdAt=" + createdAt +
                '}';
    }
}
