/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.retencion;

import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@XmlRootElement(name = "comprobanteRetencion")
@XmlType(propOrder = {
        "id",
        "version",
        "infoTributaria",
        "infoCompRetencion",
        "impuesto",
        "campoAdicional"
})
public class ComprobanteRetencion {

    private final String id;
    private final String version;
    private final InfoTributaria infoTributaria;
    private final InfoCompRetencion infoCompRetencion;
    private final List<Impuesto> impuesto;
    @Singular("campoAdicional")
    private final List<CampoAdicional> campoAdicional;

    @XmlElementWrapper(name = "impuestos")
    public List<Impuesto> getImpuesto() {
        return impuesto;
    }

    @XmlElementWrapper(name = "infoAdicional")
    public List<CampoAdicional> getCampoAdicional() {
        return campoAdicional;
    }

}