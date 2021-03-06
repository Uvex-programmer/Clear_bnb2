package models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NamedQueries({
        @NamedQuery(name = "Session.deleteById",
                query = "DELETE FROM Session s WHERE s.id = :id")
})

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private int user_id;
    @CreationTimestamp
    private java.sql.Timestamp created_at;

    private java.sql.Timestamp expire_at;
    @UpdateTimestamp
    private java.sql.Timestamp last_modified;



    public Session() {}

    public Session(String email, Timestamp expire_at, int user_id) {
        this.expire_at = expire_at;
        this.email = email;
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Timestamp last_modified) {
        this.last_modified = last_modified;
    }
}

