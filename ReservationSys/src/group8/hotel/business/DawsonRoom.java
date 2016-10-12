/**
 * 
 */
package group8.hotel.business;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.*;

/**
 * A DawsonRoom class that creates a room with its roomNumber and roomType.
 * 
 * @author Hannah Ly
 * @version 27/09/2016
 * @since 1.8
 * 
 */
public class DawsonRoom implements Room {
	private int roomNumber;
	private RoomType roomType;

	private static final long serialVersionUID = 42031768871L;

	/**
	 * A two parameter constructor that takes in a roomNumber and a roomType.
	 * 
	 * @param roomNumber
	 *            The number of the room.
	 * @param roomType
	 *            The type of the room.
	 * 
	 * @throws An
	 *             IllegalArgumentException if the roomNumber is invalid.
	 */
	public DawsonRoom(int roomNumber, RoomType roomType) {
		if (roomType == null)
			throw new IllegalArgumentException("roomType cannot be null");

		int floorNumber = roomNumber / 100;
		int middleNumber = roomNumber / 10 % 10;
		int roomNum = roomNumber % 10;

		if (floorNumber <= 8 && floorNumber >= 1) {
			if (middleNumber == 0) {
				if (roomNum <= 8 && roomNum >= 1) {

					this.roomNumber = roomNumber;
				} else
					throw new IllegalArgumentException("Room number is invalid");
			} else
				throw new IllegalArgumentException("Room number is invalid");
		} else
			throw new IllegalArgumentException("Room number is invalid");

		if (floorNumber >= 1 && floorNumber <= 5)
			this.roomType = roomType.NORMAL;
		else if (floorNumber >= 6 && floorNumber <= 7)
			this.roomType = roomType.SUITE;
		else
			this.roomType = roomType.PENTHOUSE;
	}

	/**
	 * Compares two room objects.
	 * 
	 * @return -1 if the first room number is smaller than the second room
	 *         number; 0 if both rooms have the same room number; 1 if the first
	 *         room number is greater than the second room number.
	 */
	public int compareTo(Room o) {
		if (this.roomNumber < o.getRoomNumber())
			return -1;
		else if (this.roomNumber == o.getRoomNumber())
			return 0;
		else
			return 1;
	}

	/**
	 * Checks if two room objects are equal depending on their room number.
	 * 
	 * @return true if both room objects are pointing at the same reference;
	 *         false if the second room object is null; false if the second room
	 *         object is not an instanceof Room; false if both room objects do
	 *         not have the same room number.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Room))
			return false;

		DawsonRoom other = (DawsonRoom) obj;
		if (this.roomNumber != other.getRoomNumber())
			return false;

		return true;
	}

	/**
	 * Gets the floor number.
	 * 
	 * @return The floor number.
	 */
	public int getFloor() {
		return this.roomNumber / 100;
	}

	/**
	 * Gets the room number. (Single digit)
	 * 
	 * @return The room number.
	 */
	public int getNumber() {
		return this.roomNumber % 10;
	}

	/**
	 * Gets the room type.
	 * 
	 * @return The room type.
	 */
	public RoomType getRoomType() {
		return roomType;
	}

	/**
	 * Gets the room number. (Three digits)
	 * 
	 * @return The room number.
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Gets the hash code.
	 * 
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + roomNumber;

		return result;
	}

	/**
	 * To String representation of the room.
	 * 
	 * @return the String representation of the Room.
	 */
	@Override
	public String toString() {
		return roomNumber + "*" + roomType;
	}
}
