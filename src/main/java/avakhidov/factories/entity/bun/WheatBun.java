package avakhidov.factories.entity.bun;


import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public class WheatBun extends Bun {

    public WheatBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, int temperature, LocalTime time) {
        super(parameterDough.toKneadTheDough(temperature, time), recipeReady);
    }

    @Override
    protected void setKindDough() {
        super.getPrepareDough().setKindDough(
                DoughUtil.setParameterKindDoughFromAMPM(super.getPrepareDough().getTime())
        );
    }
}
