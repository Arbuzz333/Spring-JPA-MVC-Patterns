package avakhidov.factories.entity.bun;


import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class CornBun extends Bun {

    public CornBun(BuildParameterPrepareDough parameterDough, boolean recipeReady) {
        super(parameterDough.toKneadTheDough(), recipeReady);
    }

    @Override
    protected void setKindDough() {
        super.getPrepareDough().setKindDough(
                DoughUtil.setParameterKindDoughFromFlour(super.getPrepareDough().getFlour().getKind())
        );

    }
}
