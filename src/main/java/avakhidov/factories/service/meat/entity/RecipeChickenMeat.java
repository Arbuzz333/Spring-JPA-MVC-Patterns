package avakhidov.factories.service.meat.entity;

import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.service.meat.enums.MeatCode;



public class RecipeChickenMeat extends RecipeMeat{

    private Chicken partChicken;

    public RecipeChickenMeat(MeatCode meatCode, Chicken partChicken) {
        this.meatCode = meatCode;
        this.partChicken = partChicken;
    }

    public Chicken getLivestock() {
        return partChicken;
    }
}
