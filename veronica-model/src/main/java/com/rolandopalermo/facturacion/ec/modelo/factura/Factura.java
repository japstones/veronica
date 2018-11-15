/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.factura;

import com.rolandopalermo.facturacion.ec.modelo.Comprobante;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Rolando
 */
@Getter
@Setter
@XmlRootElement(name = "factura")
@XmlType(propOrder = {
        "id",
        "version",
        "infoTributaria",
        "infoFactura",
        "detalle",
        "campoAdicional"
})
public class Factura extends Comprobante {

    private InfoFactura infoFactura;
    @Singular("detalle")
    private List<FacturaDetalle> detalle;

    @XmlElementWrapper(name = "detalles")
    public List<FacturaDetalle> getDetalle() {
        return detalle;
    }

}