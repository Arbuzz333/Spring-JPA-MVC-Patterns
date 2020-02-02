package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.order.OrderPancake;

public interface PancakeRecipeCreate<T extends Flour> {

    OrderPancake<T> pancakeOrderCreate(PancakeVisitor visitor);
}
