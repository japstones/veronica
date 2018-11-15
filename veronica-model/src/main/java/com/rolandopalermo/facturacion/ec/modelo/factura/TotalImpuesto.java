/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.factura;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * @author Rolando
 */
@Getter
@Setter
@XmlType(propOrder = {
        "codigo",
        "codigoPorcentaje",
        "baseImponible",
        "tarifa",
        "valor"
})
public class TotalImpuesto {

    private String codigo;
    private String codigoPorcentaje;
    private BigDecimal baseImponible;
    private BigDecimal tarifa;
    private BigDecimal valor;

}