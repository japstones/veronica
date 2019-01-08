package com.rolandopalermo.facturacion.ec.mapper.common;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import org.springframework.stereotype.Component;

@Component("impuestoMapper")
public class ImpuestoMapper implements Mapper<ImpuestoDTO, Impuesto> {
    @Override
    public Impuesto convert(final ImpuestoDTO impuestoDTO) {
        if (impuestoDTO == null) {
            return null;
        }

        final Impuesto impuesto = new Impuesto();
        impuesto.setCodigo(impuestoDTO.getCodigo());
        impuesto.setCodigoPorcentaje(impuestoDTO.getCodigoPorcentaje());
        impuesto.setTarifa(impuestoDTO.getTarifa());
        impuesto.setBaseImponible(impuestoDTO.getBaseImponible());
        impuesto.setValor(impuestoDTO.getValor());
        return impuesto;
    }
}
