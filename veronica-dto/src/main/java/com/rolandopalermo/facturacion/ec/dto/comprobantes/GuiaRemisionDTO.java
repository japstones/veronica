package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



import java.util.List;


@Getter
@Setter
@XmlRootElement(name = "guiaRemision")
@XmlType(propOrder = {
		"id",
		"version",
		"infoTributaria",
		"infoGuiaRemision",
		"destinatario",
		"campoAdicional"
})
public class GuiaRemisionDTO extends ComprobanteDTO {
		
		private InfoGuiaRemisionDTO infoGuiaRemisionDTO;
	 
	 	private List<GuiaDetallesDTO> guiadetalle;
	

}
