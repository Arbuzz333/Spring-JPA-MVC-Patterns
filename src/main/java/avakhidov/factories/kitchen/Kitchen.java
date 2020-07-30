package avakhidov.factories.kitchen;

import avakhidov.factories.entity.Product;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.service.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Kitchen implements Cook {

    private final Map<MainIngredientEnum, List<Recipe>> enumRecipeMap;
    private final Map<MainIngredientEnum, Double> mainIngredientWeight;

    public Kitchen(Map<MainIngredientEnum, List<Recipe>> enumRecipeMap, Map<MainIngredientEnum, Double> mainIngredientWeight) {
        this.enumRecipeMap = enumRecipeMap;
        this.mainIngredientWeight = mainIngredientWeight;
    }

    @Override
    public List<Product> createProductList(Map<MainIngredientEnum, Integer> enumIntegerMap) {
        List<Product> productList = new ArrayList<>();
        if (enumIntegerMap.isEmpty()) {
            return productList;
        }
        for (Map.Entry<MainIngredientEnum, Integer> enumIntegerEntry : enumIntegerMap.entrySet()) {
            if (enumRecipeMap.containsKey(enumIntegerEntry.getKey())) {
                int recipeIndex = 0;
                int count = enumIntegerEntry.getValue();
                while (count != 0) {
                    productList.add((Product) enumRecipeMap.get(enumIntegerEntry.getKey()).get(recipeIndex)
                            .cooked(mainIngredientWeight.get(enumIntegerEntry.getKey())));
                    count--;
                    recipeIndex++;
                    if (recipeIndex == enumRecipeMap.get(enumIntegerEntry.getKey()).size()) {
                        recipeIndex = 0;
                    }
                }
            }
        }
        return productList;
    }

    @Override
    public List<Product> createProductList(int count) {
        Map<MainIngredientEnum, Integer> enumIntegerMap = new TreeMap<>();
        MainIngredientEnum[] values = MainIngredientEnum.values();
        for (MainIngredientEnum value : values) {
            enumIntegerMap.put(value, count);
        }
        return createProductList(enumIntegerMap);
    }
}
