package com.rolandopalermo.facturacion.ec.common.util;

import java.util.HashMap;

public class Constantes {

	/**
	 * Utility classes should not have a public constructor.
	 */
	private Constantes() {
	}

	public static final String API_DOC_ANEXO_1 = "Ver ficha técnica - Anexo 1";

	@SuppressWarnings("serial")
	public static final HashMap<String, String> FORMAS_PAGO_MAP = new HashMap<String, String>() {
		{
			put("01", "SIN UTILIZACION DEL SISTEMA FINANCIERO");
			put("15", "COMPENSACIÓN DE DEUDAS");
			put("16", "TARJETA DE DÉBITO");
			put("17", "DINERO ELECTRÓNICO");
			put("18", "TARJETA PREPAGO");
			put("19", "TARJETA DE CRÉDITO");
			put("20", "OTROS CON UTILIZACION DEL SISTEMA FINANCIERO");
			put("21", "ENDOSO DE TÍTULOS");
		}
	};

}
