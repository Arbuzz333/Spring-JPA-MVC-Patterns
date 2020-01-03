package avakhidov.factories.entity.meat;

import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.service.MainIngredient;

public abstract class Meat implements MainIngredient {

    private KindMeat kindMeat;

    private FatMeat fatMeat;

    protected Meat(KindMeat kindMeat, FatMeat fatMeat) {
        this.kindMeat = kindMeat;
        this.fatMeat = fatMeat;
    }

    public KindMeat getKindMeat() {
        return kindMeat;
    }

    public FatMeat getFatMeat() {
        return fatMeat;
    }

    public MainIngredientEnum getMainIngredient() {
        return MainIngredientEnum.MEAT;
    }

}
