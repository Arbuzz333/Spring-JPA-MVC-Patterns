package avakhidov.springpatterns.agreement.config;


import avakhidov.springpatterns.agreement.storage.StorageAgreementMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static avakhidov.springpatterns.agreement.entyties.Agreement.car_loan;
import static avakhidov.springpatterns.agreement.entyties.Agreement.mortgage;




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
}
