package com.rolandopalermo.facturacion.ec.mapper.guiaremision;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.InfoGuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.guia.InfoGuiaRemision;
import org.springframework.stereotype.Component;

@Component("infoGuiaRemisionMapper")
public class InfoGuiaRemisionMapper implements Mapper<InfoGuiaRemisionDTO, InfoGuiaRemision> {
    @Override
    public InfoGuiaRemision convert(final InfoGuiaRemisionDTO infoGuiaRemisionDTO) {

        if(infoGuiaRemisionDTO == null) {
            return null;
        }

        final InfoGuiaRemision infoGuiaRemision = new InfoGuiaRemision();
        infoGuiaRemision.setDirEstablecimiento(infoGuiaRemisionDTO.getDirEstablecimiento());
        infoGuiaRemision.setDirPartida(infoGuiaRemisionDTO.getDirPartida());
        infoGuiaRemision.setRazonSocialTransportista(infoGuiaRemisionDTO.getRazonSocialTransportista());
        infoGuiaRemision.setTipoIdentificacionTransportista(infoGuiaRemisionDTO.getTipoIdentificacionTransportista());
        infoGuiaRemision.setRucTransportista(infoGuiaRemisionDTO.getRucTransportista());
        infoGuiaRemision.setObligadoContabilidad(infoGuiaRemisionDTO.getObligadoContabilidad());
        infoGuiaRemision.setContribuyenteEspecial(infoGuiaRemisionDTO.getContribuyenteEspecial());
        infoGuiaRemision.setFechaIniTransporte(infoGuiaRemisionDTO.getFechaIniTransporte());
        infoGuiaRemision.setFechaFinTransporte(infoGuiaRemisionDTO.getFechaFinTransporte());
        infoGuiaRemision.setPlaca(infoGuiaRemisionDTO.getPlaca());
        infoGuiaRemision.setRise(infoGuiaRemisionDTO.getRise());
        return infoGuiaRemision;
    }
}
