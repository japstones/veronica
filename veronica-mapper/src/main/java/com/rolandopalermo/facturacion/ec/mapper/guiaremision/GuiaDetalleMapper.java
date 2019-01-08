package com.rolandopalermo.facturacion.ec.mapper.guiaremision;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaDetallesDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaDetalles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("guiaDetalleMapper")
public class GuiaDetalleMapper implements Mapper<GuiaDetallesDTO, GuiaDetalles> {

    private Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper;

    @Override
    public GuiaDetalles convert(final GuiaDetallesDTO guiaDetallesDTO) {

        if (guiaDetallesDTO == null) {
            return null;
        }

        final GuiaDetalles detalle = new GuiaDetalles();
        detalle.setCodigoInterno(guiaDetallesDTO.getCodigoInterno());
        detalle.setCodigoAdicional(guiaDetallesDTO.getCodigoAdicional());
        detalle.setDescripcion(guiaDetallesDTO.getDescripcion());
        detalle.setCantidad(guiaDetallesDTO.getCantidad());
        detalle.setDetAdicional(getDetAdicionalMapper().convertAll(guiaDetallesDTO.getDetAdicional()));
        return detalle;
    }

    protected Mapper<DetAdicionalDTO, DetAdicional> getDetAdicionalMapper() {
        return detAdicionalMapper;
    }

    @Autowired
    @Qualifier("detAdicionalMapper")
    public void setDetAdicionalMapper(Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper) {
        this.detAdicionalMapper = detAdicionalMapper;
    }
}
