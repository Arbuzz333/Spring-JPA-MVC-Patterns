package avakhidov.factories.service.meat.entity;

import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.service.meat.enums.MeatCode;



public class RecipePorkMeat extends RecipeMeat {

    private Pig partPig;

    protected RecipePorkMeat(MeatCode meatCode, Pig partPig) {
        this.meatCode = meatCode;
        this.partPig = partPig;
    }

    public Pig getLivestock() {
        return partPig;
    }
}
