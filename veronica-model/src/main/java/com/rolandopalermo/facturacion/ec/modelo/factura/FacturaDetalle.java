/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.factura;

import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
@XmlType(propOrder = {
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
public class FacturaDetalle {

	private String codigoPrincipal;
	private String codigoAuxiliar;
	private String descripcion;
	private BigDecimal cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal descuento;
	private BigDecimal precioTotalSinImpuesto;
	@Singular("detAdicional")
	private List < DetAdicional > detAdicional;
	@Singular("impuesto")
	private List < Impuesto > impuesto;

	@XmlElementWrapper(name = "detallesAdicionales")
	public List < DetAdicional > getDetAdicional() {
		return detAdicional;
	}

	@XmlElementWrapper(name = "impuestos")
	public List < Impuesto > getImpuesto() {
		return impuesto;
	}

}