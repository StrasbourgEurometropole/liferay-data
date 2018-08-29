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

package eu.strasbourg.service.project.model;

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
 * This class is a wrapper for {@link Initiative}.
 * </p>
 *
 * @author Cedric Henry
 * @see Initiative
 * @generated
 */
@ProviderType
public class InitiativeWrapper implements Initiative, ModelWrapper<Initiative> {
	public InitiativeWrapper(Initiative initiative) {
		_initiative = initiative;
	}

	@Override
	public Class<?> getModelClass() {
		return Initiative.class;
	}

	@Override
	public String getModelClassName() {
		return Initiative.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("initiativeId", getInitiativeId());
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
		attributes.put("title", getTitle());
		attributes.put("author", getAuthor());
		attributes.put("description", getDescription());
		attributes.put("videoUrl", getVideoUrl());
		attributes.put("externalImageURL", getExternalImageURL());
		attributes.put("externalImageCopyright", getExternalImageCopyright());
		attributes.put("mediaChoice", getMediaChoice());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("publikId", getPublikId());
		attributes.put("imageId", getImageId());
		attributes.put("filesIds", getFilesIds());
		attributes.put("consultationPlacesBody", getConsultationPlacesBody());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long initiativeId = (Long)attributes.get("initiativeId");

		if (initiativeId != null) {
			setInitiativeId(initiativeId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String videoUrl = (String)attributes.get("videoUrl");

		if (videoUrl != null) {
			setVideoUrl(videoUrl);
		}

		String externalImageURL = (String)attributes.get("externalImageURL");

		if (externalImageURL != null) {
			setExternalImageURL(externalImageURL);
		}

		String externalImageCopyright = (String)attributes.get(
				"externalImageCopyright");

		if (externalImageCopyright != null) {
			setExternalImageCopyright(externalImageCopyright);
		}

		Boolean mediaChoice = (Boolean)attributes.get("mediaChoice");

		if (mediaChoice != null) {
			setMediaChoice(mediaChoice);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String filesIds = (String)attributes.get("filesIds");

		if (filesIds != null) {
			setFilesIds(filesIds);
		}

		String consultationPlacesBody = (String)attributes.get(
				"consultationPlacesBody");

		if (consultationPlacesBody != null) {
			setConsultationPlacesBody(consultationPlacesBody);
		}
	}

	/**
	* Returns the media choice of this initiative.
	*
	* @return the media choice of this initiative
	*/
	@Override
	public boolean getMediaChoice() {
		return _initiative.getMediaChoice();
	}

	/**
	* Returns <code>true</code> if this initiative is approved.
	*
	* @return <code>true</code> if this initiative is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _initiative.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _initiative.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this initiative is denied.
	*
	* @return <code>true</code> if this initiative is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _initiative.isDenied();
	}

	/**
	* Returns <code>true</code> if this initiative is a draft.
	*
	* @return <code>true</code> if this initiative is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _initiative.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _initiative.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this initiative is expired.
	*
	* @return <code>true</code> if this initiative is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _initiative.isExpired();
	}

	/**
	* Returns <code>true</code> if this initiative is inactive.
	*
	* @return <code>true</code> if this initiative is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _initiative.isInactive();
	}

	/**
	* Returns <code>true</code> if this initiative is incomplete.
	*
	* @return <code>true</code> if this initiative is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _initiative.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this initiative is media choice.
	*
	* @return <code>true</code> if this initiative is media choice; <code>false</code> otherwise
	*/
	@Override
	public boolean isMediaChoice() {
		return _initiative.isMediaChoice();
	}

	@Override
	public boolean isNew() {
		return _initiative.isNew();
	}

	/**
	* Returns <code>true</code> if this initiative is pending.
	*
	* @return <code>true</code> if this initiative is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _initiative.isPending();
	}

	/**
	* Returns <code>true</code> if this initiative is scheduled.
	*
	* @return <code>true</code> if this initiative is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _initiative.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _initiative.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _initiative.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.Initiative> toCacheModel() {
		return _initiative.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.Initiative toEscapedModel() {
		return new InitiativeWrapper(_initiative.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.Initiative toUnescapedModel() {
		return new InitiativeWrapper(_initiative.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.Initiative initiative) {
		return _initiative.compareTo(initiative);
	}

	/**
	* Returns the status of this initiative.
	*
	* @return the status of this initiative
	*/
	@Override
	public int getStatus() {
		return _initiative.getStatus();
	}

	@Override
	public int hashCode() {
		return _initiative.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _initiative.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new InitiativeWrapper((Initiative)_initiative.clone());
	}

	/**
	* Returns the author of this initiative.
	*
	* @return the author of this initiative
	*/
	@Override
	public java.lang.String getAuthor() {
		return _initiative.getAuthor();
	}

	/**
	* Returns the consultation places body of this initiative.
	*
	* @return the consultation places body of this initiative
	*/
	@Override
	public java.lang.String getConsultationPlacesBody() {
		return _initiative.getConsultationPlacesBody();
	}

	/**
	* Returns the description of this initiative.
	*
	* @return the description of this initiative
	*/
	@Override
	public java.lang.String getDescription() {
		return _initiative.getDescription();
	}

	/**
	* Returns the external image copyright of this initiative.
	*
	* @return the external image copyright of this initiative
	*/
	@Override
	public java.lang.String getExternalImageCopyright() {
		return _initiative.getExternalImageCopyright();
	}

	/**
	* Returns the external image url of this initiative.
	*
	* @return the external image url of this initiative
	*/
	@Override
	public java.lang.String getExternalImageURL() {
		return _initiative.getExternalImageURL();
	}

	/**
	* Returns the files IDs of this initiative.
	*
	* @return the files IDs of this initiative
	*/
	@Override
	public java.lang.String getFilesIds() {
		return _initiative.getFilesIds();
	}

	/**
	* Returns the publik ID of this initiative.
	*
	* @return the publik ID of this initiative
	*/
	@Override
	public java.lang.String getPublikId() {
		return _initiative.getPublikId();
	}

	/**
	* Returns the status by user name of this initiative.
	*
	* @return the status by user name of this initiative
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _initiative.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this initiative.
	*
	* @return the status by user uuid of this initiative
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _initiative.getStatusByUserUuid();
	}

	/**
	* Returns the title of this initiative.
	*
	* @return the title of this initiative
	*/
	@Override
	public java.lang.String getTitle() {
		return _initiative.getTitle();
	}

	/**
	* Returns the user name of this initiative.
	*
	* @return the user name of this initiative
	*/
	@Override
	public java.lang.String getUserName() {
		return _initiative.getUserName();
	}

	/**
	* Returns the user uuid of this initiative.
	*
	* @return the user uuid of this initiative
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _initiative.getUserUuid();
	}

	/**
	* Returns the uuid of this initiative.
	*
	* @return the uuid of this initiative
	*/
	@Override
	public java.lang.String getUuid() {
		return _initiative.getUuid();
	}

	/**
	* Returns the video url of this initiative.
	*
	* @return the video url of this initiative
	*/
	@Override
	public java.lang.String getVideoUrl() {
		return _initiative.getVideoUrl();
	}

	@Override
	public java.lang.String toString() {
		return _initiative.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _initiative.toXmlString();
	}

	/**
	* Returns the create date of this initiative.
	*
	* @return the create date of this initiative
	*/
	@Override
	public Date getCreateDate() {
		return _initiative.getCreateDate();
	}

	/**
	* Returns the modified date of this initiative.
	*
	* @return the modified date of this initiative
	*/
	@Override
	public Date getModifiedDate() {
		return _initiative.getModifiedDate();
	}

	/**
	* Returns the status date of this initiative.
	*
	* @return the status date of this initiative
	*/
	@Override
	public Date getStatusDate() {
		return _initiative.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _initiative.getCategories();
	}

	/**
	* Retourne les noms des lieux placit de l'initiative
	*/
	@Override
	public java.util.List<java.lang.String> getPlaceNames(
		java.util.Locale locale) {
		return _initiative.getPlaceNames(locale);
	}

	/**
	* Retourne les ids SIG des lieux placit de l'initiative
	*/
	@Override
	public java.util.List<java.lang.String> getPlaceSIGIds(
		java.util.Locale locale) {
		return _initiative.getPlaceSIGIds(locale);
	}

	/**
	* Retourne la liste des lieux placit liés à l'initiative
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.PlacitPlace> getPlacitPlaces() {
		return _initiative.getPlacitPlaces();
	}

	/**
	* Returns the asset entry ID of this initiative.
	*
	* @return the asset entry ID of this initiative
	*/
	@Override
	public long getAssetEntryId() {
		return _initiative.getAssetEntryId();
	}

	/**
	* Returns the company ID of this initiative.
	*
	* @return the company ID of this initiative
	*/
	@Override
	public long getCompanyId() {
		return _initiative.getCompanyId();
	}

	/**
	* Returns the group ID of this initiative.
	*
	* @return the group ID of this initiative
	*/
	@Override
	public long getGroupId() {
		return _initiative.getGroupId();
	}

	/**
	* Returns the image ID of this initiative.
	*
	* @return the image ID of this initiative
	*/
	@Override
	public long getImageId() {
		return _initiative.getImageId();
	}

	/**
	* Returns the initiative ID of this initiative.
	*
	* @return the initiative ID of this initiative
	*/
	@Override
	public long getInitiativeId() {
		return _initiative.getInitiativeId();
	}

	/**
	* Returns the primary key of this initiative.
	*
	* @return the primary key of this initiative
	*/
	@Override
	public long getPrimaryKey() {
		return _initiative.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this initiative.
	*
	* @return the status by user ID of this initiative
	*/
	@Override
	public long getStatusByUserId() {
		return _initiative.getStatusByUserId();
	}

	/**
	* Returns the user ID of this initiative.
	*
	* @return the user ID of this initiative
	*/
	@Override
	public long getUserId() {
		return _initiative.getUserId();
	}

	@Override
	public void persist() {
		_initiative.persist();
	}

	/**
	* Sets the asset entry ID of this initiative.
	*
	* @param assetEntryId the asset entry ID of this initiative
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_initiative.setAssetEntryId(assetEntryId);
	}

	/**
	* Sets the author of this initiative.
	*
	* @param author the author of this initiative
	*/
	@Override
	public void setAuthor(java.lang.String author) {
		_initiative.setAuthor(author);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_initiative.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this initiative.
	*
	* @param companyId the company ID of this initiative
	*/
	@Override
	public void setCompanyId(long companyId) {
		_initiative.setCompanyId(companyId);
	}

	/**
	* Sets the consultation places body of this initiative.
	*
	* @param consultationPlacesBody the consultation places body of this initiative
	*/
	@Override
	public void setConsultationPlacesBody(
		java.lang.String consultationPlacesBody) {
		_initiative.setConsultationPlacesBody(consultationPlacesBody);
	}

	/**
	* Sets the create date of this initiative.
	*
	* @param createDate the create date of this initiative
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_initiative.setCreateDate(createDate);
	}

	/**
	* Sets the description of this initiative.
	*
	* @param description the description of this initiative
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_initiative.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_initiative.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_initiative.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_initiative.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the external image copyright of this initiative.
	*
	* @param externalImageCopyright the external image copyright of this initiative
	*/
	@Override
	public void setExternalImageCopyright(
		java.lang.String externalImageCopyright) {
		_initiative.setExternalImageCopyright(externalImageCopyright);
	}

	/**
	* Sets the external image url of this initiative.
	*
	* @param externalImageURL the external image url of this initiative
	*/
	@Override
	public void setExternalImageURL(java.lang.String externalImageURL) {
		_initiative.setExternalImageURL(externalImageURL);
	}

	/**
	* Sets the files IDs of this initiative.
	*
	* @param filesIds the files IDs of this initiative
	*/
	@Override
	public void setFilesIds(java.lang.String filesIds) {
		_initiative.setFilesIds(filesIds);
	}

	/**
	* Sets the group ID of this initiative.
	*
	* @param groupId the group ID of this initiative
	*/
	@Override
	public void setGroupId(long groupId) {
		_initiative.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this initiative.
	*
	* @param imageId the image ID of this initiative
	*/
	@Override
	public void setImageId(long imageId) {
		_initiative.setImageId(imageId);
	}

	/**
	* Sets the initiative ID of this initiative.
	*
	* @param initiativeId the initiative ID of this initiative
	*/
	@Override
	public void setInitiativeId(long initiativeId) {
		_initiative.setInitiativeId(initiativeId);
	}

	/**
	* Sets whether this initiative is media choice.
	*
	* @param mediaChoice the media choice of this initiative
	*/
	@Override
	public void setMediaChoice(boolean mediaChoice) {
		_initiative.setMediaChoice(mediaChoice);
	}

	/**
	* Sets the modified date of this initiative.
	*
	* @param modifiedDate the modified date of this initiative
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_initiative.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_initiative.setNew(n);
	}

	/**
	* Sets the primary key of this initiative.
	*
	* @param primaryKey the primary key of this initiative
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_initiative.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_initiative.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik ID of this initiative.
	*
	* @param publikId the publik ID of this initiative
	*/
	@Override
	public void setPublikId(java.lang.String publikId) {
		_initiative.setPublikId(publikId);
	}

	/**
	* Sets the status of this initiative.
	*
	* @param status the status of this initiative
	*/
	@Override
	public void setStatus(int status) {
		_initiative.setStatus(status);
	}

	/**
	* Sets the status by user ID of this initiative.
	*
	* @param statusByUserId the status by user ID of this initiative
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_initiative.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this initiative.
	*
	* @param statusByUserName the status by user name of this initiative
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_initiative.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this initiative.
	*
	* @param statusByUserUuid the status by user uuid of this initiative
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_initiative.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this initiative.
	*
	* @param statusDate the status date of this initiative
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_initiative.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this initiative.
	*
	* @param title the title of this initiative
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_initiative.setTitle(title);
	}

	/**
	* Sets the user ID of this initiative.
	*
	* @param userId the user ID of this initiative
	*/
	@Override
	public void setUserId(long userId) {
		_initiative.setUserId(userId);
	}

	/**
	* Sets the user name of this initiative.
	*
	* @param userName the user name of this initiative
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_initiative.setUserName(userName);
	}

	/**
	* Sets the user uuid of this initiative.
	*
	* @param userUuid the user uuid of this initiative
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_initiative.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this initiative.
	*
	* @param uuid the uuid of this initiative
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_initiative.setUuid(uuid);
	}

	/**
	* Sets the video url of this initiative.
	*
	* @param videoUrl the video url of this initiative
	*/
	@Override
	public void setVideoUrl(java.lang.String videoUrl) {
		_initiative.setVideoUrl(videoUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InitiativeWrapper)) {
			return false;
		}

		InitiativeWrapper initiativeWrapper = (InitiativeWrapper)obj;

		if (Objects.equals(_initiative, initiativeWrapper._initiative)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _initiative.getStagedModelType();
	}

	@Override
	public Initiative getWrappedModel() {
		return _initiative;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _initiative.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _initiative.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_initiative.resetOriginalValues();
	}

	private final Initiative _initiative;
}