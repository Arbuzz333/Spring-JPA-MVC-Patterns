package avakhidov.factories;

import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.service.RecipeBun;
import avakhidov.factories.service.serviceimpl.BuckwheatBunRecipe;
import avakhidov.factories.service.serviceimpl.CornBunRecipe;
import avakhidov.factories.service.serviceimpl.WheatBunRecipe;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(RecipeBunTest.class);


    @Autowired
    private RecipeBun recipeBun;

    @Test
    public void makingRollTest() {

        List<Bun> buns = Arrays.asList(recipeBun.makingBun(new WheatBunRecipe()),
                recipeBun.makingBun(new CornBunRecipe()),
                recipeBun.makingBun(new BuckwheatBunRecipe()));

        buns.forEach(r -> {logger.info(r.getPrepareDough().getKindDough());
        logger.info(r.getPrepareDough().getFlour().getGrinding());
        });
        assertEquals(buns.size(), 3);

    }
}
