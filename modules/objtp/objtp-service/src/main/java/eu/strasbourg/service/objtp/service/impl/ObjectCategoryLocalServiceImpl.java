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

import java.io.IOException;
import java.net.MalformedURLException;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.objtp.model.ObjectCategory;
import eu.strasbourg.service.objtp.service.base.ObjectCategoryLocalServiceBaseImpl;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

/**
 * The implementation of the object category local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.objtp.service.ObjectCategoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author JeremyZwickert
 * @see ObjectCategoryLocalServiceBaseImpl
 * @see eu.strasbourg.service.objtp.service.ObjectCategoryLocalServiceUtil
 */
public class ObjectCategoryLocalServiceImpl
	extends ObjectCategoryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.objtp.service.ObjectCategoryLocalServiceUtil} to access the object category local service.
	 */
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass());
	
	/**
	 * Lance l'import des catégories d'objet
	 * @throws MalformedURLException 
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@Override
	public boolean doImport() throws JSONException, IOException {
		_log.info("Start importing object categories");
		
		// On vide d'abord la base
		objectCategoryPersistence.removeAll();
		
		JSONObject json = null;
		try {
			// On récupère le JSON contenant les catégories d'objet trouvé depuis un appel à l'API
			String url = StrasbourgPropsUtil.getObjtpURL() + "liste_categories/";
			json = JSONHelper.readJsonFromURL(url);
		} catch (IOException e) { 
			// Erreur de lecture URL
			_log.error("Catégorie d'objet : Erreur URL");
			_log.error(e);
			return false;
		} catch (JSONException e) { 
			// Erreur de parse du JSON
			_log.error("Catégorie d'objet : Format JSON invalide");
			_log.error(e);
			return false;
		}
		
	    // Récupère les résultats
	    JSONArray objectCategories = json.getJSONArray("result");
	    
	    if(objectCategories == null) {
	    	_log.error("Aucune catégorie d'objet");
	    }
	    
	    for (int i = 0; i < objectCategories.length(); i++) {
	    	_log.info("Import catégorie d'objet : " + (i + 1) + "/" + objectCategories.length());
	    	JSONObject objectCategory = objectCategories.getJSONObject(i);
	    	this.importObjectCategory(objectCategory);
	    }		
	    _log.info("Finish importing object categories");
		return true;
	}
	
	@Override
	public boolean importObjectCategory(JSONObject objectCategoryJSON) {
		
		// Récupération des différents champs
		String categoryName = objectCategoryJSON.getString("nom_categorie");
		if (Validator.isNull(categoryName)) {
			_log.error("Champ nom catégorie absent");
			return false;
		}	
		String codeCategory = objectCategoryJSON.getString("code_categorie");
		if (Validator.isNull(categoryName)) {
			_log.error("Champ code catégorie absent");
			return false;
		}
		
		ObjectCategory objectCategory = objectCategoryLocalService.createObjectCategory(codeCategory);
		
		objectCategory.setName(categoryName);
		
		objectCategoryLocalService.updateObjectCategory(objectCategory);
	
		return true;
	}
}