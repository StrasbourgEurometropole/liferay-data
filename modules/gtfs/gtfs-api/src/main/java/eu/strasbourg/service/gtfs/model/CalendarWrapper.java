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
 * This class is a wrapper for {@link Calendar}.
 * </p>
 *
 * @author Cedric Henry
 * @see Calendar
 * @generated
 */
@ProviderType
public class CalendarWrapper implements Calendar, ModelWrapper<Calendar> {

	public CalendarWrapper(Calendar calendar) {
		_calendar = calendar;
	}

	@Override
	public Class<?> getModelClass() {
		return Calendar.class;
	}

	@Override
	public String getModelClassName() {
		return Calendar.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("service_id", getService_id());
		attributes.put("start_date", getStart_date());
		attributes.put("end_date", getEnd_date());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String service_id = (String)attributes.get("service_id");

		if (service_id != null) {
			setService_id(service_id);
		}

		Date start_date = (Date)attributes.get("start_date");

		if (start_date != null) {
			setStart_date(start_date);
		}

		Date end_date = (Date)attributes.get("end_date");

		if (end_date != null) {
			setEnd_date(end_date);
		}
	}

	@Override
	public Object clone() {
		return new CalendarWrapper((Calendar)_calendar.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Calendar calendar) {
		return _calendar.compareTo(calendar);
	}

	/**
	 * Returns the end_date of this calendar.
	 *
	 * @return the end_date of this calendar
	 */
	@Override
	public Date getEnd_date() {
		return _calendar.getEnd_date();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _calendar.getExpandoBridge();
	}

	/**
	 * Returns the ID of this calendar.
	 *
	 * @return the ID of this calendar
	 */
	@Override
	public long getId() {
		return _calendar.getId();
	}

	/**
	 * Returns the primary key of this calendar.
	 *
	 * @return the primary key of this calendar
	 */
	@Override
	public long getPrimaryKey() {
		return _calendar.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendar.getPrimaryKeyObj();
	}

	/**
	 * Returns the service_id of this calendar.
	 *
	 * @return the service_id of this calendar
	 */
	@Override
	public String getService_id() {
		return _calendar.getService_id();
	}

	/**
	 * Returns the start_date of this calendar.
	 *
	 * @return the start_date of this calendar
	 */
	@Override
	public Date getStart_date() {
		return _calendar.getStart_date();
	}

	@Override
	public int hashCode() {
		return _calendar.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _calendar.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _calendar.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _calendar.isNew();
	}

	@Override
	public void persist() {
		_calendar.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_calendar.setCachedModel(cachedModel);
	}

	/**
	 * Sets the end_date of this calendar.
	 *
	 * @param end_date the end_date of this calendar
	 */
	@Override
	public void setEnd_date(Date end_date) {
		_calendar.setEnd_date(end_date);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_calendar.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_calendar.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_calendar.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the ID of this calendar.
	 *
	 * @param id the ID of this calendar
	 */
	@Override
	public void setId(long id) {
		_calendar.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_calendar.setNew(n);
	}

	/**
	 * Sets the primary key of this calendar.
	 *
	 * @param primaryKey the primary key of this calendar
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_calendar.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_calendar.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the service_id of this calendar.
	 *
	 * @param service_id the service_id of this calendar
	 */
	@Override
	public void setService_id(String service_id) {
		_calendar.setService_id(service_id);
	}

	/**
	 * Sets the start_date of this calendar.
	 *
	 * @param start_date the start_date of this calendar
	 */
	@Override
	public void setStart_date(Date start_date) {
		_calendar.setStart_date(start_date);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.gtfs.model.Calendar> toCacheModel() {

		return _calendar.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Calendar toEscapedModel() {
		return new CalendarWrapper(_calendar.toEscapedModel());
	}

	@Override
	public String toString() {
		return _calendar.toString();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Calendar toUnescapedModel() {
		return new CalendarWrapper(_calendar.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _calendar.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarWrapper)) {
			return false;
		}

		CalendarWrapper calendarWrapper = (CalendarWrapper)obj;

		if (Objects.equals(_calendar, calendarWrapper._calendar)) {
			return true;
		}

		return false;
	}

	@Override
	public Calendar getWrappedModel() {
		return _calendar;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _calendar.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _calendar.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_calendar.resetOriginalValues();
	}

	private final Calendar _calendar;

}