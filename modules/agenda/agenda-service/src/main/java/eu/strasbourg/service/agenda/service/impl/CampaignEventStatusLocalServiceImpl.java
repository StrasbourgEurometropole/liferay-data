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

package eu.strasbourg.service.agenda.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.service.base.CampaignEventStatusLocalServiceBaseImpl;

/**
 * The implementation of the campaign event status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventStatusLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.CampaignEventStatusLocalServiceUtil
 */
@ProviderType
public class CampaignEventStatusLocalServiceImpl
	extends CampaignEventStatusLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.CampaignEventStatusLocalServiceUtil} to access the campaign event status local service.
	 */
	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public CampaignEventStatus createCampaignEventStatus() throws PortalException {
		long pk = counterLocalService.increment();

		CampaignEventStatus campaignEventStatus = this.campaignEventStatusLocalService
			.createCampaignEventStatus(pk);
		
		campaignEventStatus.setCampaignEventId(0);
		campaignEventStatus.setPreviousStatusId(0);

		return campaignEventStatus;
	}

	/**
	 * Retourne les statuts d'un événement
	 */
	@Override
	public List<CampaignEventStatus> getByCampaignEvent(long campaignEventId) {
		return this.campaignEventStatusPersistence.findByCampaignEventId(campaignEventId);
	}
}