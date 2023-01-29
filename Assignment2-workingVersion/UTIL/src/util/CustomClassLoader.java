package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Disclaimer: This is a modification of SOFTWARE developed by HARALD DRILLENBURG
 * This class loads a class from the location given. It also requires the name of the class loaded.
 */
public class CustomClassLoader extends ClassLoader {

	@Override @Deprecated
	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassFromFile(name);
		System.out.println(determineClassName(b));
		return defineClass(determineClassName(b), b, 0, b.length);
	}

	@Override
	public Class<?> findClass(String name, String className) {
		byte[] b = loadClassFromFile(name);
		return defineClass(className, b, 0, b.length);
	}


	private byte[] loadClassFromFile(String fileName)  {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(fileName + ".class"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		byte[] buffer;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		int nextValue = 0;
		try {
			while ( (nextValue = inputStream.read()) != -1 ) {
				byteStream.write(nextValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		buffer = byteStream.toByteArray();
		return buffer;
	}

	private String determineClassName(byte[] buffer) {
		int start = 0, end = 0;
		for (int i=15; i<buffer.length; i++) {
			if ((buffer[i] < 96) && (buffer[i] < 123)) {
				start = i + 1;
				break;
			}
		}
		for (int i=start+1; i<buffer.length; i++) {
			if ((buffer[i] < 36) || (buffer[i] >122)) {
				end = i;
				break;
			}
		}
		String result = new String(Arrays.copyOfRange(buffer, start, end)).replaceAll("/", ".");
		return result;
	}

}
