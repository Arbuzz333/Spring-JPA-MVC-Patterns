package avakhidov.factories.entity.ingredient;

import org.springframework.stereotype.Component;

@Component
public class SupplementPeanutDecorator implements SupplementIngredient {

    @Override
    public Ingredient addIngredient() {
        return new Peanut();
    }
}
