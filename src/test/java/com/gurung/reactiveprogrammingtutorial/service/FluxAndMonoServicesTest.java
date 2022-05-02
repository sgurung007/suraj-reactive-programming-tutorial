package com.gurung.reactiveprogrammingtutorial.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices=new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var frutisFlux= fluxAndMonoServices.fruitsFlux();
        StepVerifier.create(frutisFlux)
                .expectNext("Mango", "Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitMono() {
        var frutisMono=fluxAndMonoServices.fruitMono();

        StepVerifier.create(frutisMono)
                .expectNext("Mango")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap(){
        Flux<String> frutisFluxMap = fluxAndMonoServices.fruitsFluxMap();

        StepVerifier.create(frutisFluxMap)
                .expectNext("MANGO","ORANGE","BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter(){
        Flux<String> frutisFluxFilter = fluxAndMonoServices.fruitsFluxFilter(5);

        StepVerifier.create(frutisFluxFilter)
                .expectNext("Orange","Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterAndMap(){
        Flux<String> frutisFluxFilterMap = fluxAndMonoServices.fruitsFluxFilterAndMap(5);

        StepVerifier.create(frutisFluxFilterMap)
                .expectNext("ORANGE","BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterAndFlatMap(){

        Flux<String> stringFluxFlatMap = fluxAndMonoServices.fruitsFluxFilterAndFlatMap().log();

        StepVerifier.create(stringFluxFlatMap)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterAndFlatMapAsyn(){
        Flux<String> stringFlux = fluxAndMonoServices.fruitsFluxFilterAndFlatMapAsyn().log();

        StepVerifier.create(stringFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterAndFlatMapConcat(){
        Flux<String> stringFlux = fluxAndMonoServices.fruitsFluxFilterAndFlatMapConcat().log();

        StepVerifier.create(stringFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransfer(){
        Flux<String> fruitsFluxTransfer = fluxAndMonoServices.fruitsFluxTransfer(5).log();

        StepVerifier.create(fruitsFluxTransfer)
                .expectNext("Orange","Banana")
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap(){
        Mono<List<String>> fruitMonoFlatMap = fluxAndMonoServices.fruitMonoFlatMap();

        StepVerifier.create(fruitMonoFlatMap)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany(){
        Flux<String> fruitMonoFlatMapMany = fluxAndMonoServices.fruitMonoFlatMapMany();

        StepVerifier.create(fruitMonoFlatMapMany)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterEmpty(){
        Flux<String> fruitsFluxFilter = fluxAndMonoServices.fruitsFluxFilter(10);

        StepVerifier.create(fruitsFluxFilter)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxSwitchIfEmpty(){
        Flux<String> fruitsFluxSwitchIfEmpty = fluxAndMonoServices.fruitsFluxSwitchIfEmpty(7);
        StepVerifier.create(fruitsFluxSwitchIfEmpty)
                .expectNext("Pineapple","watermelon")
                .verifyComplete();
    }

    @Test
    void fruitsVeggieConcat(){
        Flux<String> fruitsVeggieConcat = fluxAndMonoServices.fruitsVeggieConcat().log();
        StepVerifier.create(fruitsVeggieConcat)
                .expectNext("Mango", "Orange", "Banana","Cauliflower", "Potato")
                .verifyComplete();
    }

    @Test
    void fruitsVeggieConcatWith(){
        Flux<String> fruitsVeggieConcat = fluxAndMonoServices.fruitsVeggieConcatWith().log();
        StepVerifier.create(fruitsVeggieConcat)
                .expectNext("Mango", "Orange", "Banana","Cauliflower", "Potato")
                .verifyComplete();
    }

    @Test
    void fruitMonoConcatWith(){
        Flux<String> fruitVeggieFlux = fluxAndMonoServices.fruitMonoConcatWith();
        StepVerifier.create(fruitVeggieFlux)
                .expectNext("Mango","Cauliflower")
                .verifyComplete();
    }

    @Test
    void fruitsMerge(){
        Flux<String> fruitsMerge = fluxAndMonoServices.fruitsMerge().log();
        StepVerifier.create(fruitsMerge)
                .expectNext("Apple","Orange","Mango","Pineapple")
                .verifyComplete();
    }

    @Test
    void fruitsMergeWith(){
        Flux<String> fruitsMerge = fluxAndMonoServices.fruitsMergeWith().log();
        StepVerifier.create(fruitsMerge)
                .expectNext("Apple","Orange","Mango","Pineapple")
                .verifyComplete();
    }

    @Test
    void fruitsMergeWithSequential(){
        Flux<String> fruitsMergeWithSequential = fluxAndMonoServices.fruitsMergeWithSequential();
        StepVerifier.create(fruitsMergeWithSequential)
                .expectNext("Apple","Mango","Orange","Pineapple")
                .verifyComplete();
    }

}