package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.KindFlour;
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
public class PancakeMakerCacheTest {

    @Autowired
    PancakeMakerCache pancakeMakerCache;

    @Autowired
    PancakeMakerCache pancakeMakerCacheTwo;

    @Autowired
    PancakeMakerCache pancakeMakerCacheThree;

    @Before
    public void initPancakeMaker() {
        pancakeMakerCache.initProductMaker(PancakeBuckwheat.class, 15);
        pancakeMakerCacheTwo.initProductMaker(PancakeWheat.class, 25);
        pancakeMakerCacheThree.initProductMaker(CornBun.class, 77);
    }

    @Test
    public void makePancakeList() {

        Product<? extends MainIngredient> pancakeProduct = pancakeMakerCache.makePancake(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeProduct);

        Pancake<? extends Flour> pancake = (Pancake<? extends Flour>) pancakeProduct;
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancake.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancake.getMainIngredient().getKindDough());

        pancakeMakerCache.initProductMaker(PancakeCorn.class, 10);

        Pancake<? extends Flour> pancakeAfter = (Pancake<? extends Flour>) pancakeMakerCache.makePancake(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancakeAfter.getMainIngredient().getFlour().getKind());

        Product<? extends MainIngredient> productsAfterCachePutProduct = pancakeMakerCache.makePancakeCachePut(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);

        Pancake<? extends Flour> pancakeAfterCachePut = (Pancake<? extends Flour>) productsAfterCachePutProduct;
        assertEquals("KindFlour", KindFlour.CORN, pancakeAfterCachePut.getMainIngredient().getFlour().getKind());
    }

    @Test
    public void makePancakeListCache() {

        List<Product<? extends MainIngredient>> pancakeList = pancakeMakerCacheTwo.makeProductList(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeList);
        assertEquals("products.size()",  25, pancakeList.size());

        Pancake<? extends Flour> pancake = (Pancake<? extends Flour>) pancakeList.get(MainUtility.randomInt(0, 25 - 1));
        assertEquals("KindFlour", KindFlour.WHEAT, pancake.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancake.getMainIngredient().getKindDough());

        pancakeMakerCacheTwo.initProductMaker(PancakeBuckwheat.class, 77);

        List<Product<? extends MainIngredient>> pancakeListAfter = pancakeMakerCacheTwo.makeProductList(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeListAfter);
        assertEquals("products.size()",  25, pancakeListAfter.size());

        Pancake<? extends Flour> pancakeAfter = (Pancake<? extends Flour>) pancakeListAfter.get(MainUtility.randomInt(0, 25 - 1));
        assertEquals("KindFlour", KindFlour.WHEAT, pancakeAfter.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancakeAfter.getMainIngredient().getKindDough());

        List<Product<? extends MainIngredient>> pancakeListAfterCache = pancakeMakerCacheTwo.makePancakeListCachePut(MainIngredientEnum.PREPARE_PANCAKE_DOUGH);
        assertNotNull("products.size()", pancakeListAfterCache);
        assertEquals("products.size()",  77, pancakeListAfterCache.size());

        Pancake<? extends Flour> pancakeAfterCache = (Pancake<? extends Flour>) pancakeListAfterCache.get(MainUtility.randomInt(0, 77 - 1));
        assertEquals("KindFlour", KindFlour.BUCKWHEAT, pancakeAfterCache.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PANCAKE, pancakeAfterCache.getMainIngredient().getKindDough());
    }

    @Test
    public void makeBunListCache() {

        List<Product<? extends MainIngredient>> bunList = pancakeMakerCacheThree.makeProductList(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertNotNull("products.size()", bunList);
        assertEquals("products.size()", 77, bunList.size());
        CornBun cornBun = (CornBun) bunList.get(MainUtility.randomInt(0, 77 - 1));
        assertEquals("KindFlour", KindFlour.CORN, cornBun.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PUFF_PASTRY, cornBun.getMainIngredient().getKindDough());

        pancakeMakerCacheThree.initProductMaker(WheatBun.class, 55);
        List<Product<? extends MainIngredient>> bunListAfter = pancakeMakerCacheThree.makeProductList(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertNotNull("products.size()", bunListAfter);
        assertEquals("products.size()", 77, bunListAfter.size());
        CornBun cornBunAfter = (CornBun) bunListAfter.get(MainUtility.randomInt(0, 77 - 1));
        assertEquals("KindFlour", KindFlour.CORN, cornBunAfter.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.PUFF_PASTRY, cornBunAfter.getMainIngredient().getKindDough());

        List<Product<? extends MainIngredient>> bunListAfterCache = pancakeMakerCacheThree.makeProductListEvict(MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertNotNull("products.size()", bunListAfterCache);
        assertEquals("products.size()", 55, bunListAfterCache.size());
        WheatBun wheatBunAfterCache = (WheatBun) bunListAfterCache.get(MainUtility.randomInt(0, 55 - 1));
        assertEquals("KindFlour", KindFlour.WHEAT, wheatBunAfterCache.getMainIngredient().getFlour().getKind());
        assertEquals("KindFlour", KindDough.YEAST_DOUGH, wheatBunAfterCache.getMainIngredient().getKindDough());

    }

}
