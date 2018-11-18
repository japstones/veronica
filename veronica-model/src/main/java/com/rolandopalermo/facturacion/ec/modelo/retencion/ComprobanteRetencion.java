/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.retencion;

import com.rolandopalermo.facturacion.ec.modelo.Comprobante;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Rolando
 */
@Getter
@Setter
@XmlRootElement(name = "comprobanteRetencion")
@XmlType(propOrder = {
        "id",
        "version",
        "infoTributaria",
        "infoCompRetencion",
        "impuesto",
        "campoAdicional"
})
public class ComprobanteRetencion extends Comprobante {

    private InfoCompRetencion infoCompRetencion;
    private List<Impuesto> impuesto;

    @XmlElementWrapper(name = "impuestos")
    public List<Impuesto> getImpuesto() {
        return impuesto;
    }

}