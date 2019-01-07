package com.rolandopalermo.facturacion.ec.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolandopalermo.facturacion.ec.dao.DocDaoImp;
import com.rolandopalermo.facturacion.ec.entity.Doc;

@Service
@Transactional
public class DocServiceImp implements DocServce {
	@Autowired
	private DocDaoImp billDaoImp;

	@Override
	public void addBill(Doc bill) {
		billDaoImp.addBill(bill);

	}

	@Override
	public List<Doc> getBillStatusFalse() {
		// TODO Auto-generated method stub
		return billDaoImp.getBillStatusFalse();
	}

	@Override
	public void updateBillXml(Doc bill) {
		// TODO Auto-generated method stub
		billDaoImp.updateBillXml(bill);

	}

}
