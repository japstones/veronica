package com.rolandopalermo.facturacion.ec.web.controller;

import com.rolandopalermo.facturacion.ec.bo.FirmadorBO;
import com.rolandopalermo.facturacion.ec.common.exception.InternalServerException;
import com.rolandopalermo.facturacion.ec.common.exception.ResourceNotFoundException;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.dto.rest.ByteArrayResponseDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.FacturaRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RetencionRequestDTO;
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

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/firmar")
@Api(description = "Permite firmar un comprobante electrónico.")
public class FirmaController {

    private static final Logger logger = Logger.getLogger(FirmaController.class);

    @Autowired
    private FirmadorBO firmadorBO;

    @ApiOperation(value = "Firma un comprobante electrónico")
    @PostMapping(value = "/factura", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResponseDTO> firmarFactura(
            @ApiParam(value = "Comprobante electrónico codificado como base64", required = true) @RequestBody FacturaRequestDTO request) {
        byte[] content = new byte[0];
        try {
            if (!new File(request.getRutaArchivoPkcs12()).exists()) {
                throw new ResourceNotFoundException("No se pudo encontrar el certificado de firma digital.");
            }
            content = firmadorBO.firmarFactura(request);
        } catch (VeronicaException | JAXBException | IOException e) {
            logger.error("firmarFactura", e);
            throw new InternalServerException(e.getMessage());
        }
        return new ResponseEntity<ByteArrayResponseDTO>(new ByteArrayResponseDTO(content), HttpStatus.OK);
    }

    @ApiOperation(value = "Firma un comprobante de retención")
    @PostMapping(value = "/retencion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResponseDTO> firmarRetencion(
            @ApiParam(value = "Comprobante electrónico codificado como base64", required = true) @RequestBody RetencionRequestDTO request) {
        byte[] content = new byte[0];
        try {
            if (!new File(request.getRutaArchivoPkcs12()).exists()) {
                throw new ResourceNotFoundException("No se pudo encontrar el certificado de firma digital.");
            }
            content = firmadorBO.firmarRetencion(request);
        } catch (VeronicaException | JAXBException | IOException e) {
            logger.error("firmarRetencion", e);
            throw new InternalServerException(e.getMessage());
        }
        return new ResponseEntity<ByteArrayResponseDTO>(new ByteArrayResponseDTO(content), HttpStatus.OK);
    }

}