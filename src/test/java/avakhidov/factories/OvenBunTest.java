package avakhidov.factories;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.serviceimpl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OvenBunTest {

    @Autowired
    private WheatBunRecipe wheatBunRecipe;

    @Autowired
    private BuckwheatBunRecipe buckwheatBunRecipe;

    @Test
    public void toBakeTestPreheated() {

        Oven<Bun> oven = new PreheatedOven<>();
        OvenWorksImpl<Bun> ovenWorksImpl = new OvenWorksImpl<>(oven,
                wheatBunRecipe.cooked(180, LocalTime.of(0, 40), 95));

        Bun bun = ovenWorksImpl.toBake();

        assertEquals(bun.getFinished().getTitle(), 1);
        assertEquals(bun.getPrepack().getFlour().getKind(), KindFlour.WHEAT);
        assertEquals(bun.getClass(), WheatBun.class);
    }

    @Test
    public void toBakeTestHold() {

        Oven<Bun> oven = new HoldOven<>();
        OvenWorksImpl<Bun> ovenWorksImpl = new OvenWorksImpl<>(oven,
                buckwheatBunRecipe.cooked(180, LocalTime.of(0, 40), 115.0));

        Bun bun = ovenWorksImpl.toBake();

        assertEquals(bun.getFinished().getTitle(), 0);
        assertEquals(bun.getPrepack().getFlour().getKind(), KindFlour.BUCKWHEAT);
        assertEquals(bun.getClass(), BuckwheatBun.class);
    }

}
