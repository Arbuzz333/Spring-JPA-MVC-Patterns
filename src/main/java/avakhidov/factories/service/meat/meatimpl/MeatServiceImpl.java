package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.service.meat.MeatService;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static avakhidov.factories.enums.FatMeat.*;


public class MeatServiceImpl implements MeatService {

    final Map<Class<? extends Meat>, Meat> classMeatMap = new TreeMap<>(Comparator.comparing(Class::getName));

    @Override
    public FatMeat getLessFatInMeat(FatMeat fatMeat) {
        FatMeat[] fatMeats = FatMeat.values();
        if (fatMeat.ordinal() == 3) {
            return fatMeat;
        }
        return fatMeats[fatMeat.ordinal() + 1];
    }

    @Override
    public FatMeat getMoreFatInMeat(FatMeat fatMeat) {
        if (fatMeat.getMax() >= MEDIUMFAT.getMax()) {
            return SPECK;
        }
        if (fatMeat.getMax() == DIETARY.getMax()) {
            return LOWFAT;
        } else {
            return MEDIUMFAT;
        }
    }

    public Meat buildMeat(Class<? extends Meat> clazz) {
        return classMeatMap.get(clazz);
    }
}
