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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the AgendaExportPeriod service. Represents a row in the &quot;agenda_AgendaExportPeriod&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExportPeriod
 * @see eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodImpl
 * @see eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl
 * @generated
 */
@ProviderType
public interface AgendaExportPeriodModel extends BaseModel<AgendaExportPeriod> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a agenda export period model instance should use the {@link AgendaExportPeriod} interface instead.
	 */

	/**
	 * Returns the primary key of this agenda export period.
	 *
	 * @return the primary key of this agenda export period
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this agenda export period.
	 *
	 * @param primaryKey the primary key of this agenda export period
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this agenda export period.
	 *
	 * @return the uuid of this agenda export period
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this agenda export period.
	 *
	 * @param uuid the uuid of this agenda export period
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the agenda export period ID of this agenda export period.
	 *
	 * @return the agenda export period ID of this agenda export period
	 */
	public long getAgendaExportPeriodId();

	/**
	 * Sets the agenda export period ID of this agenda export period.
	 *
	 * @param agendaExportPeriodId the agenda export period ID of this agenda export period
	 */
	public void setAgendaExportPeriodId(long agendaExportPeriodId);

	/**
	 * Returns the start date of this agenda export period.
	 *
	 * @return the start date of this agenda export period
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this agenda export period.
	 *
	 * @param startDate the start date of this agenda export period
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this agenda export period.
	 *
	 * @return the end date of this agenda export period
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this agenda export period.
	 *
	 * @param endDate the end date of this agenda export period
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the agenda export ID of this agenda export period.
	 *
	 * @return the agenda export ID of this agenda export period
	 */
	public long getAgendaExportId();

	/**
	 * Sets the agenda export ID of this agenda export period.
	 *
	 * @param agendaExportId the agenda export ID of this agenda export period
	 */
	public void setAgendaExportId(long agendaExportId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.agenda.model.AgendaExportPeriod> toCacheModel();

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod toEscapedModel();

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}