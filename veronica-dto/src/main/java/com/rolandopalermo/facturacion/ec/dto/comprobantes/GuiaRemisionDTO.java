package com.rolandopalermo.facturacion.ec.dto.comprobantes;



import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.InfoGuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.DestinatarioDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GuiaRemisionDTO extends ComprobanteDTO {
		
		 private InfoGuiaRemisionDTO infoGuiaRemisionDTO;
		 private  List <DestinatarioDTO> destinatario;
		
	 
	

}
