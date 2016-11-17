package group8.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.*;
import group8.hotel.business.DawsonHotelFactory;
import group8.hotel.business.DawsonReservation;

/**
 * Class which manages a Reservation list's database.
 * 
 * @author Phi Dai Nguyen
 * @version 11.13.2016
 * @since 1.8
 */
public class ReservationListDB implements ReservationDAO {
	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	public ReservationListDB(ListPersistenceObject listPersistenceObject) {
		this.database = listPersistenceObject.getReservationDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = null;
	}

	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.database = listPersistenceObject.getReservationDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
	}

	@Override
	public String toString() {
		int num = database.size();
		StringBuilder sb = new StringBuilder("Number of reservations in database: " + num);

		for (Reservation r : database) {
			sb.append("\n" + r.toString());
		}

		return sb.toString();

	}

	/**
	 * Adds a reservation to the database. If the reservation overlaps with an
	 * existing reservation, a DuplicationReservationException is thrown.
	 * Otherwise, the reservation is added based on room number and the check-in
	 * date.
	 * 
	 * @param reserv
	 *            The reservation to add to the database.
	 * @throws DuplicateReservationException
	 */
	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
		if (reserv == null)
			throw new IllegalArgumentException("Reservation cannot be null.");

		int index = binarySearch(database, reserv);
		if (index >= 0)
			throw new DuplicateReservationException();
		else {

			Reservation copyReserv = factory.getReservationInstance(reserv);
			database.add((-index - 1), copyReserv);
		}

	}

	/**
	 * Saves the reservations and disconnects from the database.
	 * 
	 * @throws IOException
	 *             Problems saving or disconnecting from database.
	 */
	@Override
	public void disconnect() throws IOException {
		if (database == null)
			throw new NullPointerException("Database is empty");
		else {
			listPersistenceObject.saveReservationDatabase(database);
			database = null;
		}

	}

	/**
	 * Returns the reservations for a given customer.
	 * 
	 * @param cust
	 *            The given customer.
	 * 
	 * @return The reservations for the customer (empty list if none).
	 */
	@Override
	public List<Reservation> getReservations(Customer cust) {
		if (cust == null)
			throw new IllegalArgumentException("Customer cannot be null");
		if (database == null)
			throw new NullPointerException("Database is empty");

		List<Reservation> reservation = new ArrayList<Reservation>(0);

		for (Reservation r : database) {
			Customer theCustomer = r.getCustomer();
			if (theCustomer.getName().equals(cust))
				reservation.add(r);
		}
		return reservation;
	}

	/**
	 * Cancels a reservation.
	 * 
	 * @param reserv
	 *            The given reservation
	 * @throws NonExistingReservationException
	 *             Could not find the reservation
	 */
	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		if (database.contains(reserv))
			database.remove(reserv);
		else
			throw new NonExistingReservationException();

	}

	/**
	 * Gets a list of all rooms reserved at some point between the supplied
	 * check-in and check-out dates. Note that rooms are reserved for any
	 * overlapping period, not necessarily the entirety of the period
	 * 
	 * @param checkin
	 *            Start date of period
	 * @param checkout
	 *            End date of the period
	 * @return list of all occupied rooms. Empty list is returned if no rooms
	 *         are occupied
	 */
	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {

		List<Room> reserved = new ArrayList<Room>();
		if (checkin.isAfter(checkout))
			return reserved;

		for (Reservation reservedRooms : database) {
			LocalDate checkInTemp = reservedRooms.getCheckInDate();
			LocalDate checkOutTemp = reservedRooms.getCheckOutDate();

			if (checkInTemp.isAfter(checkin) && checkOutTemp.isBefore(checkout))
				reserved.add(reservedRooms.getRoom());
			else
				System.out.println(" This is not a reserved room during the time period!" + reservedRooms);

		}
		return reserved;

	}

	/**
	 * Gets a list of all rooms NOT reserved at some point between the supplied
	 * check-in and check-out dates. Note that only rooms that are not reserved
	 * for any period between the given dates are returned
	 * 
	 * @param checkin
	 *            Start date of period
	 * @param checkout
	 *            End date of the period
	 * @return list of all unoccupied rooms. Empty list is returned if no rooms
	 *         are unoccupied
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		List<Room> free = new ArrayList<Room>(); // same as above but if it's
													// empty. (isEmpty)

		for (Reservation getFree : database) {
			LocalDate checkInTemp = getFree.getCheckInDate();
			LocalDate checkOutTemp = getFree.getCheckOutDate();

			if (checkInTemp.isAfter(checkout) == false && checkOutTemp.isBefore(checkin) == false)
				free.add(getFree.getRoom());
			else
				System.out.println(" This is not a free room during the time period!" + getFree);
		}
		return free;

	}

	/**
	 * Gets a list of all rooms of given room type NOT reserved between the
	 * supplied check-in and check-out dates. Note that only rooms that are not
	 * reserved for any period between the given dates are returned
	 * 
	 * @param checkin
	 *            Start date of period
	 * @param checkout
	 *            End date of the period
	 * @param roomType
	 *            The type of room requested
	 * @return list of all unoccupied rooms. Empty list is returned if no rooms
	 *         are unoccupied
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		// same as above but with a specific room type.
		List<Room> freeWithType = new ArrayList<Room>();
		;
		for (Reservation freeTypeRooms : database) {
			LocalDate checkInTemp = freeTypeRooms.getCheckInDate();
			LocalDate checkOutTemp = freeTypeRooms.getCheckOutDate();

			if (checkInTemp.isAfter(checkin) == false && checkOutTemp.isBefore(checkout) == false
					&& freeTypeRooms.getRoom().getRoomType() == roomType)
				freeWithType.add(freeTypeRooms.getRoom());
			else
				System.out.println(" There is no free room with that roomtype during this period!" + freeTypeRooms);// fix
		}
		return freeWithType;
	}

	/**
	 * Removes all reservations with the checkout date prior to the current
	 * date.
	 */
	@Override
	public void clearAllPast() {

		for (Reservation r : database) 
		{
			if (r.getCheckOutDate().isBefore(LocalDate.now()))
				database.remove(r);
		}
	}

	/**
	 * Searches through a list while searching for a matching reservation
	 * 
	 * @param list
	 *            The list to be searched
	 * @param value
	 *            The object that needs to be found
	 * @return The position of the found object
	 */
	private int binarySearch(List<Reservation> list, Reservation value) {
		int low = 0;
		int high = list.size() - 1;
		int mid = 0;
		int cmp = 0;

		while (low <= high) {
			mid = (low + high) / 2;
			Reservation midVal = list.get(mid);
			cmp = midVal.compareTo(value);

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return mid;
		}
		return -(low + 1);
	}
}
