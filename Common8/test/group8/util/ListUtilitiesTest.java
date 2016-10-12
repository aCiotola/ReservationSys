/**
 * 
 */
package group8.util;

import dw317.lib.Name;

/**
 * @author Alessandro Ciotola
 *
 */
public class ListUtilitiesTest 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		testSelectionSort();
	}

	private static void testSelectionSort() 
	{
		System.out.println("\nTesting the SelectionSort method.");
		System.out.println("\nTesting Strings in selection sort.");
		String[] list = {"bbc", "ccb", "bac", "dca", "cab", "dba"};
		ListUtilities.sort(list);
		for (String display : list)
			System.out.println(display);
		
		System.out.println("\nTesting names in selection sort");
		Name nameList[] = {
				new Name ("Seaim", "Khan"),
				new Name ("Naasir", "Jusaab"),
				new Name ("Shifat", "Khan"),
				new Name ("Alessandro", "Ciotola")
				};
		ListUtilities.sort(nameList);
		for (Name display : nameList)
			System.out.println(display);
	}
}
