package avakhidov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@PropertySource("classpath:value.properties")
class AppContext {


    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {

        final PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
//        ppc.setIgnoreUnresolvablePlaceholders(true);
        ppc.setIgnoreResourceNotFound(true);

        final List<Resource> resourceLst = new ArrayList<Resource>();

//        resourceLst.add(new ClassPathResource("/value.properties"));
        resourceLst.add(new FileSystemResource("C:/Users/arbuz/IdeaProjects/Patterns/factoryone/src/main/resources/value.properties"));

        ppc.setLocations(resourceLst.toArray(new Resource[]{}));

        return ppc;
    }

}
