package avakhidov.factories;

import avakhidov.factories.entity.dough.BuckwheatDough;
import avakhidov.factories.entity.flour.BuckwheatFlour;
import avakhidov.factories.enums.GrindingFlour;
import avakhidov.factories.enums.KindFlour;
import avakhidov.factories.enums.dough.KindDough;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BuilderBuckwheatDoughTest {

    @Test
    public void builderBuckwheatDough() {
        BuckwheatDough dough = BuckwheatDough.buildBuckwheatDough(
                new BuckwheatFlour(GrindingFlour.COARSE),
                BuckwheatDough.Manufacturer.HANDMADE,
                KindDough.PUFF_PASTRY,
                25.4);

        assertEquals(dough.getFlour().getGrinding(), GrindingFlour.COARSE);
        assertEquals(dough.getFlour().getKind(), KindFlour.BUCKWHEAT);
        assertEquals(dough.getKindDough(), KindDough.PUFF_PASTRY);
        assertNotSame(dough.getManufacturer(), BuckwheatDough.Manufacturer.PREFABRICATED);
        assertEquals(dough.getFat(), 25.4);
    }
}
