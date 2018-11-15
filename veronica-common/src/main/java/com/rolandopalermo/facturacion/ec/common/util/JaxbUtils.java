package com.rolandopalermo.facturacion.ec.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;

/**
 *
 * @author Rolando
 */
public class JaxbUtils {

	/**
	 * Utility classes should not have a public constructor.
	 */
	private JaxbUtils() {
	}

	public static void marshall(Object comprobante, String rutaArchivo) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(new Class[] { comprobante.getClass() });
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.encoding", "UTF-8");
		marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
		OutputStream fos = new FileOutputStream(rutaArchivo);
		OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
		marshaller.marshal(comprobante, out);
		fos.close();
		out.close();
	}

	public static <T> T unmarshall(String string, Class<T> clase) throws JAXBException {
		JAXBContext jaxbContext = null;
		Unmarshaller unmarshaller = null;
		StringReader reader = null;
		jaxbContext = JAXBContext.newInstance(clase);
		unmarshaller = jaxbContext.createUnmarshaller();
		reader = new StringReader(string);
		@SuppressWarnings("unchecked")
		T comprobante = (T) unmarshaller.unmarshal(reader);
		return comprobante;
	}

	public static <T> T unmarshall(File file, Class<T> clase) throws JAXBException {
		JAXBContext jaxbContext = null;
		Unmarshaller unmarshaller = null;
		jaxbContext = JAXBContext.newInstance(clase);
		unmarshaller = jaxbContext.createUnmarshaller();
		@SuppressWarnings("unchecked")
		T comprobante = (T) unmarshaller.unmarshal(file);
		return comprobante;
	}

}
