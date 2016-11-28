/**
 * 
 */
package group8.hotel.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.util.*;

import javax.print.attribute.standard.PrinterLocation;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.*;
import dw317.lib.creditcard.AbstractCreditCard;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import group8.hotel.business.*;

/**
 * The HotelFileLoader class provides different load methods for the
 * Room,Customer and Reservation list.
 * 
 * @author Phi Dai Nguyen
 * @version 10/28/2016
 * 
 *
 */
public class HotelFileLoader {

	private HotelFileLoader() {
	}

	/**
	 * The getCustomerListFromSequentialFile method takes a file that contains a
	 * Customer and loads it.
	 * 
	 * @param filename
	 *            The file that contains the Customer information.
	 * @return customer The customer array that contains information about them
	 *         from the Customer file.
	 * @throws IOException
	 */
	public static Customer[] getCustomerListFromSequentialFile(String filename) throws IOException {
		Customer[] customer = new Customer[5]; // will check for size also
												// customer contains ?
		Scanner inputFile = null;
		String recordStr = null;

		try {
			BufferedReader b = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
			inputFile = new Scanner(b);
			String[] fields = null;
			int i = 0;

			// use hasNext to check for end of file.
			while (inputFile.hasNext()) {

				recordStr = inputFile.nextLine();

				fields = recordStr.split("\\*");

				try {

					if (recordStr.isEmpty())
						continue;
					customer[i] = new DawsonCustomer(fields[1], fields[2], fields[0]);

					Optional<CreditCard> creditCard;

					if (fields.length == 3 || fields[3].trim().isEmpty())
						creditCard = Optional.ofNullable(null);
					else
						creditCard = Optional.ofNullable(DawsonHotelFactory.DAWSON.getCard(fields[3], fields[4]));

					customer[i].setCreditCard(creditCard);
					i++;

					if (i >= customer.length)
						customer = Arrays.copyOf(customer, customer.length * 2 + 1);
				} catch (ArrayIndexOutOfBoundsException oob) {
					System.out.println("Record: " + recordStr + oob.getMessage());
				} catch (IllegalArgumentException iae) {
					System.out.println("Record: " + recordStr + " " + iae.getMessage());
				}

			}

			if (i < customer.length) {
				customer = Arrays.copyOf(customer, i);
			}
			return customer;

		} catch (IOException e) {
			System.out.println("Connection Error " + filename + e.getMessage());
			return new Customer[0];
		} finally {
			if (inputFile != null)
				inputFile.close();
		}

	}

	/**
	 * The getReservationListFromSequentialFile method takes a Reservation file
	 * and loads.
	 * 
	 * @param filename
	 *            The file that contains information about the person's
	 *            email,check in/out date, and room number.
	 * @param customerList
	 *            The list that contains information about the customer.
	 * @param roomList
	 *            The list that contains information about the rooms. (room
	 *            number and room type)
	 * @return reservation The reservation list containing information about the
	 *         person's email,check in and out date, and room number.
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static Reservation[] getReservationListFromSequentialFile(String filename, Customer[] customerList,
			Room[] roomList) throws IOException, IllegalArgumentException {
		Reservation[] reservation = new Reservation[2];
		Scanner inputFile = null;
		String recordStr = null;
		int foundEmailIndex;
		int foundRoomIndex;

		try {
			BufferedReader b = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
			inputFile = new Scanner(b);
			String[] fields = null;
			int i = 0;

			// hasNext to check for end of file
			while ((inputFile.hasNext())) {

				recordStr = inputFile.nextLine();
				fields = recordStr.split("\\*");

				try {
					if (recordStr.isEmpty()) {
						continue;
					}
					foundEmailIndex = searchForEmail(customerList, fields[0].trim());
					foundRoomIndex = searchForRoomNumber(roomList, Integer.parseInt(fields[7].trim()));

					if (foundEmailIndex < 0)
						throw new IllegalArgumentException(" No matching email found.");

					if (foundRoomIndex < 0)
						throw new IllegalArgumentException(" No matching room found.");

					reservation[i] = new DawsonReservation(customerList[foundEmailIndex], roomList[foundRoomIndex],
							Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]),
							Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
					i++;
					// resize to increase size of array
					if (i >= reservation.length)
						reservation = Arrays.copyOf(reservation, reservation.length * 2 + 1);

				} catch (ArrayIndexOutOfBoundsException oob) {
					System.out.println("Record: " + recordStr + oob.getMessage());
				} catch (IllegalArgumentException iae) {
					System.out.println("Record: " + recordStr + iae.getMessage());
				} catch (DateTimeException dte) {
					System.out.println("Record: " + recordStr + " " + dte.getMessage());
				}
			} // resize to capacity
			if (i < reservation.length)
				reservation = Arrays.copyOf(reservation, i);
			return reservation;
		} catch (IOException e) {
			System.out.println("Connection Error " + filename + e.getMessage());
			return new Reservation[0];
		} finally {
			if (inputFile != null)
				inputFile.close();
		}

	}

	/**
	 * The getRoomListFromSequentialFile method searches for a Room list file
	 * and loads each Room
	 * 
	 * @param filename
	 *            The file that contains information about the room number and
	 *            room type.
	 * @return room A room array containing information about the room in the
	 *         room list file.
	 * @throws IOException
	 */
	public static Room[] getRoomListFromSequentialFile(String filename) throws IOException {
		Room[] room = new Room[2]; // Will check size later, minimum of 1 set of
									// room (room# and room type).
		Scanner inputFile = null;
		String recordStr = null;
		try {
			BufferedReader b = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
			inputFile = new Scanner(b);
			String[] fields = null;
			int i = 0;
			// Use hasNext to check for end of file.
			while (inputFile.hasNext()) {
				recordStr = inputFile.nextLine();
				fields = recordStr.split("\\*");

				try {
					if (recordStr.isEmpty()) {
						continue;
					}
					room[i] = new DawsonRoom(Integer.parseInt(fields[0]), RoomType.valueOf(fields[1].toUpperCase()));
					i++;
					// Check if capacity surpassed and resize.
					if (i >= room.length)
						room = Arrays.copyOf(room, room.length * 2 + 1);

				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Record: " + recordStr + e.getMessage());
				}

			}
			// Resizing the room array.
			if (i < room.length) {
				room = Arrays.copyOf(room, i);
			}
			return room;
		} catch (IOException e) {
			System.out.println("Connection Error " + filename);
			return new Room[0];
		} finally {
			if (inputFile != null)
				inputFile.close();
		}

	}
	
	/**
	 * The searchForEmail method searches a list to find a matching email.
	 * 
	 * @param list
	 *            The list that holds the Customer's information.
	 * @param email
	 *            The email that is being searched for.
	 * @return i or -1 (i for index position found and -1 for not found).
	 */
	private static int searchForEmail(Customer[] list, String email) {
		// something with the email value

		for (int i = 0; i < list.length; i++) {

			if (list[i].getEmail().getAddress().equals(email)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * The searchForRoomNumber method searches a list to find a matching room
	 * number.
	 * 
	 * @param list
	 *            The list that holds the room number.
	 * @param number
	 *            The room number that is being searched for.
	 * @return i or -1 (i for index position found and -1 for not found).
	 */
	private static int searchForRoomNumber(Room[] list, int number) {

		for (int i = 0; i < list.length; i++)
		{
			if (list[i].getRoomNumber() == number) 
			{
				return i;
			}
		}
		return -1;
	}
}
