/**
 * 
 */
package dw317.hotel.data;

/**
 * @author Alessandro Ciotola
 *
 */
public class NonExistingCustomerException extends Exception
{
	private static final long  serialVersionUID = 42031768871L;

	public NonExistingCustomerException()
	{
		super("Cannot find any Customer matches.");
	}

	public NonExistingCustomerException(String message) 
	{
		super(message);
	}
}
