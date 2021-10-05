package util;

import io.javalin.websocket.WsContext;

import java.util.List;

public interface Observer {

    public List<?>  onUpdate(List<?> chatrooms);
}