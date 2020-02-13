package avakhidov.factories.service.orders;

import avakhidov.factories.entity.bun.Bun;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersMakerBun implements AbstractOrderMaker<Bun> {

    private int quantity;
    private Class clazz;

    @Override
    public void makeOrders(int quantity, Class clazz) {
        this.quantity = quantity;
        this.clazz = clazz;
    }

    @Override
    public List<Bun> accept(OrderMakerVisitor visitor) throws Throwable {
        return visitor.makeOrdersBun(this);
    }

    Class getClazz() {
        return clazz;
    }

    int getQuantity() {
        return quantity;
    }
}
