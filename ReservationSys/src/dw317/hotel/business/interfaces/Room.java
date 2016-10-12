/**
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;

import dw317.hotel.business.RoomType;

/**
 * A Room interface which contains the methods required for creating a Room.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public interface Room extends Comparable<Room>, Serializable {
	/**
	 * Method which gets the type of room for a customer
	 * 
	 * @return The room type
	 */
	public RoomType getRoomType();

	/**
	 * Method which gets the number of the room for a customer.
	 * 
	 * @return The room number.
	 */
	public int getRoomNumber();

	/**
	 * Gets the floor number for a customer.
	 * 
	 * @return The floor number
	 */
	public int getFloor();

	/**
	 * Method which gets the room number on a certain floor.
	 * 
	 * @return returns the number of the room on a floor.
	 */
	public int getNumber();
}
