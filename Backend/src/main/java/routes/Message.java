package routes;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "messages" )
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "message")
    private String msg;
    private java.sql.Timestamp time_sent;
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private Boolean is_support;
    @Column(name = "user_id")
    private String userId;
    private String chatroom_id;
    @CreationTimestamp
    private java.sql.Timestamp created_at;


    public Message() {

    }

    public Message(String msg, java.sql.Timestamp time_sent, String userId, String chatroom_id) {
        this.msg = msg;
        this.time_sent = time_sent;
        this.userId = userId;
        this.chatroom_id = chatroom_id;
    }

    @Override
    public String toString() {
        return "SocketMsg{ " +
                "msg='" + msg + '\'' +
                " }";
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getIs_support() {
        return is_support;
    }

    public void setIs_support(Boolean is_support) {
        this.is_support = is_support;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getTime_sent() {
        return time_sent;
    }

    public void setTime_sent(Timestamp time_sent) {
        this.time_sent = time_sent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatroom_id() {
        return chatroom_id;
    }

    public void setChatroom_id(String chatroom_id) {
        this.chatroom_id = chatroom_id;
    }

}