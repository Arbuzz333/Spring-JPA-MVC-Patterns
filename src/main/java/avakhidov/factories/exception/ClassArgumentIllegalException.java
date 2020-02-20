package avakhidov.factories.exception;

import java.util.UUID;

public class ClassArgumentIllegalException extends RuntimeException implements AttributesAware {

    private UUID uuid;
    private String code;
    private String subsystem;

    public ClassArgumentIllegalException(String message, String code, String subsystem) {
        super(message);
        this.uuid = UUID.randomUUID();
        this.code = code;
        this.subsystem = subsystem;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getSubsystem() {
        return this.subsystem;
    }
}
