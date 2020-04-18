package avakhidov.factories.service.pancake;

import avakhidov.factories.cache.ProductDouble;
import avakhidov.factories.cache.ProductMakerCache;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.MainIngredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMakerCacheEvictTest {

    @Autowired
    ProductMakerCache productMakerCacheEvict;

    @Test
    public void getDoubleProductEvict() {
        productMakerCacheEvict.initProductMakerDifferent(15, WheatBun.class, ChickenCutlet.class, PancakeWheat.class);
        productMakerCacheEvict.getDoubleProductPut(MainIngredientEnum.MEAT, ChickenCutlet.class, PancakeWheat.class);

        List<Product> product = productMakerCacheEvict.getProduct();
        assertEquals("products double.size()", 45, product.size());

        ProductDouble doubleProduct = productMakerCacheEvict.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        ChickenMeat chickenMeat = ((ChickenMeat) doubleProduct.getProductOne().getMainIngredient());
        assertEquals("KindMeat.CHICKEN", KindMeat.CHICKEN, chickenMeat.getKindMeat());

        PancakePrepareDough<WheatFlour> dough = ((PancakePrepareDough<WheatFlour>) doubleProduct.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.WHEAT, dough.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.PANCAKE, dough.getKindDough());

        productMakerCacheEvict.productDoubleEvict(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        List<Product> productEvictFirst = productMakerCacheEvict.getProduct();
        assertEquals("products double.size()", 15, productEvictFirst.size());

        ChickenCutlet chickenCutlet = (ChickenCutlet) productEvictFirst.get(0);
        assertEquals("KindMeat.CHICKEN", KindMeat.CHICKEN, chickenCutlet.getMainIngredient().getKindMeat());

        ProductDouble doubleEvictFirst = productMakerCacheEvict.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        ChickenMeat chickenMeatEvictFirst = ((ChickenMeat) doubleEvictFirst.getProductOne().getMainIngredient());
        assertEquals("KindMeat.CHICKEN", KindMeat.CHICKEN, chickenMeatEvictFirst.getKindMeat());

        PancakePrepareDough<WheatFlour> doughEvictFirst = ((PancakePrepareDough<WheatFlour>) doubleProduct.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.WHEAT, doughEvictFirst.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.PANCAKE, doughEvictFirst.getKindDough());

        productMakerCacheEvict.productDoubleEvict(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        List<Product> productEvict = productMakerCacheEvict.getProduct();
        assertEquals("products double.size()", 0, productEvict.size());

        ProductDouble doubleProductEvict = productMakerCacheEvict.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        Product<? extends MainIngredient> productOne = doubleProductEvict.getProductOne();
        assertEquals("productOne == null", null, productOne);

        Product<? extends MainIngredient> productTwo = doubleProductEvict.getProductTwo();
        assertEquals("productTwo == null", null, productTwo);

    }

}
