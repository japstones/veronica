package com.rolandopalermo.facturacion.ec.dto.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeDTO {

    private String identificador;
    private String mensaje;
    private String informacionAdicional;
    private String tipo;

}