package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@JsonPropertyOrder({
        "nombre",
        "valor"
})
public class DetAdicionalDTO {

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String valor;

}