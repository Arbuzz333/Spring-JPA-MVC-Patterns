package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.service.meat.MeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static avakhidov.factories.enums.FatMeat.DIETARY;




public class MeatServiceEasyImpl implements MeatService {

    private static final Logger LOG = LoggerFactory.getLogger(MeatServiceEasyImpl.class);


    @Override
    public FatMeat getLessFatInMeat(FatMeat fatMeat) {

        return DIETARY;
    }

    @Override
    public FatMeat getMoreFatInMeat(FatMeat fatMeat) {

        return FatMeat.SPECK;
    }

    @Override
    public Meat buildMeat(Class<? extends Meat> clazz) {
        return new Meat(KindMeat.CHICKEN, FatMeat.LOW_FAT){};
    }

    @PostConstruct
    public void init() {
        LOG.debug("Bean MeatServiceEasyImpl was created");
    }

}
