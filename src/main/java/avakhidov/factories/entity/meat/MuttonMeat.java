package avakhidov.factories.entity.meat;

import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public class MuttonMeat extends Meat {

    Sheep partSheep;

    public MuttonMeat(KindMeat kindMeat, FatMeat fatMeat, Sheep partSheep) {
        super(kindMeat, fatMeat);
        this.partSheep = partSheep;
    }

}
