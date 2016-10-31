package group8.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

/** 
 * A class that implements the Comparator<Reservation> and will
 * compare two Reservations based on their checkout date.
 * 
 * @author Alessandro Ciotola
 * @version 28/10/2016
 * @since 1.8
 *
 */
public class ReservationByCheckOutSorter implements Comparator<Reservation>
{
	/**
	 * The compare method which compares 2 Reservations based on their check out date
	 * 
	 * @param res1 The first Reservation to be compared
	 * @param res2 The second Reservation to be compared
	 * 
	 * @return The number representing the comparison
	 */
	@Override
	public int compare(Reservation res1, Reservation res2)
	{
		if (res1.equals(res2))
			return 0;
		if (!res1.getCheckOutDate().equals(res2.getCheckOutDate()))
			return res1.getCheckOutDate().compareTo(res2.getCheckOutDate());
		return res1.compareTo(res2);
	}
}
