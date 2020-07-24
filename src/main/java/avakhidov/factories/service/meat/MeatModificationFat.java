package avakhidov.factories.service.meat;

import avakhidov.factories.entity.meat.Meat;




public interface MeatModificationFat {

    Meat createMeatMoreFat(Meat source);

    Meat createMeatLessFat(Meat source);


}
