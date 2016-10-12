/**
 * 
 */
package dw317.lib.creditcard;

/**
 * @author Alessandro Ciotola
 *
 */
public class VisaTest
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		testTheOneParameterConstructor(); 
	}
	
	private static void testTheOneParameterConstructor() 
	{
		System.out.println("\nTesting the One parameter constructor.");
		testTheOneParameterConstructor("Case 1 - Valid data (4916384805145011)", "4916384805145011", true);
		
		testTheOneParameterConstructor("Case 2 - Invalid data – length is not 16 (491638480514501)", 
				"491638480514501", false);
		
		testTheOneParameterConstructor("Case 3 - Invalid data – Doesn't begin with a 4 (2916384805145011)", 
				"2916384805145011", false);
	}

	private static void testTheOneParameterConstructor(String testCase, String cardNumber, boolean expectValid) 
	{
		System.out.println("   " + testCase);
		try 
		{
			Visa v = new Visa(cardNumber);
			System.out.println("The credit card instance was created: " + v);

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
