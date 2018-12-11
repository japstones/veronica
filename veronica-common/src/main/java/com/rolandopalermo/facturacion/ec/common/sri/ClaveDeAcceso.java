package com.rolandopalermo.facturacion.ec.common.sri;

import lombok.Builder;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rolando
 */
@Builder
public class ClaveDeAcceso {

    private static final Logger logger = Logger.getLogger(ClaveDeAcceso.class);
    private final Date fechaEmision;
    private final String tipoComprobante;
    private final String ruc;
    private final String ambiente;
    private final String serie;
    private final String numeroComprobante;
    private final String codigoNumerico;
    private final String tipoEmision;

    public String generarClaveAcceso() {
        int verificador = 0;
        String ruc = this.ruc;
        if (ruc != null && ruc.length() < 13) {
            ruc = String.format("%013d", new Object[]{ruc});
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String fecha = dateFormat.format(fechaEmision);
        StringBuilder clave = new StringBuilder(fecha);
        clave.append(tipoComprobante);
        clave.append(ruc);
        clave.append(ambiente);
        clave.append(serie);
        clave.append(numeroComprobante);
        clave.append(codigoNumerico);
        clave.append(tipoEmision);
        verificador = generarDigitoModulo11(clave.toString());
        clave.append(Integer.valueOf(verificador));
        String claveGenerada = clave.toString();
        if (clave.toString().length() != 49) {
            claveGenerada = null;
        }
        return claveGenerada;
    }

    private int generarDigitoModulo11(String cadena) {
        int baseMultiplicador = 7;
        int aux[] = new int[cadena.length()];
        int multiplicador = 2;
        int total = 0;
        int verificador = 0;
        for (int i = aux.length - 1; i >= 0; i--) {
            aux[i] = Integer.parseInt((new StringBuilder()).append("").append(cadena.charAt(i)).toString());
            aux[i] = aux[i] * multiplicador;
            if (++multiplicador > baseMultiplicador) {
                multiplicador = 2;
            }
            total += aux[i];
        }

        if (total == 0 || total == 1) {
            verificador = 0;
        } else {
            verificador = 11 - total % 11 != 11 ? 11 - total % 11 : 0;
        }
        if (verificador == 10) {
            verificador = 1;
        }
        return verificador;
    }

}