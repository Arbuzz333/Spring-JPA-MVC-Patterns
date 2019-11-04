package avakhidov.factories.entity.bun;


import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public class BuckwheatBun extends Bun {

    public BuckwheatBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, int temperature, LocalTime time) {
        super(parameterDough.toKneadTheDough(temperature, time), recipeReady);
    }

    @Override
    public void setKindDough() {
        super.getPrepack().setKindDough(
        DoughUtil.setParameterKindDough(super.getPrepack().getFlour().getGrinding()));
    }
}
