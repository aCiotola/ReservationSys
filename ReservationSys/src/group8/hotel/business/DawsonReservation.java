/**
 * 
 */
package group8.hotel.business;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import dw317.hotel.business.interfaces.*;

/**
 * Creates a Reservation for a customer and displays their email, check in time,
 * check out time and room number.
 * 
 * @author Hannah Ly
 * @version 27/09/2016
 * @since 1.8
 */
public class DawsonReservation implements Reservation {
	private final Customer customer;
	private final Room room;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;

	private static final long serialVersionUID = 42031768871L;

	/**
	 * 8 parameter constructor that takes a Customer, Room check in and check
	 * out time.
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
	 * @throws IllegalArgumentException
	 *             if date is invalid or check in is after the check out date.
	 */
	public DawsonReservation(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) {
		this.customer = aCustomer;
		this.room = aRoom;

		LocalDate checkInDateTemp = LocalDate.of(inYear, inMonth, inDay);
		LocalDate checkOutDateTemp = LocalDate.of(outYear, outMonth, outDay);

		if (inDay > 31 || inDay < 1)
			throw new DateTimeException("The date is invalid");
		else if (outDay > 31 || outDay < 1)
			throw new DateTimeException("The date is invalid");
		else if (inMonth > 12 || inMonth < 1)
			throw new DateTimeException("The month is invalid");
		else if (outMonth > 12 || outMonth < 1)
			throw new DateTimeException("The month is invalid");
		else if (checkInDateTemp.isAfter(checkOutDateTemp))
			throw new IllegalArgumentException("Check in date must be before check out date!");
		else {
			this.checkInDate = LocalDate.of(inYear, inMonth, inDay);
			this.checkOutDate = LocalDate.of(outYear, outMonth, outDay);
		}
	}

	/**
	 * compareTo method that compares 2 Reservation objects.
	 * 
	 * @param o
	 *            The Reservation object.
	 * @return The number representing the comparison
	 */
	public int compareTo(Reservation o) {
		if (this.room.getRoomNumber() < o.getRoom().getRoomNumber())
			return -1;
		else if (this.room.getRoomNumber() == o.getRoom().getRoomNumber()) {
			if (this.checkInDate.isBefore(o.getCheckInDate()))
				return -1;
			else if (this.checkInDate.isEqual(o.getCheckInDate()))
				return 0;
			else
				return 1;
		} else
			return 1;
	}

	/**
	 * Method that checks if 2 reservations are the same
	 * 
	 * @param obj
	 *            The object that will be compared.
	 * @return true/false if the objects are equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		DawsonReservation other = (DawsonReservation) obj;
		if (checkInDate == null) {
			if (other.checkInDate != null)
				return false;
		} else if (!checkInDate.equals(other.checkInDate))
			return false;

		if (checkOutDate == null) {
			if (other.checkOutDate != null)
				return false;
		} else if (!checkOutDate.equals(other.checkOutDate))
			return false;

		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;

		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;

		return true;
	}

	/**
	 * Method which gets the check in date.
	 * 
	 * @return the check in date.
	 */
	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	/**
	 * Method which gets the check out date.
	 * 
	 * @return the check out date.
	 */
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * Method which gets the customer.
	 * 
	 * @return The Customer being created.
	 */
	public Customer getCustomer() {
		String firstName = this.customer.getName().getFirstName();
		String lastName = this.customer.getName().getLastName();
		String email = this.customer.getEmail().getAddress();

		DawsonCustomer customer = new DawsonCustomer(firstName, lastName, email);
		return customer;
	}

	/**
	 * Method which gets the number of days spent.
	 * 
	 * @return The number of days.
	 */
	public int getNumberDays() {
		int numberOfDays;
		numberOfDays = (int) this.checkInDate.until(this.checkOutDate, ChronoUnit.DAYS);
		return numberOfDays;
	}

	/**
	 * Method which gets the Room that the Customer will be staying in.
	 * 
	 * @return The room that the Customer will be staying in.
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * The hashcode value of the object.
	 * 
	 * @return The hashCode value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((checkInDate == null) ? 0 : checkInDate.hashCode());
		result = prime * result + ((checkOutDate == null) ? 0 : checkOutDate.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());

		return result;
	}

	/**
	 * Method which checks if a Room is currently taken. Checks if Reservation
	 * dates overlap.
	 * 
	 * @return true/false whether dates overlap or not.
	 */
	@Override
	public boolean overlap(Reservation other) {
		if (this.room.getRoomNumber() == other.getRoom().getRoomNumber()) {
			if (this.checkInDate.isAfter(other.getCheckOutDate())) {
				System.out.println("\t y Reservations are overlaping.");
				return true;
			} else if (!this.checkOutDate.isBefore(other.getCheckInDate())) {
				System.out.println("\t x Reservations are overlaping.");
				return true;
			} else {
				System.out.println("\t z Reservations are not overlaping.");
				return false;
			}
		} else {
			System.out.println("\t v Reservations are not overlaping.");
			return false;
		}
	}

	/**
	 * Method which returns a String representation of the Reservation.
	 * 
	 * @return The toString format of the Customer Reservation
	 */
	@Override
	public String toString() {
		return this.customer.getEmail().getAddress() + "*" + this.checkInDate.getYear() + "*"
				+ this.checkInDate.getMonthValue() + "*" + this.checkInDate.getDayOfMonth() + "*"
				+ this.checkOutDate.getYear() + "*" + this.checkOutDate.getMonthValue() + "*"
				+ this.checkOutDate.getDayOfMonth() + "*" + this.room.getRoomNumber();
	}
}
