package com.rolandopalermo.facturacion.ec.mapper.common;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.TotalImpuesto;
import org.springframework.stereotype.Component;

@Component("totalImpuestoMapper")
public class TotalImpuestoMapper implements Mapper<TotalImpuestoDTO, TotalImpuesto> {
    @Override
    public TotalImpuesto convert(final TotalImpuestoDTO totalImpuestoDTO) {
        if (totalImpuestoDTO == null) {
            return null;
        }

        final TotalImpuesto totalImpuesto = new TotalImpuesto();
        totalImpuesto.setCodigo(totalImpuestoDTO.getCodigo());
        totalImpuesto.setCodigoPorcentaje(totalImpuestoDTO.getCodigoPorcentaje());
        totalImpuesto.setBaseImponible(totalImpuestoDTO.getBaseImponible());
        totalImpuesto.setValor(totalImpuestoDTO.getValor());
        return totalImpuesto;
    }
}
