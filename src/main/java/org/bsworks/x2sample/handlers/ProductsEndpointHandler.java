package org.bsworks.x2sample.handlers;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.bsworks.x2.EndpointCallContext;
import org.bsworks.x2.EndpointCallErrorException;
import org.bsworks.x2.app.DefaultPersistentResourceEndpointHandler;
import org.bsworks.x2.resource.FilterConditionType;
import org.bsworks.x2.resource.Resources;
import org.bsworks.x2sample.resources.Product;


/**
 * Products management endpoint handler.
 *
 * @author Lev Himmelfarb
 */
public class ProductsEndpointHandler
	extends DefaultPersistentResourceEndpointHandler<Product> {

	/**
	 * Create new handler.
	 *
	 * @param sc Servlet context.
	 * @param resources Application resources manager.
	 */
	public ProductsEndpointHandler(
			@SuppressWarnings("unused") final ServletContext sc,
			final Resources resources) {
		super(resources.getPersistentResourceHandler(Product.class));
	}


	@Override
	public void create(final EndpointCallContext ctx, final Product recTmpl)
		throws EndpointCallErrorException {

		if (ctx.getPersistenceTransaction()
				.createPersistentResourceFetch(Product.class)
				.setFilter(ctx.getFilterSpec(Product.class)
						.addTrueCondition("title",
								FilterConditionType.EQ, recTmpl.getTitle()))
				.getCount() > 0)
			throw new EndpointCallErrorException(
					HttpServletResponse.SC_BAD_REQUEST,
					"A product with the same title already exists.");

		super.create(ctx, recTmpl);
	}

	@Override
	public Set<String> update(final EndpointCallContext ctx, final Product rec,
			final Product recTmpl)
		throws EndpointCallErrorException {

		if (ctx.getPersistenceTransaction()
				.createPersistentResourceFetch(Product.class)
				.setFilter(ctx.getFilterSpec(Product.class)
						.addTrueCondition("title",
								FilterConditionType.EQ, recTmpl.getTitle())
						.addTrueCondition("id",
								FilterConditionType.NE, rec.getId()))
				.getCount() > 0)
			throw new EndpointCallErrorException(
					HttpServletResponse.SC_BAD_REQUEST,
					"Another product with the same title exists.");

		return super.update(ctx, rec, recTmpl);
	}
}
