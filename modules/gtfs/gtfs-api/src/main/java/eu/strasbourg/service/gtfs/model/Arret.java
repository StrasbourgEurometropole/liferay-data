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

package eu.strasbourg.service.gtfs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Arret service. Represents a row in the &quot;gtfs_Arret&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see ArretModel
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.gtfs.model.impl.ArretImpl")
@ProviderType
public interface Arret extends ArretModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.gtfs.model.impl.ArretImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Arret, Long> ARRET_ID_ACCESSOR =
		new Accessor<Arret, Long>() {

			@Override
			public Long get(Arret arret) {
				return arret.getArretId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Arret> getTypeClass() {
				return Arret.class;
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
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories();

	/**
	 * Renvoie la liste des Directions de cet arret
	 */
	public java.util.List<eu.strasbourg.service.gtfs.model.Direction>
		getDirections();

	/**
	 * Renvoie la liste des Alertes de cet arret
	 */
	public java.util.List<eu.strasbourg.service.gtfs.model.Alert> getAlerts();

	/**
	 * Renvoie la liste des Alertes en cours ou à venir de cet arret
	 */
	public java.util.List<eu.strasbourg.service.gtfs.model.Alert>
		getAlertsActives();

	/**
	 * Renvoie le nombre des alertes en cours ou à venir de cet arret
	 */
	public long getCountAlertsActives();

	/**
	 * Renvoie la correspondance du type d'arret en format texte
	 */
	public String getTypeText();

	/**
	 * Renvoie les directions sans doublon de lignes shortName, [bgColor, textColor, destinationName]
	 */
	public java.util.Map<String, String[]> getDirectionsUniques();

	/**
	 * Renvoie les prochains passages
	 */
	public java.util.List<com.liferay.portal.kernel.json.JSONObject>
		getArretRealTime();

	/**
	 * Renvoie le JSON de l'entite au format GeoJSON pour la map
	 */
	public com.liferay.portal.kernel.json.JSONObject getGeoJSON(
		long groupId, java.util.Locale locale);

}