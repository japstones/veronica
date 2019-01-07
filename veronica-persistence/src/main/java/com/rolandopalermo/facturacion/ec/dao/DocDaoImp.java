package com.rolandopalermo.facturacion.ec.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rolandopalermo.facturacion.ec.entity.Doc;
import com.rolandopalermo.facturacion.ec.init.WebAppConfig;

@Repository
public class DocDaoImp implements DocDao {
	WebAppConfig web = new WebAppConfig();
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {

		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addBill(Doc bill) {
		Query query = getCurrentSession()
				.createSQLQuery("INSERT INTO public.doc(date_doc, key_doc, type_doc, status_doc,  xml_doc) VALUES "
						+ "('" + bill.getDateIssueBill() + "', '" + bill.getKeyBill() + "','" + bill.getTypeBill()
						+ "', " + bill.isStatusBill() + ", " + " XMLPARSE (DOCUMENT '" + bill.getXmlBill() + "'))");

		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	public List<Doc> getBillStatusFalse() {
		// List<Doc> doc = new ArrayList<>();
		// List<Object> result = (List<Object>) getCurrentSession().createQuery( "FROM
		// Doc where statusBill=false" ).list();
		/*
		 * Iterator itr = result.iterator(); while(itr.hasNext()){ Object[] obj =
		 * (Object[]) itr.next(); //now you have one array of Object for each row String
		 * dateIssueBill = String.valueOf(obj[0]);
		 * 
		 * String keyBill = String.valueOf(obj[1]); String typeBill =
		 * String.valueOf(obj[2]);
		 * 
		 * Integer idBill = Integer.parseInt(String.valueOf(obj[3])); String xmlBill =
		 * String.valueOf(obj[4]); boolean statusBill =
		 * Boolean.valueOf(String.valueOf(obj[5])); doc.add(new Doc(idBill, keyBill,
		 * dateIssueBill, xmlBill, statusBill, typeBill)); }
		 */

		return getCurrentSession().createQuery("FROM Doc where status_doc=false").list();
	}

	@Override
	public void updateBillXml(Doc bill) {
		Query query = getCurrentSession()
				.createSQLQuery("UPDATE doc SET  " + "xml_doc= XMLPARSE (DOCUMENT '" + bill.getXmlBill()
						+ "'),status_doc =" + bill.isStatusBill() + " " + "WHERE cod_doc= " + bill.getIdBill() + "");
		query.executeUpdate();

	}

}
