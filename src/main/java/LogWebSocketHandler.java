import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class LogWebSocketHandler {
    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception{
        System.out.println("on user connect" + user.getRemote());
    }
    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason){
        System.out.println("on close"+user.getLocalAddress()+"status code"+statusCode+"reason"+reason);
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message){
        System.out.println("System message from user"+user.getLocalAddress()+"message"+message);
    }
}
