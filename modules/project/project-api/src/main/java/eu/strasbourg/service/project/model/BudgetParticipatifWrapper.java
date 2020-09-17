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
public class BudgetParticipatifWrapper
	implements BudgetParticipatif, ModelWrapper<BudgetParticipatif> {

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
		attributes.put("summary", getSummary());
		attributes.put("budget", getBudget());
		attributes.put("motif", getMotif());
		attributes.put("placeTextArea", getPlaceTextArea());
		attributes.put("inTheNameOf", getInTheNameOf());
		attributes.put("citoyenLastname", getCitoyenLastname());
		attributes.put("citoyenFirstname", getCitoyenFirstname());
		attributes.put("citoyenAdresse", getCitoyenAdresse());
		attributes.put("citoyenPostalCode", getCitoyenPostalCode());
		attributes.put("citoyenCity", getCitoyenCity());
		attributes.put("citoyenPhone", getCitoyenPhone());
		attributes.put("citoyenMobile", getCitoyenMobile());
		attributes.put("citoyenEmail", getCitoyenEmail());
		attributes.put("citoyenBirthday", getCitoyenBirthday());
		attributes.put("hasCopyright", isHasCopyright());
		attributes.put("videoUrl", getVideoUrl());
		attributes.put("imageTimeline", getImageTimeline());
		attributes.put("opacityImage", getOpacityImage());
		attributes.put("isCrush", isIsCrush());
		attributes.put("crushComment", getCrushComment());
		attributes.put("publikId", getPublikId());
		attributes.put("imageId", getImageId());
		attributes.put("filesIds", getFilesIds());
		attributes.put("budgetPhaseId", getBudgetPhaseId());
		attributes.put("parentId", getParentId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long budgetParticipatifId = (Long)attributes.get(
			"budgetParticipatifId");

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

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
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

		String inTheNameOf = (String)attributes.get("inTheNameOf");

		if (inTheNameOf != null) {
			setInTheNameOf(inTheNameOf);
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

		Long imageTimeline = (Long)attributes.get("imageTimeline");

		if (imageTimeline != null) {
			setImageTimeline(imageTimeline);
		}

		Double opacityImage = (Double)attributes.get("opacityImage");

		if (opacityImage != null) {
			setOpacityImage(opacityImage);
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

		Long parentId = (Long)attributes.get("parentId");

		if (parentId != null) {
			setParentId(parentId);
		}
	}

	@Override
	public Object clone() {
		return new BudgetParticipatifWrapper(
			(BudgetParticipatif)_budgetParticipatif.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.BudgetParticipatif
			budgetParticipatif) {

		return _budgetParticipatif.compareTo(budgetParticipatif);
	}

	/**
	 * Retourne les commentaires de l'entité
	 */
	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Comment>
		getApprovedComments() {

		return _budgetParticipatif.getApprovedComments();
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _budgetParticipatif.getAssetEntry();
	}

	/**
	 * Retourne le nom de l'autheur sous forme "Truc M."
	 */
	@Override
	public String getAuthor() {
		return _budgetParticipatif.getAuthor();
	}

	/**
	 * Retourne l'URL de l'image de l'utilisateur
	 */
	@Override
	public String getAuthorImageURL() {
		return _budgetParticipatif.getAuthorImageURL();
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
	public String getBPbuttonMessageState(
		javax.servlet.http.HttpServletRequest request) {

		return _budgetParticipatif.getBPbuttonMessageState(request);
	}

	@Override
	public String getBPMessageState(
		javax.servlet.http.HttpServletRequest request) {

		return _budgetParticipatif.getBPMessageState(request);
	}

	@Override
	public int getBPState() {
		return _budgetParticipatif.getBPState();
	}

	/**
	 * Returns the budget of this budget participatif.
	 *
	 * @return the budget of this budget participatif
	 */
	@Override
	public String getBudget() {
		return _budgetParticipatif.getBudget();
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
	 * Retourne le statut (Enumeration) du budget participatif
	 */
	@Override
	public eu.strasbourg.service.project.constants.ParticiperCategories
		getBudgetParticipatifStatus() {

		return _budgetParticipatif.getBudgetParticipatifStatus();
	}

	/**
	 * Retourne la catégorie 'Statut BP' du budget participatif
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory
		getBudgetParticipatifStatusCategory() {

		return _budgetParticipatif.getBudgetParticipatifStatusCategory();
	}

	@Override
	public String getBudgetParticipatifStatusCategoryColor() {
		return _budgetParticipatif.getBudgetParticipatifStatusCategoryColor();
	}

	@Override
	public String getBudgetParticipatifStatusTitle(java.util.Locale locale) {
		return _budgetParticipatif.getBudgetParticipatifStatusTitle(locale);
	}

	/**
	 * Retourne la liste des entrées timelines du projet
	 */
	@Override
	public java.util.List<ProjectTimeline> getBudgetParticipatifTimelines() {
		return _budgetParticipatif.getBudgetParticipatifTimelines();
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
	 * retourne les catégories
	 *
	 * @return
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return _budgetParticipatif.getCategories();
	}

	/**
	 * Retourne X suggestions max pour un BP
	 *
	 * @return la liste de bp.
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.project.model.BudgetParticipatif> getChilds() {

		return _budgetParticipatif.getChilds();
	}

	/**
	 * Returns the citoyen adresse of this budget participatif.
	 *
	 * @return the citoyen adresse of this budget participatif
	 */
	@Override
	public String getCitoyenAdresse() {
		return _budgetParticipatif.getCitoyenAdresse();
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
	 * Returns the citoyen city of this budget participatif.
	 *
	 * @return the citoyen city of this budget participatif
	 */
	@Override
	public String getCitoyenCity() {
		return _budgetParticipatif.getCitoyenCity();
	}

	/**
	 * Returns the citoyen email of this budget participatif.
	 *
	 * @return the citoyen email of this budget participatif
	 */
	@Override
	public String getCitoyenEmail() {
		return _budgetParticipatif.getCitoyenEmail();
	}

	/**
	 * Returns the citoyen firstname of this budget participatif.
	 *
	 * @return the citoyen firstname of this budget participatif
	 */
	@Override
	public String getCitoyenFirstname() {
		return _budgetParticipatif.getCitoyenFirstname();
	}

	/**
	 * Returns the citoyen lastname of this budget participatif.
	 *
	 * @return the citoyen lastname of this budget participatif
	 */
	@Override
	public String getCitoyenLastname() {
		return _budgetParticipatif.getCitoyenLastname();
	}

	/**
	 * Returns the citoyen mobile of this budget participatif.
	 *
	 * @return the citoyen mobile of this budget participatif
	 */
	@Override
	public String getCitoyenMobile() {
		return _budgetParticipatif.getCitoyenMobile();
	}

	/**
	 * Returns the citoyen phone of this budget participatif.
	 *
	 * @return the citoyen phone of this budget participatif
	 */
	@Override
	public String getCitoyenPhone() {
		return _budgetParticipatif.getCitoyenPhone();
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
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux communes du bp
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCityCategories() {

		return _budgetParticipatif.getCityCategories();
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
	 * Returns the create date of this budget participatif.
	 *
	 * @return the create date of this budget participatif
	 */
	@Override
	public Date getCreateDate() {
		return _budgetParticipatif.getCreateDate();
	}

	/**
	 * Returns the crush comment of this budget participatif.
	 *
	 * @return the crush comment of this budget participatif
	 */
	@Override
	public String getCrushComment() {
		return _budgetParticipatif.getCrushComment();
	}

	/**
	 * Returns the description of this budget participatif.
	 *
	 * @return the description of this budget participatif
	 */
	@Override
	public String getDescription() {
		return _budgetParticipatif.getDescription();
	}

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers du bp
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getDistrictCategories() {

		return _budgetParticipatif.getDistrictCategories();
	}

	/**
	 * Retourne une chaine des 'Territoires' correspondant aux quartiers du bp
	 *
	 * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	 */
	@Override
	public String getDistrictLabel(java.util.Locale locale) {
		return _budgetParticipatif.getDistrictLabel(locale);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _budgetParticipatif.getExpandoBridge();
	}

	/**
	 * Returns the files IDs of this budget participatif.
	 *
	 * @return the files IDs of this budget participatif
	 */
	@Override
	public String getFilesIds() {
		return _budgetParticipatif.getFilesIds();
	}

	/**
	 * Retourne la liste des URLs des documents
	 */
	@Override
	public java.util.List<String> getFilesURLs() {
		return _budgetParticipatif.getFilesURLs();
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
	 * Returns the has copyright of this budget participatif.
	 *
	 * @return the has copyright of this budget participatif
	 */
	@Override
	public boolean getHasCopyright() {
		return _budgetParticipatif.getHasCopyright();
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
	 * Returns the image timeline of this budget participatif.
	 *
	 * @return the image timeline of this budget participatif
	 */
	@Override
	public long getImageTimeline() {
		return _budgetParticipatif.getImageTimeline();
	}

	/**
	 * Retourne l'URL de l'image de la timeline à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageTimelineURL() {
		return _budgetParticipatif.getImageTimelineURL();
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return _budgetParticipatif.getImageURL();
	}

	/**
	 * Returns the in the name of of this budget participatif.
	 *
	 * @return the in the name of of this budget participatif
	 */
	@Override
	public String getInTheNameOf() {
		return _budgetParticipatif.getInTheNameOf();
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
	 * Returns the modified date of this budget participatif.
	 *
	 * @return the modified date of this budget participatif
	 */
	@Override
	public Date getModifiedDate() {
		return _budgetParticipatif.getModifiedDate();
	}

	/**
	 * Returns the motif of this budget participatif.
	 *
	 * @return the motif of this budget participatif
	 */
	@Override
	public String getMotif() {
		return _budgetParticipatif.getMotif();
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
	public int getNbSupportOfUser(String publikUserId) {
		return _budgetParticipatif.getNbSupportOfUser(publikUserId);
	}

	/**
	 * Retourne le nombre de soutiens d'un utilisateur pour la phase en cours, qu'importe le projet
	 */
	@Override
	public int getNbSupportOfUserInActivePhase(String publikUserId) {
		return _budgetParticipatif.getNbSupportOfUserInActivePhase(
			publikUserId);
	}

	/**
	 * Retourne le nombre de soutien
	 */
	@Override
	public long getNbSupports() {
		return _budgetParticipatif.getNbSupports();
	}

	/**
	 * Retourne le nombre de soutien sous le format 6 digits pour l'affichage
	 *
	 * @return le nombre sous le format '000124'
	 */
	@Override
	public String getNbSupportsBoard() {
		return _budgetParticipatif.getNbSupportsBoard();
	}

	/**
	 * Returns the opacity image of this budget participatif.
	 *
	 * @return the opacity image of this budget participatif
	 */
	@Override
	public double getOpacityImage() {
		return _budgetParticipatif.getOpacityImage();
	}

	/**
	 * Retourne Le budget participatif parent dans le cas d'un bp fusionne
	 *
	 * @return Le BP parent
	 */
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif getParent() {
		return _budgetParticipatif.getParent();
	}

	/**
	 * Returns the parent ID of this budget participatif.
	 *
	 * @return the parent ID of this budget participatif
	 */
	@Override
	public long getParentId() {
		return _budgetParticipatif.getParentId();
	}

	@Override
	public BudgetPhase getPhase() {
		return _budgetParticipatif.getPhase();
	}

	/**
	 * Retourne la catégorie 'Phase du budget participatif' du budget participatif
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getPhaseCategory() {
		return _budgetParticipatif.getPhaseCategory();
	}

	@Override
	public String getPhaseTitleLabel() {
		return _budgetParticipatif.getPhaseTitleLabel();
	}

	/**
	 * Returns the place text area of this budget participatif.
	 *
	 * @return the place text area of this budget participatif
	 */
	@Override
	public String getPlaceTextArea() {
		return _budgetParticipatif.getPlaceTextArea();
	}

	/**
	 * Retourne la liste des lieux placit liés
	 */
	@Override
	public java.util.List<PlacitPlace> getPlacitPlaces() {
		return _budgetParticipatif.getPlacitPlaces();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _budgetParticipatif.getPrimaryKeyObj();
	}

	@Override
	public int getPriorityOrder() {
		return _budgetParticipatif.getPriorityOrder();
	}

	/**
	 * Retourne la categorie projet du BP
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory() {
		return _budgetParticipatif.getProjectCategory();
	}

	/**
	 * Retourne la titre du projet du BP
	 */
	@Override
	public String getProjectName() {
		return _budgetParticipatif.getProjectName();
	}

	@Override
	public String getPublicationDateFr() {
		return _budgetParticipatif.getPublicationDateFr();
	}

	/**
	 * Returns the publik ID of this budget participatif.
	 *
	 * @return the publik ID of this budget participatif
	 */
	@Override
	public String getPublikId() {
		return _budgetParticipatif.getPublikId();
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
	 * Returns the status by user name of this budget participatif.
	 *
	 * @return the status by user name of this budget participatif
	 */
	@Override
	public String getStatusByUserName() {
		return _budgetParticipatif.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this budget participatif.
	 *
	 * @return the status by user uuid of this budget participatif
	 */
	@Override
	public String getStatusByUserUuid() {
		return _budgetParticipatif.getStatusByUserUuid();
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
	 * Retourne X suggestions max pour un BP
	 *
	 * @param request la requete
	 * @param nbSuggestions le nombre de suggestions.
	 * @return la liste de bp.
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.project.model.BudgetParticipatif> getSuggestions(
				javax.servlet.http.HttpServletRequest request,
				int nbSuggestions)
			throws com.liferay.portal.kernel.exception.PortalException,
				   com.liferay.portal.kernel.search.SearchException {

		return _budgetParticipatif.getSuggestions(request, nbSuggestions);
	}

	/**
	 * Returns the summary of this budget participatif.
	 *
	 * @return the summary of this budget participatif
	 */
	@Override
	public String getSummary() {
		return _budgetParticipatif.getSummary();
	}

	/**
	 * Retourne les soutiens du budget participatif
	 *
	 * @return Liste des soutiens
	 */
	@Override
	public java.util.List<BudgetSupport> getSupports() {
		return _budgetParticipatif.getSupports();
	}

	/**
	 * Retourne les catégories 'Territoire' correspondant aux pays du budget
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTerritoryCategories() {

		return _budgetParticipatif.getTerritoryCategories();
	}

	/**
	 * Retourne les thematiques du budget participatif (
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getThematicCategories() {

		return _budgetParticipatif.getThematicCategories();
	}

	/**
	 * Retourne la catégorie 'Thematic' du budget participatif. Si plusieurs, retourne la première de la liste
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getThematicCategory() {
		return _budgetParticipatif.getThematicCategory();
	}

	/**
	 * Retourne une chaine des 'Thematics' sépararée d'un '-'
	 */
	@Override
	public String getThematicsLabel(java.util.Locale locale) {
		return _budgetParticipatif.getThematicsLabel(locale);
	}

	/**
	 * Returns the title of this budget participatif.
	 *
	 * @return the title of this budget participatif
	 */
	@Override
	public String getTitle() {
		return _budgetParticipatif.getTitle();
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

	/**
	 * Returns the user name of this budget participatif.
	 *
	 * @return the user name of this budget participatif
	 */
	@Override
	public String getUserName() {
		return _budgetParticipatif.getUserName();
	}

	/**
	 * Returns the user uuid of this budget participatif.
	 *
	 * @return the user uuid of this budget participatif
	 */
	@Override
	public String getUserUuid() {
		return _budgetParticipatif.getUserUuid();
	}

	/**
	 * Returns the uuid of this budget participatif.
	 *
	 * @return the uuid of this budget participatif
	 */
	@Override
	public String getUuid() {
		return _budgetParticipatif.getUuid();
	}

	/**
	 * Returns the video url of this budget participatif.
	 *
	 * @return the video url of this budget participatif
	 */
	@Override
	public String getVideoUrl() {
		return _budgetParticipatif.getVideoUrl();
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

	@Override
	public int hashCode() {
		return _budgetParticipatif.hashCode();
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

	/**
	 * Peut être modifié
	 */
	@Override
	public boolean isEditable() {
		return _budgetParticipatif.isEditable();
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
	 * Non faisable si le statut est : Non Recevable, Non faisable, Non retenu, Annulé, Suspendu, fusionné
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
		eu.strasbourg.service.project.model.BudgetParticipatif
			budgetParticipatif,
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
	public void setBudget(String budget) {
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
	public void setCitoyenAdresse(String citoyenAdresse) {
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
	public void setCitoyenCity(String citoyenCity) {
		_budgetParticipatif.setCitoyenCity(citoyenCity);
	}

	/**
	 * Sets the citoyen email of this budget participatif.
	 *
	 * @param citoyenEmail the citoyen email of this budget participatif
	 */
	@Override
	public void setCitoyenEmail(String citoyenEmail) {
		_budgetParticipatif.setCitoyenEmail(citoyenEmail);
	}

	/**
	 * Sets the citoyen firstname of this budget participatif.
	 *
	 * @param citoyenFirstname the citoyen firstname of this budget participatif
	 */
	@Override
	public void setCitoyenFirstname(String citoyenFirstname) {
		_budgetParticipatif.setCitoyenFirstname(citoyenFirstname);
	}

	/**
	 * Sets the citoyen lastname of this budget participatif.
	 *
	 * @param citoyenLastname the citoyen lastname of this budget participatif
	 */
	@Override
	public void setCitoyenLastname(String citoyenLastname) {
		_budgetParticipatif.setCitoyenLastname(citoyenLastname);
	}

	/**
	 * Sets the citoyen mobile of this budget participatif.
	 *
	 * @param citoyenMobile the citoyen mobile of this budget participatif
	 */
	@Override
	public void setCitoyenMobile(String citoyenMobile) {
		_budgetParticipatif.setCitoyenMobile(citoyenMobile);
	}

	/**
	 * Sets the citoyen phone of this budget participatif.
	 *
	 * @param citoyenPhone the citoyen phone of this budget participatif
	 */
	@Override
	public void setCitoyenPhone(String citoyenPhone) {
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
	public void setCrushComment(String crushComment) {
		_budgetParticipatif.setCrushComment(crushComment);
	}

	/**
	 * Sets the description of this budget participatif.
	 *
	 * @param description the description of this budget participatif
	 */
	@Override
	public void setDescription(String description) {
		_budgetParticipatif.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_budgetParticipatif.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_budgetParticipatif.setExpandoBridgeAttributes(expandoBridge);
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
	public void setFilesIds(String filesIds) {
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
	 * Sets the image timeline of this budget participatif.
	 *
	 * @param imageTimeline the image timeline of this budget participatif
	 */
	@Override
	public void setImageTimeline(long imageTimeline) {
		_budgetParticipatif.setImageTimeline(imageTimeline);
	}

	/**
	 * Sets the in the name of of this budget participatif.
	 *
	 * @param inTheNameOf the in the name of of this budget participatif
	 */
	@Override
	public void setInTheNameOf(String inTheNameOf) {
		_budgetParticipatif.setInTheNameOf(inTheNameOf);
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
	public void setMotif(String motif) {
		_budgetParticipatif.setMotif(motif);
	}

	@Override
	public void setNew(boolean n) {
		_budgetParticipatif.setNew(n);
	}

	/**
	 * Sets the opacity image of this budget participatif.
	 *
	 * @param opacityImage the opacity image of this budget participatif
	 */
	@Override
	public void setOpacityImage(double opacityImage) {
		_budgetParticipatif.setOpacityImage(opacityImage);
	}

	/**
	 * Sets the parent ID of this budget participatif.
	 *
	 * @param parentId the parent ID of this budget participatif
	 */
	@Override
	public void setParentId(long parentId) {
		_budgetParticipatif.setParentId(parentId);
	}

	/**
	 * Sets the place text area of this budget participatif.
	 *
	 * @param placeTextArea the place text area of this budget participatif
	 */
	@Override
	public void setPlaceTextArea(String placeTextArea) {
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
	public void setPublikId(String publikId) {
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
	public void setStatusByUserName(String statusByUserName) {
		_budgetParticipatif.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this budget participatif.
	 *
	 * @param statusByUserUuid the status by user uuid of this budget participatif
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
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
	 * Sets the summary of this budget participatif.
	 *
	 * @param summary the summary of this budget participatif
	 */
	@Override
	public void setSummary(String summary) {
		_budgetParticipatif.setSummary(summary);
	}

	/**
	 * Sets the title of this budget participatif.
	 *
	 * @param title the title of this budget participatif
	 */
	@Override
	public void setTitle(String title) {
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
	public void setUserName(String userName) {
		_budgetParticipatif.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this budget participatif.
	 *
	 * @param userUuid the user uuid of this budget participatif
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_budgetParticipatif.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this budget participatif.
	 *
	 * @param uuid the uuid of this budget participatif
	 */
	@Override
	public void setUuid(String uuid) {
		_budgetParticipatif.setUuid(uuid);
	}

	/**
	 * Sets the video url of this budget participatif.
	 *
	 * @param videoUrl the video url of this budget participatif
	 */
	@Override
	public void setVideoUrl(String videoUrl) {
		_budgetParticipatif.setVideoUrl(videoUrl);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.project.model.BudgetParticipatif>
			toCacheModel() {

		return _budgetParticipatif.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif
		toEscapedModel() {

		return new BudgetParticipatifWrapper(
			_budgetParticipatif.toEscapedModel());
	}

	/**
	 * Retourne la version JSON de l'entité
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON(
		String publikUserId) {

		return _budgetParticipatif.toJSON(publikUserId);
	}

	@Override
	public String toString() {
		return _budgetParticipatif.toString();
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif
		toUnescapedModel() {

		return new BudgetParticipatifWrapper(
			_budgetParticipatif.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _budgetParticipatif.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BudgetParticipatifWrapper)) {
			return false;
		}

		BudgetParticipatifWrapper budgetParticipatifWrapper =
			(BudgetParticipatifWrapper)obj;

		if (Objects.equals(
				_budgetParticipatif,
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