package com.cambioDeMoneda.repository;

import com.cambioDeMoneda.entities.Moneda;
import com.cambioDeMoneda.entities.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMonedaRepository extends JpaRepository<Moneda, Long> {

    public Moneda findByNombre(String nombre);
}
