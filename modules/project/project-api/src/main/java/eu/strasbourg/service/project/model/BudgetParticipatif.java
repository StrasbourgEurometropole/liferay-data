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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the BudgetParticipatif service. Represents a row in the &quot;project_BudgetParticipatif&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see BudgetParticipatifModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.project.model.impl.BudgetParticipatifImpl"
)
@ProviderType
public interface BudgetParticipatif
	extends BudgetParticipatifModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.project.model.impl.BudgetParticipatifImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BudgetParticipatif, Long>
		BUDGET_PARTICIPATIF_ID_ACCESSOR =
			new Accessor<BudgetParticipatif, Long>() {

				@Override
				public Long get(BudgetParticipatif budgetParticipatif) {
					return budgetParticipatif.getBudgetParticipatifId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<BudgetParticipatif> getTypeClass() {
					return BudgetParticipatif.class;
				}

			};

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	 * Retourne les thematiques du budget participatif (
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getThematicCategories();

	/**
	 * Retourne une chaine des 'Thematics' sépararée d'un '-'
	 */
	public String getThematicsLabel(java.util.Locale locale);

	/**
	 * Retourne la catégorie 'Thematic' du budget participatif. Si plusieurs, retourne la première de la liste
	 */
	public com.liferay.asset.kernel.model.AssetCategory getThematicCategory();

	/**
	 * Retourne les catégories 'Territoire' correspondant aux pays du budget
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTerritoryCategories();

	/**
	 * Retourne la catégorie 'Statut BP' du budget participatif
	 */
	public com.liferay.asset.kernel.model.AssetCategory
		getBudgetParticipatifStatusCategory();

	/**
	 * Retourne le statut (Enumeration) du budget participatif
	 */
	public eu.strasbourg.service.project.constants.ParticiperCategories
		getBudgetParticipatifStatus();

	public String getBudgetParticipatifStatusTitle(java.util.Locale locale);

	/**
	 * Retourne la catégorie 'Phase du budget participatif' du budget participatif
	 */
	public com.liferay.asset.kernel.model.AssetCategory getPhaseCategory();

	/**
	 * Retourne la liste des lieux placit liés
	 */
	public java.util.List<PlacitPlace> getPlacitPlaces();

	/**
	 * retourne les catégories
	 *
	 * @return
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories();

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	public String getImageURL();

	/**
	 * Retourne l'auteur en publik user
	 *
	 * @return
	 */
	public eu.strasbourg.service.oidc.model.PublikUser getAuthorPublikUser();

	/**
	 * Retourne l'URL de l'image de l'utilisateur
	 */
	public String getAuthorImageURL();

	/**
	 * Retourne une chaine des 'Territoires' correspondant aux quartiers du bp
	 *
	 * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	 */
	public String getDistrictLabel(java.util.Locale locale);

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers du bp
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getDistrictCategories();

	public String getBudgetParticipatifStatusCategoryColor();

	/**
	 * Retourne la categorie projet du BP
	 */
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory();

	/**
	 * Retourne la titre du projet du BP
	 */
	public String getProjectName();

	/**
	 * Retourne le nom de l'autheur sous forme "Truc M."
	 */
	public String getAuthor();

	/**
	 * A deja fait l'oeuvre d'un vote et/ou d'une decision administrative
	 */
	public boolean hasBeenVoted();

	/**
	 * Est en periode et capacite de vote
	 */
	public boolean isVotable();

	/**
	 * Peut être modifié
	 */
	public boolean isEditable();

	/**
	 * Non faisable si le statut est : Non Recevable, Non faisable, Non retenu, Annulé, Suspendu, fusionné
	 */
	public boolean isNotDoable();

	/**
	 * Le budget a-t-il ete evalue par l'administration ?
	 *
	 * @note : doit alors posseder l'un des statuts adequat
	 */
	public boolean hasBeenEvaluated();

	public int getPriorityOrder();

	public BudgetPhase getPhase();

	public String getPhaseTitleLabel();

	/**
	 * Retourne les commentaires de l'entité
	 */
	public java.util.List<eu.strasbourg.service.comment.model.Comment>
		getApprovedComments();

	/**
	 * Retourne le nombre de commentaires de l'entité
	 */
	public int getNbApprovedComments();

	/**
	 * Retourne les soutiens du budget participatif
	 *
	 * @return Liste des soutiens
	 */
	public java.util.List<BudgetSupport> getSupports();

	/**
	 * Retourne le nombre de soutien
	 */
	public long getNbSupports();

	/**
	 * Retourne le nombre de soutiens d'un utilisateur pour ce projet
	 */
	public int getNbSupportOfUser(String publikUserId);

	/**
	 * Retourne le nombre de soutiens d'un utilisateur pour la phase en cours, qu'importe le projet
	 */
	public int getNbSupportOfUserInActivePhase(String publikUserId);

	/**
	 * Retourne le nombre de soutien sous le format 6 digits pour l'affichage
	 *
	 * @return le nombre sous le format '000124'
	 */
	public String getNbSupportsBoard();

	public String getPublicationDateFr();

	/**
	 * Remplace le statut bp actuel par celui fournis en paramètre de la méthode
	 *
	 * @param budgetParticipatif
	 * @param status
	 */
	public void setBPStatus(
		BudgetParticipatif budgetParticipatif,
		eu.strasbourg.service.project.constants.ParticiperCategories status,
		long groupID);

	/**
	 * Retourne X suggestions max pour un BP
	 *
	 * @param request la requete
	 * @param nbSuggestions le nombre de suggestions.
	 * @return la liste de bp.
	 */
	public java.util.List<BudgetParticipatif> getSuggestions(
			javax.servlet.http.HttpServletRequest request, int nbSuggestions)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.search.SearchException;

	/**
	 * Retourne X suggestions max pour un BP
	 *
	 * @return la liste de bp.
	 */
	public java.util.List<BudgetParticipatif> getChilds();

	/**
	 * Retourne Le budget participatif parent dans le cas d'un bp fusionne
	 *
	 * @return Le BP parent
	 */
	public BudgetParticipatif getParent();

	/**
	 * Retourne la liste des URLs des documents
	 */
	public java.util.List<String> getFilesURLs();

	/**
	 * Retourne l'URL de l'image de la timeline à partir de l'id du DLFileEntry
	 */
	public String getImageTimelineURL();

	/**
	 * Retourne la liste des entrées timelines du projet
	 */
	public java.util.List<ProjectTimeline> getBudgetParticipatifTimelines();

	/**
	 * Retourne la version JSON de l'entité
	 */
	public com.liferay.portal.kernel.json.JSONObject toJSON(
		String publikUserId);

	public String getBPMessageState(
		javax.servlet.http.HttpServletRequest request);

	public String getBPbuttonMessageState(
		javax.servlet.http.HttpServletRequest request);

	public int getBPState();

}