package avakhidov.factories.service.orders;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.cutlet.Cutlet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderMakerVisitor {

    private final OrdersMaker maker;

    public OrderMakerVisitor(OrdersMaker maker) {
        this.maker = maker;
    }

    public List<Product> createListProductVisitor(AbstractOrderMaker...creates) throws Throwable {
        List<Product> result = new ArrayList<>();
        for (AbstractOrderMaker create : creates) {
            result.addAll(create.accept(this));
        }
        return result;
    }

    List<Bun> makeOrdersBun(OrdersMakerBun makerBun) throws Throwable {
        return this.maker.makeBunOrders(makerBun.getQuantity(), makerBun.getClazz());
    }

    List<Cutlet> makeOrdersCutlet(OrdersMakerCutlet makerCutlet) throws Throwable {
        return this.maker.makeCutletOrders(makerCutlet.getQuantity(), makerCutlet.getClazz());
    }


}
