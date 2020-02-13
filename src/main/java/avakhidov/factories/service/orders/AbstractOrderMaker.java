package avakhidov.factories.service.orders;

import avakhidov.factories.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbstractOrderMaker<S extends Product> {

    void makeOrders(int quantity, Class clazz);

    List<S> accept(OrderMakerVisitor visitor) throws Throwable;

}
