package com.cambioDeMoneda.controllers;

import com.cambioDeMoneda.entities.Moneda;
import com.cambioDeMoneda.service.IMonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/monedas/")
public class MonedaController {

    @Autowired
    private IMonedaService monedaService;

    @GetMapping(value = "all", headers = "Accept=application/json")
    public Flux<Moneda> getAll() {
        return monedaService.getAll();
    }

    @GetMapping(value = "{id}", headers = "Accept=application/json")
    public Mono<Moneda> getByID(@PathVariable Long id){
        return monedaService.getByID(id);
    }

    @PostMapping(value = "create", headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Moneda> create(@RequestBody Moneda request){
        return monedaService.create(request);
    }

    @PutMapping(value = "update", headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Moneda> update(@RequestBody Moneda request){
        return monedaService.update(request);
    }
}
