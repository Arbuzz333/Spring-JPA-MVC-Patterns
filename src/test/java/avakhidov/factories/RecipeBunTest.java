package avakhidov.factories;

import avakhidov.factories.entity.Bun;
import avakhidov.factories.service.RecipeBun;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeBunTest {

    @Autowired
    private RecipeBun recipeBun;

    @Test
    public void makingRollTest() {

        List<Bun> buns = Arrays.asList(recipeBun.makingBun(new WheatBunRecipe()),
                recipeBun.makingBun(new CornBunRecipe()),
                recipeBun.makingBun(new BuckwheatBunRecipe()));

        buns.forEach(r -> {System.out.print(r.getFlour().getKind());
        System.out.print(" ");
        System.out.println(r.getFlour().getGrinding());
        });
        assertEquals(buns.size(), 3);

    }
}
