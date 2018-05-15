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
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne les type de la participation (
	*/
	public com.liferay.asset.kernel.model.AssetCategory getType();

	/**
	* Retourne les territoire du lieu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritories();

	/**
	* Retourne la catégorie Territoire correspondant à la ville du lieu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getCityCategory();

	/**
	* Retourne le quartier
	*/
	public java.lang.String getDistrict(java.util.Locale locale);

	/**
	* Retourne la catégorie Territoire correspondant au quartier du lieu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getDistrictCategory();

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

	public int getTodayPublicationDifferenceDays();

	public int getTodayExpirationDifferenceDays();
}