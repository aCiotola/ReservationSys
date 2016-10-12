/**
 * 
 */
package dw317.lib.creditcard;

/**
 * A class which takes and validates a Visa object.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public class Visa extends AbstractCreditCard {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * A one parameter constructor which calls the validate method.
	 * 
	 * @param number
	 *            The Visa number.
	 */
	public Visa(String number) throws IllegalArgumentException {
		super(CardType.VISA, validateNumber(number));
	}

	/**
	 * A method that validates the Visa card number.
	 * 
	 * @param number
	 *            The number of the Visa.
	 * @return The validated Visa Number.
	 * @throws IllegalArgumentException
	 *             Exception thrown if the Card number is invalid.
	 */
	private static String validateNumber(String number) throws IllegalArgumentException {
		if (number == null)
			throw new IllegalArgumentException("Null Number");

		if (number.length() != 16)
			throw new IllegalArgumentException("The number of digits in a Visa must be 16");

		if (!number.matches("^[4][0-9]*$"))
			throw new IllegalArgumentException("A Visa must begin with a 4");
		return number;
	}
}
