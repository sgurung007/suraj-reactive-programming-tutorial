package com.gurung.reactiveprogrammingtutorial.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
@Slf4j
@Service
public class FluxAndMonoSideEffect {

    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(p->{
                    return p.length()>number;
                })
                .doOnNext(p->log.info("on my next: {}",p))
                .doOnSubscribe(p->log.info("do on subscribe!!!!"))
                .doOnComplete(()->log.info("do on compelte!!!!"))
                .defaultIfEmpty("default");
    }

}
