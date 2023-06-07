package com.cambioDeMoneda.service;

import com.cambioDeMoneda.entities.Operacion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOperacionService {

    public Flux<Operacion> getAll();
    public Mono<Operacion> getByID(Long id);
    public Mono<Operacion> exchange(Operacion request);
}
