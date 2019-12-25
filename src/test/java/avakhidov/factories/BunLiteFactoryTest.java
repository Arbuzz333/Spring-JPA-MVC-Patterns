package avakhidov.factories;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.BunLite;
import avakhidov.factories.entity.bun.BunLiteFactory;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.entity.ingredient.Peanut;
import avakhidov.factories.entity.ingredient.Raisins;
import avakhidov.factories.entity.ingredient.Walnut;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BunLiteFactoryTest {

    @Autowired
    private BunLiteFactory factory;

    @Test
    public void factoryTest() {
        List<Ingredient> ingredientListFirst = Arrays.asList(new Raisins("Red"), new Walnut("Dessert"));
        List<Ingredient> ingredientListFirstRevers = Arrays.asList(new Walnut("Dessert"), new Raisins("Red"));
        List<Ingredient> ingredientListNut = Arrays.asList(new Walnut("Dessert"), new Peanut("Egyptian giant"));

        ParameterPrepareDough<BuckwheatFlour> buckwheatParameter = new ParameterPrepareDough<>(
                new BuckwheatFlour(GrindingFlour.COARSE)
                , KindDough.PUFF_PASTRY
                , 38
                , LocalTime.of(1, 5)
                , 7.5
        );
        ParameterPrepareDough<BuckwheatFlour> buckwheatParameterClone = new ParameterPrepareDough<>(
                new BuckwheatFlour(GrindingFlour.COARSE)
                , KindDough.PUFF_PASTRY
                , 38
                , LocalTime.of(1, 5)
                , 7.5
        );
        ParameterPrepareDough<CornFlour> cornParameterClone = new ParameterPrepareDough<>(
                new CornFlour(GrindingFlour.FINE)
                , KindDough.SHORTCRUST_PASTRY
                , 37
                , LocalTime.of(0, 55)
                , 4.3
        );
        Product product = new Product<>()
                .outerBuilder()
                .withFinished(Finished.RAW)
                .withWeight(0.125)
                .withMainIngredient(buckwheatParameter)
                .build();
        Product productClone = new Product<>()
                .outerBuilder()
                .withFinished(Finished.RAW)
                .withWeight(0.125)
                .withMainIngredient(buckwheatParameterClone)
                .build();
        Product productCorn = new Product<>()
                .outerBuilder()
                .withFinished(Finished.RAW)
                .withWeight(0.215)
                .withMainIngredient(cornParameterClone)
                .build();

        BunLite bunLite1 = factory.getBunLite(new Date(), ingredientListFirst, product);
        BunLite bunLite2 = factory.getBunLite(new Date(), ingredientListFirstRevers, productClone);
        BunLite bunLite3 = factory.getBunLite(new Date(), ingredientListFirst, productCorn);
        BunLite bunLite4 = factory.getBunLite(new Date(), ingredientListNut, product);
        assertEquals(factory.getCountIngredientList(), 2);
        assertEquals(factory.getCountProductList(), 2);
     }
}
