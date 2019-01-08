package com.rolandopalermo.facturacion.ec.mapper.common;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import org.springframework.stereotype.Component;

@Component("campoAdicionalMapper")
public class CampoAdicionalMapper implements Mapper<CampoAdicionalDTO, CampoAdicional> {

    @Override
    public CampoAdicional convert(final CampoAdicionalDTO campoAdicionalDTO) {

        if (campoAdicionalDTO == null) {
            return null;
        }

        final CampoAdicional campoAdicional = new CampoAdicional();
        campoAdicional.setNombre(campoAdicionalDTO.getNombre());
        campoAdicional.setValue(campoAdicionalDTO.getValue());

        return campoAdicional;
    }
}
