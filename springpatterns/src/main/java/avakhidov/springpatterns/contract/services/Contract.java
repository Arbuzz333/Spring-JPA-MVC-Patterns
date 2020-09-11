package avakhidov.springpatterns.contract.services;


import org.springframework.stereotype.Component;

@Component
public interface Contract<T> {

    T getContent();

}
