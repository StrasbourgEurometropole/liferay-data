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

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.json.JSONArrayImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.TextFormatter;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.AdictServiceTracker;
import eu.strasbourg.service.adict.Street;
import eu.strasbourg.service.poi.PoiService;
import eu.strasbourg.service.poi.PoiServiceTracker;
import eu.strasbourg.service.strasbourg.service.base.StrasbourgServiceBaseImpl;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.PortletHelper;

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

	private AdictService adictService;
	private AdictServiceTracker adictServiceTracker;

	private AdictService getAdictService() {
		if (adictService == null) {
			adictServiceTracker = new AdictServiceTracker(this);
			adictServiceTracker.open();
			adictService = adictServiceTracker.getService();
		}
		return adictService;
	}

	private PoiService poiService;
	private PoiServiceTracker poiServiceTracker;

	private PoiService getPoiService() {
		if (poiService == null) {
			poiServiceTracker = new PoiServiceTracker(this);
			poiServiceTracker.open();
			poiService = poiServiceTracker.getService();
		}
		return poiService;
	}

	@Override
	public JSONObject getCopyright(long groupId, String uuid, String language) {
		DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);
		Locale locale = Locale.forLanguageTag(language);
		String copyright = FileEntryHelper.getImageCopyright(file.getFileEntryId(), locale);
		return JSONFactoryUtil.createJSONObject().put("copyright", copyright);
	}

	@Override
	public JSONObject getFileDetails(long groupId, String uuid, String language) {
		DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);

		Locale locale = Locale.forLanguageTag(language);

		JSONObject jsonDetail = JSONFactoryUtil.createJSONObject();
		jsonDetail.put("name", file.getName());
		jsonDetail.put("title", FileEntryHelper.getFileTitle(file.getFileEntryId(), locale));
		jsonDetail.put("size", TextFormatter.formatStorageSize(file.getSize(), locale));
		jsonDetail.put("type", file.getExtension());

		return jsonDetail;
	}

	@Override
	public JSONObject searchStreets(String query) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Street> streets = getAdictService().searchStreetNumbers(query);
		JSONArray jsonStreets = JSONFactoryUtil.createJSONArray();
		for (Street street : streets) {
			jsonStreets.put(street.toJSON());
		}
		result.put("streets", jsonStreets);
		return result;
	}

	@Override
	public JSONObject searchStreets(String query, String city) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Street> streets = getAdictService().searchStreetNumbers(query, city);
		JSONArray jsonStreets = JSONFactoryUtil.createJSONArray();
		for (Street street : streets) {
			jsonStreets.put(street.toJSON());
		}
		result.put("streets", jsonStreets);
		return result;
	}

	@Override
	public String getArticleHTMLContent(long groupId, String articleId) {
		try {
			JournalArticleDisplay display = JournalArticleLocalServiceUtil.getArticleDisplay(groupId, articleId,
					"exclusive", "fr_FR", null);
			return display.getContent();
		} catch (Exception ex) {
			return "";
		}

	}

	@Override
	public JSONObject getPois(String interests, long groupId) {
		return getPoiService().getPois(interests, groupId);
	}

	@Override
	public JSONObject getFavoritesPois(long groupId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}

		return getPoiService().getFavoritesPois(userId, groupId);
	}

	@Override
	public JSONObject getPois(String interests, String categories, String prefilters, long groupId, String typeContenu) {
		return getPoiService().getPois(interests, categories, prefilters, groupId, typeContenu);
	}

	@Override
	public int getPoisCategoryCount(long idCategory, String prefilters, long groupId, String typeContenu) {
		return getPoiService().getPoisCategoryCount(idCategory, prefilters, groupId, typeContenu);
	}

	@Override
	public int getPoisInterestCount(long idCategory, long groupId, String typeContenu) {
		return getPoiService().getPoisInterestCount(idCategory, groupId, typeContenu);
	}

	@Override
	public JSONObject getFavoritesPois(long groupId, String typeContenu) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}

		return getPoiService().getFavoritesPois(userId, groupId, typeContenu);
	}

	@Override
	public int getFavoritesPoisCount(long groupId, String typeContenu) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}
		return getPoiService().getFavoritesPoisCount(userId, groupId, typeContenu);
	}

	@Override
	public void hidePortlet(String portletId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		PortletHelper.hidePortlet(portletId);
	}

	@Override
	public JSONArray getCoordinateForAddress(String address) {
		try {
			return getAdictService().getCoordinateForAddress(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getTraffic() {
		return getAdictService().getTraffic();
	}

	@Override
	public JSONObject getAlerts() {
		return getAdictService().getAlerts();
	}
}