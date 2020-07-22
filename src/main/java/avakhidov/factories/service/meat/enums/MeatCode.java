package avakhidov.factories.service.meat.enums;

import avakhidov.factories.enums.FatMeat;

public enum MeatCode {

    CHICKEN_DIETARY(FatMeat.DIETARY),

    CHICKEN_LOW_FAT(FatMeat.DIETARY),

    MUTTON_LOW_FAT(FatMeat.LOW_FAT),

    PORK_SPECK(FatMeat.SPECK),

    VEAL_MEDIUM_FAT(FatMeat.MEDIUM_FAT);

    private final FatMeat fatMeat;

    MeatCode(FatMeat dietary) {
        this.fatMeat = dietary;
    }

    public FatMeat getFatMeat() {
        return fatMeat;
    }
}
