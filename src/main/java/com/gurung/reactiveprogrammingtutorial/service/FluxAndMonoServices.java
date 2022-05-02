package com.gurung.reactiveprogrammingtutorial.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Service
public class FluxAndMonoServices {

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"));
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(p->{
                    return p.length()>number;
                })
                .defaultIfEmpty("default");
    }

    public Flux<String> fruitsFluxFilterAndMap(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(p->{
                    return p.length()>number;
                }).map(p->{
                    return p.toUpperCase();
                });
    }

    public Flux<String> fruitsFluxFilterAndFlatMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(p->Flux.just(p.split("")));
    }

    public Flux<String> fruitsFluxFilterAndFlatMapAsyn() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(p->Flux.just(p.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ));
    }

    public Flux<String> fruitsFluxFilterAndFlatMapConcat() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .concatMap(p->Flux.just(p.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ));
    }

    public Flux<String> fruitsFluxTransfer(int number) {

        Function<Flux<String>,Flux<String>> filterData=data->data.filter(p->p.length()>number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData);
    }

    public Flux<String> fruitsFluxSwitchIfEmpty(int number) {

        Function<Flux<String>,Flux<String>> filterData=data->data.filter(p->p.length()>number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.fromIterable(List.of("Pineapple","watermelon")).transform(filterData));
    }

    public Flux<String> fruitsVeggieConcat(){
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Cauliflower", "Potato");
        Flux<String> concatFrutisVeggies = Flux.concat(fruits, veggies);
        return concatFrutisVeggies;
    }

    public Flux<String> fruitsVeggieConcatWith(){
        Flux<String> fruits = Flux.just("Mango", "Orange","Banana");
        Flux<String> veggies = Flux.just("Cauliflower", "Potato");
        Flux<String> concatFrutisVeggies =fruits.concatWith(veggies);
        return concatFrutisVeggies;
    }

    public Flux<String> fruitMonoConcatWith(){
        Mono<String> fruit = Mono.just("Mango");
        Mono<String> veggie = Mono.just("Cauliflower");
        Flux<String> fruitVeggieFlux = fruit.concatWith(veggie);
        return fruitVeggieFlux;
    }

    public Flux<String> fruitsMerge(){
        Flux<String> myFruits = Flux.just("Apple", "Mango").delayElements(Duration.ofMillis(50));
        Flux<String> yourFruits = Flux.just("Orange", "Pineapple").delayElements(Duration.ofMillis(75));

        return Flux.merge(myFruits,yourFruits);

    }

    public Flux<String> fruitsMergeWith(){
        Flux<String> myFruits = Flux.just("Apple", "Mango").delayElements(Duration.ofMillis(50));
        Flux<String> yourFruits = Flux.just("Orange", "Pineapple").delayElements(Duration.ofMillis(75));

        return myFruits.mergeWith(yourFruits);
    }

    public Flux<String> fruitsMergeWithSequential(){
        Flux<String> myFruits = Flux.just("Apple", "Mango").delayElements(Duration.ofMillis(50));
        Flux<String> yourFruits = Flux.just("Orange", "Pineapple").delayElements(Duration.ofMillis(75));

        return Flux.mergeSequential(myFruits,yourFruits);
    }

    public Flux<String> fruitsFluxZip(){
        Flux<String> myFruits = Flux.just("Apple", "Mango");
        Flux<String> yourFruits = Flux.just("Orange", "Pineapple");
        return Flux.zip(myFruits,yourFruits,(first,second)->first+second);
    }

    public Flux<String> fruitsFluxZipWith(){
        Flux<String> myFruits = Flux.just("Apple", "Mango");
        Flux<String> yourFruits = Flux.just("Orange", "Pineapple");
        return myFruits.zipWith(yourFruits,(first,second)->first+second);
    }

    public Flux<String> fruitsFluxZipTuple(){
        Flux<String> myFruits = Flux.just("Apple", "Mango");
        Flux<String> yourFruits = Flux.just("Orange", "Pineapple");
        Flux<String> moreFruits = Flux.just("Raspberry", "Banana");
        return Flux.zip(myFruits,yourFruits,moreFruits)
                .map(objects -> objects.getT1()+objects.getT2()+objects.getT3());
    }



    public Mono<String> fruitMono() {
        return Mono.just("Mango");
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("Mango").flatMap(p->Mono.just(List.of(p.split(""))));
    }

    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Mango").flatMapMany(p->Flux.just(p.split("")));
    }

    public Mono<String> fruitsMonoZipTuple(){
        Mono<String> myFruit = Mono.just("Apple");
        Mono<String> yourFruit = Mono.just("Banana");
        Mono<String> moreFruit = Mono.just("Pineapple");
        return Mono.zip(myFruit,yourFruit,moreFruit)
                .map(objects -> objects.getT1()+objects.getT2()+objects.getT3());
    }

}
