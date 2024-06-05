import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import static j2html.TagCreator.*;
import static spark.Spark.*;

public class Chat {

    // this map is shared between sessions and threads, so it needs to be thread-safe (http://stackoverflow.com/a/2688817)
    static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    static CarRentalSystem carRentalSystem;
    static int nextUserNumber = 1; //Assign to username for next connecting user

    public static void main(String[] args) {
        staticFiles.location("/public"); //index.html is served at localhost:4567 (default port)
        staticFiles.expireTime(86400*1*30*52);
        staticFiles.disableMimeTypeGuessing();
        webSocket("/chat", ChatWebSocketHandler.class);
        webSocket("/log", LogWebSocketHandler.class);
        get("/apiv1",(req,res) -> {
            res.type("application/json");
            return "{\"message\":\"retries\"}";
        });
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("key1","value1");
        carRentalSystem = new CarRentalSystem();
        Car car1 = new Car("ABC");
        car1.returnCar();
        carRentalSystem.addCar(car1);
        Config config = new Config("D:\\spark-websocket-master\\src\\main\\resources\\config.config");
        JSONObject cf = config.readConfig();
        System.out.println(cf.toString());
        init();
    }

    //Sends a message from one user to all users, along with a list of current usernames
    public static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("userMessage", createHtmlMessageFromSender(sender, message))
                    .put("userlist", userUsernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Builds a HTML element with a sender-name, a message, and a timestamp,
    private static String createHtmlMessageFromSender(String sender, String message) {
        Rental rental;
        if(message.equals("rent-car") || message.equals("rent a car")){
            System.out.println("have a user want to rent a car");
            Customer customer = new Customer();
            customer.setCustomerId("082091002466");
            customer.setName("Guest"+sender);
            rental = carRentalSystem.rentCar("ABC", customer, LocalDate.now());

            return article(
                    b(sender + " says:"),
                    span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
                    p(message + rental.rentalDate())
            ).render();
        }
        if(message.equals("return-car") || message.equals("return a car")){
            System.out.println("a user return a car");
        }
        return article(
                b(sender + " says:"),
                span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
                p(message)
        ).render();

    }
}
