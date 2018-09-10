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

package eu.strasbourg.service.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.project.model.ProjectFollowed;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the project followed service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.ProjectFollowedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectFollowedPersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.ProjectFollowedPersistenceImpl
 * @generated
 */
@ProviderType
public class ProjectFollowedUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ProjectFollowed projectFollowed) {
		getPersistence().clearCache(projectFollowed);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProjectFollowed> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectFollowed> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectFollowed> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProjectFollowed update(ProjectFollowed projectFollowed) {
		return getPersistence().update(projectFollowed);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProjectFollowed update(ProjectFollowed projectFollowed,
		ServiceContext serviceContext) {
		return getPersistence().update(projectFollowed, serviceContext);
	}

	/**
	* Returns all the project followeds where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching project followeds
	*/
	public static List<ProjectFollowed> findByPublikUserId(
		java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the project followeds where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @return the range of matching project followeds
	*/
	public static List<ProjectFollowed> findByPublikUserId(
		java.lang.String publikUserId, int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the project followeds where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching project followeds
	*/
	public static List<ProjectFollowed> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the project followeds where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching project followeds
	*/
	public static List<ProjectFollowed> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first project followed in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project followed
	* @throws NoSuchProjectFollowedException if a matching project followed could not be found
	*/
	public static ProjectFollowed findByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first project followed in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project followed, or <code>null</code> if a matching project followed could not be found
	*/
	public static ProjectFollowed fetchByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last project followed in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project followed
	* @throws NoSuchProjectFollowedException if a matching project followed could not be found
	*/
	public static ProjectFollowed findByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last project followed in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project followed, or <code>null</code> if a matching project followed could not be found
	*/
	public static ProjectFollowed fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the project followeds before and after the current project followed in the ordered set where publikUserId = &#63;.
	*
	* @param projectFollowedId the primary key of the current project followed
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project followed
	* @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	*/
	public static ProjectFollowed[] findByPublikUserId_PrevAndNext(
		long projectFollowedId, java.lang.String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(projectFollowedId,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the project followeds where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of project followeds where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching project followeds
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Returns all the project followeds where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching project followeds
	*/
	public static List<ProjectFollowed> findByProjectId(long projectId) {
		return getPersistence().findByProjectId(projectId);
	}

	/**
	* Returns a range of all the project followeds where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @return the range of matching project followeds
	*/
	public static List<ProjectFollowed> findByProjectId(long projectId,
		int start, int end) {
		return getPersistence().findByProjectId(projectId, start, end);
	}

	/**
	* Returns an ordered range of all the project followeds where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching project followeds
	*/
	public static List<ProjectFollowed> findByProjectId(long projectId,
		int start, int end, OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the project followeds where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching project followeds
	*/
	public static List<ProjectFollowed> findByProjectId(long projectId,
		int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first project followed in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project followed
	* @throws NoSuchProjectFollowedException if a matching project followed could not be found
	*/
	public static ProjectFollowed findByProjectId_First(long projectId,
		OrderByComparator<ProjectFollowed> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .findByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the first project followed in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project followed, or <code>null</code> if a matching project followed could not be found
	*/
	public static ProjectFollowed fetchByProjectId_First(long projectId,
		OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence()
				   .fetchByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the last project followed in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project followed
	* @throws NoSuchProjectFollowedException if a matching project followed could not be found
	*/
	public static ProjectFollowed findByProjectId_Last(long projectId,
		OrderByComparator<ProjectFollowed> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .findByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the last project followed in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project followed, or <code>null</code> if a matching project followed could not be found
	*/
	public static ProjectFollowed fetchByProjectId_Last(long projectId,
		OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence()
				   .fetchByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the project followeds before and after the current project followed in the ordered set where projectId = &#63;.
	*
	* @param projectFollowedId the primary key of the current project followed
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project followed
	* @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	*/
	public static ProjectFollowed[] findByProjectId_PrevAndNext(
		long projectFollowedId, long projectId,
		OrderByComparator<ProjectFollowed> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(projectFollowedId, projectId,
			orderByComparator);
	}

	/**
	* Removes all the project followeds where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	*/
	public static void removeByProjectId(long projectId) {
		getPersistence().removeByProjectId(projectId);
	}

	/**
	* Returns the number of project followeds where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching project followeds
	*/
	public static int countByProjectId(long projectId) {
		return getPersistence().countByProjectId(projectId);
	}

	/**
	* Returns the project followed where publikUserId = &#63; and projectId = &#63; or throws a {@link NoSuchProjectFollowedException} if it could not be found.
	*
	* @param publikUserId the publik user ID
	* @param projectId the project ID
	* @return the matching project followed
	* @throws NoSuchProjectFollowedException if a matching project followed could not be found
	*/
	public static ProjectFollowed findByPublikUserIdAndProjectId(
		java.lang.String publikUserId, long projectId)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .findByPublikUserIdAndProjectId(publikUserId, projectId);
	}

	/**
	* Returns the project followed where publikUserId = &#63; and projectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param projectId the project ID
	* @return the matching project followed, or <code>null</code> if a matching project followed could not be found
	*/
	public static ProjectFollowed fetchByPublikUserIdAndProjectId(
		java.lang.String publikUserId, long projectId) {
		return getPersistence()
				   .fetchByPublikUserIdAndProjectId(publikUserId, projectId);
	}

	/**
	* Returns the project followed where publikUserId = &#63; and projectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param projectId the project ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching project followed, or <code>null</code> if a matching project followed could not be found
	*/
	public static ProjectFollowed fetchByPublikUserIdAndProjectId(
		java.lang.String publikUserId, long projectId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByPublikUserIdAndProjectId(publikUserId, projectId,
			retrieveFromCache);
	}

	/**
	* Removes the project followed where publikUserId = &#63; and projectId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param projectId the project ID
	* @return the project followed that was removed
	*/
	public static ProjectFollowed removeByPublikUserIdAndProjectId(
		java.lang.String publikUserId, long projectId)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence()
				   .removeByPublikUserIdAndProjectId(publikUserId, projectId);
	}

	/**
	* Returns the number of project followeds where publikUserId = &#63; and projectId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param projectId the project ID
	* @return the number of matching project followeds
	*/
	public static int countByPublikUserIdAndProjectId(
		java.lang.String publikUserId, long projectId) {
		return getPersistence()
				   .countByPublikUserIdAndProjectId(publikUserId, projectId);
	}

	/**
	* Caches the project followed in the entity cache if it is enabled.
	*
	* @param projectFollowed the project followed
	*/
	public static void cacheResult(ProjectFollowed projectFollowed) {
		getPersistence().cacheResult(projectFollowed);
	}

	/**
	* Caches the project followeds in the entity cache if it is enabled.
	*
	* @param projectFolloweds the project followeds
	*/
	public static void cacheResult(List<ProjectFollowed> projectFolloweds) {
		getPersistence().cacheResult(projectFolloweds);
	}

	/**
	* Creates a new project followed with the primary key. Does not add the project followed to the database.
	*
	* @param projectFollowedId the primary key for the new project followed
	* @return the new project followed
	*/
	public static ProjectFollowed create(long projectFollowedId) {
		return getPersistence().create(projectFollowedId);
	}

	/**
	* Removes the project followed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectFollowedId the primary key of the project followed
	* @return the project followed that was removed
	* @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	*/
	public static ProjectFollowed remove(long projectFollowedId)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence().remove(projectFollowedId);
	}

	public static ProjectFollowed updateImpl(ProjectFollowed projectFollowed) {
		return getPersistence().updateImpl(projectFollowed);
	}

	/**
	* Returns the project followed with the primary key or throws a {@link NoSuchProjectFollowedException} if it could not be found.
	*
	* @param projectFollowedId the primary key of the project followed
	* @return the project followed
	* @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	*/
	public static ProjectFollowed findByPrimaryKey(long projectFollowedId)
		throws eu.strasbourg.service.project.exception.NoSuchProjectFollowedException {
		return getPersistence().findByPrimaryKey(projectFollowedId);
	}

	/**
	* Returns the project followed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectFollowedId the primary key of the project followed
	* @return the project followed, or <code>null</code> if a project followed with the primary key could not be found
	*/
	public static ProjectFollowed fetchByPrimaryKey(long projectFollowedId) {
		return getPersistence().fetchByPrimaryKey(projectFollowedId);
	}

	public static java.util.Map<java.io.Serializable, ProjectFollowed> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the project followeds.
	*
	* @return the project followeds
	*/
	public static List<ProjectFollowed> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the project followeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @return the range of project followeds
	*/
	public static List<ProjectFollowed> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the project followeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of project followeds
	*/
	public static List<ProjectFollowed> findAll(int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the project followeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectFollowedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project followeds
	* @param end the upper bound of the range of project followeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of project followeds
	*/
	public static List<ProjectFollowed> findAll(int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the project followeds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of project followeds.
	*
	* @return the number of project followeds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProjectFollowedPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectFollowedPersistence, ProjectFollowedPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ProjectFollowedPersistence.class);
}