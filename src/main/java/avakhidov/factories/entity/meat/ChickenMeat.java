package avakhidov.factories.entity.meat;

import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public class ChickenMeat extends Meat {

    private Chicken partChicken;

    public ChickenMeat(FatMeat fatMeat, Chicken partChicken) {
        super(KindMeat.CHICKEN, fatMeat);
        this.partChicken = partChicken;
    }

}
