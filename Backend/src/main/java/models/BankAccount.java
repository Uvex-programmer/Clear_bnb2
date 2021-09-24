package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double funds;

    @JsonBackReference(value="User - Account")
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    public BankAccount() {
    }
    
    public BankAccount(double funds) {
        this.funds = funds;
    }
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public double getFunds() {
        return funds;
    }
    
    public void setFunds(double funds) {
        this.funds = funds;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "BankAccount{" +
                "funds=" + funds +
                ", user=" + user +
                '}';
    }
}
