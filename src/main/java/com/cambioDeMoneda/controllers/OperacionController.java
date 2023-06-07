package com.cambioDeMoneda.controllers;

import com.cambioDeMoneda.entities.Operacion;
import com.cambioDeMoneda.service.IOperacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/operaciones/")
public class OperacionController {


    @Autowired
    private IOperacionService operacionService;

    @GetMapping(value = "all", headers = "Accept=application/json")
    public Flux<Operacion> getAll(){
        return operacionService.getAll();
    }


    @GetMapping(value = "{id}", headers = "Accept=application/json")
    public Mono<Operacion> getByID(@PathVariable Long id) {
        return operacionService.getByID(id);
    }

    @PostMapping(value = "create", headers = "Accept=application/json")
    public Mono<Operacion> exchange(@RequestBody Operacion request){
        return operacionService.exchange(request);
    }

}
