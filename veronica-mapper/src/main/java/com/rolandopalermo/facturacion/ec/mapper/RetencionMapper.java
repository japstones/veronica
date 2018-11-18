package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.InfoRetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.retencion.InfoCompRetencion;

import java.util.List;
import java.util.stream.Collectors;

public class RetencionMapper extends AbstractMapper<RetencionDTO, ComprobanteRetencion> {

    public ComprobanteRetencion toModel(RetencionDTO retencionDTO) {
        ComprobanteRetencion comprobanteRetencion = new ComprobanteRetencion();
        comprobanteRetencion.setInfoTributaria(buildInfoTributaria(retencionDTO));
        comprobanteRetencion.setCampoAdicional(buildCamposAdicionales(retencionDTO));
        comprobanteRetencion.setId(retencionDTO.getId());
        comprobanteRetencion.setVersion(retencionDTO.getVersion());

        InfoCompRetencion infoCompRetencion = new InfoCompRetencion();
        InfoRetencionDTO infoDTO = retencionDTO.getInfoRetencion();
        infoCompRetencion.setFechaEmision(infoDTO.getFechaEmision());
        infoCompRetencion.setDirEstablecimiento(infoDTO.getDirEstablecimiento());
        infoCompRetencion.setContribuyenteEspecial(infoDTO.getContribuyenteEspecial());
        infoCompRetencion.setObligadoContabilidad(infoDTO.getObligadoContabilidad());
        infoCompRetencion.setTipoIdentificacionSujetoRetenido(infoDTO.getTipoIdentificacionSujetoRetenido());
        infoCompRetencion.setRazonSocialSujetoRetenido(infoDTO.getRazonSocialSujetoRetenido());
        infoCompRetencion.setPeriodoFiscal(infoDTO.getPeriodoFiscal());
        comprobanteRetencion.setInfoCompRetencion(infoCompRetencion);

        List<ImpuestoDTO> impuestosDTO = retencionDTO.getImpuesto();
        List<Impuesto> impuestos = impuestosDTO.stream()
                .map(impuestoDTO -> {
                    Impuesto impuesto = new Impuesto();
                    impuesto.setCodigo(impuestoDTO.getCodigo());
                    impuesto.setCodigoRetencion(impuestoDTO.getCodigoRetencion());
                    impuesto.setBaseImponible(impuestoDTO.getBaseImponible());
                    impuesto.setPorcentajeRetener(impuestoDTO.getPorcentajeRetener());
                    impuesto.setValorRetenido(impuestoDTO.getValorRetenido());
                    impuesto.setCodDocSustento(impuestoDTO.getCodDocSustento());
                    impuesto.setNumDocSustento(impuestoDTO.getNumDocSustento());
                    impuesto.setFechaEmisionDocSustento(impuestoDTO.getFechaEmisionDocSustento());
                    return impuesto;
                })
                .collect(Collectors.toList());
        comprobanteRetencion.setImpuesto(impuestos);
        return comprobanteRetencion;
    }

}
