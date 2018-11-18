package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ImpuestoDTO {

    private String codigo;
    private String codigoPorcentaje;
    private String codigoRetencion;
    private BigDecimal tarifa;
    private BigDecimal baseImponible;
    private BigDecimal porcentajeRetener;
    private BigDecimal valorRetenido;
    private String codDocSustento;
    private String numDocSustento;
    private String fechaEmisionDocSustento;
    private BigDecimal valor;

}