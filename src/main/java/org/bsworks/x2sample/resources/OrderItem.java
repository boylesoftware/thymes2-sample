package org.bsworks.x2sample.resources;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.bsworks.x2.resource.IdHandling;
import org.bsworks.x2.resource.Ref;
import org.bsworks.x2.resource.annotations.IdProperty;
import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.Property;


/**
 * Order item.
 *
 * @author Lev Himmelfarb
 */
public class OrderItem {

	/**
	 * Item id.
	 */
	@IdProperty(handling=IdHandling.AUTO_GENERATED, persistentField="id")
	private Integer id;

	/**
	 * Product reference.
	 */
	@Property(persistence=@Persistence(field="product_id"))
	@NotNull
	private Ref<Product> product;

	/**
	 * Order quantity.
	 */
	@Property(persistence=@Persistence(field="qty"))
	@Min(1)
	private int quantity;


	/**
	 * Get item id.
	 *
	 * @return Item id.
	 */
	public Integer getId() {

		return this.id;
	}

	/**
	 * Set item id.
	 *
	 * @param id Item id.
	 */
	public void setId(final Integer id) {

		this.id = id;
	}

	/**
	 * Get product.
	 *
	 * @return Product reference.
	 */
	public Ref<Product> getProduct() {

		return this.product;
	}

	/**
	 * Set product.
	 *
	 * @param product Product reference.
	 */
	public void setProduct(final Ref<Product> product) {

		this.product = product;
	}

	/**
	 * Get order quantity,
	 *
	 * @return Order quantity.
	 */
	public int getQuantity() {

		return this.quantity;
	}

	/**
	 * Set order quantity.
	 *
	 * @param quantity Order quantity.
	 */
	public void setQuantity(final int quantity) {

		this.quantity = quantity;
	}
}
