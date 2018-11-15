package com.rolandopalermo.facturacion.ec.ride;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.rolandopalermo.facturacion.ec.common.util.Constantes.FORMAS_PAGO_MAP;

public class RIDEGenerator {

	public static JasperPrint convertirFacturaARide(String numeroAutorizacion, String fechaAutorizacion, String xmlFilePath) throws JRException {
		InputStream employeeReportStream = RIDEGenerator.class.getResourceAsStream("/com/rolandopalermo/facturacion/ec/ride/RIDE_factura.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);
		Map parameters = new HashMap();
		parameters.put("numeroAutorizacion", numeroAutorizacion);
		parameters.put("fechaAutorizacion", fechaAutorizacion);
		parameters.put("hmapFormasPago", FORMAS_PAGO_MAP);
		JRXmlDataSource xmlDataSource = new JRXmlDataSource(xmlFilePath, "/factura");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, xmlDataSource);
		return jasperPrint;
	}

}