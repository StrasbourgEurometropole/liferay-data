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

package eu.strasbourg.service.interest.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.interest.service.InterestServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>InterestServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>eu.strasbourg.service.interest.model.InterestSoap</code>. If the method in the
 * service utility returns a
 * <code>eu.strasbourg.service.interest.model.Interest</code>, that is translated to a
 * <code>eu.strasbourg.service.interest.model.InterestSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
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
 * @see InterestServiceHttp
 * @generated
 */
public class InterestServiceSoap {

	/**
	 * Retourne la liste de tous les centres d'intérêt
	 */
	public static String getInterests() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				InterestServiceUtil.getInterests();

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Modifie les intérêts de l'utilisateur ayant l'id (publik) passé en
	 * paramètre
	 */
	public static String setUserInterests(String userId, String interestIds)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				InterestServiceUtil.setUserInterests(userId, interestIds);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Retourne la liste des intérêts de l'utilisateur ayant l'id (publik) passé
	 * en paramètre
	 */
	public static String getUserInterests(String userId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				InterestServiceUtil.getUserInterests(userId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(InterestServiceSoap.class);

}