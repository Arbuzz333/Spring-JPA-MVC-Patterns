package avakhidov.services.impl;

import avakhidov.data.CalendarEvent;
import avakhidov.services.OrderService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(1)
public class CalendarOrder implements OrderService<CalendarEvent> {

    @Override
    public List<String> getDescriptionEvents(List<CalendarEvent> events) {

        List<String> collect = events.stream()
                .map(e -> e.getDateTime().toString())
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public Class getEventType() {
        return CalendarEvent.class;
    }

}
