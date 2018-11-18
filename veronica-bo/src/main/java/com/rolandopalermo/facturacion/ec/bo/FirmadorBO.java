package com.rolandopalermo.facturacion.ec.bo;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.sri.Firmador;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.FirmaFacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.FirmaRetencionDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FirmadorBO {

    private static final Logger logger = Logger.getLogger(FirmadorBO.class);
    @Autowired
    private GeneradorBO generadorBO;

    public byte[] firmarFactura(FirmaFacturaDTO firmaRequestDTO) throws VeronicaException, JAXBException {
        return firnarComprobanteElectronico(firmaRequestDTO.getFacturaAsObj(), firmaRequestDTO.getFacturaAsBase64(), firmaRequestDTO.getRutaArchivoPkcs12(), firmaRequestDTO.getClaveArchivopkcs12());
    }

    public byte[] firmarRetencion(FirmaRetencionDTO firmaRequestDTO) throws VeronicaException, JAXBException {
        return firnarComprobanteElectronico(firmaRequestDTO.getRetencionAsObj(), firmaRequestDTO.getRetencionAsBase64(), firmaRequestDTO.getRutaArchivoPkcs12(), firmaRequestDTO.getClaveArchivopkcs12());
    }

    private byte[] firnarComprobanteElectronico(ComprobanteDTO comprobanteDTO, byte[] comprobanteAsBase64, String rutaCertificado, String passwordCertificado) throws VeronicaException, JAXBException {
        try {
            // Actividad 1.- Generar archivo temporales para el XML y su respectivo archivo firmado
            String rutaArchivoXML = UUID.randomUUID().toString();
            File temp = File.createTempFile(rutaArchivoXML, ".xml");
            rutaArchivoXML = temp.getAbsolutePath();
            String rutaArchivoXMLFirmado = UUID.randomUUID().toString();
            File tempFirmado = File.createTempFile(rutaArchivoXMLFirmado, ".xml");
            rutaArchivoXMLFirmado = tempFirmado.getAbsolutePath();
            byte[] contentAsBase64 = new byte[0];
            if(comprobanteAsBase64 != null && comprobanteAsBase64.length > 0) {
                contentAsBase64 = comprobanteAsBase64;
            } else if(comprobanteDTO != null) {
                if(comprobanteDTO instanceof FacturaDTO) {
                    contentAsBase64 = generadorBO.generarXMLFactura((FacturaDTO)comprobanteDTO);
                } else if(comprobanteDTO instanceof RetencionDTO) {
                    contentAsBase64 = generadorBO.generarXMLRetencion((RetencionDTO) comprobanteDTO);
                } else {
                    throw new VeronicaException("No es un tipo de documento válido.");
                }
            }
            // Actividad 2.- Guardar datos en archivo xml
            try (FileOutputStream fos = new FileOutputStream(rutaArchivoXML)) {
                fos.write(contentAsBase64);
            }
            // Actividad 3.- Firmar el archivo xml creado temporalmente
            Firmador firmador = new Firmador(rutaArchivoXML, rutaArchivoXMLFirmado, rutaCertificado, passwordCertificado);
            firmador.firmar();
            // 4.- Obtener el contenido del archivo XML
            Path path = Paths.get(rutaArchivoXMLFirmado);
            byte[] data = Files.readAllBytes(path);
            if (!temp.delete() || !tempFirmado.delete()) {
                throw new VeronicaException("No se pudo eliminar los archivos temporales.");
            }
            return data;
        } catch (IOException ex) {
            throw new VeronicaException(ex);
        }
    }

}