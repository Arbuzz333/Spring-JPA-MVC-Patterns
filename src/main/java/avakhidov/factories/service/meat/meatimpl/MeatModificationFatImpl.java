package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.service.meat.MeatModificationFat;
import avakhidov.factories.service.meat.MeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;




@Service
public class MeatModificationFatImpl implements MeatModificationFat {

    @Autowired
    @Lazy
    private MeatService meatServiceEasy;

    public Meat createMeatMoreFat(Meat source) {

        FatMeat moreFatInMeat = meatServiceEasy.getMoreFatInMeat(source.getFatMeat());

        return new Meat(source.getKindMeat(), moreFatInMeat) {};

    }

    public Meat createMeatLessFat(Meat source) {

        return new Meat(source.getKindMeat(), FatMeat.DIETARY) {};

    }
}
