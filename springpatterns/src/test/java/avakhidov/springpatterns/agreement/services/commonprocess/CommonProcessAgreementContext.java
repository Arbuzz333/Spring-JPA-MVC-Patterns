package avakhidov.springpatterns.agreement.services.commonprocess;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.test.context.TestConfiguration;




@TestConfiguration
@ComponentScan(value = {"avakhidov.springpatterns.agreement.services.commonprocess", "avakhidov.springpatterns.agreement.config"})
public class CommonProcessAgreementContext {

    public static final String test_system = "test_system";

    @Autowired
    private StorageAgreementCommonMemory storageCommonTest;

    @Bean
    public CommonProcessAgreement commonProcessAgreementTest() {
        return new CommonProcessAgreement(storageCommonTest);
    }

}
