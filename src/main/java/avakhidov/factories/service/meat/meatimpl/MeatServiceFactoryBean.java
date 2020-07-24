package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.livestock.Calf;
import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.livestock.Pig;
import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.meat.VealMeat;
import avakhidov.factories.enums.FatMeat;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class MeatServiceFactoryBean implements FactoryBean<MeatServiceImpl>, InitializingBean {

    private MeatServiceImpl meatService = new MeatServiceImpl();

    @Override
    public MeatServiceImpl getObject() {
        return meatService;
    }

    @Override
    public Class<?> getObjectType() {
        return MeatServiceImpl.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() {
        meatService.classMeatMap.put(ChickenMeat.class, new ChickenMeat(FatMeat.DIETARY, new Chicken()));
        meatService.classMeatMap.put(MuttonMeat.class, new MuttonMeat(FatMeat.MEDIUM_FAT, new Sheep()));
        meatService.classMeatMap.put(PorkMeat.class, new PorkMeat(FatMeat.SPECK, new Pig()));
        meatService.classMeatMap.put(VealMeat.class, new VealMeat(FatMeat.LOW_FAT, new Calf()));
    }
}
