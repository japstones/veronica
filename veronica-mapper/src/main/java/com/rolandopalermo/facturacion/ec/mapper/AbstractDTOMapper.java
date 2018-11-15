package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.AbstractComprobanteDTO;
import com.rolandopalermo.facturacion.ec.modelo.Comprobante;

public abstract class AbstractDTOMapper<DTO extends AbstractComprobanteDTO, Model extends Comprobante> {

    public abstract Model toModel(DTO dto);

}