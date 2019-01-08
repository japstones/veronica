package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoFacturaDTO extends InfoComprobanteDTO {

    @NotEmpty
    private String tipoIdentificacionComprador;
    private String guiaRemision;
    @NotEmpty
    private String razonSocialComprador;
    @NotEmpty
    private String identificacionComprador;
    private String direccionComprador;
    @NotNull
    private BigDecimal totalSinImpuestos;
    private BigDecimal totalDescuento;
    @NotEmpty
    @Valid
    private List<TotalImpuestoDTO> totalImpuesto;
    private BigDecimal propina;
    @NotNull
    private BigDecimal importeTotal;
    private String moneda;
    @NotEmpty
    @Valid
    private List<PagoDTO> pagos;
    private BigDecimal valorRetIva;
    private BigDecimal valorRetRenta;

}