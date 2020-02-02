package avakhidov.factories.entity.dough;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.MainIngredient;

import java.time.LocalTime;

public class ParameterPrepareDough<T extends Flour> implements MainIngredient {

    private int temperature;

    private LocalTime time;

    private Dough<T> dough;

    public ParameterPrepareDough(T flourDough, KindDough kindDoughParameter,
                                 int temperature, LocalTime time, double fatParameter) {
        this.temperature = temperature;
        this.time = time;
        this.dough = new Dough.BuilderDough<T>() {{
            withFlour(flourDough);
            withKindDough(kindDoughParameter);
            withFat(fatParameter);
        }}.buildDough();
    }

    public T getFlour() {
        return dough.getFlour();
    }

    public void setKindDough(KindDough kind) {
        this.dough = new Dough.BuilderDough<T>() {{
            withFlour(dough.getFlour());
            withKindDough(kind);
            withFat(dough.getFat());
        }}.buildDough();
    }

    public void setKindDoughAndFat(KindDough kindDoughBuild, double fatBuild) {
        this.dough = new Dough.BuilderDough<T>() {{
            withFlour(dough.getFlour());
            withKindDough(kindDoughBuild);
            withFat(fatBuild);
        }}.buildDough();
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

    @Override
    public MainIngredientEnum getMainIngredient() {
        return MainIngredientEnum.PARAMETER_PREPARE_DOUGH;
    }
}
