/**
 * 
 */
package dw317.lib;

import java.util.Optional;

/**
 * @author Alessandro Ciotola
 *
 */
public class AddressTest 
{
	public static void main(String[] args) 
	{
		testTheNoParameterConstructor();
		testTheThreeParameterConstructor();
		testTheFiveParameterConstructor();
		testGetCivicNumber();
		testSetCivicNumber();
		testGetCity();
		testSetCity();
		testGetStreetName();
		testSetStreetName();
		testSetProvince();
		testSetCode();
	}

	private static void testTheNoParameterConstructor()
	{
		System.out.println("\nTesting the no parameter constructor.");
		Address noParamAddress = new Address ();
		System.out.println("Results of the no Param Constructor" + noParamAddress);
		System.out.println("The length should be 0: " + noParamAddress.getCivicNumber().length());
	}
	
	private static void testTheThreeParameterConstructor() 
	{
		System.out.println("\nTesting the three parameter constructor.");
		testTheThreeParameterConstructor("Case 1 - Valid data (3040 Sherbrooke Westmount)", "3040", "Sherbrooke",
				"Westmount", true);

		testTheThreeParameterConstructor("Case 2 - Invalid data – empty civicNumber (Sherbrooke Westmount)", "",
				"Sherbrooke", "Westmount", false);
		
		testTheThreeParameterConstructor("Case 3 - Invalid data – empty streetName (3040 Westmount)", "3040",
				"", "Westmount", false);
		
		testTheThreeParameterConstructor("Case 4 - Invalid data – empty city (3040 Sherbrooke)", "3040",
				"Sherbrooke", "", false);
		
		testTheThreeParameterConstructor("Case 5 - Invalid data – space in civicNumber ( Sherbrooke Westmount)", " 3040 ",
				"Sherbrooke", "Westmount", true);
		
		testTheThreeParameterConstructor("Case 6 - Invalid data – space in streetName (3040  Westmount)", "3040",
				" Sherbrooke ", "Westmount", true);
		
		testTheThreeParameterConstructor("Case 7 - Invalid data – space in city (3040 Sherbrooke )", "3040",
				"Sherbrooke", " Westmount ", true);
		
		testTheThreeParameterConstructor("Case 8 - Invalid data – null civicNumber (null Sherbrooke Westmount)",
						null,"Sherbrooke","Westmount",false);
		
		testTheThreeParameterConstructor("Case 9 - Invalid data – null civicNumber (3040 null Westmount)",
						"3040", null, "Westmount",false);
		
		testTheThreeParameterConstructor("Case 10 - Invalid data – null civicNumber (3040 Sherbrooke null)",
						"3040", "Sherbrooke", null,false);
	}

	private static void testTheThreeParameterConstructor(String testCase, String civicNumber, String streetName,
			String city, boolean expectValid) 
	{
		System.out.println("   " + testCase);

		try 
		{
			Address theAddress = new Address(civicNumber, streetName, city);
			System.out.print("\tThe Address instance was created: " + theAddress);

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
	
	//Testing the Civic Numbers

	private static void testGetCivicNumber()
	{
		System.out.println("\nTesting the getCivicNumber method.");
		testGetCivicNumber("Case 1: 3040 without leading/trailing spaces",
				"3040","3040");
		testGetCivicNumber("Case 2: 3040 with leading/trailing spaces",
				"    3040    ","3040");
	}
	
	private static void testGetCivicNumber(String testCase, 
			String civicNumber, String expectedCivicNumber)
	{
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address (civicNumber, "Sherbrooke","Westmount");
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	private static void testSetCivicNumber() {
		System.out.println("\nTesting the setCivicNumber method.");
		testSetCivicNumber("Case 1: Valid - 2086 without leading/trailing spaces",
				"2086","2086",true);
		testSetCivicNumber("Case 2: Invalid null civic number",
				null,"",false);
	}
	
	private static void testSetCivicNumber(String testCase, 
			String civicNumber, String expectedCivicNumber,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount");
		try {
			theAddress.setCivicNumber(civicNumber);
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}
	
	//Testing the City Name
	
	private static void testGetCity()
	{
		System.out.println("\nTesting the getCity method.");
		testGetCity("Case 1: Westmount without leading/trailing spaces",
				"Westmount","Westmount");
		testGetCity("Case 2: Westmount with leading/trailing spaces",
				"    Westmount    ","Westmount");
	}
	
	private static void testGetCity(String testCase, 
			String city, String expectedCity)
	{
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke",city);
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCity().equals(expectedCity))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	private static void testSetCity() {
		System.out.println("\nTesting the setCity method.");
		testSetCity("Case 1: Valid - Montreal without leading/trailing spaces",
				"Montreal","Montreal",true);
		testSetCity("Case 2: Invalid null city",
				null,"",false);
	}
	
	private static void testSetCity(String testCase, 
			String city, String expectedCity,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount");
		try {
			theAddress.setCity(city);
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getCity().equals(expectedCity))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}
	
	//Testing the Street Name
	
	private static void testGetStreetName()
	{
		System.out.println("\nTesting the getStreetName method.");
		testGetStreetName("Case 1: Sherbrooke without leading/trailing spaces",
				"Sherbrooke","Sherbrooke");
		testGetStreetName("Case 2: Sherbrooke with leading/trailing spaces",
				"    Sherbrooke    ","Sherbrooke");
	}
	
	private static void testGetStreetName(String testCase, 
			String streetName, String expectedStreetName)
	{
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", streetName,"Westmount");
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getStreetName().equals(expectedStreetName))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	private static void testSetStreetName() {
		System.out.println("\nTesting the setStreetName method.");
		testSetStreetName("Case 1: Valid - Troupe without leading/trailing spaces",
				"Troupe","Troupe",true);
		testSetStreetName("Case 2: Invalid null StreetName",
				null,"",false);
	}
	
	private static void testSetStreetName(String testCase, 
			String streetName, String expectedStreetName,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount");
		try {
			theAddress.setStreetName(streetName);
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getStreetName().equals(expectedStreetName))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}
	
	private static void testTheFiveParameterConstructor() 
	{
		System.out.println("\nTesting the five parameter constructor.");
		testTheFiveParameterConstructor("Case 1 - Invalid data – empty civicNumber ( Sherbrooke Westmount Quebec 514)", "",
				"Sherbrooke", "Westmount", "Quebec", "514", false);
		
		testTheFiveParameterConstructor("Case 2 - Invalid data – empty streetName (3040  Westmount Quebec 514)", "3040",
				"", "Westmount", "Quebec", "514", false);
		
		testTheFiveParameterConstructor("Case 3 - Invalid data – empty city (3040 Sherbrooke  Quebec 514)", "3040",
				"Sherbrooke", "", "Quebec", "514", false);
		
		testTheFiveParameterConstructor("Case 4 - valid data – space in civicNumber ( 3040  Sherbrooke Westmount Quebec 514)", " 3040 ",
				"Sherbrooke", "Westmount", "Quebec", "514", true);
		
		testTheFiveParameterConstructor("Case 5 - valid data – space in streetName (3040  Sherbrooke  Westmount Quebec 514)", "3040",
				" Sherbrooke ", "Westmount", "Quebec", "514", true);
		
		testTheFiveParameterConstructor("Case 6 - valid data – space in city (3040 Sherbrooke  Westmount  Quebec 514)", "3040",
				"Sherbrooke", " Westmount ", "Quebec", "514", true);
		
		testTheFiveParameterConstructor("Case 7 - Invalid data – null civicNumber (null Sherbrooke Westmount Quebec 514)",
						null,"Sherbrooke","Westmount", "Quebec", "514", false);
		
		testTheFiveParameterConstructor("Case 8 - Invalid data – null streetName (3040 null Westmount Quebec 514)",
						"3040", null, "Westmount", "Quebec", "514", false);
		
		testTheFiveParameterConstructor("Case 9 - Invalid data – null city (3040 Sherbrooke null Quebec 514",
						"3040", "Sherbrooke", null, "Quebec", "514", false);
		
		testTheFiveParameterConstructor("Case 10 - Valid data (3040 Sherbrooke Westmount Quebec 514)", "3040", "Sherbrooke",
				"Westmount", "Quebec", "514", true);

		testTheFiveParameterConstructor("Case 11 - Valid data – null province (3040 Sherbrooke Westmount null 514)", "3040",
				"Sherbrooke", "Westmount", null, "514", true);
		
		testTheFiveParameterConstructor("Case 12 - Valid data – null code (3040 Sherbrooke Westmount Quebec null)", "3040",
				"Sherbrooke", "Westmount", "Quebec", null, true);
		
		testTheFiveParameterConstructor("Case 13 - Valid data – space in province (3040 Sherbrooke Westmount  Quebec  514)", "3040",
				"Sherbrooke", "Westmount", " Quebec ", "514", true);
		
		testTheFiveParameterConstructor("Case 14 - Valid data – space in code (3040 Sherbrooke Westmount Quebec  514 )", "3040",
				"Sherbrooke", "Westmount", "Quebec", " 514 ", true);
	}

	private static void testTheFiveParameterConstructor(String testCase, String civicNumber, String streetName,
			String city, String province, String code, boolean expectValid) 
	{
		System.out.println("   " + testCase);

		try 
		{
			Address theAddress = new Address(civicNumber, streetName, city, Optional.ofNullable(province), Optional.ofNullable(code));
			System.out.print("\tThe Address instance was created: " + theAddress);

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
	
	private static void testSetProvince()
	{
		System.out.println("\nTesting the setProvince method.");
		testSetProvince("Case 1 - Valid data (Quebec)", "Quebec", true);
		testSetProvince("Case 3 - Valid data (null)", null, true);
		testSetProvince("Case 4 - valid data (Space)", " Quebec ", true);
	}
	
	private static void testSetProvince(String testCase, String province, boolean expectValid)
	{
		System.out.println("   " + testCase);

		try 
		{
			Address theAddress = new Address("3040", "Sherbrooke", "Westmount", Optional.ofNullable(province), Optional.ofNullable("514"));
			System.out.print("\tThe Address instance was created: " + theAddress);

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
	
	private static void testSetCode()
	{
		System.out.println("\nTesting the setCode method.");
		testSetCode("Case 1 - Valid data (514)", "514", true);
		testSetCode("Case 3 - Valid data (null)", null, true);
		testSetCode("Case 4 - valid data (Space)", " 514 ", true);
	}
	
	private static void testSetCode(String testCase, String code, boolean expectValid)
	{
		System.out.println("   " + testCase);

		try 
		{
			Address theAddress = new Address("3040", "Sherbrooke", "Westmount", Optional.ofNullable("Quebec"), Optional.ofNullable(code));
			System.out.print("\tThe Address instance was created: " + theAddress);

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
