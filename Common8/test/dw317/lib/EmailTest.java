/**
 * 
 */
package dw317.lib;

/**
 * @author Alessandro Ciotola
 *
 */
public class EmailTest 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		testCompareTo();
		testEquals();
		testGetAddress();
		testGetHostName();
		testGetUserId();
		testHashCode();
		testTheOneParameterConstructor();
	}
	
	private static void testCompareTo()
	{
		System.out.println("\nTesting the compareTo method.");
		testCompareTo("Case 1 - Valid data (fakeemail20@gmail.wtv.com - fakeemail20@gmail.wtv.com)", 
				"fakeemail20@gmail.wtv.com", "fakeemail20@gmail.wtv.com", true);

		testCompareTo("Case 2 - Valid data (fakeemail20@gmail.com - wrongemail20@gmail.wtv.com)", 
				"fakeemail20@gmail.com", "wrongemail@outlook.com", true);
		
		testCompareTo("Case 3 - Same userId, different hostname", "fakeemail20@outlook.com", "fakeemail20@hotmail.com", true);
		
		testCompareTo("Case 3 - different userId, same hostname", "fakeemail20@gmail.com", "myEmail@gmail.com", true);
	}
	
	private static void testCompareTo (String testCase, String emailAddress, String otherEmail, boolean expectValid)
	{
		System.out.println("   " + testCase);
		try 
		{
			Email address1 = new Email(emailAddress);
			Email address2 = new Email(otherEmail);
			System.out.print("\t Comparing completed: " + address1.compareTo(address2));

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
	
	private static void testEquals()
	{
		System.out.println("\nTesting the equals method.");
		testEquals("Case 1 - Valid data (fakeemail20@gmail.wtv.com - fakeemail20@gmail.wtv.com = true)", 
				"fakeemail20@gmail.wtv.com", "fakeemail20@gmail.wtv.com", true);

		testEquals("Case 2 - Valid data (fakeemail20@gmail.wtv.com - wrongemail20@gmail.wtv.com = false)", 
				"fakeemail20@gmail.wtv.com", "wrongemail20@gmail.wtv.com", true);
	}
	
	private static void testEquals (String testCase, String emailAddress, String expectedEmail, boolean expectValid)
	{
		System.out.println("   " + testCase);
		try 
		{
			Email address1 = new Email(emailAddress);
			Email address2 = new Email(expectedEmail);
			System.out.print("\t equals completed: " + address1.equals(address2));

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
	
	private static void testGetAddress()
	{
		System.out.println("\nTesting the getAddress method.");
		testGetAddress("Case 1 - Valid data (fakeemail20@gmail.wtv.com)", "fakeemail20@gmail.wtv.com", "fakeemail20@gmail.wtv.com");

		testGetAddress("Case 2 - Valid data (fakeemail20@gm-ail.wtv.com)", "fakeemail20@gm-ail.wtv.com", "fakeemail20@gm-ail.wtv.com");
		
		testGetAddress("Case 3 - Valid data (fakeemail20@gmail.com)", "fakeemail20@gmail.com", "fakeemail20@gmail.com");
		
		testGetAddress("Case 4 - Valid data (fakeemail20@gmail76.wtv.com)", "fakeemail20@gmail76.wtv.com", "fakeemail20@gmail76.wtv.com");
	}
	
	private static void testGetAddress(String testCase, String emailAddress, String expectedAddress)
	{
		System.out.println("   " + testCase);
		Email address = new Email (emailAddress);
		System.out.print("\tThe Email Address is: " + address.getAddress());

		if (!address.getAddress().equals(expectedAddress))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}
	
	private static void testGetHostName()
	{
		System.out.println("\nTesting the getHost method.");
		testGetHostName("Case 1 - Valid data (fakeemail20@gmail.wtv.com)", "fakeemail20@gmail.wtv.com", "gmail.wtv.com");

		testGetHostName("Case 2 - Valid data (fakeemail20@gm-ail.wtv.com)", "fakeemail20@gm-ail.wtv.com", "gm-ail.wtv.com");
		
		testGetHostName("Case 3 - Valid data (fakeemail20@gmail.com)", "fakeemail20@gmail.com", "gmail.com");
		
		testGetHostName("Case 4 - Valid data (fakeemail20@gmail76.wtv.com)", "fakeemail20@gmail76.wtv.com", "gmail76.wtv.com");
		
		testGetHostName("Case 5 - Invalid data - Hyphen infront of Host Name(fakeemail20@-gmail.wtv.com)", 
						"fakeemail20@-gmail.wtv.com", "fakeemail20");
		
		testGetHostName("Case 6 - Valid data (a@a.com)", "a@a.com", "a.com");
	} 
	
	private static void testGetHostName(String testCase, 
			String emailAddress, String expectedUserId)
	{
		System.out.println("   " + testCase);
		try
		{
			Email address = new Email (emailAddress);
			System.out.print("\tThe host name is: " + address.getHost());
			if (!address.getHost().equals(expectedUserId))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch(IllegalArgumentException iae)
		{
			System.out.print("\t" + iae.getMessage());
		}
		System.out.println("\n");
	}
	
	
	private static void testGetUserId()
	{
		System.out.println("\nTesting the getUserId method.");
		testGetUserId("Case 1 - Valid data (fakeemail20@gmail.wtv.com)", "fakeemail20@gmail.wtv.com", "fakeemail20");

		testGetUserId("Case 2 - Valid data (fak_eemail20@gmail.wtv.com)", "fak_eemail20@gmail.wtv.com", "fak_eemail20");
		
		testGetUserId("Case 3 - Valid data (fakee.mail20@gmail.wtv.com)", "fakee.mail20@gmail.wtv.com", "fakee.mail20");
		
		testGetUserId("Case 4 - Valid data (fake-email20@gmail.wtv.com)", "fake-email20@gmail.wtv.com", "fake-email20");
		
		testGetUserId("Case 5 - Invalid data - dot infront of UserId(.fakeemail20@gmail.wtv.com)", 
						".fakeemail20@gmail.wtv.com", "fakeemail20");
		
		testGetUserId("Case 6 - Valid data (a@a.com)", "a@a.com", "a");
	}
	
	private static void testGetUserId(String testCase, 
			String emailAddress, String expectedUserId)
	{
		try
		{
			System.out.println("   " + testCase);
			Email address = new Email (emailAddress);
			System.out.print("\tThe UserId is: " + address.getUserId());

			if (!address.getUserId().equals(expectedUserId))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch(IllegalArgumentException iae)
		{
			System.out.print("\t" + iae.getMessage());
		}

		System.out.println("\n");
	}
	
	private static void testHashCode()
	{
		System.out.println("\nTesting the HashCode method.");
		testHashCode("Case 1 - Valid data (fakeemail20@gmail.wtv.com - fakeemail20@gmail.wtv.com == same)", 
				"fakeemail20@gmail.wtv.com", "fakeemail20@gmail.wtv.com", true);

		testHashCode("Case 2 - Valid data (fakeemail20@gmail.wtv.com - wrongemail20@gmail.wtv.com = different)", 
				"fakeemail20@gmail.wtv.com", "wrongemail20@gmail.wtv.com", true);
	}
	
	private static void testHashCode (String testCase, String emailAddress, String expectedEmail, boolean expectValid)
	{
		System.out.println("   " + testCase);
		try 
		{
			Email address1 = new Email(emailAddress);
			Email address2 = new Email(expectedEmail);
			System.out.print("\t hashCode1 completed: " + address1.hashCode());
			System.out.print("\t hashCode2 completed: " + address2.hashCode());

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
	
	private static void testTheOneParameterConstructor() 
	{
		System.out.println("\nTesting the One parameter constructor.");
		testTheOneParameterConstructor("Case 1 - Valid data (fakeemail20@gmail.wtv.com)", "fakeemail20@gmail.com", true);

		testTheOneParameterConstructor("Case 2 - Valid data (fak_ee.mai-l20@gma-il.wtv.com)", 
				"fak_ee.mai-l20@gmail.wtv.com", true);
		
		testTheOneParameterConstructor("Case 3 - Invalid data – dot infront of userId (.fakeemail20@gmail.wtv.com)", 
				".fakeemail20@gmail.wtv.com", false);
		
		testTheOneParameterConstructor("Case 4 - Invalid data – dot after userId (fakeemail20.@gmail.wtv.com)", 
				"fakeemail20.@gmail.wtv.com", false);
		
		testTheOneParameterConstructor("Case 5 - Invalid data – invalid character in userId (fake?email20@gmail.wtv.com)",
				"fake?email20@gmail.wtv.com", false);
		
		testTheOneParameterConstructor("Case 6 - Invalid data – hyphen infront of host name (fakeemail20@-gmail.wtv.com)", 
				"fakeemail20@-gmail.wtv.com", false);
		
		testTheOneParameterConstructor("Case 7 - Invalid data – hyphen after host name (fakeemail20@gmail.wtv.com-)", 
				"fakeemail20@gmail.wtv.com-", false);
		
		testTheOneParameterConstructor("Case 8 - Invalid data – invalid character in host name (fakeemail20@gma$il.wtv.com)",
				"fakeemail20@gma$il.wtv.com", false);
		
		testTheOneParameterConstructor("Case 9 - Valid data (  fakeemail20@gmail.wtv.com  )", 
				"  fakeemail20@gmail.wtv.com  ", true);
		
		testTheOneParameterConstructor("Case 10 - Invalid data - no '@'", "fakeemail20gmail.wtv.com", false);
		
		testTheOneParameterConstructor("Case 11 - Valid data - dots", "fak.ee.mail20@gmail.wtv.com", true);
		
		testTheOneParameterConstructor("Case 12 - Invalid data - null", null , false);
		
		testTheOneParameterConstructor("Case 11 - Invalid data - too many dots", "fake..email20@gmail.wtv.com", false);
	}

	private static void testTheOneParameterConstructor(String testCase, String emailAddress, boolean expectValid) 
	{
		System.out.println("   " + testCase);
		try 
		{
			Email address = new Email(emailAddress);
			System.out.print("\tThe Email instance was created: " + address);

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
