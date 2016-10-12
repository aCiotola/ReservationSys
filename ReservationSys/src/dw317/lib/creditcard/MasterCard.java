package dw317.lib.creditcard;

/**
 * A class which takes and validates a MasterCard object.
 * 
 * @author Alessandro Ciotola
 * @version 27/09/2016
 * @since 1.8
 */
public final class MasterCard extends AbstractCreditCard {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * A one parameter constructor which calls the validate method.
	 * 
	 * @param number
	 *            The MasterCard number.
	 */
	public MasterCard(String number) throws IllegalArgumentException {
		super(CardType.MASTERCARD, validateNumber(number));
	}

	/**
	 * A method that validates the MasterCard number.
	 * 
	 * @param number
	 *            The number of the MasterCard.
	 * @return The validated MasterCard Number.
	 * @throws IllegalArgumentException
	 *             Exception thrown if the Card number is invalid.
	 */
	private static String validateNumber(String number) throws IllegalArgumentException {
		if (number == null)
			throw new IllegalArgumentException("Null Number");

		if (number.length() != 16)
			throw new IllegalArgumentException("The number of digits in a MasterCard must be 16");

		if (!number.matches("^[51-55][0-9]*$"))
			throw new IllegalArgumentException("A MasterCard must begin with 51 - 55");
		return number;
	}
}
