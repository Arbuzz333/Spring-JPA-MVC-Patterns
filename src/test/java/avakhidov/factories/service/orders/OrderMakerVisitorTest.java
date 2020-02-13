package avakhidov.factories.service.orders;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMakerVisitorTest {

    @Autowired
    private OrderMakerVisitor visitor;
    @Qualifier("ordersMakerBun")
    @Autowired
    private AbstractOrderMaker makerBun;
    @Qualifier("ordersMakerCutlet")
    @Autowired
    private AbstractOrderMaker makerCutlet;

    @Test
    public void makeOrdersFirst() throws Throwable {
        makerBun.makeOrders(25, CornBun.class);
        makerCutlet.makeOrders(27, PorkCutlet.class);
        List<Product> listProduct = visitor.createListProductVisitor(makerBun, makerCutlet);

        assertEquals(listProduct.size(), 52);
        ParameterPrepareDough mainIngredientZero = (ParameterPrepareDough) listProduct.get(0).getMainIngredient();
        ParameterPrepareDough mainIngredientEleven = (ParameterPrepareDough) listProduct.get(11).getMainIngredient();
        assertEquals(mainIngredientZero.getKindDough(), KindDough.PUFF_PASTRY);
        assertEquals(mainIngredientEleven.getFlour().getKind(), KindFlour.CORN);

        Cutlet cutlet = (Cutlet) listProduct.get(35);
        assertEquals(((Meat)cutlet.getMainIngredient()).getKindMeat(), KindMeat.PORK);
    }

    @Test
    public void makeOrdersSecond() throws Throwable {
        makerBun.makeOrders(15, WheatBun.class);
        makerCutlet.makeOrders(127, ChickenCutlet.class);
        List<Product> listProduct = visitor.createListProductVisitor(makerBun, makerCutlet);

        assertEquals(listProduct.size(), 142);

        Cutlet cutlet = (Cutlet) listProduct.get(35);
        assertEquals(((Meat)cutlet.getMainIngredient()).getKindMeat(), KindMeat.CHICKEN);

        ParameterPrepareDough mainIngredientZero = (ParameterPrepareDough) listProduct.get(0).getMainIngredient();
        ParameterPrepareDough mainIngredientEleven = (ParameterPrepareDough) listProduct.get(11).getMainIngredient();
        assertEquals(mainIngredientZero.getMainIngredient(), MainIngredientEnum.PARAMETER_PREPARE_DOUGH);
        assertEquals(mainIngredientEleven.getFlour().getKind(), KindFlour.WHEAT);
    }
}