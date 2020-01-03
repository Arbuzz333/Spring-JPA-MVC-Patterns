package avakhidov.factories;


import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.FatMeat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.kitchen.KitchenFreezer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenFreezerTest {

    @Autowired
    private KitchenFreezer kitchenFreezer;

    @Test
    public void createCutletWithBunListTest() {
        List<Product> cutletWithBunList = kitchenFreezer.createProductList(125);
        assertEquals(cutletWithBunList.size(), 250);

        List<Product> productList = kitchenFreezer.createProductList(Map.of(MainIngredientEnum.PARAMETER_MEAT_DUMPLINGS, 75));
        assertTrue(productList.isEmpty());

        List<Product> productListBun = kitchenFreezer.createProductList(Map.of(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, 70));
        assertEquals(productListBun.size(), 70);
        KindDough kindDough = ((ParameterPrepareDough) productListBun.stream()
                .filter(product -> ((ParameterPrepareDough) product.getMainIngredient()).getFlour().getKind().equals(KindFlour.CORN)).findFirst().get()
                .getMainIngredient()).getKindDough();
        assertEquals(kindDough, KindDough.PUFF_PASTRY);
    }

    @Test
    public void createProductListMeat() {
        List<Product> productListCutlet = kitchenFreezer.createProductList(Map.of(MainIngredientEnum.MEAT, 74));
        assertEquals(productListCutlet.size(), 74);
        FatMeat fatMeat = ((Meat) productListCutlet.stream()
                .filter(product -> ((Meat) product.getMainIngredient()).getKindMeat().equals(KindMeat.CHICKEN)).findFirst().get()
                .getMainIngredient()).getFatMeat();
        assertEquals(fatMeat, FatMeat.DIETARY);
    }
}
