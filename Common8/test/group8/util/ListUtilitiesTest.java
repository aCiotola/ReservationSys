package group8.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import dw317.lib.Name;

/** 
 * 
 * @author Alessandro Ciotola
 * @version 28/10/2016
 * @since 1.8
 *
 */
public class ListUtilitiesTest 
{
	public static Charset charEncoding = StandardCharsets.UTF_8;
	
	public static void main(String[] args)
	{
		testMerge();
		testSaveListToTextFile();
		testSelectionSort();
	}

	public static void testMerge() 
	{
		File dir = new File("datafiles/testMerge");
		dir.mkdirs();
		Name[] nameArray = { new Name("Mercedes", "Bmw"), new Name("Alessandro", "Ciotola"),
				new Name("Nissan", "Honda"), new Name("Hannah", "Ly"), new Name("Steven", "Nguyen"), };

		Name[] aNameArray = { new Name("Uncle", "Bens"), new Name("Sentra", "Civic"), new Name("Hannah", "Ly"),
				new Name("Alessandro", "Motola"), };

		System.out.println("\nTesting the Merge method");
		testMerge("Case 1 - Valid Data", nameArray, aNameArray);
		testMerge("Case 2 - Duplicates", nameArray, nameArray);
	}

	@SuppressWarnings("rawtypes")
	public static void testMerge(String testCase, Name[] list1, Name[] list2)
	{
		System.out.println("\t" + testCase);
		try {
			Comparable[] mergedList = ListUtilities.merge(list1, list2, "datafiles/test/mergeTestDup.txt");
			System.out.println("\tArray Created:\n");
			for (int i = 0; i < mergedList.length; i++)
				System.out.println("\t" + mergedList[i]);

		} catch (IOException io) {
			System.out.println(io.getMessage());
			System.out.println();
		} catch (Exception e) {
			System.out.println("\tUnexpected Exception!!! " + e.getClass() + "\n" + e.getMessage());
			System.out.println();
		}
		System.out.println();
	}

	private static void testSaveListToTextFile() 
	{
		File dir = new File("datafiles/test");
		dir.mkdirs();
		Name[] nameArray = { new Name("Alessandro", "Ciotola"), new Name("Steven", "Nguyen"),
				new Name("Hannah", "Ly") };

		System.out.println("\nTesting the SaveListToTextFile method");
		testSaveListToTextFile("Case 1 - Valid", nameArray, "datafiles/test/list.txt", true);
		testSaveListToTextFile("Case 2 - Valid", nameArray, "datafiles/test/list2.txt");
		testSaveListToTextFile("Case 3 - Valid", nameArray, "datafiles/test/list3.txt", true, charEncoding);
	}

	private static void testSaveListToTextFile(String testCase, Object[] obj, String fileName) 
	{
		System.out.println("\t" + testCase);
		try {
			ListUtilities.saveListToTextFile(obj, fileName);
			System.out.println("\tThe list has been saved to a Text File!");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}
	}

	private static void testSaveListToTextFile(String testCase, Object[] obj, String fileName, boolean append)
	{
		System.out.println("\t" + testCase);
		try {
			ListUtilities.saveListToTextFile(obj, fileName, append);
			System.out.println("\tThe list has been saved to a Text File!");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}

	}

	private static void testSaveListToTextFile(String testCase, Object[] obj, String fileName, boolean append,
			Charset charEncoding) 
	{
		System.out.println("\t" + testCase);
		try {
			ListUtilities.saveListToTextFile(obj, fileName, append, charEncoding);
			System.out.println("\tThe list has been saved to a Text File!");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}

	}

	private static void testSelectionSort()
	{
		System.out.println("\n\nTesting the SelectionSort method.");

		String[] list = { "bbc", "ccb", "bac", "dca", "cab", "dba" };
		testSelectionSortStrings("\nCase 1 - Testing Strings in selection sort.", list, true);

		Name nameList[] = { new Name("Alessandro", "Ciotola"), new Name("Steven", "Nguyen"),
				new Name("Hannah", "Ly"), };
		testSelectionSortNames("\nCase 2 - Testing names in selection sort", nameList, true);

		testSelectionSortStrings("Case 3 - Testing nulls in selection sort", null, false);

		String[] notFullList = new String[5];
		notFullList[0] = "One";
		notFullList[1] = "Two";
		notFullList[2] = "Three";
		testSelectionSortStrings("Case 4 - Testing not full to capacity arrays in selection sort", notFullList, false);
	}

	private static void testSelectionSortStrings(String testCase, String[] str, boolean expectValid) 
	{
		System.out.println("   " + testCase);
		try {
			ListUtilities.sort(str);
			for (String display : str)
				System.out.println(display);

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (NullPointerException npe) {
			System.out.print("\t" + npe.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testSelectionSortNames(String testCase, Name[] names, boolean expectValid) 
	{
		System.out.println("   " + testCase);
		try {
			ListUtilities.sort(names);
			for (Name display : names)
				System.out.println(display);

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}
}
