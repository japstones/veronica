package com.rolandopalermo.facturacion.ec.mapper.common;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import org.springframework.stereotype.Component;

@Component("detAdicionalMapper")
public class DetAdicionalMapper implements Mapper<DetAdicionalDTO, DetAdicional> {
    @Override
    public DetAdicional convert(final DetAdicionalDTO detAdicionalDTO) {

        if (detAdicionalDTO == null) {
            return null;
        }

        final DetAdicional detAdicional = new DetAdicional();
        detAdicional.setNombre(detAdicionalDTO.getNombre());
        detAdicional.setValor(detAdicionalDTO.getValor());
        return detAdicional;
    }
}
