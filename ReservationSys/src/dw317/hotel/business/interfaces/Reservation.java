/**
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Reservation interface which contains the methods required creating a
 * reservation.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public interface Reservation extends Comparable<Reservation>, Serializable {
	/**
	 * Method which gets the Customer.
	 * 
	 * @return the Customer
	 */
	public Customer getCustomer();

	/**
	 * Method which gets the Room for a Customer.
	 * 
	 * @return The room.
	 */
	public Room getRoom();

	/**
	 * Method which gets the date that the Customer checked in.
	 * 
	 * @return The checking date for the customer
	 */
	public LocalDate getCheckInDate();

	/**
	 * Method which gets the date the Customer checks out.
	 * 
	 * @return The checkout date for the Customer.
	 */
	public LocalDate getCheckOutDate();

	/**
	 * Method which gets the number of days a Customer stayed.
	 * 
	 * @return The number of days a customer stayed.
	 */
	public int getNumberDays();

	/**
	 * checks whether the room being reserved between the two reservation is the
	 * same and if so, whether the reservation dates overlap.
	 * 
	 * @param other
	 *            The other customer's reservation.
	 * @return true or false if the room is already reserved.
	 */
	public boolean overlap(Reservation other);
}
