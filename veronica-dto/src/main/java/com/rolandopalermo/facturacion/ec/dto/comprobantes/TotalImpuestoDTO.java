package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@JsonPropertyOrder({
        "codigo",
        "codigoPorcentaje",
        "descuentoAdicional",
        "baseImponible",
        "tarifa",
        "valor"
})
public class TotalImpuestoDTO {

    @NotEmpty
    private String codigo;
    @NotEmpty
    private String codigoPorcentaje;
    private BigDecimal descuentoAdicional;
    @NotNull
    private BigDecimal baseImponible;
    private BigDecimal tarifa;
    @NotNull
    private BigDecimal valor;

}