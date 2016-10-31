/**
 * 
 */
package group8.hotel.data;

import group8.util.ListUtilities;
import group8.hotel.business.*;
import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.*;
/**
 * @author Hannah Ly
 * @version 28/10/2016
 * @since 1.8
 *
 * This application sorts and merges the Customer, Reservation and Room files.
 */
public class MergeSortApp {

			public static void main(String[] args) throws IOException 
			{
				Room[] rooms;
				Customer[] mergedCustomers;
				Reservation[] mergedReservations;
				
				rooms = sortRoomsAndWriteToFile();
				
				mergedCustomers = sortCustomersAndWriteToFile();
				
				mergedReservations = sortReservationsAndWriteToFile(mergedCustomers, rooms);

			}
			

			/**
			 * This method loads and reads unsorted customer files and sorts them by the email host name.
			 * It then merges all the sorted customer files into one sorted customer file and saves it in a text file.
			 * 
			 * @return sorted customer array
			 * @throws IOException
			 */
			private static Customer[] sortCustomersAndWriteToFile() throws IOException
			{
				Customer[] customers;
				Customer[] mergedCustomers = new DawsonCustomer [0];
				String fileName = "";
				
				File duplicateDirectory = new File("datafiles/duplicates");
				
				if(!duplicateDirectory.exists())
				{
					duplicateDirectory.mkdirs();
				}
				
				File sortedDirectory = new File("datafiles/sorted");
				
				if(!sortedDirectory.exists())
				{
					sortedDirectory.mkdirs();
				}
				
				File duplicateCustomer = new File("datafiles/duplicates/duplicateCustomers.txt");
				
				for(int i = 1; i < 11; i ++)
				{
					fileName = sortedDirectory + "/sortedCustomers" + i + ".txt";
					
					customers = HotelFileLoader.getCustomerListFromSequentialFile("datafiles/unsorted/customers" + i + ".txt");
					
					ListUtilities.sort(customers);
					
					File customersFile = new File(fileName);
					
					ListUtilities.saveListToTextFile(customers, fileName);
					
					mergedCustomers = (Customer[])(ListUtilities.merge(customers, mergedCustomers, "datafiles/duplicates/duplicateCustomers.txt"));
										
				}
				
				File databaseDirectory = new File("datafiles/database");
				
				if(!databaseDirectory.exists())
				{
					databaseDirectory.mkdirs();
				}
				
				File mergedCustomersFile = new File(databaseDirectory + "/customers.txt");
				
				mergedCustomersFile.createNewFile();
				
				ListUtilities.saveListToTextFile(mergedCustomers, databaseDirectory + "/customers.txt");

				return mergedCustomers;
			}
			
			
			/**
			 * This method loads and reads unsorted reservation files and sorts them by room number.
			 * It then merges all the sorted reservation files into one sorted reservation file and saves it in a text file.
			 * 
			 * @param customer The sorted Customer list
			 * @param room The sorted Room list
			 * @return sorted reservation array
			 * @throws IllegalArgumentException
			 * @throws IOException
			 */
			public static Reservation[] sortReservationsAndWriteToFile(Customer[] customer, Room[] room) throws IllegalArgumentException, IOException
			{
				Reservation[] reservations;
				Reservation[] mergedReservations = new Reservation[0];
				String fileName;
				
				File duplicateDirectory = new File("datafiles/duplicates");
				
				if(!duplicateDirectory.exists())
				{
					duplicateDirectory.mkdirs();
				}
				
				File sortedDirectory = new File("datafiles/sorted");
				
				if(!sortedDirectory.exists())
				{
					sortedDirectory.mkdirs();
				}
				
				File duplicateReservations = new File("datafiles/duplicates/duplicateReservations.txt");
				
				for(int i = 1; i < 11; i ++)
				{
					fileName = sortedDirectory + "/sortedReservations" + i + ".txt";
					
					reservations = HotelFileLoader.getReservationListFromSequentialFile("datafiles/unsorted/reservations" + i + ".txt", customer, room);
					
					ListUtilities.sort(reservations);
					
					File reservationsFile = new File(fileName);
					
					ListUtilities.saveListToTextFile(reservations, fileName);
					
					mergedReservations = (Reservation[])(ListUtilities.merge(reservations, mergedReservations, "datafiles/duplicates/duplicateReservations.txt"));
				}
				
				File databaseDirectory = new File("datafiles/database");
				
				if(!databaseDirectory.exists())
				{
					databaseDirectory.mkdirs();
				}
				
				File mergedReservationsFile = new File(databaseDirectory + "/reservations.txt");
				
				mergedReservationsFile.createNewFile();
				
				ListUtilities.saveListToTextFile(mergedReservations, databaseDirectory + "/reservations.txt");
				
				return mergedReservations;
			}
			
			/**
			 * This method loads and reads the unsorted room file and sorts it by the room number.
			 * It then saves the file in a text file.
			 * 
			 * @return sorted room array
			 * @throws IOException
			 */
			private static Room[] sortRoomsAndWriteToFile() throws IOException
			{
				Room[] rooms;
				String fileName;
				
				File databaseDirectory = new File("datafiles/database");
				
				if(!databaseDirectory.exists())
				{
					databaseDirectory.mkdirs();
				}
				
				fileName = databaseDirectory + "/rooms.txt";
				
				rooms = HotelFileLoader.getRoomListFromSequentialFile("datafiles/unsorted/rooms.txt");
				
				ListUtilities.sort(rooms);
				
				File roomsFile = new File(fileName);
				
				ListUtilities.saveListToTextFile(rooms, fileName);
				
				roomsFile.createNewFile();
				
				return rooms;
			}

}
