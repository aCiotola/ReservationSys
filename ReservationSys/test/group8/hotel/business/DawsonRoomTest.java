/**
 * 
 */
package group8.hotel.business;

import dw317.hotel.business.RoomType;

/**
 * @author Hannah Ly
 *
 */
public class DawsonRoomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		testCompareTo();
		
		testEquals();
		
		testGetFloor();
		
		testGetNumber();
		
		testGetRoomType();
		
		testGetRoomNumber();
		
		testHashCode();
		
		testTheTwoParameterConstructor();
		
		testToString();
		
	}
	
	
	public static void testCompareTo()
	{
		DawsonRoom room1 = new DawsonRoom(201, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(701, RoomType.SUITE);
		DawsonRoom room3 = new DawsonRoom(701, RoomType.SUITE);
		
		testCompareTo("Case 1 - Valid - Room 1 should be before Room 2", room1, room3);
		testCompareTo("Case 2 - Valid - Room 1 should be the same as Room 2", room2, room3); 
		testCompareTo("Case 3 - Valid - Room 1 should be after Room 2", room3, room1); 
	}
	
	public static void testCompareTo(String testCase, DawsonRoom room1, DawsonRoom room2)
	{
		System.out.println(testCase);
		
		System.out.print("First room: " + room1);
		System.out.print("\t Second room: " + room2);
		
		if(room1.compareTo(room2) < 0)
		{
			System.out.print("\n Value returned by the compareTo method: " + room1.compareTo(room2));
			System.out.print("\n The first room comes before the second room.");
		}
		else
			if(room1.compareTo(room2) == 0)
			{
				System.out.print("\n Value returned by the compareTo method: " + room1.compareTo(room2));
				System.out.print("\n Both rooms are the same.");
			}
			else
			{
				System.out.print("\n Value returned by the compareTo method: " + room1.compareTo(room2));
				System.out.print("\n The second room is before the first room.");
			}
		
		System.out.println("\n");
	}
	
	public static void testEquals()
	{
		DawsonRoom room1 = new DawsonRoom(701, RoomType.SUITE);
		DawsonRoom room2 = new DawsonRoom(701, RoomType.SUITE);
		DawsonRoom room3 = new DawsonRoom(308, RoomType.NORMAL);
		
		testEquals("Case 1 - Valid - Both rooms point to the same object; returns true", room1, room1);
		testEquals("Case 2 - Valid - Reoom 2 is null; returls false", room1, null);
		testEquals("Case 3 - Valid - Both rooms should be the same; returns true", room1, room2);
		testEquals("Case 4 - Valid - Both rooms should be different; returns false", room1, room3);
	}
	
	public static void testEquals(String testCase, DawsonRoom room1, DawsonRoom room2)
	{
		System.out.println(testCase);
		
		System.out.print("First room: " + room1);
		System.out.print("\t Second room: " + room2);
		
		if(room1.equals(room2))
		{
			System.out.print("\nThe rooms are the same.");
			System.out.print("\t Value returned by the method: " + room1.equals(room2));
		}
		else
		{
			System.out.print("\nThe rooms are different.");
			System.out.print("\t Value returned by the method: " + room1.equals(room2));
		}
		
		System.out.println("\n");
	}
	
	public static void testGetFloor()
	{
		testGetFloor("Case 1 - Valid - Floor number is 7", 701, 7);
		testGetFloor("Case 2 - Invalid - Floor number should be 7", 801, 7);
	}
	
	public static void testGetFloor(String testCase, int roomNumber, int expectedFloorNumber)
	{
		System.out.println(testCase);
		
		DawsonRoom room = new DawsonRoom(roomNumber, RoomType.NORMAL);
		
		System.out.print("The room was created: " + room);
		
		if(!(room.getFloor() == expectedFloorNumber))
			System.out.print("   Error. Expected INVALID. FAIL");
		
		System.out.println("\n");
	}
	
	public static void testGetNumber()
	{
		testGetNumber("Case 1 - Valid - Room number is 1", 701, 1);
	}
	
	public static void testGetNumber(String testCase, int roomNumber, int expectedNumber)
	{
		System.out.println(testCase);
		
		DawsonRoom room = new DawsonRoom(roomNumber, RoomType.SUITE);
		
		System.out.print("The room was created: " + room);
		
		if(!(room.getNumber() == expectedNumber))
			System.out.print("   Error. Expected INVALID. FAIL");
		
		System.out.println("\n");
	}
	
	public static void testGetRoomType()
	{
		testGetRoomType("Case 1 - Valid - Room type is NORMAL", RoomType.NORMAL, RoomType.NORMAL);
		testGetRoomType("Case 2 - Valid - Room type is SUITE", RoomType.SUITE, RoomType.SUITE);
		testGetRoomType("Case 3 - Valid - Room type is PENTHOUSE", RoomType.PENTHOUSE, RoomType.PENTHOUSE);
		testGetRoomType("Case 4 - Invalid - Room type should be PENTHOUSE", RoomType.NORMAL, RoomType.PENTHOUSE);
	}
	
	public static void testGetRoomType(String testCase, RoomType roomType, RoomType expectedRoomType)
	{
		System.out.println(testCase);
		
		DawsonRoom room = new DawsonRoom(701, roomType);
		
		System.out.print("The room was created: " + room);
		
		if(!room.getRoomType().equals(expectedRoomType))
			System.out.print("\t Error. Expected INVALID. FAIL");
		
		System.out.println("\n");
	}
	
	public static void testGetRoomNumber()
	{
		testGetRoomNumber("Case 1 - Valid - Room number is 701", 701, 701);
		testGetRoomNumber("Case 2 - Invalid - Room number should be 701", 801, 701);
	}
	
	public static void testGetRoomNumber(String testCase, int roomNumber, int expectedRoomNumber)
	{
		System.out.println(testCase);
		
		DawsonRoom room = new DawsonRoom(roomNumber, RoomType.NORMAL);
		
		System.out.print("The room was created: " + room);
		
		if(!(room.getRoomNumber() == (expectedRoomNumber)))
			System.out.print("   Error. Expected INVALID. FAIL");
		
		System.out.println("\n");
	}
	
	public static void testHashCode()
	{
		DawsonRoom room1 = new DawsonRoom(701, RoomType.SUITE);
		DawsonRoom room2 = new DawsonRoom(308, RoomType.NORMAL);
		testHashCode("Case 1 - Valid - The hash codes should be the same.", room1, room1);
		testHashCode("Case 2 - Valid - The hash codes should be different.", room1, room2);
	}
	
	public static void testHashCode(String testCase, DawsonRoom room1, DawsonRoom room2)
	{
		System.out.println(testCase);
		System.out.print("First room: " + room1);
		System.out.print("\t Second room 2: " + room2);
		
		if(room1.getRoomNumber() == room2.getRoomNumber())
		{
			System.out.print("\nHash codes for both rooms are the same.");
			System.out.print("Hash code room 1: " + room1.hashCode());
			System.out.print("\t \t Hash code room 2: " + room2.hashCode());
		}
		else
		{
			System.out.print("\nHash codes for both room are different.");
			System.out.print("Hash code room1: " + room1.hashCode());
			System.out.print("\t \t Hash code room 2: " + room2.hashCode());
		}
		
		System.out.println("\n");
	}
	
	public static void testTheTwoParameterConstructor()
	{
		testTheTwoParameterConstructor("Case 1 - Valid - Normal room", 201, RoomType.NORMAL, true);
		
		testTheTwoParameterConstructor("Case 2 - Valid - Suite room", 701, RoomType.SUITE, true);
		
		testTheTwoParameterConstructor("Case 3 - Valid- Penthouse room", 801, RoomType.PENTHOUSE, true);
		
		testTheTwoParameterConstructor("Case 4 - Invalid - Floor number is bigger than 8", 901, RoomType.NORMAL, false);
		
		testTheTwoParameterConstructor("Case 5 - Invalid - Middle number is not zero(0)", 721, RoomType.NORMAL, false);
		
		testTheTwoParameterConstructor("Case 6 - Invalid - Room number is bigger than 8", 709, RoomType.NORMAL, false);
		
		testTheTwoParameterConstructor("Case 7 - Invalid - Room number is smaller than 1", 700, RoomType.NORMAL, false);

		testTheTwoParameterConstructor("Case 8 - Invalid - Room type is null", 801, null, false);
		
		testTheTwoParameterConstructor("Case 9 - Invalid - No room number", 0, RoomType.NORMAL, false);	
	}
	
	public static void testTheTwoParameterConstructor(String testCase, int roomNumber, RoomType roomType, boolean expectedValid)
	{
		System.out.println(testCase);
		
		try
		{
			DawsonRoom room = new DawsonRoom(roomNumber, roomType);
			System.out.print("A new room was created: " + room);
			
			if(!expectedValid)
			{
				System.out.print("\t Error. Expected INVALID. FAIL --try--");
			}
		}
		catch(IllegalArgumentException iae)
		{
			System.out.print("Printing exception message: " + iae.getMessage());
			
			if(expectedValid)
			{
				System.out.print("\t Error. Expected VALID. FAIL --catch--");
			}
		}
		System.out.println("\n");
	}
	
	public static void testToString()
	{
		testToString("Case 1 - Valid - Room is 701*normal", 701, RoomType.SUITE, "701*suite");
		testToString("Case 2 - Invalid - Room should be 701*suite", 701, RoomType.NORMAL, "701*suite");
	}
	
	public static void testToString(String testCase, int roomNumber, RoomType roomType, String expectedRoom)
	{
		System.out.println(testCase);
		
		DawsonRoom room = new DawsonRoom(roomNumber, roomType);
		
		System.out.print("The room was created: " + room);
		
		if(!room.toString().equals(expectedRoom))
			System.out.print("   Error. Expected INVALID. FAIL");
		
		System.out.println("\n");
	}
}
