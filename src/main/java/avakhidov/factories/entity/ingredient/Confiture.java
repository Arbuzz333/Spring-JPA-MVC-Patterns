package avakhidov.factories.entity.ingredient;

import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.service.MainIngredient;

import java.io.Serializable;

public class Confiture implements MainIngredient, Serializable {

    @Override
    public MainIngredientEnum getMainIngredient() {
        return MainIngredientEnum.CONFITURE;
    }
}
