/**
 * 
 */
package dw317.lib;

/**
 * Defines a Name object, encapsulates a first and last name.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 *
 */
public class Name implements Comparable<Name> {
	private String firstName = "";
	private String lastName = "";
	private static final long serialVersionUID = 42031768871L;

	/**
	 * 2 parameter constructor that takes the first and last names of a person.
	 * 
	 * @param firstName
	 *            first name of the person
	 * @param lastName
	 *            last name of the person
	 */
	public Name(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
	}

	/**
	 * An overridden equals that checks the equality between Name object and
	 * another object.
	 * 
	 * @param obj
	 *            An object.
	 * @return A boolean value showing the equality.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Name newName = (Name) obj;

		return firstName.equalsIgnoreCase(newName.firstName) && lastName.equalsIgnoreCase(newName.lastName);
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

		result = prime * result + lastName.toUpperCase().hashCode();
		result += firstName.toUpperCase().hashCode();

		return result;
	}

	/**
	 * gets the first name of the person
	 * 
	 * @return firstName first name of the person
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * gets the last name of the person
	 * 
	 * @return lastName last name of the person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * returns the full name of the person
	 * 
	 * @return fullName the persons='s entire name
	 */
	public String getFullName() {
		String fullName = firstName + " " + lastName;
		return fullName;
	}

	/**
	 * sets the first name of the person
	 * 
	 * @param firstName
	 *            first name of the person
	 */
	public void setFirstName(String firstName) {
		this.firstName = validateName(firstName);
	}

	/**
	 * sets the last name of the person
	 * 
	 * @param lastName
	 *            last name of the person
	 */
	public void setLastName(String lastName) {
		this.lastName = validateName(lastName);
	}

	/**
	 * to String representation of the full name
	 */
	@Override
	public String toString() {
		return (firstName + "*" + lastName);
	}

	/**
	 * An overridden compareTo method that compares two Name objects.
	 * 
	 * @param aName
	 *            A Name object.
	 * @return The number representing the comparison.
	 */
	@Override
	public int compareTo(Name name) {
		if (this.getLastName().compareToIgnoreCase(name.getLastName()) == 0)
			return this.getFirstName().compareToIgnoreCase(name.getFirstName());
		return this.getLastName().compareToIgnoreCase(name.getLastName());
	}

	/**
	 * A method that validates the name part being given.
	 * 
	 * @param namePart
	 *            The first or last name (not full name).
	 * @return The validated first or last name.
	 * @throws IllegalArgumentException
	 *             if the name is Invalid.
	 */
	public String validateName(String namePart) {
		if (namePart == null || namePart == "")
			throw new IllegalArgumentException("Name Error - cannot be null or empty. Invalid value = " + namePart);

		namePart = namePart.trim();

		if (namePart.length() < 2)
			throw new IllegalArgumentException("The name must be longer than 2 letters!");

		for (int i = 0; i < namePart.length(); i++) {
			if (!namePart.matches("[a-zA-Z]+") && namePart.charAt(i) != '-' && namePart.charAt(i) != ' '
					&& namePart.charAt(i) != '\'')
				throw new IllegalArgumentException("A name must only contain letters, hyphens, spaces and apostraphes");
		}
		return namePart;
	}
}
