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

package eu.strasbourg.service.gtfs.model;

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
 * This class is a wrapper for {@link CalendarDate}.
 * </p>
 *
 * @author Cedric Henry
 * @see CalendarDate
 * @generated
 */
@ProviderType
public class CalendarDateWrapper implements CalendarDate,
	ModelWrapper<CalendarDate> {
	public CalendarDateWrapper(CalendarDate calendarDate) {
		_calendarDate = calendarDate;
	}

	@Override
	public Class<?> getModelClass() {
		return CalendarDate.class;
	}

	@Override
	public String getModelClassName() {
		return CalendarDate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("service_id", getService_id());
		attributes.put("date", getDate());
		attributes.put("exception_type", getException_type());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String service_id = (String)attributes.get("service_id");

		if (service_id != null) {
			setService_id(service_id);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Integer exception_type = (Integer)attributes.get("exception_type");

		if (exception_type != null) {
			setException_type(exception_type);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _calendarDate.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _calendarDate.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _calendarDate.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _calendarDate.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.CalendarDate> toCacheModel() {
		return _calendarDate.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate toEscapedModel() {
		return new CalendarDateWrapper(_calendarDate.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate toUnescapedModel() {
		return new CalendarDateWrapper(_calendarDate.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.gtfs.model.CalendarDate calendarDate) {
		return _calendarDate.compareTo(calendarDate);
	}

	/**
	* Returns the exception_type of this calendar date.
	*
	* @return the exception_type of this calendar date
	*/
	@Override
	public int getException_type() {
		return _calendarDate.getException_type();
	}

	@Override
	public int hashCode() {
		return _calendarDate.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendarDate.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CalendarDateWrapper((CalendarDate)_calendarDate.clone());
	}

	/**
	* Returns the service_id of this calendar date.
	*
	* @return the service_id of this calendar date
	*/
	@Override
	public java.lang.String getService_id() {
		return _calendarDate.getService_id();
	}

	/**
	* Returns the uuid of this calendar date.
	*
	* @return the uuid of this calendar date
	*/
	@Override
	public java.lang.String getUuid() {
		return _calendarDate.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _calendarDate.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _calendarDate.toXmlString();
	}

	/**
	* Returns the date of this calendar date.
	*
	* @return the date of this calendar date
	*/
	@Override
	public Date getDate() {
		return _calendarDate.getDate();
	}

	/**
	* Returns the ID of this calendar date.
	*
	* @return the ID of this calendar date
	*/
	@Override
	public long getId() {
		return _calendarDate.getId();
	}

	/**
	* Returns the primary key of this calendar date.
	*
	* @return the primary key of this calendar date
	*/
	@Override
	public long getPrimaryKey() {
		return _calendarDate.getPrimaryKey();
	}

	@Override
	public void persist() {
		_calendarDate.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_calendarDate.setCachedModel(cachedModel);
	}

	/**
	* Sets the date of this calendar date.
	*
	* @param date the date of this calendar date
	*/
	@Override
	public void setDate(Date date) {
		_calendarDate.setDate(date);
	}

	/**
	* Sets the exception_type of this calendar date.
	*
	* @param exception_type the exception_type of this calendar date
	*/
	@Override
	public void setException_type(int exception_type) {
		_calendarDate.setException_type(exception_type);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_calendarDate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_calendarDate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_calendarDate.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this calendar date.
	*
	* @param id the ID of this calendar date
	*/
	@Override
	public void setId(long id) {
		_calendarDate.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_calendarDate.setNew(n);
	}

	/**
	* Sets the primary key of this calendar date.
	*
	* @param primaryKey the primary key of this calendar date
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_calendarDate.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_calendarDate.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service_id of this calendar date.
	*
	* @param service_id the service_id of this calendar date
	*/
	@Override
	public void setService_id(java.lang.String service_id) {
		_calendarDate.setService_id(service_id);
	}

	/**
	* Sets the uuid of this calendar date.
	*
	* @param uuid the uuid of this calendar date
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_calendarDate.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarDateWrapper)) {
			return false;
		}

		CalendarDateWrapper calendarDateWrapper = (CalendarDateWrapper)obj;

		if (Objects.equals(_calendarDate, calendarDateWrapper._calendarDate)) {
			return true;
		}

		return false;
	}

	@Override
	public CalendarDate getWrappedModel() {
		return _calendarDate;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _calendarDate.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _calendarDate.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_calendarDate.resetOriginalValues();
	}

	private final CalendarDate _calendarDate;
}