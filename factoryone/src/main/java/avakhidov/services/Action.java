package avakhidov.services;

import avakhidov.data.BaseEvent;

public interface Action<T extends BaseEvent> extends BaseInterface{

    String getAction(T type);

}
