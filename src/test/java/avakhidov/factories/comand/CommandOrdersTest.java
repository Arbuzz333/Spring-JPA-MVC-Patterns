package avakhidov.factories.comand;

import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.ingredient.Peanut;
import avakhidov.factories.entity.meat.PorkMeat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandOrdersTest {
    @Autowired
    private CommandOrders commandOrders;

    @Test
    public void createOderChickenCutlet() {
        assertTrue(commandOrders.getOrders().isEmpty());
        List<UUID> uuids = new LinkedList<>();

        uuids.add(commandOrders.createOderChickenCutlet());
        assertEquals(commandOrders.getOrders().size(), 1);

        uuids.add(commandOrders.createOrderCornBun());
        assertEquals(commandOrders.getOrders().size(), 2);

        uuids.add(commandOrders.createOrderPorkCutlet());
        assertEquals(commandOrders.getOrders().size(), 3);

        uuids.add(commandOrders.createOrderWheatBun());
        assertEquals(commandOrders.getOrders().size(), 4);

        assertEquals(commandOrders.getOrders().get(uuids.get(2)).getMainIngredient().getClass(), PorkMeat.class);
        assertEquals(((WheatBun)commandOrders.getOrders().get(uuids.get(3))).getAdditionalIngredient().get(0).getClass(), Peanut.class);

        assertTrue(commandOrders.getOrders().containsKey(uuids.get(1)));
        commandOrders.undoOrder(uuids.get(1));
        assertFalse(commandOrders.getOrders().containsKey(uuids.get(1)));

        assertEquals(((WheatBun)commandOrders.getOrders().get(uuids.get(3))).getWeight(), 0.075, 0.001);
        assertEquals(((ChickenCutlet)commandOrders.getOrders().get(uuids.get(0))).getWeight(), 0.125, 0.001);
    }

}
