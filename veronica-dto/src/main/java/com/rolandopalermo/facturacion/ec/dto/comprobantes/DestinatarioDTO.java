package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioDTO {
	
		private String identificacionDestinatario;
		private String razonSocialDestinatario;
		private String dirDestinatario;
		private String motivoTraslado;
		private String docAduaneroUnico;
		private String codEstabDestino;
		private String ruta;
		private String codDocSustento;
		private String numDocSustento;
		private String numAutDocSustento;
		private String fechaEmisionDocSustento;
		private List<GuiaDetallesDTO> detalle;

		  
}
