package avakhidov.factories.entity.bun;


import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.entity.ingredient.SupplementIngredientDecorator;
import avakhidov.factories.enums.dough.DoughUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WheatBun extends Bun {

    private LocalTime localTime;

    private List<Ingredient> additionalIngredient;

    public WheatBun(ParameterPrepareDough<WheatFlour> prepareDough, boolean recipeReady, double weight) {
        super(prepareDough, recipeReady, weight);
        additionalIngredient = new ArrayList<>();
    }

    @Override
    public void setKindDough() {
        if (localTime == null) {
            localTime = LocalTime.now();
        }
        super.getMainIngredient().setKindDough(
                DoughUtil.setParameterKindDoughFromAMPM(localTime)
        );
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public void supplement(SupplementIngredientDecorator supplementIngredient) {
        this.additionalIngredient.addAll(supplementIngredient.addIngredients());
    }

    public List<Ingredient> getAdditionalIngredient() {
        return additionalIngredient;
    }
}
