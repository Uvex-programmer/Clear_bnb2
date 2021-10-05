package util;

import io.javalin.websocket.WsContext;
import repositories.MessageRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject {
    List<Observer> customerSupportWorkers = new ArrayList<>();
    HashMap<String, List<WsContext>> chatRooms =  new HashMap<>();
    MessageRepository messageRepository = new MessageRepository();

    public void add(Observer worker) {
        customerSupportWorkers.add(worker);
    }

    public void notifyObservers(String id) {
        List<?> chatrooms = messageRepository.uniqueOpenThreads();
       // chatRooms.put(id, participants);
        for (Observer observer : customerSupportWorkers) {
            observer.onUpdate(chatrooms);
        }
    }
}
