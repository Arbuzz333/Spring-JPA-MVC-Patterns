package avakhidov.factories.service.pancake;

import avakhidov.factories.cache.ProductDouble;
import avakhidov.factories.cache.ProductMakerCache;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.MainIngredient;
import avakhidov.factories.utility.MainUtility;
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
public class ProductMakerCacheTest {

    @Autowired
    ProductMakerCache productMakerCache;

    @Autowired
    ProductMakerCache productMakerCacheTwo;

    @Autowired
    ProductMakerCache productMakerCacheThree;

    @Autowired
    ProductMakerCache productMakerCacheFour;

    @Before
    public void initPancakeMaker() {
        productMakerCache.initProductMaker(PancakeBuckwheat.class, 15);
        productMakerCacheTwo.initProductMaker(PancakeWheat.class, 25);
        productMakerCacheThree.initProductMaker(CornBun.class, 77);
        productMakerCacheFour.initProductMakerDifferent(17, PorkCutlet.class, CornBun.class, PancakeBuckwheat.class);
    }

    @Test
    public void makePancakeList() {

        Product<? extends MainIngredient> pancakeProduct = productMakerCache.getProduct(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeProduct);

        Pancake<? extends Flour> pancake = (Pancake<? extends Flour>) pancakeProduct;
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancake.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancake.getMainIngredient().getKindDough());

        productMakerCache.initProductMaker(PancakeCorn.class, 10);

        Pancake<? extends Flour> pancakeAfter = (Pancake<? extends Flour>) productMakerCache.getProduct(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancakeAfter.getMainIngredient().getFlour().getKind());

        Product<? extends MainIngredient> productsAfterCachePutProduct = productMakerCache.getProductCachePut(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        Pancake<? extends Flour> pancakeAfterCachePut = (Pancake<? extends Flour>) productsAfterCachePutProduct;
        assertEquals("KindFlour", KindFlour.CORN, pancakeAfterCachePut.getMainIngredient().getFlour().getKind());
    }

    @Test
    public void makePancakeListCache() {

        List<Product<? extends MainIngredient>> pancakeList = productMakerCacheTwo.getProductList(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeList);
        assertEquals("products.size()",  25, pancakeList.size());

        Pancake<? extends Flour> pancake = (Pancake<? extends Flour>) pancakeList.get(MainUtility.randomInt(0, 25 - 1));
        assertEquals("KindFlour", KindFlour.WHEAT, pancake.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancake.getMainIngredient().getKindDough());

        productMakerCacheTwo.initProductMaker(PancakeBuckwheat.class, 77);

        List<Product<? extends MainIngredient>> pancakeListAfter = productMakerCacheTwo.getProductList(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeListAfter);
        assertEquals("products.size()",  25, pancakeListAfter.size());

        Pancake<? extends Flour> pancakeAfter = (Pancake<? extends Flour>) pancakeListAfter.get(MainUtility.randomInt(0, 25 - 1));
        assertEquals("KindFlour", KindFlour.WHEAT, pancakeAfter.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancakeAfter.getMainIngredient().getKindDough());

        List<Product<? extends MainIngredient>> pancakeListAfterCache = productMakerCacheTwo.getProductListCachePut(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeListAfterCache);
        assertEquals("products.size()",  77, pancakeListAfterCache.size());

        Pancake<? extends Flour> pancakeAfterCache = (Pancake<? extends Flour>) pancakeListAfterCache.get(MainUtility.randomInt(0, 77 - 1));
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancakeAfterCache.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancakeAfterCache.getMainIngredient().getKindDough());
    }

    @Test
    public void makeBunListCache() {

        List<Product<? extends MainIngredient>> bunList = productMakerCacheThree.getProductList(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertNotNull("products.size()", bunList);
        assertEquals("products.size()", 77, bunList.size());
        CornBun cornBun = (CornBun) bunList.get(MainUtility.randomInt(0, 77 - 1));
        assertEquals("KindFlour", KindFlour.CORN, cornBun.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PUFF_PASTRY, cornBun.getMainIngredient().getKindDough());

        productMakerCacheThree.initProductMaker(WheatBun.class, 55);
        List<Product<? extends MainIngredient>> bunListAfter = productMakerCacheThree.getProductList(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertNotNull("products.size()", bunListAfter);
        assertEquals("products.size()", 77, bunListAfter.size());
        CornBun cornBunAfter = (CornBun) bunListAfter.get(MainUtility.randomInt(0, 77 - 1));
        assertEquals("KindFlour", KindFlour.CORN, cornBunAfter.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PUFF_PASTRY, cornBunAfter.getMainIngredient().getKindDough());

        List<Product<? extends MainIngredient>> bunListAfterCache = productMakerCacheThree.makeProductListEvict(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertNotNull("products.size()", bunListAfterCache);
        assertEquals("products.size()", 55, bunListAfterCache.size());
        WheatBun wheatBunAfterCache = (WheatBun) bunListAfterCache.get(MainUtility.randomInt(0, 55 - 1));
        assertEquals("KindFlour", KindFlour.WHEAT, wheatBunAfterCache.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PUFF_PASTRY, wheatBunAfterCache.getMainIngredient().getKindDough());

    }

    @Test
    public void getDoubleProductTest() {
        ProductDouble doubleProduct = productMakerCacheFour.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        List<Product> product = productMakerCacheFour.getProduct();
        assertEquals("products double.size()", 51, product.size());

        PorkMeat porkMeat = ((PorkMeat) doubleProduct.getProductOne().getMainIngredient());
        assertEquals("KindMeat.PORK", KindMeat.PORK, porkMeat.getKindMeat());

        PancakePrepareDough<BuckwheatFlour> dough = ((PancakePrepareDough<BuckwheatFlour>) doubleProduct.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.BUCKWHEAT, dough.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.PANCAKE, dough.getKindDough());

        ProductDouble doubleProductDough = productMakerCacheFour.getDoubleProduct(MainIngredientEnum.MEAT, MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        PorkMeat porkMeatDough = ((PorkMeat) doubleProductDough.getProductOne().getMainIngredient());
        assertEquals("KindMeat.PORK", KindMeat.PORK, porkMeatDough.getKindMeat());

        PancakePrepareDough<BuckwheatFlour> doughDough = ((PancakePrepareDough<BuckwheatFlour>) doubleProductDough.getProductTwo().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.BUCKWHEAT, doughDough.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.PANCAKE, doughDough.getKindDough());

        ProductDouble doubleProductReplace = productMakerCacheFour.getDoubleProduct(MainIngredientEnum.PARAMETER_PREPARE_DOUGH, MainIngredientEnum.MEAT);
        PorkMeat porkMeatReplace = ((PorkMeat) doubleProductReplace.getProductTwo().getMainIngredient());
        assertEquals("KindMeat.PORK", KindMeat.PORK, porkMeatReplace.getKindMeat());

        ParameterPrepareDough<CornFlour> doughReplace = ((ParameterPrepareDough<CornFlour>) doubleProductReplace.getProductOne().getMainIngredient());
        assertEquals("KindFlour.BUCKWHEAT", KindFlour.CORN, doughReplace.getFlour().getKind());
        assertEquals("KindDough.PANCAKE", KindDough.PUFF_PASTRY, doughReplace.getKindDough());

    }

}
