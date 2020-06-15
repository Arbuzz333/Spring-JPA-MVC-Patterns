package avakhidov.services;

import avakhidov.data.BaseEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService<T extends BaseEvent> extends BaseInterface{

    List<String> getDescriptionEvents(List<T> events);

}
