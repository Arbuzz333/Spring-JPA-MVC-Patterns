package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.service.meat.MeatService;
import org.springframework.stereotype.Service;

import static avakhidov.factories.enums.FatMeat.*;

@Service
public class MeatServiceImpl implements MeatService {

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
}
