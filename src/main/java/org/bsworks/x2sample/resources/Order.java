package org.bsworks.x2sample.resources;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bsworks.x2.resource.Ref;
import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.PersistentResource;
import org.bsworks.x2.resource.annotations.Property;


/**
 * Order.
 *
 * @author Lev Himmelfarb
 */
@PersistentResource(persistentCollection="ord")
public class Order
	extends AbstractPersistentResource {

	/**
	 * Customer account reference.
	 */
	@Property(persistence=@Persistence(field="account_id"))
	@NotNull
	private Ref<Account> account;

	/**
	 * Order items.
	 */
	@Property(persistence=@Persistence(collection="ord_item",
			parentIdField="ord_id"))
	@NotNull
	@Size(min=1)
	@Valid
	private Set<OrderItem> items;


	/**
	 * Get customer account.
	 *
	 * @return Customer account reference.
	 */
	public Ref<Account> getAccount() {

		return this.account;
	}

	/**
	 * Set customer account.
	 *
	 * @param account Customer account reference.
	 */
	public void setAccount(final Ref<Account> account) {

		this.account = account;
	}

	/**
	 * Get order items.
	 *
	 * @return Order items.
	 */
	public Set<OrderItem> getItems() {

		return this.items;
	}

	/**
	 * Set order items.
	 *
	 * @param items Order items.
	 */
	public void setItems(final Set<OrderItem> items) {

		this.items = items;
	}
}
