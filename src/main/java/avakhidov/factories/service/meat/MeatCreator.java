package avakhidov.factories.service.meat;

import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.service.meat.entity.RecipeMeat;
import avakhidov.factories.service.meat.enums.MeatCode;
import avakhidov.factories.service.meat.meatimpl.MeatFactoryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public interface MeatCreator<T extends Meat> {

    T creatMeat(RecipeMeat recipe);

    MeatCode getMeatCode();

    @Autowired
    default void registerMyself(MeatFactoryObject factory) {
        factory.registerMeatCreator(getMeatCode(), this);
    }

}
