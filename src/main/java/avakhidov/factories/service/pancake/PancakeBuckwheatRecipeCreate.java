package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.ingredient.SourCream;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.order.OrderPancake;
import org.springframework.stereotype.Service;

@Service
public class PancakeBuckwheatRecipeCreate implements PancakeRecipeCreate<BuckwheatFlour> {

    @Override
    public OrderPancake<BuckwheatFlour> pancakeOrderCreate(PancakeVisitor visitor) {
        Pancake<BuckwheatFlour> flourPancake = visitor.pancakeBuckwheatVisit(this);
        return new OrderPancake<>((PancakeBuckwheat) flourPancake, new SourCream("15% fat"));
    }
}
