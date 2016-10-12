/**
 * 
 */
package group8.hotel.business;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.CreditCard;

/**
 * @author Alessandro Ciotola
 *
 */
public class DawsonHotelFactoryTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testGetCard();
		testGetCustomerInstance();
		testGetRoomInstance();
		testGetReservationInstance();
	}

	private static void testGetCard() 
	{
		System.out.println("\nTesting the getCard method.");
		testGetCard("Case 1 - Valid data (MasterCard, 5570147598726899)", "MASTERCARD", "5570147598726899", true);

		testGetCard("Case 2 - Valid data (Amex, 346937831543158)", "AMEX", "346937831543158", true);
		
		testGetCard("Case 3 - inValid data - Not enough numbers (Amex, 34937831543158)", "AMEX", "34937831543158", false);
		
		testGetCard("Case 4 - inValid data - Doesn't begin with the right numbers (Amex, 225937831543158)", "AMEX", "225937831543158", false);
		
		
	}
	
	private static void testGetCard(String testCase, String cardType, String number, boolean expectValid)
	{		
		System.out.println("   " + testCase);
		try 
		{
			CreditCard cc = DawsonHotelFactory.DAWSON.getCard(cardType, number);
			System.out.print("\tThe CreditCard instance was created: " + cc);

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testGetCustomerInstance() {
		System.out.println("\nTesting the getCustomerInstance method.");
		testGetCustomerInstance("Case 1 - Valid data (john, bin, john.bin@gmail.com)", "john", "bin",
				"john.bin@gmail.com", true);

		testGetCustomerInstance("Case 2 - Valid data (henry, shan, henry_shan@hotmail.com)", "henry", "shan",
				"henry_shan@hotmail.com", true);

		testGetCustomerInstance("Case 3 - Invalid data - no name (henry_shan@hotmail.com)", ""
				+ "", "shan",
				"henry_shan@hotmail.com", false);
	}

	private static void testGetCustomerInstance(String testCase, String firstName, String lastName, String email,
			boolean expectValid) {
		System.out.println("   " + testCase);

		try {
			Customer cus = DawsonHotelFactory.DAWSON.getCustomerInstance(firstName, lastName, email);
			System.out.print("\tThe Customer instance was created: " + cus);

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

	private static void testGetRoomInstance() {
		System.out.println("\nTesting the getRoomInstance method.");
		testGetRoomInstance("Case 1 - Valid data (101 - normal)", 101, "NORMAL", true);

		testGetRoomInstance("Case 2 - Valid data (702 - Suite)", 702, "SUITE", true);
		
		testGetRoomInstance("Case 3 - Invalid data - non existant room (914 - Suite)", 914, "SUITE", false);
		
		testGetRoomInstance("Case 4 - Invalid data - non existant roomType (702 - HOME)", 702, "HOME", false);
	}

	private static void testGetRoomInstance(String testCase, int roomNumber, String roomType, boolean expectValid) {
		System.out.println("   " + testCase);

		try {
			Room room = DawsonHotelFactory.DAWSON.getRoomInstance(roomNumber, roomType);
			System.out.print("\tThe Room instance was created: " + room);

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

	private static void testGetReservationInstance() 
	{
		System.out.println("\nTesting the getReservationInstance method.");
		
		testGetReservationInstance("Case 1 - Valid data (john, bin, john.bin@gmail.com, 101, normal, 2000, 3, 5, 2000, 3, 20)",
				"john", "bin", "john.bin@gmail.com", 101, "NORMAL", 2000, 3, 5, 2000, 3, 20, true);

		testGetReservationInstance("Case 2 - Valid data (Susan, nom, susan_nom@hotmail.ok.com, 702, Suite, 2016, 7, 2, 2016, 7, 25)",
				"susan", "nom", "susan_nom@hotmail.ok.com", 702, "SUITE", 2016, 7, 2, 2016, 7, 25, true);
		
		testGetReservationInstance("Case 3 - Invalid data - out date is before in date (Susan, nom, susan_nom@hotmail.ok.com, "
				+ "702, Suite, 2016, 7, 2, 2016, 7, 25)", "susan", "nom", "susan_nom@hotmail.ok.com", 702, "SUITE", 2016, 0, 2, 
				2017, 6, 22, false);
	}

	private static void testGetReservationInstance(String testCase, String firstName, String lastName, String email,
			int roomNumber, String roomType, int inYear, int inMonth, int inDay, int outYear, int outMonth,
			int outDay, boolean expectValid) 
	{
		System.out.println("   " + testCase);

		try {
			Customer cus = DawsonHotelFactory.DAWSON.getCustomerInstance(firstName, lastName, email);
			Room room = DawsonHotelFactory.DAWSON.getRoomInstance(roomNumber, roomType);
			Reservation res = DawsonHotelFactory.DAWSON.getReservationInstance(cus, room, inYear, inMonth, 
					inDay, outYear, outMonth, outDay);
			Reservation resCopy = DawsonHotelFactory.DAWSON.getReservationInstance(res);
			
			System.out.println("\tThe Reservation instance was created: " + res);
			System.out.println("\tCopied Reservation instance: was created:" + resCopy);

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
