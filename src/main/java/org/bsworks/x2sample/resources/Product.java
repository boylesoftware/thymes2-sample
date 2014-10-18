package org.bsworks.x2sample.resources;

import java.math.BigDecimal;

import org.bsworks.x2.resource.IdHandling;
import org.bsworks.x2.resource.MetaPropertyType;
import org.bsworks.x2.resource.annotations.IdProperty;
import org.bsworks.x2.resource.annotations.MetaProperty;
import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.PersistentResource;
import org.bsworks.x2.resource.annotations.Property;


/**
 * Product.
 *
 * @author Lev Himmelfarb
 */
@PersistentResource(persistentCollection="product")
public class Product {

	/**
	 * Record id.
	 */
	@IdProperty(handling=IdHandling.AUTO_GENERATED, persistentField="id")
	private Integer id;

	/**
	 * Record version number.
	 */
	@MetaProperty(type=MetaPropertyType.VERSION, persistentField="version")
	private int version;

	/**
	 * Product title.
	 */
	@Property(persistence=@Persistence(field="title"))
	private String title;

	/**
	 * Product price.
	 */
	@Property(persistence=@Persistence(field="price"))
	private BigDecimal price;


	/**
	 * Get record id.
	 *
	 * @return Record id.
	 */
	public Integer getId() {

		return this.id;
	}

	/**
	 * Set record id.
	 *
	 * @param id Record id.
	 */
	public void setId(final Integer id) {

		this.id = id;
	}

	/**
	 * Get record version.
	 *
	 * @return Record version number.
	 */
	public int getVersion() {

		return this.version;
	}

	/**
	 * Set record version.
	 *
	 * @param version Record version number.
	 */
	public void setVersion(final int version) {

		this.version = version;
	}

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
