package com.rolandopalermo.facturacion.ec.bo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import com.rolandopalermo.facturacion.ec.mapper.factura.FacturaMapper;
import com.rolandopalermo.facturacion.ec.mapper.guiaremision.GuiaRemisionMapper;
import com.rolandopalermo.facturacion.ec.mapper.retencion.RetencionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.util.FileUtils;
import com.rolandopalermo.facturacion.ec.common.util.JaxbUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.GenericComprobanteRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RespuestaComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RespuestaSolicitudDTO;
import com.rolandopalermo.facturacion.ec.mapper.RespuestaComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.RespuestaSolicitudMapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.soap.client.AutorizacionComprobanteProxy;
import com.rolandopalermo.facturacion.ec.soap.client.EnvioComprobantesProxy;

@Service
public class SriBO {

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
    private GuiaRemisionMapper guiaRemisionMapper;

    public RespuestaSolicitudDTO enviarComprobante(GenericComprobanteRequestDTO comprobanteDTO, String wsdlRecepcion) throws IOException, JAXBException, VeronicaException {
        // Actividad 1.- Crear archivo temporal para el xml
        String rutaArchivoXML = UUID.randomUUID().toString();
        File temp = File.createTempFile(rutaArchivoXML, ".xml");
        rutaArchivoXML = temp.getAbsolutePath();
        String claveAcceso = "";
        // Actividad 2.- Ejecutar Marshalling
        if(comprobanteDTO.getComprobanteAsObj() instanceof FacturaDTO) {
            Factura factura = facturaMapper.convert((FacturaDTO) comprobanteDTO.getComprobanteAsObj());
            claveAcceso = factura.getInfoTributaria().getClaveAcceso();
            JaxbUtils.marshall(factura, rutaArchivoXML);
        } else if(comprobanteDTO.getComprobanteAsObj() instanceof RetencionDTO) {
            ComprobanteRetencion retencion = retencionMapper.convert((RetencionDTO) comprobanteDTO.getComprobanteAsObj());
            claveAcceso = retencion.getInfoTributaria().getClaveAcceso();
            JaxbUtils.marshall(retencion, rutaArchivoXML);
            
            
        }else if(comprobanteDTO.getComprobanteAsObj() instanceof GuiaRemisionDTO) {
            GuiaRemision guiaRemision = guiaRemisionMapper.convert((GuiaRemisionDTO) comprobanteDTO.getComprobanteAsObj());
            claveAcceso = guiaRemision.getInfoTributaria().getClaveAcceso();
            JaxbUtils.marshall(guiaRemision, rutaArchivoXML);
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
