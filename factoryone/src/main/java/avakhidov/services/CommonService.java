package avakhidov.services;

import avakhidov.data.BaseEvent;
import avakhidov.data.CalendarEvent;
import avakhidov.data.MoneyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommonService {

    @Autowired
    private List<OrderService> orderServices;

    @Autowired
    private List<Action> actions;

    public List<String> createCommonEvents(List<BaseEvent> listEvents) {
        List<String> descriptionEvents = new ArrayList<>();

        Map<? extends Class<? extends BaseEvent>, List<BaseEvent>> mapEvents =
                listEvents
                        .stream()
                        .collect(Collectors.groupingBy(BaseEvent::getClass));

        for (Map.Entry<? extends Class<? extends BaseEvent>, List<BaseEvent>> event : mapEvents.entrySet()) {
                if (event.getKey().equals(CalendarEvent.class)) {
                    descriptionEvents.addAll(orderServices.get(0).getDescriptionEvents(event.getValue()));
                } else if (event.getKey().equals(MoneyEvent.class)) {
                    descriptionEvents.addAll(orderServices.get(1).getDescriptionEvents(event.getValue()));
                }
        }
        return descriptionEvents;
    }

    public<T extends BaseEvent> Map<UUID, List<String>> createCommonAction(List<T> events) {
        Map<UUID, List<String>> descriptions = new HashMap<>();
        for (T event : events) {
            for (Action action : actions) {
                if (event.getClass().equals(action.getEventType())) {
                    String description = action.getAction(event);
                    descriptions.merge(event.getUuid(), Collections.singletonList(description), (v1, v2) -> {
                        v1.addAll(v2);
                        return v1;
                    });
                }
            }
        }
        return descriptions;
    }
}
