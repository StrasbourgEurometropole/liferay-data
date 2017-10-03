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
 * The extended model interface for the CampaignEvent service. Represents a row in the &quot;agenda_CampaignEvent&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see CampaignEventModel
 * @see eu.strasbourg.service.agenda.model.impl.CampaignEventImpl
 * @see eu.strasbourg.service.agenda.model.impl.CampaignEventModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.CampaignEventImpl")
@ProviderType
public interface CampaignEvent extends CampaignEventModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.CampaignEventImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CampaignEvent, Long> CAMPAIGN_EVENT_ID_ACCESSOR =
		new Accessor<CampaignEvent, Long>() {
			@Override
			public Long get(CampaignEvent campaignEvent) {
				return campaignEvent.getCampaignEventId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CampaignEvent> getTypeClass() {
				return CampaignEvent.class;
			}
		};

	public eu.strasbourg.service.agenda.model.Campaign getCampaign();

	/**
	* Retourne la liste de CampaignEventStatus, correspondant à l'historique
	* des statuts de l'événement classé par ordre chronologique
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEventStatus> getStatusHistory();

	/**
	* Renvoie le dernier statut en date de l'événement
	*/
	public eu.strasbourg.service.agenda.model.CampaignEventStatus getLastStatus();

	public eu.strasbourg.service.agenda.model.CampaignEventStatus updateStatus(
		int newStatus, java.lang.String comment,
		com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Retourne true si l'utilisateur est manager de la campagne à laquelle
	* appartient l'événement
	*/
	public boolean isUserManagerOfTheEvent(long userId);

	/**
	* Retourne les thèmes
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThemes();

	/**
	* Retourne le label des thèles de l'événement
	*/
	public java.lang.String getThemeLabel(java.util.Locale locale);

	/**
	* Retourne les types
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes();

	/**
	* Retourne le label des types de l'événement
	*/
	public java.lang.String getTypeLabel(java.util.Locale locale);

	/**
	* Retourne les publics
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getPublics();

	/**
	* Retourne le label des publics de l'événement
	*/
	public java.lang.String getPublicLabel(java.util.Locale locale);

	/**
	* Retourne le nom du service
	*/
	public java.lang.String getServiceName(java.util.Locale locale);

	public void sendDeleteMail()
		throws com.liferay.portal.kernel.exception.PortalException;

	public void sendDeletionDeniedMail()
		throws com.liferay.portal.kernel.exception.PortalException;

	public void sendStatusMail()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getWebImageURL();

	/**
	* Retourne l'objet "LegacyPlace" correspondant au lieu de l'événement, s'il
	* existe
	*/
	public eu.strasbourg.utils.models.LegacyPlace getLegacyPlace(
		java.util.Locale locale);

	/**
	* Retourne les périodes
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getPeriods();

	/**
	* Retourne le territoire (la ville) de l'événement (si lieu manuel)
	*/
	public java.lang.String getCity();

	/**
	* Retourne le label des manifestations de l'événement
	*/
	public java.lang.String getManifestationLabel(java.util.Locale locale);

	/**
	* Retourne la version JSON de l'object
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();

	/**
	* Retourne le nom lieu rattaché à l'événement
	*/
	public java.lang.String getPlaceAlias(java.util.Locale locale);
}