package org.bsworks.x2sample.resources;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bsworks.x2.resource.ResourcePropertyAccess;
import org.bsworks.x2.resource.annotations.AccessRestriction;
import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.PersistentResource;
import org.bsworks.x2.resource.annotations.Property;


/**
 * Product.
 *
 * @author Lev Himmelfarb
 */
@PersistentResource(persistentCollection="product", accessRestrictions={
	@AccessRestriction(value=ResourcePropertyAccess.SUBMIT,
			allowTo={ "admin" }),
	@AccessRestriction(value=ResourcePropertyAccess.DELETE,
			allowTo={ "admin" })
})
public class Product
	extends AbstractPersistentResource {

	/**
	 * Product title.
	 */
	@Property(persistence=@Persistence(field="title"))
	@NotNull
	@Size(min=1, max=50)
	private String title;

	/**
	 * Product price.
	 */
	@Property(persistence=@Persistence(field="price"))
	@NotNull
	@Digits(integer=3, fraction=2)
	@DecimalMin(value="0.00")
	private BigDecimal price;


	/**
	 * Get product title.
	 *
	 * @return Product title.
	 */
	public String getTitle() {

		return this.title;
	}

	/**
	 * Set product title.
	 *
	 * @param title Product title.
	 */
	public void setTitle(final String title) {

		this.title = title;
	}

	/**
	 * Get product price.
	 *
	 * @return Product price.
	 */
	public BigDecimal getPrice() {

		return this.price;
	}

	/**
	 * Set product price.
	 *
	 * @param price Product price.
	 */
	public void setPrice(final BigDecimal price) {

		this.price = price;
	}
}
