/**
 * 
 */
package dw317.hotel.data;

/**
 * @author Alessandro Ciotola
 *
 */
public class NonExistingReservationException extends Exception
{
	private static final long  serialVersionUID = 42031768871L;

	public NonExistingReservationException()
	{
		super("Cannot find any Reservation matches.");
	}

	public NonExistingReservationException(String message) 
	{
		super(message);
	}
}
