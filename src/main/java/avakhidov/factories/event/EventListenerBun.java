package avakhidov.factories.event;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class EventListenerBun implements EventListener {

    private Set<UUID> uuidsFINISHED = new HashSet<>();

    @Override
    public void notifyEvent(UUID uuid) {
        uuidsFINISHED.add(uuid);
    }

    public Set<UUID> getUuidsFINISHED() {
        return uuidsFINISHED;
    }
}
