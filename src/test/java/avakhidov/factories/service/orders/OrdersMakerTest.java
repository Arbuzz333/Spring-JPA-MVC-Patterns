package avakhidov.factories.service.orders;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.enums.KindMeat;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersMakerTest {

    @Autowired
    private OrdersMaker ordersMaker;

    @Test
    public void makeCornBunListTest() throws Throwable {
        List<Product> products = ordersMaker.makeOrders(7, CornBun.class);

        assertEquals("List of CornBun size must be 7", products.size(), 7);
        ParameterPrepareDough<CornFlour> prepareDough = (ParameterPrepareDough<CornFlour>) products.get(0).getMainIngredient();
        assertEquals("Main ingredient from CornFlour", prepareDough.getKindDough(), KindDough.PUFF_PASTRY);
    }

    @Test
    public void makeChickenCutletListTest() throws Throwable {
        List<Product> products = ordersMaker.makeOrders(5, ChickenCutlet.class);

        assertEquals("List of CornBun size must be 5", products.size(), 5);
        ChickenMeat ingredient = (ChickenMeat) products.get(0).getMainIngredient();
        assertEquals("Main ingredient ChickenMeat", ingredient.getKindMeat(), KindMeat.CHICKEN);
    }

    @Test
    public void makePorkCutletListTest() throws Throwable {
        List<Product> products = ordersMaker.makeOrders(77, PorkCutlet.class);

        assertEquals("List of PorkCutlet size must be 77", products.size(), 77);
        PorkMeat ingredient = (PorkMeat) products.get(0).getMainIngredient();
        assertEquals("Main ingredient PorkMeat", ingredient.getKindMeat(), KindMeat.PORK);
    }

    @Test
    public void makeWheatBunListTest() throws Throwable {
        List<Product> products = ordersMaker.makeOrders(12, WheatBun.class);

        assertEquals("List of WheatBun size must be 12", products.size(), 12);
        ParameterPrepareDough<WheatFlour> prepareDough = (ParameterPrepareDough<WheatFlour>) products.get(0).getMainIngredient();
        assertEquals("Main ingredient from CornFlour", prepareDough.getKindDough(), KindDough.YEAST_DOUGH);
    }

}