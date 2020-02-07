package avakhidov.factories.service.orders;

import avakhidov.factories.annotations.KitchenFreezerAspect;
import avakhidov.factories.comand.CommandOrders;
import avakhidov.factories.entity.bun.CornBun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.PorkCutlet;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static avakhidov.factories.utility.MainUtility.repeat;

@Component
public class OrdersSplitter {

    @KitchenFreezerAspect(productClass = CornBun.class, kindDough = KindDough.PUFF_PASTRY, kindFlour = KindFlour.CORN)
    public List<CornBun> getOrdersCornBun(int count, CommandOrders commandOrders) {
        List<CornBun> result = new ArrayList<>();

        repeat(count, () -> result.add((CornBun) commandOrders.getOrders().remove(commandOrders.createOrderCornBun())));

        return result;
    }

    @KitchenFreezerAspect(productClass = ChickenCutlet.class)
    public List<ChickenCutlet> getOrdersChickenCutlet(int count, CommandOrders commandOrders) {
        List<ChickenCutlet> result = new ArrayList<>();

        repeat(count, () -> result.add((ChickenCutlet) commandOrders.getOrders().remove(commandOrders.createOderChickenCutlet())));

        return result;
    }

    @KitchenFreezerAspect(productClass = PorkCutlet.class)
    public List<PorkCutlet> getOrdersPorkCutlet(int count, CommandOrders commandOrders) {
        List<PorkCutlet> result = new ArrayList<>();

        repeat(count, () -> result.add((PorkCutlet) commandOrders.getOrders().remove(commandOrders.createOrderPorkCutlet())));

        return result;
    }

    @KitchenFreezerAspect(productClass = WheatBun.class)
    public List<WheatBun> getOrdersWheatBun(int count, CommandOrders commandOrders) {
        List<WheatBun> result = new ArrayList<>();

        repeat(count, () -> result.add((WheatBun) commandOrders.getOrders().remove(commandOrders.createOrderWheatBun())));

        return result;
    }

}
