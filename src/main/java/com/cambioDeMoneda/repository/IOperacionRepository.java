package com.cambioDeMoneda.repository;

import com.cambioDeMoneda.entities.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperacionRepository extends JpaRepository<Operacion, Long> {
}
