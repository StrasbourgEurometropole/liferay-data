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

package eu.strasbourg.service.gtfs.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.IOException;

import eu.strasbourg.service.gtfs.model.Route;
import eu.strasbourg.service.gtfs.service.base.RouteLocalServiceBaseImpl;

/**
 * The implementation of the route local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.RouteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see RouteLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.RouteLocalServiceUtil
 */
public class RouteLocalServiceImpl extends RouteLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.RouteLocalServiceUtil} to access the route local service.
	 */
	
	/**
	 * Crée une Route vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Route createRoute(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		Route route = this.routeLocalService.createRoute(pk);

		return route;
	}
	
	/**
	 * Met à jour une Route et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Route updateRoute(Route route, ServiceContext sc) throws PortalException {
		route = this.routeLocalService.updateRoute(route);

		return route;
	}
	
	/**
	 * Supprime une Route
	 */
	@Override
	public Route removeRoute(long routeId) throws PortalException {
		Route route = this.routePersistence.remove(routeId);

		return route;
	}
	
	/**
	 * Supprime toutes les Routes
	 */
	@Override
	public void removeAllRoute() throws PortalException {
		this.routePersistence.removeAll();
	}
	
}