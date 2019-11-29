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

import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChickenCutletTest {

    @Autowired
    ChickenCutletRecipe chickenCutletRecipe;

    @Test
    public void chickenCloneTest() {

        ChickenCutlet chickenMeatCutlet =
                (ChickenCutlet) chickenCutletRecipe.cooked(37, LocalTime.of(1, 10), 0.135);

        Cutlet<ChickenMeat> clone = chickenMeatCutlet.cloneChickenCutlet(chickenMeatCutlet);
        Sesame sesame = clone.getSesame();
    }

}
