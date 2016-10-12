/**
 * 
 */
package dw317.lib.creditcard;

/**
 * @author Alessandro Ciotola
 *
 */
public class AmexTest 
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
		testTheOneParameterConstructor("Case 1 - Valid data (346937831543158)", "346937831543158", true);

		testTheOneParameterConstructor("Case 2 - Valid data (372934988912226)", "372934988912226", true);
		
		testTheOneParameterConstructor("Case 3 - Invalid data – length is not 15 (37293498891222)", 
				"37293498891222", false);
		
		testTheOneParameterConstructor("Case 4 - Invalid data – Doesn't begin with 34 or 37 (732934988912226)", 
				"732934988912226", false);
	}

	private static void testTheOneParameterConstructor(String testCase, String cardNumber, boolean expectValid) 
	{
		System.out.println("   " + testCase);
		try 
		{
			Amex a = new Amex(cardNumber);
			System.out.println("The credit card instance was created: " + a);

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
