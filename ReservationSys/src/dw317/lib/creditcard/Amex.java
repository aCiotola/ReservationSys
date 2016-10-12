/**
 * 
 */
package dw317.lib.creditcard;

/**
 * A class which takes and validates an Amex object.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public class Amex extends AbstractCreditCard {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * A one parameter constructor which calls the validate method.
	 * 
	 * @param number
	 *            The Amex card number.
	 */
	public Amex(String number) throws IllegalArgumentException {
		super(CardType.AMEX, validateNumber(number));
	}

	/**
	 * A method that validates the MasterCard number.
	 * 
	 * @param number
	 *            The number of the Amex card.
	 * @return The validated Amex card Number.
	 * @throws IllegalArgumentException
	 *             Exception thrown if the Card number is invalid.
	 */
	private static String validateNumber(String number) throws IllegalArgumentException {
		if (number == null)
			throw new IllegalArgumentException("Null Number");

		String firstNumbers = "";
		firstNumbers = number.substring(0, 2);

		if (number.length() != 15)
			throw new IllegalArgumentException("The number of digits in a Amex must be 15");

		if (!firstNumbers.equals("34") && !firstNumbers.equals("37") || firstNumbers == null)
			throw new IllegalArgumentException("An Amex must begin with 34 or 37");
		return number;
	}
}
