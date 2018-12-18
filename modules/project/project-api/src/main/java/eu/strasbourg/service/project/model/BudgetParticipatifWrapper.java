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
 * This class is a wrapper for {@link BudgetParticipatif}.
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetParticipatif
 * @generated
 */
@ProviderType
public class BudgetParticipatifWrapper implements BudgetParticipatif,
	ModelWrapper<BudgetParticipatif> {
	public BudgetParticipatifWrapper(BudgetParticipatif budgetParticipatif) {
		_budgetParticipatif = budgetParticipatif;
	}

	@Override
	public Class<?> getModelClass() {
		return BudgetParticipatif.class;
	}

	@Override
	public String getModelClassName() {
		return BudgetParticipatif.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("budgetParticipatifId", getBudgetParticipatifId());
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
		attributes.put("budget", getBudget());
		attributes.put("motif", getMotif());
		attributes.put("placeTextArea", getPlaceTextArea());
		attributes.put("citoyenLastname", getCitoyenLastname());
		attributes.put("citoyenFirstname", getCitoyenFirstname());
		attributes.put("citoyenAdresse", getCitoyenAdresse());
		attributes.put("citoyenPostalCode", getCitoyenPostalCode());
		attributes.put("citoyenCity", getCitoyenCity());
		attributes.put("citoyenPhone", getCitoyenPhone());
		attributes.put("citoyenMobile", getCitoyenMobile());
		attributes.put("citoyenEmail", getCitoyenEmail());
		attributes.put("citoyenBirthday", getCitoyenBirthday());
		attributes.put("hasCopyright", getHasCopyright());
		attributes.put("videoUrl", getVideoUrl());
		attributes.put("isCrush", getIsCrush());
		attributes.put("crushComment", getCrushComment());
		attributes.put("publikId", getPublikId());
		attributes.put("imageId", getImageId());
		attributes.put("filesIds", getFilesIds());
		attributes.put("budgetPhaseId", getBudgetPhaseId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long budgetParticipatifId = (Long)attributes.get("budgetParticipatifId");

		if (budgetParticipatifId != null) {
			setBudgetParticipatifId(budgetParticipatifId);
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

		String budget = (String)attributes.get("budget");

		if (budget != null) {
			setBudget(budget);
		}

		String motif = (String)attributes.get("motif");

		if (motif != null) {
			setMotif(motif);
		}

		String placeTextArea = (String)attributes.get("placeTextArea");

		if (placeTextArea != null) {
			setPlaceTextArea(placeTextArea);
		}

		String citoyenLastname = (String)attributes.get("citoyenLastname");

		if (citoyenLastname != null) {
			setCitoyenLastname(citoyenLastname);
		}

		String citoyenFirstname = (String)attributes.get("citoyenFirstname");

		if (citoyenFirstname != null) {
			setCitoyenFirstname(citoyenFirstname);
		}

		String citoyenAdresse = (String)attributes.get("citoyenAdresse");

		if (citoyenAdresse != null) {
			setCitoyenAdresse(citoyenAdresse);
		}

		Long citoyenPostalCode = (Long)attributes.get("citoyenPostalCode");

		if (citoyenPostalCode != null) {
			setCitoyenPostalCode(citoyenPostalCode);
		}

		String citoyenCity = (String)attributes.get("citoyenCity");

		if (citoyenCity != null) {
			setCitoyenCity(citoyenCity);
		}

		String citoyenPhone = (String)attributes.get("citoyenPhone");

		if (citoyenPhone != null) {
			setCitoyenPhone(citoyenPhone);
		}

		String citoyenMobile = (String)attributes.get("citoyenMobile");

		if (citoyenMobile != null) {
			setCitoyenMobile(citoyenMobile);
		}

		String citoyenEmail = (String)attributes.get("citoyenEmail");

		if (citoyenEmail != null) {
			setCitoyenEmail(citoyenEmail);
		}

		Date citoyenBirthday = (Date)attributes.get("citoyenBirthday");

		if (citoyenBirthday != null) {
			setCitoyenBirthday(citoyenBirthday);
		}

		Boolean hasCopyright = (Boolean)attributes.get("hasCopyright");

		if (hasCopyright != null) {
			setHasCopyright(hasCopyright);
		}

		String videoUrl = (String)attributes.get("videoUrl");

		if (videoUrl != null) {
			setVideoUrl(videoUrl);
		}

		Boolean isCrush = (Boolean)attributes.get("isCrush");

		if (isCrush != null) {
			setIsCrush(isCrush);
		}

		String crushComment = (String)attributes.get("crushComment");

		if (crushComment != null) {
			setCrushComment(crushComment);
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

		Long budgetPhaseId = (Long)attributes.get("budgetPhaseId");

		if (budgetPhaseId != null) {
			setBudgetPhaseId(budgetPhaseId);
		}
	}

	/**
	* Returns the has copyright of this budget participatif.
	*
	* @return the has copyright of this budget participatif
	*/
	@Override
	public boolean getHasCopyright() {
		return _budgetParticipatif.getHasCopyright();
	}

	/**
	* Returns the is crush of this budget participatif.
	*
	* @return the is crush of this budget participatif
	*/
	@Override
	public boolean getIsCrush() {
		return _budgetParticipatif.getIsCrush();
	}

	/**
	* Le budget a-t-il ete evalue par l'administration ?
	*
	* @note : doit alors posseder l'un des statuts adequat
	*/
	@Override
	public boolean hasBeenEvaluated() {
		return _budgetParticipatif.hasBeenEvaluated();
	}

	/**
	* A deja fait l'oeuvre d'un vote et/ou d'une decision administrative
	*/
	@Override
	public boolean hasBeenVoted() {
		return _budgetParticipatif.hasBeenVoted();
	}

	/**
	* Returns <code>true</code> if this budget participatif is approved.
	*
	* @return <code>true</code> if this budget participatif is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _budgetParticipatif.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _budgetParticipatif.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this budget participatif is denied.
	*
	* @return <code>true</code> if this budget participatif is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _budgetParticipatif.isDenied();
	}

	/**
	* Returns <code>true</code> if this budget participatif is a draft.
	*
	* @return <code>true</code> if this budget participatif is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _budgetParticipatif.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _budgetParticipatif.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this budget participatif is expired.
	*
	* @return <code>true</code> if this budget participatif is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _budgetParticipatif.isExpired();
	}

	/**
	* Returns <code>true</code> if this budget participatif is has copyright.
	*
	* @return <code>true</code> if this budget participatif is has copyright; <code>false</code> otherwise
	*/
	@Override
	public boolean isHasCopyright() {
		return _budgetParticipatif.isHasCopyright();
	}

	/**
	* Returns <code>true</code> if this budget participatif is inactive.
	*
	* @return <code>true</code> if this budget participatif is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _budgetParticipatif.isInactive();
	}

	/**
	* Returns <code>true</code> if this budget participatif is incomplete.
	*
	* @return <code>true</code> if this budget participatif is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _budgetParticipatif.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this budget participatif is is crush.
	*
	* @return <code>true</code> if this budget participatif is is crush; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsCrush() {
		return _budgetParticipatif.isIsCrush();
	}

	@Override
	public boolean isNew() {
		return _budgetParticipatif.isNew();
	}

	/**
	* Non faisable si le statut est : Non Recevable, Non faisable, Non retenu, Annulé, Suspendu
	*/
	@Override
	public boolean isNotDoable() {
		return _budgetParticipatif.isNotDoable();
	}

	/**
	* Returns <code>true</code> if this budget participatif is pending.
	*
	* @return <code>true</code> if this budget participatif is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _budgetParticipatif.isPending();
	}

	/**
	* Returns <code>true</code> if this budget participatif is scheduled.
	*
	* @return <code>true</code> if this budget participatif is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _budgetParticipatif.isScheduled();
	}

	/**
	* Est en periode et capacite de vote
	*/
	@Override
	public boolean isVotable() {
		return _budgetParticipatif.isVotable();
	}

	/**
	* Retourne les catégories 'Statut BP' du budget participatif
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getBudgetParticipatifStatusCategory() {
		return _budgetParticipatif.getBudgetParticipatifStatusCategory();
	}

	/**
	* Retourne la categorie projet du BP
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory() {
		return _budgetParticipatif.getProjectCategory();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _budgetParticipatif.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _budgetParticipatif.getExpandoBridge();
	}

	/**
	* Retourne la version JSON de l'entité
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON(
		java.lang.String publikUserId) {
		return _budgetParticipatif.toJSON(publikUserId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.BudgetParticipatif> toCacheModel() {
		return _budgetParticipatif.toCacheModel();
	}

	/**
	* Retourne l'auteur en publik user
	*
	* @return
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser getAuthorPublikUser() {
		return _budgetParticipatif.getAuthorPublikUser();
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif toEscapedModel() {
		return new BudgetParticipatifWrapper(_budgetParticipatif.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif toUnescapedModel() {
		return new BudgetParticipatifWrapper(_budgetParticipatif.toUnescapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetPhase getPhase() {
		return _budgetParticipatif.getPhase();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.BudgetParticipatif budgetParticipatif) {
		return _budgetParticipatif.compareTo(budgetParticipatif);
	}

	/**
	* Retourne le nombre de commentaires de l'entité
	*/
	@Override
	public int getNbApprovedComments() {
		return _budgetParticipatif.getNbApprovedComments();
	}

	/**
	* Retourne le nombre de soutiens d'un utilisateur pour ce projet
	*/
	@Override
	public int getNbSupportOfUser(java.lang.String publikUserId) {
		return _budgetParticipatif.getNbSupportOfUser(publikUserId);
	}

	/**
	* Retourne le nombre de soutiens d'un utilisateur pour la phase en cours, qu'importe le projet
	*/
	@Override
	public int getNbSupportOfUserInActivePhase(java.lang.String publikUserId) {
		return _budgetParticipatif.getNbSupportOfUserInActivePhase(publikUserId);
	}

	/**
	* Returns the status of this budget participatif.
	*
	* @return the status of this budget participatif
	*/
	@Override
	public int getStatus() {
		return _budgetParticipatif.getStatus();
	}

	@Override
	public int hashCode() {
		return _budgetParticipatif.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _budgetParticipatif.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new BudgetParticipatifWrapper((BudgetParticipatif)_budgetParticipatif.clone());
	}

	@Override
	public java.lang.String getAuthor() {
		return _budgetParticipatif.getAuthor();
	}

	/**
	* Retourne l'URL de l'image de l'utilisateur
	*/
	@Override
	public java.lang.String getAuthorImageURL() {
		return _budgetParticipatif.getAuthorImageURL();
	}

	/**
	* Returns the budget of this budget participatif.
	*
	* @return the budget of this budget participatif
	*/
	@Override
	public java.lang.String getBudget() {
		return _budgetParticipatif.getBudget();
	}

	@Override
	public java.lang.String getBudgetParticipatifStatusCategoryColor() {
		return _budgetParticipatif.getBudgetParticipatifStatusCategoryColor();
	}

	@Override
	public java.lang.String getBudgetParticipatifStatusTitle(
		java.util.Locale locale) {
		return _budgetParticipatif.getBudgetParticipatifStatusTitle(locale);
	}

	/**
	* Returns the citoyen adresse of this budget participatif.
	*
	* @return the citoyen adresse of this budget participatif
	*/
	@Override
	public java.lang.String getCitoyenAdresse() {
		return _budgetParticipatif.getCitoyenAdresse();
	}

	/**
	* Returns the citoyen city of this budget participatif.
	*
	* @return the citoyen city of this budget participatif
	*/
	@Override
	public java.lang.String getCitoyenCity() {
		return _budgetParticipatif.getCitoyenCity();
	}

	/**
	* Returns the citoyen email of this budget participatif.
	*
	* @return the citoyen email of this budget participatif
	*/
	@Override
	public java.lang.String getCitoyenEmail() {
		return _budgetParticipatif.getCitoyenEmail();
	}

	/**
	* Returns the citoyen firstname of this budget participatif.
	*
	* @return the citoyen firstname of this budget participatif
	*/
	@Override
	public java.lang.String getCitoyenFirstname() {
		return _budgetParticipatif.getCitoyenFirstname();
	}

	/**
	* Returns the citoyen lastname of this budget participatif.
	*
	* @return the citoyen lastname of this budget participatif
	*/
	@Override
	public java.lang.String getCitoyenLastname() {
		return _budgetParticipatif.getCitoyenLastname();
	}

	/**
	* Returns the citoyen mobile of this budget participatif.
	*
	* @return the citoyen mobile of this budget participatif
	*/
	@Override
	public java.lang.String getCitoyenMobile() {
		return _budgetParticipatif.getCitoyenMobile();
	}

	/**
	* Returns the citoyen phone of this budget participatif.
	*
	* @return the citoyen phone of this budget participatif
	*/
	@Override
	public java.lang.String getCitoyenPhone() {
		return _budgetParticipatif.getCitoyenPhone();
	}

	/**
	* Returns the crush comment of this budget participatif.
	*
	* @return the crush comment of this budget participatif
	*/
	@Override
	public java.lang.String getCrushComment() {
		return _budgetParticipatif.getCrushComment();
	}

	/**
	* Returns the description of this budget participatif.
	*
	* @return the description of this budget participatif
	*/
	@Override
	public java.lang.String getDescription() {
		return _budgetParticipatif.getDescription();
	}

	/**
	* Retourne une chaine des 'Territoires' correspondant aux quartiers du bp
	*
	* @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	*/
	@Override
	public java.lang.String getDistrictLabel(java.util.Locale locale) {
		return _budgetParticipatif.getDistrictLabel(locale);
	}

	/**
	* Returns the files IDs of this budget participatif.
	*
	* @return the files IDs of this budget participatif
	*/
	@Override
	public java.lang.String getFilesIds() {
		return _budgetParticipatif.getFilesIds();
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _budgetParticipatif.getImageURL();
	}

	/**
	* Returns the motif of this budget participatif.
	*
	* @return the motif of this budget participatif
	*/
	@Override
	public java.lang.String getMotif() {
		return _budgetParticipatif.getMotif();
	}

	/**
	* Retourne le nombre de soutien sous le format 6 digits pour l'affichage
	*
	* @return le nombre sous le format '000124'
	*/
	@Override
	public java.lang.String getNbSupportsBoard() {
		return _budgetParticipatif.getNbSupportsBoard();
	}

	@Override
	public java.lang.String getPhaseTitleLabel() {
		return _budgetParticipatif.getPhaseTitleLabel();
	}

	/**
	* Returns the place text area of this budget participatif.
	*
	* @return the place text area of this budget participatif
	*/
	@Override
	public java.lang.String getPlaceTextArea() {
		return _budgetParticipatif.getPlaceTextArea();
	}

	/**
	* Retourne la titre du projet du BP
	*/
	@Override
	public java.lang.String getProjectName() {
		return _budgetParticipatif.getProjectName();
	}

	@Override
	public java.lang.String getPublicationDateFr() {
		return _budgetParticipatif.getPublicationDateFr();
	}

	/**
	* Returns the publik ID of this budget participatif.
	*
	* @return the publik ID of this budget participatif
	*/
	@Override
	public java.lang.String getPublikId() {
		return _budgetParticipatif.getPublikId();
	}

	/**
	* Returns the status by user name of this budget participatif.
	*
	* @return the status by user name of this budget participatif
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _budgetParticipatif.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this budget participatif.
	*
	* @return the status by user uuid of this budget participatif
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _budgetParticipatif.getStatusByUserUuid();
	}

	/**
	* Retourne une chaine des 'Thematics' sépararée d'un '-'
	*/
	@Override
	public java.lang.String getThematicsLabel(java.util.Locale locale) {
		return _budgetParticipatif.getThematicsLabel(locale);
	}

	/**
	* Returns the title of this budget participatif.
	*
	* @return the title of this budget participatif
	*/
	@Override
	public java.lang.String getTitle() {
		return _budgetParticipatif.getTitle();
	}

	/**
	* Returns the user name of this budget participatif.
	*
	* @return the user name of this budget participatif
	*/
	@Override
	public java.lang.String getUserName() {
		return _budgetParticipatif.getUserName();
	}

	/**
	* Returns the user uuid of this budget participatif.
	*
	* @return the user uuid of this budget participatif
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _budgetParticipatif.getUserUuid();
	}

	/**
	* Returns the uuid of this budget participatif.
	*
	* @return the uuid of this budget participatif
	*/
	@Override
	public java.lang.String getUuid() {
		return _budgetParticipatif.getUuid();
	}

	/**
	* Returns the video url of this budget participatif.
	*
	* @return the video url of this budget participatif
	*/
	@Override
	public java.lang.String getVideoUrl() {
		return _budgetParticipatif.getVideoUrl();
	}

	@Override
	public java.lang.String toString() {
		return _budgetParticipatif.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _budgetParticipatif.toXmlString();
	}

	/**
	* Returns the citoyen birthday of this budget participatif.
	*
	* @return the citoyen birthday of this budget participatif
	*/
	@Override
	public Date getCitoyenBirthday() {
		return _budgetParticipatif.getCitoyenBirthday();
	}

	/**
	* Returns the create date of this budget participatif.
	*
	* @return the create date of this budget participatif
	*/
	@Override
	public Date getCreateDate() {
		return _budgetParticipatif.getCreateDate();
	}

	/**
	* Returns the modified date of this budget participatif.
	*
	* @return the modified date of this budget participatif
	*/
	@Override
	public Date getModifiedDate() {
		return _budgetParticipatif.getModifiedDate();
	}

	/**
	* Returns the status date of this budget participatif.
	*
	* @return the status date of this budget participatif
	*/
	@Override
	public Date getStatusDate() {
		return _budgetParticipatif.getStatusDate();
	}

	/**
	* Retourne les commentaires de l'entité
	*/
	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Comment> getApprovedComments() {
		return _budgetParticipatif.getApprovedComments();
	}

	/**
	* retourne les catégories
	*
	* @return
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _budgetParticipatif.getCategories();
	}

	/**
	* Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers du bp
	*
	* @return : null si vide, sinon la liste des catégories
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistrictCategories() {
		return _budgetParticipatif.getDistrictCategories();
	}

	/**
	* Retourne la liste des lieux placit liés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.PlacitPlace> getPlacitPlaces() {
		return _budgetParticipatif.getPlacitPlaces();
	}

	/**
	* Retourne X suggestions max pour un BP
	*
	* @param request la requete
	* @param nbSuggestions le nombre de suggestions.
	* @return la liste de bp.
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetParticipatif> getSuggestions(
		javax.servlet.http.HttpServletRequest request, int nbSuggestions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.search.SearchException {
		return _budgetParticipatif.getSuggestions(request, nbSuggestions);
	}

	/**
	* Retourne les soutiens du budget participatif
	*
	* @return Liste des soutiens
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getSupports() {
		return _budgetParticipatif.getSupports();
	}

	/**
	* Retourne les catégories 'Territoire' correspondant aux pays du budget
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritoryCategories() {
		return _budgetParticipatif.getTerritoryCategories();
	}

	/**
	* Retourne les thematiques de la participation (
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThematicCategories() {
		return _budgetParticipatif.getThematicCategories();
	}

	/**
	* Returns the budget participatif ID of this budget participatif.
	*
	* @return the budget participatif ID of this budget participatif
	*/
	@Override
	public long getBudgetParticipatifId() {
		return _budgetParticipatif.getBudgetParticipatifId();
	}

	/**
	* Returns the budget phase ID of this budget participatif.
	*
	* @return the budget phase ID of this budget participatif
	*/
	@Override
	public long getBudgetPhaseId() {
		return _budgetParticipatif.getBudgetPhaseId();
	}

	/**
	* Returns the citoyen postal code of this budget participatif.
	*
	* @return the citoyen postal code of this budget participatif
	*/
	@Override
	public long getCitoyenPostalCode() {
		return _budgetParticipatif.getCitoyenPostalCode();
	}

	/**
	* Returns the company ID of this budget participatif.
	*
	* @return the company ID of this budget participatif
	*/
	@Override
	public long getCompanyId() {
		return _budgetParticipatif.getCompanyId();
	}

	/**
	* Returns the group ID of this budget participatif.
	*
	* @return the group ID of this budget participatif
	*/
	@Override
	public long getGroupId() {
		return _budgetParticipatif.getGroupId();
	}

	/**
	* Returns the image ID of this budget participatif.
	*
	* @return the image ID of this budget participatif
	*/
	@Override
	public long getImageId() {
		return _budgetParticipatif.getImageId();
	}

	/**
	* Retourne le nombre de soutien
	*/
	@Override
	public long getNbSupports() {
		return _budgetParticipatif.getNbSupports();
	}

	/**
	* Returns the primary key of this budget participatif.
	*
	* @return the primary key of this budget participatif
	*/
	@Override
	public long getPrimaryKey() {
		return _budgetParticipatif.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this budget participatif.
	*
	* @return the status by user ID of this budget participatif
	*/
	@Override
	public long getStatusByUserId() {
		return _budgetParticipatif.getStatusByUserId();
	}

	/**
	* Returns the user ID of this budget participatif.
	*
	* @return the user ID of this budget participatif
	*/
	@Override
	public long getUserId() {
		return _budgetParticipatif.getUserId();
	}

	@Override
	public void persist() {
		_budgetParticipatif.persist();
	}

	/**
	* Remplace le statut bp actuel par celui fournis en paramètre de la méthode
	*
	* @param budgetParticipatif
	* @param status
	*/
	@Override
	public void setBPStatus(
		eu.strasbourg.service.project.model.BudgetParticipatif budgetParticipatif,
		eu.strasbourg.service.project.constants.ParticiperCategories status,
		long groupID) {
		_budgetParticipatif.setBPStatus(budgetParticipatif, status, groupID);
	}

	/**
	* Sets the budget of this budget participatif.
	*
	* @param budget the budget of this budget participatif
	*/
	@Override
	public void setBudget(java.lang.String budget) {
		_budgetParticipatif.setBudget(budget);
	}

	/**
	* Sets the budget participatif ID of this budget participatif.
	*
	* @param budgetParticipatifId the budget participatif ID of this budget participatif
	*/
	@Override
	public void setBudgetParticipatifId(long budgetParticipatifId) {
		_budgetParticipatif.setBudgetParticipatifId(budgetParticipatifId);
	}

	/**
	* Sets the budget phase ID of this budget participatif.
	*
	* @param budgetPhaseId the budget phase ID of this budget participatif
	*/
	@Override
	public void setBudgetPhaseId(long budgetPhaseId) {
		_budgetParticipatif.setBudgetPhaseId(budgetPhaseId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_budgetParticipatif.setCachedModel(cachedModel);
	}

	/**
	* Sets the citoyen adresse of this budget participatif.
	*
	* @param citoyenAdresse the citoyen adresse of this budget participatif
	*/
	@Override
	public void setCitoyenAdresse(java.lang.String citoyenAdresse) {
		_budgetParticipatif.setCitoyenAdresse(citoyenAdresse);
	}

	/**
	* Sets the citoyen birthday of this budget participatif.
	*
	* @param citoyenBirthday the citoyen birthday of this budget participatif
	*/
	@Override
	public void setCitoyenBirthday(Date citoyenBirthday) {
		_budgetParticipatif.setCitoyenBirthday(citoyenBirthday);
	}

	/**
	* Sets the citoyen city of this budget participatif.
	*
	* @param citoyenCity the citoyen city of this budget participatif
	*/
	@Override
	public void setCitoyenCity(java.lang.String citoyenCity) {
		_budgetParticipatif.setCitoyenCity(citoyenCity);
	}

	/**
	* Sets the citoyen email of this budget participatif.
	*
	* @param citoyenEmail the citoyen email of this budget participatif
	*/
	@Override
	public void setCitoyenEmail(java.lang.String citoyenEmail) {
		_budgetParticipatif.setCitoyenEmail(citoyenEmail);
	}

	/**
	* Sets the citoyen firstname of this budget participatif.
	*
	* @param citoyenFirstname the citoyen firstname of this budget participatif
	*/
	@Override
	public void setCitoyenFirstname(java.lang.String citoyenFirstname) {
		_budgetParticipatif.setCitoyenFirstname(citoyenFirstname);
	}

	/**
	* Sets the citoyen lastname of this budget participatif.
	*
	* @param citoyenLastname the citoyen lastname of this budget participatif
	*/
	@Override
	public void setCitoyenLastname(java.lang.String citoyenLastname) {
		_budgetParticipatif.setCitoyenLastname(citoyenLastname);
	}

	/**
	* Sets the citoyen mobile of this budget participatif.
	*
	* @param citoyenMobile the citoyen mobile of this budget participatif
	*/
	@Override
	public void setCitoyenMobile(java.lang.String citoyenMobile) {
		_budgetParticipatif.setCitoyenMobile(citoyenMobile);
	}

	/**
	* Sets the citoyen phone of this budget participatif.
	*
	* @param citoyenPhone the citoyen phone of this budget participatif
	*/
	@Override
	public void setCitoyenPhone(java.lang.String citoyenPhone) {
		_budgetParticipatif.setCitoyenPhone(citoyenPhone);
	}

	/**
	* Sets the citoyen postal code of this budget participatif.
	*
	* @param citoyenPostalCode the citoyen postal code of this budget participatif
	*/
	@Override
	public void setCitoyenPostalCode(long citoyenPostalCode) {
		_budgetParticipatif.setCitoyenPostalCode(citoyenPostalCode);
	}

	/**
	* Sets the company ID of this budget participatif.
	*
	* @param companyId the company ID of this budget participatif
	*/
	@Override
	public void setCompanyId(long companyId) {
		_budgetParticipatif.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this budget participatif.
	*
	* @param createDate the create date of this budget participatif
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_budgetParticipatif.setCreateDate(createDate);
	}

	/**
	* Sets the crush comment of this budget participatif.
	*
	* @param crushComment the crush comment of this budget participatif
	*/
	@Override
	public void setCrushComment(java.lang.String crushComment) {
		_budgetParticipatif.setCrushComment(crushComment);
	}

	/**
	* Sets the description of this budget participatif.
	*
	* @param description the description of this budget participatif
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_budgetParticipatif.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_budgetParticipatif.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_budgetParticipatif.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_budgetParticipatif.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the files IDs of this budget participatif.
	*
	* @param filesIds the files IDs of this budget participatif
	*/
	@Override
	public void setFilesIds(java.lang.String filesIds) {
		_budgetParticipatif.setFilesIds(filesIds);
	}

	/**
	* Sets the group ID of this budget participatif.
	*
	* @param groupId the group ID of this budget participatif
	*/
	@Override
	public void setGroupId(long groupId) {
		_budgetParticipatif.setGroupId(groupId);
	}

	/**
	* Sets whether this budget participatif is has copyright.
	*
	* @param hasCopyright the has copyright of this budget participatif
	*/
	@Override
	public void setHasCopyright(boolean hasCopyright) {
		_budgetParticipatif.setHasCopyright(hasCopyright);
	}

	/**
	* Sets the image ID of this budget participatif.
	*
	* @param imageId the image ID of this budget participatif
	*/
	@Override
	public void setImageId(long imageId) {
		_budgetParticipatif.setImageId(imageId);
	}

	/**
	* Sets whether this budget participatif is is crush.
	*
	* @param isCrush the is crush of this budget participatif
	*/
	@Override
	public void setIsCrush(boolean isCrush) {
		_budgetParticipatif.setIsCrush(isCrush);
	}

	/**
	* Sets the modified date of this budget participatif.
	*
	* @param modifiedDate the modified date of this budget participatif
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_budgetParticipatif.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the motif of this budget participatif.
	*
	* @param motif the motif of this budget participatif
	*/
	@Override
	public void setMotif(java.lang.String motif) {
		_budgetParticipatif.setMotif(motif);
	}

	@Override
	public void setNew(boolean n) {
		_budgetParticipatif.setNew(n);
	}

	/**
	* Sets the place text area of this budget participatif.
	*
	* @param placeTextArea the place text area of this budget participatif
	*/
	@Override
	public void setPlaceTextArea(java.lang.String placeTextArea) {
		_budgetParticipatif.setPlaceTextArea(placeTextArea);
	}

	/**
	* Sets the primary key of this budget participatif.
	*
	* @param primaryKey the primary key of this budget participatif
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_budgetParticipatif.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_budgetParticipatif.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik ID of this budget participatif.
	*
	* @param publikId the publik ID of this budget participatif
	*/
	@Override
	public void setPublikId(java.lang.String publikId) {
		_budgetParticipatif.setPublikId(publikId);
	}

	/**
	* Sets the status of this budget participatif.
	*
	* @param status the status of this budget participatif
	*/
	@Override
	public void setStatus(int status) {
		_budgetParticipatif.setStatus(status);
	}

	/**
	* Sets the status by user ID of this budget participatif.
	*
	* @param statusByUserId the status by user ID of this budget participatif
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_budgetParticipatif.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this budget participatif.
	*
	* @param statusByUserName the status by user name of this budget participatif
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_budgetParticipatif.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this budget participatif.
	*
	* @param statusByUserUuid the status by user uuid of this budget participatif
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_budgetParticipatif.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this budget participatif.
	*
	* @param statusDate the status date of this budget participatif
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_budgetParticipatif.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this budget participatif.
	*
	* @param title the title of this budget participatif
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_budgetParticipatif.setTitle(title);
	}

	/**
	* Sets the user ID of this budget participatif.
	*
	* @param userId the user ID of this budget participatif
	*/
	@Override
	public void setUserId(long userId) {
		_budgetParticipatif.setUserId(userId);
	}

	/**
	* Sets the user name of this budget participatif.
	*
	* @param userName the user name of this budget participatif
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_budgetParticipatif.setUserName(userName);
	}

	/**
	* Sets the user uuid of this budget participatif.
	*
	* @param userUuid the user uuid of this budget participatif
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_budgetParticipatif.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this budget participatif.
	*
	* @param uuid the uuid of this budget participatif
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_budgetParticipatif.setUuid(uuid);
	}

	/**
	* Sets the video url of this budget participatif.
	*
	* @param videoUrl the video url of this budget participatif
	*/
	@Override
	public void setVideoUrl(java.lang.String videoUrl) {
		_budgetParticipatif.setVideoUrl(videoUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BudgetParticipatifWrapper)) {
			return false;
		}

		BudgetParticipatifWrapper budgetParticipatifWrapper = (BudgetParticipatifWrapper)obj;

		if (Objects.equals(_budgetParticipatif,
					budgetParticipatifWrapper._budgetParticipatif)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _budgetParticipatif.getStagedModelType();
	}

	@Override
	public BudgetParticipatif getWrappedModel() {
		return _budgetParticipatif;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _budgetParticipatif.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _budgetParticipatif.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_budgetParticipatif.resetOriginalValues();
	}

	private final BudgetParticipatif _budgetParticipatif;
}