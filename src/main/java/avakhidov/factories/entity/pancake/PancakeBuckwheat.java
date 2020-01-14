package avakhidov.factories.entity.pancake;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.ingredient.Confiture;

public class PancakeBuckwheat extends Pancake<BuckwheatFlour> {

    public PancakeBuckwheat(PancakePrepareDough<BuckwheatFlour> mainIngredient, double weight) {
        super(mainIngredient, weight, new Confiture());
    }
}
