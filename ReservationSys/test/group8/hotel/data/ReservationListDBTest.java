/**
 * 
 */
package group8.hotel.data;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;

import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;

import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.creditcard.CreditCard;
import group8.hotel.business.DawsonCustomer;
import group8.hotel.business.DawsonHotelFactory;
import group8.hotel.business.DawsonReservation;
import group8.hotel.business.DawsonRoom;
import group8.util.ListUtilities;

/**
 * @author Phi Dai Nguyen
 *
 */
public class ReservationListDBTest {
	public static void main(String[] args) throws DuplicateCustomerException, NonExistingReservationException {
		testOneParameterReservationConstructor();
		testTwoParameterReservationConstructor();

		testReservationAdd();
		testReservationDisconnect();
		testGetReservations();
		testCancel();
		testGetReservationRooms();
		testGetFreeRooms();
		testGetFreeRoomsWithType();
		testClearAllPast();
	}

	private static void testOneParameterReservationConstructor() {
		System.out.println("\nTesting the one parameter constructor.");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		testOneParameterReservationConstructor("Case 1: Valid", listPersistenceObject);
		testOneParameterReservationConstructor("Case 2: null listPersistenceObject", null);
		teardown();
	}

	public static void testOneParameterReservationConstructor(String testCase, ListPersistenceObject list) {
		System.out.println("\t" + testCase);
		try {
			ReservationListDB rDB = new ReservationListDB(list);
			System.out.print("\tThe ReservationListDB instance was created:\n\t" + rDB);
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid list value. " + iae.getMessage());
		} catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();
	}

	private static void testTwoParameterReservationConstructor() {
		System.out.println("\nTesting the two parameter constructor.");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		testTwoParameterReservationConstructor("Case 1: Valid", listPersistenceObject, DawsonHotelFactory.DAWSON);
		testTwoParameterReservationConstructor("Case 2: null listPersistenceObject", null, DawsonHotelFactory.DAWSON);
		testTwoParameterReservationConstructor("Case 3: null DawsonHotelFactory", listPersistenceObject, null);
		teardown();
	}

	public static void testTwoParameterReservationConstructor(String testCase, ListPersistenceObject list,
			HotelFactory factory) {
		System.out.println("\t" + testCase);
		try {
			ReservationListDB rDB = new ReservationListDB(list, factory);
			System.out.print("\tThe CustomerListDB instance was created:\n\t" + rDB);
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid list value. " + iae.getMessage());
		} catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();
	}

	private static void testReservationAdd() throws DuplicateCustomerException {
		System.out.println("\nTesting the Add method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

		DawsonRoom room1 = new DawsonRoom(103, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(402, RoomType.PENTHOUSE);

		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "00000000@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "NNNNNNNNNNNN@gmail.com");
		DawsonCustomer customer3 = new DawsonCustomer("Sam", "Nicholas", "NNNNNNNNNNNN@gmail.com");

		DawsonReservation reservation1 = new DawsonReservation(customer1, room1, 2020, 5, 1, 2020, 5, 15);
		DawsonReservation reservation2 = new DawsonReservation(customer2, room2, 2020, 1, 1, 2020, 2, 1);
		DawsonReservation reservation3 = new DawsonReservation(customer3, room2, 2020, 1, 1, 2020, 2, 1);

		testReservationAdd("Case 1: Valid - Adding 2 different Reservations", reservation1, reservation2,
				listPersistenceObject);
		testReservationAdd("Case 2: Invalid - Adding the same Reservation", reservation2, reservation3,
				listPersistenceObject);
		testReservationAdd("Case 3: Invalid - null reservation1", null, reservation2, listPersistenceObject);
		testReservationAdd("Case 4: Invalid - null reservation2", reservation1, null, listPersistenceObject);
		teardown();
	}

	public static void testReservationAdd(String testcase, DawsonReservation res1, DawsonReservation res2,
			ListPersistenceObject listPersistenceObject) throws DuplicateCustomerException {
		System.out.println("\n" + testcase);

		try {
			ReservationListDB rDB = new ReservationListDB(listPersistenceObject, DawsonHotelFactory.DAWSON);
			rDB.add(res1);
			rDB.add(res2);
			System.out.print("\tThe ReservationListDB instance was created:\n\t" + rDB);
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid list value. " + iae.getMessage());
		} catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();

	}

	private static void testReservationDisconnect() {
		System.out.println("\nTesting the Disconnect method");
		setup();
		DawsonCustomer customer1 = new DawsonCustomer("Samuel", "Mark", "MMMMMMMMMMMMMMM@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Zed", "Nicholas", "ZZZZZZZZZZZZZZZZ@gmail.com");

		DawsonRoom room1 = new DawsonRoom(103, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(302, RoomType.SUITE);

		DawsonReservation reservation1 = new DawsonReservation(customer1, room1, 2020, 1, 1, 2020, 1, 2);
		DawsonReservation reservation2 = new DawsonReservation(customer2, room2, 2020, 5, 1, 2020, 5, 2);
		testReservationDisconnect("Case 1 - Valid Reservation", reservation1);
		testReservationDisconnect("Case 2 - Valid Reservation", reservation2);
		testReservationDisconnect("Case 3 - Invalid: Null Reservation ", null);
		teardown();
	}

	public static void testReservationDisconnect(String testCase, DawsonReservation res) {
		System.out.println("\t" + testCase);
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB rDB = new ReservationListDB(listPersistenceObject, DawsonHotelFactory.DAWSON);

		try {
			rDB.add(res);
			System.out.println("\tReservation added.");
			System.out.println(rDB);
			rDB.disconnect();
			System.out.print("\tReservation disconnected.");
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid value. " + iae.getMessage());
		} catch (DuplicateReservationException dre) {
			System.out.print("\tError! There is a duplicate Reservation. " + dre.getMessage());
		} catch (IOException io) {
			System.out.print("\tError! Problem disconnecting. " + io.getMessage());
		} catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();
	}

	private static void testGetReservations() {
		System.out.println("\nTesting the getReservations method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB rDB = new ReservationListDB(listPersistenceObject);

		DawsonCustomer customer1 = new DawsonCustomer("Derek", "McAwesome", "mcawesome.derek@456.com");
		DawsonCustomer customer2 = new DawsonCustomer("Raj", "Wong", "raj@aing.ru");
		DawsonCustomer customer3 = new DawsonCustomer("Bob", "Sum", "Bobby234@outlook.com");

		testGetReservations("Case 1 - Valid: Search for Derek McAwesome", customer1, rDB);
		testGetReservations("Case 2 - Valid: Search for Raj Wong", customer2, rDB);
		testGetReservations("Case 3 - Valid: Search for non existing Bob Sum", customer3, rDB);
		testGetReservations("Case 4 - Invalid: Search for null", null, rDB);
		teardown();
	}

	public static void testGetReservations(String testCase, Customer customer, ReservationListDB rDB) {
		System.out.println("\t" + testCase);

		try {

			rDB.getReservations(customer);
			System.out.println("\tReservation received.");

		} catch (NullPointerException npe) {
			System.out.print("\tError! There is a null list value. " + npe.getMessage());
			
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid value. " + iae.getMessage());
		}
		catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();

	}

	private static void testCancel() throws NonExistingReservationException {
		System.out.println("\nTesting the cancel method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

		ReservationListDB rDB = new ReservationListDB(listPersistenceObject);

		DawsonCustomer customer1 = new DawsonCustomer("Huey", "Freeman", "huey@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Zed", "Nicholas", "ZZZZZZZZZZZZZZZZ@gmail.com");

		DawsonRoom room1 = new DawsonRoom(702, RoomType.SUITE);
		DawsonRoom room2 = new DawsonRoom(302, RoomType.SUITE);

		DawsonReservation reservation1 = new DawsonReservation(customer1, room1, 2016, 6, 21, 2016, 6, 29);
		DawsonReservation reservation2 = new DawsonReservation(customer2, room2, 2020, 5, 1, 2020, 5, 2);

		testCancel("\nCase 1 : Valid remove", reservation1, rDB);
		testCancel("\nCase 2 : InValid remove (Non existing reservation)", reservation2, rDB);
		testCancel("\nCase 3 : Invalid remove (null)", null, rDB);
		teardown();
	}

	public static void testCancel(String testCase, DawsonReservation res, ReservationListDB rDB)
			throws NonExistingReservationException {
		System.out.println("\t" + testCase);

		try {
			rDB.cancel(res);
			System.out.println("\nCancelled Successfully!");
		} catch (NullPointerException npe) {
			System.out.println("\tError! There is a null list value. " + npe.getMessage());
		} catch (IllegalArgumentException iae) {
			System.out.println("\tError! This is an invalid list value." + iae.getMessage());
		} catch (NonExistingReservationException non) {
			System.out.println("\tError! The reservation does not exist!" + non.getMessage());
		}

	}

	private static void testGetReservationRooms() {
		System.out.println("\nTesting the getReservationRooms method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB rDB = new ReservationListDB(listPersistenceObject);

		LocalDate checkIn1 = LocalDate.of(2017, 1, 8);
		LocalDate checkOut1 = LocalDate.of(2017, 1, 19);

		testGetReservationRooms("Case 1 - Valid (Reserved Room)", checkIn1, checkOut1, rDB);
		testGetReservationRooms("Case 2 - Invalid (Null checkIn)", null, checkOut1, rDB);
		testGetReservationRooms("Case 3 - Invalid (Null checkOut)", checkIn1, null, rDB);
		
		teardown();
	}

	public static void testGetReservationRooms(String testCase, LocalDate checkIn, LocalDate checkOut,
			ReservationListDB rDB) {
		System.out.println("\t" + testCase);

		try {
			rDB.getReservedRooms(checkIn, checkOut);
			System.out.println("There is a reserved room!");
		} catch (IllegalArgumentException iae) {
			System.out.println("\tError! Has to be a date!" + iae.getMessage());
		} catch (DateTimeException dte) {
			System.out.println("\tInvalid dates!" + dte.getMessage());
		} catch (NullPointerException npe) {
			System.out.println("\tError! There is a null value!");
		}
	}

	private static void testGetFreeRooms() {
		System.out.println("\nTesting the getFreeRooms method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB rDB = new ReservationListDB(listPersistenceObject);

		LocalDate checkIn1 = LocalDate.of(2020, 1, 8);
		LocalDate checkOut1 = LocalDate.of(2020, 1, 19);

		LocalDate checkIn2 = LocalDate.of(2016, 9, 10);
		LocalDate checkOut2 = LocalDate.of(2016, 9, 15);

		testGetFreeRooms("Case 1 - Valid (Free Room)", checkIn1, checkOut1, rDB);
		testGetFreeRooms("Case 2 - Valid (Not a Free Room", checkIn2, checkOut2, rDB);
		testGetFreeRooms("Case 3 - Invalid (Null checkIn)", null, checkOut1, rDB);
		testGetFreeRooms("Case 4 - Invalid (Null checkOut)", checkIn1, null, rDB);
		testGetFreeRooms("case 5 - Invalid (both nulls)", null, null, rDB);
		teardown();
	}

	public static void testGetFreeRooms(String testCase, LocalDate checkIn, LocalDate checkOut, ReservationListDB rDB) {
		System.out.println("\t" + testCase);

		try {
			rDB.getFreeRooms(checkIn, checkOut);
			System.out.println("There is a free room!");
		} catch (IllegalArgumentException iae) {
			System.out.println("\tError! Has to be a date!" + iae.getMessage());
		} catch (DateTimeException dte) {
			System.out.println("\tInvalid dates!" + dte.getMessage());
		} catch (NullPointerException npe) {
			System.out.println("\tError! There is a null value!");
		}
	}

	private static void testGetFreeRoomsWithType() {
		System.out.println("\nTesting the getFreeRooms method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB rDB = new ReservationListDB(listPersistenceObject);

		LocalDate checkIn1 = LocalDate.of(2020, 1, 8);
		LocalDate checkOut1 = LocalDate.of(2020, 1, 19);

		LocalDate checkIn2 = LocalDate.of(2016, 9, 10);
		LocalDate checkOut2 = LocalDate.of(2016, 9, 15);
		RoomType type = RoomType.NORMAL;
		RoomType type2 = RoomType.SUITE;

		testGetFreeRoomsWithType("Case 1 - Valid (Free Room + type)", checkIn1, checkOut1, type, rDB);
		testGetFreeRoomsWithType("Case 2 - Valid (No Free Room + type)", checkIn2, checkOut2, type, rDB);
		testGetFreeRoomsWithType("Case 3 - Invalid (Null checkIn)", null, checkOut1, type, rDB);
		testGetFreeRoomsWithType("Case 4 - Invalid (Null checkOut)", checkIn1, null, type, rDB);
		testGetFreeRoomsWithType("case 5 - Invalid (3 nulls)", null, null, null, rDB);
		teardown();

	}

	public static void testGetFreeRoomsWithType(String testCase, LocalDate checkIn, LocalDate checkOut, RoomType type,
			ReservationListDB rDB) {
		System.out.println("\t" + testCase);

		try {
			rDB.getFreeRooms(checkIn, checkOut, type);
			System.out.println("There is a free room!");
		} catch (IllegalArgumentException iae) {
			System.out.println("\tError! Has to be a date!" + iae.getMessage());
		} catch (DateTimeException dte) {
			System.out.println("\tInvalid dates!" + dte.getMessage());
		} catch (NullPointerException npe) {
			System.out.println("\tError! There is a null value!" + npe.getMessage());
		}
	}

	private static void testClearAllPast() {
		System.out.println("\nTesting the clearAllPast method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB rDB = new ReservationListDB(listPersistenceObject);

		
		testClearAllPast("Case 1 - Valid Clear", rDB);
		teardown();
	}

	public static void testClearAllPast(String testCase, ReservationListDB rDB)
	{
		System.out.println("\t" + testCase);
		try
		{
			rDB.clearAllPast();
			System.out.println("Cleared reservations whose checkout date is before the current date!");
			System.out.println(rDB);
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}

	private static void setup() {
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "301*suite";
		rooms[3] = "401*penthouse";

		String[] custs = new String[8];
		custs[0] = "mcawesome.derek@456.com*Derek*McAwesome*visa*4556681351886004";
		custs[1] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs[2] = "tremgin4@alloqc.ca*Ginette*Tremblay*visa*4532913138654408";
		custs[3] = "chrissytocool404@hitmall.com*Chrissy*Adams*mastercard*5209371001459604";
		custs[4] = "buildco@house.com*Bob*Boulder*amex*375415349547759";
		custs[5] = "alonso@mclaren.com*Fernando*Alonso*visa*4556054416014030";
		custs[6] = "sandra@mycompany.com*Sandra*Bilcos*mastercard*5432977945459309";
		custs[7] = "flowerpower2@peace.net*Rose*Hilton*amex*344931899529540";

		String[] reservs = new String[8];
		reservs[0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs[1] = "alonso@mclaren.com*2016*9*13*2016*9*27*101";
		reservs[2] = "sandra@mycompany.com*2017*11*1*2017*11*11*206";
		reservs[3] = "tremgin4@alloqc.ca*2016*12*1*2016*12*26*304";
		reservs[4] = "buildco@house.com*2017*2*13*2017*2*15*601";
		reservs[5] = "chrissytocool404@hitmall.com*2016*10*13*2016*10*27*701";
		reservs[6] = "flowerpower2@peace.net*2016*10*11*2016*11*2*704";
		reservs[7] = "mcawesome.derek@456.com*2017*1*8*2017*1*19*801";

		File dir = new File("testfiles");
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(rooms, "testfiles/testRooms.txt");
			ListUtilities.saveListToTextFile(custs, "testfiles/testCustomers.txt");
			ListUtilities.saveListToTextFile(reservs, "testfiles/testReservations.txt");
		} catch (IOException io) {
			System.out.println("Error creating file in setUp()");
		}
	}

	private static void teardown() {
		File theFile = new File("testfiles/testRooms.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testCustomers.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testReservations.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
	}
}
