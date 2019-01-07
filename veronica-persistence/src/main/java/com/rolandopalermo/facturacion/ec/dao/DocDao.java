package com.rolandopalermo.facturacion.ec.dao;

import java.util.List;

import com.rolandopalermo.facturacion.ec.entity.Doc;

public interface DocDao {
	public void addBill(Doc bill);

	public List<Doc> getBillStatusFalse();

	public void updateBillXml(Doc bill);

}
