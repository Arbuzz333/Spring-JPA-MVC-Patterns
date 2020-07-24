package avakhidov.factories.service.meat.meatimpl;

import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.service.meat.entity.RecipeChickenMeat;
import avakhidov.factories.service.meat.entity.RecipeMeat;
import avakhidov.factories.service.meat.enums.MeatCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;




@RunWith(SpringRunner.class)
@SpringBootTest
public class MeatFactoryObjectTest {

    @Autowired
    private MeatFactoryObject factory;

    @Test(expected = UnsupportedOperationException.class)
    public void createThrows() {
        RecipeMeat recipeMeat = new RecipeChickenMeat(MeatCode.CHICKEN_LOW_FAT, new Chicken());

        ChickenMeat meat = factory.create(recipeMeat);

        assertEquals(meat.getFatMeat(), MeatCode.CHICKEN_LOW_FAT.getFatMeat());
    }


    @Test()
    public void create() {
        RecipeMeat recipeMeat = new RecipeChickenMeat(MeatCode.CHICKEN_DIETARY, new Chicken());

        ChickenMeat meat = factory.create(recipeMeat);

        assertEquals(meat.getFatMeat(), MeatCode.CHICKEN_DIETARY.getFatMeat());
    }
}