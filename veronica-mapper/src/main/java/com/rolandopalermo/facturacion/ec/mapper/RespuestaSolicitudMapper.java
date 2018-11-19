package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.rest.ComprobanteRespuestaDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.MensajeDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RespuestaSolicitudDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import recepcion.ws.sri.gob.ec.RespuestaSolicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RespuestaSolicitudMapper {

    private static final Logger logger = Logger.getLogger(RespuestaSolicitudMapper.class);

    public RespuestaSolicitudDTO toModel(RespuestaSolicitud respuestaSolicitud) {
        RespuestaSolicitudDTO dto = new RespuestaSolicitudDTO();
        if (respuestaSolicitud != null) {
            dto.setEstado(respuestaSolicitud.getEstado());
            List<ComprobanteRespuestaDTO> respuestas = new ArrayList<>();
            if (respuestaSolicitud.getComprobantes() != null && respuestaSolicitud.getComprobantes().getComprobante() != null) {
                respuestas = respuestaSolicitud.getComprobantes().getComprobante().stream()
                        .map(comprobante -> {
                            ComprobanteRespuestaDTO comprobanteRespuestaDTO = new ComprobanteRespuestaDTO();
                            comprobanteRespuestaDTO.setClaveAcceso(comprobante.getClaveAcceso());
                            if (comprobante.getMensajes() != null && comprobante.getMensajes().getMensaje() != null) {
                                List<MensajeDTO> mensajes = comprobante.getMensajes().getMensaje().stream()
                                        .map(mensaje -> {
                                            MensajeDTO mensajeDTO = new MensajeDTO();
                                            mensajeDTO.setIdentificador(mensaje.getIdentificador());
                                            mensajeDTO.setInformacionAdicional(mensaje.getInformacionAdicional());
                                            mensajeDTO.setMensaje(mensaje.getMensaje());
                                            mensajeDTO.setTipo(mensaje.getTipo());
                                            return mensajeDTO;
                                        })
                                        .collect(Collectors.toList());
                                comprobanteRespuestaDTO.setMensajes(mensajes);
                            }

                            return comprobanteRespuestaDTO;
                        })
                        .collect(Collectors.toList());
            }
            dto.setComprobantes(respuestas);
        }
        return dto;
    }

}