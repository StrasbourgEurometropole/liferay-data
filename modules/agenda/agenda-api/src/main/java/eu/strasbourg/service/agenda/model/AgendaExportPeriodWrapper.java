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

package eu.strasbourg.service.agenda.model;

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
 * This class is a wrapper for {@link AgendaExportPeriod}.
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExportPeriod
 * @generated
 */
@ProviderType
public class AgendaExportPeriodWrapper implements AgendaExportPeriod,
	ModelWrapper<AgendaExportPeriod> {
	public AgendaExportPeriodWrapper(AgendaExportPeriod agendaExportPeriod) {
		_agendaExportPeriod = agendaExportPeriod;
	}

	@Override
	public Class<?> getModelClass() {
		return AgendaExportPeriod.class;
	}

	@Override
	public String getModelClassName() {
		return AgendaExportPeriod.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("agendaExportPeriodId", getAgendaExportPeriodId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("agendaExportId", getAgendaExportId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long agendaExportPeriodId = (Long)attributes.get("agendaExportPeriodId");

		if (agendaExportPeriodId != null) {
			setAgendaExportPeriodId(agendaExportPeriodId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Long agendaExportId = (Long)attributes.get("agendaExportId");

		if (agendaExportId != null) {
			setAgendaExportId(agendaExportId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _agendaExportPeriod.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _agendaExportPeriod.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _agendaExportPeriod.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _agendaExportPeriod.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.AgendaExportPeriod> toCacheModel() {
		return _agendaExportPeriod.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod toEscapedModel() {
		return new AgendaExportPeriodWrapper(_agendaExportPeriod.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod toUnescapedModel() {
		return new AgendaExportPeriodWrapper(_agendaExportPeriod.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod) {
		return _agendaExportPeriod.compareTo(agendaExportPeriod);
	}

	@Override
	public int hashCode() {
		return _agendaExportPeriod.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _agendaExportPeriod.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AgendaExportPeriodWrapper((AgendaExportPeriod)_agendaExportPeriod.clone());
	}

	/**
	* Returns the uuid of this agenda export period.
	*
	* @return the uuid of this agenda export period
	*/
	@Override
	public java.lang.String getUuid() {
		return _agendaExportPeriod.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _agendaExportPeriod.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _agendaExportPeriod.toXmlString();
	}

	/**
	* Returns the end date of this agenda export period.
	*
	* @return the end date of this agenda export period
	*/
	@Override
	public Date getEndDate() {
		return _agendaExportPeriod.getEndDate();
	}

	/**
	* Returns the start date of this agenda export period.
	*
	* @return the start date of this agenda export period
	*/
	@Override
	public Date getStartDate() {
		return _agendaExportPeriod.getStartDate();
	}

	/**
	* Returns the agenda export ID of this agenda export period.
	*
	* @return the agenda export ID of this agenda export period
	*/
	@Override
	public long getAgendaExportId() {
		return _agendaExportPeriod.getAgendaExportId();
	}

	/**
	* Returns the agenda export period ID of this agenda export period.
	*
	* @return the agenda export period ID of this agenda export period
	*/
	@Override
	public long getAgendaExportPeriodId() {
		return _agendaExportPeriod.getAgendaExportPeriodId();
	}

	/**
	* Returns the primary key of this agenda export period.
	*
	* @return the primary key of this agenda export period
	*/
	@Override
	public long getPrimaryKey() {
		return _agendaExportPeriod.getPrimaryKey();
	}

	@Override
	public void persist() {
		_agendaExportPeriod.persist();
	}

	/**
	* Sets the agenda export ID of this agenda export period.
	*
	* @param agendaExportId the agenda export ID of this agenda export period
	*/
	@Override
	public void setAgendaExportId(long agendaExportId) {
		_agendaExportPeriod.setAgendaExportId(agendaExportId);
	}

	/**
	* Sets the agenda export period ID of this agenda export period.
	*
	* @param agendaExportPeriodId the agenda export period ID of this agenda export period
	*/
	@Override
	public void setAgendaExportPeriodId(long agendaExportPeriodId) {
		_agendaExportPeriod.setAgendaExportPeriodId(agendaExportPeriodId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_agendaExportPeriod.setCachedModel(cachedModel);
	}

	/**
	* Sets the end date of this agenda export period.
	*
	* @param endDate the end date of this agenda export period
	*/
	@Override
	public void setEndDate(Date endDate) {
		_agendaExportPeriod.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_agendaExportPeriod.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_agendaExportPeriod.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_agendaExportPeriod.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_agendaExportPeriod.setNew(n);
	}

	/**
	* Sets the primary key of this agenda export period.
	*
	* @param primaryKey the primary key of this agenda export period
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_agendaExportPeriod.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_agendaExportPeriod.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this agenda export period.
	*
	* @param startDate the start date of this agenda export period
	*/
	@Override
	public void setStartDate(Date startDate) {
		_agendaExportPeriod.setStartDate(startDate);
	}

	/**
	* Sets the uuid of this agenda export period.
	*
	* @param uuid the uuid of this agenda export period
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_agendaExportPeriod.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AgendaExportPeriodWrapper)) {
			return false;
		}

		AgendaExportPeriodWrapper agendaExportPeriodWrapper = (AgendaExportPeriodWrapper)obj;

		if (Objects.equals(_agendaExportPeriod,
					agendaExportPeriodWrapper._agendaExportPeriod)) {
			return true;
		}

		return false;
	}

	@Override
	public AgendaExportPeriod getWrappedModel() {
		return _agendaExportPeriod;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _agendaExportPeriod.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _agendaExportPeriod.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_agendaExportPeriod.resetOriginalValues();
	}

	private final AgendaExportPeriod _agendaExportPeriod;
}