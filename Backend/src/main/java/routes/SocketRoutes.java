package routes;

import express.Express;
import io.javalin.websocket.WsContext;
import logic.SocketLogic;
import models.Message;
import java.util.List;
import java.util.Map;

import static logic.SocketLogic.chatRoomExists;
import static logic.SocketLogic.isAdmin;

public class SocketRoutes {
    private final Express app;
    private final SocketLogic socketLogic = new SocketLogic();

    public SocketRoutes(Express app) {
        this.app = app;
        this.socketMethods();
    }

    public void socketMethods() {
        app.ws("/websockets/chat", ws -> {
            ws.onConnect(ctx -> { ctx.send(socketLogic.onConnectHandler(ctx, ctx.cookie("current-user"))); });
            ws.onMessage(ctx -> {
                Message msg = socketLogic.onMessageHandler(ctx);
                String room_id = ctx.message(Message.class).getChatroom_id();
                Map<String, List<WsContext>> chatRooms = socketLogic.getChatRooms();
                if(isAdmin(room_id)) {
                    msg.setIs_support(true);
                    List<WsContext> chatRoom = socketLogic.getUsersFromRoom(room_id);
                    if(!chatRoom.contains(ctx)) { socketLogic.addToRoom(room_id, ctx); }

                    socketLogic.getUsersFromRoom(room_id).forEach(client -> { client.send(msg); });
                }
                else if (chatRoomExists(chatRooms, ctx)) {
                    socketLogic.createChatRoom(ctx, msg).forEach(worker -> { worker.send(msg); });
                    ctx.send(msg);
                }
                else {
                    chatRooms.get(ctx.cookie("id")).forEach((participant) -> { participant.send(msg); });
                }
            });
            ws.onBinaryMessage(ctx -> System.out.println("Message"));
            ws.onClose(ctx -> ctx.send("Close."));
            ws.onError(ctx -> ctx.send("Error."));
        });
    }
}
