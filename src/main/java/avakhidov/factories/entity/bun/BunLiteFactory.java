package avakhidov.factories.entity.bun;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.Flour;
import avakhidov.factories.entity.ingredient.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
public class BunLiteFactory {

    private List<List<Ingredient>> listListIngredient = new ArrayList<>();
    private List<Product<ParameterPrepareDough<? extends Flour>>> productList = new ArrayList<>();

    public BunLite getBunLite(Date date, List<Ingredient> ingredientList, Product<ParameterPrepareDough<? extends Flour>> product) {

        List<Ingredient> findIngredient = null;
        if (listListIngredient.isEmpty()) {
            listListIngredient.add(ingredientList);
            findIngredient = ingredientList;
        } else {
            ingredientList.sort(Comparator.comparing(Ingredient::getName));
            ListIterator<List<Ingredient>> listIterator = listListIngredient.listIterator();
            while (listIterator.hasNext()) {
                List<Ingredient> ingredients = listIterator.next();
                ingredients.sort((t2, t1) -> t2.getName().compareTo(t1.getName()));
                if (ingredients.equals(ingredientList)) {
                    findIngredient = ingredients;
                    break;
                } else {
                    findIngredient = ingredientList;
                    listIterator.add(ingredientList);
                }
            }
        }
        Product<ParameterPrepareDough<? extends Flour>> findProduct;
        if (productList.isEmpty()) {
            productList.add(product);
            findProduct = product;
        } else {
            findProduct = productList
                    .stream()
                    .filter(p -> p.equals(product))
                    .findAny()
                    .orElseGet(() -> {
                        productList.add(product);
                        return product;
                    });
        }
        return new BunLite(date, findIngredient, findProduct);
    }

    public int getCountIngredientList() {
        return listListIngredient.size();
    }

    public int getCountProductList() {
        return productList.size();
    }
}
