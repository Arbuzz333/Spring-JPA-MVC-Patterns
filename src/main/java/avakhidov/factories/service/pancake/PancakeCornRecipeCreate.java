package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.ingredient.Walnut;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.order.OrderPancake;
import org.springframework.stereotype.Service;

@Service
public class PancakeCornRecipeCreate implements PancakeRecipeCreate<CornFlour> {

    @Override
    public OrderPancake<CornFlour> pancakeOrderCreate(PancakeVisitor visitor) {
        Pancake<CornFlour> flourPancake = visitor.pancakeCornVisit(this);
        return new OrderPancake<>((PancakeCorn) flourPancake, new Walnut("Mediterranean"));
    }
}
