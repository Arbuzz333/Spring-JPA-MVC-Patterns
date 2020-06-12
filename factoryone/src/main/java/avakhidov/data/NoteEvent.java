package avakhidov.data;

import avakhidov.enumes.Priority;

public class NoteEvent extends BaseEvent {

    private String description;

    private Priority priority;

    public NoteEvent(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }
}
