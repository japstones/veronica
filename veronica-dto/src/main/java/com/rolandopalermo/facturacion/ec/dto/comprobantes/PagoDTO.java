package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonPropertyOrder({
        "formaPago",
        "total",
        "plazo",
        "unidadTiempo"
})
public class PagoDTO {

    private String formaPago;
    private BigDecimal total;
    private String plazo;
    private String unidadTiempo;

}