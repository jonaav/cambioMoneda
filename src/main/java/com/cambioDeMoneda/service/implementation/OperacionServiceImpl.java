package com.cambioDeMoneda.service.implementation;

import com.cambioDeMoneda.entities.Moneda;
import com.cambioDeMoneda.entities.Operacion;
import com.cambioDeMoneda.repository.IMonedaRepository;
import com.cambioDeMoneda.repository.IOperacionRepository;
import com.cambioDeMoneda.service.IOperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperacionServiceImpl implements IOperacionService {


    @Autowired
    private IOperacionRepository operacionRepository;

    @Autowired
    private IMonedaRepository monedaRepository;

    @Override
    public Flux<Operacion> getAll() {
        Flux<Operacion> operaciones = Flux.fromIterable(operacionRepository.findAll());
        return operaciones;
    }
    @Override
    public Mono<Operacion> getByID(Long id) {
        Operacion operacion = operacionRepository.findById(id).orElse(null);
        return Mono.just(operacion);
    }


    public Mono<Operacion> exchange(Operacion request){

        Operacion nuevaOperacion = calcularTipoDeCambio(request);
        nuevaOperacion.calcularCambio();

        return Mono.just(operacionRepository.save(nuevaOperacion));
    }

    private Operacion calcularTipoDeCambio(Operacion request) {

        Moneda mOrigen = monedaRepository.findByNombre(request.getMonedaOrigen());
        Moneda mDestino = monedaRepository.findByNombre(request.getMonedaDestino());

        Float tipoDeCambio = mDestino.getValorDolar()/ mOrigen.getValorDolar();

        return Operacion.builder()
                .monto(request.getMonto())
                .monedaOrigen(request.getMonedaOrigen())
                .monedaDestino(request.getMonedaDestino())
                .tipoDeCambio(tipoDeCambio)
                .build();

    }

}
