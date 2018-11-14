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
 * @see eu.strasbourg.service.project.model.impl.BudgetParticipatifImpl
 * @see eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.BudgetParticipatifImpl")
@ProviderType
public interface BudgetParticipatif extends BudgetParticipatifModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.BudgetParticipatifImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BudgetParticipatif, Long> BUDGET_PARTICIPATIF_ID_ACCESSOR =
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
	* Retourne les thematiques de la participation (
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThematicCategories();

	/**
	* Retourne une chaine des 'Thematics' sépararée d'un '-'
	*/
	public java.lang.String getThematicsLabel(java.util.Locale locale);

	/**
	* Retourne les catégories 'Territoire' correspondant aux pays du budget
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritoryCategories();

	/**
	* Retourne les catégories 'Statut BP' du budget participatif
	*/
	public com.liferay.asset.kernel.model.AssetCategory getBudgetParticipatifStatusCategory();

	public java.lang.String getBudgetParticipatifStatusTitle(
		java.util.Locale locale);

	/**
	* Retourne la liste des lieux placit liés
	*/
	public java.util.List<eu.strasbourg.service.project.model.PlacitPlace> getPlacitPlaces();

	/**
	* retourne les catégories
	*
	* @return
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne l'URL de l'image de l'utilisateur
	*/
	public java.lang.String getAuthorImageURL();

	/**
	* Retourne une chaine des 'Territoires' correspondant aux quartiers de la petition
	*
	* @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	*/
	public java.lang.String getDistrictLabel(java.util.Locale locale);

	/**
	* Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la petition
	*
	* @return : null si vide, sinon la liste des catégories
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistrictCategories();

	public java.lang.String getBudgetParticipatifStatusCategoryColor();

	/**
	* Retourne la categorie projet du BP
	*/
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory();

	/**
	* Retourne la titre du projet du BP
	*/
	public java.lang.String getProjectName();

	public java.lang.String getAuthor();

	/**
	* Peut apporter une reaction (commenter, liker, participer) a l'entite
	*/
	public boolean isJudgeable();

	/**
	* Est en periode et capacite de vote
	*/
	public boolean isVotable();

	/**
	* Non faisable si le statut est : Non Recevable, Non faisable, Non retenu, Annulé, Suspendu
	*/
	public boolean isNotDoable();

	public eu.strasbourg.service.project.model.BudgetPhase getPhase();

	public java.lang.String getPhaseTitleLabel();

	/**
	* Le budget a-t-il ete evalue par l'administration ?
	*
	* @note : doit alors posseder l'un des statuts adequat
	*/
	public boolean hasBeenEvaluated();

	/**
	* Retourne les commentaires de l'entité
	*/
	public java.util.List<eu.strasbourg.service.comment.model.Comment> getApprovedComments();

	/**
	* Retourne le nombre de commentaires de l'entité
	*/
	public int getNbApprovedComments();

	/**
	* Retourne les soutiens du budget participatif
	*
	* @return Liste des soutiens
	*/
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getSupports();

	/**
	* Retourne le nombre de soutien
	*/
	public long getNbSupports();

	/**
	* Retourne le nombre de soutiens d'un utilisateur pour ce projet
	*/
	public int getNbSupportOfUser(java.lang.String publikUserId);

	/**
	* Retourne le nombre de soutiens d'un utilisateur pour la phase en cours, qu'importe le projet
	*/
	public int getNbSupportOfUserInActivePhase(java.lang.String publikUserId);

	/**
	* Retourne le nombre de soutien sous le format 6 digits pour l'affichage
	*
	* @return le nombre sous le format '000124'
	*/
	public java.lang.String getNbSupportsBoard();

	public java.lang.String getPublicationDateFr();

	/**
	* Remplace le statut bp actuel par celui fournis en paramètre de la méthode
	*
	* @param budgetParticipatif
	* @param status
	*/
	public void setBPStatus(
		eu.strasbourg.service.project.model.BudgetParticipatif budgetParticipatif,
		eu.strasbourg.service.project.constants.ParticiperCategories status,
		long groupID);

	/**
	* Retourne la version JSON de l'entité
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON(
		java.lang.String publikUserId);
}