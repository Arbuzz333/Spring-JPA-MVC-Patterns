package avakhidov.services.impl;

import avakhidov.anotations.TimesOfDayModification;
import avakhidov.data.BaseEvent;
import avakhidov.data.NoteEvent;
import avakhidov.enumes.Priority;
import avakhidov.enumes.TimesOfDay;
import avakhidov.services.Action;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static avakhidov.utils.UtilsForString.concatWithSpaceOrEmpty;

@Service
@TimesOfDayModification(mod = TimesOfDay.NIGHT)
public class NoteAction implements Action<NoteEvent> {

    @Override
    public String getAction(NoteEvent type) {

        String description = type.getDescription();
        Priority priority = type.getPriority();
        String concat = concatWithSpaceOrEmpty(description, priority.name());

        return concat;
    }

    @Override
    public Class getEventType() {
        return NoteEvent.class;
    }
}
