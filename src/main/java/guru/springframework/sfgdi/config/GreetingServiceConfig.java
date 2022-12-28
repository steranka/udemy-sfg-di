package guru.springframework.sfgdi.config;

import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

/**
 * Created by jt on 2/20/21.
 */
@PropertySource("classpath:datasource.properties")
@PropertySource("classpath:file1.properties")
@PropertySource("classpath:file2.properties")
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {

    @Bean
    public FakeDataSource fakeDataSource(@Value("${guru.username}") String username,
                                  @Value("${guru.password}") String password,
                                  @Value("${guru.jdbcurl}") String jdbcurl){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jdbcurl);
        System.out.println("fakeDataSource Bean created as " + fakeDataSource.toString());
        return fakeDataSource;
    }

    @Bean
    public Object whichProp(@Value("${foo.bar}") String fooBar, @Value("${foo.baz}") String fooBaz){
        System.out.println("Looks like foo.bar prop is set to " + fooBar  + ", foo.baz=" + fooBaz);
        return new Object();
    }

    @Bean
    PetServiceFactory petServiceFactory(){
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory){
       return petServiceFactory.getPetService("dog");
    }

    @Bean
    @Profile("cat")
    PetService catPetService(PetServiceFactory petServiceFactory){
        return petServiceFactory.getPetService("cat");
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService(){
        return new I18NSpanishService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }

    @Bean
    public Object DisplayPropertiesEnvironment(Environment environment) {
        AbstractEnvironment env = (AbstractEnvironment) environment;
        for (org.springframework.core.env.PropertySource<?> source : env.getPropertySources()) {
            if (source instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource) source;
                System.out.println("Prop: " + source.getName() + "=" + mapPropertySource.getSource());
            }
        }
        return new Object();
    }
}
