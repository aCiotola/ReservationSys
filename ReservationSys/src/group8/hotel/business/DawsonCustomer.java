/**
 * 
 */
package group8.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.*;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;

/**
 * @author Hannah Ly
 * @author Alessandro Ciotola (setCreditCard, getCreditCard)
 * @version 27/09/2016
 * @since 1.8
 *
 */
public class DawsonCustomer implements Customer {
	private Name name;
	private Email email;
	private CreditCard card;

	/**
	 * A three parameter constructor that takes in a firstName, a lastName and
	 * an email.
	 * 
	 * @param firstName
	 *            The firstName of the customer.
	 * @param lastName
	 *            The lastName of the customer.
	 * @param email
	 *            The email of the customer.
	 * 
	 */
	public DawsonCustomer(String firstName, String lastName, String email) {
		this.name = new Name(firstName, lastName);
		this.email = new Email(email);
	}

	private static final long serialVersionUID = 42031768871L;

	/**
	 * Compares two customer objects.
	 * 
	 * @return -1 if the first email comes before the second email; 0 if both
	 *         customers have the same email; 1 if the first email comes after
	 *         the second email.
	 */
	public int compareTo(Customer o) {
		if (this.email.compareTo(o.getEmail()) < 0)
			return -1;
		else if (this.email.compareTo(o.getEmail()) == 0)
			return 0;
		else
			return 1;
	}

	/**
	 * Checks if two customer objects are equal depending on their email.
	 * 
	 * @return true if both customer objects are pointing at the same reference;
	 *         false if the second customer object is null; false if the second
	 *         customer object is not an instanceof Customer; false if both
	 *         customer objects do not have the same email.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Customer))
			return false;

		DawsonCustomer other = (DawsonCustomer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		return true;
	}

	/**
	 * Gets the customer's email.
	 * 
	 * @return The customer's email.
	 */
	public Email getEmail() {
		return this.email;
	}

	/**
	 * Gets the customer's name.
	 * 
	 * @return The customer's name.
	 */
	public Name getName() {
		Name name = new Name(this.name.getFirstName(), this.name.getLastName());
		return name;
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

		result = prime * result + ((email == null) ? 0 : email.hashCode());

		return result;
	}

	/**
	 * Gets the customer's credit card.
	 * 
	 * @return The customer's credit card OR null if the customer does not have
	 *         a credit card.
	 */
	public Optional<CreditCard> getCreditCard() {
		return Optional.ofNullable(card);
	}

	/**
	 * Sets the customer's credit card.
	 * 
	 * @param card
	 */
	public void setCreditCard(Optional<CreditCard> card) {
		if (card.isPresent() == true)
			this.card = card.get();
		else
			this.card = card.orElse(null);
	}

	/**
	 * String representation of the customer.
	 * 
	 * @return String representation of the Customer.
	 */
	@Override
	public String toString() {
		if (card != null)
			return email + "*" + name.getFirstName() + "*" + name.getLastName() + "*" + card.getType() + "*"
					+ card.getNumber();
		else
			return email + "*" + name.getFirstName() + "*" + name.getLastName() + "**";
	}

}
