package avakhidov.factories.kitchen;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.MainIngredientEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class KitchenFreezer implements Cook {

    private final Logger logger = LoggerFactory.getLogger(KitchenFreezer.class);

    private static final int FREEZER_CAPACITY = 100;

    @Autowired
    private Kitchen kitchen;

    private Map<MainIngredientEnum, Deque<Product>> enumProductMapFreezer = new TreeMap<>();

    private void fillTheFreeze(MainIngredientEnum ingredientEnum) {
        List<Product> productList = kitchen.createProductList(Map.of(ingredientEnum, FREEZER_CAPACITY));
        for (Product product : productList) {
            enumProductMapFreezer.merge(product.getMainIngredient().getMainIngredient(), new ArrayDeque<>(Collections.singleton(product)), (v1, v2) -> {
                v1.addLast(v2.remove());
                return v1;
            });
        }
        if (enumProductMapFreezer.containsKey(ingredientEnum)) {
            logger.info("The Freezer is filled with {} in quantity {}", ingredientEnum, enumProductMapFreezer.get(ingredientEnum).size());
        }
    }

    @Override
    public List<Product> createProductList(Map<MainIngredientEnum, Integer> enumIntegerMap) {
        fillTheFreezeIsEmpty();
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<MainIngredientEnum, Integer> ingredientEnumEntry : enumIntegerMap.entrySet()) {
            if (enumProductMapFreezer.containsKey(ingredientEnumEntry.getKey())) {
                int kitchenCount = enumProductMapFreezer.get(ingredientEnumEntry.getKey()).size() - ingredientEnumEntry.getValue();
                if (kitchenCount > 0) {
                    for (int i = 0; i < ingredientEnumEntry.getValue(); i++) {
                        productList.add(enumProductMapFreezer.get(ingredientEnumEntry.getKey()).poll());
                    }
                } else {
                    productList.addAll(enumProductMapFreezer.get(ingredientEnumEntry.getKey()));
                    enumProductMapFreezer.get(ingredientEnumEntry.getKey()).clear();
                    fillTheFreeze(ingredientEnumEntry.getKey());

                    int countCutletAdditive = Math.abs(kitchenCount);
                    for (int i = 0; i < countCutletAdditive; i++) {
                        productList.add(enumProductMapFreezer.get(ingredientEnumEntry.getKey()).poll());
                    }
                }
            }
        }
        enumProductMapFreezer.keySet().forEach(f -> logger.info("In the Freezer left {} in quantity {}", f, enumProductMapFreezer.get(f).size()));
        return productList;
    }

    @Override
    public List<Product> createProductList(int count) {
        Map<MainIngredientEnum, Integer> mainIngredientEnumIntegerMap = new TreeMap<>();
        Arrays.stream(MainIngredientEnum.values()).forEach(v -> mainIngredientEnumIntegerMap.put(v, count));
        return createProductList(mainIngredientEnumIntegerMap);
    }

    public void fillTheFreezeIsEmpty() {
        MainIngredientEnum[] values = MainIngredientEnum.values();
        Arrays.stream(values).forEach(v -> {
            if (enumProductMapFreezer.get(v) == null || enumProductMapFreezer.get(v).isEmpty()) {
                fillTheFreeze(v);
            }
        });
    }
}
