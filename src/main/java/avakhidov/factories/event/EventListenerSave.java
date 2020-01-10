package avakhidov.factories.event;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EventListenerSave implements EventListener {

    private Set<UUID> uuidsFINISHED = new HashSet<>();

    @Override
    public void notifyEventFINISHED(UUID uuid) {
        uuidsFINISHED.add(uuid);
    }
}
