package group8.hotel.data;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.util.List;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group8.util.Utility;

/**
 * A class that that implements the ListPersistenceObject interface 
 * and interacts with object serialized files. 
 * ObjectSerializedList allow the hotel system to retrieve/save 
 * the database from/to object serialized files.
 * 
 * @author Alessandro Ciotola
 * @version 11/28/2016
 * @since 1.8
 *
 */
public class ObjectSerializedList implements ListPersistenceObject
{ 	
	private final String roomFileName;
	private final String customerFileName;
	private final String reservationFileName;
	
	/**
	 * A three parameter constructor that sets the Room, Customer and Reservation filename.
	 * 
	 * @param roomFileName The name of the Room text file
	 * @param customerFileName The name of the Customer text file.
	 * @param reservationFileName The name of the Reservation text file
	 */
	public ObjectSerializedList(String roomFileName, String customerFileName, String reservationFileName)
	{
		this.roomFileName = roomFileName;
		this.customerFileName = customerFileName;
		this.reservationFileName = reservationFileName;
	}

	/**
	 * A method which gets and deserializes the Customer database.
	 * 
	 * @return The Customer database.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerDatabase() 
	{
		List<Customer> customers = null;
		try
		{
			customers = (List<Customer>)Utility.deserializeObject(customerFileName);
		}
		catch(IOException|ClassNotFoundException e)
		{
			System.out.println("An error occured when attempting to grab the database: " + e.getMessage());
		}
		return customers;
	}

	/**
	 * A method which gets and deserializes the Reservation database.
	 * 
	 * @return The Reservation database.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservationDatabase()
	{
		List<Reservation> reservations = null;
		try
		{
			reservations = (List<Reservation>)Utility.deserializeObject(reservationFileName);
		}
		catch(IOException|ClassNotFoundException e)
		{
			System.out.println("An error occured when attempting to grab the database: " + e.getMessage());
		}
		return reservations;
	}

	/**
	 * A method which gets and deserializes the Room database.
	 * 
	 * @return The Room database.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomDatabase()
	{
		List<Room> rooms = null;
		try
		{
			rooms = (List<Room>)Utility.deserializeObject(roomFileName);
		}
		catch(IOException|ClassNotFoundException e)
		{
			System.out.println("An error occured when attempting to grab the database: " + e.getMessage());
		}
		return rooms;
	}
	
	/**
	 * A method which saves and Serializes the Customer database.
	 * 
	 * @param custs The Customer list.
	 * @throws IOException
	 */
	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException 
	{
		try
		{
			Utility.serializeObject(custs, customerFileName);
		}
		catch(InvalidClassException|NotSerializableException e)
		{
			System.out.println("An error occured when attempting to save the database: " + e.getMessage());
		}
	}

	/**
	 * A method which saves and Serializes the Reservation database.
	 * 
	 * @param reservs The Reservation list.
	 * @throws IOException
	 */
	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException
	{
		try
		{
			Utility.serializeObject(reservs, reservationFileName);
		}
		catch(InvalidClassException|NotSerializableException e)
		{
			System.out.println("An error occured when attempting to save the database: " + e.getMessage());
		}
	}
}
