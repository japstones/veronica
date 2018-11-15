package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@JsonPropertyOrder({
        "ambiente",
        "tipoEmision",
        "razonSocial",
        "nombreComercial",
        "ruc",
        "claveAcceso",
        "codDoc",
        "estab",
        "ptoEmi",
        "secuencial",
        "dirMatriz"
})
public class InfoTributariaDTO {

    @NotEmpty
    private String ambiente;
    @NotEmpty
    private String tipoEmision;
    @NotEmpty
    private String razonSocial;
    private String nombreComercial;
    @NotEmpty
    private String ruc;
    @NotEmpty
    private String claveAcceso;
    @NotEmpty
    private String codDoc;
    @NotEmpty
    private String estab;
    @NotEmpty
    private String ptoEmi;
    @NotEmpty
    private String secuencial;
    @NotEmpty
    private String dirMatriz;

}