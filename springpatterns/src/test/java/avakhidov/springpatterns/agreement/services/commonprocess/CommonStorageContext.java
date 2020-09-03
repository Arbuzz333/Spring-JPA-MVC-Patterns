package avakhidov.springpatterns.agreement.services.commonprocess;


import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.storage.PriorityLimit;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@TestConfiguration
public class CommonStorageContext {

    public static final String test_system = "test_system";

    @Bean
    public StorageAgreementCommonMemory storageCommonTest() {
        StorageAgreementCommonMemory storage = new StorageAgreementCommonMemory(test_system);
        fillStartStorageThrowOptional(storage);
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

    private void fillStartStorageThrowOptional(Object o) {
        Optional.of((PriorityLimit<Agreement> & StorageAgreement<String, Agreement>) o)
                .ifPresent(x -> x.fillStartStorage(buildAgreements()));
    }

}
