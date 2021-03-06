package avakhidov.factories;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.BunLite;
import avakhidov.factories.entity.bun.BunLiteFactory;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.entity.flour.CornFlour;
import avakhidov.factories.entity.flour.FlourCounterServiceAspect;
import avakhidov.factories.entity.flour.WheatFlour;
import avakhidov.factories.entity.ingredient.Ingredient;
import avakhidov.factories.entity.ingredient.Peanut;
import avakhidov.factories.entity.ingredient.Raisins;
import avakhidov.factories.entity.ingredient.Walnut;
import avakhidov.factories.enums.Finished;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BunLiteFactoryTest {

    @Autowired
    private BunLiteFactory factory;

    @Autowired
    private BunLiteFactory factorySecond;

    @Autowired
    private FlourCounterServiceAspect flourCounterServiceAspect;

    List<Ingredient> ingredientListFirst;
    List<Ingredient> ingredientListFirstRevers;
    List<Ingredient> ingredientListNut;
    List<Ingredient> ingredientListNutRevers;

    Product product;
    Product productClone;
    Product productCorn;
    Product productWheat;

    @Value("#{new Double('${weight_bun}')}")
    double weightOne;
    @Value("#{new Double('${coefficient_buckwheat}')}")
    double coefficient_buckwheat;

    @Value("#{new Double('${coefficient_wheat}')}")
    double coefficient_wheat;

    @Before
    public void fillingListIngredient() {
        ingredientListFirst = Arrays.asList(new Raisins("Red"), new Walnut("Dessert"));
        ingredientListFirstRevers = Arrays.asList(new Walnut("Dessert"), new Raisins("Red"));
        ingredientListNut = Arrays.asList(new Walnut("Dessert"), new Peanut("Egyptian giant"));
        ingredientListNutRevers = Arrays.asList(new Peanut("Egyptian giant"), new Walnut("Dessert"));

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
        ParameterPrepareDough<WheatFlour> wheatParameter = new ParameterPrepareDough<>(
                new WheatFlour(GrindingFlour.MEDIUM)
                , KindDough.YEAST_DOUGH
                , 42
                , LocalTime.of(0, 25)
                , 15.7
        );
        product = new Product<>()
                .outerBuilder()
                .withFinished(Finished.RAW)
                .withWeight(weightOne)
                .withMainIngredient(buckwheatParameter)
                .build();
        productClone = new Product<>()
                .outerBuilder()
                .withFinished(Finished.RAW)
                .withWeight(weightOne)
                .withMainIngredient(buckwheatParameterClone)
                .build();
        productCorn = new Product<>()
                .outerBuilder()
                .withFinished(Finished.RAW)
                .withWeight(weightOne)
                .withMainIngredient(cornParameterClone)
                .build();
        productWheat = new Product<>()
                .outerBuilder()
                .withFinished(Finished.RAW)
                .withWeight(weightOne)
                .withMainIngredient(wheatParameter)
                .build();

    }

    @Test()
    public void factoryTest() {

        BunLite bunLite1 = factory.getBunLite(new Date(), ingredientListFirst, product);
        BunLite bunLiteSecond = factorySecond.getBunLite(new Date(), ingredientListFirst, product);
        BunLite bunLite2 = factory.getBunLite(new Date(), ingredientListFirstRevers, productClone);
        BunLite bunLite3 = factory.getBunLite(new Date(), ingredientListFirst, productCorn);
        BunLite bunLite4 = factory.getBunLite(new Date(), ingredientListNut, product);
        BunLite bunLite5 = factory.getBunLite(new Date(), ingredientListNutRevers, productWheat);
        BunLite bunLite6 = factory.getBunLite(new Date(), ingredientListNut, productWheat);
        assertEquals(factory.getCountIngredientList(), 2);
        assertEquals(factory.getCountProductList(), 3);

        assertEquals(bunLite1.getProduct(), bunLite2.getProduct());
        assertEquals(bunLite1.getProduct(), bunLiteSecond.getProduct());
        assertEquals(bunLite1.getProduct(), bunLite4.getProduct());
        assertEquals(bunLite1.getAdditionalIngredient(), bunLite3.getAdditionalIngredient());
        assertEquals(bunLite4.getAdditionalIngredient(), bunLite5.getAdditionalIngredient());
        assertEquals(bunLite5.getProduct(), bunLite6.getProduct());

        flourCounterServiceAspect.getFlourDoubleMap().clear();
    }

    @Test
    public void flourDoubleMapTest() {

        factory.getBunLite(new Date(), ingredientListFirst, product);
        factory.getBunLite(new Date(), ingredientListFirstRevers, productClone);
        factory.getBunLite(new Date(), ingredientListFirst, productCorn);
        factory.getBunLite(new Date(), ingredientListNut, product);
        factory.getBunLite(new Date(), ingredientListNutRevers, productWheat);
        factory.getBunLite(new Date(), ingredientListNut, productWheat);

        Map<KindFlour, Double> flourDoubleMap = flourCounterServiceAspect.getFlourDoubleMap();
        Set<KindFlour> flours = flourDoubleMap.keySet();

        assertEquals(factory.getCountIngredientList(), 2);
        assertEquals(factory.getCountProductList(), 3);

        assertEquals(flours.size(), 2);
        Double buckwheatWeight = flourDoubleMap.get(KindFlour.BUCKWHEAT);
        Double wheatWeight = flourDoubleMap.get(KindFlour.WHEAT);
        assertEquals(buckwheatWeight, coefficient_buckwheat * weightOne * 3);
        assertEquals(wheatWeight, coefficient_wheat * weightOne * 2);

    }
}
