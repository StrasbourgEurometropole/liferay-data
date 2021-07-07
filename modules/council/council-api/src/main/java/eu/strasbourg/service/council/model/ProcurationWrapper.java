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

package eu.strasbourg.service.council.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Procuration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Procuration
 * @generated
 */
@ProviderType
public class ProcurationWrapper
	implements Procuration, ModelWrapper<Procuration> {

	public ProcurationWrapper(Procuration procuration) {
		_procuration = procuration;
	}

	@Override
	public Class<?> getModelClass() {
		return Procuration.class;
	}

	@Override
	public String getModelClassName() {
		return Procuration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("procurationId", getProcurationId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("officialVotersId", getOfficialVotersId());
		attributes.put("officialUnavailableId", getOfficialUnavailableId());
		attributes.put("councilSessionId", getCouncilSessionId());
		attributes.put("isAbsent", isIsAbsent());
		attributes.put("procurationMode", getProcurationMode());
		attributes.put("isPresential", isIsPresential());
		attributes.put("isAfterVote", isIsAfterVote());
		attributes.put("procurationStartHour", getProcurationStartHour());
		attributes.put("procurationEndHour", getProcurationEndHour());
		attributes.put("procurationStartPoint", getProcurationStartPoint());
		attributes.put("procurationEndPoint", getProcurationEndPoint());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long procurationId = (Long)attributes.get("procurationId");

		if (procurationId != null) {
			setProcurationId(procurationId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		Long officialVotersId = (Long)attributes.get("officialVotersId");

		if (officialVotersId != null) {
			setOfficialVotersId(officialVotersId);
		}

		Long officialUnavailableId = (Long)attributes.get(
			"officialUnavailableId");

		if (officialUnavailableId != null) {
			setOfficialUnavailableId(officialUnavailableId);
		}

		Long councilSessionId = (Long)attributes.get("councilSessionId");

		if (councilSessionId != null) {
			setCouncilSessionId(councilSessionId);
		}

		Boolean isAbsent = (Boolean)attributes.get("isAbsent");

		if (isAbsent != null) {
			setIsAbsent(isAbsent);
		}

		String procurationMode = (String)attributes.get("procurationMode");

		if (procurationMode != null) {
			setProcurationMode(procurationMode);
		}

		Boolean isPresential = (Boolean)attributes.get("isPresential");

		if (isPresential != null) {
			setIsPresential(isPresential);
		}

		Boolean isAfterVote = (Boolean)attributes.get("isAfterVote");

		if (isAfterVote != null) {
			setIsAfterVote(isAfterVote);
		}

		Date procurationStartHour = (Date)attributes.get(
			"procurationStartHour");

		if (procurationStartHour != null) {
			setProcurationStartHour(procurationStartHour);
		}

		Date procurationEndHour = (Date)attributes.get("procurationEndHour");

		if (procurationEndHour != null) {
			setProcurationEndHour(procurationEndHour);
		}

		Integer procurationStartPoint = (Integer)attributes.get(
			"procurationStartPoint");

		if (procurationStartPoint != null) {
			setProcurationStartPoint(procurationStartPoint);
		}

		Integer procurationEndPoint = (Integer)attributes.get(
			"procurationEndPoint");

		if (procurationEndPoint != null) {
			setProcurationEndPoint(procurationEndPoint);
		}
	}

	@Override
	public Object clone() {
		return new ProcurationWrapper((Procuration)_procuration.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.council.model.Procuration procuration) {

		return _procuration.compareTo(procuration);
	}

	/**
	 * Returns the company ID of this procuration.
	 *
	 * @return the company ID of this procuration
	 */
	@Override
	public long getCompanyId() {
		return _procuration.getCompanyId();
	}

	/**
	 * Returns the council session ID of this procuration.
	 *
	 * @return the council session ID of this procuration
	 */
	@Override
	public long getCouncilSessionId() {
		return _procuration.getCouncilSessionId();
	}

	/**
	 * Returns the create date of this procuration.
	 *
	 * @return the create date of this procuration
	 */
	@Override
	public Date getCreateDate() {
		return _procuration.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _procuration.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this procuration.
	 *
	 * @return the group ID of this procuration
	 */
	@Override
	public long getGroupId() {
		return _procuration.getGroupId();
	}

	/**
	 * Returns the is absent of this procuration.
	 *
	 * @return the is absent of this procuration
	 */
	@Override
	public boolean getIsAbsent() {
		return _procuration.getIsAbsent();
	}

	/**
	 * Returns the is after vote of this procuration.
	 *
	 * @return the is after vote of this procuration
	 */
	@Override
	public boolean getIsAfterVote() {
		return _procuration.getIsAfterVote();
	}

	/**
	 * Returns the is presential of this procuration.
	 *
	 * @return the is presential of this procuration
	 */
	@Override
	public boolean getIsPresential() {
		return _procuration.getIsPresential();
	}

	/**
	 * Returns the modified date of this procuration.
	 *
	 * @return the modified date of this procuration
	 */
	@Override
	public Date getModifiedDate() {
		return _procuration.getModifiedDate();
	}

	/**
	 * Returns the official unavailable ID of this procuration.
	 *
	 * @return the official unavailable ID of this procuration
	 */
	@Override
	public long getOfficialUnavailableId() {
		return _procuration.getOfficialUnavailableId();
	}

	/**
	 * Retourne le nom complet de l'élu reçevant la procuration
	 */
	@Override
	public String getOfficialVotersFullName() {
		return _procuration.getOfficialVotersFullName();
	}

	/**
	 * Returns the official voters ID of this procuration.
	 *
	 * @return the official voters ID of this procuration
	 */
	@Override
	public long getOfficialVotersId() {
		return _procuration.getOfficialVotersId();
	}

	/**
	 * Returns the primary key of this procuration.
	 *
	 * @return the primary key of this procuration
	 */
	@Override
	public long getPrimaryKey() {
		return _procuration.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _procuration.getPrimaryKeyObj();
	}

	/**
	 * Returns the procuration end hour of this procuration.
	 *
	 * @return the procuration end hour of this procuration
	 */
	@Override
	public Date getProcurationEndHour() {
		return _procuration.getProcurationEndHour();
	}

	/**
	 * Returns the procuration end point of this procuration.
	 *
	 * @return the procuration end point of this procuration
	 */
	@Override
	public int getProcurationEndPoint() {
		return _procuration.getProcurationEndPoint();
	}

	/**
	 * Returns the procuration ID of this procuration.
	 *
	 * @return the procuration ID of this procuration
	 */
	@Override
	public long getProcurationId() {
		return _procuration.getProcurationId();
	}

	/**
	 * Returns the procuration mode of this procuration.
	 *
	 * @return the procuration mode of this procuration
	 */
	@Override
	public String getProcurationMode() {
		return _procuration.getProcurationMode();
	}

	/**
	 * Returns the procuration start hour of this procuration.
	 *
	 * @return the procuration start hour of this procuration
	 */
	@Override
	public Date getProcurationStartHour() {
		return _procuration.getProcurationStartHour();
	}

	/**
	 * Returns the procuration start point of this procuration.
	 *
	 * @return the procuration start point of this procuration
	 */
	@Override
	public int getProcurationStartPoint() {
		return _procuration.getProcurationStartPoint();
	}

	/**
	 * Returns the status of this procuration.
	 *
	 * @return the status of this procuration
	 */
	@Override
	public int getStatus() {
		return _procuration.getStatus();
	}

	/**
	 * Returns the status by user ID of this procuration.
	 *
	 * @return the status by user ID of this procuration
	 */
	@Override
	public long getStatusByUserId() {
		return _procuration.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this procuration.
	 *
	 * @return the status by user name of this procuration
	 */
	@Override
	public String getStatusByUserName() {
		return _procuration.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this procuration.
	 *
	 * @return the status by user uuid of this procuration
	 */
	@Override
	public String getStatusByUserUuid() {
		return _procuration.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this procuration.
	 *
	 * @return the status date of this procuration
	 */
	@Override
	public Date getStatusDate() {
		return _procuration.getStatusDate();
	}

	/**
	 * Returns the user ID of this procuration.
	 *
	 * @return the user ID of this procuration
	 */
	@Override
	public long getUserId() {
		return _procuration.getUserId();
	}

	/**
	 * Returns the user name of this procuration.
	 *
	 * @return the user name of this procuration
	 */
	@Override
	public String getUserName() {
		return _procuration.getUserName();
	}

	/**
	 * Returns the user uuid of this procuration.
	 *
	 * @return the user uuid of this procuration
	 */
	@Override
	public String getUserUuid() {
		return _procuration.getUserUuid();
	}

	/**
	 * Returns the uuid of this procuration.
	 *
	 * @return the uuid of this procuration
	 */
	@Override
	public String getUuid() {
		return _procuration.getUuid();
	}

	@Override
	public int hashCode() {
		return _procuration.hashCode();
	}

	/**
	 * Returns <code>true</code> if this procuration is approved.
	 *
	 * @return <code>true</code> if this procuration is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _procuration.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _procuration.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this procuration is denied.
	 *
	 * @return <code>true</code> if this procuration is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _procuration.isDenied();
	}

	/**
	 * Returns <code>true</code> if this procuration is a draft.
	 *
	 * @return <code>true</code> if this procuration is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _procuration.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _procuration.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this procuration is expired.
	 *
	 * @return <code>true</code> if this procuration is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _procuration.isExpired();
	}

	/**
	 * Returns <code>true</code> if this procuration is inactive.
	 *
	 * @return <code>true</code> if this procuration is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _procuration.isInactive();
	}

	/**
	 * Returns <code>true</code> if this procuration is incomplete.
	 *
	 * @return <code>true</code> if this procuration is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _procuration.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this procuration is is absent.
	 *
	 * @return <code>true</code> if this procuration is is absent; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsAbsent() {
		return _procuration.isIsAbsent();
	}

	/**
	 * Returns <code>true</code> if this procuration is is after vote.
	 *
	 * @return <code>true</code> if this procuration is is after vote; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsAfterVote() {
		return _procuration.isIsAfterVote();
	}

	/**
	 * Returns <code>true</code> if this procuration is is presential.
	 *
	 * @return <code>true</code> if this procuration is is presential; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsPresential() {
		return _procuration.isIsPresential();
	}

	@Override
	public boolean isNew() {
		return _procuration.isNew();
	}

	/**
	 * Returns <code>true</code> if this procuration is pending.
	 *
	 * @return <code>true</code> if this procuration is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _procuration.isPending();
	}

	/**
	 * Returns <code>true</code> if this procuration is scheduled.
	 *
	 * @return <code>true</code> if this procuration is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _procuration.isScheduled();
	}

	@Override
	public void persist() {
		_procuration.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_procuration.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this procuration.
	 *
	 * @param companyId the company ID of this procuration
	 */
	@Override
	public void setCompanyId(long companyId) {
		_procuration.setCompanyId(companyId);
	}

	/**
	 * Sets the council session ID of this procuration.
	 *
	 * @param councilSessionId the council session ID of this procuration
	 */
	@Override
	public void setCouncilSessionId(long councilSessionId) {
		_procuration.setCouncilSessionId(councilSessionId);
	}

	/**
	 * Sets the create date of this procuration.
	 *
	 * @param createDate the create date of this procuration
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_procuration.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_procuration.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_procuration.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_procuration.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this procuration.
	 *
	 * @param groupId the group ID of this procuration
	 */
	@Override
	public void setGroupId(long groupId) {
		_procuration.setGroupId(groupId);
	}

	/**
	 * Sets whether this procuration is is absent.
	 *
	 * @param isAbsent the is absent of this procuration
	 */
	@Override
	public void setIsAbsent(boolean isAbsent) {
		_procuration.setIsAbsent(isAbsent);
	}

	/**
	 * Sets whether this procuration is is after vote.
	 *
	 * @param isAfterVote the is after vote of this procuration
	 */
	@Override
	public void setIsAfterVote(boolean isAfterVote) {
		_procuration.setIsAfterVote(isAfterVote);
	}

	/**
	 * Sets whether this procuration is is presential.
	 *
	 * @param isPresential the is presential of this procuration
	 */
	@Override
	public void setIsPresential(boolean isPresential) {
		_procuration.setIsPresential(isPresential);
	}

	/**
	 * Sets the modified date of this procuration.
	 *
	 * @param modifiedDate the modified date of this procuration
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_procuration.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_procuration.setNew(n);
	}

	/**
	 * Sets the official unavailable ID of this procuration.
	 *
	 * @param officialUnavailableId the official unavailable ID of this procuration
	 */
	@Override
	public void setOfficialUnavailableId(long officialUnavailableId) {
		_procuration.setOfficialUnavailableId(officialUnavailableId);
	}

	/**
	 * Sets the official voters ID of this procuration.
	 *
	 * @param officialVotersId the official voters ID of this procuration
	 */
	@Override
	public void setOfficialVotersId(long officialVotersId) {
		_procuration.setOfficialVotersId(officialVotersId);
	}

	/**
	 * Sets the primary key of this procuration.
	 *
	 * @param primaryKey the primary key of this procuration
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_procuration.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_procuration.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the procuration end hour of this procuration.
	 *
	 * @param procurationEndHour the procuration end hour of this procuration
	 */
	@Override
	public void setProcurationEndHour(Date procurationEndHour) {
		_procuration.setProcurationEndHour(procurationEndHour);
	}

	/**
	 * Sets the procuration end point of this procuration.
	 *
	 * @param procurationEndPoint the procuration end point of this procuration
	 */
	@Override
	public void setProcurationEndPoint(int procurationEndPoint) {
		_procuration.setProcurationEndPoint(procurationEndPoint);
	}

	/**
	 * Sets the procuration ID of this procuration.
	 *
	 * @param procurationId the procuration ID of this procuration
	 */
	@Override
	public void setProcurationId(long procurationId) {
		_procuration.setProcurationId(procurationId);
	}

	/**
	 * Sets the procuration mode of this procuration.
	 *
	 * @param procurationMode the procuration mode of this procuration
	 */
	@Override
	public void setProcurationMode(String procurationMode) {
		_procuration.setProcurationMode(procurationMode);
	}

	/**
	 * Sets the procuration start hour of this procuration.
	 *
	 * @param procurationStartHour the procuration start hour of this procuration
	 */
	@Override
	public void setProcurationStartHour(Date procurationStartHour) {
		_procuration.setProcurationStartHour(procurationStartHour);
	}

	/**
	 * Sets the procuration start point of this procuration.
	 *
	 * @param procurationStartPoint the procuration start point of this procuration
	 */
	@Override
	public void setProcurationStartPoint(int procurationStartPoint) {
		_procuration.setProcurationStartPoint(procurationStartPoint);
	}

	/**
	 * Sets the status of this procuration.
	 *
	 * @param status the status of this procuration
	 */
	@Override
	public void setStatus(int status) {
		_procuration.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this procuration.
	 *
	 * @param statusByUserId the status by user ID of this procuration
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_procuration.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this procuration.
	 *
	 * @param statusByUserName the status by user name of this procuration
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_procuration.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this procuration.
	 *
	 * @param statusByUserUuid the status by user uuid of this procuration
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_procuration.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this procuration.
	 *
	 * @param statusDate the status date of this procuration
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_procuration.setStatusDate(statusDate);
	}

	/**
	 * Sets the user ID of this procuration.
	 *
	 * @param userId the user ID of this procuration
	 */
	@Override
	public void setUserId(long userId) {
		_procuration.setUserId(userId);
	}

	/**
	 * Sets the user name of this procuration.
	 *
	 * @param userName the user name of this procuration
	 */
	@Override
	public void setUserName(String userName) {
		_procuration.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this procuration.
	 *
	 * @param userUuid the user uuid of this procuration
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_procuration.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this procuration.
	 *
	 * @param uuid the uuid of this procuration
	 */
	@Override
	public void setUuid(String uuid) {
		_procuration.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.council.model.Procuration> toCacheModel() {

		return _procuration.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.council.model.Procuration toEscapedModel() {
		return new ProcurationWrapper(_procuration.toEscapedModel());
	}

	@Override
	public String toString() {
		return _procuration.toString();
	}

	@Override
	public eu.strasbourg.service.council.model.Procuration toUnescapedModel() {
		return new ProcurationWrapper(_procuration.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _procuration.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcurationWrapper)) {
			return false;
		}

		ProcurationWrapper procurationWrapper = (ProcurationWrapper)obj;

		if (Objects.equals(_procuration, procurationWrapper._procuration)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _procuration.getStagedModelType();
	}

	@Override
	public Procuration getWrappedModel() {
		return _procuration;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _procuration.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _procuration.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_procuration.resetOriginalValues();
	}

	private final Procuration _procuration;

}