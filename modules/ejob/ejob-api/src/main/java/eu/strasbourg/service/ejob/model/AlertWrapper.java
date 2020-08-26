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

package eu.strasbourg.service.ejob.model;

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
 * This class is a wrapper for {@link Alert}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Alert
 * @generated
 */
@ProviderType
public class AlertWrapper implements Alert, ModelWrapper<Alert> {

	public AlertWrapper(Alert alert) {
		_alert = alert;
	}

	@Override
	public Class<?> getModelClass() {
		return Alert.class;
	}

	@Override
	public String getModelClassName() {
		return Alert.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("alertId", getAlertId());
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
		attributes.put("name", getName());
		attributes.put("keyWord", getKeyWord());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("language", getLanguage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long alertId = (Long)attributes.get("alertId");

		if (alertId != null) {
			setAlertId(alertId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String keyWord = (String)attributes.get("keyWord");

		if (keyWord != null) {
			setKeyWord(keyWord);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		String language = (String)attributes.get("language");

		if (language != null) {
			setLanguage(language);
		}
	}

	@Override
	public Object clone() {
		return new AlertWrapper((Alert)_alert.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.ejob.model.Alert alert) {
		return _alert.compareTo(alert);
	}

	/**
	 * Returns the alert ID of this alert.
	 *
	 * @return the alert ID of this alert
	 */
	@Override
	public long getAlertId() {
		return _alert.getAlertId();
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _alert.getAssetEntry();
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return _alert.getCategories();
	}

	/**
	 * Returns the company ID of this alert.
	 *
	 * @return the company ID of this alert
	 */
	@Override
	public long getCompanyId() {
		return _alert.getCompanyId();
	}

	/**
	 * Returns the create date of this alert.
	 *
	 * @return the create date of this alert
	 */
	@Override
	public Date getCreateDate() {
		return _alert.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _alert.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this alert.
	 *
	 * @return the group ID of this alert
	 */
	@Override
	public long getGroupId() {
		return _alert.getGroupId();
	}

	/**
	 * Returns the key word of this alert.
	 *
	 * @return the key word of this alert
	 */
	@Override
	public String getKeyWord() {
		return _alert.getKeyWord();
	}

	/**
	 * Returns the language of this alert.
	 *
	 * @return the language of this alert
	 */
	@Override
	public String getLanguage() {
		return _alert.getLanguage();
	}

	/**
	 * Returns the modified date of this alert.
	 *
	 * @return the modified date of this alert
	 */
	@Override
	public Date getModifiedDate() {
		return _alert.getModifiedDate();
	}

	/**
	 * Returns the name of this alert.
	 *
	 * @return the name of this alert
	 */
	@Override
	public String getName() {
		return _alert.getName();
	}

	/**
	 * Returns the primary key of this alert.
	 *
	 * @return the primary key of this alert
	 */
	@Override
	public long getPrimaryKey() {
		return _alert.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _alert.getPrimaryKeyObj();
	}

	/**
	 * Returns the publik user ID of this alert.
	 *
	 * @return the publik user ID of this alert
	 */
	@Override
	public String getPublikUserId() {
		return _alert.getPublikUserId();
	}

	/**
	 * Returns the status of this alert.
	 *
	 * @return the status of this alert
	 */
	@Override
	public int getStatus() {
		return _alert.getStatus();
	}

	/**
	 * Returns the status by user ID of this alert.
	 *
	 * @return the status by user ID of this alert
	 */
	@Override
	public long getStatusByUserId() {
		return _alert.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this alert.
	 *
	 * @return the status by user name of this alert
	 */
	@Override
	public String getStatusByUserName() {
		return _alert.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this alert.
	 *
	 * @return the status by user uuid of this alert
	 */
	@Override
	public String getStatusByUserUuid() {
		return _alert.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this alert.
	 *
	 * @return the status date of this alert
	 */
	@Override
	public Date getStatusDate() {
		return _alert.getStatusDate();
	}

	/**
	 * Returns the user ID of this alert.
	 *
	 * @return the user ID of this alert
	 */
	@Override
	public long getUserId() {
		return _alert.getUserId();
	}

	/**
	 * Returns the user name of this alert.
	 *
	 * @return the user name of this alert
	 */
	@Override
	public String getUserName() {
		return _alert.getUserName();
	}

	/**
	 * Returns the user uuid of this alert.
	 *
	 * @return the user uuid of this alert
	 */
	@Override
	public String getUserUuid() {
		return _alert.getUserUuid();
	}

	/**
	 * Returns the uuid of this alert.
	 *
	 * @return the uuid of this alert
	 */
	@Override
	public String getUuid() {
		return _alert.getUuid();
	}

	@Override
	public int hashCode() {
		return _alert.hashCode();
	}

	/**
	 * Returns <code>true</code> if this alert is approved.
	 *
	 * @return <code>true</code> if this alert is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _alert.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _alert.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this alert is denied.
	 *
	 * @return <code>true</code> if this alert is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _alert.isDenied();
	}

	/**
	 * Returns <code>true</code> if this alert is a draft.
	 *
	 * @return <code>true</code> if this alert is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _alert.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _alert.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this alert is expired.
	 *
	 * @return <code>true</code> if this alert is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _alert.isExpired();
	}

	/**
	 * Returns <code>true</code> if this alert is inactive.
	 *
	 * @return <code>true</code> if this alert is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _alert.isInactive();
	}

	/**
	 * Returns <code>true</code> if this alert is incomplete.
	 *
	 * @return <code>true</code> if this alert is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _alert.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _alert.isNew();
	}

	/**
	 * Returns <code>true</code> if this alert is pending.
	 *
	 * @return <code>true</code> if this alert is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _alert.isPending();
	}

	/**
	 * Returns <code>true</code> if this alert is scheduled.
	 *
	 * @return <code>true</code> if this alert is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _alert.isScheduled();
	}

	@Override
	public void persist() {
		_alert.persist();
	}

	@Override
	public boolean sendMail(
		java.util.List<eu.strasbourg.service.ejob.model.Offer> listOffer) {

		return _alert.sendMail(listOffer);
	}

	/**
	 * Sets the alert ID of this alert.
	 *
	 * @param alertId the alert ID of this alert
	 */
	@Override
	public void setAlertId(long alertId) {
		_alert.setAlertId(alertId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_alert.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this alert.
	 *
	 * @param companyId the company ID of this alert
	 */
	@Override
	public void setCompanyId(long companyId) {
		_alert.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this alert.
	 *
	 * @param createDate the create date of this alert
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_alert.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_alert.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_alert.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_alert.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this alert.
	 *
	 * @param groupId the group ID of this alert
	 */
	@Override
	public void setGroupId(long groupId) {
		_alert.setGroupId(groupId);
	}

	/**
	 * Sets the key word of this alert.
	 *
	 * @param keyWord the key word of this alert
	 */
	@Override
	public void setKeyWord(String keyWord) {
		_alert.setKeyWord(keyWord);
	}

	/**
	 * Sets the language of this alert.
	 *
	 * @param language the language of this alert
	 */
	@Override
	public void setLanguage(String language) {
		_alert.setLanguage(language);
	}

	/**
	 * Sets the modified date of this alert.
	 *
	 * @param modifiedDate the modified date of this alert
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_alert.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this alert.
	 *
	 * @param name the name of this alert
	 */
	@Override
	public void setName(String name) {
		_alert.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_alert.setNew(n);
	}

	/**
	 * Sets the primary key of this alert.
	 *
	 * @param primaryKey the primary key of this alert
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_alert.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_alert.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the publik user ID of this alert.
	 *
	 * @param publikUserId the publik user ID of this alert
	 */
	@Override
	public void setPublikUserId(String publikUserId) {
		_alert.setPublikUserId(publikUserId);
	}

	/**
	 * Sets the status of this alert.
	 *
	 * @param status the status of this alert
	 */
	@Override
	public void setStatus(int status) {
		_alert.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this alert.
	 *
	 * @param statusByUserId the status by user ID of this alert
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_alert.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this alert.
	 *
	 * @param statusByUserName the status by user name of this alert
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_alert.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this alert.
	 *
	 * @param statusByUserUuid the status by user uuid of this alert
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_alert.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this alert.
	 *
	 * @param statusDate the status date of this alert
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_alert.setStatusDate(statusDate);
	}

	/**
	 * Sets the user ID of this alert.
	 *
	 * @param userId the user ID of this alert
	 */
	@Override
	public void setUserId(long userId) {
		_alert.setUserId(userId);
	}

	/**
	 * Sets the user name of this alert.
	 *
	 * @param userName the user name of this alert
	 */
	@Override
	public void setUserName(String userName) {
		_alert.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this alert.
	 *
	 * @param userUuid the user uuid of this alert
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_alert.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this alert.
	 *
	 * @param uuid the uuid of this alert
	 */
	@Override
	public void setUuid(String uuid) {
		_alert.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.ejob.model.Alert> toCacheModel() {

		return _alert.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.ejob.model.Alert toEscapedModel() {
		return new AlertWrapper(_alert.toEscapedModel());
	}

	@Override
	public String toString() {
		return _alert.toString();
	}

	@Override
	public eu.strasbourg.service.ejob.model.Alert toUnescapedModel() {
		return new AlertWrapper(_alert.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _alert.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AlertWrapper)) {
			return false;
		}

		AlertWrapper alertWrapper = (AlertWrapper)obj;

		if (Objects.equals(_alert, alertWrapper._alert)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _alert.getStagedModelType();
	}

	@Override
	public Alert getWrappedModel() {
		return _alert;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _alert.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _alert.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_alert.resetOriginalValues();
	}

	private final Alert _alert;

}