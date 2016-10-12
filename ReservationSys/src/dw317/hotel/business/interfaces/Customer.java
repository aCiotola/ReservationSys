package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;

import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

/**
 * A Customer interface which contains the methods required for a Customer.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public interface Customer extends Comparable<Customer>, Serializable {
	/**
	 * 111 Gets a full name.
	 * 
	 * @return the Full name of a person.
	 */
	public Name getName();

	/**
	 * Gets the email Address.
	 * 
	 * @return The email Address of a Customer.
	 */
	public Email getEmail();

	/**
	 * Gets the creditCard
	 * 
	 * @return the credit card of a customer.
	 */
	public Optional<CreditCard> getCreditCard();

	/**
	 * Sets the credit card of a customer.
	 * 
	 * @param card
	 *            the Credit card of a customer.
	 */
	public void setCreditCard(Optional<CreditCard> card);
}
