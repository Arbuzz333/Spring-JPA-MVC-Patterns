package avakhidov.factories.entity.bun;


import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class WheatBun extends Bun {

    public WheatBun(BuildParameterPrepareDough parameterDough, boolean recipeReady) {
        super(parameterDough.toKneadTheDough(), recipeReady);
    }

    @Override
    protected void setKindDough() {
        super.getPrepareDough().setKindDough(
                DoughUtil.setParameterKindDoughFromAMPM(super.getPrepareDough().getTime())
        );
    }
}
