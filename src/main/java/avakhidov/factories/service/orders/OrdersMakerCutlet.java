package avakhidov.factories.service.orders;

import avakhidov.factories.entity.cutlet.Cutlet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersMakerCutlet implements AbstractOrderMaker<Cutlet> {

    private int quantity;
    private Class clazz;

    @Override
    public void makeOrders(int quantity, Class clazz) {
        this.quantity = quantity;
        this.clazz = clazz;
    }

    @Override
    public List<Cutlet> accept(OrderMakerVisitor visitor) throws Throwable {
        return visitor.makeOrdersCutlet(this);
    }

    int getQuantity() {
        return quantity;
    }

    Class getClazz() {
        return clazz;
    }
}
