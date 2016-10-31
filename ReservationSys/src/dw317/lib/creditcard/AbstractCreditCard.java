package dw317.lib.creditcard;

/**
 * The abstract class AbstractCreditCard implements from CreditCard and purpose
 * of this class is that it grabs the credit card type and number checks if the
 * credit card is actually a credit card itself.
 * 
 * @author Phi Dai Nguyen
 * @version 9.27.2016
 * @since 1.8
 */
public abstract class AbstractCreditCard implements CreditCard {
	private static final long serialVersionUID = 42031768871L;
	private final CardType cardType;
	private final String number;

	/**
	 * Two parameter constructor. Throws an exception if either card type or the
	 * number is null.
	 * 
	 * @param cardType The type of card
	 * @param number The number of the CreditCard.
	 * @throws NullPointerException if card is null.
	 */
	public AbstractCreditCard(CardType cardType, String number) {
		if (cardType != null && number != null) {
			this.cardType =  	cardType;
			this.number = validateLuhnAlgorithm(number);
		} else
			throw new NullPointerException("Null cardtype or number");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCreditCard other = (AbstractCreditCard) obj;
		if (cardType != other.cardType)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;

		return true;
	}
	
	/**
	 * Method which gets the number of teh CreditCard.
	 * 
	 * @return The number of the CreditCard.
	 */
	@Override
	public String getNumber() {
		return number;
	}

	/**
	 * Method which gets the type of CreditCard.
	 * 
	 * @return The type of CreditCard
	 */
	@Override
	public CardType getType() {
		return cardType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return cardType + "*" + number;
	}

	/**
	 * The validateLuhnAlgorithm method grabs the credit card digit and checks if the numbers
	 * are an valid credit card.
	 * @param number
	 * @return The number if it's a valid credit card else throw an exception.
	 *         Note that the method has to return something so returning the
	 *         number will make the compiler not complain.
	 */
	public static String validateLuhnAlgorithm(String number) { 


		int[] temp = new int[number.length()];
		int[] resized = new int[number.length()];

		number.trim();

		if (number.length() == 0 || number == null)	// if the user doesn't have a credit card
			return number;

		if (number.length() != 16 && number.length() != 15)
			throw new IllegalArgumentException("Not a credit card");

		int intNumber = 0;
		String singleNumber;

		for (int start = 0; start < number.length(); start++) {
			if (start == number.length() - 1) {
				singleNumber = number.substring(start);
				try {
					intNumber = Integer.parseInt(singleNumber);
				} catch (NumberFormatException nfe) {
					System.out.println("Invalid digit! " + nfe);

				}
			}

			else if (start != number.length()) {
				int index = start + 1;
				singleNumber = number.substring(start, index);

				try {
					intNumber = Integer.parseInt(singleNumber);
				} catch (NumberFormatException nfe) {
					System.out.println("Invalid digit! " + nfe);

				}
			}
			temp[start] = intNumber;
		}

		for (int j = 0; j < resized.length; j++) {
			resized[j] = temp[j];
		}

		int total = 0;

		for (int odd = resized.length - 2; odd >= 0; odd = odd - 2) {

			resized[odd] = resized[odd] * 2;
		}

		for (int subtract = 0; subtract < resized.length; subtract++) {
			if (resized[subtract] > 9)
				resized[subtract] = resized[subtract] - 9;
		}

		for (int add = 0; add < resized.length; add++) {
			total += resized[add];
			if (add == resized.length - 1) {
				if (total % 10 == 0) {
					return number;
				} else
					throw new IllegalArgumentException("Not a credit card!");
			}

		}

		return number;
	}
}
