package avakhidov.data;

import java.util.UUID;

public abstract class BaseEvent {

    private UUID uuid;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
