package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class CampoAdicionalDTO {

    @NotEmpty
    private String value;
    @NotEmpty
    private String nombre;

}