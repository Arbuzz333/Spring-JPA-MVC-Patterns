package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.exception.ClassArgumentIllegalException;
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
        if (makerBun.getProductClazz().getSuperclass().equals(Bun.class)) {
            List<Bun> bunList = this.maker.makeBunOrders(makerBun.getQuantity(), makerBun.getProductClazz());
            return new ArrayList<>(bunList);
        } else {
            throw new ClassArgumentIllegalException(makerBun.getProductClazz().toString(), "Method: makeOrdersProduct(OrdersMakerProduct maker)", this.getClass().toString());
        }
    }
}
