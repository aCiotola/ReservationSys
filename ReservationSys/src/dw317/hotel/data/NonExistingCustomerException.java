package dw317.hotel.data;

/**
 * The thrown Exception if the Customer does not exits.
 * 
 * @author Alessandro Ciotola
 * @version 10/11/2016
 * @since 1.8
 *
 */
public class NonExistingCustomerException extends Exception
{
	private static final long  serialVersionUID = 42031768871L;

	/**
	 * Throws the "Cannot find any Customer Exception"
	 */
	public NonExistingCustomerException()
	{
		super("Cannot find any Customer matches.");
	}

	/**
	 * takes the given message.
	 * 
	 * @param message The message that will be shown when the NonExistingCustomerException is thrown
	 */
	public NonExistingCustomerException(String message) 
	{
		super(message);
	}
}
