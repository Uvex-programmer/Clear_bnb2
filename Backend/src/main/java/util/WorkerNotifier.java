package util;

import io.javalin.websocket.WsContext;
import repositories.MessageRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerNotifier {
    List<CustomerSupport> customerSupportWorkers = new ArrayList<>();
    MessageRepository messageRepository = new MessageRepository();

    public void add(CustomerSupport worker) {
        customerSupportWorkers.add(worker);
        notifyObserver(worker.getId());
    }

    public void notifyObserver(String id) {
        List<?> chatRooms = getChatRooms();
        for (CustomerSupport worker : customerSupportWorkers) {
            if(worker.getId() == id) {
                worker.onUpdate(chatRooms);
            }
        }
    }

    public List<?> getChatRooms() {
       return messageRepository.uniqueOpenThreads();
    }

    public void notifyAllObservers(String id) {
        List<?> chatRooms = getChatRooms();
        for (CustomerSupport worker : customerSupportWorkers) {
            worker.onUpdate(chatRooms);
        }
    }
}
