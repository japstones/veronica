/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.factura;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Rolando
 */
@Getter
@Setter
@XmlType(propOrder = {
        "fechaEmision",
        "dirEstablecimiento",
        "contribuyenteEspecial",
        "obligadoContabilidad",
        "tipoIdentificacionComprador",
        "guiaRemision",
        "razonSocialComprador",
        "identificacionComprador",
        "direccionComprador",
        "totalSinImpuestos",
        "totalDescuento",
        "totalImpuesto",
        "propina",
        "importeTotal",
        "moneda",
        "pago",
        "valorRetIva",
        "valorRetRenta"
})
public class InfoFactura {

    private String fechaEmision;
    private String dirEstablecimiento;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;
    private String tipoIdentificacionComprador;
    private String guiaRemision;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String direccionComprador;
    private BigDecimal totalSinImpuestos;
    private BigDecimal totalDescuento;
    private List<TotalImpuesto> totalImpuesto;
    private BigDecimal propina;
    private BigDecimal importeTotal;
    private String moneda;
    private List<Pago> pago;
    private BigDecimal valorRetIva;
    private BigDecimal valorRetRenta;

    @XmlElementWrapper(name = "totalConImpuestos")
    public List<TotalImpuesto> getTotalImpuesto() {
        return totalImpuesto;
    }

    @XmlElementWrapper(name = "pagos")
    public List<Pago> getPago() {
        return pago;
    }

}