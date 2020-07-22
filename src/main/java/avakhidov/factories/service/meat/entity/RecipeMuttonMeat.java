package avakhidov.factories.service.meat.entity;

import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.service.meat.enums.MeatCode;




public class RecipeMuttonMeat extends RecipeMeat{

    private Sheep partSheep;

    protected RecipeMuttonMeat(MeatCode meatCode, Sheep partSheep) {
        this.meatCode = meatCode;
        this.partSheep = partSheep;
    }

    public Sheep getLivestock() {
        return partSheep;
    }

}
