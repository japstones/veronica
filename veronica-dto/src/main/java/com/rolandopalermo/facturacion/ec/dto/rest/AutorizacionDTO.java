package com.rolandopalermo.facturacion.ec.dto.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AutorizacionDTO {

    private final String estado;
    private final String numeroAutorizacion;
    private final String fechaAutorizacion;
    private final String ambiente;
    private final String comprobante;
    private final List<MensajeDTO> mensajes;

}