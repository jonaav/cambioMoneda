package com.cambioDeMoneda.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="operaciones")
public class Operacion implements Serializable {

    private static final long serialVesionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float monto;
    private String monedaOrigen;
    private String monedaDestino;
    private Float montoTipoDeCambio;
    private Float tipoDeCambio;

    public void calcularCambio(){montoTipoDeCambio= monto*tipoDeCambio;}
}
