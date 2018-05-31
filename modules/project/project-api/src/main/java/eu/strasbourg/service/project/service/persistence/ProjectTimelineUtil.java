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

import eu.strasbourg.service.project.model.ProjectTimeline;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the project timeline service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.ProjectTimelinePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectTimelinePersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.ProjectTimelinePersistenceImpl
 * @generated
 */
@ProviderType
public class ProjectTimelineUtil {
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
	public static void clearCache(ProjectTimeline projectTimeline) {
		getPersistence().clearCache(projectTimeline);
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
	public static List<ProjectTimeline> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectTimeline> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectTimeline> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProjectTimeline update(ProjectTimeline projectTimeline) {
		return getPersistence().update(projectTimeline);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProjectTimeline update(ProjectTimeline projectTimeline,
		ServiceContext serviceContext) {
		return getPersistence().update(projectTimeline, serviceContext);
	}

	/**
	* Returns all the project timelines where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching project timelines
	*/
	public static List<ProjectTimeline> findByProjectId(long projectId) {
		return getPersistence().findByProjectId(projectId);
	}

	/**
	* Returns a range of all the project timelines where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of project timelines
	* @param end the upper bound of the range of project timelines (not inclusive)
	* @return the range of matching project timelines
	*/
	public static List<ProjectTimeline> findByProjectId(long projectId,
		int start, int end) {
		return getPersistence().findByProjectId(projectId, start, end);
	}

	/**
	* Returns an ordered range of all the project timelines where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of project timelines
	* @param end the upper bound of the range of project timelines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching project timelines
	*/
	public static List<ProjectTimeline> findByProjectId(long projectId,
		int start, int end, OrderByComparator<ProjectTimeline> orderByComparator) {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the project timelines where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of project timelines
	* @param end the upper bound of the range of project timelines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching project timelines
	*/
	public static List<ProjectTimeline> findByProjectId(long projectId,
		int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first project timeline in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project timeline
	* @throws NoSuchProjectTimelineException if a matching project timeline could not be found
	*/
	public static ProjectTimeline findByProjectId_First(long projectId,
		OrderByComparator<ProjectTimeline> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectTimelineException {
		return getPersistence()
				   .findByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the first project timeline in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project timeline, or <code>null</code> if a matching project timeline could not be found
	*/
	public static ProjectTimeline fetchByProjectId_First(long projectId,
		OrderByComparator<ProjectTimeline> orderByComparator) {
		return getPersistence()
				   .fetchByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the last project timeline in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project timeline
	* @throws NoSuchProjectTimelineException if a matching project timeline could not be found
	*/
	public static ProjectTimeline findByProjectId_Last(long projectId,
		OrderByComparator<ProjectTimeline> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectTimelineException {
		return getPersistence()
				   .findByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the last project timeline in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project timeline, or <code>null</code> if a matching project timeline could not be found
	*/
	public static ProjectTimeline fetchByProjectId_Last(long projectId,
		OrderByComparator<ProjectTimeline> orderByComparator) {
		return getPersistence()
				   .fetchByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the project timelines before and after the current project timeline in the ordered set where projectId = &#63;.
	*
	* @param projectTimelineId the primary key of the current project timeline
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project timeline
	* @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	*/
	public static ProjectTimeline[] findByProjectId_PrevAndNext(
		long projectTimelineId, long projectId,
		OrderByComparator<ProjectTimeline> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchProjectTimelineException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(projectTimelineId, projectId,
			orderByComparator);
	}

	/**
	* Removes all the project timelines where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	*/
	public static void removeByProjectId(long projectId) {
		getPersistence().removeByProjectId(projectId);
	}

	/**
	* Returns the number of project timelines where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching project timelines
	*/
	public static int countByProjectId(long projectId) {
		return getPersistence().countByProjectId(projectId);
	}

	/**
	* Caches the project timeline in the entity cache if it is enabled.
	*
	* @param projectTimeline the project timeline
	*/
	public static void cacheResult(ProjectTimeline projectTimeline) {
		getPersistence().cacheResult(projectTimeline);
	}

	/**
	* Caches the project timelines in the entity cache if it is enabled.
	*
	* @param projectTimelines the project timelines
	*/
	public static void cacheResult(List<ProjectTimeline> projectTimelines) {
		getPersistence().cacheResult(projectTimelines);
	}

	/**
	* Creates a new project timeline with the primary key. Does not add the project timeline to the database.
	*
	* @param projectTimelineId the primary key for the new project timeline
	* @return the new project timeline
	*/
	public static ProjectTimeline create(long projectTimelineId) {
		return getPersistence().create(projectTimelineId);
	}

	/**
	* Removes the project timeline with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectTimelineId the primary key of the project timeline
	* @return the project timeline that was removed
	* @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	*/
	public static ProjectTimeline remove(long projectTimelineId)
		throws eu.strasbourg.service.project.exception.NoSuchProjectTimelineException {
		return getPersistence().remove(projectTimelineId);
	}

	public static ProjectTimeline updateImpl(ProjectTimeline projectTimeline) {
		return getPersistence().updateImpl(projectTimeline);
	}

	/**
	* Returns the project timeline with the primary key or throws a {@link NoSuchProjectTimelineException} if it could not be found.
	*
	* @param projectTimelineId the primary key of the project timeline
	* @return the project timeline
	* @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	*/
	public static ProjectTimeline findByPrimaryKey(long projectTimelineId)
		throws eu.strasbourg.service.project.exception.NoSuchProjectTimelineException {
		return getPersistence().findByPrimaryKey(projectTimelineId);
	}

	/**
	* Returns the project timeline with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectTimelineId the primary key of the project timeline
	* @return the project timeline, or <code>null</code> if a project timeline with the primary key could not be found
	*/
	public static ProjectTimeline fetchByPrimaryKey(long projectTimelineId) {
		return getPersistence().fetchByPrimaryKey(projectTimelineId);
	}

	public static java.util.Map<java.io.Serializable, ProjectTimeline> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the project timelines.
	*
	* @return the project timelines
	*/
	public static List<ProjectTimeline> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the project timelines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project timelines
	* @param end the upper bound of the range of project timelines (not inclusive)
	* @return the range of project timelines
	*/
	public static List<ProjectTimeline> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the project timelines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project timelines
	* @param end the upper bound of the range of project timelines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of project timelines
	*/
	public static List<ProjectTimeline> findAll(int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the project timelines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectTimelineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of project timelines
	* @param end the upper bound of the range of project timelines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of project timelines
	*/
	public static List<ProjectTimeline> findAll(int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the project timelines from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of project timelines.
	*
	* @return the number of project timelines
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProjectTimelinePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectTimelinePersistence, ProjectTimelinePersistence> _serviceTracker =
		ServiceTrackerFactory.open(ProjectTimelinePersistence.class);
}