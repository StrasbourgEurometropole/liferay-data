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

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the GoogleMyBusinessHistoric service. Represents a row in the &quot;place_GoogleMyBusinessHistoric&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see GoogleMyBusinessHistoricModel
 * @see eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricImpl
 * @see eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricImpl")
@ProviderType
public interface GoogleMyBusinessHistoric extends GoogleMyBusinessHistoricModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<GoogleMyBusinessHistoric, Long> GOOGLE_MY_BUSINESS_HISTORIC_ID_ACCESSOR =
		new Accessor<GoogleMyBusinessHistoric, Long>() {
			@Override
			public Long get(GoogleMyBusinessHistoric googleMyBusinessHistoric) {
				return googleMyBusinessHistoric.getGoogleMyBusinessHistoricId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<GoogleMyBusinessHistoric> getTypeClass() {
				return GoogleMyBusinessHistoric.class;
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
	* Renvoie le label affichable du resultat de google mybusiness
	*
	* @return
	*/
	public java.lang.String getResultLabel();

	/**
	* Ajout d'une ligne dans le resultat de google mybusiness
	*
	* @return
	*/
	public void addNewOperation(java.lang.String operation);

	/**
	* Envoi du mail d'envoi
	*/
	public void sendMail();
}