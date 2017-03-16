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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.CampaignEventStatusLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil;
import eu.strasbourg.service.agenda.service.base.CampaignEventLocalServiceBaseImpl;

/**
 * The implementation of the campaignEvent local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.campaignEvent.service.CampaignEventLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventLocalServiceBaseImpl
 * @see eu.strasbourg.service.campaignEvent.service.CampaignEventLocalServiceUtil
 */
@ProviderType
public class CampaignEventLocalServiceImpl
	extends CampaignEventLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.campaignEvent.service.
	 * CampaignEventLocalServiceUtil} to access the campaignEvent local service.
	 */

	/**
	 * Crée une campaignEvent vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public CampaignEvent createCampaignEvent(ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		CampaignEvent campaignEvent = this.campaignEventLocalService
			.createCampaignEvent(pk);

		campaignEvent.setServiceId((long) 0);
		campaignEvent.setImageId((long) 0);
		campaignEvent.setWebImageId((long) 0);
		campaignEvent.setPlaceCityId((long) 0);
		campaignEvent.setThemeId((long) 0);
		campaignEvent.setCampaignId((long) 0);
		campaignEvent.setTypeId((long) 0);
		campaignEvent.setFree(-1);
		campaignEvent.setGroupId(sc.getScopeGroupId());
		campaignEvent.setUserName(user.getFullName());
		campaignEvent.setUserId(sc.getUserId());
		campaignEvent.setStatus(WorkflowConstants.STATUS_DRAFT);

		return campaignEvent;
	}

	/**
	 * Supprime une campaignEvent
	 */
	@Override
	public CampaignEvent removeCampaignEvent(long campaignEventId)
		throws PortalException {
		// Supprime le CampaignEvent
		CampaignEvent campaignEvent = campaignEventPersistence
			.remove(campaignEventId);

		// Supprime l'historique de statuts
		for (CampaignEventStatus status : campaignEvent.getStatusHistory()) {
			CampaignEventStatusLocalServiceUtil
				.deleteCampaignEventStatus(status);
		}

		// Supprime les périodes
		List<EventPeriod> periods = campaignEvent.getPeriods();
		for (EventPeriod period : periods) {
			EventPeriodLocalServiceUtil.deleteEventPeriod(period);
		}

		return campaignEvent;
	}

	/**
	 * Lance une recherche par mots-clés, thème et status
	 */
	@Override
	public List<CampaignEvent> findByKeywordThemeAndStatus(String keyword,
		long themeId, int status, long userId, long groupId, int start,
		int end) {
		return this.campaignEventFinder.findByKeywordThemeAndStatus(keyword,
			themeId, status, userId, groupId, start, end);
	}

	/**
	 * Compte de la recherche par mots-clés, thème et status
	 */
	@Override
	public long findByKeywordThemeAndStatusCount(String keyword, long themeId,
		int status, long userId, long groupId) {
		return this.campaignEventFinder.findByKeywordThemeAndStatusCount(
			keyword, themeId, status, userId, groupId);
	}

	/**
	 * Retourne les événements d'une campagne
	 */
	@Override
	public List<CampaignEvent> findByCampaignId(long campaignId) {
		return this.campaignEventPersistence.findByCampaignId(campaignId);
	}

}
