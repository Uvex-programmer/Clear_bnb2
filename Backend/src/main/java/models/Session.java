package models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    @CreationTimestamp
    private java.sql.Timestamp created_at;
//    private java.sql.Timestamp expire_at;
    @UpdateTimestamp
    private java.sql.Timestamp last_modified;

    public Session() {}

    public Session(String email) {
        this.email = email;
    }
}

