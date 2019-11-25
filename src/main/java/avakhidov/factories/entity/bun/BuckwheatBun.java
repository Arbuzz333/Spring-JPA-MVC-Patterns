package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.enums.dough.DoughUtil;

public class BuckwheatBun extends Bun {

    public BuckwheatBun(ParameterPrepareDough<BuckwheatFlour> parameterDough, boolean recipeReady, double weight) {
        super(parameterDough, recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        super.getMainIngredient().setKindDough(
        DoughUtil.setParameterKindDough(super.getMainIngredient().getFlour().getGrinding()));
    }
}
