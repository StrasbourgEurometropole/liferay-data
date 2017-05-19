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

package eu.strasbourg.service.activity.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ActivityCourseSchedule. This utility wraps
 * {@link eu.strasbourg.service.activity.service.impl.ActivityCourseScheduleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseScheduleLocalService
 * @see eu.strasbourg.service.activity.service.base.ActivityCourseScheduleLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.ActivityCourseScheduleLocalServiceImpl
 * @generated
 */
@ProviderType
public class ActivityCourseScheduleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.ActivityCourseScheduleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return getService().search(searchContext);
	}

	/**
	* Adds the activity course schedule to the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseSchedule the activity course schedule
	* @return the activity course schedule that was added
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule addActivityCourseSchedule(
		eu.strasbourg.service.activity.model.ActivityCourseSchedule activityCourseSchedule) {
		return getService().addActivityCourseSchedule(activityCourseSchedule);
	}

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule createActivityCourseSchedule(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createActivityCourseSchedule(sc);
	}

	/**
	* Creates a new activity course schedule with the primary key. Does not add the activity course schedule to the database.
	*
	* @param activityCourseScheduleId the primary key for the new activity course schedule
	* @return the new activity course schedule
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule createActivityCourseSchedule(
		long activityCourseScheduleId) {
		return getService()
				   .createActivityCourseSchedule(activityCourseScheduleId);
	}

	/**
	* Deletes the activity course schedule from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseSchedule the activity course schedule
	* @return the activity course schedule that was removed
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule deleteActivityCourseSchedule(
		eu.strasbourg.service.activity.model.ActivityCourseSchedule activityCourseSchedule) {
		return getService().deleteActivityCourseSchedule(activityCourseSchedule);
	}

	/**
	* Deletes the activity course schedule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseScheduleId the primary key of the activity course schedule
	* @return the activity course schedule that was removed
	* @throws PortalException if a activity course schedule with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule deleteActivityCourseSchedule(
		long activityCourseScheduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteActivityCourseSchedule(activityCourseScheduleId);
	}

	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule fetchActivityCourseSchedule(
		long activityCourseScheduleId) {
		return getService().fetchActivityCourseSchedule(activityCourseScheduleId);
	}

	/**
	* Returns the activity course schedule matching the UUID and group.
	*
	* @param uuid the activity course schedule's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course schedule, or <code>null</code> if a matching activity course schedule could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule fetchActivityCourseScheduleByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService()
				   .fetchActivityCourseScheduleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the activity course schedule with the primary key.
	*
	* @param activityCourseScheduleId the primary key of the activity course schedule
	* @return the activity course schedule
	* @throws PortalException if a activity course schedule with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule getActivityCourseSchedule(
		long activityCourseScheduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getActivityCourseSchedule(activityCourseScheduleId);
	}

	/**
	* Returns the activity course schedule matching the UUID and group.
	*
	* @param uuid the activity course schedule's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course schedule
	* @throws PortalException if a matching activity course schedule could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule getActivityCourseScheduleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getActivityCourseScheduleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule removeActivityCourseSchedule(
		long activityCourseScheduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .removeActivityCourseSchedule(activityCourseScheduleId);
	}

	/**
	* Updates the activity course schedule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param activityCourseSchedule the activity course schedule
	* @return the activity course schedule that was updated
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule updateActivityCourseSchedule(
		eu.strasbourg.service.activity.model.ActivityCourseSchedule activityCourseSchedule) {
		return getService().updateActivityCourseSchedule(activityCourseSchedule);
	}

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourseSchedule updateActivityCourseSchedule(
		eu.strasbourg.service.activity.model.ActivityCourseSchedule activityCourseSchedule,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateActivityCourseSchedule(activityCourseSchedule, sc);
	}

	/**
	* Returns the number of activity course schedules.
	*
	* @return the number of activity course schedules
	*/
	public static int getActivityCourseSchedulesCount() {
		return getService().getActivityCourseSchedulesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Returns a range of all the activity course schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @return the range of activity course schedules
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> getActivityCourseSchedules(
		int start, int end) {
		return getService().getActivityCourseSchedules(start, end);
	}

	/**
	* Returns all the activity course schedules matching the UUID and company.
	*
	* @param uuid the UUID of the activity course schedules
	* @param companyId the primary key of the company
	* @return the matching activity course schedules, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> getActivityCourseSchedulesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getActivityCourseSchedulesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of activity course schedules matching the UUID and company.
	*
	* @param uuid the UUID of the activity course schedules
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of activity course schedules
	* @param end the upper bound of the range of activity course schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching activity course schedules, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> getActivityCourseSchedulesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.ActivityCourseSchedule> orderByComparator) {
		return getService()
				   .getActivityCourseSchedulesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne les horaires d'un cours dans un lieu
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> getByActivityCoursePlace(
		long activityCoursePlaceId) {
		return getService().getByActivityCoursePlace(activityCoursePlaceId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ActivityCourseScheduleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ActivityCourseScheduleLocalService, ActivityCourseScheduleLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ActivityCourseScheduleLocalService.class);
}