package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.common.sri.ClaveDeAcceso;
import com.rolandopalermo.facturacion.ec.common.util.DateUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;

public abstract class AbstractComprobanteMapper<DTO extends ComprobanteDTO> {

    private Logger logger = Logger.getLogger(AbstractComprobanteMapper.class);

    protected String getClaveAcceso(final InfoTributaria infoTributaria, final String fechaEmision) {
        final StringBuilder sb = new StringBuilder(infoTributaria.getPtoEmi());
        sb.append(infoTributaria.getEstab());
        final String serie = sb.toString();
        final String codigoNumerico = RandomStringUtils.randomNumeric(8);
        try {
            return ClaveDeAcceso.builder()
                    .fechaEmision(DateUtils.getFechaFromStringddMMyyyy(fechaEmision))
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
            logger.error("Error al generar la Clave de Acceso", e);
            return null;
        }
    }

    protected abstract String getFechaEmision(final DTO comprobanteDTO);

}