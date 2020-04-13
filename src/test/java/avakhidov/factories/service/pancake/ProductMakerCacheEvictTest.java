package avakhidov.factories.service.pancake;

import avakhidov.factories.cache.ProductDouble;
import avakhidov.factories.cache.ProductMakerCache;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.MainIngredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMakerCacheEvictTest {

    @Autowired
    ProductMakerCache productMakerCacheFist;

    @Autowired
    ProductMakerCache productMakerCacheSecond;

    @Before
    public void initPancakeMaker() {
        productMakerCacheFist.initProductMakerDifferent(15, WheatBun.class, ChickenCutlet.class, PancakeWheat.class);

        productMakerCacheSecond.initProductMakerDifferent(7, BuckwheatBun.class, PorkCutlet.class, PancakeCorn.class);
    }

    @Test
    public void getDoubleProductEvict() {

        List<Product> product = productMakerCacheFist.getProduct();
        assertEquals("products double.size()", 45, product.size());

        ProductDouble doubleProduct = productMakerCacheFist.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        ChickenMeat chickenMeat = ((ChickenMeat) doubleProduct.getProductOne().getMainIngredient());
        assertEquals("KindMeat.CHICKEN", KindMeat.CHICKEN, chickenMeat.getKindMeat());

        PancakePrepareDough<WheatFlour> dough = ((PancakePrepareDough<WheatFlour>) doubleProduct.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.WHEAT, dough.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.PANCAKE, dough.getKindDough());

        productMakerCacheFist.productDoubleEvict(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        List<Product> productEvictFirst = productMakerCacheFist.getProduct();
        assertEquals("products double.size()", 15, productEvictFirst.size());

        ChickenCutlet chickenCutlet = (ChickenCutlet) productEvictFirst.get(0);
        assertEquals("KindMeat.CHICKEN", KindMeat.CHICKEN, chickenCutlet.getMainIngredient().getKindMeat());

        ProductDouble doubleEvictFirst = productMakerCacheFist.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        ChickenMeat chickenMeatEvictFirst = ((ChickenMeat) doubleEvictFirst.getProductOne().getMainIngredient());
        assertEquals("KindMeat.CHICKEN", KindMeat.CHICKEN, chickenMeatEvictFirst.getKindMeat());

        PancakePrepareDough<WheatFlour> doughEvictFirst = ((PancakePrepareDough<WheatFlour>) doubleProduct.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.WHEAT, doughEvictFirst.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.PANCAKE, doughEvictFirst.getKindDough());

        productMakerCacheFist.productDoubleEvict(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        List<Product> productEvict = productMakerCacheFist.getProduct();
        assertEquals("products double.size()", 0, productEvict.size());

        ProductDouble doubleProductEvict = productMakerCacheFist.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        Product<? extends MainIngredient> productOne = doubleProductEvict.getProductOne();
        assertEquals("productOne == null", null, productOne);

        Product<? extends MainIngredient> productTwo = doubleProductEvict.getProductTwo();
        assertEquals("productTwo == null", null, productTwo);

    }

    @Test
    public void getDoubleProductEvictExpire() {

        List<Product> product = productMakerCacheSecond.getProduct();
        assertEquals("products double.size()", 21, product.size());

        ProductDouble doubleProduct = productMakerCacheSecond.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        PorkMeat chickenMeat = ((PorkMeat) doubleProduct.getProductOne().getMainIngredient());
        assertEquals("KindMeat.CHICKEN", KindMeat.PORK, chickenMeat.getKindMeat());

        ParameterPrepareDough<BuckwheatFlour> dough = ((ParameterPrepareDough<BuckwheatFlour>) doubleProduct.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.BUCKWHEAT, dough.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.CHOUX_PASTRY, dough.getKindDough());

        productMakerCacheSecond.getProduct().clear();
        List<Product> productClear = productMakerCacheSecond.getProduct();
        assertEquals("products double.size()", 0, productClear.size());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ProductDouble doubleProductClear = productMakerCacheSecond.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        PorkMeat chickenMeatClear = ((PorkMeat) doubleProductClear.getProductOne().getMainIngredient());
        assertEquals("KindMeat.CHICKEN", KindMeat.PORK, chickenMeatClear.getKindMeat());

        ParameterPrepareDough<BuckwheatFlour> doughClear = ((ParameterPrepareDough<BuckwheatFlour>) doubleProductClear.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.BUCKWHEAT, doughClear.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.CHOUX_PASTRY, doughClear.getKindDough());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ProductDouble doubleProductClearTwo = productMakerCacheSecond.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        PorkMeat chickenMeatClearTwo = ((PorkMeat) doubleProductClearTwo.getProductOne().getMainIngredient());
        assertEquals("KindMeat.CHICKEN", KindMeat.PORK, chickenMeatClearTwo.getKindMeat());

        ParameterPrepareDough<BuckwheatFlour> doughClearTwo = ((ParameterPrepareDough<BuckwheatFlour>) doubleProductClearTwo.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.BUCKWHEAT, doughClearTwo.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.CHOUX_PASTRY, doughClearTwo.getKindDough());
    }

}
