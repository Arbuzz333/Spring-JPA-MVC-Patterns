package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.annotations.SeeingSpecifyProductClass;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.pancake.Pancake;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrdersMakerProduct {

    private int quantity;
    private Class clazz;

    private final Map<Class, OrderMakerProductVisitor> productVisitorMap;

    public OrdersMakerProduct(
            @Qualifier("orderMakerBunVisitor") OrderMakerProductVisitor makerBun,
            @Qualifier("orderMakerCutletVisitor") OrderMakerProductVisitor makerCutlet,
            @Qualifier("orderMakerPancakeVisitor") OrderMakerProductVisitor makerPancake) {
        productVisitorMap = Map.of(
                Bun.class, makerBun,
                Pancake.class, makerPancake,
                Cutlet.class, makerCutlet);
    }

    public void initOrdersMakerProduct(int quantity, Class clazz) {
        this.quantity = quantity;
        this.clazz = clazz;
    }

    @SeeingSpecifyProductClass
    public List<Product> accept() throws Throwable {
        OrderMakerProductVisitor orderMakerProductVisitor = productVisitorMap.get(clazz.getSuperclass());
        List<Product> products = orderMakerProductVisitor.makeOrdersProduct(this);
        return new ArrayList<>(products);
    }

    public Class getProductClazz() {
        return clazz;
    }

    public int getQuantity() {
        return quantity;
    }
}
