/**
 * 
 */
package group8.hotel.data;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import group8.hotel.business.DawsonCustomer;
import group8.hotel.business.DawsonHotelFactory;
import group8.util.ListUtilities;

/**
 * @author Alessandro Ciotola
 *
 */
public class CustomerListDBTest 
{
	public static void main(String[] args) throws DuplicateCustomerException 
	{
		testOneParameterConstructor();
		testTwoParameterConstructor();
		testAdd();
		testDisconnect();
		testGetCustomer();
		testUpdate();
	}

	private static void testOneParameterConstructor()
	{
		System.out.println("\nTesting the one parameter constructor.");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		testOneParameterConstructor("Case 1: Valid", listPersistenceObject);
		testOneParameterConstructor("Case 2: null listPersistenceObject", null);
		teardown();
	}

	public static void testOneParameterConstructor(String testCase, ListPersistenceObject list)
	{
		System.out.println("\t" + testCase);
		try {
			CustomerListDB customerDB = new CustomerListDB(list);
			System.out.print("\tThe PatientListDB instance was created:\n\t" + customerDB);
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid list value. " + iae.getMessage());
		} catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();
	}

	private static void testTwoParameterConstructor() 
	{
		System.out.println("\nTesting the two parameter constructor.");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		testTwoParameterConstructor("Case 1: Valid", listPersistenceObject, DawsonHotelFactory.DAWSON);
		testTwoParameterConstructor("Case 2: null listPersistenceObject", null, DawsonHotelFactory.DAWSON);
		testTwoParameterConstructor("Case 3: null DawsonHotelFactory", listPersistenceObject, null);
		teardown();
	}

	public static void testTwoParameterConstructor(String testCase, ListPersistenceObject list, HotelFactory factory)
	{
		System.out.println("\t" + testCase);
		try {
			CustomerListDB customerDB = new CustomerListDB(list, factory);
			System.out.print("\tThe CustomerListDB instance was created:\n\t" + customerDB);
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid list value. " + iae.getMessage());
		} catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();
	}

	private static void testAdd() throws DuplicateCustomerException
	{
		System.out.println("\nTesting the Add method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		DawsonCustomer customer1 = new DawsonCustomer("John", "Smith", "00000000@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Sam", "Nicholas", "NNNNNNNNNNNN@gmail.com");
		DawsonCustomer customer3 = new DawsonCustomer("Sam", "Nicholas", "NNNNNNNNNNNN@gmail.com");
		
		testAdd("Case 1: Valid - Adding 2 different Customers", customer1, customer2, listPersistenceObject);
		testAdd("Case 2: Invalid - Adding the same Customers", customer2, customer3, listPersistenceObject);
		testAdd("Case 3: Invalid - null customer1", null, customer2, listPersistenceObject);
		testAdd("Case 4: Invalid - null customer2", customer1, null, listPersistenceObject);
		teardown();
	}

	private static void testAdd(String testCase, DawsonCustomer cust1, DawsonCustomer cust2, ListPersistenceObject listPersistenceObject)
			throws DuplicateCustomerException 
	{
		try 
		{
			CustomerListDB cDB = new CustomerListDB(listPersistenceObject, DawsonHotelFactory.DAWSON);
			cDB.add(cust1);
			cDB.add(cust2);
			System.out.print("\tThe CustomerListDB instance was created:\n\t" + cDB);
		} catch (IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid list value. " + iae.getMessage());
		} catch (Exception e) {
			System.out.print("\tError!" + e.getClass() + " " + e.getMessage());
		}
		System.out.println();
	}

	private static void testDisconnect() 
	{
		System.out.println("\nTesting the Disconnect method");
		setup();
		DawsonCustomer customer1 = new DawsonCustomer("Samuel", "Mark", "MMMMMMMMMMMMMMM@outlook.com");
		DawsonCustomer customer2 = new DawsonCustomer("Zed", "Nicholas", "ZZZZZZZZZZZZZZZZ@gmail.com");
		testDisconnect("Case 1 - Valid Customer", customer1);
		testDisconnect("Case 2 - Valid Customer", customer2);
		testDisconnect("Case 3 - Invalid: Null Customer ", null);
		teardown();
	}
	
	public static void testDisconnect(String testCase, DawsonCustomer cust)
	{
		System.out.println("\t" + testCase);
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		CustomerListDB cDB = new CustomerListDB(listPersistenceObject, DawsonHotelFactory.DAWSON);
		
		try {
			cDB.add(cust);
			System.out.println("\tCustomer added.");
			System.out.println(cDB);
			cDB.disconnect();
			System.out.print("\tCustomer disconnected.");
		}
		catch(IllegalArgumentException iae) {
			System.out.print("\tError! There is an invalid value. "+iae.getMessage());
		}
		catch(DuplicateCustomerException dpe) {
			System.out.print("\tError! There is a duplicate Customer. "+dpe.getMessage());
		}
		catch(IOException io) {
			System.out.print("\tError! Problem disconnecting. "+io.getMessage());
		}
		catch(Exception e) {
			System.out.print("\tError!" +e.getClass()+" "+ e.getMessage() );
		}
		System.out.println();
	}


	private static void testGetCustomer() 
	{
		System.out.println("\nTesting the getCustomer method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		CustomerListDB cDB = new CustomerListDB(listPersistenceObject);
		testGetCustomer("Case 1 - Valid: Search for tremgin4@alloqc.ca", "tremgin4@alloqc.ca", cDB);
		testGetCustomer("Case 2 - Valid: Search for buildco@house.com", "buildco@house.com", cDB);
		testGetCustomer("Case 3 - Valid: Search for non existing mistake@gmail.com", "mistake@gmail.com", cDB);
		testGetCustomer("Case 4 - Invalid: Search for null", null, cDB);
		teardown();
		
	}

	public static void testGetCustomer(String testCase, String email, CustomerListDB custDB) 
	{
		System.out.println("\t" + testCase);
		try 
		{
			Email e = new Email(email);
			custDB.getCustomer(e);
			System.out.println("\tCustomer received.");
		}
		catch(NullPointerException npe)
		{
			System.out.print("\tError! There is a null list value. "+npe.getMessage());
		}
		catch(IllegalArgumentException iae)
		{
			System.out.print("\tError! There is an invalid value. "+iae.getMessage());
		}
		catch(NonExistingCustomerException non)
		{
			System.out.println("Error! Customer does not exist. " + non.getMessage());
		}
		catch(Exception e)
		{
			System.out.print("\tError!" +e.getClass()+" "+ e.getMessage() );
		}
		System.out.println();
	}
	
	private static void testUpdate() 
	{
		System.out.println("\nTesting the Update method");
		setup();
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		CustomerListDB cDB = new CustomerListDB(listPersistenceObject);
		
		
		testUpdate("Case 1 - Valid: VISA, 4556681351886004", "mcawesome.derek@456.com", CardType.VISA , "4556681351886004", cDB);
		testUpdate("Case 2 - Valid: VISA, 4556681351886004", "chrissytocool404@hitmall.com", CardType.MASTERCARD , 
				"5209371001459604", cDB);
		testUpdate("Case 3 - Invalid: null Email", null, CardType.MASTERCARD, "5209371001459604", cDB);
		testUpdate("Case 4 - Invalid: null card number", "sandra@mycompany.com", CardType.VISA , null, cDB);
		testUpdate("Case 5 - Invalid: null card type", "sandra@mycompany.com", null, "4556681351886004", cDB);
		testUpdate("Case 6 - Invalid: null database", "sandra@mycompany.com", CardType.VISA, "4556681351886004", null);
		teardown();
	}
	
	public static void testUpdate(String testCase, String email, CardType cType, String number, CustomerListDB list) 
	{
		System.out.println("\t" + testCase);
		System.out.println("\tUpdating the database...");
		
		try{			
			CreditCard card = CreditCard.getInstance(cType, number);
			list.update(new Email(email), card);
			System.out.println("\t"+list);
		}
		catch(NonExistingCustomerException non)
		{
			System.out.println("\tError: Customer does not exist. " + non.getMessage());
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("\tError: Invalid value for update. " + iae.getMessage());
		}
		catch(Exception e)
		{
			System.out.print("\tError!" +e.getClass()+" "+ e.getMessage() );
		}
		System.out.println();
	}
	
	
	private static void setup()
	{
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "301*suite";
		rooms[3] = "401*penthouse";
		
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
