package avakhidov.factories.service.pancake;

import avakhidov.factories.cache.ProductMakerCache;
import avakhidov.factories.cache.ProductMakerCachePrototype;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.DoughUtil;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.MainIngredient;
import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;
import avakhidov.factories.utility.MainUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMakerCacheTest {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductMakerCacheTest.class);

    @Autowired
    ProductMakerCachePrototype prototype;

    private ProductMakerCache productMakerCache;

    private ProductMakerCache productMakerCacheTwo;

    private ProductMakerCache productMakerCacheThree;

    private ProductMakerCache productMakerCacheFour;

    @Before
    public void initPancakeMaker() {
        productMakerCache = prototype.getProductMakerCache();
        productMakerCacheTwo = prototype.getProductMakerCache();
        productMakerCacheThree = prototype.getProductMakerCache();
        productMakerCacheFour = prototype.getProductMakerCache();

        productMakerCache.initProductMaker(PancakeBuckwheat.class, 15);
        productMakerCacheTwo.initProductMaker(PancakeWheat.class, 25);
        productMakerCacheThree.initProductMaker(CornBun.class, 77);
        productMakerCacheFour.initProductMakerDifferent(17, PorkCutlet.class, CornBun.class, PancakeBuckwheat.class);
    }

    @Test
    public void makePancakeList() {

        Product<? extends MainIngredient> pancakeProduct = productMakerCache.getProduct(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeProduct);

        PancakeBuckwheat pancake = (PancakeBuckwheat) pancakeProduct;
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancake.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancake.getMainIngredient().getKindDough());

        productMakerCache.initProductMaker(PancakeCorn.class, 10);

        Pancake<? extends Flour> pancakeAfter = (Pancake<? extends Flour>) productMakerCache.getProduct(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancakeAfter.getMainIngredient().getFlour().getKind());

        Product<? extends MainIngredient> productsAfterCachePutProduct = productMakerCache.getProductCachePut(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        Pancake<? extends Flour> pancakeAfterCachePut = (Pancake<? extends Flour>) productsAfterCachePutProduct;
        assertEquals("KindFlour", KindFlour.CORN, pancakeAfterCachePut.getMainIngredient().getFlour().getKind());

        productMakerCache.initProductMaker(PancakeWheat.class, 7);
        productMakerCache.getProduct().forEach(p -> p.setFinished(Finished.FINISHED));

        Product<? extends MainIngredient> productWheat = productMakerCache.getProductEvict(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        PancakeWheat pancakeWheat = (PancakeWheat) productWheat;
        assertEquals("KindFlour", KindFlour.WHEAT, pancakeWheat.getMainIngredient().getFlour().getKind());

        Product<? extends MainIngredient> product = productMakerCache.getProduct(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        PancakeWheat pancakeCorn = (PancakeWheat) product;
        assertEquals("KindFlour", KindFlour.WHEAT, pancakeCorn.getMainIngredient().getFlour().getKind());

        productMakerCache.initProductMaker(PancakeBuckwheat.class, 10);

        Product<? extends MainIngredient> productBuckwheat = productMakerCache.getProduct(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        PancakeBuckwheat pancakeFinished = (PancakeBuckwheat) productBuckwheat;
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancakeFinished.getMainIngredient().getFlour().getKind());
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

        productMakerCacheTwo.initProductMaker(ChickenCutlet.class, 7);
        List<Product<? extends MainIngredient>> pancakeListSeven = productMakerCacheTwo.getProductListCachePut(MainIngredientEnum.MEAT);

        List<Product<? extends MainIngredient>> listCondition = productMakerCacheTwo.getProductListFromCache(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertEquals("products.size()",  77, listCondition.size());

        Pancake<? extends Flour> pancakeCondition = (Pancake<? extends Flour>) listCondition.get(MainUtility.randomInt(0, 77 - 1));
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancakeCondition.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancakeCondition.getMainIngredient().getKindDough());

        List<Product<? extends MainIngredient>> listMeat = productMakerCacheTwo.getProductListFromCache(MainIngredientEnum.MEAT);
        assertNull("listMeat.size()", listMeat);
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
        List<Product<? extends MainIngredient>> bunListAfter = productMakerCacheThree.getProductListFromCache(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
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
        assertEquals("KindFlour", DoughUtil.setParameterKindDoughFromAMPM(LocalTime.now()), wheatBunAfterCache.getMainIngredient().getKindDough());

        List<Product<? extends MainIngredient>> bunListAfterEvict = productMakerCacheThree.getProductListFromCache(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertNull("products.size()", bunListAfterEvict);

        OrdersMakerProduct visitor = productMakerCache.getVisitor();
        OrdersMakerProduct visitorTwo = productMakerCacheTwo.getVisitor();
        OrdersMakerProduct visitorThree = productMakerCacheThree.getVisitor();
        OrdersMakerProduct visitorFour = productMakerCacheFour.getVisitor();
        logger.info(visitor.getClass().getName());
        logger.info(visitorTwo.getClass().getName());
        logger.info(visitorThree.getClass().getName());
        logger.info(visitorFour.getClass().getName());
    }

}
