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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import eu.strasbourg.service.objtp.model.FoundObject;
import eu.strasbourg.service.objtp.service.base.FoundObjectServiceBaseImpl;

/**
 * The implementation of the found object remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.objtp.service.FoundObjectService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author JeremyZwickert
 * @see FoundObjectServiceBaseImpl
 * @see eu.strasbourg.service.objtp.service.FoundObjectServiceUtil
 */
public class FoundObjectServiceImpl extends FoundObjectServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.objtp.service.FoundObjectServiceUtil} to access the found object remote service.
	 */
	
	/**
	 * Retourne la liste des objets d'une catégorie
	 * @throws PortalException 
	 */
	@Override
	public JSONArray getFoundObjectByCategoryCode(String codeCategory) throws PortalException {
		List<FoundObject> objects = this.foundObjectLocalService.getFoundObjectByCategoryCode(codeCategory);
	
		JSONArray jsonObjects = JSONFactoryUtil.createJSONArray();
		
		for (FoundObject object : objects) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("date_depot", object.getDate());
			jsonObject.put("code_categorie", object.getCategoryCode());
			jsonObject.put("numero_objet", object.getNumber());
			jsonObject.put("nom_categorie", this.objectCategoryLocalService.getObjectCategory(object.getCategoryCode()).getName());
			
			jsonObjects.put(jsonObject);
		}

		return jsonObjects;
	}
}