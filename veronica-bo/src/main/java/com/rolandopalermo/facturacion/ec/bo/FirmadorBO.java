package com.rolandopalermo.facturacion.ec.bo;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.sri.Firmador;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.GenericComprobanteRequestDTO;
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

    @Autowired
    private GeneradorBO generadorBO;

    public <T extends ComprobanteDTO> byte[] firmarComprobante(final GenericComprobanteRequestDTO<T> requestDTO)
            throws VeronicaException, JAXBException, IOException {

        if (requestDTO.getComprobanteAsObj() != null) {
            return firnarComprobanteElectronico(requestDTO.getComprobanteAsObj(),
                    requestDTO.getRutaArchivoPkcs12(), requestDTO.getClaveArchivopkcs12());
        } else if (requestDTO.getComprobanteAsBase64() != null) {
            return firnarComprobanteElectronico(requestDTO.getComprobanteAsBase64(),
                    requestDTO.getRutaArchivoPkcs12(), requestDTO.getClaveArchivopkcs12());
        } else {
            throw new VeronicaException("No es un tipo de documento válido.");
        }
    }

    byte[] firnarComprobanteElectronico(final ComprobanteDTO comprobanteDTO,
                                        final String rutaCertificado,
                                        final String passwordCertificado) throws VeronicaException, JAXBException, IOException {
        byte[] contentAsBase64 = new byte[0];
        if (comprobanteDTO instanceof FacturaDTO) {
            contentAsBase64 = generadorBO.generarXMLFactura((FacturaDTO) comprobanteDTO);
        } else if (comprobanteDTO instanceof RetencionDTO) {
            contentAsBase64 = generadorBO.generarXMLRetencion((RetencionDTO) comprobanteDTO);
        } else if (comprobanteDTO instanceof GuiaRemisionDTO) {
            contentAsBase64 = generadorBO.generarGuiaXMLRemison((GuiaRemisionDTO) comprobanteDTO);
        } else {
            throw new VeronicaException("No es un tipo de documento válido.");
        }
        return firnarContenido(contentAsBase64, rutaCertificado, passwordCertificado);
    }

    byte[] firnarComprobanteElectronico(byte[] comprobanteAsBase64, String rutaCertificado, String passwordCertificado)
            throws VeronicaException, IOException {
        return firnarContenido(comprobanteAsBase64, rutaCertificado, passwordCertificado);
    }

    private byte[] firnarContenido(byte[] cotenido, String rutaCertificado, String passwordCertificado)
            throws IOException, VeronicaException {
        // Actividad 1.- Generar archivo temporales para el XML y su respectivo archivo
        // firmado
        String rutaArchivoXML = UUID.randomUUID().toString();
        File temp = File.createTempFile(rutaArchivoXML, ".xml");
        rutaArchivoXML = temp.getAbsolutePath();
        String rutaArchivoXMLFirmado = UUID.randomUUID().toString();
        File tempFirmado = File.createTempFile(rutaArchivoXMLFirmado, ".xml");
        rutaArchivoXMLFirmado = tempFirmado.getAbsolutePath();
        // Actividad 2.- Guardar datos en archivo xml
        try (FileOutputStream fos = new FileOutputStream(rutaArchivoXML)) {
            fos.write(cotenido);
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
    }

}