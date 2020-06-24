package avakhidov.factories.entity.ingredient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientDequeCircleTest {


    @Autowired
    ScopeSupplementIngredient scopeSupplementIngredient;

    @Autowired
    ScopeSupplementIngredient scopeSupplementIngredientSecond;

    @Test
    public void getIngredient() {
        SupplementIngredient ingredientFirst = scopeSupplementIngredient.getScopeIngredient();
        SupplementIngredient ingredientSecond = scopeSupplementIngredient.getScopeIngredient();
        SupplementIngredient ingredientThird = scopeSupplementIngredient.getScopeIngredient();

        SupplementIngredient ingredientFourth = scopeSupplementIngredient.getScopeIngredient();
        SupplementIngredient ingredientFifth = scopeSupplementIngredient.getScopeIngredient();
        SupplementIngredient ingredientSixth = scopeSupplementIngredient.getScopeIngredient();
        SupplementIngredient ingredientSeventh = scopeSupplementIngredient.getScopeIngredient();

        SupplementIngredient ingredient2First = scopeSupplementIngredientSecond.getScopeIngredient();

        assertEquals("For ingredientFirst", Walnut.class, ingredientFirst.addIngredient().getClass());
        assertEquals("For ingredientSecond", Raisins.class, ingredientSecond.addIngredient().getClass());
        assertEquals("For ingredientThird", Peanut.class, ingredientThird.addIngredient().getClass());

        assertEquals("For ingredientFourth", Walnut.class, ingredientFourth.addIngredient().getClass());
        assertEquals("For ingredientFifth", Raisins.class, ingredientFifth.addIngredient().getClass());
        assertEquals("For ingredientSixth", Peanut.class, ingredientSixth.addIngredient().getClass());
        assertEquals("For ingredientSeventh", Walnut.class, ingredientSeventh.addIngredient().getClass());

        assertEquals("For ingredient2First", Walnut.class, ingredient2First.addIngredient().getClass());
    }
}