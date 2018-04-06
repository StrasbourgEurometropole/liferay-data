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

package eu.strasbourg.service.objtp.service.impl;

import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import eu.strasbourg.service.objtp.model.ObjectCategory;
import eu.strasbourg.service.objtp.service.base.ObjectCategoryServiceBaseImpl;

/**
 * The implementation of the object category remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.objtp.service.ObjectCategoryService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author JeremyZwickert
 * @see ObjectCategoryServiceBaseImpl
 * @see eu.strasbourg.service.objtp.service.ObjectCategoryServiceUtil
 */
public class ObjectCategoryServiceImpl extends ObjectCategoryServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.objtp.service.ObjectCategoryServiceUtil} to access the object category remote service.
	 */
	
	@Override
	public JSONArray getObjectCategories() {
		List<ObjectCategory> objectCategories = this.objectCategoryLocalService.getObjectCategories(-1, -1);
	
		JSONArray jsonObjectCategories = JSONFactoryUtil.createJSONArray();
		
		for (ObjectCategory objectCategory : objectCategories) {
			JSONObject jsonObjectCategory = JSONFactoryUtil.createJSONObject();
			jsonObjectCategory.put("code_categorie", objectCategory.getCode());
			jsonObjectCategory.put("nom_categorie", objectCategory.getName());
			
			jsonObjectCategories.put(jsonObjectCategory);
		}

		return jsonObjectCategories;
	}

}