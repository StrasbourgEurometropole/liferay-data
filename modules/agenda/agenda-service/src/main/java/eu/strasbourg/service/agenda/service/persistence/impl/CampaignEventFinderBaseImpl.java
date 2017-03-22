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

package eu.strasbourg.service.agenda.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventPersistence;

import java.util.Set;

/**
 * @author BenjaminBini
 * @generated
 */
public class CampaignEventFinderBaseImpl extends BasePersistenceImpl<CampaignEvent> {
	@Override
	public Set<String> getBadColumnNames() {
		return getCampaignEventPersistence().getBadColumnNames();
	}

	/**
	 * Returns the campaign event persistence.
	 *
	 * @return the campaign event persistence
	 */
	public CampaignEventPersistence getCampaignEventPersistence() {
		return campaignEventPersistence;
	}

	/**
	 * Sets the campaign event persistence.
	 *
	 * @param campaignEventPersistence the campaign event persistence
	 */
	public void setCampaignEventPersistence(
		CampaignEventPersistence campaignEventPersistence) {
		this.campaignEventPersistence = campaignEventPersistence;
	}

	@BeanReference(type = CampaignEventPersistence.class)
	protected CampaignEventPersistence campaignEventPersistence;
}