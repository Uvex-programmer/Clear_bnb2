package util;

import io.javalin.websocket.WsContext;
import routes.Message;

import java.util.List;

public interface Observer {
    public void onUpdate(List<?> chatrooms);
}