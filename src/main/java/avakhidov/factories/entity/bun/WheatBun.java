package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.enums.dough.DoughUtil;

public class WheatBun extends Bun {

    public WheatBun(ParameterPrepareDough<WheatFlour> prepareDough, boolean recipeReady, double weight) {
        super(prepareDough, recipeReady, weight);
    }

    @Override
    public void setKindDough() {
        super.getMainIngredient().setKindDough(
                DoughUtil.setParameterKindDoughFromAMPM(super.getMainIngredient().getTime())
        );
    }
}
