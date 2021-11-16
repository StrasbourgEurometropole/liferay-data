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
 * The base model interface for the CacheJson service. Represents a row in the &quot;agenda_CacheJson&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>eu.strasbourg.service.agenda.model.impl.CacheJsonModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>eu.strasbourg.service.agenda.model.impl.CacheJsonImpl</code>.
 * </p>
 *
 * @author BenjaminBini
 * @see CacheJson
 * @generated
 */
@ProviderType
public interface CacheJsonModel extends BaseModel<CacheJson> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cache json model instance should use the {@link CacheJson} interface instead.
	 */

	/**
	 * Returns the primary key of this cache json.
	 *
	 * @return the primary key of this cache json
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cache json.
	 *
	 * @param primaryKey the primary key of this cache json
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cache json.
	 *
	 * @return the uuid of this cache json
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this cache json.
	 *
	 * @param uuid the uuid of this cache json
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the event ID of this cache json.
	 *
	 * @return the event ID of this cache json
	 */
	public long getEventId();

	/**
	 * Sets the event ID of this cache json.
	 *
	 * @param eventId the event ID of this cache json
	 */
	public void setEventId(long eventId);

	/**
	 * Returns the json event of this cache json.
	 *
	 * @return the json event of this cache json
	 */
	@AutoEscape
	public String getJsonEvent();

	/**
	 * Sets the json event of this cache json.
	 *
	 * @param jsonEvent the json event of this cache json
	 */
	public void setJsonEvent(String jsonEvent);

	/**
	 * Returns the create event of this cache json.
	 *
	 * @return the create event of this cache json
	 */
	public Date getCreateEvent();

	/**
	 * Sets the create event of this cache json.
	 *
	 * @param createEvent the create event of this cache json
	 */
	public void setCreateEvent(Date createEvent);

	/**
	 * Returns the modified event of this cache json.
	 *
	 * @return the modified event of this cache json
	 */
	public Date getModifiedEvent();

	/**
	 * Sets the modified event of this cache json.
	 *
	 * @param modifiedEvent the modified event of this cache json
	 */
	public void setModifiedEvent(Date modifiedEvent);

	/**
	 * Returns the is active of this cache json.
	 *
	 * @return the is active of this cache json
	 */
	public boolean getIsActive();

	/**
	 * Returns <code>true</code> if this cache json is is active.
	 *
	 * @return <code>true</code> if this cache json is is active; <code>false</code> otherwise
	 */
	public boolean isIsActive();

	/**
	 * Sets whether this cache json is is active.
	 *
	 * @param isActive the is active of this cache json
	 */
	public void setIsActive(boolean isActive);

	/**
	 * Returns the regenerated date of this cache json.
	 *
	 * @return the regenerated date of this cache json
	 */
	public Date getRegeneratedDate();

	/**
	 * Sets the regenerated date of this cache json.
	 *
	 * @param regeneratedDate the regenerated date of this cache json
	 */
	public void setRegeneratedDate(Date regeneratedDate);

	/**
	 * Returns the has schedules of this cache json.
	 *
	 * @return the has schedules of this cache json
	 */
	public boolean getHasSchedules();

	/**
	 * Returns <code>true</code> if this cache json is has schedules.
	 *
	 * @return <code>true</code> if this cache json is has schedules; <code>false</code> otherwise
	 */
	public boolean isHasSchedules();

	/**
	 * Sets whether this cache json is has schedules.
	 *
	 * @param hasSchedules the has schedules of this cache json
	 */
	public void setHasSchedules(boolean hasSchedules);

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
		eu.strasbourg.service.agenda.model.CacheJson cacheJson);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.agenda.model.CacheJson>
		toCacheModel();

	@Override
	public eu.strasbourg.service.agenda.model.CacheJson toEscapedModel();

	@Override
	public eu.strasbourg.service.agenda.model.CacheJson toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}