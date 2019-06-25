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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.IOException;
import java.util.List;

import eu.strasbourg.service.gtfs.model.Direction;
import eu.strasbourg.service.gtfs.service.base.DirectionLocalServiceBaseImpl;

/**
 * The implementation of the direction local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.DirectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see DirectionLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil
 */
public class DirectionLocalServiceImpl extends DirectionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil} to access the direction local service.
	 */
	
	public final static Log log = LogFactoryUtil.getLog(DirectionLocalServiceImpl.class);
	
	/**
	 * Crée une entree avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Direction createDirection(ServiceContext sc) throws PortalException {		
		long pk = counterLocalService.increment();
		
		Direction direction = this.directionLocalService.createDirection(pk);
		
		direction.setGroupId(sc.getScopeGroupId());

		return direction;
	}
	
	/**
	 * Met à jour une entree et l'enregistre en base de données
	 * @throws PortalException 
	 * @throws IOException
	 */
	@Override
	public Direction updateDirection(Direction direction, ServiceContext sc) throws PortalException {		
		direction = this.directionLocalService.updateDirection(direction);

		return direction;
	}
	
	/**
	 * Supprime l'entree
	 */
	@Override
	public Direction removeDirection(long directionId) throws PortalException {		
		// Supprime l'entree
		Direction direction = this.directionPersistence.remove(directionId);

		return direction;
	}
	
	/**
	 * Retourne toutes les entrees d'un groupe
	 */
	@Override
	public List<Direction> getByGroupId(long groupId) {
		return this.directionPersistence.findByGroupId(groupId);
	}
	
	/**
	 * Retourne toutes direction d'un arret
	 */
	@Override
	public List<Direction> getByStopId(String stopId) {
		return this.directionPersistence.findByStopId(stopId);
	}
	
	/**
	 * Retourne toutes direction d'une ligne
	 */
	@Override
	public List<Direction> getByRouteId(String routeId) {
		return this.directionPersistence.findByRouteId(routeId);
	}
	
}