package com.rolandopalermo.facturacion.ec.dto.comprobantes;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AbstractComprobanteDTO {

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
