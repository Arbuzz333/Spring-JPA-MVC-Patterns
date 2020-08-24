package avakhidov.springpatterns.agreement.config;


import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.services.commonprocess.StorageAgreementCommonMemory;
import avakhidov.springpatterns.agreement.storage.StorageAgreementMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

import static avakhidov.springpatterns.agreement.entyties.Agreement.car_loan;
import static avakhidov.springpatterns.agreement.entyties.Agreement.mortgage;
import static avakhidov.springpatterns.agreement.entyties.Agreement.common;




@Configuration
public class AgreementConfig {

    @Bean
    public StorageAgreementMemory storageMortgage() {
        return new StorageAgreementMemory(mortgage);
    }

    @Bean
    public StorageAgreementMemory storageCarLoan() {
        return new StorageAgreementMemory(car_loan);
    }

    @Bean
    public StorageAgreementCommonMemory storageCommon() {
        StorageAgreementCommonMemory storage = new StorageAgreementCommonMemory(common);
        storage.fillStartStorage(buildAgreements());
        return storage;
    }

    private List<Agreement> buildAgreements() {

        String number = "771";
        String numberBig = "1000";
        String owner = "owner_one";
        String ownerBig = "owner_big";
        Agreement one = new Agreement(number, owner, new BigDecimal(777));
        Agreement two = new Agreement("772", "owner_two", new BigDecimal(123));
        Agreement three = new Agreement("773", "owner_three", new BigDecimal(555));
        Agreement four = new Agreement("774", "owner_four", new BigDecimal(4477));
        Agreement big = new Agreement(numberBig, ownerBig, new BigDecimal(7577));
        return List.of(one, two, three, four, big);
    }
}
