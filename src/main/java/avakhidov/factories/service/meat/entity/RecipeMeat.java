package avakhidov.factories.service.meat.entity;

import avakhidov.factories.entity.livestock.Livestock;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.service.meat.enums.MeatCode;



public abstract class RecipeMeat {

    protected MeatCode meatCode;

    public MeatCode getMeatCode() {
        return meatCode;
    }

    public abstract Livestock getLivestock();

}
