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
 * The extended model interface for the Manifestation service. Represents a row in the &quot;agenda_Manifestation&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see ManifestationModel
 * @see eu.strasbourg.service.agenda.model.impl.ManifestationImpl
 * @see eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.ManifestationImpl")
@ProviderType
public interface Manifestation extends ManifestationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.ManifestationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Manifestation, Long> MANIFESTATION_ID_ACCESSOR = new Accessor<Manifestation, Long>() {
			@Override
			public Long get(Manifestation manifestation) {
				return manifestation.getManifestationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Manifestation> getTypeClass() {
				return Manifestation.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattachée cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);

	/**
	* Renvoie la liste des éditions de la galerie
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents();

	/**
	* Renvoie la liste des ids des éditions de la galerie sous forme de String
	*/
	public java.lang.String getEventsIds();

	/**
	* Renvoie la liste des éditions publiées de la galerie
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getPublishedEvents();

	/**
	* Retourne la période principale de la manifestation sous forme de String
	* dans la locale passée en paramètre
	*/
	public java.lang.String getManifestationScheduleDisplay(
		java.util.Locale locale);

	/**
	* Renvoie la version live de la galerie d'édition, si elle existe
	*/
	public eu.strasbourg.service.agenda.model.Manifestation getLiveVersion();
}