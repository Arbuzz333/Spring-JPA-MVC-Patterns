package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.service.orders.OrdersMaker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderMakerCutletVisitor implements OrderMakerProductVisitor {

    private final OrdersMaker maker;

    public OrderMakerCutletVisitor(OrdersMaker maker) {
        this.maker = maker;
    }

    public List<Product> makeOrdersProduct(OrdersMakerProduct makerBun) throws Throwable {
        List<Cutlet> bunList = this.maker.makeCutletOrders(makerBun.getQuantity(), makerBun.getProductClazz());
        return new ArrayList<>(bunList);
    }
}
