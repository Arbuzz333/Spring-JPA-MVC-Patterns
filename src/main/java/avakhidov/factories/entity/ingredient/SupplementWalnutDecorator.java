package avakhidov.factories.entity.ingredient;

import org.springframework.stereotype.Component;

@Component
public class SupplementWalnutDecorator implements SupplementIngredient {

    @Override
    public Ingredient addIngredient() {
        return new Walnut();
    }
}
