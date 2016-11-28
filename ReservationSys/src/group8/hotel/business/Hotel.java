/**
 * 
 */
package group8.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import dw317.hotel.business.*;
import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.*;
import dw317.lib.Email;
import dw317.lib.creditcard.*;
import dw317.lib.creditcard.CreditCard.CardType;
import group8.hotel.data.*;
import group8.hotel.data.SequentialTextFileList;

/**
 * Creates a Hotel object that allows the frontdesk to interact with the informations about the customers and the reservation.
 * 
 * @author Hannah Ly
 * @version 27/11/2016
 * @since 1.8
 */
public class Hotel implements HotelManager
{
	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private static final long serialVersionUID = 42031768871L;
	
	public Hotel (HotelFactory factory, CustomerDAO customers, 
			ReservationDAO reservations)
	{
		if(factory == null || customers == null || reservations == null)
			throw new IllegalArgumentException("The parameters cannot be null.");
		else
		{
			this.factory = factory;
			this.customers = customers;
			this.reservations = reservations;
		}
	}

	/**
	 * Cancels a given reservation
	 * @param reservation
	 * @throws NonExistingReservationException
	 */
	@Override
	public void cancelReservation(Reservation reservation) throws NonExistingReservationException 
	{
		if(reservation == null)
			throw new IllegalArgumentException("The reservation cannot be null.");
		else
			reservations.cancel(reservation);
	}

	/**
	 * Saves all data, clears expired reservations and closes the hotel.
	 * 
	 * @throws IOException
	 *             If there is a problem closing the hotel files.
	 */
	@Override
	public void closeHotel() throws IOException 
	{
		customers.disconnect();
		reservations.disconnect();
	}

	/**
	 * Creates and adds a reservation for a customer.
	 * @param customer The given customer
	 * @param checkin	The requested check in date
	 * @param checkout  The requested check out date
	 * @param roomType  The requested room type
	 * @return The Reservation if possible
	 */
	@Override
	public Optional<Reservation> createReservation(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType) 
	{		
		DawsonHotelAllocationPolicy dhap = new DawsonHotelAllocationPolicy(reservations);
		
		Optional<Room> availableRoom = dhap.getAvailableRoom(checkin, checkout, roomType);
		
		Room aRoom = availableRoom.get();
		
		int yearIn = checkin.getYear();
		int monthIn = checkin.getMonthValue();
		int dayIn = checkin.getDayOfMonth();
		
		int yearOut = checkout.getYear();
		int monthOut = checkout.getMonthValue();
		int dayOut = checkout.getDayOfMonth();
		
		
		try
		{
			if(availableRoom != null)
			{
				Reservation rsvp = new DawsonReservation (customer, aRoom, yearIn, monthIn, dayIn, yearOut, monthOut, dayOut);
				reservations.add(rsvp);
				
				System.out.println("\n The reservation was succesfully created.");
				
				return Optional.ofNullable(rsvp);
			}
		}
		catch(DuplicateReservationException dre)
		{
			System.out.println("\n There is no available room for those dates!");
		}
		
		return null;
	}

	/**
	 * Finds and returns a customer record.
	 * @param email  The customer's e-mail address
	 * @return Customer object
	 * @throws NonExistingCustomerException
	 *         if the customer with the given e-mail cannot be found
	 */
	@Override
	public Customer findCustomer(String email) throws NonExistingCustomerException 
	{
	
		Email emailTemp = new Email (email);
		
		Customer cust = customers.getCustomer(emailTemp);
		
		return cust;
	}

	/**
	 * Finds all reservations made by a customer
	 * @param customer
	 * @return List of Reservations. Returns empty list if no reservations can be found.
	 */
	@Override
	public List<Reservation> findReservations(Customer customer) 
	{	
		List<Reservation> rsvp = new ArrayList <Reservation>();
		
		rsvp = reservations.getReservations(customer);
		
		return rsvp;
	}

	/**
	 * Registers a new Customer
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return The Customer object
	 * @throws DuplicateCustomerException is a customer with same e-mail address exists
	 */
	@Override
	public Customer registerCustomer(String firstName, String lastName, String email)
			throws DuplicateCustomerException 
	{
		DawsonCustomer cust = new DawsonCustomer(firstName, lastName, email);
		
		customers.add(cust);
		
		return cust;		
	}

	/**
	 * Adds or updates the credit card associate with a customer.
	 * @param email The email address of the customer
	 * @param cardType
	 * @param cardnumber
	 * @return the updated Customer
	 * @throws NonExistingCustomerException
	 *         if the customer with the given e-mail cannot be found
	 */
	@Override
	public Customer updateCreditCard(String email, String cardType, String cardnumber)
			throws NonExistingCustomerException 
	{
		Email emailTemp = new Email (email);
		
		CardType cardTypeTemp = CardType.valueOf(cardType);
		
		CreditCard cardTemp = CreditCard.getInstance(cardTypeTemp, cardnumber);
		
		customers.update(emailTemp, cardTemp);
		
		Customer updatedCust = customers.getCustomer(emailTemp);
		
		return updatedCust;
	}
}
