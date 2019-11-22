package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

import java.time.LocalTime;

public class CornBun extends Bun {

    public CornBun(ParameterPrepareDough<CornFlour> parameterDough, boolean recipeReady, double weight) {
        super(parameterDough, recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        super.getPrepack().setKindDough(
                DoughUtil.setParameterKindDoughFromFlour(super.getPrepack().getFlour().getKind())
        );

    }
}
