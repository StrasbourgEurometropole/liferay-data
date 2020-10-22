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
 * Provides a wrapper for {@link ActivityOrganizerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizerLocalService
 * @generated
 */
@ProviderType
public class ActivityOrganizerLocalServiceWrapper
	implements ActivityOrganizerLocalService,
			   ServiceWrapper<ActivityOrganizerLocalService> {

	public ActivityOrganizerLocalServiceWrapper(
		ActivityOrganizerLocalService activityOrganizerLocalService) {

		_activityOrganizerLocalService = activityOrganizerLocalService;
	}

	/**
	 * Adds the activity organizer to the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityOrganizer the activity organizer
	 * @return the activity organizer that was added
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
		addActivityOrganizer(
			eu.strasbourg.service.activity.model.ActivityOrganizer
				activityOrganizer) {

		return _activityOrganizerLocalService.addActivityOrganizer(
			activityOrganizer);
	}

	/**
	 * Creates a new activity organizer with the primary key. Does not add the activity organizer to the database.
	 *
	 * @param activityOrganizerId the primary key for the new activity organizer
	 * @return the new activity organizer
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
		createActivityOrganizer(long activityOrganizerId) {

		return _activityOrganizerLocalService.createActivityOrganizer(
			activityOrganizerId);
	}

	/**
	 * Crée une activité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
			createActivityOrganizer(
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.createActivityOrganizer(sc);
	}

	/**
	 * Deletes the activity organizer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityOrganizer the activity organizer
	 * @return the activity organizer that was removed
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
		deleteActivityOrganizer(
			eu.strasbourg.service.activity.model.ActivityOrganizer
				activityOrganizer) {

		return _activityOrganizerLocalService.deleteActivityOrganizer(
			activityOrganizer);
	}

	/**
	 * Deletes the activity organizer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityOrganizerId the primary key of the activity organizer
	 * @return the activity organizer that was removed
	 * @throws PortalException if a activity organizer with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
			deleteActivityOrganizer(long activityOrganizerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.deleteActivityOrganizer(
			activityOrganizerId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _activityOrganizerLocalService.dynamicQuery();
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

		return _activityOrganizerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.activity.model.impl.ActivityOrganizerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _activityOrganizerLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.activity.model.impl.ActivityOrganizerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _activityOrganizerLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _activityOrganizerLocalService.dynamicQueryCount(dynamicQuery);
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

		return _activityOrganizerLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
		fetchActivityOrganizer(long activityOrganizerId) {

		return _activityOrganizerLocalService.fetchActivityOrganizer(
			activityOrganizerId);
	}

	/**
	 * Returns the activity organizer matching the UUID and group.
	 *
	 * @param uuid the activity organizer's UUID
	 * @param groupId the primary key of the group
	 * @return the matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
		fetchActivityOrganizerByUuidAndGroupId(String uuid, long groupId) {

		return _activityOrganizerLocalService.
			fetchActivityOrganizerByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.activity.model.ActivityOrganizer> findByKeyword(
			String keyword, long groupId, int start, int end) {

		return _activityOrganizerLocalService.findByKeyword(
			keyword, groupId, start, end);
	}

	/**
	 * Compte de la recherche par mots-clés
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		return _activityOrganizerLocalService.findByKeywordCount(
			keyword, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _activityOrganizerLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the activity organizer with the primary key.
	 *
	 * @param activityOrganizerId the primary key of the activity organizer
	 * @return the activity organizer
	 * @throws PortalException if a activity organizer with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
			getActivityOrganizer(long activityOrganizerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.getActivityOrganizer(
			activityOrganizerId);
	}

	/**
	 * Returns the activity organizer matching the UUID and group.
	 *
	 * @param uuid the activity organizer's UUID
	 * @param groupId the primary key of the group
	 * @return the matching activity organizer
	 * @throws PortalException if a matching activity organizer could not be found
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
			getActivityOrganizerByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.
			getActivityOrganizerByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the activity organizers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.activity.model.impl.ActivityOrganizerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of activity organizers
	 * @param end the upper bound of the range of activity organizers (not inclusive)
	 * @return the range of activity organizers
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.activity.model.ActivityOrganizer>
			getActivityOrganizers(int start, int end) {

		return _activityOrganizerLocalService.getActivityOrganizers(start, end);
	}

	/**
	 * Returns all the activity organizers matching the UUID and company.
	 *
	 * @param uuid the UUID of the activity organizers
	 * @param companyId the primary key of the company
	 * @return the matching activity organizers, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.activity.model.ActivityOrganizer>
			getActivityOrganizersByUuidAndCompanyId(
				String uuid, long companyId) {

		return _activityOrganizerLocalService.
			getActivityOrganizersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of activity organizers matching the UUID and company.
	 *
	 * @param uuid the UUID of the activity organizers
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of activity organizers
	 * @param end the upper bound of the range of activity organizers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching activity organizers, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.activity.model.ActivityOrganizer>
			getActivityOrganizersByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<eu.strasbourg.service.activity.model.ActivityOrganizer>
						orderByComparator) {

		return _activityOrganizerLocalService.
			getActivityOrganizersByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of activity organizers.
	 *
	 * @return the number of activity organizers
	 */
	@Override
	public int getActivityOrganizersCount() {
		return _activityOrganizerLocalService.getActivityOrganizersCount();
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary>
		getAttachedVocabularies(long groupId) {

		return _activityOrganizerLocalService.getAttachedVocabularies(groupId);
	}

	/**
	 * Retourne toutes les éditions d'un groupe
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.activity.model.ActivityOrganizer> getByGroupId(
			long groupId) {

		return _activityOrganizerLocalService.getByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _activityOrganizerLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _activityOrganizerLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _activityOrganizerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
			removeActivityOrganizer(long activityOrganizerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.removeActivityOrganizer(
			activityOrganizerId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public com.liferay.portal.kernel.search.Hits search(
			com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {

		return _activityOrganizerLocalService.search(searchContext);
	}

	/**
	 * Updates the activity organizer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param activityOrganizer the activity organizer
	 * @return the activity organizer that was updated
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
		updateActivityOrganizer(
			eu.strasbourg.service.activity.model.ActivityOrganizer
				activityOrganizer) {

		return _activityOrganizerLocalService.updateActivityOrganizer(
			activityOrganizer);
	}

	/**
	 * Met à jour une activité et l'enregistre en base de données
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer
			updateActivityOrganizer(
				eu.strasbourg.service.activity.model.ActivityOrganizer
					activityOrganizer,
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.updateActivityOrganizer(
			activityOrganizer, sc);
	}

	/**
	 * Met à jour le statut de l'édition par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer updateStatus(
			long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _activityOrganizerLocalService.updateStatus(
			userId, entryId, status);
	}

	@Override
	public ActivityOrganizerLocalService getWrappedService() {
		return _activityOrganizerLocalService;
	}

	@Override
	public void setWrappedService(
		ActivityOrganizerLocalService activityOrganizerLocalService) {

		_activityOrganizerLocalService = activityOrganizerLocalService;
	}

	private ActivityOrganizerLocalService _activityOrganizerLocalService;

}