package avakhidov.factories.service.pancake;

import avakhidov.factories.cache.ProductDouble;
import avakhidov.factories.cache.ProductMakerCache;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DoubleProductMakerCacheTest {

    @Autowired
    ProductMakerCache productMakerCacheFour;

    @Before
    public void initPancakeMaker() {
        productMakerCacheFour.initProductMakerDifferent(17, PorkCutlet.class, CornBun.class, PancakeBuckwheat.class);
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
