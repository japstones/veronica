package com.rolandopalermo.facturacion.ec.bo;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.util.FileUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.FacturaMapper;
import com.rolandopalermo.facturacion.ec.mapper.RetencionMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class GeneradorBO {

    private static final Logger logger = Logger.getLogger(GeneradorBO.class);

    public byte[] generarXMLFactura(FacturaDTO facturaRequestDTO) throws VeronicaException, IOException, JAXBException {
        FacturaMapper mapper = new FacturaMapper();
        return FileUtils.convertirObjAXML(mapper.toModel(facturaRequestDTO));
    }

    public byte[] generarXMLRetencion(RetencionDTO retencionDTO) throws VeronicaException, IOException, JAXBException {
       RetencionMapper mapper = new RetencionMapper();
        return FileUtils.convertirObjAXML(mapper.toModel(retencionDTO));
    }

}
