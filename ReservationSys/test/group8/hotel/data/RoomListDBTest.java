package group8.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group8.util.ListUtilities;
import group8.util.Utility;

public class RoomListDBTest {

	public static void main(String[] args) throws IOException {
		testGetRooms();
		testToString();
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
			
			Utility.serializeObject(rooms, "testfiles/testRooms.ser");
			Utility.serializeObject(custs, "testfiles/testCustomers.ser");
			Utility.serializeObject(reservs, "testfiles/testReservations.ser");
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

	private static void testGetRooms() throws IOException {
		setup();
		ObjectSerializedList list = new ObjectSerializedList
				("testfiles/testRooms.ser", "testfiles/testCustomers.ser",
						"testfiles/testReservations.ser");
		convertSeqToSer("testfiles/testRooms.txt", "testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		RoomListDB db = new RoomListDB(list);
		
		System.out.println("Normal rooms:");
		List<Room> normal = db.getRooms(RoomType.NORMAL);
		for (Room r : normal)
			System.out.println(r.toString());			

		System.out.println("\nSuites:");
		List<Room> suite = db.getRooms(RoomType.SUITE);
		for (Room r : suite)
			System.out.println(r.toString());
		
		System.out.println("\nPenthouse rooms:");
		List<Room> ph = db.getRooms(RoomType.PENTHOUSE);
		for (Room r : ph)
			System.out.println(r.toString());
		
		teardown();
	}

	private static void testToString() throws IOException{
		setup();
		ObjectSerializedList list = new ObjectSerializedList
				("testfiles/testRooms.ser", "testfiles/testCustomers.ser",
						"testfiles/testReservations.ser");
		convertSeqToSer("testfiles/testRooms.txt", "testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		RoomListDB db = new RoomListDB(list);
		
		System.out.println("\n" + db.toString());

		teardown();
	}
	
	public static void convertSeqToSer(String seqRooms, String seqCustomers, String seqReservations) throws IOException 
	{
		SequentialTextFileList textFile = new SequentialTextFileList(seqRooms, seqCustomers, seqReservations);

		List<Room> rooms = textFile.getRoomDatabase();
		Utility.serializeObject(rooms, "testfiles/testRooms.ser");
		
		List<Customer> customers = textFile.getCustomerDatabase();
		Utility.serializeObject(customers, "testfiles/testCustomers.ser");
		
		List<Reservation> reservations = textFile.getReservationDatabase();
		Utility.serializeObject(reservations, "testfiles/testReservations.ser");		
	}
}
