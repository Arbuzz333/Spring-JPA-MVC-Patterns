package avakhidov.factories.service.orders.ordersvisitor;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.exception.ClassArgumentIllegalException;
import avakhidov.factories.order.OrderPancake;
import avakhidov.factories.service.pancake.PancakeBuckwheatRecipeCreate;
import avakhidov.factories.service.pancake.PancakeCornRecipeCreate;
import avakhidov.factories.service.pancake.PancakeRecipe;
import avakhidov.factories.service.pancake.PancakeRecipeCreate;
import avakhidov.factories.service.pancake.PancakeWheatRecipeCreate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static avakhidov.factories.utility.MainUtility.repeat;

@Service
public class OrderMakerPancakeVisitor implements OrderMakerProductVisitor {

    private final PancakeRecipe pancakeRecipe;

    private Map<Class<? extends Pancake<? extends Flour>>, PancakeRecipeCreate<? extends Flour>> classOrderPancakeMap;

    public OrderMakerPancakeVisitor(
            PancakeRecipe pancakeRecipe,
            PancakeBuckwheatRecipeCreate pancakeBuckwheatRecipe,
            PancakeCornRecipeCreate pancakeCornRecipe,
            PancakeWheatRecipeCreate pancakeWheatRecipe) {
        this.pancakeRecipe = pancakeRecipe;
        classOrderPancakeMap = Map.of(
                PancakeBuckwheat.class, pancakeBuckwheatRecipe,
                PancakeCorn.class, pancakeCornRecipe,
                PancakeWheat.class, pancakeWheatRecipe);
    }

    @Override
    public List<Product> makeOrdersProduct(OrdersMakerProduct maker){

        List<Product> products = new ArrayList<>();
        if (maker.getProductClazz().getSuperclass().equals(Pancake.class)) {

            repeat(maker.getQuantity(), () -> products.addAll(pancakeRecipe.createListPancakeRecipe(classOrderPancakeMap.get(maker.getProductClazz()))
                    .stream()
                    .map((Function<OrderPancake, Pancake>) OrderPancake::getPancake)
                    .collect(Collectors.toList())));
            return products;
        } else {
            throw new ClassArgumentIllegalException(maker.getProductClazz().toString(), "Method: makeOrdersProduct(OrdersMakerProduct maker)", this.getClass().toString());
        }
    }
}
