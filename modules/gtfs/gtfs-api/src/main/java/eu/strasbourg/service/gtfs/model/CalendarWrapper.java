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

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("service_id", getService_id());
		attributes.put("monday", getMonday());
		attributes.put("tuesday", getTuesday());
		attributes.put("wednesday", getWednesday());
		attributes.put("thursday", getThursday());
		attributes.put("friday", getFriday());
		attributes.put("saturday", getSaturday());
		attributes.put("sunday", getSunday());
		attributes.put("start_date", getStart_date());
		attributes.put("end_date", getEnd_date());

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

		Boolean monday = (Boolean)attributes.get("monday");

		if (monday != null) {
			setMonday(monday);
		}

		Boolean tuesday = (Boolean)attributes.get("tuesday");

		if (tuesday != null) {
			setTuesday(tuesday);
		}

		Boolean wednesday = (Boolean)attributes.get("wednesday");

		if (wednesday != null) {
			setWednesday(wednesday);
		}

		Boolean thursday = (Boolean)attributes.get("thursday");

		if (thursday != null) {
			setThursday(thursday);
		}

		Boolean friday = (Boolean)attributes.get("friday");

		if (friday != null) {
			setFriday(friday);
		}

		Boolean saturday = (Boolean)attributes.get("saturday");

		if (saturday != null) {
			setSaturday(saturday);
		}

		Boolean sunday = (Boolean)attributes.get("sunday");

		if (sunday != null) {
			setSunday(sunday);
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

	/**
	* Returns the friday of this calendar.
	*
	* @return the friday of this calendar
	*/
	@Override
	public boolean getFriday() {
		return _calendar.getFriday();
	}

	/**
	* Returns the monday of this calendar.
	*
	* @return the monday of this calendar
	*/
	@Override
	public boolean getMonday() {
		return _calendar.getMonday();
	}

	/**
	* Returns the saturday of this calendar.
	*
	* @return the saturday of this calendar
	*/
	@Override
	public boolean getSaturday() {
		return _calendar.getSaturday();
	}

	/**
	* Returns the sunday of this calendar.
	*
	* @return the sunday of this calendar
	*/
	@Override
	public boolean getSunday() {
		return _calendar.getSunday();
	}

	/**
	* Returns the thursday of this calendar.
	*
	* @return the thursday of this calendar
	*/
	@Override
	public boolean getThursday() {
		return _calendar.getThursday();
	}

	/**
	* Returns the tuesday of this calendar.
	*
	* @return the tuesday of this calendar
	*/
	@Override
	public boolean getTuesday() {
		return _calendar.getTuesday();
	}

	/**
	* Returns the wednesday of this calendar.
	*
	* @return the wednesday of this calendar
	*/
	@Override
	public boolean getWednesday() {
		return _calendar.getWednesday();
	}

	@Override
	public boolean isCachedModel() {
		return _calendar.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _calendar.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this calendar is friday.
	*
	* @return <code>true</code> if this calendar is friday; <code>false</code> otherwise
	*/
	@Override
	public boolean isFriday() {
		return _calendar.isFriday();
	}

	/**
	* Returns <code>true</code> if this calendar is monday.
	*
	* @return <code>true</code> if this calendar is monday; <code>false</code> otherwise
	*/
	@Override
	public boolean isMonday() {
		return _calendar.isMonday();
	}

	@Override
	public boolean isNew() {
		return _calendar.isNew();
	}

	/**
	* Returns <code>true</code> if this calendar is saturday.
	*
	* @return <code>true</code> if this calendar is saturday; <code>false</code> otherwise
	*/
	@Override
	public boolean isSaturday() {
		return _calendar.isSaturday();
	}

	/**
	* Returns <code>true</code> if this calendar is sunday.
	*
	* @return <code>true</code> if this calendar is sunday; <code>false</code> otherwise
	*/
	@Override
	public boolean isSunday() {
		return _calendar.isSunday();
	}

	/**
	* Returns <code>true</code> if this calendar is thursday.
	*
	* @return <code>true</code> if this calendar is thursday; <code>false</code> otherwise
	*/
	@Override
	public boolean isThursday() {
		return _calendar.isThursday();
	}

	/**
	* Returns <code>true</code> if this calendar is tuesday.
	*
	* @return <code>true</code> if this calendar is tuesday; <code>false</code> otherwise
	*/
	@Override
	public boolean isTuesday() {
		return _calendar.isTuesday();
	}

	/**
	* Returns <code>true</code> if this calendar is wednesday.
	*
	* @return <code>true</code> if this calendar is wednesday; <code>false</code> otherwise
	*/
	@Override
	public boolean isWednesday() {
		return _calendar.isWednesday();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _calendar.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Calendar> toCacheModel() {
		return _calendar.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Calendar toEscapedModel() {
		return new CalendarWrapper(_calendar.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Calendar toUnescapedModel() {
		return new CalendarWrapper(_calendar.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Calendar calendar) {
		return _calendar.compareTo(calendar);
	}

	@Override
	public int hashCode() {
		return _calendar.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendar.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CalendarWrapper((Calendar)_calendar.clone());
	}

	/**
	* Returns the service_id of this calendar.
	*
	* @return the service_id of this calendar
	*/
	@Override
	public java.lang.String getService_id() {
		return _calendar.getService_id();
	}

	/**
	* Returns the uuid of this calendar.
	*
	* @return the uuid of this calendar
	*/
	@Override
	public java.lang.String getUuid() {
		return _calendar.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _calendar.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _calendar.toXmlString();
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

	/**
	* Returns the start_date of this calendar.
	*
	* @return the start_date of this calendar
	*/
	@Override
	public Date getStart_date() {
		return _calendar.getStart_date();
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
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_calendar.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_calendar.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_calendar.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this calendar is friday.
	*
	* @param friday the friday of this calendar
	*/
	@Override
	public void setFriday(boolean friday) {
		_calendar.setFriday(friday);
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

	/**
	* Sets whether this calendar is monday.
	*
	* @param monday the monday of this calendar
	*/
	@Override
	public void setMonday(boolean monday) {
		_calendar.setMonday(monday);
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
	* Sets whether this calendar is saturday.
	*
	* @param saturday the saturday of this calendar
	*/
	@Override
	public void setSaturday(boolean saturday) {
		_calendar.setSaturday(saturday);
	}

	/**
	* Sets the service_id of this calendar.
	*
	* @param service_id the service_id of this calendar
	*/
	@Override
	public void setService_id(java.lang.String service_id) {
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

	/**
	* Sets whether this calendar is sunday.
	*
	* @param sunday the sunday of this calendar
	*/
	@Override
	public void setSunday(boolean sunday) {
		_calendar.setSunday(sunday);
	}

	/**
	* Sets whether this calendar is thursday.
	*
	* @param thursday the thursday of this calendar
	*/
	@Override
	public void setThursday(boolean thursday) {
		_calendar.setThursday(thursday);
	}

	/**
	* Sets whether this calendar is tuesday.
	*
	* @param tuesday the tuesday of this calendar
	*/
	@Override
	public void setTuesday(boolean tuesday) {
		_calendar.setTuesday(tuesday);
	}

	/**
	* Sets the uuid of this calendar.
	*
	* @param uuid the uuid of this calendar
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_calendar.setUuid(uuid);
	}

	/**
	* Sets whether this calendar is wednesday.
	*
	* @param wednesday the wednesday of this calendar
	*/
	@Override
	public void setWednesday(boolean wednesday) {
		_calendar.setWednesday(wednesday);
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