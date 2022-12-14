package guru.springframework.sfgdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Controller
@Profile("loud")
public class GreetingServiceLoudImpl implements  GreetingService {
    @Override
    public String sayGreeting() {
        return "GreetingServiceLoudImpl: SAYS HI";
    }
}
