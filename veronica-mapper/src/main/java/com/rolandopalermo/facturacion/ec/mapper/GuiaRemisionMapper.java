package com.rolandopalermo.facturacion.ec.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.DestinatarioDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaDetallesDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.guia.Destinatario;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaDetalles;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.guia.InfoGuiaRemision;

@Component
public class GuiaRemisionMapper extends AbstractComprobanteMapper<GuiaRemisionDTO, GuiaRemision> {
	private static final Logger logger = Logger.getLogger(GuiaRemisionMapper.class);

	@Override
	public GuiaRemision toModel(GuiaRemisionDTO guiaRemisionDTO) {
		 InfoTributaria infoTributaria = buildInfoTributaria(guiaRemisionDTO);
		GuiaRemision guiaRemision=new GuiaRemision();
		guiaRemision.setCampoAdicional(buildCamposAdicionales(guiaRemisionDTO));
		guiaRemision.setId(guiaRemisionDTO.getId());
		guiaRemision.setVersion(guiaRemisionDTO.getVersion());
		guiaRemision.setInfoTributaria(infoTributaria);
		
		//Procesando el Detalle de la guia 
		
		   List<DestinatarioDTO> guiaDetalleDTO = guiaRemisionDTO.getDestinatario();
		   List<Destinatario> guiaDestiRes=guiaDetalleDTO.stream()
		   .map(guiaDestinatario -> {
			   Destinatario des = new Destinatario(); 
			   des.setIdentificacionDestinatario(guiaDestinatario.getIdentificacionDestinatario());
			   des.setRazonSocialDestinatario(guiaDestinatario.getRazonSocialDestinatario());
			   des.setDirDestinatario(guiaDestinatario.getDirDestinatario());
			   des.setMotivoTraslado(guiaDestinatario.getMotivoTraslado());
			   des.setDocAduaneroUnico(guiaDestinatario.getDocAduaneroUnico());
			   des.setCodEstabDestino(guiaDestinatario.getCodEstabDestino());
			   des.setRuta(guiaDestinatario.getRuta());
			   des.setCodDocSustento(guiaDestinatario.getCodDocSustento());
			   des.setNumDocSustento(guiaDestinatario.getNumDocSustento());
			   des.setNumAutDocSustento(guiaDestinatario.getNumAutDocSustento());
			   des.setFechaEmisionDocSustento(guiaDestinatario.getFechaEmisionDocSustento());
			   List<GuiaDetallesDTO> guiaDetalleDTOS = guiaDestinatario.getDetalle();
			   List<GuiaDetalles> detalles = guiaDetalleDTOS.stream()
					   .map(guiaDetalleRes->{
						   GuiaDetalles detalle = new GuiaDetalles(); 
						   detalle.setCodigoInterno(guiaDetalleRes.getCodigoInterno());
		                	detalle.setCodigoAdicional(guiaDetalleRes.getCodigoAdicional());
		                	detalle.setDescripcion(guiaDetalleRes.getDescripcion());
		                	detalle.setCantidad(guiaDetalleRes.getCantidad());
		                	List<DetAdicionalDTO> detallesAdicionalesDTO =guiaDetalleRes.getDetAdicional();
		                	List<DetAdicional> detallesAdicionales = detallesAdicionalesDTO.stream()
		                            .map(detAdicionalDTO -> {
		                                DetAdicional detAdicional = new DetAdicional();
		                                detAdicional.setNombre(detAdicionalDTO.getNombre());
		                                detAdicional.setValor(detAdicionalDTO.getValor());
		                                
		                                return detAdicional;
		                            })
		                            .collect(Collectors.toList());
		                	detalle.setDetAdicional(detallesAdicionales);
		                    return detalle;
					   })
					   .collect(Collectors.toList());
					   
			   	des.setDetalle(detalles);
					
			   return des;
		   })
		   .collect(Collectors.toList());
		   guiaRemision.setDestinatario(guiaDestiRes);
		   
	        
		//Prosesamos Guia 
		InfoGuiaRemision infoGuiaRemision = new InfoGuiaRemision();
		infoGuiaRemision.setDirEstablecimiento(guiaRemisionDTO.getInfoGuiaRemisionDTO().getDirEstablecimiento());
		infoGuiaRemision.setDirPartida(guiaRemisionDTO.getInfoGuiaRemisionDTO().getDirPartida());
		infoGuiaRemision.setRazonSocialTransportista(guiaRemisionDTO.getInfoGuiaRemisionDTO().getRazonSocialTransportista());
		infoGuiaRemision.setTipoIdentificacionTransportista(guiaRemisionDTO.getInfoGuiaRemisionDTO().getTipoIdentificacionTransportista());
		infoGuiaRemision.setRucTransportista(guiaRemisionDTO.getInfoGuiaRemisionDTO().getRucTransportista());
		infoGuiaRemision.setObligadoContabilidad(guiaRemisionDTO.getInfoGuiaRemisionDTO().getObligadoContabilidad());
		infoGuiaRemision.setContribuyenteEspecial(guiaRemisionDTO.getInfoGuiaRemisionDTO().getContribuyenteEspecial());
		infoGuiaRemision.setFechaIniTransporte(guiaRemisionDTO.getInfoGuiaRemisionDTO().getFechaIniTransporte());
		infoGuiaRemision.setFechaFinTransporte(guiaRemisionDTO.getInfoGuiaRemisionDTO().getFechaFinTransporte());
		infoGuiaRemision.setPlaca(guiaRemisionDTO.getInfoGuiaRemisionDTO().getPlaca());
		
		guiaRemision.setInfoGuiaRemision(infoGuiaRemision);
		
		
		return guiaRemision;
	}
	

}
