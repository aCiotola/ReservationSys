package dw317.lib;

import java.io.Serializable;

/**
 * A class that obtains an email address and validates it.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public class Email implements Serializable, Comparable<Email> {
	private static final long serialVersionUID = 42031768871L;
	private final String address;

	/**
	 * A one parameter Constructor which initializes the address and calls the
	 * validate method for it.
	 * 
	 * @param address
	 *            The full Email address string.
	 */
	public Email(String address) {
		this.address = validateEmail(address);
	}

	/**
	 * An overridden compareTo method that compares two Email objects.
	 * 
	 * @param email
	 *            An Email object.
	 * @return The number representing the comparison.
	 */
	@Override
	public int compareTo(Email email) {
		if (this.getHost().compareToIgnoreCase(email.getHost()) < 0)
			return this.getUserId().compareToIgnoreCase(email.getUserId());
		return this.getHost().compareToIgnoreCase(email.getHost());
	}

	/**
	 * Method that checks if 2 address's are the same.
	 * 
	 * @param object
	 *            the object that will be compared.
	 * @return true or false if the address' are the same.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;

		if (object == null)
			return false;

		if (this.getClass() != object.getClass())
			return false;

		Email email = (Email) object;
		return this.address.equalsIgnoreCase(email.address);
	}

	/**
	 * Method to get the full email address.
	 * 
	 * @return The Email address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method to get the Host name from the email address.
	 * 
	 * @return The host Name from the email address.
	 */
	public String getHost() {
		String host = "";
		int index = address.indexOf("@");
		host = address.substring(index + 1);
		return host;
	}

	/**
	 * Method to get the userId from the email address.
	 * 
	 * @return The userId from the email address.
	 */
	public String getUserId() {
		String userId = "";
		int index = address.indexOf("@");
		userId = address.substring(0, index);
		return userId;
	}

	/**
	 * Gets the hashcode value of the object.
	 * 
	 * @return The hashcode value.
	 */
	@Override
	public int hashCode() {
		int prime = 37;
		int result = 1;

		result = prime * result + address.toUpperCase().hashCode();
		result += address.toUpperCase().hashCode();

		return result;
	}

	/**
	 * An overridden toString method that returns a string representation of the
	 * object.
	 * 
	 * @return A string representation of the Email object.
	 */
	@Override
	public String toString() {
		return address;
	}

	/**
	 * A method that validates if the given address is valid.
	 * 
	 * @param email
	 *            The given email address to be validated.
	 * @return The validated address.
	 * @throws IllegalArgumentException
	 *             exception thrown if address is invalid.
	 */
	private String validateEmail(String email) throws IllegalArgumentException {
		if (email == null)
			throw new IllegalArgumentException("Address cannot be null!");

		String trimmedEmail = email.trim();
		if (!trimmedEmail.matches("(.*)@(.*)"))
			throw new IllegalArgumentException("Not a valid Email Address! " + "(Must have @)");

		int numPeriods = 0;
		int hostNameMaxLength = 32;
		int atIndex = trimmedEmail.indexOf("@");
		String userId = trimmedEmail.substring(0, atIndex);
		String hostName = trimmedEmail.substring(atIndex + 1, trimmedEmail.length());

		for (int i = 0; i < userId.length() - 1; i++) {
			if (userId.charAt(i) == '.')
				numPeriods++;
			if (numPeriods > 1)
				throw new IllegalArgumentException("Cannot have more than " + "one dot");
		}

		for (int i = 0; i < hostName.length() - 1; i++) {
			if (hostName.charAt(i) == '.') {
				hostNameMaxLength += 32;
				if (hostName.length() > hostNameMaxLength)
					throw new IllegalArgumentException("Host Name is too " + "long (Must be MAX 32 characters long)");
			}
		}

		if (userId.length() < 1 || userId.length() > 32 || hostName.length() < 1)
			throw new IllegalArgumentException(
					"UserId and HostName must be " + "larger than 1 or less than 32 characters!");

		if (!userId.matches("^[^.][a-z-A-Z._0-9]*[^.]$"))
			throw new IllegalArgumentException("UserId must only have letters," + " digits, '-', '_', '.' and "
					+ "must not start or end with a '.'");

		if (!hostName.matches("^[^-][-.a-zA-Z0-9]*[^-]$"))
			throw new IllegalArgumentException(
					"Host Name must only have letters, " + "digits, '-' and must not start or " + "end with a '-'");
		return trimmedEmail;
	}
}
