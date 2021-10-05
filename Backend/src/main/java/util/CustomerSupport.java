package util;

import io.javalin.websocket.WsContext;
import java.util.List;

public class CustomerSupport implements Observer {
    private WsContext worker;
    private String id;


    public CustomerSupport(WsContext worker, String id) {
        this.worker = worker;
        this.id = id;
    }



    @Override
    public List<?> onUpdate(List<?> chatRooms) {
        return chatRooms;
    }


    public WsContext getWorker() {
        return worker;
    }

    public void setWorker(WsContext worker) {
        this.worker = worker;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
