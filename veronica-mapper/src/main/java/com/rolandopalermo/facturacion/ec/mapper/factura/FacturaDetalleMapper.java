package com.rolandopalermo.facturacion.ec.mapper.factura;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Factura Detalle Mapper. Mapper encargado de poblar el detalle de la factura.
 */
@Component("facturaDetalleMapper")
public class FacturaDetalleMapper implements Mapper<FacturaDetalleDTO, FacturaDetalle> {

    private Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;

    @Override
    public FacturaDetalle convert(final FacturaDetalleDTO facturaDetalleDTO) {

        if (facturaDetalleDTO == null) {
            return null;
        }

        final FacturaDetalle facturaDetalle = new FacturaDetalle();
        facturaDetalle.setCodigoPrincipal(facturaDetalleDTO.getCodigoPrincipal());
        facturaDetalle.setCodigoAuxiliar(facturaDetalleDTO.getCodigoAuxiliar());
        facturaDetalle.setDescripcion(facturaDetalleDTO.getDescripcion());
        facturaDetalle.setCantidad(facturaDetalleDTO.getCantidad());
        facturaDetalle.setPrecioUnitario(facturaDetalleDTO.getPrecioUnitario());
        facturaDetalle.setDescuento(facturaDetalleDTO.getDescuento());
        facturaDetalle.setPrecioTotalSinImpuesto(facturaDetalleDTO.getPrecioTotalSinImpuesto());
        facturaDetalle.setDetAdicional(getDetAdicionalMapper().convertAll(facturaDetalleDTO.getDetAdicional()));
        facturaDetalle.setImpuesto(getImpuestoMapper().convertAll(facturaDetalleDTO.getImpuesto()));
        return facturaDetalle;
    }

    protected Mapper<DetAdicionalDTO, DetAdicional> getDetAdicionalMapper() {
        return detAdicionalMapper;
    }

    @Autowired
    @Qualifier("detAdicionalMapper")
    public void setDetAdicionalMapper(Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper) {
        this.detAdicionalMapper = detAdicionalMapper;
    }

    protected Mapper<ImpuestoDTO, Impuesto> getImpuestoMapper() {
        return impuestoMapper;
    }

    @Autowired
    @Qualifier("impuestoMapper")
    public void setImpuestoMapper(Mapper<ImpuestoDTO, Impuesto> impuestoMapper) {
        this.impuestoMapper = impuestoMapper;
    }
}
