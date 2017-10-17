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

package eu.strasbourg.service.agenda.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.agenda.service.EventServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link EventServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link eu.strasbourg.service.agenda.model.EventSoap}.
 * If the method in the service utility returns a
 * {@link eu.strasbourg.service.agenda.model.Event}, that is translated to a
 * {@link eu.strasbourg.service.agenda.model.EventSoap}. Methods that SOAP cannot
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
 * @author BenjaminBini
 * @see EventServiceHttp
 * @see eu.strasbourg.service.agenda.model.EventSoap
 * @see EventServiceUtil
 * @generated
 */
@ProviderType
public class EventServiceSoap {
	public static java.lang.String getCategories() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = EventServiceUtil.getCategories();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPublics() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = EventServiceUtil.getPublics();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getThemes() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = EventServiceUtil.getThemes();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getTypes() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = EventServiceUtil.getTypes();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getServices() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = EventServiceUtil.getServices();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getTerritories() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = EventServiceUtil.getTerritories();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getCategory(long id)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = EventServiceUtil.getCategory(id);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getEvent(long id) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = EventServiceUtil.getEvent(id);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getEvents() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = EventServiceUtil.getEvents();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getEventsByDate(java.lang.String date)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = EventServiceUtil.getEventsByDate(date);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getEventsByCategory(long categoryId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = EventServiceUtil.getEventsByCategory(categoryId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getEventsByPlace(java.lang.String placeSIGId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = EventServiceUtil.getEventsByPlace(placeSIGId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getEventsByLanguage(
		java.lang.String language) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = EventServiceUtil.getEventsByLanguage(language);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(EventServiceSoap.class);
}