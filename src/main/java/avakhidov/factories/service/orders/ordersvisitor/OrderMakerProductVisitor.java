package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderMakerProductVisitor {

    List<Product> makeOrdersProduct(OrdersMakerProduct maker) throws Throwable;
}
