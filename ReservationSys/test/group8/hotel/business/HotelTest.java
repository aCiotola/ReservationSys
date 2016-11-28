/**
 * 
 */
package group8.hotel.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.*;
import dw317.lib.Email;
import dw317.lib.creditcard.*;
import group8.hotel.data.*;
import group8.hotel.data.SequentialTextFileList;
import group8.util.ListUtilities;

/**
 * @author Hannah Ly
 * @version 27/11/2016
 * @since 1.8
 */
public class HotelTest 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		testTheFourParameterConstructor();
		testCancelReservation();
		testCloseHotel();
		testCreateReservation();
		testFindCustomer();
		testFindReservations();
		testRegisterCustomer();
		testUpdateCreditCard();
	}
	
	public static void testTheFourParameterConstructor()
	{
		System.out.println("\nTesting the four parameter constructor.");
		setup();
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		testTheFourParameterConstructor("Case 1 - Valid", factory, customers, reservations);
		testTheFourParameterConstructor("Case 2 - Invalid", null, customers, reservations);
		testTheFourParameterConstructor("Case 3 - Invalid", factory, null, reservations);
		testTheFourParameterConstructor("Case 4 - Invalid", factory, customers, null);
		
		teardown();
	}
	
	public static void testTheFourParameterConstructor(String testCase, HotelFactory factory, CustomerDAO customers, ReservationDAO reservations)
	{
		System.out.print("\n" + testCase);
		
		try
		{	
			Hotel hotel = new Hotel(factory, customers, reservations);
			System.out.println("\nHotel class instantiation successful");
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("\nError! There is an invalid list value. " + iae.getMessage());
		}
	}
	
	public static void testCancelReservation()
	{
		System.out.println("\nTesting the cancelReservation method.");
		setup();
		
		Customer cust1 = new DawsonCustomer("Rose","Hilton","flowerpower2@peace.net");
		Customer cust2 = new DawsonCustomer("Bob", "Chris", "bobChris@hotmail.ca");
		Room room1 = new DawsonRoom(704, RoomType.SUITE);
		
		Reservation rsvp1 = new DawsonReservation(cust1, room1, 2016, 10, 11, 2016, 11, 2);
		Reservation rsvp2 = new DawsonReservation(cust2, room1, 2016, 10, 11, 2016, 11, 2);
		
		testCancelReservation("Case 1 - Valid", rsvp1);
		testCancelReservation("case 2 - Invalid: no reservation", null);
		testCancelReservation("Case 3 - Invalid: fake reservation", rsvp2);
		
		teardown();
	}
	
	public static void testCancelReservation(String testCase, Reservation reservation)
	{
		System.out.print("\n" + testCase);
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		try
		{
			Hotel hotel = new Hotel(factory, customers, reservations);
			
			hotel.cancelReservation(reservation);
			
			System.out.println("\n Reservation was cancelled successfully.");
		}
		catch(NonExistingReservationException nere)
		{
			System.out.println("\nError! No matching reservation found. " + nere.getMessage());
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("\nError! There is an invalid value. " + iae.getMessage());
		}
	}
	
	public static void testCloseHotel()
	{
		System.out.println("\nTesting the closeHotel method.");
		setup();
		
		testCloseHotel("Case 1 - Valid");
		
		teardown();
	}
	
	public static void testCloseHotel(String testCase)
	{
		System.out.print("\n" + testCase);
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		try
		{
			Hotel hotel = new Hotel(factory, customers, reservations);
			hotel.registerCustomer("bob", "bob", "bob@gmail.com");
			hotel.closeHotel();
			System.out.println("\n Successfully closed the hotel.");
			
			factory = DawsonHotelFactory.DAWSON;
			customers = new CustomerListDB(listPersistenceObject);
			reservations = new ReservationListDB(listPersistenceObject);
			hotel = new Hotel(factory, customers, reservations);
			System.out.print("\n Reopening hotel.");
			System.out.println("\n Customer just added found in hotel " + hotel.findCustomer("bob@gmail.com"));
			
		}
		catch(IOException ioe)
		{
			System.out.println("\n Unexpected error while closing the hotel. " + ioe.getMessage());
		} 
		catch (DuplicateCustomerException dce) 
		{
			System.out.println("\n Error! Customer already exist. " + dce.getMessage());
		} 
		catch (NonExistingCustomerException nece) 
		{
			System.out.println("\n Error! No matching customer found. " + nece.getMessage());
		} 
	}
	
	public static void testCreateReservation()
	{
		System.out.println("\nTesting the createReservation method.");
		setup();
		
		Customer cust1 = new DawsonCustomer("bob", "bob", "bob@gmail.com");
		Customer cust2 = new DawsonCustomer("Hi", "hello", "hi_@hello.com");
		
		LocalDate checkin1 = LocalDate.of(2016, 06, 17);
		LocalDate checkout1 = LocalDate.of(2016, 06, 25);
		
		LocalDate checkin3 = LocalDate.of(2016, 10, 11);
		LocalDate checkout3 = LocalDate.of(2016, 11, 02);
		
		LocalDate checkin2 = LocalDate.of(2016, 01, 25);
		LocalDate checkout2 = LocalDate.of(2016, 01, 30);
		
		LocalDate checkin4 = LocalDate.of(2017, 01, 8);
		LocalDate checkout4 = LocalDate.of(2017, 01, 19);
		
		testCreateReservation("Case 1 - Valid", cust1, checkin1, checkout1, RoomType.NORMAL);
		testCreateReservation("Case 2 - Valid", cust2, checkin3, checkout3, RoomType.SUITE);
		testCreateReservation("Case 3 - Valid", cust1, checkin2, checkout2, RoomType.PENTHOUSE);
		
		teardown();
	}
	
	public static void testCreateReservation(String testCase, Customer customer, LocalDate checkin, LocalDate checkout, RoomType roomType)
	{
		System.out.print("\n" + testCase);
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		try
		{
			Hotel hotel = new Hotel(factory, customers, reservations);
			hotel.createReservation(customer, checkin, checkout, roomType);
		}
		catch(IndexOutOfBoundsException iob)
		{
			System.out.println("\n There is no available room for the dates provided.");
		}
	}
	
	public static void testFindCustomer()
	{
		System.out.println("\nTesting the findCustomer method.");
		setup();
		
		testFindCustomer("Case 1 - Valid", "alonso@mclaren.com");
		testFindCustomer("Case 2 - Invalid: null email", null);
		testFindCustomer("Case 3 - Invalid: fake customer", "Iamfake@hehe.xd");
		
		teardown();
	}
	
	public static void testFindCustomer(String testCase, String email)
	{
		System.out.print("\n" + testCase);
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		try
		{
			Hotel hotel = new Hotel(factory, customers, reservations);
			
			Customer cust = hotel.findCustomer(email);
			System.out.println("\n There was a match: " + cust);
		}
		catch(NonExistingCustomerException nece)
		{
			System.out.println("\n Error! No matching customer found. " + nece.getMessage());
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("\n Error! There is a invalid value. " + iae.getMessage());
		}
	}
	
	public static void testFindReservations()
	{
		System.out.println("\nTesting the findReservations method.");
		setup();
		
		Customer cust1 = new DawsonCustomer("Rose","Hilton","flowerpower2@peace.net");
		Customer cust2 = new DawsonCustomer("Bob", "Chris", "bobChris@hotmail.ca");
		Room room1 = new DawsonRoom(704, RoomType.SUITE);
		
		testFindReservations("Case 1 - Valid: loaded list", cust1);
		testFindReservations("Case 2 - Valid: fake customer, empty list", cust2);
		testFindReservations("Case 3 - Invalid: null customer", null);
		
		teardown();
	}
	
	public static void testFindReservations(String testCase, Customer customer)
	{
		System.out.print("\n" + testCase);
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		try
		{
			Hotel hotel = new Hotel(factory, customers, reservations);

			List<Reservation> rsvp = new ArrayList<Reservation>();
			
			rsvp = hotel.findReservations(customer);
			
			System.out.println("\n" + rsvp);
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("\n Error! There is a invalid value. " + iae.getMessage());
		}
	}
	
	public static void testRegisterCustomer()
	{
		System.out.println("\nTesting the registerCustomer method.");
		setup();
		
		testRegisterCustomer("Case 1 - Valid", "Jay", "El", "ElJay@gmail.com");
		testRegisterCustomer("Case 2 - Invalid: customer already exist", "Raj", "Wong", "raj@aing.ru");
		
		teardown();
	}
	
	public static void testRegisterCustomer(String testCase, String firstName, String lastName, String email)
	{
		System.out.print("\n" + testCase);
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		try
		{
			Hotel hotel = new Hotel(factory, customers, reservations);
			
			Customer cust = hotel.registerCustomer(firstName, lastName, email);
			
			System.out.println("\n Successfully registered the new customer: " + cust);
		}
		catch(DuplicateCustomerException dce)
		{
			System.out.println("\n Error! Customer already exist. " + dce.getMessage());
		}
	}
	
	public static void testUpdateCreditCard()
	{
		System.out.println("\nTesting the updateCreditCard method.");
		setup();
		
		testUpdateCreditCard("Case 1 - Valid", "raj@aing.ru", "AMEX", "371846941701077");
		testUpdateCreditCard("Case 2 - Invalid: fake customer", "fake@hehe.xd", "AMEX", "371846941701077");
		
		teardown();
	}
	
	public static void testUpdateCreditCard(String testCase, String email, String cardType, String cardnumber)
	{
		System.out.print("\n" + testCase);
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		
		HotelFactory factory = DawsonHotelFactory.DAWSON;
		CustomerDAO customers = new CustomerListDB(listPersistenceObject);
		ReservationDAO reservations = new ReservationListDB(listPersistenceObject);
		
		try
		{
			Hotel hotel = new Hotel(factory, customers, reservations);
			
			Customer cust = hotel.updateCreditCard(email, cardType, cardnumber);
			
			System.out.println("\n Customer's credit card was successfully updated: " + cust);
		}
		catch(NonExistingCustomerException nece)
		{
			System.out.println("\n Error! No matching customer found. " + nece.getMessage());
		}
	}
	
	private static void setup()
	{
		String[] rooms = new String[8];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "206*normal";
		rooms[3] = "304*normal";
		rooms[4] = "601*suite";
		rooms[5] = "701*suite";
		rooms[6] = "704*suite";
		rooms[7] = "801*penthouse";
		
		String[] custs = new String[8];
		custs [0] = "mcawesome.derek@456.com*Derek*McAwesome*visa*4556681351886004";
		custs [1] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs [2] = "tremgin4@alloqc.ca*Ginette*Tremblay*visa*4532913138654408";
		custs [3] = "chrissytocool404@hitmall.com*Chrissy*Adams*mastercard*5209371001459604";
		custs [4] = "buildco@house.com*Bob*Boulder*amex*375415349547759";
		custs [5] = "alonso@mclaren.com*Fernando*Alonso*visa*4556054416014030";
		custs [6] = "sandra@mycompany.com*Sandra*Bilcos*mastercard*5432977945459309";
		custs [7] = "flowerpower2@peace.net*Rose*Hilton*amex*344931899529540";

		String[] reservs = new String[8];
		reservs [0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs [1] = "alonso@mclaren.com*2016*9*13*2016*9*27*101";
		reservs [2] = "sandra@mycompany.com*2017*11*1*2017*11*11*206";
		reservs [3] = "tremgin4@alloqc.ca*2016*12*1*2016*12*26*304";
		reservs [4] = "buildco@house.com*2017*2*13*2017*2*15*601";
		reservs [5] = "chrissytocool404@hitmall.com*2016*10*13*2016*10*27*701";
		reservs [6] = "flowerpower2@peace.net*2016*10*11*2016*11*2*704";
		reservs [7] = "mcawesome.derek@456.com*2017*1*8*2017*1*19*801";

		File dir = new File("testfiles");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(rooms, 
					"testfiles/testRooms.txt");
			ListUtilities.saveListToTextFile(custs, 
					"testfiles/testCustomers.txt");
			ListUtilities.saveListToTextFile(reservs, 
					"testfiles/testReservations.txt");
		}
		catch(IOException io){
			System.out.println
			("Error creating file in setUp()");
		}
	}	
	
	private static void teardown() 
	{
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
