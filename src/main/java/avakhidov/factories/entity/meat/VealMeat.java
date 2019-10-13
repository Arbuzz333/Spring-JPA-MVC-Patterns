package avakhidov.factories.entity.meat;

import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public class VealMeat extends Meat {

    private Calf partCalf;

    public VealMeat(FatMeat fatMeat, Calf calf) {
        super(KindMeat.VEAL, fatMeat);
        this.partCalf = calf;
    }

}
