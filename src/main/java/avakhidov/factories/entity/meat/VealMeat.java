package avakhidov.factories.entity.meat;

import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public class VealMeat extends Meat {

    private Calf partCaif;

    protected VealMeat(KindMeat kindMeat, FatMeat fatMeat) {
        super(kindMeat, fatMeat);
    }

}
