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

package eu.strasbourg.service.search.log.service.impl;

import java.util.Date;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.search.log.model.SearchLog;
import eu.strasbourg.service.search.log.service.base.SearchLogLocalServiceBaseImpl;

/**
 * The implementation of the search log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.search.log.service.SearchLogLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see SearchLogLocalServiceBaseImpl
 * @see eu.strasbourg.service.search.log.service.SearchLogLocalServiceUtil
 */
@ProviderType
public class SearchLogLocalServiceImpl extends SearchLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.search.log.service.SearchLogLocalServiceUtil} to
	 * access the search log local service.
	 */
	/**
	 * Crée un log de la recherche
	 */
	@Override
	public SearchLog addSearchLog(ServiceContext sc, String keywords,
		long resultCount, AssetEntry result1, AssetEntry result2,
		AssetEntry result3, AssetEntry userTargetResult, long searchTime)
		throws PortalException {

		long pk = counterLocalService.increment();

		SearchLog searchLog = this.searchLogLocalService.createSearchLog(pk);

		searchLog.setGroupId(sc.getScopeGroupId());
		searchLog.setKeywords(keywords);
		searchLog.setResultCount(resultCount);
		if (result1 != null) {
			searchLog.setResult1ClassId(result1.getClassNameId());
			searchLog.setResult1ClassPK(result1.getClassPK());
			searchLog.setResult1Title(result1.getTitle(sc.getLocale()));
		}
		if (result2 != null) {
			searchLog.setResult2ClassId(result2.getClassNameId());
			searchLog.setResult2ClassPK(result2.getClassPK());
			searchLog.setResult2Title(result2.getTitle(sc.getLocale()));
		}
		if (result3 != null) {
			searchLog.setResult3ClassId(result3.getClassNameId());
			searchLog.setResult3ClassPK(result3.getClassPK());
			searchLog.setResult3Title(result3.getTitle(sc.getLocale()));
		}
		if (userTargetResult != null) {
			searchLog.setUserTargetClassId(userTargetResult.getClassNameId());
			searchLog.setUserTargetClassPK(userTargetResult.getClassPK());
			searchLog
				.setUserTargetTitle(userTargetResult.getTitle(sc.getLocale()));
		}
		searchLog.setDate(new Date());
		searchLog.setGroupId(sc.getScopeGroupId());
		searchLog.setLayoutFriendlyURL(sc.getLayoutURL());
		searchLog.setLayoutId(sc.getPlid());
		searchLog.setSearchTime(searchTime);
		searchLog.setLanguage(sc.getLocale().getLanguage());

		this.searchLogLocalService.updateSearchLog(searchLog);
		return searchLog;
	}
}