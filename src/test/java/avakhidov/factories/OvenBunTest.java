package avakhidov.factories;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.RecipeBun;
import avakhidov.factories.service.serviceimpl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OvenBunTest {

    @Autowired
    private OvenWorks ovenWorks;

    @Autowired
    private RecipeBun recipeBun;

    @Test
    public void toBakeTestPreheated() {

        Oven<Bun> oven = new PreheatedOven<>();
        ovenWorks.setOven(oven);

        Product<Bun> product = ovenWorks.toBake(recipeBun.makingBun(new WheatBunRecipe()));
        Bun bun = product.getPrepack();

        assertEquals(product.getFinished().getTitle(), 1);
        assertEquals(bun.getPrepareDough().getFlour().getKind(), KindFlour.WHEAT);
        assertEquals(bun.getClass(), WheatBun.class);
    }

    @Test
    public void toBakeTestHold() {

        Oven<Bun> oven = new HoldOven<>();
        ovenWorks.setOven(oven);

        Product<Bun> product = ovenWorks.toBake(recipeBun.makingBun(new BuckwheatBunRecipe()));
        Bun bun = product.getPrepack();

        assertEquals(product.getFinished().getTitle(), 0);
        assertEquals(bun.getPrepareDough().getFlour().getKind(), KindFlour.BUCKWHEAT);
        assertEquals(bun.getClass(), BuckwheatBun.class);
    }


}
