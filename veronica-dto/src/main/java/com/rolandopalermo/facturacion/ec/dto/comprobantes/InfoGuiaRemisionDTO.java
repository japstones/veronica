package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "dirEstablecimiento", "dirPartida", "razonSocialTransportista", "tipoIdentificacionTransportista",
		"rucTransportista", "rise", "obligadoContabilidad", "contribuyenteEspecial", "fechaIniTransporte",
		"fechaFinTransporte", "placa"})
public class InfoGuiaRemisionDTO {

	private String dirEstablecimiento;
	private String dirPartida;
	private String razonSocialTransportista;
	private String tipoIdentificacionTransportista;
	private String rucTransportista;
	private String rise;
	private String obligadoContabilidad;
	private String contribuyenteEspecial;
	private String fechaIniTransporte;
	private String fechaFinTransporte;
	private String placa;


}
