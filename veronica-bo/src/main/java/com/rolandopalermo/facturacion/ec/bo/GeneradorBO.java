package com.rolandopalermo.facturacion.ec.bo;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.util.FileUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.mapper.FacturaDTOMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class GeneradorBO {

    private static final Logger logger = Logger.getLogger(GeneradorBO.class);

    public byte[] generarXMLFactura(FacturaDTO facturaRequestDTO) throws VeronicaException, IOException, JAXBException {
        FacturaDTOMapper mapper = new FacturaDTOMapper();
        return FileUtils.convertirObjAXML(mapper.toModel(facturaRequestDTO));
    }

}
