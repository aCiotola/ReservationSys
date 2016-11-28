package group8.hotel.data;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group8.util.Utility;

/**
 * An application which creates the initial .ser files from the files This app
 * loads the data from the sequential text files into a List and serializes them
 * to object serialized files.
 * 
 * @author Alessandro Ciotola
 * @version 11/28/2016
 * @since 1.8
 *
 */
public class SerializedFileLoaderApp 
{
	public static void main(String[] args) throws IOException
	{
		String roomFile = "datafiles/database/rooms.txt";
		String customerFile = "datafiles/database/customers.txt";
		String reservationFile = "datafiles/database/reservations.txt";
		
		try
		{
			SequentialTextFileList file = new SequentialTextFileList(roomFile, customerFile, reservationFile);
		
			List<Room> room = file.getRoomDatabase();
			Utility.serializeObject(room, "datafiles/database/rooms.ser");
		
			List<Customer> customer = file.getCustomerDatabase();
			Utility.serializeObject(customer, "datafiles/database/customers.ser");
		
			List<Reservation> reservation = file.getReservationDatabase();
			Utility.serializeObject(reservation, "datafiles/database/reservations.ser");
		}
		catch(InvalidClassException|NotSerializableException e)
		{
			System.out.println("An error occured when serializing the database: " + e.getMessage());
		}
	}
}
