package com.rolandopalermo.facturacion.ec.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doc")
public class Doc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_doc")
	private int idBill;

	@Column(name = "key_doc")
	private String keyBill;

	@Column(name = "date_doc")
	private String dateIssueBill;

	@Column(name = "xml_doc")
	private String xmlBill;

	@Column(name = "status_doc")
	private boolean statusBill;

	@Column(name = "type_doc")
	private String typeBill;

	public Doc(String keyBill, String dateIssueBill, String xmlBill, boolean statusBill, String typeBill) {
		super();

		this.keyBill = keyBill;
		this.dateIssueBill = dateIssueBill;
		this.xmlBill = xmlBill;
		this.statusBill = statusBill;
		this.typeBill = typeBill;
	}

	public Doc(int idBill, String keyBill, String dateIssueBill, String xmlBill, boolean statusBill, String typeBill) {
		super();
		this.idBill = idBill;
		this.keyBill = keyBill;
		this.dateIssueBill = dateIssueBill;
		this.xmlBill = xmlBill;
		this.statusBill = statusBill;
		this.typeBill = typeBill;
	}

	public Doc() {
		super();
	}

}
