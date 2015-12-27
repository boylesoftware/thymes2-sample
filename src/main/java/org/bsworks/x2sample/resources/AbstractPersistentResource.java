package org.bsworks.x2sample.resources;

import java.util.Date;

import org.bsworks.x2.resource.IdHandling;
import org.bsworks.x2.resource.MetaPropertyType;
import org.bsworks.x2.resource.annotations.IdProperty;
import org.bsworks.x2.resource.annotations.MetaProperty;


/**
 * Common parent for persistent application resources.
 *
 * @author Lev Himmelfarb
 */
public abstract class AbstractPersistentResource {

	/**
	 * Record id.
	 */
	@IdProperty(handling=IdHandling.AUTO_GENERATED, persistentField="id")
	private Integer id;

	/**
	 * Record version number.
	 */
	@MetaProperty(type=MetaPropertyType.VERSION, persistentField="version")
	private Integer version;

	/**
	 * Record created timestamp.
	 */
	@MetaProperty(type=MetaPropertyType.CREATION_TIMESTAMP,
			persistentField="created_on")
	private Date createdOn;

	/**
	 * Actor that created the record.
	 */
	@MetaProperty(type=MetaPropertyType.CREATION_ACTOR,
			persistentField="created_by")
	private String createdBy;

	/**
	 * Record last modification timestamp.
	 */
	@MetaProperty(type=MetaPropertyType.MODIFICATION_TIMESTAMP,
			persistentField="modified_on")
	private Date lastModifiedOn;

	/**
	 * Actor that last modified the record.
	 */
	@MetaProperty(type=MetaPropertyType.MODIFICATION_ACTOR,
			persistentField="modified_by")
	private String lastModifiedBy;


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
	public Integer getVersion() {

		return this.version;
	}

	/**
	 * Set record version.
	 *
	 * @param version Record version number.
	 */
	public void setVersion(final Integer version) {

		this.version = version;
	}

	/**
	 * Get record creation timestamp.
	 *
	 * @return Record creation timestamp.
	 */
	public Date getCreatedOn() {

		return this.createdOn;
	}

	/**
	 * Set record creation timestamp.
	 *
	 * @param createdOn Record creation timestamp.
	 */
	public void setCreatedOn(final Date createdOn) {

		this.createdOn = createdOn;
	}

	/**
	 * Get actor that created the record.
	 *
	 * @return Actor username.
	 */
	public String getCreatedBy() {

		return this.createdBy;
	}

	/**
	 * Set actor that created the record.
	 *
	 * @param createdBy Actor username.
	 */
	public void setCreatedBy(final String createdBy) {

		this.createdBy = createdBy;
	}

	/**
	 * Get record last modification timestamp.
	 *
	 * @return Record last modification timestamp.
	 */
	public Date getLastModifiedOn() {

		return this.lastModifiedOn;
	}

	/**
	 * Set record last modification timestamp.
	 *
	 * @param lastModifiedOn Record last modification timestamp.
	 */
	public void setLastModifiedOn(final Date lastModifiedOn) {

		this.lastModifiedOn = lastModifiedOn;
	}

	/**
	 * Get actor that last modified the record.
	 *
	 * @return Actor username.
	 */
	public String getLastModifiedBy() {

		return this.lastModifiedBy;
	}

	/**
	 * Set actor that last modified the record.
	 *
	 * @param lastModifiedBy Actor username.
	 */
	public void setLastModifiedBy(final String lastModifiedBy) {

		this.lastModifiedBy = lastModifiedBy;
	}
}
