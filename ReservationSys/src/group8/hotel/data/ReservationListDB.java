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
 * 
 * @author Phi Dai Nguyen
 * @version 11.13.2016
 */
public class ReservationListDB implements ReservationDAO {
	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	public ReservationListDB(ListPersistenceObject listPersistenceObject) {
		this.allRooms = listPersistenceObject.getRoomDatabase();
		this.database = listPersistenceObject.getReservationDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
	}

	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.allRooms = listPersistenceObject.getRoomDatabase();
		this.database = listPersistenceObject.getReservationDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
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

		for (int i = 0; i < database.size(); i++){
				if (reserv.overlap(database.get(i)))
						throw new DuplicateReservationException("This room is already reserved!");
		}
		
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
			throw new NullPointerException("Databse is empty");
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

		List<Reservation> reservation = new ArrayList<Reservation>();
		
		for (int i = 0; i < database.size(); i++){
			if (database.get(i).getCustomer().getName().equals(cust.getName()))
				reservation.add(database.get(i));
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
		
		//

		for (int i = 0; i < database.size(); i++){			
				if (database.get(i).getCheckInDate().isBefore(checkout))
					if (database.get(i).getCheckOutDate().isAfter(checkin))
						reserved.add(database.get(i).getRoom());
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
		List<Room> free = new ArrayList<Room>(allRooms); 
		List<Room> reserved = getReservedRooms(checkin,checkout);
		for (int i = 0; i < reserved.size(); i++)
		{
			for (int j = 0; j < free.size(); j++)
			{
				if (free.get(j).getRoomNumber() == (reserved.get(i).getRoomNumber()))
				{
					free.remove(free.get(j));
					j--;
				}
			}			
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
		List<Room> freeRooms = getFreeRooms(checkin,checkout);
		
		for (int i = 0; i < freeRooms.size(); i++)
		{
			if (freeRooms.get(i).getRoomType().equals(roomType))
				freeWithType.add(freeRooms.get(i));
		}
		return freeWithType;
		
	}

	/**
	 * Removes all reservations with the checkout date prior to the current
	 * date.
	 */
	@Override
	public void clearAllPast() {

		for (int i = 0; i < database.size(); i++){
			if (database.get(i).getCheckOutDate().isBefore(LocalDate.now())){
				database.remove(i);
				i--;
			}
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
