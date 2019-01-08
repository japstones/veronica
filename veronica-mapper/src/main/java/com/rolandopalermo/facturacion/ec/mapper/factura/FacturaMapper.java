package com.rolandopalermo.facturacion.ec.mapper.factura;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.*;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("facturaMapper")
public class FacturaMapper extends AbstractComprobanteMapper<FacturaDTO> implements Mapper<FacturaDTO, Factura> {

    private Mapper<InfoTributariaDTO, InfoTributaria> InfoTributariaMapper;
    private Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper;
    private Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper;
    private Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper;

    @Override
    public Factura convert(final FacturaDTO facturaDTO) {

        if (facturaDTO == null) {
            return null;
        }

        final Factura factura = new Factura();
        factura.setId(facturaDTO.getId());
        factura.setVersion(facturaDTO.getVersion());
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(facturaDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(facturaDTO)));
            factura.setInfoTributaria(infoTributaria);

        }
        factura.setCampoAdicional(getCampoAdicionalMapper().convertAll(facturaDTO.getCampoAdicional()));
        factura.setInfoFactura(getInfoFacturaMapper().convert(facturaDTO.getInfoFactura()));
        factura.setDetalle(getFacturaDetalleMapper().convertAll(facturaDTO.getDetalle()));
        return factura;
    }

    @Override
    protected String getFechaEmision(final FacturaDTO facturaDTO) {
        return Optional.ofNullable(facturaDTO)
                .map(FacturaDTO::getInfoFactura)
                .map(InfoFacturaDTO::getFechaEmision)
                .orElse(null);
    }

    protected Mapper<InfoTributariaDTO, InfoTributaria> getInfoTributariaMapper() {
        return InfoTributariaMapper;
    }

    @Autowired
    @Qualifier("infoTributariaMapper")
    public void setInfoTributariaMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper) {
        InfoTributariaMapper = infoTributariaMapper;
    }

    protected Mapper<CampoAdicionalDTO, CampoAdicional> getCampoAdicionalMapper() {
        return campoAdicionalMapper;
    }

    @Autowired
    @Qualifier("campoAdicionalMapper")
    public void setCampoAdicionalMapper(Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper) {
        this.campoAdicionalMapper = campoAdicionalMapper;
    }

    protected Mapper<InfoFacturaDTO, InfoFactura> getInfoFacturaMapper() {
        return infoFacturaMapper;
    }

    @Autowired
    @Qualifier("infoFacturaMapper")
    public void setInfoFacturaMapper(Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper) {
        this.infoFacturaMapper = infoFacturaMapper;
    }

    protected Mapper<FacturaDetalleDTO, FacturaDetalle> getFacturaDetalleMapper() {
        return facturaDetalleMapper;
    }

    @Autowired
    @Qualifier("facturaDetalleMapper")
    public void setFacturaDetalleMapper(Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper) {
        this.facturaDetalleMapper = facturaDetalleMapper;
    }
}
