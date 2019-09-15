package avakhidov.factories.entity.bun;


import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class BuckwheatBun extends Bun {

    public BuckwheatBun(BuildParameterPrepareDough parameterDough, boolean recipeReady) {
        super(parameterDough.toKneadTheDough(), recipeReady);
    }

    @Override
    protected void setKindDough() {
        super.getPrepareDough().setKindDough(
        DoughUtil.setParameterKindDough(super.getPrepareDough().getFlour().getGrinding()));
    }
}
