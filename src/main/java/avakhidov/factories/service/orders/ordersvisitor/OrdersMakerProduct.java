package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersMakerProduct {

    private int quantity;
    private Class clazz;
    private OrderMakerProductVisitor visitor;

    public void initOrdersMakerProduct(int quantity, Class clazz, OrderMakerProductVisitor visitor) {
        this.quantity = quantity;
        this.clazz = clazz;
        this.visitor = visitor;
    }

    public List<Product> accept() throws Throwable {
        List<Product> products = visitor.makeOrdersProduct(this);
        return new ArrayList<>(products);
    }

    public Class getProductClazz() {
        return clazz;
    }

    public int getQuantity() {
        return quantity;
    }
}
