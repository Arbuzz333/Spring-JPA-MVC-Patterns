package avakhidov.factories.entity.ingredient;

import avakhidov.factories.enums.CustomScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static avakhidov.factories.utility.MainUtility.randomInt;


@Component
@Scope(CustomScope.PERIODICAL)
public class IngredientRandom {

    private final SupplementIngredient ingredient;

    public IngredientRandom(
            List<SupplementIngredient> ingredient) {
        SupplementIngredient last = ingredient.get(randomInt(0, ingredient.size() - 1));
        this.ingredient = last;
    }

    public SupplementIngredient getIngredient() {
        return ingredient;
    }
}
