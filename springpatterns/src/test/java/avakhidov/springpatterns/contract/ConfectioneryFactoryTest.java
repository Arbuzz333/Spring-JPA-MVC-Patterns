package avakhidov.springpatterns.contract;

import avakhidov.springpatterns.contract.services.Contract;
import avakhidov.springpatterns.contract.entyties.Currency;
import avakhidov.springpatterns.contract.entyties.CurrencyAmerica;
import avakhidov.springpatterns.contract.services.MilkFactory;
import avakhidov.springpatterns.contract.services.ConfectioneryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfectioneryFactoryTest {

    @Autowired
    ConfectioneryFactory<CurrencyAmerica> confectioneryFactory;

    @Autowired
    MilkFactory<CurrencyAmerica> milkFactory;


    @Test
    public void createTest() {

        Contract<CurrencyAmerica> confectioneryContract = confectioneryFactory.getContract();
        Currency confectioneryContent = confectioneryContract.getContent();
        Currency.CurrencyType confectioneryType = confectioneryContent.getCurrencyType();

        Currency milkContent = milkFactory.getContract().getContent();
        Currency.CurrencyType milkType = milkContent.getCurrencyType();

        assertEquals("", Currency.CurrencyType.RUB, confectioneryType);

        assertEquals("", Currency.CurrencyType.RUB, milkType);

        assertNotEquals("", CurrencyAmerica.class, confectioneryContent.getClass());
        assertNotEquals("", CurrencyAmerica.class, milkContent.getClass());

    }

}
