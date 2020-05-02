package avakhidov.factories;

import avakhidov.factories.entity.bun.BuckwheatBun;
import avakhidov.factories.entity.bun.Bun;
import avakhidov.factories.entity.bun.WheatBun;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.event.EventListenerBun;
import avakhidov.factories.service.Oven;
import avakhidov.factories.service.recipe.bun.BuckwheatBunRecipe;
import avakhidov.factories.service.recipe.bun.CornBunRecipe;
import avakhidov.factories.service.recipe.bun.WheatBunRecipe;
import avakhidov.factories.service.serviceimpl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OvenBunTest {

    @Autowired
    private WheatBunRecipe wheatBunRecipe;

    @Autowired
    private BuckwheatBunRecipe buckwheatBunRecipe;

    @Autowired
    private CornBunRecipe cornBunRecipe;

    @Autowired
    private EventListenerBun listenerBun;

    @Test
    public void toBakeTestPreheated() {

        Oven<Bun> oven = new PreheatedOven<>();
        OvenWorksImpl<Bun> ovenWorksImpl = new OvenWorksImpl<>(oven,
                wheatBunRecipe.cooked(0.95));

        Bun bun = ovenWorksImpl.toBake();

        assertEquals(bun.getFinished().getTitle(), 1);
        assertEquals(bun.getMainIngredient().getFlour().getKind(), KindFlour.WHEAT);
        assertEquals(bun.getClass(), WheatBun.class);
    }

    @Test
    public void toBakeTestHold() {

        Oven<Bun> oven = HoldOvenBunSingleton.getInstance();
        OvenWorksImpl<Bun> ovenWorksImpl = new OvenWorksImpl<>(oven,
                buckwheatBunRecipe.cooked(0.115));

        Bun bun = ovenWorksImpl.toBake();

        assertEquals(bun.getFinished().getTitle(), 0);
        assertEquals(bun.getMainIngredient().getFlour().getKind(), KindFlour.BUCKWHEAT);
        assertEquals(bun.getClass(), BuckwheatBun.class);
    }

    @Test
    public void listenerTest() {
        List<Bun> buns = Arrays.asList(cornBunRecipe.cooked(0.75), wheatBunRecipe.cooked(0.120),
                buckwheatBunRecipe.cooked(0.95));
        Oven<Bun> oven = new PreheatedOven<>();
        buns.forEach(b -> b.setListenerSave(listenerBun));
        buns.forEach(oven::toBake);

        assertTrue(listenerBun.getUuidsFINISHED().contains(buns.get(0).getUuid()));
        assertTrue(listenerBun.getUuidsFINISHED().contains(buns.get(1).getUuid()));
        assertTrue(listenerBun.getUuidsFINISHED().contains(buns.get(2).getUuid()));
        assertEquals(listenerBun.getUuidsFINISHED().size(), 3);

    }

}
