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

package eu.strasbourg.service.strasbourg.service.impl;

import java.util.Locale;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.TextFormatter;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.strasbourg.service.base.StrasbourgServiceBaseImpl;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The implementation of the strasbourg remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.strasbourg.service.StrasbourgService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgServiceBaseImpl
 * @see eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil
 */
@ProviderType
public class StrasbourgServiceImpl extends StrasbourgServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil} to access
	 * the strasbourg remote service.
	 */
	@Override
	public JSONObject getCopyright(long groupId, String uuid, String language) {
		DLFileEntry file = DLFileEntryLocalServiceUtil
			.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);
		Locale locale = Locale.forLanguageTag(language);
		String copyright = FileEntryHelper
			.getImageCopyright(file.getFileEntryId(), locale);
		return JSONFactoryUtil.createJSONObject().put("copyright", copyright);
	}
	
	@Override
	public JSONObject getFileDetails(long groupId, String uuid, String language) {
		DLFileEntry file = DLFileEntryLocalServiceUtil
			.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);
		
		Locale locale = Locale.forLanguageTag(language);
		
		JSONObject jsonDetail = JSONFactoryUtil.createJSONObject();
		jsonDetail.put("name", file.getName());
		jsonDetail.put("title", FileEntryHelper.getFileTitle(file.getFileEntryId(), locale));
		jsonDetail.put("size", TextFormatter.formatStorageSize(file.getSize(), locale));
		jsonDetail.put("type", file.getExtension());
		
		return jsonDetail;
	}
}