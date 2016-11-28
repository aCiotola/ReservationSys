package dw317.hotel.business;

import java.time.LocalDate;
import java.util.*;

import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;
import group8.hotel.business.DawsonRoom;

/**
 * The DawsonHotelAllocationPolicy class takes in a ReservationDAO object and
 * follows a algorithm under the getAvaiableRoom method.
 * 
 * 
 * @author Phi Dai Nguyen
 *
 */
public class DawsonHotelAllocationPolicy implements AllocationPolicy {

	private static final long serialVersionUID = 42031768871L;
	private ReservationDAO reservationDAO;

	public DawsonHotelAllocationPolicy(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;

	}

	/**
	 * The getAvailableRoom method returns the next available room.
	 * 
	 * @param checkin
	 *            The check in date.
	 * @param checkout
	 *            The check out date.
	 * @param roomType
	 *            The room type.
	 * @return The Optional<Room> that contains nothing or a room.
	 */
	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		List<Room> freeRooms = reservationDAO.getFreeRooms(checkin, checkout, roomType);
		int[] floor = new int[8]; // 8 floors

		int high = floor[0];
		int floorNumber = 0;
		int theFloor = 0;
		int potentialRoom = 0;
		
		if (freeRooms.size() <= 0){
			return Optional.empty();
		}

		// for lopp to find each floor's amount of rooms.
		for (int i = 0; i < floor.length; i++) {
			for (int j = 0; j < freeRooms.size(); j++) {
				if (i + 1 == freeRooms.get(j).getFloor()) {
					floor[i]++;
				}
			}
		}
		// for lopp to find the floor with the most amount of rooms
		for (int i = 1; i < floor.length; i++) {
			if (floor[i] > high) {
				high = floor[i];
				theFloor = i;
			}
		}
		floorNumber = theFloor + 1;

		potentialRoom = chooseRandomRoom(floorNumber, roomType);
		
		boolean available = false;
		
		while(!available)
		{
			if(freeRooms.size() == 0)
				available = true;
			else
			{
			for(int i = 0; i < freeRooms.size(); i++)
			{
				if(potentialRoom == freeRooms.get(i).getRoomNumber())
				{
					available = true;
					i = freeRooms.size();
				}
			}
			
			if(available == false)
				potentialRoom = chooseRandomRoom(floorNumber, roomType);
			}
		}	
		DawsonRoom theRoom = new DawsonRoom(potentialRoom, roomType);
		return Optional.ofNullable(theRoom);
	}

	/**
	 * This chooseRandomRoom method randomizes a number from different values 
	 * depend on the room type. Since we know that the room will be for sure 
	 * free, the randomized room number combined with the floor will be the 
	 * selected room for the user.
	 * 
	 * @param floor
	 *            The floor to find a room in.
	 *
	 * @param roomType
	 *            The room type to use to find a specific room with that type.
	 * @return The room number.
	 */
	private int chooseRandomRoom(int floor, RoomType roomType) {

		int roomNumber = 0;
		int theNumber = 0;

		Random rand = new Random();

		if (roomType.equals(RoomType.NORMAL)) {
			roomNumber = rand.nextInt((8 - 1) + 1) + 1;
		} else if (roomType.equals(RoomType.SUITE)) {
			roomNumber = rand.nextInt((4 - 1) + 1) + 1;
		} else {
			roomNumber = 1;
		}

		theNumber = (floor * 100) + roomNumber;

		return theNumber;

	}

}
