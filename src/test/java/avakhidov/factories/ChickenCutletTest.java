package avakhidov.factories;


import avakhidov.factories.entity.cutlet.ChickenCutlet;
import avakhidov.factories.entity.cutlet.Cutlet;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.ChickenMeat;
import avakhidov.factories.service.serviceimpl.cutlet.ChickenCutletRecipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChickenCutletTest {

    @Autowired
    ChickenCutletRecipe chickenCutletRecipe;

    @Test
    public void chickenCloneTest() {

        ChickenCutlet chickenMeatCutlet =
                (ChickenCutlet) chickenCutletRecipe.cooked(0.135);

        Cutlet<ChickenMeat> clone = chickenMeatCutlet.cloneChickenCutlet(chickenMeatCutlet);
        Sesame sesameClone = clone.getSesame();
        Sesame sesame = chickenMeatCutlet.getSesame();

        assertSame(sesameClone, sesame);
        assertNotSame(chickenMeatCutlet, clone);
        assertNotSame(chickenMeatCutlet.getParameterPrepareDoughBun(), clone.getParameterPrepareDoughBun());
        assertNotSame(chickenMeatCutlet.getSesameBun(), clone.getSesameBun());

        assertSame(chickenMeatCutlet.getParameterPrepareDoughBun().getTime(), clone.getParameterPrepareDoughBun().getTime());
        assertEquals(chickenMeatCutlet.getSesameBun().getWeight(), clone.getSesameBun().getWeight(), 0.0001);
        assertEquals(chickenMeatCutlet.getWeight(), clone.getWeight(), 0.00001);
    }

}
