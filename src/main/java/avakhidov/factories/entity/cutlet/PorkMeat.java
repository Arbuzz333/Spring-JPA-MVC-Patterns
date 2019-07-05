package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public class PorkMeat extends Meat {

    private Pig partPig;

    public PorkMeat(FatMeat fatMeat, Pig partPig) {
        super(KindMeat.PORK, fatMeat);
        this.partPig = partPig;
    }
}
