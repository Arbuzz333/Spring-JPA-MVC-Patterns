package avakhidov.factories.utility;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.MainIngredientEnum;

import java.util.List;
import java.util.Random;

public enum  MainUtility {

    ;

    public static <T extends Product> List<T> getList(List<Product> products, MainIngredientEnum mainIngredientEnum, List<T> result) {
        products.forEach(v -> {
            if (v.getMainIngredient().getMainIngredient().equals(mainIngredientEnum)) {
                result.add((T) v);
            }
        });
        return result;
    }

    public static void repeat(int n, Runnable r) {
        for (int i = 0; i < n; i++)
            r.run();
    }

    public static int randomInt(int begin, int end) {
        Random random = new Random(System.currentTimeMillis());
        return begin + random.nextInt(end - begin + 1);
    }
}
