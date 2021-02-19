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

package eu.strasbourg.service.help.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link HelpProposal}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpProposal
 * @generated
 */
@ProviderType
public class HelpProposalWrapper
	extends BaseModelWrapper<HelpProposal>
	implements HelpProposal, ModelWrapper<HelpProposal> {

	public HelpProposalWrapper(HelpProposal helpProposal) {
		super(helpProposal);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("helpProposalId", getHelpProposalId());
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
		attributes.put("inTheNameOf", getInTheNameOf());
		attributes.put("address", getAddress());
		attributes.put("city", getCity());
		attributes.put("postalCode", getPostalCode());
		attributes.put("modifiedByUserDate", getModifiedByUserDate());
		attributes.put("spokenLanguages", getSpokenLanguages());
		attributes.put("imageId", getImageId());
		attributes.put("publikId", getPublikId());
		attributes.put("publicationDate", getPublicationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long helpProposalId = (Long)attributes.get("helpProposalId");

		if (helpProposalId != null) {
			setHelpProposalId(helpProposalId);
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

		String inTheNameOf = (String)attributes.get("inTheNameOf");

		if (inTheNameOf != null) {
			setInTheNameOf(inTheNameOf);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		Long postalCode = (Long)attributes.get("postalCode");

		if (postalCode != null) {
			setPostalCode(postalCode);
		}

		Date modifiedByUserDate = (Date)attributes.get("modifiedByUserDate");

		if (modifiedByUserDate != null) {
			setModifiedByUserDate(modifiedByUserDate);
		}

		String spokenLanguages = (String)attributes.get("spokenLanguages");

		if (spokenLanguages != null) {
			setSpokenLanguages(spokenLanguages);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}
	}

	/**
	 * Returns the address of this help proposal.
	 *
	 * @return the address of this help proposal
	 */
	@Override
	public String getAddress() {
		return model.getAddress();
	}

	/**
	 * Retourne l'AssetEntry rattaché a cette proposition d'aide
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return model.getAssetEntry();
	}

	/**
	 * Retourne l'utilisateur Publik depositaire
	 *
	 * @return
	 */
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser getAuthor() {
		return model.getAuthor();
	}

	/**
	 * Retourne l'adresse mail du depositaire s'il existe
	 */
	@Override
	public String getAuthorEmail() {
		return model.getAuthorEmail();
	}

	/**
	 * Retourne le nom de du depositaire sous forme "Truc M." ou le "Au nom de ..."
	 */
	@Override
	public String getAuthorLabel() {
		return model.getAuthorLabel();
	}

	/**
	 * Retourne le nom prenom du depositaire s'il existe
	 */
	@Override
	public String getAuthorNameLabel() {
		return model.getAuthorNameLabel();
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cette proposition d'aide (via
	 * l'assetEntry)
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return model.getCategories();
	}

	/**
	 * Returns the city of this help proposal.
	 *
	 * @return the city of this help proposal
	 */
	@Override
	public String getCity() {
		return model.getCity();
	}

	/**
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes de la proposition d'aide
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCityCategories() {

		return model.getCityCategories();
	}

	/**
	 * Returns the company ID of this help proposal.
	 *
	 * @return the company ID of this help proposal
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this help proposal.
	 *
	 * @return the create date of this help proposal
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this help proposal.
	 *
	 * @return the description of this help proposal
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la initiative
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getDistrictCategories() {

		return model.getDistrictCategories();
	}

	/**
	 * Returns the group ID of this help proposal.
	 *
	 * @return the group ID of this help proposal
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the help proposal ID of this help proposal.
	 *
	 * @return the help proposal ID of this help proposal
	 */
	@Override
	public long getHelpProposalId() {
		return model.getHelpProposalId();
	}

	/**
	 * Returns the image ID of this help proposal.
	 *
	 * @return the image ID of this help proposal
	 */
	@Override
	public long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the in the name of of this help proposal.
	 *
	 * @return the in the name of of this help proposal
	 */
	@Override
	public String getInTheNameOf() {
		return model.getInTheNameOf();
	}

	/**
	 * Returns the modified by user date of this help proposal.
	 *
	 * @return the modified by user date of this help proposal
	 */
	@Override
	public Date getModifiedByUserDate() {
		return model.getModifiedByUserDate();
	}

	/**
	 * Returns the modified date of this help proposal.
	 *
	 * @return the modified date of this help proposal
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Retourne le nombre de demandes d'aides pour cette proposition
	 */
	@Override
	public int getNbHelpRequests() {
		return model.getNbHelpRequests();
	}

	/**
	 * Returns the postal code of this help proposal.
	 *
	 * @return the postal code of this help proposal
	 */
	@Override
	public long getPostalCode() {
		return model.getPostalCode();
	}

	/**
	 * Returns the primary key of this help proposal.
	 *
	 * @return the primary key of this help proposal
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the publication date of this help proposal.
	 *
	 * @return the publication date of this help proposal
	 */
	@Override
	public Date getPublicationDate() {
		return model.getPublicationDate();
	}

	/**
	 * @return La date de publication au format français jj/mm/aaaa
	 */
	@Override
	public String getPublicationDateFr() {
		return model.getPublicationDateFr();
	}

	/**
	 * Returns the publik ID of this help proposal.
	 *
	 * @return the publik ID of this help proposal
	 */
	@Override
	public String getPublikId() {
		return model.getPublikId();
	}

	/**
	 * Returns the spoken languages of this help proposal.
	 *
	 * @return the spoken languages of this help proposal
	 */
	@Override
	public String getSpokenLanguages() {
		return model.getSpokenLanguages();
	}

	/**
	 * Returns the status of this help proposal.
	 *
	 * @return the status of this help proposal
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this help proposal.
	 *
	 * @return the status by user ID of this help proposal
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this help proposal.
	 *
	 * @return the status by user name of this help proposal
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this help proposal.
	 *
	 * @return the status by user uuid of this help proposal
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this help proposal.
	 *
	 * @return the status date of this help proposal
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Retourne les catégories 'Territoire' correspondant aux pays de la initiative
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTerritoryCategories() {

		return model.getTerritoryCategories();
	}

	/**
	 * Returns the title of this help proposal.
	 *
	 * @return the title of this help proposal
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the user ID of this help proposal.
	 *
	 * @return the user ID of this help proposal
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this help proposal.
	 *
	 * @return the user name of this help proposal
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this help proposal.
	 *
	 * @return the user uuid of this help proposal
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this help proposal.
	 *
	 * @return the uuid of this help proposal
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this help proposal is approved.
	 *
	 * @return <code>true</code> if this help proposal is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this help proposal is denied.
	 *
	 * @return <code>true</code> if this help proposal is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this help proposal is a draft.
	 *
	 * @return <code>true</code> if this help proposal is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this help proposal is expired.
	 *
	 * @return <code>true</code> if this help proposal is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this help proposal is inactive.
	 *
	 * @return <code>true</code> if this help proposal is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this help proposal is incomplete.
	 *
	 * @return <code>true</code> if this help proposal is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this help proposal is pending.
	 *
	 * @return <code>true</code> if this help proposal is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this help proposal is scheduled.
	 *
	 * @return <code>true</code> if this help proposal is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the address of this help proposal.
	 *
	 * @param address the address of this help proposal
	 */
	@Override
	public void setAddress(String address) {
		model.setAddress(address);
	}

	/**
	 * Sets the city of this help proposal.
	 *
	 * @param city the city of this help proposal
	 */
	@Override
	public void setCity(String city) {
		model.setCity(city);
	}

	/**
	 * Sets the company ID of this help proposal.
	 *
	 * @param companyId the company ID of this help proposal
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this help proposal.
	 *
	 * @param createDate the create date of this help proposal
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this help proposal.
	 *
	 * @param description the description of this help proposal
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the group ID of this help proposal.
	 *
	 * @param groupId the group ID of this help proposal
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the help proposal ID of this help proposal.
	 *
	 * @param helpProposalId the help proposal ID of this help proposal
	 */
	@Override
	public void setHelpProposalId(long helpProposalId) {
		model.setHelpProposalId(helpProposalId);
	}

	/**
	 * Sets the image ID of this help proposal.
	 *
	 * @param imageId the image ID of this help proposal
	 */
	@Override
	public void setImageId(long imageId) {
		model.setImageId(imageId);
	}

	/**
	 * Sets the in the name of of this help proposal.
	 *
	 * @param inTheNameOf the in the name of of this help proposal
	 */
	@Override
	public void setInTheNameOf(String inTheNameOf) {
		model.setInTheNameOf(inTheNameOf);
	}

	/**
	 * Sets the modified by user date of this help proposal.
	 *
	 * @param modifiedByUserDate the modified by user date of this help proposal
	 */
	@Override
	public void setModifiedByUserDate(Date modifiedByUserDate) {
		model.setModifiedByUserDate(modifiedByUserDate);
	}

	/**
	 * Sets the modified date of this help proposal.
	 *
	 * @param modifiedDate the modified date of this help proposal
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the postal code of this help proposal.
	 *
	 * @param postalCode the postal code of this help proposal
	 */
	@Override
	public void setPostalCode(long postalCode) {
		model.setPostalCode(postalCode);
	}

	/**
	 * Sets the primary key of this help proposal.
	 *
	 * @param primaryKey the primary key of this help proposal
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the publication date of this help proposal.
	 *
	 * @param publicationDate the publication date of this help proposal
	 */
	@Override
	public void setPublicationDate(Date publicationDate) {
		model.setPublicationDate(publicationDate);
	}

	/**
	 * Sets the publik ID of this help proposal.
	 *
	 * @param publikId the publik ID of this help proposal
	 */
	@Override
	public void setPublikId(String publikId) {
		model.setPublikId(publikId);
	}

	/**
	 * Sets the spoken languages of this help proposal.
	 *
	 * @param spokenLanguages the spoken languages of this help proposal
	 */
	@Override
	public void setSpokenLanguages(String spokenLanguages) {
		model.setSpokenLanguages(spokenLanguages);
	}

	/**
	 * Sets the status of this help proposal.
	 *
	 * @param status the status of this help proposal
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this help proposal.
	 *
	 * @param statusByUserId the status by user ID of this help proposal
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this help proposal.
	 *
	 * @param statusByUserName the status by user name of this help proposal
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this help proposal.
	 *
	 * @param statusByUserUuid the status by user uuid of this help proposal
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this help proposal.
	 *
	 * @param statusDate the status date of this help proposal
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the title of this help proposal.
	 *
	 * @param title the title of this help proposal
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the user ID of this help proposal.
	 *
	 * @param userId the user ID of this help proposal
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this help proposal.
	 *
	 * @param userName the user name of this help proposal
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this help proposal.
	 *
	 * @param userUuid the user uuid of this help proposal
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this help proposal.
	 *
	 * @param uuid the uuid of this help proposal
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Retourne la version JSON de l'entité
	 *
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.toJSON();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected HelpProposalWrapper wrap(HelpProposal helpProposal) {
		return new HelpProposalWrapper(helpProposal);
	}

}