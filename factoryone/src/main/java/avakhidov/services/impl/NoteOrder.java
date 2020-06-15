package avakhidov.services.impl;

import avakhidov.anotations.NewClass;
import avakhidov.data.NoteEvent;
import avakhidov.enumes.Priority;
import avakhidov.services.OrderService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static avakhidov.utils.UtilsForString.concatWithSpaceOrEmpty;



@Service
@Order(3)
@NewClass(newClass = NoteOrderNew.class)
public class NoteOrder implements OrderService<NoteEvent> {

    @Override
    public List<String> getDescriptionEvents(List<NoteEvent> events) {
        List<String> descriptions = new ArrayList<>();

        for (NoteEvent noteEvent : events) {
            String description = noteEvent.getDescription();
            Priority priority = noteEvent.getPriority();
            String concat = concatWithSpaceOrEmpty(description, priority.name());
            descriptions.add(concat);
        }
        return descriptions;
    }

    @Override
    public Class getEventType() {
        return NoteEvent.class;
    }
}
