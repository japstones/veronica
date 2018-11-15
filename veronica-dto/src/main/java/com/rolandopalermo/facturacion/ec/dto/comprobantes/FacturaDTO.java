package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "version",
        "infoTributaria",
        "infoFactura",
        "detalle",
        "campoAdicional"
})
public class FacturaDTO extends AbstractComprobanteDTO {

    @Valid
    private InfoFacturaDTO infoFactura;
    @NotNull
    @Valid
    @Size(min = 1)
    private List<FacturaDetalleDTO> detalle;

}