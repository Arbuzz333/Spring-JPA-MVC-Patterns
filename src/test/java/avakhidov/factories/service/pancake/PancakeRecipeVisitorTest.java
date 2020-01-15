package avakhidov.factories.service.pancake;

import avakhidov.factories.entity.dough.pancakedough.PancakePrepareDough;
import avakhidov.factories.entity.ingredient.Ketchup;
import avakhidov.factories.entity.ingredient.SourCream;
import avakhidov.factories.entity.ingredient.Walnut;
import avakhidov.factories.entity.pancake.PancakeBuckwheat;
import avakhidov.factories.entity.pancake.PancakeCorn;
import avakhidov.factories.entity.pancake.PancakeWheat;
import avakhidov.factories.enums.MainIngredientEnum;
import avakhidov.factories.order.OrderPancake;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PancakeRecipeVisitorTest {

    @Autowired
    private PancakeRecipe pancakeRecipe;

    @Autowired
    private PancakeBuckwheatRecipeCreate pancakeBuckwheatRecipe;

    @Autowired
    private PancakeCornRecipeCreate pancakeCornRecipe;

    @Autowired
    private PancakeWheatRecipeCreate pancakeWheatRecipe;

    private Map<Class, OrderPancake> classOrderPancakeMap = new HashMap<>();
    private List<OrderPancake> listPancakeRecipe = new ArrayList<>();

    @Before
    public void createListClassOrder() {
        listPancakeRecipe = pancakeRecipe.createListPancakeRecipe(pancakeBuckwheatRecipe, pancakeCornRecipe, pancakeWheatRecipe);
        classOrderPancakeMap = listPancakeRecipe
                .stream()
                .collect(Collectors.toMap(o -> o.getPancake().getClass(), o -> o));
    }

    @Test
    public void createListPancakeRecipe() {
        assertEquals("listPancakeRecipe size", listPancakeRecipe.size(), 3);

        assertEquals("Map<Class, OrderPancake> classOrderPancakeMap size", classOrderPancakeMap.size(), 3);

        OrderPancake orderPancakeCorn = classOrderPancakeMap.get(PancakeCorn.class);
        OrderPancake orderPancakeBuckwheat = classOrderPancakeMap.get(PancakeBuckwheat.class);
        OrderPancake orderPancakeWheat = classOrderPancakeMap.get(PancakeWheat.class);

        assertEquals(".getEgg().getQuantity()",
                ((PancakePrepareDough) orderPancakeBuckwheat.getPancake().getMainIngredient()).getEgg().getQuantity(),
                2);
        assertEquals("SourCream.getClass()", orderPancakeBuckwheat.getIngredient().getClass(), SourCream.class);

        assertEquals("getIngredient().getMainIngredient()",
                orderPancakeCorn.getPancake().getIngredient().getMainIngredient(),
                MainIngredientEnum.COTTAGE_CHEESE);
        assertEquals("Walnut.getClass()", orderPancakeCorn.getIngredient().getClass(), Walnut.class);

        assertEquals(".getEgg().getQuantity()",
                orderPancakeWheat.getPancake().getIngredient().getMainIngredient(),
                MainIngredientEnum.MEAT);
        assertEquals("Ketchup.getClass()", orderPancakeWheat.getIngredient().getClass(), Ketchup.class);

    }
}
