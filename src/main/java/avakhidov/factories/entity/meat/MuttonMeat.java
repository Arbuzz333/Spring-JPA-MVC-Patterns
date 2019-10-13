package avakhidov.factories.entity.meat;

import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public class MuttonMeat extends Meat {

    private Sheep partSheep;

    public MuttonMeat(FatMeat fatMeat, Sheep partSheep) {
        super(KindMeat.MUTTON, fatMeat);
        this.partSheep = partSheep;
    }

}
