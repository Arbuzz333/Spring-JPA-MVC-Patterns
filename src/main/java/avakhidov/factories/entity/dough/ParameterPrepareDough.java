package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.dough.KindDough;

import java.time.LocalTime;

public class ParameterPrepareDough<T extends Flour> {

    private int temperature;

    private LocalTime time;

    private T flour;

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
}
