/**
 * 
 */
package dw317.hotel.business;

/**
 * A RoomType enum containing the different types of rooms.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public enum RoomType {
	NORMAL, SUITE, PENTHOUSE;

	/**
	 * toString method which returns a lower case string of the type of room.
	 * 
	 * @return The lower case version of the type of room.
	 */
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
