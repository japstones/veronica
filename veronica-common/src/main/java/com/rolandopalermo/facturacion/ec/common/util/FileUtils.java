package com.rolandopalermo.facturacion.ec.common.util;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtils {

	/**
	 * Utility classes should not have a public constructor.
	 */
	private FileUtils() {
	}

	public static byte[] convertirObjAXML(Object doc) throws VeronicaException, IOException, JAXBException {
		File temp = File.createTempFile(UUID.randomUUID().toString(), ".xml");
		String nombreArchivo = temp.getAbsolutePath();
		JaxbUtils.marshall(doc, nombreArchivo);
		Path path = Paths.get(nombreArchivo);
		byte[] data = Files.readAllBytes(path);
		if (!temp.delete()) {
			throw new VeronicaException("No se pudo eliminar el archivo temporal.");
		}
		return data;
	}

	public static byte[] convertirArchivoAByteArray(File file) throws IOException {
		byte[] buffer = new byte[(int) file.length()];
		InputStream ios = null;
		try {
			ios = new FileInputStream(file);
			if (ios.read(buffer) == -1) {
				throw new IOException("EOF reached while trying to read the whole file");
			}
		} catch (Exception ex) {
			buffer = null;
		} finally {
			try {
				if (ios != null) {
					ios.close();
				}
			} catch (IOException e) {
			}
		}
		return buffer;
	}

}
