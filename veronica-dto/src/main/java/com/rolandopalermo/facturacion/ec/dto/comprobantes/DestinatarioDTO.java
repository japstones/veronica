package com.rolandopalermo.facturacion.ec.dto.comprobantes;

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
}
