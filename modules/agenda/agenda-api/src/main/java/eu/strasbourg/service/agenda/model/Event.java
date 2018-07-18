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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Event service. Represents a row in the &quot;agenda_Event&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see EventModel
 * @see eu.strasbourg.service.agenda.model.impl.EventImpl
 * @see eu.strasbourg.service.agenda.model.impl.EventModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.EventImpl")
@ProviderType
public interface Event extends EventModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.EventImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Event, Long> EVENT_ID_ACCESSOR = new Accessor<Event, Long>() {
			@Override
			public Long get(Event event) {
				return event.getEventId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Event> getTypeClass() {
				return Event.class;
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
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);

	/**
	* Retourne la liste des manifestations auxquelles cette édition appartient
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations();

	/**
	* Retourne la liste des IDs des manifestations auxquelles cette édition
	* appartient sous forme de String
	*/
	public java.lang.String getManifestationsIds();

	/**
	* Retourne la liste des galeries publiées
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getPublishedManifestations();

	/**
	* Retourne la liste des périodes auxquelles l'événement à lieu (classées
	* par date de début croissante)
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getEventPeriods();

	/**
	* Retourne la liste des périodes courantes et futures auxuqlles l'événement
	* à lieu
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getCurrentAndFuturePeriods();

	/**
	* Retourne la date de début de la future ou courante période de l'événement
	*/
	public java.util.Date getStartDateFirstCurrentAndFuturePeriod();

	/**
	* Retourne la période principale de l'événement (de la première date de
	* début à la dernière date de fin) sous forme de String dans la locale
	* passée en paramètre
	*/
	public java.lang.String getEventScheduleDisplay(java.util.Locale locale);

	/**
	* Retourne la version live de l'édition, si elle existe
	*/
	public eu.strasbourg.service.agenda.model.Event getLiveVersion();

	/**
	* Retourne l'id du lieu de l'événement s'il existe, 0 sinon
	*/
	public long getPlaceId();

	/**
	* Retourne le nom de la ville, provenant du lieu interne s'il existe, du
	* lieu lié sinon
	*/
	public java.lang.String getCity(java.util.Locale locale);

	/**
	* Retourne le nom du lieu, provenant du lieu interne s'il existe, du lieu
	* lié sinon
	*/
	public java.lang.String getPlaceAlias(java.util.Locale locale);

	/**
	* Retourne l'adresse sans la ville
	*/
	public java.lang.String getPlaceAddress(java.util.Locale locale);

	/**
	* Retourne la ville
	*/
	public java.lang.String getPlaceCity(java.util.Locale locale);

	/**
	* Retourne les coordonnees mercator en axe X (longitude)
	*/
	public java.lang.String getMercatorX();

	/**
	* Retourne les coordonnees mercator en axe Y (latitude)
	*/
	public java.lang.String getMercatorY();

	/**
	* Retourne la liste des participations de l'evenement
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.EventParticipation> getEventParticipations();

	/**
	* Retourne le nombre de participation a l'evenement
	*/
	public int getNbEventParticipations();

	/**
	* Retourne le label de 5 digits du nombre de participation a l'evenement
	*/
	public java.lang.String getNbEventParticipationsLabel();

	/**
	* Retourne true si l'événement est accessible pour au moins un type de
	* handicap
	*/
	public boolean hasAnyAccessForDisabled();

	/**
	* Retourne l'adresse complète du lieu, provenant du lieu interne s'il
	* existe, du lieu lié sinon
	*/
	public java.lang.String getPlaceAddressHTML(java.util.Locale locale);

	/**
	* Retourne les types de l'événement
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes();

	/**
	* Retourne le label des types de l'événement
	*/
	public java.lang.String getTypeLabel(java.util.Locale locale);

	/**
	* Retourne les themes de l'événement
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThemes();

	/**
	* Retourne le label des thèmes de l'événement
	*/
	public java.lang.String getThemeLabel(java.util.Locale locale);

	/**
	* Retourne les publics de l'événement
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getPublics();

	/**
	* Retourne le label des publics de l'événement
	*/
	public java.lang.String getPublicLabel(java.util.Locale locale);

	/**
	* Retourne les territoires de l'événement
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritories();

	/**
	* Retourne le label des territoires de l'événement
	*/
	public java.lang.String getTerritoryLabel(java.util.Locale locale);

	/**
	* Retourne les territoires de l'événement
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getServices();

	public java.time.LocalDate getNextOpenDate();

	/**
	* Retourne la version JSON de l'événenement
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}