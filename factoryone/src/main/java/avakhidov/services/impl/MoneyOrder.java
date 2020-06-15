package avakhidov.services.impl;

import avakhidov.data.MoneyEvent;
import avakhidov.services.OrderService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static avakhidov.utils.UtilsForString.concatWithSpaceOrEmpty;

@Service
@Order(2)
public class MoneyOrder implements OrderService<MoneyEvent> {

    @Override
    public List<String> getDescriptionEvents(List<MoneyEvent> events) {

        List<String> collect = events.stream().map(moneyEvent -> {
            BigDecimal money = moneyEvent.getMoney();
            String engineeringString = money.toEngineeringString();
            String description = moneyEvent.getDescription();
            return concatWithSpaceOrEmpty(description, engineeringString);
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public Class getEventType() {
        return MoneyEvent.class;
    }
}
