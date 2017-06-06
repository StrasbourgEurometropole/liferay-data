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
 * Provides a wrapper for {@link ActivityCoursePlaceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePlaceLocalService
 * @generated
 */
@ProviderType
public class ActivityCoursePlaceLocalServiceWrapper
	implements ActivityCoursePlaceLocalService,
		ServiceWrapper<ActivityCoursePlaceLocalService> {
	public ActivityCoursePlaceLocalServiceWrapper(
		ActivityCoursePlaceLocalService activityCoursePlaceLocalService) {
		_activityCoursePlaceLocalService = activityCoursePlaceLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _activityCoursePlaceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _activityCoursePlaceLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _activityCoursePlaceLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _activityCoursePlaceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _activityCoursePlaceLocalService.search(searchContext);
	}

	/**
	* Adds the activity course place to the database. Also notifies the appropriate model listeners.
	*
	* @param activityCoursePlace the activity course place
	* @return the activity course place that was added
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace addActivityCoursePlace(
		eu.strasbourg.service.activity.model.ActivityCoursePlace activityCoursePlace) {
		return _activityCoursePlaceLocalService.addActivityCoursePlace(activityCoursePlace);
	}

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace createActivityCoursePlace(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.createActivityCoursePlace(sc);
	}

	/**
	* Creates a new activity course place with the primary key. Does not add the activity course place to the database.
	*
	* @param activityCoursePlaceId the primary key for the new activity course place
	* @return the new activity course place
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace createActivityCoursePlace(
		long activityCoursePlaceId) {
		return _activityCoursePlaceLocalService.createActivityCoursePlace(activityCoursePlaceId);
	}

	/**
	* Deletes the activity course place from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCoursePlace the activity course place
	* @return the activity course place that was removed
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace deleteActivityCoursePlace(
		eu.strasbourg.service.activity.model.ActivityCoursePlace activityCoursePlace) {
		return _activityCoursePlaceLocalService.deleteActivityCoursePlace(activityCoursePlace);
	}

	/**
	* Deletes the activity course place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCoursePlaceId the primary key of the activity course place
	* @return the activity course place that was removed
	* @throws PortalException if a activity course place with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace deleteActivityCoursePlace(
		long activityCoursePlaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.deleteActivityCoursePlace(activityCoursePlaceId);
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace fetchActivityCoursePlace(
		long activityCoursePlaceId) {
		return _activityCoursePlaceLocalService.fetchActivityCoursePlace(activityCoursePlaceId);
	}

	/**
	* Returns the activity course place matching the UUID and group.
	*
	* @param uuid the activity course place's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course place, or <code>null</code> if a matching activity course place could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace fetchActivityCoursePlaceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _activityCoursePlaceLocalService.fetchActivityCoursePlaceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the activity course place with the primary key.
	*
	* @param activityCoursePlaceId the primary key of the activity course place
	* @return the activity course place
	* @throws PortalException if a activity course place with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace getActivityCoursePlace(
		long activityCoursePlaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.getActivityCoursePlace(activityCoursePlaceId);
	}

	/**
	* Returns the activity course place matching the UUID and group.
	*
	* @param uuid the activity course place's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course place
	* @throws PortalException if a matching activity course place could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace getActivityCoursePlaceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.getActivityCoursePlaceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime une entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace removeActivityCoursePlace(
		long activityCoursePlaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.removeActivityCoursePlace(activityCoursePlaceId);
	}

	/**
	* Updates the activity course place in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param activityCoursePlace the activity course place
	* @return the activity course place that was updated
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace updateActivityCoursePlace(
		eu.strasbourg.service.activity.model.ActivityCoursePlace activityCoursePlace) {
		return _activityCoursePlaceLocalService.updateActivityCoursePlace(activityCoursePlace);
	}

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace updateActivityCoursePlace(
		eu.strasbourg.service.activity.model.ActivityCoursePlace activityCoursePlace,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityCoursePlaceLocalService.updateActivityCoursePlace(activityCoursePlace,
			sc);
	}

	/**
	* Returns the number of activity course places.
	*
	* @return the number of activity course places
	*/
	@Override
	public int getActivityCoursePlacesCount() {
		return _activityCoursePlaceLocalService.getActivityCoursePlacesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _activityCoursePlaceLocalService.getOSGiServiceIdentifier();
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
		return _activityCoursePlaceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _activityCoursePlaceLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _activityCoursePlaceLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Lance une recherche par liste d'ids
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> findByIds(
		java.util.List<java.lang.Long> activityCoursePlaceIds) {
		return _activityCoursePlaceLocalService.findByIds(activityCoursePlaceIds);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _activityCoursePlaceLocalService.findByKeyword(keyword, groupId,
			start, end);
	}

	/**
	* Récupère la liste des lieux sans horaires
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> findWithNoSchedule(
		long groupId) {
		return _activityCoursePlaceLocalService.findWithNoSchedule(groupId);
	}

	/**
	* Returns a range of all the activity course places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @return the range of activity course places
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> getActivityCoursePlaces(
		int start, int end) {
		return _activityCoursePlaceLocalService.getActivityCoursePlaces(start,
			end);
	}

	/**
	* Returns all the activity course places matching the UUID and company.
	*
	* @param uuid the UUID of the activity course places
	* @param companyId the primary key of the company
	* @return the matching activity course places, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> getActivityCoursePlacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _activityCoursePlaceLocalService.getActivityCoursePlacesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of activity course places matching the UUID and company.
	*
	* @param uuid the UUID of the activity course places
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of activity course places
	* @param end the upper bound of the range of activity course places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching activity course places, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> getActivityCoursePlacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.ActivityCoursePlace> orderByComparator) {
		return _activityCoursePlaceLocalService.getActivityCoursePlacesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _activityCoursePlaceLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne les lieux d'un cours
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> getByActivityCourse(
		long activityCourseId) {
		return _activityCoursePlaceLocalService.getByActivityCourse(activityCourseId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> getByGroupId(
		long groupId) {
		return _activityCoursePlaceLocalService.getByGroupId(groupId);
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
		return _activityCoursePlaceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _activityCoursePlaceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ActivityCoursePlaceLocalService getWrappedService() {
		return _activityCoursePlaceLocalService;
	}

	@Override
	public void setWrappedService(
		ActivityCoursePlaceLocalService activityCoursePlaceLocalService) {
		_activityCoursePlaceLocalService = activityCoursePlaceLocalService;
	}

	private ActivityCoursePlaceLocalService _activityCoursePlaceLocalService;
}