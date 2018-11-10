package com.rolandopalermo.facturacion.ec.web.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolandopalermo.facturacion.ec.bo.SriBO;
import com.rolandopalermo.facturacion.ec.common.exception.BadRequestException;
import com.rolandopalermo.facturacion.ec.common.exception.InternalServerException;
import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.common.exception.ResourceNotFoundException;
import com.rolandopalermo.facturacion.ec.common.util.FechasUtil;
import com.rolandopalermo.facturacion.ec.ride.RIDEGenerator;

import autorizacion.ws.sri.gob.ec.Autorizacion;
import autorizacion.ws.sri.gob.ec.RespuestaComprobante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(value = "/api/v1/ride")
@Api(description = "Genera la RIDE de un comprobante electrónico como XML.")
public class RideController {

	private static final Logger logger = Logger.getLogger(RideController.class);

	@Autowired
	private SriBO sriBO;

	@Value("${sri.wsdl.autorizacion}")
	private String wsdlAutorizacion;

	@ApiOperation(value = "Genera la RIDE de una factura en formato pdf")
	@GetMapping(value = "/factura/{claveAcceso}", produces = "application/pdf")
	public ResponseEntity<InputStreamResource> generarRideFactura(@ApiParam(value = "Clave de acceso del comprobante electrónico", required = true) @PathVariable("claveAcceso") String claveAcceso) {
		try {
			RespuestaComprobante respuesta = sriBO.autorizarComprobante(claveAcceso, wsdlAutorizacion);
			Optional<Autorizacion> autorizacion = respuesta.getAutorizaciones().getAutorizacion().stream().findFirst();
			if (autorizacion.isPresent()) {
				File comprobante = File.createTempFile(autorizacion.get().getNumeroAutorizacion(), ".xml");
				Path path = Paths.get(comprobante.getAbsolutePath());
				try (BufferedWriter writer = Files.newBufferedWriter(path)) {
					writer.write(autorizacion.get().getComprobante());
				}
				JasperPrint jasperPrint = RIDEGenerator.getInvoiceRide(autorizacion.get().getNumeroAutorizacion(),
						FechasUtil.convertirGreggorianToDDMMYYYY(autorizacion.get().getFechaAutorizacion().toString()), comprobante.getAbsolutePath());
				byte[] pdfContent = JasperExportManager.exportReportToPdf(jasperPrint);
				HttpHeaders headers = new HttpHeaders();
				headers.add("content-disposition", "inline; filename=" + respuesta.getClaveAccesoConsultada() + ".pdf");
			    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			    headers.add("Pragma", "no-cache");
			    headers.add("Expires", "0");
			    if (!comprobante.delete()) {
					new Exception("No se puede eliminar  el archivo temporal");
				}
				return ResponseEntity
			            .ok()
			            .headers(headers)
			            .contentLength(pdfContent.length)
			            .contentType(MediaType.parseMediaType("application/octet-stream"))
			            .body(new InputStreamResource(new ByteArrayInputStream(pdfContent)));
			} else {
				throw new ResourceNotFoundException(String.format("No se pudo encontrar el comprobante de autorización [%S].", claveAcceso));
			}
		} catch (NegocioException e) {
			logger.error("generarRideFactura", e);
			throw new BadRequestException(e.getMessage());
		} catch (ResourceNotFoundException e) {
			logger.error("generarRideFactura", e);
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			logger.error("generarRideFactura", e);
			throw new InternalServerException(e.getMessage());
		}
	}

}