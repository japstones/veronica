/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Rolando
 */
@Getter
@Setter
public class CampoAdicional {

    private String value;
    private String nombre;

    @XmlAttribute(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

}