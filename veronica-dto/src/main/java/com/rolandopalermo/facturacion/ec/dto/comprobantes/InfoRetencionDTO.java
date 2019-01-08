package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoRetencionDTO extends InfoComprobanteDTO {

    @NotEmpty
    private String tipoIdentificacionSujetoRetenido;
    @NotEmpty
    private String razonSocialSujetoRetenido;
    @NotEmpty
    private String identificacionSujetoRetenido;
    private String periodoFiscal;

}