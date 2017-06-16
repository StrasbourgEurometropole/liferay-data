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
 * The extended model interface for the Campaign service. Represents a row in the &quot;agenda_Campaign&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see CampaignModel
 * @see eu.strasbourg.service.agenda.model.impl.CampaignImpl
 * @see eu.strasbourg.service.agenda.model.impl.CampaignModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.CampaignImpl")
@ProviderType
public interface Campaign extends CampaignModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.CampaignImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Campaign, Long> CAMPAIGN_ID_ACCESSOR = new Accessor<Campaign, Long>() {
			@Override
			public Long get(Campaign campaign) {
				return campaign.getCampaignId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Campaign> getTypeClass() {
				return Campaign.class;
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
	* Retourne les themes de la campagne
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThemes();

	/**
	* Retourne true si l'utilisateur passé en paramètre est manager de la
	* campagne
	*/
	public boolean isManagedByUser(long userId);

	public java.util.List<com.liferay.portal.kernel.model.User> getManagers();

	/**
	* Retourne la liste des événements de la campagne
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> getEvents();

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getDefaultImageURL();

	/**
	* Génère l'export et place le fichier dans le dossier d'import des
	* événements
	*/
	public void export();

	/**
	* Génère l'export JSON
	*/
	public com.liferay.portal.kernel.json.JSONObject generateExport();
}