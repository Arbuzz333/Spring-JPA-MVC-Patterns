package avakhidov.factories.cache;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.service.recipe.cutlet.ChickenCutletRecipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static avakhidov.factories.utility.MainUtility.randomInt;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMakerEhCacheTest {

    @Autowired
    ProductMakerEhCache makerEhCache;

    @Autowired
    ChickenCutletRecipe chickenCutletRecipe;

    @Test
    public void getProductListEhcache() {

        List<Product> productListEhcache = makerEhCache.getProductListEhcache(CornBun.class, 7);
        assertEquals("", 7, productListEhcache.size());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        makerEhCache.setProductList(Collections.singletonList(chickenCutletRecipe.cooked(0.0015)));

        List<Product> products = makerEhCache.getProductListEhcache();
        assertEquals("", 1, products.size());

        List<Product> productListEhcacheTwo = makerEhCache.getProductListEhcache(PorkCutlet.class, 5);
        assertEquals("", 5, productListEhcacheTwo.size());

        List<Product> productList = makerEhCache.getProductListEhcache();
        assertEquals("", 5, productList.size());

        PorkCutlet cornBun  = (PorkCutlet) productList.get(randomInt(0, productList.size() - 1));
        assertEquals("", KindMeat.PORK, cornBun.getMainIngredient().getKindMeat());


    }

}