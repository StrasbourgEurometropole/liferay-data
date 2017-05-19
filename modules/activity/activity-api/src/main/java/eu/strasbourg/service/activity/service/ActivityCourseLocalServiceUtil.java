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
 * Provides the local service utility for ActivityCourse. This utility wraps
 * {@link eu.strasbourg.service.activity.service.impl.ActivityCourseLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseLocalService
 * @see eu.strasbourg.service.activity.service.base.ActivityCourseLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.ActivityCourseLocalServiceImpl
 * @generated
 */
@ProviderType
public class ActivityCourseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.ActivityCourseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the activity course to the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was added
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse addActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse) {
		return getService().addActivityCourse(activityCourse);
	}

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse createActivityCourse(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createActivityCourse(sc);
	}

	/**
	* Creates a new activity course with the primary key. Does not add the activity course to the database.
	*
	* @param activityCourseId the primary key for the new activity course
	* @return the new activity course
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse createActivityCourse(
		long activityCourseId) {
		return getService().createActivityCourse(activityCourseId);
	}

	/**
	* Deletes the activity course from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was removed
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse deleteActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse) {
		return getService().deleteActivityCourse(activityCourse);
	}

	/**
	* Deletes the activity course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course that was removed
	* @throws PortalException if a activity course with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse deleteActivityCourse(
		long activityCourseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteActivityCourse(activityCourseId);
	}

	public static eu.strasbourg.service.activity.model.ActivityCourse fetchActivityCourse(
		long activityCourseId) {
		return getService().fetchActivityCourse(activityCourseId);
	}

	/**
	* Returns the activity course matching the UUID and group.
	*
	* @param uuid the activity course's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse fetchActivityCourseByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchActivityCourseByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the activity course with the primary key.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course
	* @throws PortalException if a activity course with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse getActivityCourse(
		long activityCourseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getActivityCourse(activityCourseId);
	}

	/**
	* Returns the activity course matching the UUID and group.
	*
	* @param uuid the activity course's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course
	* @throws PortalException if a matching activity course could not be found
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse getActivityCourseByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getActivityCourseByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse removeActivityCourse(
		long activityCourseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeActivityCourse(activityCourseId);
	}

	/**
	* Updates the activity course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was updated
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse updateActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse) {
		return getService().updateActivityCourse(activityCourse);
	}

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse updateActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateActivityCourse(activityCourse, sc);
	}

	/**
	* Met à jour le statut de l'édition par le framework workflow
	*/
	public static eu.strasbourg.service.activity.model.ActivityCourse updateStatus(
		long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateStatus(userId, entryId, status);
	}

	/**
	* Returns the number of activity courses.
	*
	* @return the number of activity courses
	*/
	public static int getActivityCoursesCount() {
		return getService().getActivityCoursesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Returns a range of all the activity courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @return the range of activity courses
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCourses(
		int start, int end) {
		return getService().getActivityCourses(start, end);
	}

	/**
	* Returns all the activity courses matching the UUID and company.
	*
	* @param uuid the UUID of the activity courses
	* @param companyId the primary key of the company
	* @return the matching activity courses, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCoursesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getActivityCoursesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of activity courses matching the UUID and company.
	*
	* @param uuid the UUID of the activity courses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching activity courses, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCoursesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.ActivityCourse> orderByComparator) {
		return getService()
				   .getActivityCoursesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne les cours d'une activité
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getByActivity(
		long activityId) {
		return getService().getByActivity(activityId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getByGroupId(
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

	/**
	* Compte de la recherche par mots-clés
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static ActivityCourseLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ActivityCourseLocalService, ActivityCourseLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ActivityCourseLocalService.class);
}