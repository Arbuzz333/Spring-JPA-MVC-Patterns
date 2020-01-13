package avakhidov.factories.event;

import java.util.UUID;

public interface EventListener {

    void notifyEvent(UUID uuid);
}
