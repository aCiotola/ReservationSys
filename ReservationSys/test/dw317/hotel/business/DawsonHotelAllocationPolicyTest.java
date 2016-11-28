package dw317.hotel.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.DawsonHotelAllocationPolicy;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group8.hotel.data.ReservationListDB;
import group8.hotel.data.SequentialTextFileList;
import group8.util.ListUtilities;

public class DawsonHotelAllocationPolicyTest {
	
	public static void main (String[] args){
		
		testGetAvailableRoom();
	}
	
	private static void testGetAvailableRoom(){
		System.out.println("\tTesting the getAvailableRoom method");
		setup();
		
		ListPersistenceObject listPersistenceObject = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB rDB = new ReservationListDB(listPersistenceObject);
		DawsonHotelAllocationPolicy dhlp = new DawsonHotelAllocationPolicy(rDB);
		
		LocalDate checkIn1 = LocalDate.of(2020, 1, 1);
		LocalDate checkOut1 = LocalDate.of(2020, 1, 20);
		
		LocalDate checkIn2 = LocalDate.of(2016, 9, 10);
		LocalDate checkOut2 = LocalDate.of(2016, 9, 15);
		
		LocalDate checkIn3 = LocalDate.of(2017, 1, 8);
		LocalDate checkOut3 = LocalDate.of(2017, 1, 19);
		
		RoomType type1 = RoomType.NORMAL;
		RoomType type2 = RoomType.SUITE;
		RoomType type3 = RoomType.PENTHOUSE;
		
		
		testGetAvailableRoom("Case 1 : Valid method",checkIn1, checkOut1,type1,dhlp);
		testGetAvailableRoom("Case 2 : Valid method",checkIn2,checkOut2,type1,dhlp);
		testGetAvailableRoom("Case 3 : Valid method",checkIn1,checkOut1,type2,dhlp);
		testGetAvailableRoom("Case 4 : Valid method",checkIn1,checkOut1,type3,dhlp);
		testGetAvailableRoom("Case 5 : null checkIn/checkOut",null,null,type1,dhlp);
		testGetAvailableRoom("Case 6 : null room type",checkIn1,checkOut1,null,dhlp);
		testGetAvailableRoom("Case 7 : null DawsonHotelAllocationPolicy",checkIn1,checkIn2,type2,null);
		testGetAvailableRoom("Case 8 : No avaiable rooms",checkIn3,checkOut3,type3,dhlp); // IndexOutOfBounds
		
		teardown();
		
	}
	private static void testGetAvailableRoom(String testCase, LocalDate checkIn, LocalDate checkOut, RoomType type, DawsonHotelAllocationPolicy dhlp){
		System.out.println("\t\n" + testCase + "\n");
		
		try{
		
		Optional<Room> availableRooms = dhlp.getAvailableRoom(checkIn,checkOut,type);
		System.out.println("Available rooms are: " + availableRooms);
		}
		catch (NullPointerException npe){
			System.out.println("\tError! Null value! " + npe.getMessage());
		}
		catch (Exception e){
			System.out.print("\tError! " + e.getClass() + " " + e.getMessage());
		}
	}
	
	private static void setup() {
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
