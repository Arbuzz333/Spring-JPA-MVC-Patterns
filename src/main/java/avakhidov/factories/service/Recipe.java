package avakhidov.factories.service;


import java.time.LocalTime;

public interface Recipe<T> {

    T cooked(int temperature, LocalTime time);
}
