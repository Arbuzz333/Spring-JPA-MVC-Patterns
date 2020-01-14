package avakhidov.factories.entity.dough.pancakedough;

import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.ingredient.Egg;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;

import java.time.LocalTime;

public abstract class PancakePrepareDough<T extends Flour> extends ParameterPrepareDough<T> {

    private Egg egg;

    public PancakePrepareDough(T flourDough, KindDough kindDoughParameter,
                               int temperature, LocalTime time, double fatParameter, Egg egg) {
        super(flourDough, kindDoughParameter, temperature, time, fatParameter);
        this.egg = egg;
    }

    @Override
    public MainIngredientEnum getMainIngredient() {
        return MainIngredientEnum.PREPARE_PANCAKE_DOUGH;
    }

    public Egg getEgg() {
        return egg;
    }
}
