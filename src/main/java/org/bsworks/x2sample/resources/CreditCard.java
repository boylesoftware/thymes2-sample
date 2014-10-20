package org.bsworks.x2sample.resources;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.bsworks.x2.resource.ResourcePropertyAccess;
import org.bsworks.x2.resource.annotations.AccessRestriction;
import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.Property;
import org.bsworks.x2.resource.validation.constraints.CreditCardNumber;


/**
 * Credit card.
 *
 * @author Lev Himmelfarb
 */
public class CreditCard
	extends PaymentInfo {

	/**
	 * Card type.
	 */
	@Property(persistence=@Persistence(field="type"))
	@NotNull
	private CreditCardType type;

	/**
	 * Card number.
	 */
	@Property(
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SEE)
		})
	@NotNull
	@CreditCardNumber
	private String number;

	/**
	 * Last 4 digits of the card number.
	 *
	 * <p>Automatically updated by {@link #setNumber(String)}.
	 */
	@Property(persistence=@Persistence(field="lastdigits"),
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SUBMIT)
		})
	@Pattern(regexp="\\d{4}")
	private String numberLastDigits;

	/**
	 * Expiration date.
	 */
	@Property(persistence=@Persistence(field="expdate"))
	@NotNull
	@Pattern(regexp="20\\d{2}-(0[1-9]|1[0-2])")
	private String expirationDate;

	/**
	 * Card code for CCV.
	 */
	@Property(
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SEE)
		})
	@NotNull
	@Pattern(regexp="\\d{3,4}")
	private String code;


	/**
	 * Create new object.
	 */
	public CreditCard() {
		super(PaymentMethod.CREDIT_CARD);
	}


	/**
	 * Get card type.
	 *
	 * @return Card type.
	 */
	public CreditCardType getType() {

		return this.type;
	}

	/**
	 * Set card type.
	 *
	 * @param type Card type.
	 */
	public void setType(final CreditCardType type) {

		this.type = type;
	}

	/**
	 * Get card number.
	 *
	 * @return Card number.
	 */
	public String getNumber() {

		return this.number;
	}

	/**
	 * Set card number.
	 *
	 * @param number Card number.
	 */
	public void setNumber(final String number) {

		this.number = number;

		this.numberLastDigits = ((number != null) && (number.length() >= 4) ?
				number.substring(number.length() - 4) : null);
	}

	/**
	 * Get last digits of the card number.
	 *
	 * @return Last 4 digits of the card number.
	 */
	public String getNumberLastDigits() {

		return this.numberLastDigits;
	}

	/**
	 * Set last digits of the card number.
	 *
	 * @param numberLastDigits Last 4 digits of the card number.
	 */
	public void setNumberLastDigits(final String numberLastDigits) {

		this.numberLastDigits = numberLastDigits;
	}

	/**
	 * Get card expiration date.
	 *
	 * @return Expiration date in "yyyy-MM" format.
	 */
	public String getExpirationDate() {

		return this.expirationDate;
	}

	/**
	 * Set card expiration date.
	 *
	 * @param expirationDate Expiration date in "yyyy-MM" format.
	 */
	public void setExpirationDate(final String expirationDate) {

		this.expirationDate = expirationDate;
	}

	/**
	 * Get card code for CCV.
	 *
	 * @return Card code.
	 */
	public String getCode() {

		return this.code;
	}

	/**
	 * Set card code for CCV.
	 *
	 * @param code Card code.
	 */
	public void setCode(final String code) {

		this.code = code;
	}
}
