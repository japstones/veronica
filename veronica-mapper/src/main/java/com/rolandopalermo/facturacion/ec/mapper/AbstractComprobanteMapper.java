package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.comprobantes.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Comprobante;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractComprobanteMapper<DTO extends ComprobanteDTO, Model extends Comprobante> {

    public abstract Model toModel(DTO dto);

    protected InfoTributaria buildInfoTributaria(DTO dto) {
        InfoTributaria InfoTributaria = new InfoTributaria();
        InfoTributaria.setAmbiente(dto.getInfoTributaria().getAmbiente());
        InfoTributaria.setTipoEmision(dto.getInfoTributaria().getTipoEmision());
        InfoTributaria.setRazonSocial(dto.getInfoTributaria().getRazonSocial());
        InfoTributaria.setNombreComercial(dto.getInfoTributaria().getNombreComercial());
        InfoTributaria.setRuc(dto.getInfoTributaria().getRuc());
        InfoTributaria.setCodDoc(dto.getInfoTributaria().getCodDoc());
        InfoTributaria.setEstab(dto.getInfoTributaria().getEstab());
        InfoTributaria.setPtoEmi(dto.getInfoTributaria().getPtoEmi());
        InfoTributaria.setSecuencial(dto.getInfoTributaria().getSecuencial());
        InfoTributaria.setDirMatriz(dto.getInfoTributaria().getDirMatriz());
        return InfoTributaria;
    }

    protected List<CampoAdicional> buildCamposAdicionales(DTO dto) {
        //Procesar detalles adicionales
        List<CampoAdicionalDTO> infoAdicionalDTO = dto.getCampoAdicional();
        List<CampoAdicional> infoAdicional = infoAdicionalDTO.stream()
                .map(campoAdicionalDTO -> {
                    CampoAdicional campoAdicional = new CampoAdicional();
                    campoAdicional.setNombre(campoAdicionalDTO.getNombre());
                    campoAdicional.setValue(campoAdicionalDTO.getValue());
                    return campoAdicional;
                })
                .collect(Collectors.toList());
        return infoAdicional;
    }

}