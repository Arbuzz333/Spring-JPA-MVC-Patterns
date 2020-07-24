package avakhidov.springpatterns;


import avakhidov.anotations.EnableMainRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;





@SpringBootApplication
@PropertySource("classpath:value.properties")
@EnableMainRunner
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);

    }
}
