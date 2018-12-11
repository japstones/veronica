package com.rolandopalermo.facturacion.ec.bo;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.util.FileUtils;
import com.rolandopalermo.facturacion.ec.common.util.JaxbUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.GenericComprobanteRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RespuestaComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RespuestaSolicitudDTO;
import com.rolandopalermo.facturacion.ec.mapper.FacturaMapper;
import com.rolandopalermo.facturacion.ec.mapper.RespuestaComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.RespuestaSolicitudMapper;
import com.rolandopalermo.facturacion.ec.mapper.RetencionMapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.soap.client.AutorizacionComprobanteProxy;
import com.rolandopalermo.facturacion.ec.soap.client.EnvioComprobantesProxy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
public class SriBO {

    private static final Logger logger = Logger.getLogger(SriBO.class);

    @Autowired
    private FirmadorBO firmadorBO;
    @Autowired
    private RespuestaComprobanteMapper respuestaComprobanteMapper;
    @Autowired
    private RespuestaSolicitudMapper respuestaSolicitudMapper;
    @Autowired
    private FacturaMapper facturaMapper;
    @Autowired
    private RetencionMapper retencionMapper;
    @Autowired
    private GeneradorBO generadorBO;

    public RespuestaSolicitudDTO enviarComprobante(GenericComprobanteRequestDTO comprobanteDTO, String wsdlRecepcion) throws IOException, JAXBException, VeronicaException {
        // Actividad 1.- Crear archivo temporal para el xml
        String rutaArchivoXML = UUID.randomUUID().toString();
        File temp = File.createTempFile(rutaArchivoXML, ".xml");
        rutaArchivoXML = temp.getAbsolutePath();
        String claveAcceso = "";
        // Actividad 2.- Ejecutar Marshalling
        if(comprobanteDTO.getComprobanteAsObj() instanceof FacturaDTO) {
            Factura factura = facturaMapper.toModel((FacturaDTO) comprobanteDTO.getComprobanteAsObj());
            claveAcceso = factura.getInfoTributaria().getClaveAcceso();
            JaxbUtils.marshall(factura, rutaArchivoXML);
        } else if(comprobanteDTO.getComprobanteAsObj() instanceof RetencionDTO) {
            ComprobanteRetencion retencion = retencionMapper.toModel((RetencionDTO) comprobanteDTO.getComprobanteAsObj());
            claveAcceso = retencion.getInfoTributaria().getClaveAcceso();
            JaxbUtils.marshall(retencion, rutaArchivoXML);
        }
        // Actividad 3.- Firmar el archivo
        byte[] xml = firmadorBO.firnarComprobanteElectronico(FileUtils.convertirArchivoAByteArray(temp),
                comprobanteDTO.getRutaArchivoPkcs12(), comprobanteDTO.getClaveArchivopkcs12());
        //Actividad 4.- Llamar al WS del SRI
        EnvioComprobantesProxy proxy = new EnvioComprobantesProxy(wsdlRecepcion);
        //Actividad 5.- Procesar respuesta del SRI
        RespuestaSolicitudDTO respuestaSolicitudDTO = respuestaSolicitudMapper.toModel(proxy.enviarComprobante(xml));
        respuestaSolicitudDTO.setClaveAcceso(claveAcceso);
        if (!temp.delete()) {
            throw new VeronicaException("No se pudo eliminar los archivos temporales.");
        }
        return respuestaSolicitudDTO;
    }

    public RespuestaComprobanteDTO autorizarComprobante(String claveAcceso, String wsdlAutorizacion) throws MalformedURLException {
        AutorizacionComprobanteProxy proxy = new AutorizacionComprobanteProxy(wsdlAutorizacion);
        RespuestaComprobanteDTO respuestaComprobanteDTO = respuestaComprobanteMapper.toModel(proxy.autorizacionIndividual(claveAcceso));
        return respuestaComprobanteDTO;
    }

}
