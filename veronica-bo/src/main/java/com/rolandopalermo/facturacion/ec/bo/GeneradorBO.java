package com.rolandopalermo.facturacion.ec.bo;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.util.FileUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.factura.FacturaMapper;
import com.rolandopalermo.facturacion.ec.mapper.guiaremision.GuiaRemisionMapper;
import com.rolandopalermo.facturacion.ec.mapper.retencion.RetencionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class GeneradorBO {

    @Autowired
    private FacturaMapper facturaMapper;
    @Autowired
    private RetencionMapper retencionMapper;
    @Autowired
    private GuiaRemisionMapper guiaRemisionMapper;

    public byte[] generarXMLFactura(FacturaDTO facturaRequestDTO) throws VeronicaException, IOException, JAXBException {
        return FileUtils.convertirObjAXML(facturaMapper.convert(facturaRequestDTO));
    }

    public byte[] generarXMLRetencion(RetencionDTO retencionDTO) throws VeronicaException, IOException, JAXBException {
        return FileUtils.convertirObjAXML(retencionMapper.convert(retencionDTO));
    }

    public byte[] generarGuiaXMLRemison(GuiaRemisionDTO guiaRemisionDTO) throws VeronicaException, IOException, JAXBException {
        return FileUtils.convertirObjAXML(guiaRemisionMapper.convert(guiaRemisionDTO));
    }

}
