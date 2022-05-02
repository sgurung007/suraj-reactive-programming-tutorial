package com.gurung.reactiveprogrammingtutorial.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoSideEffectTest {
    FluxAndMonoSideEffect fluxAndMonoSideEffect=new FluxAndMonoSideEffect();

    @Test
    void fruitsFluxFilter(){
        Flux<String> stringFlux = fluxAndMonoSideEffect.fruitsFluxFilter(5);
        StepVerifier.create(stringFlux)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

}