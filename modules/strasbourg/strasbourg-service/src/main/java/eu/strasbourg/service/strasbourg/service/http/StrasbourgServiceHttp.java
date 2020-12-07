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

package eu.strasbourg.service.strasbourg.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>StrasbourgServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgServiceSoap
 * @generated
 */
@ProviderType
public class StrasbourgServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getCopyright(
		HttpPrincipal httpPrincipal, long groupId, String uuid,
		String language) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getCopyright",
				_getCopyrightParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, uuid, language);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getFileDetails(
		HttpPrincipal httpPrincipal, long groupId, String uuid,
		String language) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getFileDetails",
				_getFileDetailsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, uuid, language);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject searchStreets(
		HttpPrincipal httpPrincipal, String query) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "searchStreets",
				_searchStreetsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, query);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject searchStreets(
		HttpPrincipal httpPrincipal, String query, String city) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "searchStreets",
				_searchStreetsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, query, city);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String getArticleHTMLContent(
		HttpPrincipal httpPrincipal, long groupId, String articleId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getArticleHTMLContent",
				_getArticleHTMLContentParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, String interests, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getPois",
				_getPoisParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, interests, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, String interests, long groupId,
		String localeId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getPois",
				_getPoisParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, interests, groupId, localeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		HttpPrincipal httpPrincipal, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getFavoritesPois",
				_getFavoritesPoisParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, String interests, String categories,
		String prefilters, long groupId, String typeContenu) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getPois",
				_getPoisParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, interests, categories, prefilters, groupId,
				typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, String interests, String categories,
		String prefilters, long groupId, String typeContenu, String localeId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getPois",
				_getPoisParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, interests, categories, prefilters, groupId,
				typeContenu, localeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getPoisCategoryCount(
		HttpPrincipal httpPrincipal, long idCategory, String prefilters,
		long groupId, String typeContenu) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getPoisCategoryCount",
				_getPoisCategoryCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, idCategory, prefilters, groupId, typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getPoisInterestCount(
		HttpPrincipal httpPrincipal, long idCategory, long groupId,
		String typeContenu) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getPoisInterestCount",
				_getPoisInterestCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, idCategory, groupId, typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		HttpPrincipal httpPrincipal, long groupId, String typeContenu) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getFavoritesPois",
				_getFavoritesPoisParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		HttpPrincipal httpPrincipal, long groupId, String typeContenu,
		String localeId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getFavoritesPois",
				_getFavoritesPoisParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, typeContenu, localeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFavoritesPoisCount(
		HttpPrincipal httpPrincipal, long groupId, String typeContenu) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getFavoritesPoisCount",
				_getFavoritesPoisCountParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void hidePortlet(
		HttpPrincipal httpPrincipal, String portletId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "hidePortlet",
				_hidePortletParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray
		getCoordinateForAddress(HttpPrincipal httpPrincipal, String address) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getCoordinateForAddress",
				_getCoordinateForAddressParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, address);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getTraffic(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getTraffic",
				_getTrafficParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getAlerts(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getAlerts",
				_getAlertsParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unfoldPortlet(
		HttpPrincipal httpPrincipal, String portletId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "unfoldPortlet",
				_unfoldPortletParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void foldPortlet(
		HttpPrincipal httpPrincipal, String portletId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "foldPortlet",
				_foldPortletParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray
		getPracticeCategories(
			HttpPrincipal httpPrincipal, long parentCategoryId,
			String localeId) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getPracticeCategories",
				_getPracticeCategoriesParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentCategoryId, localeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject addDocument(
		HttpPrincipal httpPrincipal, String fileContent, String fileName,
		String commissionName, String publicationDate, String documentType,
		String documentName) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "addDocument",
				_addDocumentParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileContent, fileName, commissionName,
				publicationDate, documentType, documentName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray
		getStructuresByGroupIds(HttpPrincipal httpPrincipal, long[] groupIds) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getStructuresByGroupIds",
				_getStructuresByGroupIdsParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray
		getTemplatesByClassPk(HttpPrincipal httpPrincipal, long classPK) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getTemplatesByClassPk",
				_getTemplatesByClassPkParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getTagsAndCategoriesByGroupIdsAndClassName(
			HttpPrincipal httpPrincipal, long[] groupIds, String className) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class,
				"getTagsAndCategoriesByGroupIdsAndClassName",
				_getTagsAndCategoriesByGroupIdsAndClassNameParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds, className);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray getTagsByGroupIds(
		HttpPrincipal httpPrincipal, long[] groupIds) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getTagsByGroupIds",
				_getTagsByGroupIdsParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray
		getCategoriesByClassNameAndGroupIds(
			HttpPrincipal httpPrincipal, long[] groupIds, String className) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class,
				"getCategoriesByClassNameAndGroupIds",
				_getCategoriesByClassNameAndGroupIdsParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds, className);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray
		getVocabulariesByGroupIds(
			HttpPrincipal httpPrincipal, long[] groupIds) {

		try {
			MethodKey methodKey = new MethodKey(
				StrasbourgServiceUtil.class, "getVocabulariesByGroupIds",
				_getVocabulariesByGroupIdsParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		StrasbourgServiceHttp.class);

	private static final Class<?>[] _getCopyrightParameterTypes2 = new Class[] {
		long.class, String.class, String.class
	};
	private static final Class<?>[] _getFileDetailsParameterTypes3 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _searchStreetsParameterTypes4 =
		new Class[] {String.class};
	private static final Class<?>[] _searchStreetsParameterTypes5 =
		new Class[] {String.class, String.class};
	private static final Class<?>[] _getArticleHTMLContentParameterTypes6 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getPoisParameterTypes7 = new Class[] {
		String.class, long.class
	};
	private static final Class<?>[] _getPoisParameterTypes8 = new Class[] {
		String.class, long.class, String.class
	};
	private static final Class<?>[] _getFavoritesPoisParameterTypes9 =
		new Class[] {long.class};
	private static final Class<?>[] _getPoisParameterTypes10 = new Class[] {
		String.class, String.class, String.class, long.class, String.class
	};
	private static final Class<?>[] _getPoisParameterTypes11 = new Class[] {
		String.class, String.class, String.class, long.class, String.class,
		String.class
	};
	private static final Class<?>[] _getPoisCategoryCountParameterTypes12 =
		new Class[] {long.class, String.class, long.class, String.class};
	private static final Class<?>[] _getPoisInterestCountParameterTypes13 =
		new Class[] {long.class, long.class, String.class};
	private static final Class<?>[] _getFavoritesPoisParameterTypes14 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getFavoritesPoisParameterTypes15 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _getFavoritesPoisCountParameterTypes16 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _hidePortletParameterTypes17 = new Class[] {
		String.class
	};
	private static final Class<?>[] _getCoordinateForAddressParameterTypes18 =
		new Class[] {String.class};
	private static final Class<?>[] _getTrafficParameterTypes19 = new Class[] {
		
	};
	private static final Class<?>[] _getAlertsParameterTypes20 = new Class[] {};
	private static final Class<?>[] _unfoldPortletParameterTypes21 =
		new Class[] {String.class};
	private static final Class<?>[] _foldPortletParameterTypes22 = new Class[] {
		String.class
	};
	private static final Class<?>[] _getPracticeCategoriesParameterTypes23 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _addDocumentParameterTypes24 = new Class[] {
		String.class, String.class, String.class, String.class, String.class,
		String.class
	};
	private static final Class<?>[] _getStructuresByGroupIdsParameterTypes25 =
		new Class[] {long[].class};
	private static final Class<?>[] _getTemplatesByClassPkParameterTypes26 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getTagsAndCategoriesByGroupIdsAndClassNameParameterTypes27 =
			new Class[] {long[].class, String.class};
	private static final Class<?>[] _getTagsByGroupIdsParameterTypes28 =
		new Class[] {long[].class};
	private static final Class<?>[]
		_getCategoriesByClassNameAndGroupIdsParameterTypes29 = new Class[] {
			long[].class, String.class
		};
	private static final Class<?>[] _getVocabulariesByGroupIdsParameterTypes30 =
		new Class[] {long[].class};

}