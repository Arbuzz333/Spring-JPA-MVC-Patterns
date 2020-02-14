package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.service.orders.OrdersMaker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderMakerBunVisitor implements OrderMakerProductVisitor {

    private final OrdersMaker maker;

    public OrderMakerBunVisitor(OrdersMaker maker) {
        this.maker = maker;
    }

    public List<Product> makeOrdersProduct(OrdersMakerProduct makerBun) throws Throwable {
        List<Bun> bunList = this.maker.makeBunOrders(makerBun.getQuantity(), makerBun.getClazz());
        return new ArrayList<>(bunList);
    }
}
