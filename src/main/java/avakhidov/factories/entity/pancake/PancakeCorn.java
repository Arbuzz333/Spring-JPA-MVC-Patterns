package avakhidov.factories.entity.pancake;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.ingredient.CottageCheese;

public class PancakeCorn extends Pancake<CornFlour> {

    public PancakeCorn(PancakePrepareDough<CornFlour> mainIngredient, double weight) {
        super(mainIngredient, weight, new CottageCheese());
    }
}
