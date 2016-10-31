package group8.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import group8.util.ListUtilities;

public class RoomListDBTest {

	public static void main(String[] args) {
		testGetRooms();
		testToString();
	}
	
	private static void setup()
	{
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "301*suite";
		rooms[3] = "401*penthouse";
		
		String[] custs = new String[8];
		custs [0] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs [1] = "joe.mancini@mail.me*Joe*Mancini**";
		//...
		custs [7] = "d@zzz.com*Da*Ja*amex*347964972957716";


		String[] reservs = new String[8];
		reservs [0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs [1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		//...
		reservs [7] = "d@zzz.com*2016*10*12*2016*10*15*102";

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

	private static void testGetRooms() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		RoomListDB db = new RoomListDB(file);
		
		System.out.println("Normal rooms:");
		List<Room> normal = db.getRooms(RoomType.NORMAL);
		for (Room r : normal)
			System.out.println(r.toString());			

		System.out.println("Suites:");
		List<Room> suite = db.getRooms(RoomType.SUITE);
		for (Room r : suite)
			System.out.println(r.toString());
		
		System.out.println("Penthouse rooms:");
		List<Room> ph = db.getRooms(RoomType.PENTHOUSE);
		for (Room r : ph)
			System.out.println(r.toString());
		
		teardown();
	}

	private static void testToString(){
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		RoomListDB db = new RoomListDB(file);
		
		System.out.println(db.toString());

		teardown();
	}

}
