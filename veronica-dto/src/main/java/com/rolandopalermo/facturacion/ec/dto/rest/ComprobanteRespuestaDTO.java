package com.rolandopalermo.facturacion.ec.dto.rest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComprobanteRespuestaDTO {

    private String claveAcceso;
    private List<MensajeDTO> mensajes;

}