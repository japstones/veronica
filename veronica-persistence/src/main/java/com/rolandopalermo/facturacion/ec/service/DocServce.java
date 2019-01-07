package com.rolandopalermo.facturacion.ec.service;

import java.util.List;

import com.rolandopalermo.facturacion.ec.entity.Doc;

public interface DocServce {
	public void addBill(Doc bill);

	public List<Doc> getBillStatusFalse();

	public void updateBillXml(Doc bill);
}
