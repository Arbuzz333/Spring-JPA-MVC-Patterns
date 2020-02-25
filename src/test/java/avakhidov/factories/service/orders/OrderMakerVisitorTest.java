package avakhidov.factories.service.orders;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.MuttonCutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import avakhidov.factories.service.orders.ordersvisitor.OrdersMakerProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static avakhidov.factories.utility.MainUtility.randomInt;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMakerVisitorTest {

    @Autowired
    private OrdersMakerProduct visitor;

    @Test
    public void makeOrdersFirst() throws Throwable {
        visitor.initOrdersMakerProduct(25, CornBun.class);
        List<Product> acceptCornBun = visitor.accept();
        visitor.initOrdersMakerProduct(27, PorkCutlet.class);
        List<Product> acceptPorkCutlet = visitor.accept();
        List<Product> listProduct = new ArrayList<>(acceptCornBun);
        listProduct.addAll(acceptPorkCutlet);

        assertEquals("", listProduct.size(), 52);
        ParameterPrepareDough mainIngredientZero = (ParameterPrepareDough) listProduct.get(0).getMainIngredient();
        ParameterPrepareDough mainIngredientEleven = (ParameterPrepareDough) listProduct.get(11).getMainIngredient();
        assertEquals("", mainIngredientZero.getKindDough(), KindDough.PUFF_PASTRY);
        assertEquals("", mainIngredientEleven.getFlour().getKind(), KindFlour.CORN);

        Cutlet cutlet = (Cutlet) listProduct.get(35);
        assertEquals("", ((Meat)cutlet.getMainIngredient()).getKindMeat(), KindMeat.PORK);
        List<Product> acceptPorkCutletTwo = visitor.accept();
        List<Product> acceptCornBunTwo = visitor.accept();
    }

    @Test
    public void makeOrdersSecond() throws Throwable {
        visitor.initOrdersMakerProduct(15, WheatBun.class);
        List<Product> acceptWheatBun = visitor.accept();
        visitor.initOrdersMakerProduct(127, ChickenCutlet.class);
        List<Product> acceptChickenCutlet = visitor.accept();
        List<Product> listProduct = new ArrayList<>(acceptWheatBun);
        listProduct.addAll(acceptChickenCutlet);

        assertEquals("", listProduct.size(), 142);

        Cutlet cutlet = (Cutlet) listProduct.get(35);
        assertEquals("", ((Meat)cutlet.getMainIngredient()).getKindMeat(), KindMeat.CHICKEN);

        ParameterPrepareDough mainIngredientZero = (ParameterPrepareDough) listProduct.get(0).getMainIngredient();
        ParameterPrepareDough mainIngredientEleven = (ParameterPrepareDough) listProduct.get(11).getMainIngredient();
        assertEquals("", mainIngredientZero.getMainIngredient(), MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertEquals("", mainIngredientEleven.getFlour().getKind(), KindFlour.WHEAT);
        List<Product> acceptWheatBunTwo = visitor.accept();
        List<Product> acceptChickenCutletTwo = visitor.accept();
    }

    @Test
    public void makeOrderPancake() throws Throwable {
        visitor.initOrdersMakerProduct(77, PancakeCorn.class);
        List<Product> productList = visitor.accept();

        assertEquals("", productList.size(), 77);

        Pancake<? extends Flour> pancake = (Pancake<? extends Flour>) productList.get(randomInt(0, productList.size() - 1));
        PancakePrepareDough<? extends Flour> pancakeDough = pancake.getMainIngredient();
        assertEquals("", pancakeDough.getKindDough(), KindDough.PANCAKE);
    }

    @Test
    public void makeOrderExceptionMuttonCutlet() throws Throwable {
        visitor.initOrdersMakerProduct(25, MuttonCutlet.class);
        List<Product> accept = visitor.accept();
        assertTrue("", accept.isEmpty());
    }

    @Test
    public void makeOrderSpecify() throws Throwable {
        visitor.initOrdersMakerProduct(135, BuckwheatBun.class);
        List<Product> accept = visitor.accept();

        assertEquals("", accept.size(), 135);

        ParameterPrepareDough mainIngredientZero = (ParameterPrepareDough) accept.get(randomInt(0, accept.size() - 1)).getMainIngredient();
        ParameterPrepareDough mainIngredientEleven = (ParameterPrepareDough) accept.get(randomInt(0, accept.size() - 1)).getMainIngredient();
        assertEquals("", mainIngredientZero.getKindDough(), KindDough.CHOUX_PASTRY);
        assertEquals("", mainIngredientEleven.getFlour().getKind(), KindFlour.BUCKWHEAT);

    }
}