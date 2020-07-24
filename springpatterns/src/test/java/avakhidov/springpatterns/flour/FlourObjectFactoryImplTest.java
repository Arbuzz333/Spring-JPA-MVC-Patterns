package avakhidov.springpatterns.flour;


import avakhidov.springpatterns.flour.entyties.BuckwheatFlour;
import avakhidov.springpatterns.flour.entyties.CornFlour;
import avakhidov.springpatterns.flour.enums.FlourCode;
import avakhidov.springpatterns.flour.enums.GrindingFlour;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@SpringBootTest
public class FlourObjectFactoryImplTest {


    @Autowired
    private FlourObjectFactory factory;

    @Test
    public void getFlour() {

        BuckwheatFlour buckwheatFlour = factory.getFlour(FlourCode.BUCKWHEAT_FLOUR_COARSE);

        CornFlour cornFlour = factory.getFlour(FlourCode.CORN_FLOUR_MEDIUM);

        assertEquals(buckwheatFlour.getGrinding(), (GrindingFlour.COARSE));
        assertEquals(cornFlour.getGrinding(), (GrindingFlour.MEDIUM));
    }


}