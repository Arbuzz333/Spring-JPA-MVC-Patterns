package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.exception.ClassArgumentIllegalException;
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

    public List<Product> makeOrdersProduct(OrdersMakerProduct makerCutlet) throws Throwable {

        if (makerCutlet.getProductClazz().getSuperclass().equals(Cutlet.class)) {
            List<Cutlet> bunList = this.maker.makeCutletOrders(makerCutlet.getQuantity(), makerCutlet.getProductClazz());
            return new ArrayList<>(bunList);
        } else {
            throw new ClassArgumentIllegalException(makerCutlet.getProductClazz().toString(), "Method: makeOrdersProduct(OrdersMakerProduct maker)", this.getClass().toString());
        }
    }
}
