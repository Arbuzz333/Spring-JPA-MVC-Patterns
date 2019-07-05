package avakhidov.factories.entity.cutlet;

import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;

public abstract class Meat {

    private KindMeat kindMeat;

    private FatMeat fatMeat;

    protected Meat(KindMeat kindMeat, FatMeat fatMeat) {
        this.kindMeat = kindMeat;
        this.fatMeat = fatMeat;
    }

}
