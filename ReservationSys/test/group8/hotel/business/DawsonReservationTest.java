/**
 * 
 */
package group8.hotel.business;
import java.time.DateTimeException;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;

/**
 * @author Hannah Ly
 *
 */
public class DawsonReservationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		testCompareTo();
		testEquals();
		testGetCustomer();
		
		testGetRoom();
		
		testGetCheckInDate();
		testGetCheckOutDate();
		
		testGetNumberDays();
		
		testHashCode();

		testOverlap();
		
		testTheEightParameterConstructor();
		
		testToString();
	}
	
	public static void testCompareTo()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		DawsonReservation rsvp1 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer2, room1, 2016, 8, 26, 2016, 8, 30);
		DawsonReservation rsvp3 = new DawsonReservation(customer1, room2, 2016, 8, 26, 2016, 8, 30);
		DawsonReservation rsvp4 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		
		testCompareTo("Case 1 - Valid - Same room, different dates; Reservation 1 is BEFORE reservation 2; returns -1", rsvp1, rsvp2);
		testCompareTo("Case 1 - Valid - Same room, different dates; Reservation 1 is AFTER reservation 2; returns 1", rsvp2, rsvp1);
		testCompareTo("Case 2 - Valid - Different rooms; Reservation 1 is BEFORE reservation 2; returns -1", rsvp1, rsvp3);
		testCompareTo("Case 3 - Valid - Different rooms; Reservation 1 is AFTER reservation 2; returns 1", rsvp3, rsvp1);
		testCompareTo("Case 5 - Valid - Same room, same dates, same object; Reservation 1 is the same as reservation 2; returns 0", rsvp1, rsvp1);
		testCompareTo("Case 5 - Valid - Same room, same dates, different objects; Reservation 1 is the same as reservation 2; returns 0", rsvp1, rsvp4);
		
	}

	public static void testCompareTo(String testCase, DawsonReservation rsvp1, DawsonReservation rsvp2)
	{
		System.out.println(testCase);
		System.out.print("Reservation #1: " + rsvp1);
		System.out.print("\t Reservation #2: " + rsvp2);
		
		if(rsvp1.compareTo(rsvp2) < 0)
		{
			System.out.print("\nReservation 1 comes before reservation 2.");
			System.out.print("\t Result returned by the method: " + rsvp1.compareTo(rsvp2));
		}
		else
			if(rsvp1.compareTo(rsvp2) == 0)
			{
				System.out.print("\nReservation 1 is the same as reservation 2.");
				System.out.print("\t Result returned by the method: " + rsvp1.compareTo(rsvp2));
			}
			else
			{
				System.out.print("\nReservation 1 comes after reservation 2.");
				System.out.print("\t Result returned by the method: " + rsvp1.compareTo(rsvp2));
			}
		
		System.out.println("\n");
	}
	
	public static void testEquals()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		DawsonReservation rsvp1 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer1, room1, 2016, 8, 26, 2016, 8, 30);
		DawsonReservation rsvp3 = new DawsonReservation(customer1, room2, 2016, 8, 26, 2016, 8, 30);
		DawsonReservation rsvp4 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp5 = new DawsonReservation(customer2, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp6 = new DawsonReservation(customer2, room1, 2016, 4, 28, 2016, 7, 25);
		DawsonReservation rsvp7 = new DawsonReservation(customer2, room1, 2016, 4, 28, 2016, 5, 16);
		
		testEquals("Case 1 - Valid - Same object; returns true", rsvp1, rsvp1);
		testEquals("Case 2 - Valid - Second reservation is null; returns false", rsvp1, null);
		testEquals("Case 3 - Valid - Different objects, same information; returns true", rsvp1, rsvp4);
		testEquals("Case 4 - Valid - Same customer, same room, different dates; return false", rsvp1, rsvp2);
		testEquals("Case 5 - Valid - Same customer, different rooms, same date; return false", rsvp2, rsvp3);
		testEquals("Case 6 - Valid - Different customers, same room, same date; return false", rsvp4, rsvp5);
		testEquals("Case 7 - Valid - Same customer, same room, different checkIn dates, same checkOut date; return false", rsvp5, rsvp6);
		testEquals("Case 7 - Valid - Same customer, same room, same checkIn date, different checkOut dates; return false", rsvp6, rsvp7);
	}
	
	public static void testEquals(String testCase, DawsonReservation rsvp1, DawsonReservation rsvp2)
	{
		System.out.println(testCase);
		System.out.print("Reservation #1: " + rsvp1);
		System.out.print("\t Reservation #2: " + rsvp2);
		
		if(rsvp1.equals(rsvp2))
		{
			System.out.print("\nThe two reservation objects are the same.");
			System.out.print("\t Value returned by the method: " + rsvp1.equals(rsvp2));
		}
		else
		{
			System.out.print("\nThe two reservation objects are the different.");
			System.out.print("\t Value returned by the method: " + rsvp1.equals(rsvp2));
		}
		
		System.out.println("\n");
	}
	
	public static void testGetCustomer()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room = new DawsonRoom(301, RoomType.NORMAL);

		DawsonReservation rsvp1 = new DawsonReservation(customer1, room, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer2, room, 2016, 5, 24, 2016, 7, 25);
		
		testGetCustomer("Case 1 - Valid - smith.john@gmail.com*John*Smith", rsvp1);
		testGetCustomer("Case 2 - Valid - sam98bong@outlook.com*Sam*Bong*", rsvp2);
	}
	
	public static void testGetCustomer(String testCase, DawsonReservation rsvp)
	{
		System.out.println(testCase);
		System.out.println("This is the customer's information: " + rsvp.getCustomer());
		
		System.out.println("\n");
	}
	
	public static void testGetRoom()
	{
		DawsonCustomer customer = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		DawsonReservation rsvp1 = new DawsonReservation(customer, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer, room2, 2016, 5, 24, 2016, 7, 25);
		
		testGetRoom("Case 1 - Valid - 301*normal", rsvp1);
		testGetRoom("Case 2 - Valid - 703*suite", rsvp2);
	}
	
	public static void testGetRoom(String testCase, DawsonReservation rsvp)
	{
		System.out.println(testCase);
		System.out.println("This is the room: " + rsvp.getRoom());
		
		System.out.println("\n");
	}

	public static void testGetCheckInDate()
	{
		DawsonCustomer customer = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		DawsonRoom room = new DawsonRoom(301, RoomType.NORMAL);

		DawsonReservation rsvp1 = new DawsonReservation(customer, room, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer, room, 2016, 4, 28, 2016, 5, 16);
		
		testGetCheckInDate("Case 1 - Valid - 2016-05-24", rsvp1);
		testGetCheckInDate("Case 2 - Valid - 2016-04-28", rsvp2);
	}
	
	public static void testGetCheckInDate(String testCase, DawsonReservation rsvp)
	{
		System.out.println(testCase);
		System.out.println("This is the check in date: " + rsvp.getCheckInDate());
		
		System.out.println("\n");
	}
	
	public static void testGetCheckOutDate()
	{
		DawsonCustomer customer = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		DawsonRoom room = new DawsonRoom(301, RoomType.NORMAL);

		DawsonReservation rsvp1 = new DawsonReservation(customer, room, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer, room, 2016, 4, 28, 2016, 5, 16);
		
		testGetCheckOutDate("Case 1 - Valid - 2016-07-25", rsvp1);
		testGetCheckOutDate("Case 2 - Valid - 2016-05-16", rsvp2);
	}
	
	public static void testGetCheckOutDate(String testCase, DawsonReservation rsvp)
	{
		System.out.println(testCase);
		System.out.println("This is the check out date: " + rsvp.getCheckOutDate());
		
		System.out.println("\n");
	}
	
	public static void testGetNumberDays()
	{
		DawsonCustomer customer = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		DawsonRoom room = new DawsonRoom(301, RoomType.NORMAL);

		DawsonReservation rsvp1 = new DawsonReservation(customer, room, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer, room, 2016, 4, 28, 2016, 5, 16);
		
		testGetNumberDays("Case 1 - Valid - checkOutDate-checkInDate", rsvp1);
		testGetNumberDays("Case 2 - Valid - checkOutDate-checkInDate", rsvp2);
	}
	
	public static void testGetNumberDays(String testCase, DawsonReservation rsvp)
	{
		System.out.println(testCase);
		System.out.println("Numbers of days of the stay: " + rsvp.getNumberDays());
		
		System.out.println("\n");
	}
	
	public static void testHashCode()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		DawsonReservation rsvp1 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp3 = new DawsonReservation(customer2, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp4 = new DawsonReservation(customer1, room2, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp5 = new DawsonReservation(customer1, room1, 2016, 4, 28, 2016, 7, 25);
		DawsonReservation rsvp6 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 6, 16);

		testHashCode("Case 1 - Valid - Same object; same hashCode", rsvp1, rsvp1);
		testHashCode("Case 2 - Valid - Different object, same information; same hashCode", rsvp1, rsvp2);
		testHashCode("Case 3 - Valid - Different object, only different customer; different hashCode", rsvp1, rsvp3);
		testHashCode("Case 4 - Valid - Different object, only different room; different hashCode", rsvp1, rsvp4);
		testHashCode("Case 5 - Valid - Different object, only different checkInDate; different hashCode", rsvp1, rsvp5);
		testHashCode("Case 6 - Valid - Different object, only different checkOutDate; different hashCode", rsvp1, rsvp6);
	}
	
	public static void testHashCode(String testCase, DawsonReservation rsvp1, DawsonReservation rsvp2)
	{
		System.out.println(testCase);
		System.out.print("Reservation #1: " + rsvp1);
		System.out.print("\t Reservation #2: " + rsvp2);
		
		if(rsvp1.hashCode() == rsvp2.hashCode())
		{
			System.out.print("\nHash codes for both reservations are the same.");
			System.out.print("Hash code room 1: " + rsvp1.hashCode());
			System.out.print("\t \t Hash code room 2: " + rsvp2.hashCode());
		}
		else
		{
			System.out.print("\nHash codes for both reservations are different.");
			System.out.print("Hash code room1: " + rsvp1.hashCode());
			System.out.print("\t \t Hash code room 2: " + rsvp2.hashCode());
		}
		
		System.out.println("\n");	
	}
	
	public static void testOverlap()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		DawsonReservation rsvp1 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer2, room2, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp3 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp4 = new DawsonReservation(customer1, room1, 2016, 6, 24, 2016, 7, 25);
		
		
		testOverlap("Case 1 - Valid - Different rooms, same dates; returns false", rsvp1, rsvp2);
		testOverlap("Case 2 - Valid - Same rooms, same dates; returns true", rsvp1, rsvp3);
		testOverlap("Case 3 - Valid - Reservation 2 overlaps reservation 1; returns true", rsvp3, rsvp4);
		testOverlap("Case 4 - Valid - Reservation 1 overlaps reservation 2; returns true", rsvp4, rsvp3);
	}
	
	public static void testOverlap(String testCase, DawsonReservation rsvp1, DawsonReservation rsvp2)
	{
		System.out.println(testCase);
		
		if(rsvp1.overlap(rsvp2))
		{}
		
		System.out.println("\n");
	}
	
	public static void testTheEightParameterConstructor()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		testTheEightParameterConstructor("Case 1 - Valid", customer1, room1, 2016, 6, 24, 2016, 7, 25, true);
		testTheEightParameterConstructor("Case 2 - Valid", customer2, room2, 2016, 10, 31, 2016, 11, 11, true);
		
		testTheEightParameterConstructor("Case 3 - Invalid - inMonth < 1", customer1, room2, 2016, 0, 31, 2016, 11, 11, false);
		testTheEightParameterConstructor("Case 4 - Invalid - inMonth > 12", customer1, room1, 2016, 13, 31, 2016, 11, 11, false);
		testTheEightParameterConstructor("Case 5 - Invalid - inDay < 1", customer2, room2, 2016, 11, 0, 2016, 11, 11, false);
		testTheEightParameterConstructor("Case 6 - Invalid - inDay > 31", customer1, room1, 2016, 11, 32, 2016, 11, 11, false);
		
		testTheEightParameterConstructor("Case 7 - Invalid - outMonth < 1", customer1, room2, 2016, 10, 31, 2016, 0, 11, false);
		testTheEightParameterConstructor("Case 8 - Invalid - outMonth > 12", customer2, room1, 2016, 11, 30, 2016, 23, 11, false);
		testTheEightParameterConstructor("Case 9 - Invalid - outDay < 1", customer1, room2, 2016, 11, 11, 2016, 11, 0, false);
		testTheEightParameterConstructor("Case 10 - Invalid - outDay > 31", customer2, room2, 2016, 11, 32, 2016, 11, 45, false);
		
		testTheEightParameterConstructor("Case 11 - Invalid - checkOut is before the checkIn", customer1, room2, 2016, 12, 31, 2016, 11, 11, false);
		
	}
	
	public static void testTheEightParameterConstructor(String testCase, Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay, 
			int outYear, int outMonth, int outDay, boolean expectedValid)
	{
		System.out.println(testCase);
		
		try
		{
			DawsonReservation rsvp = new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay);
			System.out.print("A new reservation was created: " + rsvp);
			
			if(!expectedValid)
			{
				System.out.print("\t Error. Expected INVALID. FAIL");
			}
		}
		catch(IllegalArgumentException iae)
		{
			System.out.print("Printing exception message: " + iae.getMessage());
			
			if(expectedValid)
			{
				System.out.print("\t Error. Expected VALID. FAIL");
			}
		}
		catch(DateTimeException e)
		{
			System.out.print("Date time exception: " + e.getMessage());
			if(expectedValid)
			{
				System.out.print("\t Error. Expected VALID. FAIL");
			}
			
		}
		System.out.println("\n");
	}
	
	public static void testToString()
	{
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		DawsonReservation rsvp1 = new DawsonReservation(customer1, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation rsvp2 = new DawsonReservation(customer2, room2, 2016, 10, 31, 2016, 11, 01);

		testToString("Case 1 - Valid", rsvp1);
		testToString("Case 2 - Valid", rsvp2);
	}
	
	public static void testToString(String testCase, DawsonReservation rsvp1)
	{
		System.out.println(testCase);
		
		System.out.print("Format: email*inYear*inMonth*inDay*outYear*outMonth*outDay*roomNumber");
		System.out.print("\n" + rsvp1.toString());
		
		System.out.println("\n");
	}
	
	
}
