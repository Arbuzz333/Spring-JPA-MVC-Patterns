package avakhidov.factories.service.meat;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;

public interface MeatService {

    FatMeat getLessFatInMeat(FatMeat fatMeat);

    FatMeat getMoreFatInMeat(FatMeat fatMeat);

    Meat buildMeat(Class<? extends Meat> clazz);
}
