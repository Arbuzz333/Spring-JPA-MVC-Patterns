package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.enums.dough.DoughUtil;

public class CornBun extends Bun {

    public CornBun(ParameterPrepareDough<CornFlour> parameterDough, boolean recipeReady, double weight) {
        super(parameterDough, recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        super.getMainIngredient().setKindDough(
                DoughUtil.setParameterKindDoughFromFlour(super.getMainIngredient().getFlour().getKind())
        );

    }
}
