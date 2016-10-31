package group8.util;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

/** 
 * A ListUtilities class that contains different utility methods for
 * files.
 * 
 * @author Alessandro Ciotola
 * @version 28/10/2016
 * @since 1.8
 *
 */
public class ListUtilities 
{
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	private ListUtilities()
	{}

	/**
	 * A method that takes in a list & a key that will be searched in the list
	 * 
	 * @param list The list that will be searched
	 * @param key The key that needs to be found in the list
	 * 
	 * @return The position of the found/non found object in the list 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int binarySearch(Comparable[] list, Comparable key)
	{
		int low = 0;
		int mid = 0;
		int high = list.length - 1;
		int result = 0;

		while(high >= low)
		{
			mid = (high + low)/2;
			result = list[mid].compareTo(key);

			if (result > 0)
				high = mid - 1;
			else if(result < 0)
				low = mid + 1;
			else
				return mid;
		}
		return -low + 1;
	}
	
	/**
	 * A method that takes a duplicate from the merge method and saves the duplicate to a file
	 * 
	 * @param list1Stuff The duplicate from the first list
	 * @param list2Stuff The duplicate from the second list
	 * @param duplicateFileName The name of the file that will keep the duplicates
	 * 
	 * @throws IOException If file/dir is not found
	 */
	@SuppressWarnings("rawtypes")
	private static void duplicateObjectsInList(Comparable list1Stuff,
			Comparable list2Stuff, String duplicateFileName) throws IOException
	{
		//2 objects are equal (so size 2 array)
		String[] string = new String[2];
		string[0] = list1Stuff.toString() + " (Merged) ";
		string[1] = list2Stuff.toString();
		saveListToTextFile(string, duplicateFileName, true);	
	}

	/**
	 * A method that will make the merged list full to capacity if there were any duplicates in the lists. 
	 * 
	 * @param list The list that had a duplicate.
	 * @param list3 The list which will be the merged list.
	 * @param posList The position of the list that had a duplicate
	 * @param posList3 The position of the list which will be the merged list
	 * @param duplicateFileName The name of the file that will contain the duplicates.
	 * 
	 * @return The position of The list which will be the merged list
	 * 
	 * @throws IOException if file/dir is not found
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static int fullToCapacity (Comparable[] list, Comparable[] list3, int posList, int posList3, String duplicateFileName) 
			throws IOException
	{
		if(list3[posList3].compareTo(list[posList]) == 0)
		{
			if(list3[posList3].hashCode() == list[posList].hashCode())
				duplicateObjectsInList(list3[posList], list[posList],
				duplicateFileName);
			else
			{
				posList3++;
				list3[posList3] = list[posList];
			}
			posList++;
		}
		posList3++;		
		while (posList < list.length)
		{
			list3[posList3] = list[posList];
			posList++;
			posList3++;
		}
		return posList3;
	}	
	
	/**
	 * Efficiently merges two sorted lists of objects in ascending natural
	 * order. If the duplicate objects are in both lists, the object from list1
	 * is merged into the resulting list, and both objects are written to the
	 * duplicate file.
	 *
	 * Precondition: Assumes that the lists are not null and that both lists
	 * contain objects that can be compared to each other and are filled to
	 * capacity.
	 *
	 *
	 * @param list1 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 *
	 * @param list2 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 *
	 * @param duplicateFileName The name of the file in datafiles\duplicates to
	 * which duplicate pairs will be appended.
	 *
	 * @throws IllegalArgumentException if either parameter is not full to
	 * capacity.
	 *
	 * @throws NullPointerException if either list is null.
	 * 
	 * @return The merged list.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName)
			throws IOException
	{
		if(list1.length == 0 && list2.length != 0)
			 return list2;
		 if(list2.length == 0 && list1.length != 0)
			 return list1;
		
		Comparable[] list3 = (Comparable[]) Array.newInstance(
				list1.getClass().getComponentType(), list1.length + list2.length);

		int posList1 = 0;
		int posList2 = 0;
		int posList3 = 0;
		
		while(posList1 < list1.length && posList2 < list2.length) 
		{
			Comparable list1Stuff = list1[posList1];
			Comparable list2Stuff = list2[posList2];
			
			if (list1Stuff.compareTo(list2Stuff) < 0)
			{
				list3[posList3] = list1Stuff;
				posList1++;
			}
			else if(list2Stuff.compareTo(list1Stuff) < 0) 
			{
				list3[posList3] = list2Stuff;
				posList2++;
			}
			else
			{
				//2 objects are equal if their hashcodes are equal.
				if (list1Stuff.hashCode() == list2Stuff.hashCode())
				{
					list3[posList3] = list1Stuff;
					duplicateObjectsInList(list1Stuff, list2Stuff,
							 duplicateFileName);
				}
				else
				{
					list3[posList3] = list1Stuff;
					posList3++;
					list3[posList3] = list2Stuff;
				}
				posList1++;
				posList2++;
			}
			
			posList3++;			
		}
		if (posList1 < list1.length)
		{
			posList3 = fullToCapacity(list1, list3, posList1, posList3 - 1, duplicateFileName);
		}
		else if (posList2 < list2.length)
		{
			posList3 = fullToCapacity(list2, list3, posList2, posList3 - 1, duplicateFileName);
		}
		
		return Arrays.copyOf(list3, posList3);
	}
	
	/**
	 * A method which takes a list and a filename. Then saves the list to a text file with the given name
	 * 
	 * @param objects The list that will be saved to a file
	 * @param filename The name of the saved text file
	 * 
	 * @throws FileNotFoundException If the file/dir is not found
	 * @throws UnsupportedEncodingException If the encoding is invalid
	 */
	public static void saveListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	/**
	 * A method which takes in a list,the name of the file and a boolean, then saves the list to the given
	 * file name.
	 * 
	 * @param objects The list that will be saved to a file
	 * @param filename The name of the saved text file
	 * @param append If true, the information will be added to the end of the file.
	 * 
	 * @throws FileNotFoundException If the file/dir is not found
	 * @throws UnsupportedEncodingException If the encoding is invalid
	 * 
	 */
	public static void saveListToTextFile(Object[] objects, String filename, boolean append)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	/**
	 * A method which takes in a list,the name of the file, a boolean and an encoding, then saves the list to the given
	 * file name.
	 * 
	 * @param objects The list that will be saved to a file
	 * @param filename The name of the saved text file
	 * @param append If true, the information will be added to the end of the file.
	 * @param characterEncoding The type of character encoding
	 * 
	 * @throws FileNotFoundException If the file/dir is not found
	 * @throws UnsupportedEncodingException If the encoding is invalid
	 * 
	 */
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
		} finally {
			if (outputFile != null)
				outputFile.close();
		}
	}

	
	/**
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
	
	/**
	 * Sorts a list of objects in the given order.
	 * 
	 * Precondition: 	Assumes that the list is not null and that the 
	 *	list's capacity is equal to the list's size.
	 * 
	 *
	 * @param list 	A list of objects. Assumes that the
	 *             	list's capacity is equal to the list's size. 
	 *             
	 * @param sortOrder A Comparator object that defines the
	 *		  sort order
	 * 
	 * @throws  IllegalArgumentException if the parameter is     
	 *			not full to capacity.
	 *
	 * @throws	NullPointerException if the list or sortOrder 
	 * 		are null.
	 */
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static void sort(Comparable[] list, Comparator sortOrder)
			 throws IllegalArgumentException, NullPointerException
	 {
		 Arrays.sort(list, sortOrder);
	 }

}
