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
 * The base model interface for the CampaignEventStatus service. Represents a row in the &quot;agenda_CampaignEventStatus&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>eu.strasbourg.service.agenda.model.impl.CampaignEventStatusImpl</code>.
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventStatus
 * @generated
 */
@ProviderType
public interface CampaignEventStatusModel
	extends BaseModel<CampaignEventStatus> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a campaign event status model instance should use the {@link CampaignEventStatus} interface instead.
	 */

	/**
	 * Returns the primary key of this campaign event status.
	 *
	 * @return the primary key of this campaign event status
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this campaign event status.
	 *
	 * @param primaryKey the primary key of this campaign event status
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this campaign event status.
	 *
	 * @return the uuid of this campaign event status
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this campaign event status.
	 *
	 * @param uuid the uuid of this campaign event status
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the status ID of this campaign event status.
	 *
	 * @return the status ID of this campaign event status
	 */
	public long getStatusId();

	/**
	 * Sets the status ID of this campaign event status.
	 *
	 * @param statusId the status ID of this campaign event status
	 */
	public void setStatusId(long statusId);

	/**
	 * Returns the status of this campaign event status.
	 *
	 * @return the status of this campaign event status
	 */
	public Integer getStatus();

	/**
	 * Sets the status of this campaign event status.
	 *
	 * @param status the status of this campaign event status
	 */
	public void setStatus(Integer status);

	/**
	 * Returns the comment of this campaign event status.
	 *
	 * @return the comment of this campaign event status
	 */
	@AutoEscape
	public String getComment();

	/**
	 * Sets the comment of this campaign event status.
	 *
	 * @param comment the comment of this campaign event status
	 */
	public void setComment(String comment);

	/**
	 * Returns the deletion denied of this campaign event status.
	 *
	 * @return the deletion denied of this campaign event status
	 */
	public Boolean getDeletionDenied();

	/**
	 * Sets the deletion denied of this campaign event status.
	 *
	 * @param deletionDenied the deletion denied of this campaign event status
	 */
	public void setDeletionDenied(Boolean deletionDenied);

	/**
	 * Returns the date of this campaign event status.
	 *
	 * @return the date of this campaign event status
	 */
	public Date getDate();

	/**
	 * Sets the date of this campaign event status.
	 *
	 * @param date the date of this campaign event status
	 */
	public void setDate(Date date);

	/**
	 * Returns the campaign event ID of this campaign event status.
	 *
	 * @return the campaign event ID of this campaign event status
	 */
	public long getCampaignEventId();

	/**
	 * Sets the campaign event ID of this campaign event status.
	 *
	 * @param campaignEventId the campaign event ID of this campaign event status
	 */
	public void setCampaignEventId(long campaignEventId);

	/**
	 * Returns the previous status ID of this campaign event status.
	 *
	 * @return the previous status ID of this campaign event status
	 */
	public long getPreviousStatusId();

	/**
	 * Sets the previous status ID of this campaign event status.
	 *
	 * @param previousStatusId the previous status ID of this campaign event status
	 */
	public void setPreviousStatusId(long previousStatusId);

	/**
	 * Returns the user ID of this campaign event status.
	 *
	 * @return the user ID of this campaign event status
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this campaign event status.
	 *
	 * @param userId the user ID of this campaign event status
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this campaign event status.
	 *
	 * @return the user uuid of this campaign event status
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this campaign event status.
	 *
	 * @param userUuid the user uuid of this campaign event status
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this campaign event status.
	 *
	 * @return the user name of this campaign event status
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this campaign event status.
	 *
	 * @param userName the user name of this campaign event status
	 */
	public void setUserName(String userName);

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
		eu.strasbourg.service.agenda.model.CampaignEventStatus
			campaignEventStatus);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.agenda.model.CampaignEventStatus>
		toCacheModel();

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus
		toEscapedModel();

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus
		toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}