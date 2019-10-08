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
 * This class is a wrapper for {@link BudgetPhase}.
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetPhase
 * @generated
 */
@ProviderType
public class BudgetPhaseWrapper implements BudgetPhase,
	ModelWrapper<BudgetPhase> {
	public BudgetPhaseWrapper(BudgetPhase budgetPhase) {
		_budgetPhase = budgetPhase;
	}

	@Override
	public Class<?> getModelClass() {
		return BudgetPhase.class;
	}

	@Override
	public String getModelClassName() {
		return BudgetPhase.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("budgetPhaseId", getBudgetPhaseId());
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
		attributes.put("description", getDescription());
		attributes.put("numberOfVote", getNumberOfVote());
		attributes.put("isActive", getIsActive());
		attributes.put("beginDate", getBeginDate());
		attributes.put("endDate", getEndDate());
		attributes.put("beginVoteDate", getBeginVoteDate());
		attributes.put("endVoteDate", getEndVoteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long budgetPhaseId = (Long)attributes.get("budgetPhaseId");

		if (budgetPhaseId != null) {
			setBudgetPhaseId(budgetPhaseId);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long numberOfVote = (Long)attributes.get("numberOfVote");

		if (numberOfVote != null) {
			setNumberOfVote(numberOfVote);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		Date beginDate = (Date)attributes.get("beginDate");

		if (beginDate != null) {
			setBeginDate(beginDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date beginVoteDate = (Date)attributes.get("beginVoteDate");

		if (beginVoteDate != null) {
			setBeginVoteDate(beginVoteDate);
		}

		Date endVoteDate = (Date)attributes.get("endVoteDate");

		if (endVoteDate != null) {
			setEndVoteDate(endVoteDate);
		}
	}

	/**
	* Returns the is active of this budget phase.
	*
	* @return the is active of this budget phase
	*/
	@Override
	public boolean getIsActive() {
		return _budgetPhase.getIsActive();
	}

	/**
	* Returns <code>true</code> if this budget phase is approved.
	*
	* @return <code>true</code> if this budget phase is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _budgetPhase.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _budgetPhase.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this budget phase is denied.
	*
	* @return <code>true</code> if this budget phase is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _budgetPhase.isDenied();
	}

	/**
	* Returns <code>true</code> if this budget phase is a draft.
	*
	* @return <code>true</code> if this budget phase is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _budgetPhase.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _budgetPhase.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this budget phase is expired.
	*
	* @return <code>true</code> if this budget phase is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _budgetPhase.isExpired();
	}

	/**
	* Renvoie si la phase est en période de dépot
	*/
	@Override
	public boolean isInDepositPeriod() {
		return _budgetPhase.isInDepositPeriod();
	}

	/**
	* Renvoie si la phase est en période de vote
	*/
	@Override
	public boolean isInVotingPeriod() {
		return _budgetPhase.isInVotingPeriod();
	}

	/**
	* Returns <code>true</code> if this budget phase is inactive.
	*
	* @return <code>true</code> if this budget phase is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _budgetPhase.isInactive();
	}

	/**
	* Returns <code>true</code> if this budget phase is incomplete.
	*
	* @return <code>true</code> if this budget phase is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _budgetPhase.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this budget phase is is active.
	*
	* @return <code>true</code> if this budget phase is is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsActive() {
		return _budgetPhase.isIsActive();
	}

	@Override
	public boolean isNew() {
		return _budgetPhase.isNew();
	}

	/**
	* Returns <code>true</code> if this budget phase is pending.
	*
	* @return <code>true</code> if this budget phase is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _budgetPhase.isPending();
	}

	/**
	* Returns <code>true</code> if this budget phase is scheduled.
	*
	* @return <code>true</code> if this budget phase is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _budgetPhase.isScheduled();
	}

	/**
	* Retourne la categorie "Phase du budget participatif" de la phase
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getPhaseCategory() {
		return _budgetPhase.getPhaseCategory();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _budgetPhase.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _budgetPhase.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.BudgetPhase> toCacheModel() {
		return _budgetPhase.toCacheModel();
	}

	/**
	* La l'etat de la phase
	*
	* @return
	*/
	@Override
	public eu.strasbourg.service.project.constants.PhaseState getPhaseState() {
		return _budgetPhase.getPhaseState();
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetPhase toEscapedModel() {
		return new BudgetPhaseWrapper(_budgetPhase.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetPhase toUnescapedModel() {
		return new BudgetPhaseWrapper(_budgetPhase.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.BudgetPhase budgetPhase) {
		return _budgetPhase.compareTo(budgetPhase);
	}

	/**
	* Returns the status of this budget phase.
	*
	* @return the status of this budget phase
	*/
	@Override
	public int getStatus() {
		return _budgetPhase.getStatus();
	}

	@Override
	public int hashCode() {
		return _budgetPhase.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _budgetPhase.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new BudgetPhaseWrapper((BudgetPhase)_budgetPhase.clone());
	}

	/**
	* Returns the description of this budget phase.
	*
	* @return the description of this budget phase
	*/
	@Override
	public java.lang.String getDescription() {
		return _budgetPhase.getDescription();
	}

	/**
	* Genere le label de haut de page affichant le temps restant avant de passer a la prochaine
	* peridode de la phase en cours
	*/
	@Override
	public java.lang.String getLivePeriodLabel() {
		return _budgetPhase.getLivePeriodLabel();
	}

	/**
	* Returns the status by user name of this budget phase.
	*
	* @return the status by user name of this budget phase
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _budgetPhase.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this budget phase.
	*
	* @return the status by user uuid of this budget phase
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _budgetPhase.getStatusByUserUuid();
	}

	/**
	* Returns the title of this budget phase.
	*
	* @return the title of this budget phase
	*/
	@Override
	public java.lang.String getTitle() {
		return _budgetPhase.getTitle();
	}

	/**
	* Returns the user name of this budget phase.
	*
	* @return the user name of this budget phase
	*/
	@Override
	public java.lang.String getUserName() {
		return _budgetPhase.getUserName();
	}

	/**
	* Returns the user uuid of this budget phase.
	*
	* @return the user uuid of this budget phase
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _budgetPhase.getUserUuid();
	}

	/**
	* Returns the uuid of this budget phase.
	*
	* @return the uuid of this budget phase
	*/
	@Override
	public java.lang.String getUuid() {
		return _budgetPhase.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _budgetPhase.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _budgetPhase.toXmlString();
	}

	/**
	* Returns the begin date of this budget phase.
	*
	* @return the begin date of this budget phase
	*/
	@Override
	public Date getBeginDate() {
		return _budgetPhase.getBeginDate();
	}

	/**
	* Returns the begin vote date of this budget phase.
	*
	* @return the begin vote date of this budget phase
	*/
	@Override
	public Date getBeginVoteDate() {
		return _budgetPhase.getBeginVoteDate();
	}

	/**
	* Returns the create date of this budget phase.
	*
	* @return the create date of this budget phase
	*/
	@Override
	public Date getCreateDate() {
		return _budgetPhase.getCreateDate();
	}

	/**
	* Returns the end date of this budget phase.
	*
	* @return the end date of this budget phase
	*/
	@Override
	public Date getEndDate() {
		return _budgetPhase.getEndDate();
	}

	/**
	* Returns the end vote date of this budget phase.
	*
	* @return the end vote date of this budget phase
	*/
	@Override
	public Date getEndVoteDate() {
		return _budgetPhase.getEndVoteDate();
	}

	/**
	* Returns the modified date of this budget phase.
	*
	* @return the modified date of this budget phase
	*/
	@Override
	public Date getModifiedDate() {
		return _budgetPhase.getModifiedDate();
	}

	/**
	* Returns the status date of this budget phase.
	*
	* @return the status date of this budget phase
	*/
	@Override
	public Date getStatusDate() {
		return _budgetPhase.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _budgetPhase.getCategories();
	}

	/**
	* Returns the budget phase ID of this budget phase.
	*
	* @return the budget phase ID of this budget phase
	*/
	@Override
	public long getBudgetPhaseId() {
		return _budgetPhase.getBudgetPhaseId();
	}

	/**
	* Returns the company ID of this budget phase.
	*
	* @return the company ID of this budget phase
	*/
	@Override
	public long getCompanyId() {
		return _budgetPhase.getCompanyId();
	}

	/**
	* Returns the group ID of this budget phase.
	*
	* @return the group ID of this budget phase
	*/
	@Override
	public long getGroupId() {
		return _budgetPhase.getGroupId();
	}

	/**
	* Returns the number of vote of this budget phase.
	*
	* @return the number of vote of this budget phase
	*/
	@Override
	public long getNumberOfVote() {
		return _budgetPhase.getNumberOfVote();
	}

	/**
	* Returns the primary key of this budget phase.
	*
	* @return the primary key of this budget phase
	*/
	@Override
	public long getPrimaryKey() {
		return _budgetPhase.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this budget phase.
	*
	* @return the status by user ID of this budget phase
	*/
	@Override
	public long getStatusByUserId() {
		return _budgetPhase.getStatusByUserId();
	}

	/**
	* Returns the user ID of this budget phase.
	*
	* @return the user ID of this budget phase
	*/
	@Override
	public long getUserId() {
		return _budgetPhase.getUserId();
	}

	@Override
	public void persist() {
		_budgetPhase.persist();
	}

	/**
	* Sets the begin date of this budget phase.
	*
	* @param beginDate the begin date of this budget phase
	*/
	@Override
	public void setBeginDate(Date beginDate) {
		_budgetPhase.setBeginDate(beginDate);
	}

	/**
	* Sets the begin vote date of this budget phase.
	*
	* @param beginVoteDate the begin vote date of this budget phase
	*/
	@Override
	public void setBeginVoteDate(Date beginVoteDate) {
		_budgetPhase.setBeginVoteDate(beginVoteDate);
	}

	/**
	* Sets the budget phase ID of this budget phase.
	*
	* @param budgetPhaseId the budget phase ID of this budget phase
	*/
	@Override
	public void setBudgetPhaseId(long budgetPhaseId) {
		_budgetPhase.setBudgetPhaseId(budgetPhaseId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_budgetPhase.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this budget phase.
	*
	* @param companyId the company ID of this budget phase
	*/
	@Override
	public void setCompanyId(long companyId) {
		_budgetPhase.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this budget phase.
	*
	* @param createDate the create date of this budget phase
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_budgetPhase.setCreateDate(createDate);
	}

	/**
	* Sets the description of this budget phase.
	*
	* @param description the description of this budget phase
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_budgetPhase.setDescription(description);
	}

	/**
	* Sets the end date of this budget phase.
	*
	* @param endDate the end date of this budget phase
	*/
	@Override
	public void setEndDate(Date endDate) {
		_budgetPhase.setEndDate(endDate);
	}

	/**
	* Sets the end vote date of this budget phase.
	*
	* @param endVoteDate the end vote date of this budget phase
	*/
	@Override
	public void setEndVoteDate(Date endVoteDate) {
		_budgetPhase.setEndVoteDate(endVoteDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_budgetPhase.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_budgetPhase.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_budgetPhase.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this budget phase.
	*
	* @param groupId the group ID of this budget phase
	*/
	@Override
	public void setGroupId(long groupId) {
		_budgetPhase.setGroupId(groupId);
	}

	/**
	* Sets whether this budget phase is is active.
	*
	* @param isActive the is active of this budget phase
	*/
	@Override
	public void setIsActive(boolean isActive) {
		_budgetPhase.setIsActive(isActive);
	}

	/**
	* Sets the modified date of this budget phase.
	*
	* @param modifiedDate the modified date of this budget phase
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_budgetPhase.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_budgetPhase.setNew(n);
	}

	/**
	* Sets the number of vote of this budget phase.
	*
	* @param numberOfVote the number of vote of this budget phase
	*/
	@Override
	public void setNumberOfVote(long numberOfVote) {
		_budgetPhase.setNumberOfVote(numberOfVote);
	}

	/**
	* Sets the primary key of this budget phase.
	*
	* @param primaryKey the primary key of this budget phase
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_budgetPhase.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_budgetPhase.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this budget phase.
	*
	* @param status the status of this budget phase
	*/
	@Override
	public void setStatus(int status) {
		_budgetPhase.setStatus(status);
	}

	/**
	* Sets the status by user ID of this budget phase.
	*
	* @param statusByUserId the status by user ID of this budget phase
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_budgetPhase.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this budget phase.
	*
	* @param statusByUserName the status by user name of this budget phase
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_budgetPhase.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this budget phase.
	*
	* @param statusByUserUuid the status by user uuid of this budget phase
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_budgetPhase.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this budget phase.
	*
	* @param statusDate the status date of this budget phase
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_budgetPhase.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this budget phase.
	*
	* @param title the title of this budget phase
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_budgetPhase.setTitle(title);
	}

	/**
	* Sets the user ID of this budget phase.
	*
	* @param userId the user ID of this budget phase
	*/
	@Override
	public void setUserId(long userId) {
		_budgetPhase.setUserId(userId);
	}

	/**
	* Sets the user name of this budget phase.
	*
	* @param userName the user name of this budget phase
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_budgetPhase.setUserName(userName);
	}

	/**
	* Sets the user uuid of this budget phase.
	*
	* @param userUuid the user uuid of this budget phase
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_budgetPhase.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this budget phase.
	*
	* @param uuid the uuid of this budget phase
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_budgetPhase.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BudgetPhaseWrapper)) {
			return false;
		}

		BudgetPhaseWrapper budgetPhaseWrapper = (BudgetPhaseWrapper)obj;

		if (Objects.equals(_budgetPhase, budgetPhaseWrapper._budgetPhase)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _budgetPhase.getStagedModelType();
	}

	@Override
	public BudgetPhase getWrappedModel() {
		return _budgetPhase;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _budgetPhase.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _budgetPhase.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_budgetPhase.resetOriginalValues();
	}

	private final BudgetPhase _budgetPhase;
}