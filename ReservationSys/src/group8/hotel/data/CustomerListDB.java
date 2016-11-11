
package group8.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.*;
import dw317.hotel.data.interfaces.*;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;
import group8.hotel.business.*;

/**
 * Class which manages a Customer list's database.
 * 
 * @author Hannah Ly 
 * @author Alessandro Ciotola
 * @version 11/11/2016
 * @since 1.8
 *
 */
public class CustomerListDB  implements CustomerDAO
{
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory ; 
		
	/**
	 * One parameter constructor which takes in a listPersistenceObject
	 * 
	 * @param listPersistenceObject An object that causes interactions with sequential files
	 */
	public CustomerListDB (ListPersistenceObject listPersistenceObject)
	{
		if(listPersistenceObject == null)
			throw new IllegalArgumentException("listPersistenceObject cannot be null");
		
		this.database = listPersistenceObject.getCustomerDatabase();
		this.listPersistenceObject = listPersistenceObject;
		factory = DawsonHotelFactory.DAWSON;
	}

	/**
	 * Two parameter contructor that takes in a listPersistenceObject and a HotelFactory
	 * 
	 * @param listPersistenceObject An object that causes interactions with sequential files
	 * @param factory Creates a Customer instance for the database.
	 */
	public CustomerListDB (ListPersistenceObject listPersistenceObject,
						HotelFactory factory)
	{
		if(listPersistenceObject == null || factory == null)
			throw new IllegalArgumentException("listPersistenceObject and the factory cannot be null");
		
		this.database = listPersistenceObject.getCustomerDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
	}
	
	/**
	 * Class that adds a customer to the database
	 * 
	 * @param cust The customer to be added
	 */
	@Override
	public void add(Customer cust) throws DuplicateCustomerException
	{
		if(cust == null)
			throw new IllegalArgumentException("Customer cannot be null.");
		
		int index = binarySearch(database, cust.getEmail());		
		if(index >= 0)
			throw new DuplicateCustomerException(cust + " already exist.");
		else 
		{
			Customer copyCustomer = factory.getCustomerInstance(cust.getName().getFirstName(), 
					cust.getName().getLastName(),cust.getEmail().toString());
			database.add((-index - 1),copyCustomer);
		}
	}
	
	/**
	 * Searches through a list while searching for a matching email
	 * 
	 * @param list The list to be searched
	 * @param key The object that needs to be found
	 * @return The position of the found object
	 */
	public static int binarySearch(List<Customer> list, Email key)
	{
		int low = 0;
		int mid = 0;
		int high = list.size() - 1;
		int result = 0;

		while(high >= low)
		{
			mid = (high + low)/2;
			result = list.get(mid).getEmail().compareTo(key);

			if (result > 0)
				high = mid - 1;
			else if(result < 0)
				low = mid + 1;
			else
				return mid;
		}
		return -(low + 1);
	}
	
	/**
	 * This method saves the database to disk and assign null to the database field.	 * 
	 */
	@Override
	public void disconnect() throws IOException
	{
		if (database == null)
			throw new IllegalArgumentException("The database cannot be null!");
		else
		{
			listPersistenceObject.saveCustomerDatabase(database);
			database = null;
		}
	}

	/**
	 * Returns a reference to the Customer with the given email address
	 * 
	 * @param email The Customer's email.
	 * 
	 * @return The reference to the Customer
	 */
	@Override
	public Customer getCustomer(Email email) throws NonExistingCustomerException 
	{	
		if(email == null)
			throw new IllegalArgumentException("Email cannot be null.");		
		if(database == null)
			throw new IllegalArgumentException("Database cannot be null.");
		
		int index = binarySearch(database, email);
		
		if(index >= 0)
			return database.get(index);
		else
			throw new NonExistingCustomerException("Cannot find a Customer match.");
	}
	
	/**
	 * Method that returns a String representation of the contents of the database.
	 * 
	 * @return the string representation of the database's contents.
	 */
	@Override
	public String toString()
	{
		int num = database.size();
		StringBuilder sb = new StringBuilder("Number of customers in database: " + num);
		
		for (Customer c : database)
		{
			sb.append("\n" + c.toString());
		}
		
		return sb.toString();
	}
	
	/**
	 * Updates a customer in the database if the customer exists
	 * 
	 * @param email The email of the Customer
	 * @param card The creditcard of the Customer
	 */
	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException
	{
		int index = binarySearch(database, email);
		if (index >= 0)
		{
			Customer customer = database.get(index);
			Customer copyCustomer = factory.getCustomerInstance(customer.getName().getFirstName(),
					customer.getName().getLastName(), email.toString());
			database.set(index, copyCustomer);   
		}
		else
			throw new NonExistingCustomerException("Customer does not exist!");
			
	}
}
