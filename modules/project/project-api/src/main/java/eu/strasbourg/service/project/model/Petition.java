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
 * The extended model interface for the Petition service. Represents a row in the &quot;project_Petition&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see PetitionModel
 * @see eu.strasbourg.service.project.model.impl.PetitionImpl
 * @see eu.strasbourg.service.project.model.impl.PetitionModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.PetitionImpl")
@ProviderType
public interface Petition extends PetitionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.PetitionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Petition, Long> PETITION_ID_ACCESSOR = new Accessor<Petition, Long>() {
			@Override
			public Long get(Petition petition) {
				return petition.getPetitionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Petition> getTypeClass() {
				return Petition.class;
			}
		};

	/**
	* Retourne les catégories 'Territoire' correspondant aux pays de la petition
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritoryCategories();

	/**
	* Retourne les catégories 'Projets' correspondant aux pays de la petition
	*/
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategories();

	public java.lang.String getProjectTitle(java.util.Locale locale);

	/**
	* Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la petition
	*
	* @return : null si vide, sinon la liste des catégories
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistrictCategories();

	/**
	* méthode permettant de récuperer les faux signataires d'une pétitions.
	*
	* @return les faux signataires.
	*/
	public int getCountFakeSignataire();

	/**
	* méthode permettant de récupérer le pourcentage de signatures obtenu.
	*
	* @return le pourcentage en long.
	*/
	public double getPourcentageSignature();

	/**
	* Méthode permettant de retourner le nombre de signataire de la pétition
	*
	* @return le nombre.
	*/
	public long getNombreSignature();

	/**
	* méthode permettant d'afficher le nombre de signature.
	*
	* @return le nombre avec des zeros devant.
	*/
	public java.lang.String getNombreSignatureBoard();

	/**
	* Retourne une chaine des 'Territoires' correspondant aux quartiers de la petition
	*
	* @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	*/
	public java.lang.String getDistrictLabel(java.util.Locale locale);

	public java.lang.String getThematicLabel(java.util.Locale locale);

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne le nombre de likes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	public int getNbLikes();

	/**
	* Retourne le nombre de dislikes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	public int getNbDislikes();

	/**
	* Peut apporter une reaction (commenter, liker, participer) a l'entite
	*/
	public boolean isJudgeable();

	/**
	* Calcul la différence de jours entre la date du jour et celle d'expiration
	*/
	public int getTodayExpirationDifferenceDays();

	/**
	* Retourne la liste des URLs des documents
	*/
	public java.util.List<java.lang.String> getFilesURLs();

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne 3 suggestions max pour un thème appartenant à la vidéo en cours
	*
	* @param locale la locale de la région
	* @return la liste de pétition.
	*/
	public java.util.List<eu.strasbourg.service.project.model.Petition> getSuggestions(
		java.util.Locale locale);

	/**
	* Retourne X suggestions max pour un thème appartenant à la vidéo en cours
	*
	* @param locale        la locale de la région
	* @param nbSuggestions le nombre de suggestions.
	* @return la liste de pétition.
	*/
	public java.util.List<eu.strasbourg.service.project.model.Petition> getSuggestions(
		java.util.Locale locale, int nbSuggestions);

	/**
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);

	/**
	* Retourne le label de 5 digits du nombre de commentaires de l'entité
	*/
	public java.lang.String getNbApprovedCommentsLabel();

	/**
	* Retourne le nombre de commentaires de l'entité
	*/
	public int getNbApprovedComments();

	/**
	* Retourne les commentaires de l'entité
	*/
	public java.util.List<eu.strasbourg.service.comment.model.Comment> getApprovedComments();

	/**
	* Retourne les thematiques de la petition (
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThematicCategories();

	/**
	* Retourne le projet de la petition (
	*/
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory();

	public java.lang.String getAssetEntryTitle();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	public java.util.List<eu.strasbourg.service.project.model.Signataire> getSignataires();

	/**
	* Retourne le status de la petition
	*/
	public com.liferay.asset.kernel.model.AssetCategory getPetitionStatusCategory();

	/**
	* méthode permettant de récuperer le nombre de signataire nécessaire pour finir la pétition.
	*
	* @return le nombre
	*/
	public long getSignataireNeeded();

	/**
	* méthode de récupération du status
	*
	* @return le status.
	*/
	public java.lang.String getPetitionStatus();

	/**
	* méthode de récupération du status
	*
	* @return le status.
	*/
	public java.lang.String getFrontStatusFR();

	/**
	* méthode d'affichage des information du status pour excel.
	*
	* @return le status.
	*/
	public java.lang.String getPetitionStatusExcel();

	/**
	* méthode de récupération du status
	*
	* @return le status.
	*/
	public java.lang.String getProDureeFR();

	/**
	* Retourne la liste des lieux placit liés à la petition
	*/
	public java.util.List<eu.strasbourg.service.project.model.PlacitPlace> getPlacitPlaces();

	/**
	* Demande si l'utilisateur demandé a signe la petition
	*
	* @throws PortletException
	*/
	public boolean hasUserSigned(java.lang.String publikUserId)
		throws javax.portlet.PortletException;

	public java.lang.String getPublicationDateFr();

	/**
	* Retourne la version JSON de l'entité
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON(
		java.lang.String publikUserId);
}