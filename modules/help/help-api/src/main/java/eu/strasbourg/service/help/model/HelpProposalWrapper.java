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
import org.osgi.annotation.versioning.ProviderType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("modifiedByUserDate", getModifiedByUserDate());
		attributes.put("spokenLanguages", getSpokenLanguages());
		attributes.put("imageId", getImageId());
		attributes.put("publikId", getPublikId());
		attributes.put("comment", getComment());

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

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
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

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	/**
	 * Retourne la catégorie statut activite proposition d'aide de l'aide
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory
		getActivitStatusCategory() {

		return model.getActivitStatusCategory();
	}

	/**
	 * Retourne la class du statut d'activité de la proposition d'aide (
	 */
	@Override
	public String getActivityStatusClass() {
		return model.getActivityStatusClass();
	}

	/**
	 * Retourne le statut d'activité de la proposition d'aide (
	 */
	@Override
	public String getActivityStatusTitle(java.util.Locale locale) {
		return model.getActivityStatusTitle(locale);
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

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
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
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes de la initiative
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCityCategories() {

		return model.getCityCategories();
	}

	/**
	 * Returns the comment of this help proposal.
	 *
	 * @return the comment of this help proposal
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the localized comment of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized comment of this help proposal
	 */
	@Override
	public String getComment(java.util.Locale locale) {
		return model.getComment(locale);
	}

	/**
	 * Returns the localized comment of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized comment of this help proposal. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getComment(java.util.Locale locale, boolean useDefault) {
		return model.getComment(locale, useDefault);
	}

	/**
	 * Returns the localized comment of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized comment of this help proposal
	 */
	@Override
	public String getComment(String languageId) {
		return model.getComment(languageId);
	}

	/**
	 * Returns the localized comment of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized comment of this help proposal
	 */
	@Override
	public String getComment(String languageId, boolean useDefault) {
		return model.getComment(languageId, useDefault);
	}

	@Override
	public String getCommentCurrentLanguageId() {
		return model.getCommentCurrentLanguageId();
	}

	@Override
	public String getCommentCurrentValue() {
		return model.getCommentCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized comments of this help proposal.
	 *
	 * @return the locales and localized comments of this help proposal
	 */
	@Override
	public Map<java.util.Locale, String> getCommentMap() {
		return model.getCommentMap();
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

	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
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
	 * Returns the localized description of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this help proposal
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
	}

	/**
	 * Returns the localized description of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this help proposal. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return model.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this help proposal
	 */
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this help proposal
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return model.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return model.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return model.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this help proposal.
	 *
	 * @return the locales and localized descriptions of this help proposal
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return model.getDescriptionMap();
	}

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la proposition d'aide
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
	 * Retourne les type d'aide de la proposition d'aide
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getHelpProposalTypeCategories() {

		return model.getHelpProposalTypeCategories();
	}

	/**
	 * Retourne une chaine des localisations correspondant
	 */
	@Override
	public String getHelpProposalTypeLabel(java.util.Locale locale) {
		return model.getHelpProposalTypeLabel(locale);
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(java.util.Locale locale) {
		return model.getImageCopyright(locale);
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
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return model.getImageURL();
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
	 * Retourne une chaine des localisations correspondant
	 */
	@Override
	public String getLocalisationLabel(java.util.Locale locale) {
		return model.getLocalisationLabel(locale);
	}

	/**
	 * Retourne la catégorie statut activite proposition d'aide de l'aide
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory
		getModerationStatusCategory() {

		return model.getModerationStatusCategory();
	}

	/**
	 * Retourne la class du statut de modération de la proposition d'aide (
	 */
	@Override
	public String getModerationStatusClass() {
		return model.getModerationStatusClass();
	}

	/**
	 * Retourne le statut de modération de la proposition d'aide (
	 */
	@Override
	public String getModerationStatusTitle(java.util.Locale locale) {
		return model.getModerationStatusTitle(locale);
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
	 * Returns the phone number of this help proposal.
	 *
	 * @return the phone number of this help proposal
	 */
	@Override
	public String getPhoneNumber() {
		return model.getPhoneNumber();
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
	 * Returns the localized spoken languages of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized spoken languages of this help proposal
	 */
	@Override
	public String getSpokenLanguages(java.util.Locale locale) {
		return model.getSpokenLanguages(locale);
	}

	/**
	 * Returns the localized spoken languages of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized spoken languages of this help proposal. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getSpokenLanguages(
		java.util.Locale locale, boolean useDefault) {

		return model.getSpokenLanguages(locale, useDefault);
	}

	/**
	 * Returns the localized spoken languages of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized spoken languages of this help proposal
	 */
	@Override
	public String getSpokenLanguages(String languageId) {
		return model.getSpokenLanguages(languageId);
	}

	/**
	 * Returns the localized spoken languages of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized spoken languages of this help proposal
	 */
	@Override
	public String getSpokenLanguages(String languageId, boolean useDefault) {
		return model.getSpokenLanguages(languageId, useDefault);
	}

	@Override
	public String getSpokenLanguagesCurrentLanguageId() {
		return model.getSpokenLanguagesCurrentLanguageId();
	}

	@Override
	public String getSpokenLanguagesCurrentValue() {
		return model.getSpokenLanguagesCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized spoken languageses of this help proposal.
	 *
	 * @return the locales and localized spoken languageses of this help proposal
	 */
	@Override
	public Map<java.util.Locale, String> getSpokenLanguagesMap() {
		return model.getSpokenLanguagesMap();
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
	 * Retourne les catégories 'Territoire' correspondant aux pays de la proposaition d'aide
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
	 * Returns the localized title of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this help proposal
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return model.getTitle(locale);
	}

	/**
	 * Returns the localized title of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this help proposal. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return model.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this help proposal in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this help proposal
	 */
	@Override
	public String getTitle(String languageId) {
		return model.getTitle(languageId);
	}

	/**
	 * Returns the localized title of this help proposal in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this help proposal
	 */
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return model.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return model.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return model.getTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized titles of this help proposal.
	 *
	 * @return the locales and localized titles of this help proposal
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return model.getTitleMap();
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

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport(defaultImportLocale);
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
	 * Sets the comment of this help proposal.
	 *
	 * @param comment the comment of this help proposal
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets the localized comment of this help proposal in the language.
	 *
	 * @param comment the localized comment of this help proposal
	 * @param locale the locale of the language
	 */
	@Override
	public void setComment(String comment, java.util.Locale locale) {
		model.setComment(comment, locale);
	}

	/**
	 * Sets the localized comment of this help proposal in the language, and sets the default locale.
	 *
	 * @param comment the localized comment of this help proposal
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setComment(
		String comment, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setComment(comment, locale, defaultLocale);
	}

	@Override
	public void setCommentCurrentLanguageId(String languageId) {
		model.setCommentCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized comments of this help proposal from the map of locales and localized comments.
	 *
	 * @param commentMap the locales and localized comments of this help proposal
	 */
	@Override
	public void setCommentMap(Map<java.util.Locale, String> commentMap) {
		model.setCommentMap(commentMap);
	}

	/**
	 * Sets the localized comments of this help proposal from the map of locales and localized comments, and sets the default locale.
	 *
	 * @param commentMap the locales and localized comments of this help proposal
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setCommentMap(
		Map<java.util.Locale, String> commentMap,
		java.util.Locale defaultLocale) {

		model.setCommentMap(commentMap, defaultLocale);
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
	 * Sets the localized description of this help proposal in the language.
	 *
	 * @param description the localized description of this help proposal
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this help proposal in the language, and sets the default locale.
	 *
	 * @param description the localized description of this help proposal
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		model.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this help proposal from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this help proposal
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		model.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this help proposal from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this help proposal
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		model.setDescriptionMap(descriptionMap, defaultLocale);
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
	 * Sets the phone number of this help proposal.
	 *
	 * @param phoneNumber the phone number of this help proposal
	 */
	@Override
	public void setPhoneNumber(String phoneNumber) {
		model.setPhoneNumber(phoneNumber);
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
	 * Sets the localized spoken languages of this help proposal in the language.
	 *
	 * @param spokenLanguages the localized spoken languages of this help proposal
	 * @param locale the locale of the language
	 */
	@Override
	public void setSpokenLanguages(
		String spokenLanguages, java.util.Locale locale) {

		model.setSpokenLanguages(spokenLanguages, locale);
	}

	/**
	 * Sets the localized spoken languages of this help proposal in the language, and sets the default locale.
	 *
	 * @param spokenLanguages the localized spoken languages of this help proposal
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSpokenLanguages(
		String spokenLanguages, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setSpokenLanguages(spokenLanguages, locale, defaultLocale);
	}

	@Override
	public void setSpokenLanguagesCurrentLanguageId(String languageId) {
		model.setSpokenLanguagesCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized spoken languageses of this help proposal from the map of locales and localized spoken languageses.
	 *
	 * @param spokenLanguagesMap the locales and localized spoken languageses of this help proposal
	 */
	@Override
	public void setSpokenLanguagesMap(
		Map<java.util.Locale, String> spokenLanguagesMap) {

		model.setSpokenLanguagesMap(spokenLanguagesMap);
	}

	/**
	 * Sets the localized spoken languageses of this help proposal from the map of locales and localized spoken languageses, and sets the default locale.
	 *
	 * @param spokenLanguagesMap the locales and localized spoken languageses of this help proposal
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSpokenLanguagesMap(
		Map<java.util.Locale, String> spokenLanguagesMap,
		java.util.Locale defaultLocale) {

		model.setSpokenLanguagesMap(spokenLanguagesMap, defaultLocale);
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
	 * Sets the localized title of this help proposal in the language.
	 *
	 * @param title the localized title of this help proposal
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		model.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this help proposal in the language, and sets the default locale.
	 *
	 * @param title the localized title of this help proposal
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitle(
		String title, java.util.Locale locale, java.util.Locale defaultLocale) {

		model.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		model.setTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized titles of this help proposal from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this help proposal
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		model.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this help proposal from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this help proposal
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		model.setTitleMap(titleMap, defaultLocale);
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
	public com.liferay.portal.kernel.json.JSONObject toJSON(
			java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.toJSON(locale);
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