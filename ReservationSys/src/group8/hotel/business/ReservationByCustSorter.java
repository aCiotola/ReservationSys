package group8.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

/** 
 * A class that implements the Comparator<Reservation> and will
 * compare two Reservations based on their checkin date.
 * 
 * @author Alessandro Ciotola
 * @version 28/10/2016
 * @since 1.8
 *
 */
public class ReservationByCustSorter implements Comparator<Reservation> 
{
	/**
	 * The compare method which compares 2 Reservations based on their check in date
	 * 
	 * @param res1 The first Reservation to be compared
	 * @param res2 The second Reservation to be compared
	 * 
	 * @return The number representing the comparison
	 */
	@Override
	public int compare(Reservation r1, Reservation r2) 
	{
		if (r1.equals(r2))
			return 0;
		if (!r1.getCustomer().equals(r2.getCustomer()))
			return r1.getCustomer().compareTo(r2.getCustomer());
		//if same customer, order by check in (i.e., default)
		return r1.compareTo(r2);
	}
}
