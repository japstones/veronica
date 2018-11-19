package com.rolandopalermo.facturacion.ec.dto.rest;

import lombok.Builder;

import java.util.List;

@Builder
public class RespuestaComprobanteDTO {

    private final String claveAccesoConsultada;
    private final String numeroComprobantes;
    private final List<AutorizacionDTO> autorizaciones;

}