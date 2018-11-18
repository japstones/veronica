package com.rolandopalermo.facturacion.ec.dto.rest;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirmaFacturaDTO {

    private byte[] facturaAsBase64;
    private FacturaDTO facturaAsObj;
    private String rutaArchivoPkcs12;
    private String claveArchivopkcs12;

}
