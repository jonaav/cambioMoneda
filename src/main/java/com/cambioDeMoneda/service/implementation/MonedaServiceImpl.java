package com.cambioDeMoneda.service.implementation;

import com.cambioDeMoneda.controllers.OperacionController;
import com.cambioDeMoneda.entities.Moneda;
import com.cambioDeMoneda.repository.IMonedaRepository;
import com.cambioDeMoneda.service.IMonedaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MonedaServiceImpl implements IMonedaService {

    private static final Logger log = LoggerFactory.getLogger(OperacionController.class);
    @Autowired
    private IMonedaRepository monedaRepository;
    @Override
    public Flux<Moneda> getAll() {
        Flux<Moneda> monedas = Flux.fromIterable(monedaRepository.findAll());
        return monedas;
    }

    @Override
    public Mono<Moneda> getByID(Long id) {
        Moneda monedabd = monedaRepository.findById(id).orElse(null);
        Mono<Moneda> moneda = Mono.just(monedabd);
        return moneda;
    }

    @Override
    public Mono<Moneda> create(Moneda request) {
        Mono<Moneda> moneda = Mono.just(monedaRepository.save(request));
        return moneda;
    }

    @Override
    public Mono<Moneda> update(Moneda request) {
        Mono<Moneda> moneda = Mono.just(monedaRepository.save(request));
        return moneda;
    }
/*
    @Override
    public Mono<Moneda> delete(Long id) {

        Moneda obj = monedaRepository.findById(id).orElse(null);
        obj.setDisponible(false);
        Mono<Moneda> moneda = Mono.just(monedaRepository.save(obj));
        return moneda;
    }

 */
}
