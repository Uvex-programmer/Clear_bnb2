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
            ws.onConnect(ctx -> ctx.send(socketLogic.onConnectHandler(ctx, ctx.cookie("current-user"))));
            ws.onMessage(ctx -> {
                socketLogic.onMessageHandler(ctx).forEach((msg, list) -> {
                    list.forEach(participant -> participant.send(msg));
                });
            });
            ws.onBinaryMessage(ctx -> System.out.println("Message"));
            ws.onClose(ctx -> {
                socketLogic.removeUserFromRoom(ctx);
                ctx.send("Close.");
            });
            ws.onError(ctx ->{
                socketLogic.removeUserFromRoom(ctx);
                ctx.send("Errored.");
            });
        });
    }
}
