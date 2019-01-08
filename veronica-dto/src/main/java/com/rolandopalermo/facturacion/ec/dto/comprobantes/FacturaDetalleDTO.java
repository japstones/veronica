package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "codigoPrincipal",
        "codigoAuxiliar",
        "descripcion",
        "cantidad",
        "precioUnitario",
        "descuento",
        "precioTotalSinImpuesto",
        "detAdicional",
        "impuesto"
})
public class FacturaDetalleDTO {

    private String codigoPrincipal;
    private String codigoAuxiliar;
    @NotEmpty
    private String descripcion;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
    private BigDecimal precioTotalSinImpuesto;
    private List<DetAdicionalDTO> detAdicional;
    @NotEmpty
    @Valid
    private List<ImpuestoDTO> impuesto;

}