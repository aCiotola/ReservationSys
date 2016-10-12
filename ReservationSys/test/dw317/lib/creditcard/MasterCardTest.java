/**
 * 
 */
package dw317.lib.creditcard;

/**
 * @author Alessandro Ciotola
 *
 */
public class MasterCardTest
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
		testTheOneParameterConstructor("Case 1 - Valid data (5570147598726899)", "5570147598726899", true);

		testTheOneParameterConstructor("Case 2 - Valid data (5383122517916803)", "5383122517916803", true);
		
		testTheOneParameterConstructor("Case 3 - Invalid data – length is not 16 (557014759872689)", 
				"557014759872689", false);
		
		testTheOneParameterConstructor("Case 4 - Invalid data – Doesn't begin with 51-55 (7570147598726899)", 
				"7570147598726899", false);
	}

	private static void testTheOneParameterConstructor(String testCase, String cardNumber, boolean expectValid) 
	{
		System.out.println("   " + testCase);
		try 
		{
			MasterCard mc = new MasterCard(cardNumber);
			System.out.println("The credit card instance was created: " + mc);

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
