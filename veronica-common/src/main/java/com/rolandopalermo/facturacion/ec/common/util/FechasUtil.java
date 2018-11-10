package com.rolandopalermo.facturacion.ec.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechasUtil {

	public static String convertirGreggorianToDDMMYYYY(String fecha) throws Exception {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			date = sdf.parse(fecha);
			DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fecha = formatoFecha.format(date);
			return fecha;
		} catch (ParseException e) {
			throw new Exception(String.format("La fecha {%s} es inválida", fecha));
		}
	}

}