package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public class BuckwheatBun extends Bun {

    public BuckwheatBun(ParameterPrepareDough<BuckwheatFlour> parameterDough, boolean recipeReady, double weight) {
        super(parameterDough, recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        super.getPrepack().setKindDough(
        DoughUtil.setParameterKindDough(super.getPrepack().getFlour().getGrinding()));
    }
}
