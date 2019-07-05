package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public class ChickenMeat extends Meat {

    Chicken partChicken;

    public ChickenMeat(FatMeat fatMeat, Chicken partChicken) {
        super(KindMeat.CHICKEN, fatMeat);
        this.partChicken = partChicken;
    }

}
