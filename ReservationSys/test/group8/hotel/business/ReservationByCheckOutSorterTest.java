/**
 * 
 */
package group8.hotel.business;

import dw317.hotel.business.RoomType;
import group8.hotel.business.DawsonCustomer;
import group8.hotel.business.DawsonReservation;
import group8.hotel.business.DawsonRoom;
import group8.hotel.business.ReservationByCheckOutSorter;

/** 
 * 
 * @author Alessandro Ciotola
 * @version 28/10/2016
 * @since 1.8
 *
 */
public class ReservationByCheckOutSorterTest
{
	public static void main(String[] args) 
	{
		testCompare();
	}

	private static void testCompare()
	{
		//Comparing Checkout dates
		System.out.println("Testing the Compare method!");
		ReservationByCheckOutSorter chk = new ReservationByCheckOutSorter();
		
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "smith.john@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Bong", "sam98bong@outlook.com");
		
		DawsonRoom room1 = new DawsonRoom(301, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(703, RoomType.SUITE);
		
		DawsonReservation res1 = new DawsonReservation(customer2, room1, 2016, 5, 24, 2016, 7, 25);
		DawsonReservation res2 = new DawsonReservation(customer1, room2, 2016, 8, 26, 2016, 8, 30);
		DawsonReservation res3 = new DawsonReservation(customer1, room2, 2016, 8, 26, 2016, 8, 30);
		DawsonReservation res4 = new DawsonReservation(customer2, room2, 2016, 3, 12, 2016, 3, 18);
		DawsonReservation res5 = new DawsonReservation(customer1, room1, 2015, 2, 16, 2015, 2, 27);
		DawsonReservation res6 = new DawsonReservation(customer2, room2, 2016, 9, 22, 2016, 9, 29);
		
		System.out.println("Case 1 - Same Reservations (Same Customer, Same room, Same check in & check out time)");
		if (chk.compare(res2, res3) != 0)
			System.out.print("  Error! Values are expected to be equal. ==== FAILED TEST ====");
		else
			System.out.println("\tReservation 2 & Reservation 3 are the same");
		
		System.out.println("\nCase 2 - Different Customers, Same rooms, earlier check out date");
		if (chk.compare(res5, res1) >= 0)
			System.out.print("  Error! Values are expected to be less than 0. ==== FAILED TEST ====");
		else
			System.out.println("\tReservation 5's Check out date is before Reservation 1");
		
		System.out.println("\nCase 3 - Different Customer, Same rooms, later checkout date");
		if (chk.compare(res3, res4) <= 0)
			System.out.print("  Error! Values are expected to be greater than 0. ==== FAILED TEST ====");
		else
			System.out.println("\tReservation 3's checkout date is after Reservation 4");
		
		System.out.println("\nCase 4 - Same Customer, Different room, earlier check out date");
		if (chk.compare(res5, res2) >= 0)
			System.out.print("  Error! Values are expected to be less than 0. ==== FAILED TEST ====");
		else
			System.out.println("\tReservation 5's checkout date is before Reservation 2");
		
		System.out.println("\nCase 5 - Same, Customer, Same room, Different time, later check out date");
		if (chk.compare(res4, res6) >= 0)
			System.out.print("  Error! Values are expected to be less than 0. ==== FAILED TEST ====");
		else
			System.out.println("\tReservation 6's checkout date is before Reservation 4");
	}
}
