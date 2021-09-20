package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @Transient
    private LocalDateTime created_at;
    //    @OneToMany
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private List<Review> reviews;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    
    @OneToMany(mappedBy = "receiver")
    private List<Transaction> receivedTransactions;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Property> properties = new ArrayList<>();
    
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BankAccount account;
    
    public User() {
    }
    
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    public void addProperty(Property property) {
        properties.add(property);
        property.setUser(this);
    }
    
    public void addAccount(BankAccount account) {
        account.setUser(this);
        this.setAccount(account);
    }
    
    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setUser(this);
    }
    
    public void addTransaction(Transaction transaction, User user, Booking booking) {
        transactions.add(transaction);
        transaction.setUser(this);
        user.receivedTransactions.add(transaction);
        transaction.setReceiver(user);
        booking.setTransaction(transaction);
        transaction.setBooking(booking);
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }
    
    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    public BankAccount getAccount() {
        return account;
    }
    
    public void setAccount(BankAccount account) {
        this.account = account;
    }
    
    //    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

//    public List<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(List<Booking> bookings) {
//        this.bookings = bookings;
//    }
//
//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(List<Transaction> transactions) {
//        this.transactions = transactions;
//    }
//
//    public List<Transaction> getReceivedTransactions() {
//        return receivedTransactions;
//    }
//
//    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
//        this.receivedTransactions = receivedTransactions;
//    }
    
    public List<Property> getProperties() {
        return properties;
    }
    
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}


