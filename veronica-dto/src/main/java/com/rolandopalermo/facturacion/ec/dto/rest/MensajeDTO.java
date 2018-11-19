package com.rolandopalermo.facturacion.ec.dto.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MensajeDTO {

    private final String identificador;
    private final String mensaje;
    private final String informacionAdicional;
    private final String tipo;

}