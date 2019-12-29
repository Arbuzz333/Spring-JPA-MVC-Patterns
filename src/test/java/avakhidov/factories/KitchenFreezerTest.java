package avakhidov.factories;


import avakhidov.factories.entity.Product;
import avakhidov.factories.kitchen.KitchenFreezer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenFreezerTest {

    @Autowired
    private KitchenFreezer kitchenFreezer;

    @Test
    public void createCutletWithBunListTest() {
        List<Product> cutletWithBunList = kitchenFreezer.createCutletWithBunList(125);
        assertEquals(cutletWithBunList.size(), 375);
    }
}
