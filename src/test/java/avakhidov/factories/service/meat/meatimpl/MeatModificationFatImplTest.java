package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.livestock.Sheep;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.entity.meat.MuttonMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.service.meat.MeatModificationFat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MeatModificationFatImplTest {

    @Autowired
    private MeatModificationFat modificationFat;


    @Test
    public void createMeatMoreFat() {
        Meat meat = new ChickenMeat(FatMeat.DIETARY, new Chicken());

        Meat meatMoreFat = modificationFat.createMeatMoreFat(meat);

        assertEquals(meatMoreFat.getFatMeat(), FatMeat.SPECK);
    }

    @Test
    public void createMeatLessFat() {
        Meat meat = new MuttonMeat(FatMeat.SPECK, new Sheep());
        Meat meatLessFat = modificationFat.createMeatLessFat(meat);

        assertEquals(meatLessFat.getFatMeat(), FatMeat.DIETARY);
    }
}
