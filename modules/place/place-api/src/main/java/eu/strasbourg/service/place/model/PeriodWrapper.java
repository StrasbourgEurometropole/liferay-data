/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Period}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see Period
 * @generated
 */
@ProviderType
public class PeriodWrapper implements Period, ModelWrapper<Period> {
	public PeriodWrapper(Period period) {
		_period = period;
	}

	@Override
	public Class<?> getModelClass() {
		return Period.class;
	}

	@Override
	public String getModelClassName() {
		return Period.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("periodId", getPeriodId());
		attributes.put("name", getName());
		attributes.put("defaultPeriod", getDefaultPeriod());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("linkLabel", getLinkLabel());
		attributes.put("linkURL", getLinkURL());
		attributes.put("alwaysOpen", getAlwaysOpen());
		attributes.put("placeId", getPlaceId());
		attributes.put("subPlaceId", getSubPlaceId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long periodId = (Long)attributes.get("periodId");

		if (periodId != null) {
			setPeriodId(periodId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean defaultPeriod = (Boolean)attributes.get("defaultPeriod");

		if (defaultPeriod != null) {
			setDefaultPeriod(defaultPeriod);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String linkLabel = (String)attributes.get("linkLabel");

		if (linkLabel != null) {
			setLinkLabel(linkLabel);
		}

		String linkURL = (String)attributes.get("linkURL");

		if (linkURL != null) {
			setLinkURL(linkURL);
		}

		Boolean alwaysOpen = (Boolean)attributes.get("alwaysOpen");

		if (alwaysOpen != null) {
			setAlwaysOpen(alwaysOpen);
		}

		Long placeId = (Long)attributes.get("placeId");

		if (placeId != null) {
			setPlaceId(placeId);
		}

		Long subPlaceId = (Long)attributes.get("subPlaceId");

		if (subPlaceId != null) {
			setSubPlaceId(subPlaceId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _period.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _period.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _period.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _period.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.Period> toCacheModel() {
		return _period.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.Period toEscapedModel() {
		return new PeriodWrapper(_period.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.Period toUnescapedModel() {
		return new PeriodWrapper(_period.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.place.model.Period period) {
		return _period.compareTo(period);
	}

	@Override
	public int hashCode() {
		return _period.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _period.getPrimaryKeyObj();
	}

	/**
	* Returns the always open of this period.
	*
	* @return the always open of this period
	*/
	@Override
	public java.lang.Boolean getAlwaysOpen() {
		return _period.getAlwaysOpen();
	}

	/**
	* Returns the default period of this period.
	*
	* @return the default period of this period
	*/
	@Override
	public java.lang.Boolean getDefaultPeriod() {
		return _period.getDefaultPeriod();
	}

	@Override
	public java.lang.Object clone() {
		return new PeriodWrapper((Period)_period.clone());
	}

	/**
	* Returns the link label of this period.
	*
	* @return the link label of this period
	*/
	@Override
	public java.lang.String getLinkLabel() {
		return _period.getLinkLabel();
	}

	/**
	* Returns the link u r l of this period.
	*
	* @return the link u r l of this period
	*/
	@Override
	public java.lang.String getLinkURL() {
		return _period.getLinkURL();
	}

	/**
	* Returns the name of this period.
	*
	* @return the name of this period
	*/
	@Override
	public java.lang.String getName() {
		return _period.getName();
	}

	/**
	* Returns the uuid of this period.
	*
	* @return the uuid of this period
	*/
	@Override
	public java.lang.String getUuid() {
		return _period.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _period.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _period.toXmlString();
	}

	/**
	* Returns the end date of this period.
	*
	* @return the end date of this period
	*/
	@Override
	public Date getEndDate() {
		return _period.getEndDate();
	}

	/**
	* Returns the start date of this period.
	*
	* @return the start date of this period
	*/
	@Override
	public Date getStartDate() {
		return _period.getStartDate();
	}

	/**
	* Retourne les Slots de la période
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Slot> getSlots() {
		return _period.getSlots();
	}

	/**
	* Returns the period ID of this period.
	*
	* @return the period ID of this period
	*/
	@Override
	public long getPeriodId() {
		return _period.getPeriodId();
	}

	/**
	* Returns the place ID of this period.
	*
	* @return the place ID of this period
	*/
	@Override
	public long getPlaceId() {
		return _period.getPlaceId();
	}

	/**
	* Returns the primary key of this period.
	*
	* @return the primary key of this period
	*/
	@Override
	public long getPrimaryKey() {
		return _period.getPrimaryKey();
	}

	/**
	* Returns the sub place ID of this period.
	*
	* @return the sub place ID of this period
	*/
	@Override
	public long getSubPlaceId() {
		return _period.getSubPlaceId();
	}

	@Override
	public void persist() {
		_period.persist();
	}

	/**
	* Sets the always open of this period.
	*
	* @param alwaysOpen the always open of this period
	*/
	@Override
	public void setAlwaysOpen(java.lang.Boolean alwaysOpen) {
		_period.setAlwaysOpen(alwaysOpen);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_period.setCachedModel(cachedModel);
	}

	/**
	* Sets the default period of this period.
	*
	* @param defaultPeriod the default period of this period
	*/
	@Override
	public void setDefaultPeriod(java.lang.Boolean defaultPeriod) {
		_period.setDefaultPeriod(defaultPeriod);
	}

	/**
	* Sets the end date of this period.
	*
	* @param endDate the end date of this period
	*/
	@Override
	public void setEndDate(Date endDate) {
		_period.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_period.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_period.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_period.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the link label of this period.
	*
	* @param linkLabel the link label of this period
	*/
	@Override
	public void setLinkLabel(java.lang.String linkLabel) {
		_period.setLinkLabel(linkLabel);
	}

	/**
	* Sets the link u r l of this period.
	*
	* @param linkURL the link u r l of this period
	*/
	@Override
	public void setLinkURL(java.lang.String linkURL) {
		_period.setLinkURL(linkURL);
	}

	/**
	* Sets the name of this period.
	*
	* @param name the name of this period
	*/
	@Override
	public void setName(java.lang.String name) {
		_period.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_period.setNew(n);
	}

	/**
	* Sets the period ID of this period.
	*
	* @param periodId the period ID of this period
	*/
	@Override
	public void setPeriodId(long periodId) {
		_period.setPeriodId(periodId);
	}

	/**
	* Sets the place ID of this period.
	*
	* @param placeId the place ID of this period
	*/
	@Override
	public void setPlaceId(long placeId) {
		_period.setPlaceId(placeId);
	}

	/**
	* Sets the primary key of this period.
	*
	* @param primaryKey the primary key of this period
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_period.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_period.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this period.
	*
	* @param startDate the start date of this period
	*/
	@Override
	public void setStartDate(Date startDate) {
		_period.setStartDate(startDate);
	}

	/**
	* Sets the sub place ID of this period.
	*
	* @param subPlaceId the sub place ID of this period
	*/
	@Override
	public void setSubPlaceId(long subPlaceId) {
		_period.setSubPlaceId(subPlaceId);
	}

	/**
	* Sets the uuid of this period.
	*
	* @param uuid the uuid of this period
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_period.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PeriodWrapper)) {
			return false;
		}

		PeriodWrapper periodWrapper = (PeriodWrapper)obj;

		if (Objects.equals(_period, periodWrapper._period)) {
			return true;
		}

		return false;
	}

	@Override
	public Period getWrappedModel() {
		return _period;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _period.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _period.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_period.resetOriginalValues();
	}

	private final Period _period;
}