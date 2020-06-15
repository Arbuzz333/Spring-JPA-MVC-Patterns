package avakhidov.services.impl;

import avakhidov.data.NoteEvent;
import avakhidov.enumes.Priority;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static avakhidov.utils.UtilsForString.concatWithSpaceOrEmpty;


@Service
@Order(4)
public class NoteOrderNew extends NoteOrder {

    @Override
    public List<String> getDescriptionEvents(List<NoteEvent> events) {
        List<String> descriptions = new ArrayList<>();

        for (NoteEvent noteEvent : events) {
            String description = noteEvent.getDescription();
            Priority priority = noteEvent.getPriority();
            String concat1 = concatWithSpaceOrEmpty(description, priority.name());
            String concat2 = concatWithSpaceOrEmpty(concat1, noteEvent.getUuid().toString());
            descriptions.add(concat2);
        }
        return descriptions;
    }

}
