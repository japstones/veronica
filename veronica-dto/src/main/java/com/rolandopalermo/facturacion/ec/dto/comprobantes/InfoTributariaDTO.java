package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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