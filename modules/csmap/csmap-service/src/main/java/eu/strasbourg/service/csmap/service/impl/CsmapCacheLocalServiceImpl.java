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

package eu.strasbourg.service.csmap.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.service.agenda.exception.NoSuchManifestationException;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException;
import eu.strasbourg.service.csmap.model.CsmapCache;
import eu.strasbourg.service.csmap.service.base.CsmapCacheLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the csmap cache local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.csmap.service.CsmapCacheLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CsmapCacheLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.csmap.model.CsmapCache",
	service = AopService.class
)
public class CsmapCacheLocalServiceImpl extends CsmapCacheLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.csmap.service.CsmapCacheLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.csmap.service.CsmapCacheLocalServiceUtil</code>.
	 */

	@Override
	public CsmapCache findByCodeCache(long codeCache) {
		try {
			return csmapCachePersistence.findByCodeCache(codeCache);
		} catch (NoSuchCsmapCacheException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CsmapCache> findLastProcessNotSuccess() {
		return csmapCachePersistence.findByLastProcessNotSuccess(false);
	}

	@Override
	public JSONObject getJsonVide() {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
		JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
		JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
		json.put("ADD", jsonAjout);
		json.put("UPDATE", jsonModif);
		json.put("DELETE", jsonSuppr);
		return json;
	}
}