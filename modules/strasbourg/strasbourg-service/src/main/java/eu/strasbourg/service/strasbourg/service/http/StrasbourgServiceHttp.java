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
 * {@link StrasbourgServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
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
 * @see HttpPrincipal
 * @see StrasbourgServiceUtil
 * @generated
 */
@ProviderType
public class StrasbourgServiceHttp {
	public static com.liferay.portal.kernel.json.JSONObject getCopyright(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String uuid,
		java.lang.String language) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getCopyright", _getCopyrightParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					uuid, language);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getFileDetails(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String uuid,
		java.lang.String language) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getFileDetails", _getFileDetailsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					uuid, language);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject searchStreets(
		HttpPrincipal httpPrincipal, java.lang.String query) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"searchStreets", _searchStreetsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, query);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject searchStreets(
		HttpPrincipal httpPrincipal, java.lang.String query,
		java.lang.String city) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"searchStreets", _searchStreetsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, query,
					city);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getArticleHTMLContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getArticleHTMLContent",
					_getArticleHTMLContentParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, java.lang.String interests, long groupId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getPois", _getPoisParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					interests, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, java.lang.String interests, long groupId,
		java.lang.String localeId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getPois", _getPoisParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					interests, groupId, localeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
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
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getFavoritesPois", _getFavoritesPoisParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, java.lang.String interests,
		java.lang.String categories, java.lang.String prefilters, long groupId,
		java.lang.String typeContenu) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getPois", _getPoisParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					interests, categories, prefilters, groupId, typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getPois(
		HttpPrincipal httpPrincipal, java.lang.String interests,
		java.lang.String categories, java.lang.String prefilters, long groupId,
		java.lang.String typeContenu, java.lang.String localeId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getPois", _getPoisParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					interests, categories, prefilters, groupId, typeContenu,
					localeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getPoisCategoryCount(HttpPrincipal httpPrincipal,
		long idCategory, java.lang.String prefilters, long groupId,
		java.lang.String typeContenu) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getPoisCategoryCount",
					_getPoisCategoryCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					idCategory, prefilters, groupId, typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getPoisInterestCount(HttpPrincipal httpPrincipal,
		long idCategory, long groupId, java.lang.String typeContenu) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getPoisInterestCount",
					_getPoisInterestCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					idCategory, groupId, typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String typeContenu) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getFavoritesPois", _getFavoritesPoisParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String typeContenu, java.lang.String localeId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getFavoritesPois", _getFavoritesPoisParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					typeContenu, localeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFavoritesPoisCount(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String typeContenu) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getFavoritesPoisCount",
					_getFavoritesPoisCountParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					typeContenu);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void hidePortlet(HttpPrincipal httpPrincipal,
		java.lang.String portletId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"hidePortlet", _hidePortletParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray getCoordinateForAddress(
		HttpPrincipal httpPrincipal, java.lang.String address) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getCoordinateForAddress",
					_getCoordinateForAddressParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, address);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
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
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getTraffic", _getTrafficParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
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
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"getAlerts", _getAlertsParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unfoldPortlet(HttpPrincipal httpPrincipal,
		java.lang.String portletId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"unfoldPortlet", _unfoldPortletParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey, portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void foldPortlet(HttpPrincipal httpPrincipal,
		java.lang.String portletId) {
		try {
			MethodKey methodKey = new MethodKey(StrasbourgServiceUtil.class,
					"foldPortlet", _foldPortletParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(StrasbourgServiceHttp.class);
	private static final Class<?>[] _getCopyrightParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _getFileDetailsParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _searchStreetsParameterTypes4 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _searchStreetsParameterTypes5 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleHTMLContentParameterTypes6 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getPoisParameterTypes7 = new Class[] {
			java.lang.String.class, long.class
		};
	private static final Class<?>[] _getPoisParameterTypes8 = new Class[] {
			java.lang.String.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _getFavoritesPoisParameterTypes9 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getPoisParameterTypes10 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _getPoisParameterTypes11 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, long.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _getPoisCategoryCountParameterTypes12 = new Class[] {
			long.class, java.lang.String.class, long.class,
			java.lang.String.class
		};
	private static final Class<?>[] _getPoisInterestCountParameterTypes13 = new Class[] {
			long.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _getFavoritesPoisParameterTypes14 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getFavoritesPoisParameterTypes15 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _getFavoritesPoisCountParameterTypes16 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _hidePortletParameterTypes17 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getCoordinateForAddressParameterTypes18 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getTrafficParameterTypes19 = new Class[] {  };
	private static final Class<?>[] _getAlertsParameterTypes20 = new Class[] {  };
	private static final Class<?>[] _unfoldPortletParameterTypes21 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _foldPortletParameterTypes22 = new Class[] {
			java.lang.String.class
		};
}