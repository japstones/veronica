package com.rolandopalermo.facturacion.ec.web.controller;

import com.rolandopalermo.facturacion.ec.bo.SriBO;
import com.rolandopalermo.facturacion.ec.common.exception.InternalServerException;
import com.rolandopalermo.facturacion.ec.common.exception.ResourceNotFoundException;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.dto.rest.ClaveAccesoDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.FacturaRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.GuiaRemisionRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RespuestaComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RespuestaSolicitudDTO;
import com.rolandopalermo.facturacion.ec.dto.rest.RetencionRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/api/v1/sri")
@Api(description = "Permite enviar o autorizar un comprobante electrónico.")
public class SriController {

	private static final Logger logger = Logger.getLogger(SriController.class);
	@Autowired
	private SriBO sriBO;
	@Value("${sri.wsdl.recepcion}")
	private String wsdlRecepcion;
	@Value("${sri.wsdl.autorizacion}")
	private String wsdlAutorizacion;

	@ApiOperation(value = "Envía una factura electrónica a validar al SRI")
	@PostMapping(value = "/enviar/factura", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaSolicitudDTO> enviarFactura(
			@Valid @ApiParam(value = "Comprobante electrónico", required = true) @RequestBody FacturaRequestDTO request) {
		if (!new File(request.getRutaArchivoPkcs12()).exists()) {
			throw new ResourceNotFoundException("No se pudo encontrar el certificado de firma digital.");
		}
		try {
			return new ResponseEntity<RespuestaSolicitudDTO>(sriBO.enviarComprobante(request, wsdlRecepcion),
					HttpStatus.OK);
		} catch (IOException | JAXBException | VeronicaException e) {
			logger.error("enviarFactura", e);
			throw new InternalServerException(e.getMessage());
		}
	}

	@ApiOperation(value = "Envía un comprobante de retención a validar al SRI")
	@PostMapping(value = "/enviar/retencion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaSolicitudDTO> enviarRetencion(
			@Valid @ApiParam(value = "Comprobante electrónico", required = true) @RequestBody RetencionRequestDTO request) {
		if (!new File(request.getRutaArchivoPkcs12()).exists()) {
			throw new ResourceNotFoundException("No se pudo encontrar el certificado de firma digital.");
		}
		RespuestaSolicitudDTO respuestaSolicitudDTO = new RespuestaSolicitudDTO();
		try {
			respuestaSolicitudDTO = sriBO.enviarComprobante(request, wsdlRecepcion);
		} catch (IOException | JAXBException | VeronicaException e) {
			logger.error("enviarRetencion", e);
			throw new InternalServerException(e.getMessage());
		}
		return new ResponseEntity<RespuestaSolicitudDTO>(respuestaSolicitudDTO, HttpStatus.OK);
	}
	@ApiOperation(value = "Envía un comprobante de Guia de remision a validar al SRI")
	@PostMapping(value = "/enviar/guia-remision", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaSolicitudDTO> enviarGuiaRemision(
			 @ApiParam(value = "Comprobante electrónico", required = true) @RequestBody GuiaRemisionRequestDTO request) {
		if (!new File(request.getRutaArchivoPkcs12()).exists()) {
			throw new ResourceNotFoundException("No se pudo encontrar el certificado de firma digital.");
		}
		RespuestaSolicitudDTO respuestaSolicitudDTO = new RespuestaSolicitudDTO();
		try {
			respuestaSolicitudDTO = sriBO.enviarComprobante(request, wsdlRecepcion);
		} catch (IOException | JAXBException | VeronicaException e) {
			logger.error("enviarRetencion", e);
			throw new InternalServerException(e.getMessage());
		}
		return new ResponseEntity<RespuestaSolicitudDTO>(respuestaSolicitudDTO, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Autoriza un comprobante electrónico")
	@PostMapping(value = "/autorizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaComprobanteDTO> autorizarComprobante(
			@Valid @ApiParam(value = "Clave de acceso del comprobante electrónico", required = true) @RequestBody ClaveAccesoDTO request) {
		RespuestaComprobanteDTO respuestaComprobanteDTO = new RespuestaComprobanteDTO();
		try {
			respuestaComprobanteDTO = sriBO.autorizarComprobante(request.getClaveAcceso(), wsdlAutorizacion);
		} catch (MalformedURLException e) {
			logger.error("autorizarComprobante", e);
			throw new InternalServerException(e.getMessage());
		}
		return new ResponseEntity<RespuestaComprobanteDTO>(respuestaComprobanteDTO, HttpStatus.OK);
	}

}
