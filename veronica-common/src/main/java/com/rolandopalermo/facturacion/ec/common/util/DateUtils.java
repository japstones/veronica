package com.rolandopalermo.facturacion.ec.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * Utility classes should not have a public constructor.
     */
    private DateUtils() {
    }

    public static String convertirGreggorianToDDMMYYYY(String fecha) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        date = sdf.parse(fecha);
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        fecha = formatoFecha.format(date);
        return fecha;
    }

    public static Date getFechaFromStringddMMyyyy(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(fecha);
        return date;
    }

}