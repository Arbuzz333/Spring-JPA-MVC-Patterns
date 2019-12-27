package avakhidov.factories.kitchen;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.service.Recipe;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.VealCutletRecipe;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitchenFreezer implements Cook {

    @Autowired
    private Kitchen kitchen;
    @Autowired
    private CornBunRecipe cornBunRecipe;
    @Autowired
    private BuckwheatBunRecipe buckwheatBunRecipe;
    @Autowired
    private WheatBunRecipe wheatBunRecipe;

    @Autowired
    private ChickenCutletRecipe chickenCutletRecipe;
    @Autowired
    private VealCutletRecipe vealCutletRecipe;
    @Autowired
    private PorkCutletRecipe porkCutletRecipe;

    private List<Product<Cutlet>> productCutletList = new ArrayList<>();
    private List<Product<Bun>> productBunList = new ArrayList<>();

    {
        kitchen.setRecipeListBun(Arrays.asList(cornBunRecipe, buckwheatBunRecipe, wheatBunRecipe));
        kitchen.setRecipeListCutlet(Arrays.asList(chickenCutletRecipe, vealCutletRecipe, porkCutletRecipe));
        List<Product> productList = kitchen.createProductList(100);
        for (Product product : productList) {
            if (product.getMainIngredient())
        }
    }

    @Override
    public List<Product> createProductList(int count) {
        List<Product> productList = new ArrayList<>();

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
