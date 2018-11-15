/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Rolando
 */
@Getter
@Setter
public class DetAdicional {

    private String nombre;
    private String valor;

    @XmlAttribute(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    @XmlAttribute(name = "valor")
    public String getValor() {
        return valor;
    }

}