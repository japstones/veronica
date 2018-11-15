package com.rolandopalermo.facturacion.ec.modelo.notadebito;

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

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@XmlType(name = "", propOrder = {
        "id",
        "version",
        "infoTributaria",
        "infoNotaDebito",
        "motivo",
        "campoAdicional"
})
@XmlRootElement(name = "notaDebito")
public class NotaDebito {

    private final String id;
    private final String version;
    private final InfoTributaria infoTributaria;
    private final InfoNotaDebito infoNotaDebito;
    private final List<Motivo> motivo;
    @Singular("campoAdicional")
    private final List<CampoAdicional> campoAdicional;

    @XmlElementWrapper(name = "motivos")
    public List<Motivo> getMotivo() {
        return motivo;
    }

    @XmlElementWrapper(name = "infoAdicional")
    public List<CampoAdicional> getCampoAdicional() {
        return campoAdicional;
    }

}