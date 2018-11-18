package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RetencionDTO extends ComprobanteDTO {

    private InfoRetencionDTO infoRetencion;
    @NotNull
    @Valid
    @Size(min = 1)
    private List<ImpuestoDTO> impuesto;

}