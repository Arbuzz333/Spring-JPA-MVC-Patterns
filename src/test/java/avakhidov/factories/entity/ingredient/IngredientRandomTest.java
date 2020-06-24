package avakhidov.factories.entity.ingredient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientRandomTest implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(IngredientRandomTest.class);

    private SupplementIngredient ingredientFirst;
    private SupplementIngredient ingredientSecond;
    private SupplementIngredient ingredientThird;

    private SupplementIngredient ingredientFourth;
    private SupplementIngredient ingredientFifth;
    private SupplementIngredient ingredientSixth;


    @Test
    public void getIngredient() {
        assertEquals("For ingredientFirst", ingredientSecond.addIngredient().getClass(), ingredientFirst.addIngredient().getClass());
        assertEquals("For ingredientSecond", ingredientThird.addIngredient().getClass(), ingredientSecond.addIngredient().getClass());
        assertEquals("For ingredientThird", ingredientFirst.addIngredient().getClass(), ingredientThird.addIngredient().getClass());

        assertNotEquals("For ingredientFourth", ingredientFirst.addIngredient().getClass(), ingredientFourth.addIngredient().getClass());
        assertEquals("For ingredientFifth", ingredientFourth.addIngredient().getClass(), ingredientFifth.addIngredient().getClass());
        assertEquals("For ingredientSixth", ingredientFifth.addIngredient().getClass(), ingredientSixth.addIngredient().getClass());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        IngredientRandom beanFirst = applicationContext.getBean(IngredientRandom.class);
        ingredientFirst = beanFirst.getIngredient();

        IngredientRandom beanSecond = applicationContext.getBean(IngredientRandom.class);
        ingredientSecond = beanSecond.getIngredient();

        IngredientRandom beanThird = applicationContext.getBean(IngredientRandom.class);
        ingredientThird = beanThird.getIngredient();

        refreshIngredient(applicationContext);

        while (ingredientFirst.addIngredient().getClass().equals(ingredientFourth.addIngredient().getClass())) {
            refreshIngredient(applicationContext);
            logger.info("Ingredient is refresh");
        }
    }

    private void refreshIngredient(ApplicationContext applicationContext) {
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IngredientRandom beanFourth = applicationContext.getBean(IngredientRandom.class);
        ingredientFourth = beanFourth.getIngredient();

        IngredientRandom beanFifth = applicationContext.getBean(IngredientRandom.class);
        ingredientFifth = beanFifth.getIngredient();

        IngredientRandom beanSixth = applicationContext.getBean(IngredientRandom.class);
        ingredientSixth = beanSixth.getIngredient();
    }

}