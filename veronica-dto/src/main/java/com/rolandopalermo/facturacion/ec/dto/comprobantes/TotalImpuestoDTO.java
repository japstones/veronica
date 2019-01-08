package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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