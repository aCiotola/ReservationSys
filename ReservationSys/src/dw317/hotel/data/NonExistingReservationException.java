/**
 * 
 */
package dw317.hotel.data;

/**
 * The thrown Exception if the Reservation does not exits.
 * 
 * @author Alessandro Ciotola
 * @version 10/11/2016
 * @since 1.8
 *
 */
public class NonExistingReservationException extends Exception
{
	private static final long  serialVersionUID = 42031768871L;

	/**
	 * Throws the "Cannot find any Reservation Exception"
	 */
	public NonExistingReservationException()
	{
		super("Cannot find any Reservation matches.");
	}

	/**
	 * takes the given message.
	 * 
	 * @param message The message that will be shown when the NonExistingReservationException is thrown
	 */
	public NonExistingReservationException(String message) 
	{
		super(message);
	}
}
