package no.fintlabs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.utdanning.elev.ElevforholdResource;
import no.fint.model.resource.utdanning.elev.ElevforholdResources;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestUtil {

    private final WebClient webClient;

    public Flux<ElevforholdResource> getElevforholdResource() {
        log.info("About to fetch Mono of ElevforholdResources...");

        Flux<ElevforholdResource> elevforholdResources = webClient.get()
                .uri("/elevforhold")
                .retrieve()
                .bodyToMono(ElevforholdResources.class)
                .flatMapMany(resource -> Flux.fromIterable(resource.getContent()))
                .doOnNext(resource -> log.info("Resource recieved: {}", resource.toString()));

        log.info("This is being logged after the request...");
        return elevforholdResources;
    }

    public Mono<ElevforholdResources> getElevforholdResources() {
        log.info("About to fetch Mono of ElevforholdResources...");

        Mono<ElevforholdResources> elevforholdResources = webClient.get()
                .uri("/elevforhold")
                .retrieve()
                .bodyToMono(ElevforholdResources.class)
                .doOnNext(fravar -> {
                    log.info("Resource recieved: {}", fravar.toString());
                });

        log.info("This is being logged after the request...");
        return elevforholdResources;
    }

    public Mono<String> getUsername() {
        return Mono.just("henrik123");
    }

    public Mono<String> getPassword() {
        return Mono.just("secret123");
    }

}
