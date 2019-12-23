package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.dough.KindDough;

import java.time.LocalTime;

public class ParameterPrepareDough<T extends Flour> {

    private int temperature;

    private LocalTime time;

    private T flourDough;

    private Dough<T> dough;

    public ParameterPrepareDough(T flourDough, KindDough kindDoughParameter,
                                 int temperature, LocalTime time, double fatParameter) {
        this.flourDough = flourDough;
        this.temperature = temperature;
        this.time = time;
        this.dough = new Dough.BuilderDough<T>() {{
            withFlour(flourDough);
            withKindDough(kindDoughParameter);
            withFat(fatParameter);
        }}.buildDough();
    }

    public T getFlour() {
        return flourDough;
    }

    public void setKindDough(KindDough kindDough) {
        this.dough = new Dough<>(flourDough, kindDough, 0.0);
    }

    public void setKindDoughAndFat(KindDough kindDoughBuild, double fatBuild) {
        Dough<T> dough = new Dough.BuilderDough<T>() {{
            withFlour(flourDough);
            withKindDough(kindDoughBuild);
            withFat(fatBuild);
        }}.buildDough();
        this.dough = dough;
    }

    public KindDough getKindDough() {
        return dough.getKindDough();
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
