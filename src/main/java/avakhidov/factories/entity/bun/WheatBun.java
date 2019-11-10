package avakhidov.factories.entity.bun;


import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public class WheatBun extends Bun {

    public WheatBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, int temperature, LocalTime time, double weight) {
        super(parameterDough.toKneadTheDough(temperature, time), recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        super.getPrepack().setKindDough(
                DoughUtil.setParameterKindDoughFromAMPM(super.getPrepack().getTime())
        );
    }
}
