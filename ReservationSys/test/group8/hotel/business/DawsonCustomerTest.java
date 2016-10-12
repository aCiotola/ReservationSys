/**
 *
 */
package group8.hotel.business;

import java.util.Optional;

import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;

/**
 * @author Hannah Ly
 *
 */
public class DawsonCustomerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		testCompareTo();

		testEquals();

		testGetName();

		testGetEmail();

		testHashCode();

		testGetCreditCard();

		testSetCreditCard();

		testTheThreeParameterConstructor();

		testToString();
	}

	public static void testCompareTo()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "Ohnsmith@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "NicholasSam@gmail.com");
		DawsonCustomer customer3 = new DawsonCustomer("Sam", "Nicholas", "NicholasSam@gmail.com");

		testCompareTo("Case 1 - Valid - Same objects; returns 0;", customer1, customer1);
		testCompareTo("Case 2 - Valid - customer1 is bigger than customer2; returns 1;", customer1, customer2);
		testCompareTo("Case 3 - Valid - Different objects, same information; returns 0",customer2, customer3);
		testCompareTo("Case 4 - Valid - customer1 is smaller than customer2; returns -1", customer3, customer1);
	}

	public static void testCompareTo(String testCase, DawsonCustomer customer1, DawsonCustomer customer2)
	{
		System.out.println(testCase);
		System.out.print("First customer: " + customer1);
		System.out.print("\t Second customer: " + customer2);

		if(customer1.compareTo(customer2) < 0)
		{
			System.out.print("\nSecond customer comes before first customer.");
			System.out.print("\t Value returned by the method: " + customer1.compareTo(customer2));
		}
		else
			if(customer1.compareTo(customer2) == 0)
			{
				System.out.print("\nSecond customer is the same as first customer.");
				System.out.print("\t Value returned by the method: " + customer1.compareTo(customer2));
			}
			else
			{
				System.out.print("\nFirst customer comes before second customer.");
				System.out.print("\t Value returned by the method: " + customer1.compareTo(customer2));
			}

		System.out.println("\n");
	}

	public static void testEquals()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "Johnsmith@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "NicholasSam@gmail.com");
		DawsonCustomer customer3 = new DawsonCustomer("Sam", "Nicholas", "NicholasSam@gmail.com");

		testEquals("Case 1 - Valid - Same object; returns true;", customer1, customer1);
		testEquals("Case 2 - Valid - Second customer is null; returns false", customer1, null);
		testEquals("Case 2 - Valid - Different objects; returns false;", customer1, customer2);
		testEquals("Case 3 - Valid - Different objects, same information; returns true",customer2, customer3);
	}

	public static void testEquals(String testCase, DawsonCustomer customer1, DawsonCustomer customer2)
	{
		System.out.println(testCase);
		System.out.print("First customer: " + customer1);
		System.out.print("\t Second customer: " + customer2);

		if(customer1.equals(customer2))
		{
			System.out.print("\nThe two customer objects are the same.");
			System.out.print("\t Value returned by the method: " + customer1.equals(customer2));
		}
		else
		{
			System.out.print("\nThe two customer objects are the different.");
			System.out.print("\t Value returned by the method: " + customer1.equals(customer2));
		}

		System.out.println("\n");
	}

	private static void testGetCreditCard()
		{
			System.out.println("\nTesting the getCreditCard method.");
			testGetCreditCard("Case 1: Valid Data (Mastercard - 5570147598726899)", CreditCard.CardType.MASTERCARD,"5570147598726899", true);
			testGetCreditCard("Case 2: Valid Data (Visa - 4916384805145011)", CreditCard.CardType.VISA, "4916384805145011", true);
			testGetCreditCard("Case 3: Valid Data (Amex - 346937831543158)", CreditCard.CardType.AMEX, "346937831543158", true);
			testGetCreditCard("Case 4: Invalid Data  - Invalid starting digits (Amex - 566937831543158)", CreditCard.CardType.AMEX,
					"566937831543158", true);
			testGetCreditCard("Case 5: Invalid Data - Invalid amount of digits (Amex - 34693731543158)", CreditCard.CardType.AMEX,
					"34693731543158", true);
		}

		private static void testGetCreditCard(String testCase, CardType cardType, String cardNumber, boolean isValid)
		{
			System.out.println("   " + testCase);
			DawsonCustomer cus = new DawsonCustomer("Jim", "John", "Jimjohn@gmail.com");

			try {
				CreditCard card = CreditCard.getInstance(cardType, cardNumber);
				cus.setCreditCard(Optional.ofNullable(card));
				System.out.println("\tThe Customer instance was created: " + cus);
				System.out.println("\tThe CreditCard has been created: " + cus.getCreditCard().get());
			}
			catch (IllegalArgumentException iae) {
				System.out.print("\t"+ iae.getMessage());
				if (isValid)
					System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
			}
			catch (Exception e) {
				System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
						e.getMessage() + " ==== FAILED TEST ====");
				if (isValid)
					System.out.print(" Expected Valid.");
			}
			System.out.println("\n");
	}

	public static void testGetName()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "Johnsmith@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "NicholasSam@gmail.com");

		testGetName("Case 1 - Valid - Prints out the firstname*lastname", customer1);
		testGetName("Case 2 - Valid - Prints out the firstname*lastname", customer2);
	}

	public static void testGetName(String testCase, DawsonCustomer customer)
	{
		System.out.println(testCase);
		System.out.println("This is the customer's name: " + customer.getName());

		System.out.println("\n");
	}

	public static void testGetEmail()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "Johnsmith2@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "Nicholas_Sam@gmail.com");

		testGetEmail("Case 1 - Valid - Prints out the email", customer1);
		testGetEmail("Case 2 - Valid - Prints out the email", customer2);
	}

	public static void testGetEmail(String testCase, DawsonCustomer customer)
	{
		System.out.println(testCase);
		System.out.println("This is the customer's email: " + customer.getEmail());

		System.out.println("\n");
	}

	public static void testHashCode()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "Johnsmith2@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "Nicholas_Sam@gmail.com");
		DawsonCustomer customer3 = new DawsonCustomer("Sam", "Nicholas", "Nicholas_Sam@gmail.com");

		testHashCode("Case 1 - Valid - Same objects; same hash code", customer1, customer1);
		testHashCode("Case 2 - Valid - Different objects; differents hash codes", customer1, customer2);
		testHashCode("Case 3 - Valid - Different objects, same information; same hash code", customer2, customer3);
	}

	public static void testHashCode(String testCase, DawsonCustomer customer1, DawsonCustomer customer2)
	{
		System.out.println(testCase);
		System.out.print("First customer: " + customer1);
		System.out.print("\t Second customer: " + customer2);

		if(customer1.hashCode() == customer2.hashCode())
		{
			System.out.print("\nHash codes for both customers are the same.");
			System.out.print("Hash code room 1: " + customer1.hashCode());
			System.out.print("\t \t Hash code room 2: " + customer2.hashCode());
		}
		else
		{
			System.out.print("\nHash codes for both customers are different.");
			System.out.print("Hash code room1: " + customer1.hashCode());
			System.out.print("\t \t Hash code room 2: " + customer2.hashCode());
		}

		System.out.println("\n");
	}

	private static void testSetCreditCard()
		{
			System.out.println("\nTesting the setCreditCard method.");
			testSetCreditCard("Case 1: Valid Data (Mastercard - 5570147598726899)", CreditCard.CardType.MASTERCARD,"5570147598726899", true);
			testSetCreditCard("Case 2: Valid Data (Visa - 4916384805145011)", CreditCard.CardType.VISA, "4916384805145011", true);
			testSetCreditCard("Case 3: Valid Data (Amex - 346937831543158)", CreditCard.CardType.AMEX, "346937831543158", true);
			testSetCreditCard("Case 4: Invalid Data  - Invalid starting digits (MasterCard - 5970147598726899)", CreditCard.CardType.MASTERCARD,
					"5970147598726899", false);
			testSetCreditCard("Case 5: Invalid Data - Invalid amount of digits (Visa - 491638480145011)", CreditCard.CardType.VISA,
					"491638480145011", false);
		}

		private static void testSetCreditCard(String testCase, CardType cardType, String cardNumber, boolean isValid)
		{
			System.out.println("   " + testCase);

			DawsonCustomer cus = new DawsonCustomer("Steven", "Daiphie", "StevenDaiphie@hotmail.wtv.com");

			try {
				CreditCard card = CreditCard.getInstance(cardType, cardNumber);
				cus.setCreditCard(Optional.ofNullable(card));
				System.out.print("\tThe Customer instance was created: " + cus);
			}
			catch (IllegalArgumentException iae)
			{
				System.out.print("\t"+ iae.getMessage());
				if (isValid)
					System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
			}
			catch (Exception e) {
				System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
						e.getMessage() + " ==== FAILED TEST ====");
				if (isValid)
					System.out.print(" Expected Valid.");
			}
			System.out.println("\n");
	}

	public static void testTheThreeParameterConstructor()
	{
		testTheThreeParameterConstructor("Case 1 - Valid - Customer should be created.", "John", "Smith", "johnsmith@outlook.com", true);

		testTheThreeParameterConstructor("Case 2 - Valid - Customer should be created.", "Sam", "Graves", "graves_sam@gmail.com", true);

		testTheThreeParameterConstructor("Case 3 - Valid - Customer should be created.", "Nick", "Bong", "nick9bong@hotmail.com", true);

		testTheThreeParameterConstructor("Case 4 - Invalid - First name is empty", "", "Smith", "johnsmith@outlook.com", false);

		testTheThreeParameterConstructor("Case 5 - Invalid - Last name is empty", "John", "", "johnsmith@outlook.com", false);

		testTheThreeParameterConstructor("Case 6 - Invalid - Email is empty", "John", "Smith", "", false);
	}

	public static void testTheThreeParameterConstructor(String testCase, String firstName, String lastName, String email, boolean expectedValid)
	{
		System.out.println(testCase);

		try
		{
			DawsonCustomer customer = new DawsonCustomer(firstName, lastName, email);
			System.out.print("A new customer was created: " + customer);

			if(!expectedValid)
			{
				System.out.print("\t Error. Expected INVALID. FAIL --try--");
			}
		}
		catch(IllegalArgumentException iae)
		{
			System.out.print("Printing exception message: " + iae.getMessage());

			if(expectedValid)
			{
				System.out.print("\t Error. Expected VALID. FAIL --catch--");
			}
		}
		System.out.println("\n");
	}

	public static void testToString()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "ohnsmith@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "NicholasSam@gmail.com");

		CardType typeCard = CreditCard.CardType.AMEX;
		String creditCardNum = "344778091239307";

		CreditCard card = CreditCard.getInstance(typeCard, creditCardNum);
		customer1.setCreditCard(Optional.ofNullable(card));

		testToString("Case 1 - Valid - With a credit card", customer1);
		testToString("Case 2 - Valid - Without a credit card", customer2);
	}

	public static void testToString(String testCase, DawsonCustomer customer)
	{
		System.out.println(testCase);

		if(customer.getCreditCard() != null)
		{
			System.out.print("Format: email*firstName*lastName*cardType*cardNumber");
			System.out.print("\n" + customer.toString());
		}
		else
		{
			System.out.print("Format: email*firstName*lastName**");
			System.out.print("\n" + customer.toString());
		}

		System.out.println("\n");
	}
}
