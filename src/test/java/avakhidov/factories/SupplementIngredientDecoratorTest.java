package avakhidov.factories;

import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.entity.ingredient.SupplementIngredientDecorator;
import avakhidov.factories.entity.ingredient.SupplementPeanutDecorator;
import avakhidov.factories.entity.ingredient.SupplementRaisinsDecorator;
import avakhidov.factories.entity.ingredient.SupplementWalnutDecorator;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplementIngredientDecoratorTest {

    @Autowired
    private WheatBunRecipe wheatBunRecipe;

    @Autowired
    SupplementIngredientDecorator decorator;

    @Test
    public void additionalIngredient() {

        WheatBun wheatBun = (WheatBun) wheatBunRecipe.cooked(0.125);

        decorator.setIngredients(new SupplementPeanutDecorator(), new SupplementRaisinsDecorator(), new SupplementWalnutDecorator());
        wheatBun.supplement(decorator);
        assertEquals(wheatBun.getAdditionalIngredient().size(), 3);
    }
}
