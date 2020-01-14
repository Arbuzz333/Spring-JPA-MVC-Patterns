package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.ingredient.Ketchup;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.order.OrderPancake;
import org.springframework.stereotype.Service;

@Service
public class PancakeWheatRecipeCreate implements PancakeRecipeCreate<WheatFlour> {

    @Override
    public OrderPancake<WheatFlour> pancakeOrderCreate(PancakeVisitor visitor) {
        Pancake<WheatFlour> flourPancake = visitor.pancakeWheatVisit(this);
        return new OrderPancake<>((PancakeWheat) flourPancake, new Ketchup("Chili"));
    }
}
