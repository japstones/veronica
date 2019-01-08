package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class InfoComprobanteDTO {

    @NotEmpty
    private String fechaEmision;
    @NotEmpty
    private String dirEstablecimiento;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;

}