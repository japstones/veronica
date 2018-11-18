package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

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
