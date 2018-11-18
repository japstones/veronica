package com.rolandopalermo.facturacion.ec.dto.rest;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirmaRetencionDTO {

    private byte[] retencionAsBase64;
    private RetencionDTO retencionAsObj;
    private String rutaArchivoPkcs12;
    private String claveArchivopkcs12;

}
