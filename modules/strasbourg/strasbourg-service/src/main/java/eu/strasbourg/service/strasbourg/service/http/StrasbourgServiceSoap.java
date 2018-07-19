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

import eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link StrasbourgServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link eu.strasbourg.service.strasbourg.model.StrasbourgSoap}.
 * If the method in the service utility returns a
 * {@link eu.strasbourg.service.strasbourg.model.Strasbourg}, that is translated to a
 * {@link eu.strasbourg.service.strasbourg.model.StrasbourgSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgServiceHttp
 * @see eu.strasbourg.service.strasbourg.model.StrasbourgSoap
 * @see StrasbourgServiceUtil
 * @generated
 */
@ProviderType
public class StrasbourgServiceSoap {
	public static java.lang.String getCopyright(long groupId,
		java.lang.String uuid, java.lang.String language)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getCopyright(groupId,
					uuid, language);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getFileDetails(long groupId,
		java.lang.String uuid, java.lang.String language)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getFileDetails(groupId,
					uuid, language);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String searchStreets(java.lang.String query)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.searchStreets(query);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String searchStreets(java.lang.String query,
		java.lang.String city) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.searchStreets(query,
					city);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getArticleHTMLContent(long groupId,
		java.lang.String articleId) throws RemoteException {
		try {
			java.lang.String returnValue = StrasbourgServiceUtil.getArticleHTMLContent(groupId,
					articleId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPois(java.lang.String interests,
		long groupId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getPois(interests,
					groupId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getFavoritesPois(long groupId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getFavoritesPois(groupId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPois(java.lang.String interests,
		java.lang.String categories, java.lang.String prefilters, long groupId,
		java.lang.String typeContenu) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getPois(interests,
					categories, prefilters, groupId, typeContenu);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getPoisCategoryCount(long idCategory,
		java.lang.String prefilters, long groupId, java.lang.String typeContenu)
		throws RemoteException {
		try {
			int returnValue = StrasbourgServiceUtil.getPoisCategoryCount(idCategory,
					prefilters, groupId, typeContenu);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getPoisInterestCount(long idCategory, long groupId,
		java.lang.String typeContenu) throws RemoteException {
		try {
			int returnValue = StrasbourgServiceUtil.getPoisInterestCount(idCategory,
					groupId, typeContenu);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getFavoritesPois(long groupId,
		java.lang.String typeContenu) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getFavoritesPois(groupId,
					typeContenu);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getFavoritesPoisCount(long groupId,
		java.lang.String typeContenu) throws RemoteException {
		try {
			int returnValue = StrasbourgServiceUtil.getFavoritesPoisCount(groupId,
					typeContenu);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void hidePortlet(java.lang.String portletId)
		throws RemoteException {
		try {
			StrasbourgServiceUtil.hidePortlet(portletId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getCoordinateForAddress(
		java.lang.String address) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = StrasbourgServiceUtil.getCoordinateForAddress(address);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getTraffic() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getTraffic();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getAlerts() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = StrasbourgServiceUtil.getAlerts();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(StrasbourgServiceSoap.class);
}