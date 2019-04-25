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

package eu.strasbourg.service.agenda.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public interface CampaignEventFinder {
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> findByKeywordThemeAndStatus(
		java.lang.String keyword, long themeId, int status, long userId,
		long groupId, int start, int end);

	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> findByKeywordThemeTypeCampaignAndStatus(
		java.lang.String keyword, long themeId, long typeId, long campaignId,
		int status, long userId, long groupId, int start, int end);

	public long findByKeywordThemeAndStatusCount(java.lang.String keyword,
		long themeId, int status, long userId, long groupId);

	public long findByKeywordThemeTypeCampaignAndStatusCount(
		java.lang.String keyword, long themeId, long typeId, long campaignId,
		int status, long userId, long groupId);
}