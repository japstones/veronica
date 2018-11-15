package com.rolandopalermo.facturacion.ec.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Getter
@Setter
@XmlTransient
public class Comprobante {

    @NotEmpty
    protected String id;
    @NotEmpty
    protected String version;
    @Valid
    protected InfoTributaria infoTributaria;
    private List<CampoAdicional> campoAdicional;

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    @XmlAttribute(name = "version")
    public String getVersion() {
        return version;
    }

    @XmlElementWrapper(name = "infoAdicional")
    public List<CampoAdicional> getCampoAdicional() {
        return campoAdicional;
    }

}
