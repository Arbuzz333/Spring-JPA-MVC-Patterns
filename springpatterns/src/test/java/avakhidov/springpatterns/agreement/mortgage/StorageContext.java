package avakhidov.springpatterns.agreement.mortgage;


import avakhidov.springpatterns.agreement.entyties.Agreement;
import avakhidov.springpatterns.agreement.entyties.Payment;
import avakhidov.springpatterns.agreement.storage.StorageAgreement;
import avakhidov.springpatterns.agreement.storage.StorageAgreementMemory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;




@TestConfiguration
public class StorageContext {

    public static final String test_system = "test_system";

    @Bean
    StorageAgreement<String, Agreement> storage() {
        Agreement agreement =
                new Agreement(
                        test_system + "7771888",
                        "OwnerMortgage",
                        new Date(1583020800000L),
                        new Payment(new BigDecimal(555), new Date(1583020800000L))
                );
        List<Agreement> agreementList = new ArrayList<>();
        agreementList.add(agreement);

        return new StorageAgreementMemory(test_system){

            public Agreement getAgreement(String number) {
                return agreementList.stream().filter(a -> a.getNumber().equals(number)).findFirst().orElse(agreement);
            }

            public void saveAgreement(Agreement agreement){
                agreementList.add(agreement);
            }

            public Optional<Agreement> findAgreement(String owner){
                return agreementList.stream().filter(a -> a.getOwner().equals(owner)).findFirst();
            }
        };
    }

}
