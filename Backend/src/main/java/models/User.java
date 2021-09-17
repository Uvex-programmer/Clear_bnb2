package models;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import java.sql.Date;
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
    @Timestamp
    private Date created_at;
    @OneToOne(mappedBy = "account")
    private BankAccount account;
    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Review> reviews;
    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Booking> bookings;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Transaction> transactions;
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private List<Transaction> receivedTransactions;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Property> properties;
    
    public User() {
    }
    
    public User(Integer id, String firstName, String lastName, String email, String password, Date created_at) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
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
    
    public Date getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(Date created_at) {
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

