/**
 * 
 */
package dw317.hotel.data;

/**
 * @author Alessandro Ciotola
 *
 */
public class DuplicateReservationException extends Exception
{
	private static final long  serialVersionUID = 42031768871L;

	public DuplicateReservationException()
	{
		super("This room currently reserved for another reservation.");
	}

	public DuplicateReservationException(String message) 
	{
		super(message);
	}
}
