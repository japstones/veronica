package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import java.util.List;

import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprobanteDTO {

    @NotEmpty
    private String id;
    @NotEmpty
    private String version;
    @NotNull
    @Valid
    private InfoTributariaDTO infoTributaria;
    @Valid
    private List<CampoAdicionalDTO> campoAdicional;

}