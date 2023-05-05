package no.fintlabs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.utdanning.elev.ElevforholdResource;
import no.fint.model.resource.utdanning.elev.ElevforholdResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
public class Controller {

    private final RestUtil restUtil;

    @GetMapping("mono")
    public Mono<ResponseEntity<ElevforholdResources>> getMonoOfElevforholdResources() {
        return restUtil.getElevforholdResources()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("flux")
    public ResponseEntity<Flux<ElevforholdResource>> getFluxOfElevforholdResource() {
        Flux<ElevforholdResource> elevForholdFlux = restUtil.getElevforholdResource();
        return ResponseEntity.ok().body(elevForholdFlux);
    }

    @GetMapping("user")
    public ResponseEntity<?> getUser() {
        Mono<String> monoUsername = restUtil.getUsername();
        Mono<String> monoPassword = restUtil.getPassword();

//        monoUsername.zipWith(monoPassword, (username, password) -> new User(username, password));
        Mono<User> userMono = monoUsername.zipWith(monoPassword, User::new);
        return ResponseEntity.ok().body(userMono);

        // Slik kan man gjøre hele metoden i 1-2 linjer
//        Mono<User> userMono1 = restUtil.getUsername().zipWith(restUtil.getPassword(), User::new);
//        return ResponseEntity.ok().body(userMono1);
    }

}
