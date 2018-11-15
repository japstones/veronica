/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
@XmlType(propOrder = {
		"codigo",
		"codigoPorcentaje",
		"tarifa",
		"baseImponible",
		"valor"
})
public class Impuesto {

	private String codigo;
	private String codigoPorcentaje;
	private BigDecimal tarifa;
	private BigDecimal baseImponible;
	private BigDecimal valor;

}