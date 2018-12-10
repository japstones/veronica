package com.rolandopalermo.facturacion.ec.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaDetallesDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.TotalImpuesto;
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
		
		   List<GuiaDetallesDTO> guiaDetalleDTO = guiaRemisionDTO.getGuiadetalle();
	        List<GuiaDetalles> guiaDetalle = guiaDetalleDTO.stream()
	                .map(guiasDetRes -> {
	                	GuiaDetalles detalle = new GuiaDetalles(); 
	                	detalle.setCodigoInterno(guiasDetRes.getCodigoInterno());
	                	detalle.setCodigoAdicional(guiasDetRes.getCodigoAdicional());
	                	detalle.setDescripcion(guiasDetRes.getDescripcion());
	                	detalle.setCantidad(guiasDetRes.getCantidad());
	                	List<DetAdicionalDTO> detallesAdicionalesDTO =guiasDetRes.getDetAdicional();
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
