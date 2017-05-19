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
 * Provides a wrapper for {@link ActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityLocalService
 * @generated
 */
@ProviderType
public class ActivityLocalServiceWrapper implements ActivityLocalService,
	ServiceWrapper<ActivityLocalService> {
	public ActivityLocalServiceWrapper(
		ActivityLocalService activityLocalService) {
		_activityLocalService = activityLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _activityLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _activityLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _activityLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _activityLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _activityLocalService.search(searchContext);
	}

	/**
	* Adds the activity to the database. Also notifies the appropriate model listeners.
	*
	* @param activity the activity
	* @return the activity that was added
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity addActivity(
		eu.strasbourg.service.activity.model.Activity activity) {
		return _activityLocalService.addActivity(activity);
	}

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity createActivity(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.createActivity(sc);
	}

	/**
	* Creates a new activity with the primary key. Does not add the activity to the database.
	*
	* @param activityId the primary key for the new activity
	* @return the new activity
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity createActivity(
		long activityId) {
		return _activityLocalService.createActivity(activityId);
	}

	/**
	* Deletes the activity from the database. Also notifies the appropriate model listeners.
	*
	* @param activity the activity
	* @return the activity that was removed
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity deleteActivity(
		eu.strasbourg.service.activity.model.Activity activity) {
		return _activityLocalService.deleteActivity(activity);
	}

	/**
	* Deletes the activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityId the primary key of the activity
	* @return the activity that was removed
	* @throws PortalException if a activity with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity deleteActivity(
		long activityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.deleteActivity(activityId);
	}

	@Override
	public eu.strasbourg.service.activity.model.Activity fetchActivity(
		long activityId) {
		return _activityLocalService.fetchActivity(activityId);
	}

	/**
	* Returns the activity matching the UUID and group.
	*
	* @param uuid the activity's UUID
	* @param groupId the primary key of the group
	* @return the matching activity, or <code>null</code> if a matching activity could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity fetchActivityByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _activityLocalService.fetchActivityByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the activity with the primary key.
	*
	* @param activityId the primary key of the activity
	* @return the activity
	* @throws PortalException if a activity with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity getActivity(
		long activityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.getActivity(activityId);
	}

	/**
	* Returns the activity matching the UUID and group.
	*
	* @param uuid the activity's UUID
	* @param groupId the primary key of the group
	* @return the matching activity
	* @throws PortalException if a matching activity could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity getActivityByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.getActivityByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity removeActivity(
		long activityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.removeActivity(activityId);
	}

	/**
	* Updates the activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param activity the activity
	* @return the activity that was updated
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity updateActivity(
		eu.strasbourg.service.activity.model.Activity activity) {
		return _activityLocalService.updateActivity(activity);
	}

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity updateActivity(
		eu.strasbourg.service.activity.model.Activity activity,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.updateActivity(activity, sc);
	}

	/**
	* Met à jour le statut de l'entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity updateStatus(
		long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityLocalService.updateStatus(userId, entryId, status);
	}

	/**
	* Returns the number of activities.
	*
	* @return the number of activities
	*/
	@Override
	public int getActivitiesCount() {
		return _activityLocalService.getActivitiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _activityLocalService.getOSGiServiceIdentifier();
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
		return _activityLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _activityLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _activityLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.Activity> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _activityLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Returns a range of all the activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activities
	* @param end the upper bound of the range of activities (not inclusive)
	* @return the range of activities
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.Activity> getActivities(
		int start, int end) {
		return _activityLocalService.getActivities(start, end);
	}

	/**
	* Returns all the activities matching the UUID and company.
	*
	* @param uuid the UUID of the activities
	* @param companyId the primary key of the company
	* @return the matching activities, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.Activity> getActivitiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _activityLocalService.getActivitiesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of activities matching the UUID and company.
	*
	* @param uuid the UUID of the activities
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of activities
	* @param end the upper bound of the range of activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching activities, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.Activity> getActivitiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.Activity> orderByComparator) {
		return _activityLocalService.getActivitiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _activityLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.Activity> getByGroupId(
		long groupId) {
		return _activityLocalService.getByGroupId(groupId);
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
		return _activityLocalService.dynamicQueryCount(dynamicQuery);
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
		return _activityLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _activityLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public ActivityLocalService getWrappedService() {
		return _activityLocalService;
	}

	@Override
	public void setWrappedService(ActivityLocalService activityLocalService) {
		_activityLocalService = activityLocalService;
	}

	private ActivityLocalService _activityLocalService;
}