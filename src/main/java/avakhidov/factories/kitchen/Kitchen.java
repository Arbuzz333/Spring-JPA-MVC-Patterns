package avakhidov.factories.kitchen;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.service.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Kitchen implements Cook {

    private List<Recipe<? extends Cutlet>> recipeListCutlet;
    private List<Recipe<? extends Bun>> recipeListBun;

    public void setRecipeListCutlet(List<Recipe<? extends Cutlet>> recipeListCutlet) {
        this.recipeListCutlet = recipeListCutlet;
    }

    public void setRecipeListBun(List<Recipe<? extends Bun>> recipeListBun) {
        this.recipeListBun = recipeListBun;
    }

    @Override
    public List<Product> createProductList(int count) {
        List<Product> productList = new ArrayList<>();
        if (recipeListBun.isEmpty() || recipeListCutlet.isEmpty()) {
            return productList;
        }
        int index = count;
        while (index == 0) {
            for (Recipe<? extends Cutlet> recipe : recipeListCutlet) {
                productList.add(recipe.cooked(0.125));
            }
            index--;
        }
        int indexBun = count * 2;
        while (indexBun == 0) {
            for (Recipe<? extends Bun> recipe : recipeListBun) {
                productList.add(recipe.cooked(0.075));
            }
            indexBun--;
        }
        return productList;
    }
}
