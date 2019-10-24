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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.LocaleUtil;
import eu.strasbourg.service.agenda.service.base.AgendaExportServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.util.List;
import java.util.Locale;

/**
 * The implementation of the agenda export remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.service.AgendaExportService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExportServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.AgendaExportServiceUtil
 */
public class AgendaExportServiceImpl extends AgendaExportServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.AgendaExportServiceUtil} to access the agenda export remote service.
	 */

	/**
	 * Renvoit la liste des catégories parentes d'un vocabulaire
	 */
	@Override
	public JSONArray getParentCategories(Long vocabularyId, String localeId) throws PortalException {

		AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(vocabularyId);
		List<AssetCategory> parentCategories = AssetVocabularyHelper.getParentCategory(vocabulary.getCategories());
		Locale locale = LocaleUtil.fromLanguageId(localeId);

		JSONArray result = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : parentCategories) {
			JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
			jsonCategory.put("title",category.getTitle(locale));
			jsonCategory.put("id",category.getCategoryId());
			result.put(jsonCategory);
		}

		return result;
	}
}