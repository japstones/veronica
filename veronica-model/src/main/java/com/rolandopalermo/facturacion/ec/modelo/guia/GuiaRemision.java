/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.guia;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;

import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@XmlRootElement(name = "guiaRemision")
@XmlType(propOrder = {
		"id",
		"version",
		"infoTributaria",
		"infoGuiaRemision",
		"destinatario",
		"campoAdicional"
})
public class GuiaRemision {

	private final String id;
	private final String version;
	private final InfoTributaria infoTributaria;
	private final InfoGuiaRemision infoGuiaRemision;
	private final List < Destinatario > destinatario;
	@Singular("campoAdicional")
	private final List<CampoAdicional> campoAdicional;

	@XmlElementWrapper(name = "destinatarios")
	public List < Destinatario > getDestinatario() {
		return destinatario;
	}

	@XmlElementWrapper(name = "infoAdicional")
	public List<CampoAdicional> getCampoAdicional() {
		return campoAdicional;
	}

}