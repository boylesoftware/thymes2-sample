package org.bsworks.x2sample.resources;

import org.bsworks.x2.resource.annotations.TypeProperty;


/**
 * Abstract payment information.
 *
 * @author Lev Himmelfarb
 */
public abstract class PaymentInfo {

	/**
	 * Payment method.
	 */
	@TypeProperty(persistent=false)
	private final PaymentMethod method;


	/**
	 * Create new object.
	 *
	 * @param method Payment method.
	 */
	protected PaymentInfo(final PaymentMethod method) {

		this.method = method;
	}


	/**
	 * Get payment method.
	 *
	 * @return Payment method.
	 */
	public PaymentMethod getMethod() {

		return this.method;
	}
}
