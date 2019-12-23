package avakhidov.factories.entity.ingredient;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplementIngredientDecorator {

    private List<SupplementIngredient> ingredients;

    public void setIngredients(SupplementIngredient ...ingredients) {
        this.ingredients = Arrays.asList(ingredients);
    }

    public List<Ingredient> addIngredients() {
        return ingredients.stream().map(SupplementIngredient::addIngredient).collect(Collectors.toList());
    }
}
