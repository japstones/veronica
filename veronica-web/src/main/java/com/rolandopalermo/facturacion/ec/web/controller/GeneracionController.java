package com.rolandopalermo.facturacion.ec.web.controller;

import com.rolandopalermo.facturacion.ec.bo.GeneradorBO;
import com.rolandopalermo.facturacion.ec.common.exception.InternalServerException;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.RetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.ByteArrayResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;

import java.io.Console;
import java.io.IOException;

import static com.rolandopalermo.facturacion.ec.common.util.Constantes.API_DOC_ANEXO_1;

@RestController
@RequestMapping(value = "/api/v1/generar")
@Api(description = "Genera comprobante electrónico como XML.")
public class GeneracionController {

    private static final Logger logger = Logger.getLogger(GeneracionController.class);

    @Autowired
    private GeneradorBO generadorBO;

    @ApiOperation(value = "Genera una factura en formato XML")
    @PostMapping(value = "/factura", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResponseDTO> generarFactura(@Valid @ApiParam(value = API_DOC_ANEXO_1, required = true) @RequestBody FacturaDTO request) {
        byte[] content = new byte[0];
        try {
            content = generadorBO.generarXMLFactura(request);
            ByteArrayResponseDTO response = new ByteArrayResponseDTO(content);
        } catch (VeronicaException | IOException | JAXBException e) {
            logger.error("generarFactura", e);
            throw new InternalServerException(e.getMessage());
        }
        return new ResponseEntity<ByteArrayResponseDTO>(new ByteArrayResponseDTO(content), HttpStatus.OK);
    }

    @ApiOperation(value = "Genera un comprobante de retención en formato XML")
    @PostMapping(value = "/retencion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResponseDTO> generarRetencion(@Valid @ApiParam(value = API_DOC_ANEXO_1, required = true) @RequestBody RetencionDTO request) {
        byte[] content = new byte[0];
        try {
            content = generadorBO.generarXMLRetencion(request);
            ByteArrayResponseDTO response = new ByteArrayResponseDTO(content);
        } catch (VeronicaException | IOException | JAXBException e) {
            logger.error("generarRetencion", e);
            throw new InternalServerException(e.getMessage());
        }
        return new ResponseEntity<ByteArrayResponseDTO>(new ByteArrayResponseDTO(content), HttpStatus.OK);
    }

    
   
    @ApiOperation(value = "Genera una guia de remisión en formato XML")
    @PostMapping(value = "/guia-remision", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResponseDTO> generarGuiaRmision(@Valid @ApiParam(value = API_DOC_ANEXO_1, required = true) @RequestBody GuiaRemisionDTO request) {
        byte[] content = new byte[0];
        try {
            content = generadorBO.generarGuiaXMLRemison(request);
            ByteArrayResponseDTO response = new ByteArrayResponseDTO(content);
           
        } catch (VeronicaException | IOException | JAXBException e) {
            logger.error("generarRetencion", e);
            throw new InternalServerException(e.getMessage());
        }
        return new ResponseEntity<ByteArrayResponseDTO>(new ByteArrayResponseDTO(content), HttpStatus.OK);
    }

}