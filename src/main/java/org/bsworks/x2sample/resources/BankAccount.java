package org.bsworks.x2sample.resources;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.bsworks.x2.resource.ResourcePropertyAccess;
import org.bsworks.x2.resource.annotations.AccessRestriction;
import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.Property;
import org.bsworks.x2.resource.validation.constraints.RoutingNumber;


/**
 * Bank account information.
 *
 * @author Lev Himmelfarb
 */
public class BankAccount
	extends PaymentInfo {

	/**
	 * Account type.
	 */
	@Property(persistence=@Persistence(field="type"))
	@NotNull
	private BankAccountType type;

	/**
	 * Routing number.
	 */
	@Property(
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SEE)
		})
	@NotNull
	@RoutingNumber
	private String routingNumber;

	/**
	 * Account number.
	 */
	@Property(
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SEE)
		})
	@NotNull
	@Pattern(regexp="\\d{5,17}")
	private String number;

	/**
	 * Last 4 digits of the account number.
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
	 * Create new object.
	 */
	public BankAccount() {
		super(PaymentMethod.ACH_TRANSFER);
	}


	/**
	 * Get account type.
	 *
	 * @return Account type.
	 */
	public BankAccountType getType() {

		return this.type;
	}

	/**
	 * Set account type.
	 *
	 * @param type Account type.
	 */
	public void setType(final BankAccountType type) {

		this.type = type;
	}

	/**
	 * Get bank routing number.
	 *
	 * @return The routing number.
	 */
	public String getRoutingNumber() {

		return this.routingNumber;
	}

	/**
	 * Set bank routing number.
	 *
	 * @param routingNumber The routing number.
	 */
	public void setRoutingNumber(final String routingNumber) {

		this.routingNumber = routingNumber;
	}

	/**
	 * Get account number.
	 *
	 * @return Account number.
	 */
	public String getNumber() {

		return this.number;
	}

	/**
	 * Set account number.
	 *
	 * @param number Account number.
	 */
	public void setNumber(final String number) {

		this.number = number;

		this.numberLastDigits = ((number != null) && (number.length() >= 4) ?
				number.substring(number.length() - 4) : null);
	}

	/**
	 * Get last digits of the account number.
	 *
	 * @return Last 4 digits of the account number.
	 */
	public String getNumberLastDigits() {

		return this.numberLastDigits;
	}

	/**
	 * Set last digits of the account number.
	 *
	 * @param numberLastDigits Last 4 digits of the account number.
	 */
	public void setNumberLastDigits(final String numberLastDigits) {

		this.numberLastDigits = numberLastDigits;
	}
}
