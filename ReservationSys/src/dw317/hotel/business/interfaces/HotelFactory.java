package dw317.hotel.business.interfaces;

import java.io.Serializable;

import dw317.lib.creditcard.CreditCard;

/**
 * 
 * @author
 * @version 27/09/2016
 * @since 1.8
 *
 */
public interface HotelFactory extends Serializable {
	/**
	 * Method which creates the Customer using the first and last name and the
	 * email.
	 * 
	 * @param firstName
	 *            First name of the Customer.
	 * @param lastName
	 *            last name of the Customer.
	 * @param email
	 *            the Email Address of the Customer.
	 * @return The customer.
	 */
	Customer getCustomerInstance(String firstName, String lastName, String email);

	/**
	 * Gets the CreditCard of the Customer.
	 * 
	 * @param cardtype
	 *            The type of CreditCard.
	 * @param number
	 *            The CreditCard Number.
	 * @return The CreditCard.
	 */
	CreditCard getCard(String cardtype, String number);

	/**
	 * Method which creates the Room using the roomNumber and roomType.
	 * 
	 * @param roomNumber
	 *            The number of the room which the Customer will be staying at.
	 * @param roomtype
	 *            The type of room the Customer will be staying at.
	 * @return The Room.
	 */
	Room getRoomInstance(int roomNumber, String roomtype);

	/**
	 * Method which creates the Reservation using the Customer, Room, check in
	 * date and check out date.
	 * 
	 * @param aCustomer
	 *            The Customer that is getting the Reservation.
	 * @param aRoom
	 *            The Room that the customer will be staying in.
	 * @param inYear
	 *            The year the Customer checks in.
	 * @param inMonth
	 *            The month the Customer check in.
	 * @param inDay
	 *            The day the Customer checks in.
	 * @param outYear
	 *            The year the Customer checks out.
	 * @param outMonth
	 *            The month the Customer check out.
	 * @param outDay
	 *            The day the Customer checks out.
	 * @return The Reservation.
	 */
	Reservation getReservationInstance(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay);

	/**
	 * Method which copies the Reservation of a Customer.
	 * 
	 * @param toCopy
	 *            The Reservation onject to be copied.
	 * @return The copy of the Reservation.
	 */
	Reservation getReservationInstance(Reservation toCopy);
}
