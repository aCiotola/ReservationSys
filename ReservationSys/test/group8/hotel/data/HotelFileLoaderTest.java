package group8.hotel.data;

import java.io.IOException;

import dw317.hotel.business.interfaces.*;

public class HotelFileLoaderTest {

	public static void main (String[] args){
		testingGetRoomListFromSequentialFile();
		testingGetCustomerListFromSequentialFile();
		testingGetReservationListFromSequentialFile();
	}
	
	public static void testingGetRoomListFromSequentialFile(){
		System.out.println("\nTesting the getRoomListFromSequentialFile method\n");
		testingGetRoomListFromSequentialFile("\tCase 1 : Valid room.txt", "rooms.txt");
		testingGetRoomListFromSequentialFile("\tCase 2 : Invalid room.txt (extra * in between roomtype)", "fakerooms2.txt");
		testingGetRoomListFromSequentialFile("\tCase 3 : Invalid room.txt (only one entry: room number and as well for roomtype)", 
											"fakerooms3.txt");
		testingGetRoomListFromSequentialFile("\tCase 4: Valid room.txt (Empty line between each room)","fakerooms4.txt");
		testingGetRoomListFromSequentialFile("\tCase 5: Valid room.txt (Too much *)","fakerooms5.txt");
		
		
				
	}
	
	public static void testingGetRoomListFromSequentialFile(String testCase, String fileName){
		System.out.println("\t" + testCase);
			try {
					Room[] room = HotelFileLoader.getRoomListFromSequentialFile("datafiles/unsorted/" + fileName);
		
					System.out.println("\tThe Room list has been instantiated\n");
					for (int i = 0; i < room.length; i++)
						System.out.println(room[i]);
					
			}
			catch (IllegalArgumentException iae){
				System.out.println("\t" + iae.getMessage());
				System.out.println();
			}
			catch (IOException io){
				System.out.println("\t" + io.getMessage());
				System.out.println();
			}
			catch (Exception e){
				System.out.println("Unexpected Exception!" + e.getClass() + "/n" + e.getMessage() );
			}
	}
	
	public static void testingGetCustomerListFromSequentialFile(){
		System.out.println("\tTesting the getCustomerListFromSequentialFile method\n");
		testingGetCustomerListFromSequentialFile("\tCase 1: Valid Customer.txt", "customers1.txt");
		testingGetCustomerListFromSequentialFile("\tCase 2: Invalid Customer.txt (too much *)","customers2.txt");
		testingGetCustomerListFromSequentialFile("\tCase 3 : Invalid Customer.txt (invalid email)","fakecustomers1.txt");
		testingGetCustomerListFromSequentialFile("\tCase 4 : Invalid Customer.txt (No first name)","fakecustomers2.txt");
		testingGetCustomerListFromSequentialFile("\tCase 5 : Invalid Customer.txt (No first/last name)","fakecustomers3.txt");
		testingGetCustomerListFromSequentialFile("\tCase 6 : Valid Customer.txt (Empty space in between customer)","fakecustomers4.txt");
	}
	
	public static void testingGetCustomerListFromSequentialFile(String testCase, String fileName){
		System.out.println("\t" + testCase);
		try {
				Customer[] customer = HotelFileLoader.getCustomerListFromSequentialFile("datafiles/unsorted/" + fileName);
				
				System.out.println("\tThe Customer list has been instantiated\n");
				for (int i = 0; i < customer.length; i++)
					System.out.println(customer[i]);
				
		}
		catch (IllegalArgumentException iae){
			System.out.println("\t" + iae.getMessage());
			System.out.println();
		}
		catch (IOException io){
			System.out.println("\t" + io.getMessage());
			System.out.println();
		}
		catch (Exception e){
			System.out.println("Unexpected Exception!" + e.getClass() + "/n" + e.getMessage() ); 
		}
	}
	
	public static void testingGetReservationListFromSequentialFile(){
		System.out.println("\tTesting the getReservationListFromSequentialFile method\n");
		testingGetReservationListFromSequentialFile("\tCase 1: Valid Reservation.txt", "reservations1.txt","customers1.txt","rooms.txt");
		testingGetReservationListFromSequentialFile("\tCase 2: Invalid Reservation.txt + invalid customer","reservations2.txt","fakecustomers1","rooms.txt");
		testingGetReservationListFromSequentialFile("\tCase 3: Valid Reservation.txt (Empty line between reservations","fakereservations1.txt","customers1.txt","rooms.txt");
		testingGetReservationListFromSequentialFile("\tCase 4: Invalid Reservation.txt (Too much *)","fakereservations2.txt","reservations1.txt","rooms.txt");
	}
	
	public static void testingGetReservationListFromSequentialFile(String testCase, String fileName, String customerFileName, String roomFileName){
		System.out.println("\t" + testCase + "\n");
		
		try {
			Customer[] customer = HotelFileLoader.getCustomerListFromSequentialFile("datafiles/unsorted/" + customerFileName);
			Room[] room = HotelFileLoader.getRoomListFromSequentialFile("datafiles/unsorted/" + roomFileName);
			Reservation[] reservation = HotelFileLoader.getReservationListFromSequentialFile
							("datafiles/unsorted/" + fileName, customer, room);
			System.out.println("\tThe Reservation list has been instantiated\n");
				for (int i = 0; i < reservation.length; i++)
					System.out.println(reservation[i]);
		}
		catch (IllegalArgumentException iae){
			System.out.println("\t" + iae.getMessage());
			System.out.println();
		}
		catch (IOException io){
			System.out.println("\t" + io.getMessage());
			System.out.println();
		}
		catch (Exception e){
			System.out.println("Unexception Exception!" + e.getClass() + "/n" + e.getMessage());
		}
	}
}
