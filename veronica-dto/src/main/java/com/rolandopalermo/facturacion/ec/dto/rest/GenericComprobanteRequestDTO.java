package com.rolandopalermo.facturacion.ec.dto.rest;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.ComprobanteDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GenericComprobanteRequestDTO<TipoComprobante extends ComprobanteDTO> {

    private byte[] comprobanteAsBase64;
    private TipoComprobante comprobanteAsObj;
    @NotNull
    private String rutaArchivoPkcs12;
    @NotNull
    private String claveArchivopkcs12;

}
