package avakhidov.factories.service.meat.entity;

import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.service.meat.enums.MeatCode;


public class RecipeVealMeat extends RecipeMeat{

    private Calf partCalf;

    protected RecipeVealMeat(MeatCode meatCode, Calf partCalf) {
        this.meatCode = meatCode;
        this.partCalf = partCalf;
    }

    public Calf getLivestock() {
        return partCalf;
    }
}
