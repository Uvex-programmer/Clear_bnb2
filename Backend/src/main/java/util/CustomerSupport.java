package util;

import io.javalin.websocket.WsContext;
import java.util.List;

public class CustomerSupport implements Observer {
    WsContext worker; // = new ArrayList<>();

    public void add(WsContext ctx) { this.worker = ctx; }

    @Override
    public void onUpdate(List<?> chatrooms) {
        worker.send(chatrooms);
    }
}
