package com.rolandopalermo.facturacion.ec.common.exception;

public class VeronicaException extends Exception {

	private static final long serialVersionUID = 4956405604792374198L;

	public VeronicaException(String message) {
		super(message);
	}

	public VeronicaException(Exception ex) {
		super(ex);
	}

}