package avakhidov.factories.entity.bun;


import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public class CornBun extends Bun {

    public CornBun(BuildParameterPrepareDough parameterDough, boolean recipeReady, int temperature, LocalTime time) {
        super(parameterDough.toKneadTheDough(temperature, time), recipeReady);
    }

    @Override
    protected void setKindDough() {
        super.getPrepareDough().setKindDough(
                DoughUtil.setParameterKindDoughFromFlour(super.getPrepareDough().getFlour().getKind())
        );

    }
}
