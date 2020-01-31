package avakhidov.factories.service.orders;

import avakhidov.factories.entity.livestock.Chicken;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersMakerTest {

    @Autowired
    private OrdersMaker ordersMaker;

    @Test
    public void makeProductListTest() throws Throwable {
        ordersMaker.init();
        ordersMaker.makeProductList(15, new ChickenMeat(FatMeat.LOWFAT, new Chicken()));
    }

}