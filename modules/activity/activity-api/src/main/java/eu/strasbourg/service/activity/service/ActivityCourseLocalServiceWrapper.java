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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ActivityCourseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseLocalService
 * @generated
 */
@ProviderType
public class ActivityCourseLocalServiceWrapper
	implements ActivityCourseLocalService,
		ServiceWrapper<ActivityCourseLocalService> {
	public ActivityCourseLocalServiceWrapper(
		ActivityCourseLocalService activityCourseLocalService) {
		_activityCourseLocalService = activityCourseLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _activityCourseLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _activityCourseLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _activityCourseLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _activityCourseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _activityCourseLocalService.search(searchContext);
	}

	/**
	* Adds the activity course to the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was added
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse addActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse) {
		return _activityCourseLocalService.addActivityCourse(activityCourse);
	}

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse createActivityCourse(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.createActivityCourse(sc);
	}

	/**
	* Creates a new activity course with the primary key. Does not add the activity course to the database.
	*
	* @param activityCourseId the primary key for the new activity course
	* @return the new activity course
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse createActivityCourse(
		long activityCourseId) {
		return _activityCourseLocalService.createActivityCourse(activityCourseId);
	}

	/**
	* Deletes the activity course from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was removed
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse deleteActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse) {
		return _activityCourseLocalService.deleteActivityCourse(activityCourse);
	}

	/**
	* Deletes the activity course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course that was removed
	* @throws PortalException if a activity course with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse deleteActivityCourse(
		long activityCourseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.deleteActivityCourse(activityCourseId);
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse fetchActivityCourse(
		long activityCourseId) {
		return _activityCourseLocalService.fetchActivityCourse(activityCourseId);
	}

	/**
	* Returns the activity course matching the UUID and group.
	*
	* @param uuid the activity course's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse fetchActivityCourseByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _activityCourseLocalService.fetchActivityCourseByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the activity course with the primary key.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course
	* @throws PortalException if a activity course with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse getActivityCourse(
		long activityCourseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.getActivityCourse(activityCourseId);
	}

	/**
	* Returns the activity course matching the UUID and group.
	*
	* @param uuid the activity course's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course
	* @throws PortalException if a matching activity course could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse getActivityCourseByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.getActivityCourseByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime une entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse removeActivityCourse(
		long activityCourseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.removeActivityCourse(activityCourseId);
	}

	/**
	* Updates the activity course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was updated
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse updateActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse) {
		return _activityCourseLocalService.updateActivityCourse(activityCourse);
	}

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse updateActivityCourse(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.updateActivityCourse(activityCourse,
			sc);
	}

	/**
	* Met à jour le statut de l'édition par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse updateStatus(
		long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCourseLocalService.updateStatus(userId, entryId, status);
	}

	/**
	* Returns the number of activity courses.
	*
	* @return the number of activity courses
	*/
	@Override
	public int getActivityCoursesCount() {
		return _activityCourseLocalService.getActivityCoursesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _activityCourseLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _activityCourseLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _activityCourseLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _activityCourseLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Lance une recherche par liste d'ids
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> findByIds(
		java.util.List<java.lang.Long> activityCourseIds) {
		return _activityCourseLocalService.findByIds(activityCourseIds);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _activityCourseLocalService.findByKeyword(keyword, groupId,
			start, end);
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
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCourses(
		int start, int end) {
		return _activityCourseLocalService.getActivityCourses(start, end);
	}

	/**
	* Returns all the activity courses matching the UUID and company.
	*
	* @param uuid the UUID of the activity courses
	* @param companyId the primary key of the company
	* @return the matching activity courses, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCoursesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _activityCourseLocalService.getActivityCoursesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCoursesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.ActivityCourse> orderByComparator) {
		return _activityCourseLocalService.getActivityCoursesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _activityCourseLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne les cours d'une activité
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getByActivity(
		long activityId) {
		return _activityCourseLocalService.getByActivity(activityId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getByGroupId(
		long groupId) {
		return _activityCourseLocalService.getByGroupId(groupId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _activityCourseLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _activityCourseLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _activityCourseLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public ActivityCourseLocalService getWrappedService() {
		return _activityCourseLocalService;
	}

	@Override
	public void setWrappedService(
		ActivityCourseLocalService activityCourseLocalService) {
		_activityCourseLocalService = activityCourseLocalService;
	}

	private ActivityCourseLocalService _activityCourseLocalService;
}