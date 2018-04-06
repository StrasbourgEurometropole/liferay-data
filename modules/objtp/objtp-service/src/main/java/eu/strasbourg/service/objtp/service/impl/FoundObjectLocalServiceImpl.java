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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.objtp.model.FoundObject;
import eu.strasbourg.service.objtp.service.base.FoundObjectLocalServiceBaseImpl;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

/**
 * The implementation of the found object local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.objtp.service.FoundObjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author JeremyZwickert
 * @see FoundObjectLocalServiceBaseImpl
 * @see eu.strasbourg.service.objtp.service.FoundObjectLocalServiceUtil
 */
public class FoundObjectLocalServiceImpl extends FoundObjectLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.objtp.service.FoundObjectLocalServiceUtil} to access the found object local service.
	 */
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass());
	
	@Override
	public List<FoundObject> getFoundObjectByCategoryCode(String codeCategory) {
		return this.foundObjectPersistence.findByCategoryCode(codeCategory);
	}
	
	/**
	 * Lance l'import des objtp
	 * @throws MalformedURLException 
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@Override
	public boolean doImport() throws JSONException, IOException {
		_log.info("Start importing found objects");
		
		// On vide d'abord la base
		foundObjectPersistence.removeAll();
		
		// On récupère le JSON contenant les objets trouvés depuis un appel à l'API
		JSONObject json = null;
		try {
			String url = StrasbourgPropsUtil.getObjtpURL() + "recherche_objets";
			json = JSONHelper.readJsonFromURL(url);
		} catch (IOException e) { 
			// Erreur de lecture URL
			_log.error("Objet trouvé : Erreur URL");
			_log.error(e);
			return false;
		} catch (JSONException e) { 
			// Erreur de parse du JSON
			_log.error("Objet trouvé : Format JSON invalide");
			_log.error(e);
			return false;
		}
		
	    // Récupère les résultats
	    JSONArray foundObjects = json.getJSONArray("result");
	    
	    if(foundObjects == null) {
	    	_log.error("Aucun objet trouvé");
	    }
	    
	    for (int i = 0; i < foundObjects.length(); i++) {
	    	_log.info("Import objet trouvé : " + (i + 1) + "/" + foundObjects.length());
	    	JSONObject foundObject = foundObjects.getJSONObject(i);
	    	this.importObject(foundObject);
	    }
	    
	    _log.info("Finish importing found objects");
		
		return true;
	}
	
	@Override
	public boolean importObject(JSONObject objectJSON) throws JSONException, IOException {
		
		
		// Récupération des différents champs
		String objectNumero = objectJSON.getString("numero_objet");
		if (Validator.isNull(objectNumero)) {
			_log.error("Champ numero objet absent");
			return false;
		}	
		String depotDate = objectJSON.getString("date_depot");
		if (Validator.isNull(depotDate)) {
			_log.error("Champ date de depot manquant pour l'objet numéro : "+objectNumero);
			return false;
		}	
		String codeCategory = objectJSON.getString("code_categorie");
		if (Validator.isNull(codeCategory)) {
			_log.error("Champ code catégorie manquant pour l'objet numéro : "+objectNumero);
			return false;
		}	
		
		// Crée le nouvel objet trouvé
		FoundObject object = foundObjectLocalService.createFoundObject(objectNumero);		
		object.setCategoryCode(codeCategory);
		object.setDate(depotDate);
		
		// On récupère l'image associée à l'objet trouvé
		String url = StrasbourgPropsUtil.getObjtpURL() + "image_objet?numero_objet="+ object.getNumber();
		JSONObject json = null;
		
		try {
			json = JSONHelper.readJsonFromURL(url);
		} catch (IOException e) { 
			// Erreur de lecture URL
			_log.error("Image : Erreur URL");
			_log.error(e);
			return false;
		} catch (JSONException e) { 
			// Erreur de parse du JSON
			_log.error("Image : Format JSON invalide");
			_log.error(e);
			return false;
		}
		
		JSONArray imageArray = json.getJSONArray("result");
		
		if(imageArray == null){
			_log.error("Aucune image associée à l'objet numéro : " +objectNumero);
		}
		else {
			JSONObject image = imageArray.getJSONObject(0);
			String imageBase64 = image.getString("image");
			
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageBase64);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		}
		
		foundObjectLocalService.updateFoundObject(object);
	
		return true;
	}
}