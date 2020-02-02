package avakhidov.factories.entity.pancake;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.meat.Meat;

public class PancakeWheat extends Pancake<WheatFlour> {

    public PancakeWheat(PancakePrepareDough<WheatFlour> mainIngredient, double weight, Meat meat) {
        super(mainIngredient, weight, meat);
    }
}
