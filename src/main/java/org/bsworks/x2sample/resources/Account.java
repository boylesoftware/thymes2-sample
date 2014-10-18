package org.bsworks.x2sample.resources;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bsworks.x2.Actor;
import org.bsworks.x2.resource.ResourcePropertyAccess;
import org.bsworks.x2.resource.annotations.AccessRestriction;
import org.bsworks.x2.resource.annotations.Persistence;
import org.bsworks.x2.resource.annotations.PersistentResource;
import org.bsworks.x2.resource.annotations.Property;
import org.bsworks.x2.resource.validation.constraints.Email;
import org.bsworks.x2.resource.validation.groups.Create;
import org.bsworks.x2.util.Hex;


/**
 * User account.
 *
 * @author Lev Himmelfarb
 */
@PersistentResource(persistentCollection="account")
public class Account
	extends AbstractPersistentResource
	implements Actor {

	/**
	 * User e-mail address.
	 */
	@Property(persistence=@Persistence(field="email"))
	@NotNull
	@Size(min=1, max=50)
	@Email
	private String email;

	/**
	 * User's 128-bit AES secret key in hexadecimal encoding.
	 */
	@Property(persistence=@Persistence(field="secret"),
		updateIfNull=false,
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SEE),
			@AccessRestriction(ResourcePropertyAccess.SUBMIT)
		})
	@Pattern(regexp="[0-9a-f]{32}")
	private String secretKeyHex;

	/**
	 * User password.
	 */
	@Property(
		updateIfNull=false,
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SEE)
		})
	@NotNull(groups={ Create.class })
	private String password;

	/**
	 * User password SHA-1 digest in hexadecimal encoding.
	 *
	 * <p>Automatically updated by {@link #setPassword(String)}.
	 */
	@Property(persistence=@Persistence(field="pwddigest"),
		updateIfNull=false,
		accessRestrictions={
			@AccessRestriction(ResourcePropertyAccess.SEE),
			@AccessRestriction(ResourcePropertyAccess.SUBMIT)
		})
	@Pattern(regexp="[0-9a-f]{40}")
	private String passwordDigestHex;

	/**
	 * Tells if the user is an administrator.
	 */
	@Property(persistence=@Persistence(field="is_admin"),
		accessRestrictions={
			@AccessRestriction(value=ResourcePropertyAccess.SUBMIT,
					allowTo={ "admin" }),
			@AccessRestriction(value=ResourcePropertyAccess.UPDATE,
					allowTo={ "admin" })
		})
	private boolean admin;

	/**
	 * User first name.
	 */
	@Property(persistence=@Persistence(field="fname"))
	@NotNull
	@Size(min=1, max=50)
	private String firstName;

	/**
	 * User last name.
	 */
	@Property(persistence=@Persistence(field="lname"))
	@NotNull
	@Size(min=1, max=50)
	private String lastName;


	/**
	 * Returns user e-mail.
	 */
	@Override
	public String getUsername() {

		return this.email;
	}

	/**
	 * Opaque value is not used, returns {@code null}.
	 */
	@Override
	public String getOpaque() {

		return null;
	}

	/**
	 * Returns the AES key.
	 */
	@Override
	public SecretKey getSecretKey() {

		return new SecretKeySpec(Hex.decode(this.secretKeyHex), "AES");
	}

	/**
	 * Returns the password SHA-1 digest bytes.
	 */
	@Override
	public byte[] getCredentials() {

		return Hex.decode(this.passwordDigestHex);
	}

	/**
	 * Checks the "admin" role.
	 */
	@Override
	public boolean hasRole(final String role) {

		return ("admin".equals(role) && this.admin);
	}

	/**
	 * Checks the "admin" role.
	 */
	@Override
	public boolean hasAnyRole(Set<String> roles) {

		return (roles.contains("admin") && this.admin);
	}


	/**
	 * Get user e-mail address.
	 *
	 * @return User e-mail address.
	 */
	public String getEmail() {

		return this.email;
	}

	/**
	 * Set user e-mail address.
	 *
	 * @param email User e-mail address.
	 */
	public void setEmail(final String email) {

		this.email = email;
	}

	/**
	 * Get user secret key.
	 *
	 * @return 128-bit AES secret key in hexadecimal encoding.
	 */
	public String getSecretKeyHex() {

		return this.secretKeyHex;
	}

	/**
	 * Set user secret key.
	 *
	 * @param secretKeyHex 128-bit AES secret key in hexadecimal encoding.
	 */
	public void setSecretKeyHex(final String secretKeyHex) {

		this.secretKeyHex = secretKeyHex;
	}

	/**
	 * Get user password.
	 *
	 * @return User password.
	 */
	public String getPassword() {

		return this.password;
	}

	/**
	 * Set user password.
	 *
	 * @param password User password.
	 */
	public void setPassword(final String password) {

		this.password = password;

		if (password == null) {
			this.passwordDigestHex = null;
		} else {
			try {
				this.passwordDigestHex = Hex.encode(
						MessageDigest.getInstance("SHA-1").digest(
								password.getBytes(Charset.forName("UTF-8"))));
			} catch (final NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Get user password digest.
	 *
	 * @return SHA-1 digest in hexadecimal encoding.
	 */
	public String getPasswordDigestHex() {

		return this.passwordDigestHex;
	}

	/**
	 * Set user password digest.
	 *
	 * @param passwordDigestHex SHA-1 digest in hexadecimal encoding.
	 */
	public void setPasswordDigestHex(final String passwordDigestHex) {

		this.passwordDigestHex = passwordDigestHex;
	}

	/**
	 * Tell if the user is an administrator.
	 *
	 * @return {@code true} if the user is an administrator.
	 */
	public boolean isAdmin() {

		return this.admin;
	}

	/**
	 * Set flag that tells if the user is an administrator.
	 *
	 * @param admin {@code true} if the user is an administrator.
	 */
	public void setAdmin(final boolean admin) {

		this.admin = admin;
	}

	/**
	 * Get user first name.
	 *
	 * @return User first name.
	 */
	public String getFirstName() {

		return this.firstName;
	}

	/**
	 * Set user first name.
	 *
	 * @param firstName User first name.
	 */
	public void setFirstName(final String firstName) {

		this.firstName = firstName;
	}

	/**
	 * Get user last name.
	 *
	 * @return User last name.
	 */
	public String getLastName() {

		return this.lastName;
	}

	/**
	 * Set user last name.
	 *
	 * @param lastName User last name.
	 */
	public void setLastName(final String lastName) {

		this.lastName = lastName;
	}
}
