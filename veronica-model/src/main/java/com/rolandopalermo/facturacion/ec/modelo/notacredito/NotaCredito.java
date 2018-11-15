/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.notacredito;

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
@XmlRootElement(name = "notaCredito")
@XmlType(propOrder = {
        "id",
        "version",
        "infoTributaria",
        "infoNotaCredito",
        "detalle",
        "campoAdicional"
})
public class NotaCredito {

    private final String id;
    private final String version;
    private final InfoTributaria infoTributaria;
    private final InfoNotaCredito infoNotaCredito;
    private final List<Detalle> detalle;
    @Singular("campoAdicional")
    private final List<CampoAdicional> campoAdicional;

    @XmlElementWrapper(name = "detalles")
    public List<Detalle> getDetalle() {
        return detalle;
    }

    @XmlElementWrapper(name = "infoAdicional")
    public List<CampoAdicional> getCampoAdicional() {
        return campoAdicional;
    }

}