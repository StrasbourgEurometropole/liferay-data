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
 * The extended model interface for the Participation service. Represents a row in the &quot;project_Participation&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see ParticipationModel
 * @see eu.strasbourg.service.project.model.impl.ParticipationImpl
 * @see eu.strasbourg.service.project.model.impl.ParticipationModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.ParticipationImpl")
@ProviderType
public interface Participation extends ParticipationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.ParticipationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Participation, Long> PARTICIPATION_ID_ACCESSOR = new Accessor<Participation, Long>() {
			@Override
			public Long get(Participation participation) {
				return participation.getParticipationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Participation> getTypeClass() {
				return Participation.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des événements liés à la participation
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents();

	/**
	* Retourne la liste des lieux liés à la participation
	*/
	public java.util.List<eu.strasbourg.service.place.model.Place> getPlaces();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne le type de la participation (
	*/
	public com.liferay.asset.kernel.model.AssetCategory getTypeCategory();

	/**
	* Retourne le projet de la participation (
	*/
	public com.liferay.asset.kernel.model.AssetCategory getProjectCategory();

	/**
	* Retourne les thematiques de la participation (
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThematicCategories();

	/**
	* Retourne les catégories 'Territoire' correspondant aux pays de la participation
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritoryCategories();

	/**
	* Retourne les sous-catégories 'Territoire' correspondant aux villes de la participation
	*
	* @return : null si vide, sinon la liste des catégories
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCityCategories();

	/**
	* Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la participation
	*
	* @return : null si vide, sinon la liste des catégories
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistrictCategories();

	/**
	* Retourne une chaine des 'Territoires' correspondant aux quartiers de la participation
	*
	* @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	*/
	public java.lang.String getDistrictLabel(java.util.Locale locale);

	/**
	* Retourne le status de la participation
	*/
	public com.liferay.asset.kernel.model.AssetCategory getParticipationStatusCategory();

	/**
	* Retourne le status de la participation selon la temporalité actuelle
	*
	* @return le status suivant l'ordre :
	[soon_arrived] : date du jour antérieur à la date de publication
	[new] : 7 jour après la publication
	[in_progress] : toute la durée de la période de participation
	[soon_finished] : 7 jours avant l'expiration
	[finished] : date du jour postérieur à la date d'expiration
	*/
	public java.lang.String getParticipationStatus();

	/**
	* Calcul la différence de jours entre la date du jour et celle de publication
	*/
	public int getTodayPublicationDifferenceDays();

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
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);
}