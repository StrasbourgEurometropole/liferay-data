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
 * This class is a wrapper for {@link Project}.
 * </p>
 *
 * @author Cedric Henry
 * @see Project
 * @generated
 */
@ProviderType
public class ProjectWrapper implements Project, ModelWrapper<Project> {
	public ProjectWrapper(Project project) {
		_project = project;
	}

	@Override
	public Class<?> getModelClass() {
		return Project.class;
	}

	@Override
	public String getModelClassName() {
		return Project.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("projectId", getProjectId());
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
		attributes.put("externalImageURL", getExternalImageURL());
		attributes.put("externalImageCopyright", getExternalImageCopyright());
		attributes.put("description", getDescription());
		attributes.put("detailURL", getDetailURL());
		attributes.put("budget", getBudget());
		attributes.put("label", getLabel());
		attributes.put("duration", getDuration());
		attributes.put("partners", getPartners());
		attributes.put("contactName", getContactName());
		attributes.put("contactLine1", getContactLine1());
		attributes.put("contactLine2", getContactLine2());
		attributes.put("contactPhoneNumber", getContactPhoneNumber());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
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

		String externalImageURL = (String)attributes.get("externalImageURL");

		if (externalImageURL != null) {
			setExternalImageURL(externalImageURL);
		}

		String externalImageCopyright = (String)attributes.get(
				"externalImageCopyright");

		if (externalImageCopyright != null) {
			setExternalImageCopyright(externalImageCopyright);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String detailURL = (String)attributes.get("detailURL");

		if (detailURL != null) {
			setDetailURL(detailURL);
		}

		String budget = (String)attributes.get("budget");

		if (budget != null) {
			setBudget(budget);
		}

		String label = (String)attributes.get("label");

		if (label != null) {
			setLabel(label);
		}

		Integer duration = (Integer)attributes.get("duration");

		if (duration != null) {
			setDuration(duration);
		}

		String partners = (String)attributes.get("partners");

		if (partners != null) {
			setPartners(partners);
		}

		String contactName = (String)attributes.get("contactName");

		if (contactName != null) {
			setContactName(contactName);
		}

		String contactLine1 = (String)attributes.get("contactLine1");

		if (contactLine1 != null) {
			setContactLine1(contactLine1);
		}

		String contactLine2 = (String)attributes.get("contactLine2");

		if (contactLine2 != null) {
			setContactLine2(contactLine2);
		}

		String contactPhoneNumber = (String)attributes.get("contactPhoneNumber");

		if (contactPhoneNumber != null) {
			setContactPhoneNumber(contactPhoneNumber);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	/**
	* Returns <code>true</code> if this project is approved.
	*
	* @return <code>true</code> if this project is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _project.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _project.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this project is denied.
	*
	* @return <code>true</code> if this project is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _project.isDenied();
	}

	/**
	* Returns <code>true</code> if this project is a draft.
	*
	* @return <code>true</code> if this project is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _project.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _project.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this project is expired.
	*
	* @return <code>true</code> if this project is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _project.isExpired();
	}

	/**
	* Returns <code>true</code> if this project is inactive.
	*
	* @return <code>true</code> if this project is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _project.isInactive();
	}

	/**
	* Returns <code>true</code> if this project is incomplete.
	*
	* @return <code>true</code> if this project is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _project.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _project.isNew();
	}

	/**
	* Returns <code>true</code> if this project is pending.
	*
	* @return <code>true</code> if this project is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _project.isPending();
	}

	/**
	* Returns <code>true</code> if this project is scheduled.
	*
	* @return <code>true</code> if this project is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _project.isScheduled();
	}

	/**
	* Retourne l'asset category du projet (normalement du même non que le projet)
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory() {
		return _project.getProjectCategory();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _project.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _project.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.Project> toCacheModel() {
		return _project.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.Project toEscapedModel() {
		return new ProjectWrapper(_project.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.Project toUnescapedModel() {
		return new ProjectWrapper(_project.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.project.model.Project project) {
		return _project.compareTo(project);
	}

	/**
	* Returns the duration of this project.
	*
	* @return the duration of this project
	*/
	@Override
	public int getDuration() {
		return _project.getDuration();
	}

	/**
	* Returns the status of this project.
	*
	* @return the status of this project
	*/
	@Override
	public int getStatus() {
		return _project.getStatus();
	}

	@Override
	public int hashCode() {
		return _project.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _project.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectWrapper((Project)_project.clone());
	}

	/**
	* Returns the budget of this project.
	*
	* @return the budget of this project
	*/
	@Override
	public java.lang.String getBudget() {
		return _project.getBudget();
	}

	/**
	* Returns the contact line1 of this project.
	*
	* @return the contact line1 of this project
	*/
	@Override
	public java.lang.String getContactLine1() {
		return _project.getContactLine1();
	}

	/**
	* Returns the contact line2 of this project.
	*
	* @return the contact line2 of this project
	*/
	@Override
	public java.lang.String getContactLine2() {
		return _project.getContactLine2();
	}

	/**
	* Returns the contact name of this project.
	*
	* @return the contact name of this project
	*/
	@Override
	public java.lang.String getContactName() {
		return _project.getContactName();
	}

	/**
	* Returns the contact phone number of this project.
	*
	* @return the contact phone number of this project
	*/
	@Override
	public java.lang.String getContactPhoneNumber() {
		return _project.getContactPhoneNumber();
	}

	/**
	* Returns the description of this project.
	*
	* @return the description of this project
	*/
	@Override
	public java.lang.String getDescription() {
		return _project.getDescription();
	}

	/**
	* Returns the detail url of this project.
	*
	* @return the detail url of this project
	*/
	@Override
	public java.lang.String getDetailURL() {
		return _project.getDetailURL();
	}

	/**
	* Retourne les quartiers du projet
	*/
	@Override
	public java.lang.String getDistrictCategories(java.util.Locale locale) {
		return _project.getDistrictCategories(locale);
	}

	/**
	* Returns the external image copyright of this project.
	*
	* @return the external image copyright of this project
	*/
	@Override
	public java.lang.String getExternalImageCopyright() {
		return _project.getExternalImageCopyright();
	}

	/**
	* Returns the external image url of this project.
	*
	* @return the external image url of this project
	*/
	@Override
	public java.lang.String getExternalImageURL() {
		return _project.getExternalImageURL();
	}

	/**
	* Retourne le copyright de l'image principale
	*/
	@Override
	public java.lang.String getImageCopyright(java.util.Locale locale) {
		return _project.getImageCopyright(locale);
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _project.getImageURL();
	}

	/**
	* Returns the label of this project.
	*
	* @return the label of this project
	*/
	@Override
	public java.lang.String getLabel() {
		return _project.getLabel();
	}

	/**
	* Returns the partners of this project.
	*
	* @return the partners of this project
	*/
	@Override
	public java.lang.String getPartners() {
		return _project.getPartners();
	}

	@Override
	public java.lang.String getProjectStatus(java.util.Locale locale) {
		return _project.getProjectStatus(locale);
	}

	/**
	* Returns the status by user name of this project.
	*
	* @return the status by user name of this project
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _project.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this project.
	*
	* @return the status by user uuid of this project
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _project.getStatusByUserUuid();
	}

	/**
	* Returns the title of this project.
	*
	* @return the title of this project
	*/
	@Override
	public java.lang.String getTitle() {
		return _project.getTitle();
	}

	/**
	* Returns the user name of this project.
	*
	* @return the user name of this project
	*/
	@Override
	public java.lang.String getUserName() {
		return _project.getUserName();
	}

	/**
	* Returns the user uuid of this project.
	*
	* @return the user uuid of this project
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _project.getUserUuid();
	}

	/**
	* Returns the uuid of this project.
	*
	* @return the uuid of this project
	*/
	@Override
	public java.lang.String getUuid() {
		return _project.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _project.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _project.toXmlString();
	}

	/**
	* Returns the create date of this project.
	*
	* @return the create date of this project
	*/
	@Override
	public Date getCreateDate() {
		return _project.getCreateDate();
	}

	/**
	* Returns the modified date of this project.
	*
	* @return the modified date of this project
	*/
	@Override
	public Date getModifiedDate() {
		return _project.getModifiedDate();
	}

	/**
	* Returns the status date of this project.
	*
	* @return the status date of this project
	*/
	@Override
	public Date getStatusDate() {
		return _project.getStatusDate();
	}

	/**
	* Retourne les statuts du projet
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getAllStatus() {
		return _project.getAllStatus();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _project.getCategories();
	}

	/**
	* Retourne les sous-catégories 'Territoire' correspondant aux villes du projet
	*
	* @return : null si vide, sinon la liste des catégories
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCityCategories() {
		return _project.getCityCategories();
	}

	/**
	* Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers du projet
	*
	* @return : null si vide, sinon la liste des catégories
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistrictCategories() {
		return _project.getDistrictCategories();
	}

	/**
	* Retourne la liste des évènements du projet
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry> getEvents() {
		return _project.getEvents();
	}

	/**
	* Retourne la liste des participations du projet
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry> getParticipations() {
		return _project.getParticipations();
	}

	/**
	* Retourne la liste des entrées timelines du projet
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.ProjectTimeline> getProjectTimelines() {
		return _project.getProjectTimelines();
	}

	/**
	* Retourne les catégories 'Territoire' correspondant aux pays du projet
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritoryCategories() {
		return _project.getTerritoryCategories();
	}

	/**
	* Retourne les thematiques du projet
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThematicCategories() {
		return _project.getThematicCategories();
	}

	/**
	* Returns the company ID of this project.
	*
	* @return the company ID of this project
	*/
	@Override
	public long getCompanyId() {
		return _project.getCompanyId();
	}

	/**
	* Returns the group ID of this project.
	*
	* @return the group ID of this project
	*/
	@Override
	public long getGroupId() {
		return _project.getGroupId();
	}

	/**
	* Returns the image ID of this project.
	*
	* @return the image ID of this project
	*/
	@Override
	public long getImageId() {
		return _project.getImageId();
	}

	/**
	* Returns the primary key of this project.
	*
	* @return the primary key of this project
	*/
	@Override
	public long getPrimaryKey() {
		return _project.getPrimaryKey();
	}

	/**
	* Returns the project ID of this project.
	*
	* @return the project ID of this project
	*/
	@Override
	public long getProjectId() {
		return _project.getProjectId();
	}

	/**
	* Returns the status by user ID of this project.
	*
	* @return the status by user ID of this project
	*/
	@Override
	public long getStatusByUserId() {
		return _project.getStatusByUserId();
	}

	/**
	* Returns the user ID of this project.
	*
	* @return the user ID of this project
	*/
	@Override
	public long getUserId() {
		return _project.getUserId();
	}

	@Override
	public void persist() {
		_project.persist();
	}

	/**
	* Sets the budget of this project.
	*
	* @param budget the budget of this project
	*/
	@Override
	public void setBudget(java.lang.String budget) {
		_project.setBudget(budget);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_project.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this project.
	*
	* @param companyId the company ID of this project
	*/
	@Override
	public void setCompanyId(long companyId) {
		_project.setCompanyId(companyId);
	}

	/**
	* Sets the contact line1 of this project.
	*
	* @param contactLine1 the contact line1 of this project
	*/
	@Override
	public void setContactLine1(java.lang.String contactLine1) {
		_project.setContactLine1(contactLine1);
	}

	/**
	* Sets the contact line2 of this project.
	*
	* @param contactLine2 the contact line2 of this project
	*/
	@Override
	public void setContactLine2(java.lang.String contactLine2) {
		_project.setContactLine2(contactLine2);
	}

	/**
	* Sets the contact name of this project.
	*
	* @param contactName the contact name of this project
	*/
	@Override
	public void setContactName(java.lang.String contactName) {
		_project.setContactName(contactName);
	}

	/**
	* Sets the contact phone number of this project.
	*
	* @param contactPhoneNumber the contact phone number of this project
	*/
	@Override
	public void setContactPhoneNumber(java.lang.String contactPhoneNumber) {
		_project.setContactPhoneNumber(contactPhoneNumber);
	}

	/**
	* Sets the create date of this project.
	*
	* @param createDate the create date of this project
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_project.setCreateDate(createDate);
	}

	/**
	* Sets the description of this project.
	*
	* @param description the description of this project
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_project.setDescription(description);
	}

	/**
	* Sets the detail url of this project.
	*
	* @param detailURL the detail url of this project
	*/
	@Override
	public void setDetailURL(java.lang.String detailURL) {
		_project.setDetailURL(detailURL);
	}

	/**
	* Sets the duration of this project.
	*
	* @param duration the duration of this project
	*/
	@Override
	public void setDuration(int duration) {
		_project.setDuration(duration);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_project.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_project.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_project.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the external image copyright of this project.
	*
	* @param externalImageCopyright the external image copyright of this project
	*/
	@Override
	public void setExternalImageCopyright(
		java.lang.String externalImageCopyright) {
		_project.setExternalImageCopyright(externalImageCopyright);
	}

	/**
	* Sets the external image url of this project.
	*
	* @param externalImageURL the external image url of this project
	*/
	@Override
	public void setExternalImageURL(java.lang.String externalImageURL) {
		_project.setExternalImageURL(externalImageURL);
	}

	/**
	* Sets the group ID of this project.
	*
	* @param groupId the group ID of this project
	*/
	@Override
	public void setGroupId(long groupId) {
		_project.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this project.
	*
	* @param imageId the image ID of this project
	*/
	@Override
	public void setImageId(long imageId) {
		_project.setImageId(imageId);
	}

	/**
	* Sets the label of this project.
	*
	* @param label the label of this project
	*/
	@Override
	public void setLabel(java.lang.String label) {
		_project.setLabel(label);
	}

	/**
	* Sets the modified date of this project.
	*
	* @param modifiedDate the modified date of this project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_project.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_project.setNew(n);
	}

	/**
	* Sets the partners of this project.
	*
	* @param partners the partners of this project
	*/
	@Override
	public void setPartners(java.lang.String partners) {
		_project.setPartners(partners);
	}

	/**
	* Sets the primary key of this project.
	*
	* @param primaryKey the primary key of this project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_project.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_project.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project ID of this project.
	*
	* @param projectId the project ID of this project
	*/
	@Override
	public void setProjectId(long projectId) {
		_project.setProjectId(projectId);
	}

	/**
	* Sets the status of this project.
	*
	* @param status the status of this project
	*/
	@Override
	public void setStatus(int status) {
		_project.setStatus(status);
	}

	/**
	* Sets the status by user ID of this project.
	*
	* @param statusByUserId the status by user ID of this project
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_project.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this project.
	*
	* @param statusByUserName the status by user name of this project
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_project.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this project.
	*
	* @param statusByUserUuid the status by user uuid of this project
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_project.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this project.
	*
	* @param statusDate the status date of this project
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_project.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this project.
	*
	* @param title the title of this project
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_project.setTitle(title);
	}

	/**
	* Sets the user ID of this project.
	*
	* @param userId the user ID of this project
	*/
	@Override
	public void setUserId(long userId) {
		_project.setUserId(userId);
	}

	/**
	* Sets the user name of this project.
	*
	* @param userName the user name of this project
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_project.setUserName(userName);
	}

	/**
	* Sets the user uuid of this project.
	*
	* @param userUuid the user uuid of this project
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_project.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this project.
	*
	* @param uuid the uuid of this project
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_project.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectWrapper)) {
			return false;
		}

		ProjectWrapper projectWrapper = (ProjectWrapper)obj;

		if (Objects.equals(_project, projectWrapper._project)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _project.getStagedModelType();
	}

	@Override
	public Project getWrappedModel() {
		return _project;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _project.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _project.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_project.resetOriginalValues();
	}

	private final Project _project;
}