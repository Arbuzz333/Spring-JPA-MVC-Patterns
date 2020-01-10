package avakhidov.factories.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EventManager {

    private Map<EventTypeEnum, List<EventListener>> listeners = new HashMap<>();

    public EventManager(EventTypeEnum... operations) {
        for (EventTypeEnum operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(EventTypeEnum eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(EventTypeEnum eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(EventTypeEnum eventType, UUID uuid) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.notifyEventFINISHED(uuid);
        }
    }

    public enum EventTypeEnum {
        FINISHED_TYPE
    }

}
