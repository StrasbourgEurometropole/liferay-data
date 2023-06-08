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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.Direction;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Direction. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see DirectionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DirectionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DirectionLocalServiceUtil} to access the direction local service. Add custom service methods to <code>eu.strasbourg.service.gtfs.service.impl.DirectionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the direction to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DirectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param direction the direction
	 * @return the direction that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Direction addDirection(Direction direction);

	/**
	 * Creates a new direction with the primary key. Does not add the direction to the database.
	 *
	 * @param directionId the primary key for the new direction
	 * @return the new direction
	 */
	@Transactional(enabled = false)
	public Direction createDirection(long directionId);

	/**
	 * Crée une entree avec une PK, non ajouté à la base de donnée
	 */
	public Direction createDirection(ServiceContext sc) throws PortalException;

	/**
	 * Deletes the direction from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DirectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param direction the direction
	 * @return the direction that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Direction deleteDirection(Direction direction);

	/**
	 * Deletes the direction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DirectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction that was removed
	 * @throws PortalException if a direction with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Direction deleteDirection(long directionId) throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Direction fetchDirection(long directionId);

	/**
	 * Returns the direction matching the UUID and group.
	 *
	 * @param uuid the direction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching direction, or <code>null</code> if a matching direction could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Direction fetchDirectionByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Retourne la liste de toutes les directions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Direction> getAll();

	/**
	 * Retourne toutes les entrees d'un groupe
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Direction> getByGroupId(long groupId);

	/**
	 * Retourne toutes direction d'une ligne
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Direction> getByRouteId(String routeId);

	/**
	 * Retourne toutes direction d'un arret
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Direction> getByStopId(String stopId);

	/**
	 * Retourne une direction via son tripId
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Direction getByTripId(String tripId);

	/**
	 * Returns the direction with the primary key.
	 *
	 * @param directionId the primary key of the direction
	 * @return the direction
	 * @throws PortalException if a direction with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Direction getDirection(long directionId) throws PortalException;

	/**
	 * Returns the direction matching the UUID and group.
	 *
	 * @param uuid the direction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching direction
	 * @throws PortalException if a matching direction could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Direction getDirectionByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the directions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.DirectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @return the range of directions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Direction> getDirections(int start, int end);

	/**
	 * Returns all the directions matching the UUID and company.
	 *
	 * @param uuid the UUID of the directions
	 * @param companyId the primary key of the company
	 * @return the matching directions, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Direction> getDirectionsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of directions matching the UUID and company.
	 *
	 * @param uuid the UUID of the directions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of directions
	 * @param end the upper bound of the range of directions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching directions, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Direction> getDirectionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Direction> orderByComparator);

	/**
	 * Returns the number of directions.
	 *
	 * @return the number of directions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDirectionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Supprime les entree correspondants au routeId donnee
	 */
	public List<Direction> removeByRouteId(String routeId)
		throws PortalException;

	/**
	 * Supprime les entree correspondants au stopId donnee
	 */
	public List<Direction> removeByStopId(String stopId) throws PortalException;

	/**
	 * Supprime l'entree
	 */
	public Direction removeDirection(long directionId) throws PortalException;

	/**
	 * Supprime les entrees
	 */
	public void removeDirections(List<Direction> directions)
		throws PortalException;

	/**
	 * Updates the direction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DirectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param direction the direction
	 * @return the direction that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Direction updateDirection(Direction direction);

	/**
	 * Met à jour une entree et l'enregistre en base de données
	 *
	 * @throws PortalException
	 * @throws IOException
	 */
	public Direction updateDirection(Direction direction, ServiceContext sc)
		throws PortalException;

	/**
	 * Met à jour les entree donnees
	 *
	 * @throws IOException
	 */
	public void updateDirections(List<Direction> directions, ServiceContext sc)
		throws PortalException;

}