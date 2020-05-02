package avakhidov.factories.listeners;

import avakhidov.factories.entity.cutlet.Cutlet;
import org.springframework.context.ApplicationEvent;

public class GenericSpringEvent<T extends Cutlet> extends ApplicationEvent {

    private T cutlet;

    private boolean hasSesameBun;

    public GenericSpringEvent(T cutlet) {
        super(cutlet);
        this.cutlet = cutlet;
        this.hasSesameBun = cutlet.getSesameBun() != null;
    }

    public T getCutlet() {
        return cutlet;
    }

    public boolean isHasSesamBun() {
        return hasSesameBun;
    }
}
