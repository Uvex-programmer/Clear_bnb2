package models;

import javax.persistence.*;

@Entity
@Table( name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private Integer sessionID;
    @Column(unique = true)
    private String email;
    private String password;

    public Worker() {}

    public Worker(String email, String password, Integer sessionID) {
        this.email = email;
        this.password = password;
        this.sessionID = sessionID;
    }

    public Worker(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getSessionID() {
        return sessionID;
    }

    public void setSessionID(Integer sessionID) {
        this.sessionID = sessionID;
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

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", sessionID=" + sessionID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
