/**
 * 
 */
package dw317.hotel.data;

/**
 * The thrown Exception if the Room is currently reserved.
 * 
 * @author Alessandro Ciotola
 * @version 10/11/2016
 * @since 1.8
 *
 */
public class DuplicateReservationException extends Exception
{
	private static final long  serialVersionUID = 42031768871L;

	/**
	 * Throws the "Room is currently reserved" Exception.
	 */
	public DuplicateReservationException()
	{
		super("This room currently reserved for another reservation.");
	}

	/**
	 * takes the given message.
	 * 
	 * @param message The message that will be shown when the DuplicateReservationException is thrown.
	 */
	public DuplicateReservationException(String message) 
	{
		super(message);
	}
}
