package org.bsworks.x2sample.handlers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.bsworks.x2.Actor;
import org.bsworks.x2.EndpointCallContext;
import org.bsworks.x2.EndpointCallErrorException;
import org.bsworks.x2.HttpMethod;
import org.bsworks.x2.app.DefaultPersistentResourceEndpointHandler;
import org.bsworks.x2.resource.FilterConditionType;
import org.bsworks.x2.resource.Resources;
import org.bsworks.x2.util.Hex;
import org.bsworks.x2sample.resources.Account;


/**
 * User accounts management endpoint handler.
 *
 * @author Lev Himmelfarb
 */
public class AccountsEndpointHandler
	extends DefaultPersistentResourceEndpointHandler<Account> {

	/**
	 * Create new handler.
	 *
	 * @param sc Servlet context.
	 * @param resources Application resources manager.
	 */
	public AccountsEndpointHandler(
			@SuppressWarnings("unused") final ServletContext sc,
			final Resources resources) {
		super(resources.getPersistentResourceHandler(Account.class));
	}


	@Override
	public boolean isAllowed(final HttpMethod requestMethod,
			final String requestURI, final List<String> uriParams,
			final Actor actor) {

		// admins are allowed anything
		if ((actor != null) && actor.hasRole("admin"))
			return true;

		// anyone can create a new account
		if (requestMethod == HttpMethod.POST)
			return true;

		// get addressed account id
		final Integer accountId = (uriParams.get(0) == null ? null :
			Integer.valueOf(uriParams.get(0)));

		// get caller account id
		final Integer callerId = (actor == null ? null :
			((Account) actor).getId());

		// you can only access your own account
		return ((callerId != null) && callerId.equals(accountId));
	}

	@Override
	public void create(final EndpointCallContext ctx, final Account recTmpl)
		throws EndpointCallErrorException {

		// make e-mail lower-case
		recTmpl.setEmail(recTmpl.getEmail().toLowerCase());

		// make context authenticated if not yet
		if (ctx.getActor() == null)
			ctx.assumeActor(recTmpl);

		// make sure the e-mail is not used
		if (ctx.getPersistenceTransaction()
				.createPersistentResourceFetch(Account.class)
				.setFilter(ctx.getFilterSpec(Account.class)
						.addTrueCondition("email",
								FilterConditionType.EQ, recTmpl.getEmail()))
				.getCount() > 0)
			throw new EndpointCallErrorException(
					HttpServletResponse.SC_BAD_REQUEST,
					"This e-mail address is already used.");

		// create secret key
		try {
			final KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			recTmpl.setSecretKeyHex(Hex.encode(
					keyGen.generateKey().getEncoded()));
		} catch (final NoSuchAlgorithmException e) {
			throw new RuntimeException("AES is not supported.", e);
		}

		// proceed with the account creation
		super.create(ctx, recTmpl);
	}

	@Override
	public Set<String> update(final EndpointCallContext ctx, final Account rec,
			final Account recTmpl)
		throws EndpointCallErrorException {

		// make sure the e-mail is not used
		if (ctx.getPersistenceTransaction()
				.createPersistentResourceFetch(Account.class)
				.setFilter(ctx.getFilterSpec(Account.class)
						.addTrueCondition("email",
								FilterConditionType.EQ, recTmpl.getEmail())
						.addTrueCondition("id",
								FilterConditionType.NE, rec.getId()))
				.getCount() > 0)
			throw new EndpointCallErrorException(
					HttpServletResponse.SC_BAD_REQUEST,
					"This e-mail address is used by another acccount.");

		// proceed with updating the account
		return super.update(ctx, rec, recTmpl);
	}
}
