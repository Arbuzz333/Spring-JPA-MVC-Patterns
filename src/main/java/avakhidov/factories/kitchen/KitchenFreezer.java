package avakhidov.factories.kitchen;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.meat.Meat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.PorkCutletRecipe;
import avakhidov.factories.service.serviceimpl.cutlet.VealCutletRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

@Service
public class KitchenFreezer implements Cook {

    private static final int FREEZER_CAPACITY = 100;

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

    private Deque<Product<Meat>> productCutletList = new ArrayDeque<>();
    private Deque<Product<ParameterPrepareDough>> productBunList = new ArrayDeque<>();

    private void fillTheFreeze(int kitchenCount) {
        List<Product> productList = kitchen.createCutletWithBunList(kitchenCount);
        for (Product product : productList) {
            if (product.getMainIngredient().getMainIngredient().equals(MainIngredientEnum.PARAMETER_PREPARE_DOUGH)) {
                productBunList.addLast(product);
            } else {
                productCutletList.addLast(product);
            }
        }
    }

    @Override
    public List<Product> createCutletWithBunList(int count) {
        List<Product> productList = new ArrayList<>();
        int kitchenCount = productCutletList.size() - count;
        if (kitchenCount > 0) {
            for (int i = 0; i < count; i++) {
                productList.add(productCutletList.poll());
                productList.add(productBunList.poll());
                productList.add(productBunList.poll());
            }
            fillTheFreeze(count);
        } else {
            productList.addAll(productCutletList);
            productList.addAll(productBunList);
            List<Product> cutletWithBunList = kitchen.createCutletWithBunList(Math.abs(kitchenCount));
            productList.addAll(cutletWithBunList);
            fillTheFreeze(FREEZER_CAPACITY);
        }
        return productList;
    }

    private void setup() {
        kitchen.setRecipeListBun(Arrays.asList(cornBunRecipe, buckwheatBunRecipe, wheatBunRecipe));
        kitchen.setRecipeListCutlet(Arrays.asList(chickenCutletRecipe, vealCutletRecipe, porkCutletRecipe));
        fillTheFreeze(FREEZER_CAPACITY);
    }

    private void cleanup() {
        productCutletList.clear();
        productBunList.clear();
    }
}
