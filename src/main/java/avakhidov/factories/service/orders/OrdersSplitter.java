package avakhidov.factories.service.orders;

import avakhidov.factories.annotations.KitchenFreezerAspect;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.pancake.Pancake;
import avakhidov.factories.enums.MainIngredientEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersSplitter {

    @KitchenFreezerAspect()
    public List<Cutlet> getProductListCutlet(List<Product> products) {
        List<Cutlet> result = new ArrayList<>();

        return getList(products, MainIngredientEnum.MEAT, result);
    }

    @KitchenFreezerAspect(mainIngredientEnum = "PARAMETER_PREPARE_DOUGH")
    public List<Bun> getProductListBun(List<Product> products) {
        List<Bun> result = new ArrayList<>();

        return getList(products, MainIngredientEnum.PARAMETER_PREPARE_DOUGH, result);
    }

    @KitchenFreezerAspect(mainIngredientEnum = "PREPARE_PANCAKE_DOUGH")
    public List<Pancake> getProductListPancake(List<Product> products) {
        List<Pancake> result = new ArrayList<>();

        return getList(products, MainIngredientEnum.PREPARE_PANCAKE_DOUGH, result);
    }

    private <T extends Product> List<T> getList(List<Product> products, MainIngredientEnum mainIngredientEnum, List<T> result) {
        products.forEach(v -> {
            if (v.getMainIngredient().getMainIngredient().equals(mainIngredientEnum)) {
                result.add((T) v);
            }
        });
        return result;
    }
}
