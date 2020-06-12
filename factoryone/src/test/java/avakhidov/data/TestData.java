package avakhidov.data;

import avakhidov.enumes.Priority;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class TestData {


    public List<BaseEvent> createBaseEvents() {

        UUID uuid = UUID.randomUUID();
        CalendarEvent calendarEvent = new CalendarEvent(LocalDateTime.now());
        calendarEvent.setUuid(uuid);
        ContactEvent contactEvent = new ContactEvent("Tom", "8915");
        contactEvent.setUuid(uuid);
        MoneyEvent moneyEvent = new MoneyEvent(new BigDecimal("56"), "description money");
        moneyEvent.setUuid(uuid);
        NoteEvent noteEvent = new NoteEvent("note description", Priority.USUAL);
        noteEvent.setUuid(uuid);

        List<BaseEvent> result = Arrays.asList(calendarEvent, contactEvent, moneyEvent, noteEvent);

        return result;
    }

}
