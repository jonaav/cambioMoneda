package com.cambioDeMoneda.service;

import com.cambioDeMoneda.entities.Moneda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMonedaService {

    public Flux<Moneda> getAll();
    public Mono<Moneda> getByID(Long id);
    public Mono<Moneda> create(Moneda request);
    public Mono<Moneda> update(Moneda request);
    /*
    public Mono<Moneda> delete(Long id);
     */
}
