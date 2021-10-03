package routes;

import express.Express;
import logic.SocketLogic;

public class SocketRoutes {

    private final Express app;
    private final SocketLogic socketLogic = new SocketLogic();
    public SocketRoutes(Express app) {
        this.app = app;
        this.socketMethods();
    }

    public void socketMethods() {
        app.ws("/websockets/chat", ws -> {
            ws.onConnect(ctx -> ctx.send(socketLogic.connectedMessage(ctx, ctx.cookie("current-user"))));
            ws.onMessage(ctx -> {
                Message msg = socketLogic.onMessageHandler(ctx);
                if(ctx.cookie("current-user") != null && ctx.cookie("current-user").equals("111") || socketLogic.getChatRooms().containsKey(ctx.cookie("id"))) {
                    socketLogic.createChatRoom(ctx, msg);
                    socketLogic.getClients().forEach(client -> {
                        client.send(msg);
                    });
                } else {
                    ctx.send(msg);
                }
            });
            ws.onBinaryMessage(ctx -> System.out.println("Message"));
            ws.onClose(ctx -> ctx.send(socketLogic.removeClient(ctx, "Close.")));
            ws.onError(ctx -> ctx.send(socketLogic.removeClient(ctx, "Error.")));
        });
    }
}
