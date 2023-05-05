package no.fintlabs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {

    @Bean
    public WebClient webClient() {
        String baseUrl = "https://play-with-fint.felleskomponent.no/utdanning/elev";
        return WebClient.create(baseUrl);
    }

}
