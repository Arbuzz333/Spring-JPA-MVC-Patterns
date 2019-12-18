package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.dough.KindDough;

import java.time.LocalTime;

public class ParameterPrepareDough<T extends Flour> {

    private int temperature;

    private LocalTime time;

    private T flour;

    /*Todo replace to Dough*/
    private KindDough kindDough;

    public ParameterPrepareDough(T flour, int temperature, LocalTime time) {
        this.flour = flour;
        this.temperature = temperature;
        this.time = time;
    }

    public T getFlour() {
        return flour;
    }

    public void setKindDough(KindDough kindDough) {
        this.kindDough = kindDough;
    }

    public KindDough getKindDough() {
        return kindDough;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParameterPrepareDough)) return false;
        ParameterPrepareDough<?> that = (ParameterPrepareDough<?>) o;
        return temperature == that.temperature &&
                getTime().equals(that.getTime()) &&
                getFlour().equals(that.getFlour()) &&
                getKindDough() == that.getKindDough();
    }

}
