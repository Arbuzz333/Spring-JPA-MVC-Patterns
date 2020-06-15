package avakhidov.services;

import avakhidov.data.BaseEvent;

public interface BaseInterface {

    default Class getEventType() {
        return BaseEvent.class;
    }
}
