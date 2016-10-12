package dw317.lib.creditcard;

/**
 * The CreditCardTest class is to test mostly all the methods within the AbstractCreditCard class.
 * 
 * @author Phi Dai Nguyen
 * @version 9.26.2016
 */
import java.util.Optional;

import dw317.lib.creditcard.CreditCard.CardType;

public class CreditCardTest {

	public static void main(String[] args) {
		testingNumber();
		testingTheConstructor();
		testingHashCode();
		testingEquals();
		testingGetType();
	}

	private static void testingNumber() {
		System.out.println("\nTesting the validateLuhnAlgorithm method");
		testingNumber("Case 1: Valid Visa CreditCard", "4539717632676467", true);
		testingNumber("Case 2: Valid Mastercard CreditCard", "5478522933603016", true);
		testingNumber("Case 3: Valid Amex CreditCard", "376019655270961", true);
		testingNumber("Case 4: Invalid Visa CreditCard", "4556925490475042", false);
		testingNumber("Case 5: Invalid Mastercard CreditCard", "5374199310171485", false);
		testingNumber("Case 6: Invalid Amex CreditCard", "376398559956792", false);
		testingNumber("Case 7: CreditCard with letters", "498gji49f019304", false);
		testingNumber("Case 8: CreditCard with less than 15 digits", "15894", false);
		testingNumber("Case 9: CreditCard with letter and digits less than 15", "5938fjid43", false);

	}

	private static void testingNumber(String text, String number, boolean expectValid) {
		System.out.println("    " + text);
		try {
			AbstractCreditCard.validateLuhnAlgorithm(number);
			if (!expectValid)
				System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print(" Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXCEPTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid");
		}

		System.out.println("\n");
	}

	private static void testingTheConstructor() {
		System.out.println("\nTesting the two parameter constructor");
		testingTheConstructor("Case 1: Visa as a cardtype and valid number", CreditCard.CardType.VISA,
				"4916181441069849", true);
		testingTheConstructor("Case 2: Mastercard as a cardtype and valid number", CreditCard.CardType.MASTERCARD,
				"5466131008084773", true);
		testingTheConstructor("Case 3: Amex as a cardtype and valid number", CreditCard.CardType.AMEX,
				"347538983643678", true);
		testingTheConstructor("Case 4: Null as a cardtype and valid number", null, "5140070034778897", false);
		// Case 4 throws an exception belonging to the CreditCard interface
		testingTheConstructor("Case 5: Amex as a cardtype but null number", CreditCard.CardType.AMEX, null, false);
		testingTheConstructor("Case 6: Null as a cardtype and null number", null, null, false);
		// Case 6 throws an exception belonging to the CreditCard interface
		testingTheConstructor("Case 7: Mastercard as a cardtype but invalid number", CreditCard.CardType.MASTERCARD,
				"5176877796699409", false);

	}

	private static void testingTheConstructor(String string, CardType cardtype, String number, boolean expectValid) {
		System.out.println("   " + string);
		try {
			CreditCard card = CreditCard.getInstance(cardtype, number);
			System.out.println("The card instance has been created: " + card);

			if (!expectValid)
				System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print(" Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXCEPTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid");
		}

		System.out.println("\n");

	}

	private static void testingHashCode() {
		System.out.println("\nTesting the hashCode method");
		testingHashCode("\nCase 1: Same cardtype and card digits", CreditCard.CardType.AMEX, CreditCard.CardType.AMEX,
				"375803539392229", "375803539392229", true);
		testingHashCode("\nCase 2: Different cardtype but same card digits", CreditCard.CardType.AMEX,
				CreditCard.CardType.MASTERCARD, "375042715280813", "375042715280813", false);
		testingHashCode("\nCase 3: Different cardtype and card digits", CreditCard.CardType.MASTERCARD,
				CreditCard.CardType.AMEX, "348606218904527", "5176877796699609", false);
	}

	private static void testingHashCode(String text, CardType card, CardType expectedCard, String number,
			String expectedNumber, boolean expectValid) {
		System.out.println("   " + text);

		try {
			CreditCard card1 = CreditCard.getInstance(card, number);
			CreditCard card2 = CreditCard.getInstance(expectedCard, expectedNumber);

			int hCode1 = card1.hashCode();
			int hCode2 = card2.hashCode();
			System.out.print("\t hashCode from card1 completed: " + hCode1);
			System.out.print("\t hashCode from card2 completed: " + hCode2);

			if (hCode1 == hCode2)
				System.out.println("\nMatching cardtype and credit card number!");

			if (!expectValid)
				System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print(" Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXCEPTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid");
		}

		System.out.println("\n");

	}

	private static void testingEquals() {
		System.out.println("\nTesting equals method");
		testingEquals("Case 1: Same Cardtype/Credit Card number objects", CreditCard.CardType.AMEX,
				CreditCard.CardType.AMEX, "375803539392229", "375803539392229", true);
		testingEquals("Case 2: Same Cardtype but different credit card number objects", CreditCard.CardType.MASTERCARD,
				CreditCard.CardType.MASTERCARD, "5390425828976205", "5176877796699609", false);
		testingEquals("Case 3: Different Cardtypes and different credit card number objects", CreditCard.CardType.AMEX,
				CreditCard.CardType.MASTERCARD, "375803539392229", "5110367373982981", false);
		testingEquals("Case 4: Null Cardtypes and same credit card number objects", null, null, "4929633485211674",
				"4929633485211674", false);
		/*
		 * Case 4 has a NullPointerException that towards the CreditCard
		 * interface. Therefore any nulls in the card types will result to that
		 * exception.
		 */
		testingEquals("Case 5: Same card types but different credit card numbers", CreditCard.CardType.VISA,
				CreditCard.CardType.VISA, "4716302302901829", "4929633485211674", false);
	}

	private static void testingEquals(String text, CardType card, CardType expectedCard, String number,
			String expectedNumber, boolean expectValid) {
		System.out.println("    " + text);
		try {
			CreditCard card1 = CreditCard.getInstance(card, number);
			CreditCard card2 = CreditCard.getInstance(expectedCard, expectedNumber);

			boolean result = card1.equals(card2);

			if (result == false) { // indicating that the objects are not equal
				System.out.println(result);
			} else
				System.out.println(result);

			if (result == true)
				if (!expectValid)
					System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print(" Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXCEPTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid");
		}

		System.out.println("\n");
	}

	private static void testingGetType() {
		System.out.println("\nTesting the getType method");
		testingGetType("Case 1: Mastercard cardtype (valid digits)", CreditCard.CardType.MASTERCARD, "5251226495176162",
				true);
		testingGetType("Case 2: Visa cardtype (valid digits)", CreditCard.CardType.VISA, "4485333575649877", true);
		testingGetType("Case 3: Amex cardtype (valid digits)", CreditCard.CardType.AMEX, "344814119422870", true);
		testingGetType("Case 4: Null cardtype (valid digits)", null, "", false);
		// Case 4 is a NullPointerException leading to the CreditCard interface.
	}

	private static void testingGetType(String text, CardType card, String number, boolean expectValid) {
		System.out.println("   " + text);
		try {
			CreditCard theCard = CreditCard.getInstance(card, number);

			CardType nameCard = theCard.getType();
			System.out.println(nameCard);

			if (!expectValid)
				System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print(" Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXCEPTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid");
		}

		System.out.println("\n");
	}
}
