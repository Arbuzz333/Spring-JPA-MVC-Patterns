package avakhidov.springpatterns.agreement.services.commonprocess;


import avakhidov.springpatterns.agreement.entyties.Agreement;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;


@TestConfiguration
public class CommonStorageContext {

    public static final String test_system = "test_system";

    @Bean
    public StorageAgreementCommonMemory storageCommonTest() {
        StorageAgreementCommonMemory storage = new StorageAgreementCommonMemory(test_system);
        storage.fillStartStorage(buildAgreements());
        return storage;
    }

    private List<Agreement> buildAgreements() {

        String number = "771";
        String numberBig = "1000";
        String owner = "owner_one";
        String ownerBig = "owner_big";
        Agreement one = new Agreement(number, owner, new BigDecimal(777));
        Agreement two = new Agreement("772", owner, new BigDecimal(123));
        Agreement three = new Agreement("773", owner, new BigDecimal(555));
        Agreement four = new Agreement("774", owner, new BigDecimal(4477));
        Agreement big = new Agreement(numberBig, ownerBig, new BigDecimal(7000));
        return List.of(one, two, three, four, big);
    }

}
