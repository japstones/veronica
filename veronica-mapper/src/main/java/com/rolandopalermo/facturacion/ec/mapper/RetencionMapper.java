package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.common.sri.ClaveDeAcceso;
import com.rolandopalermo.facturacion.ec.common.util.DateUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.InfoRetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.retencion.InfoCompRetencion;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RetencionMapper extends AbstractComprobanteMapper<RetencionDTO, ComprobanteRetencion> {

    private static final Logger logger = Logger.getLogger(FacturaMapper.class);

    public ComprobanteRetencion toModel(RetencionDTO retencionDTO) {
        InfoTributaria infoTributaria = buildInfoTributaria(retencionDTO);
        ComprobanteRetencion comprobanteRetencion = new ComprobanteRetencion();
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
        StringBuilder sb = new StringBuilder(infoTributaria.getPtoEmi());
        sb.append(infoTributaria.getEstab());
        String serie = sb.toString();
        String codigoNumerico = RandomStringUtils.randomNumeric(8);
        String claveAcceso = "";
        try {
            claveAcceso = ClaveDeAcceso.builder()
                    .fechaEmision(DateUtils.getFechaFromStringddMMyyyy(infoCompRetencion.getFechaEmision()))
                    .ambiente(infoTributaria.getAmbiente())
                    .codigoNumerico(codigoNumerico)
                    .numeroComprobante(infoTributaria.getSecuencial())
                    .ruc(infoTributaria.getRuc())
                    .serie(serie)
                    .tipoComprobante(infoTributaria.getCodDoc())
                    .tipoEmision(infoTributaria.getTipoEmision())
                    .build()
                    .generarClaveAcceso();
        } catch (ParseException e) {
            logger.error("RetencionMapper", e);
        }
        infoTributaria.setClaveAcceso(claveAcceso);
        comprobanteRetencion.setInfoTributaria(infoTributaria);
        return comprobanteRetencion;
    }

}
