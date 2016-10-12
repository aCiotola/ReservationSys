/**
 * 
 */
package group8.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Alessandro Ciotola
 *
 */
public class ListUtilities {
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	private ListUtilities() {
	}

	/*
	 * Sorts a list of objects in ascending natural order using selection sort.
	 * 
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 * 
	 *
	 * @param list A list of objects. Assumes that the list's capacity is equal
	 * to the list's size.
	 * 
	 * @throws IllegalArgumentException if the parameter is not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the list is null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {
		if (list == null)
			throw new NullPointerException("The list must not be null");

		int size = list.length - 1;
		if (list[size] == null)
			throw new IllegalArgumentException("Must be full to capacity");

		Comparable temp;
		int index;

		for (int i = 0; i < list.length - 1; i++) {
			index = i;
			for (int j = i + 1; j < list.length; j++) {
				if (list[j].compareTo(list[index]) < 0)
					index = j;
			}
			temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}
	}

	public static void saveListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter outputFile = null;

		try {
			FileOutputStream f = new FileOutputStream(filename, append);
			OutputStreamWriter out = new OutputStreamWriter(f, characterEncoding);
			outputFile = new PrintWriter(new BufferedWriter(out));

			for (Object obj : objects)
				if (obj != null)
					outputFile.println(obj);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error saving list! Unable to access the device " + filename);
		}
		finally
		{
			if (outputFile != null)
				outputFile.close();
		}

	}

}
