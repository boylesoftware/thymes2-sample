package org.bsworks.x2sample.resources;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.Property;


/**
 * Address.
 *
 * @author Lev Himmelfarb
 */
public class Address {

	/**
	 * Name.
	 */
	@Property(persistence=@Persistence(field="name"))
	@NotNull
	@Size(min=1, max=50)
	private String name;

	/**
	 * Street address.
	 */
	@Property(persistence=@Persistence(field="street"))
	@NotNull
	@Size(min=1, max=50)
	private String street;

	/**
	 * Optional unit (apartment, room, suite, etc.).
	 */
	@Property(persistence=@Persistence(field="unit"))
	@Size(min=1, max=10)
	private String unit;

	/**
	 * City.
	 */
	@Property(persistence=@Persistence(field="city"))
	@NotNull
	@Size(min=1, max=30)
	private String city;

	/**
	 * 2-character state code.
	 */
	@Property(persistence=@Persistence(field="state"))
	@NotNull
	@Pattern(regexp="[A-Z]{2}")
	private String state;

	/**
	 * 5-digit ZIP code.
	 */
	@Property(persistence=@Persistence(field="zip"))
	@NotNull
	@Pattern(regexp="\\d{5}")
	private String zipCode;


	/**
	 * Get name.
	 *
	 * @return The name.
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * Set name.
	 *
	 * @param name The name.
	 */
	public void setName(final String name) {

		this.name = name;
	}

	/**
	 * Get street address.
	 *
	 * @return The street address.
	 */
	public String getStreet() {

		return this.street;
	}

	/**
	 * Set street address.
	 *
	 * @param street The street address.
	 */
	public void setStreet(final String street) {

		this.street = street;
	}

	/**
	 * Get optional unit (apartment, room, suite, etc.).
	 *
	 * @return The unit, or {@code null}.
	 */
	public String getUnit() {

		return this.unit;
	}

	/**
	 * Set optional unit (apartment, room, suite, etc.).
	 *
	 * @param unit The unit.
	 */
	public void setUnit(final String unit) {

		this.unit = unit;
	}

	/**
	 * Get city.
	 *
	 * @return The city.
	 */
	public String getCity() {

		return this.city;
	}

	/**
	 * Set city.
	 *
	 * @param city The city.
	 */
	public void setCity(final String city) {

		this.city = city;
	}

	/**
	 * Get state.
	 *
	 * @return 2-character state code.
	 */
	public String getState() {

		return this.state;
	}

	/**
	 * Set state.
	 *
	 * @param state 2-character state code.
	 */
	public void setState(final String state) {

		this.state = state;
	}

	/**
	 * Get ZIP code.
	 *
	 * @return 5-digit ZIP code.
	 */
	public String getZipCode() {

		return this.zipCode;
	}

	/**
	 * Set ZIP code.
	 *
	 * @param zipCode 5-digit ZIP code.
	 */
	public void setZipCode(final String zipCode) {

		this.zipCode = zipCode;
	}
}
