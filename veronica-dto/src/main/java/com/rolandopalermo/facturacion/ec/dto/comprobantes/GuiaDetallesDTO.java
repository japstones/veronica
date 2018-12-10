package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


import lombok.Getter;
import lombok.Setter;

 
@Getter
@Setter
@XmlRootElement(name = "detalle")
@XmlType(propOrder = {
    "codigoInterno", "codigoAdicional", "descripcion", "cantidad",
    "detAdicional"})
public class GuiaDetallesDTO {
	
	private String codigoInterno;
	private String codigoAdicional;
	private String descripcion;
	private BigDecimal cantidad;
    @Valid
    private List<DetAdicionalDTO> detAdicional;
}
