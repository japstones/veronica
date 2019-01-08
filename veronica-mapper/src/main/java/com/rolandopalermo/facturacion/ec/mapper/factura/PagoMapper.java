package com.rolandopalermo.facturacion.ec.mapper.factura;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.PagoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.Pago;
import org.springframework.stereotype.Component;

@Component("pagoMapper")
public class PagoMapper implements Mapper<PagoDTO, Pago> {
    @Override
    public Pago convert(final PagoDTO pagoDTO) {
        if (pagoDTO == null) {
            return null;
        }

        final Pago pago = new Pago();
        pago.setFormaPago(pagoDTO.getFormaPago());
        pago.setTotal(pagoDTO.getTotal());
        pago.setPlazo(pagoDTO.getPlazo());
        pago.setUnidadTiempo(pagoDTO.getUnidadTiempo());
        return pago;
    }
}
