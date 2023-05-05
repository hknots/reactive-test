package no.fintlabs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.utdanning.vurdering.FravarResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestOfWebclient {

    private final WebClient webClient;

    @GetMapping("/fravar")
    public Flux<FravarResource> fetchFravar() {
        log.info("About to fetch fravar...");

        Flux<FravarResource> fravarFlux = webClient.get()
                .uri("/fravar")
                .retrieve()
                .bodyToFlux(FravarResource.class)
                .doOnNext(fravar -> log.info("Fravar recieved: {}", fravar.toString()));

        log.info("This is being logged after the request...");
        return fravarFlux;
    }

}
