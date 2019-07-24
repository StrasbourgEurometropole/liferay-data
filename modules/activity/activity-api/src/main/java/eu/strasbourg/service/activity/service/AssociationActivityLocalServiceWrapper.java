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

import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssociationActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivityLocalService
 * @generated
 */
@ProviderType
public class AssociationActivityLocalServiceWrapper
	implements AssociationActivityLocalService,
		ServiceWrapper<AssociationActivityLocalService> {
	public AssociationActivityLocalServiceWrapper(
		AssociationActivityLocalService associationActivityLocalService) {
		_associationActivityLocalService = associationActivityLocalService;
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		return _associationActivityLocalService.search(searchContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _associationActivityLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _associationActivityLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _associationActivityLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _associationActivityLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the association activity to the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivity the association activity
	* @return the association activity that was added
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity addAssociationActivity(
		eu.strasbourg.service.activity.model.AssociationActivity associationActivity) {
		return _associationActivityLocalService.addAssociationActivity(associationActivity);
	}

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity createAssociationActivity(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.createAssociationActivity(sc);
	}

	/**
	* Creates a new association activity with the primary key. Does not add the association activity to the database.
	*
	* @param associationActivityId the primary key for the new association activity
	* @return the new association activity
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity createAssociationActivity(
		long associationActivityId) {
		return _associationActivityLocalService.createAssociationActivity(associationActivityId);
	}

	/**
	* Deletes the association activity from the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivity the association activity
	* @return the association activity that was removed
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity deleteAssociationActivity(
		eu.strasbourg.service.activity.model.AssociationActivity associationActivity) {
		return _associationActivityLocalService.deleteAssociationActivity(associationActivity);
	}

	/**
	* Deletes the association activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity that was removed
	* @throws PortalException if a association activity with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity deleteAssociationActivity(
		long associationActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.deleteAssociationActivity(associationActivityId);
	}

	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity fetchAssociationActivity(
		long associationActivityId) {
		return _associationActivityLocalService.fetchAssociationActivity(associationActivityId);
	}

	/**
	* Returns the association activity matching the UUID and group.
	*
	* @param uuid the association activity's UUID
	* @param groupId the primary key of the group
	* @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity fetchAssociationActivityByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _associationActivityLocalService.fetchAssociationActivityByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the association activity with the primary key.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity
	* @throws PortalException if a association activity with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity getAssociationActivity(
		long associationActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.getAssociationActivity(associationActivityId);
	}

	/**
	* Returns the association activity matching the UUID and group.
	*
	* @param uuid the association activity's UUID
	* @param groupId the primary key of the group
	* @return the matching association activity
	* @throws PortalException if a matching association activity could not be found
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity getAssociationActivityByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.getAssociationActivityByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime une entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity removeAssociationActivity(
		long associationActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.removeAssociationActivity(associationActivityId);
	}

	/**
	* Updates the association activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param associationActivity the association activity
	* @return the association activity that was updated
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity updateAssociationActivity(
		eu.strasbourg.service.activity.model.AssociationActivity associationActivity) {
		return _associationActivityLocalService.updateAssociationActivity(associationActivity);
	}

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity updateAssociationActivity(
		eu.strasbourg.service.activity.model.AssociationActivity associationActivity,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.updateAssociationActivity(associationActivity,
			sc);
	}

	/**
	* Met à jour le statut de l'actvité de l'association par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity updateStatus(
		long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _associationActivityLocalService.updateStatus(userId, entryId,
			status);
	}

	/**
	* Returns the number of association activities.
	*
	* @return the number of association activities
	*/
	@Override
	public int getAssociationActivitiesCount() {
		return _associationActivityLocalService.getAssociationActivitiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _associationActivityLocalService.getOSGiServiceIdentifier();
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
		return _associationActivityLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _associationActivityLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _associationActivityLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.AssociationActivity> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _associationActivityLocalService.findByKeyword(keyword, groupId,
			start, end);
	}

	/**
	* Returns a range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of association activities
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.AssociationActivity> getAssociationActivities(
		int start, int end) {
		return _associationActivityLocalService.getAssociationActivities(start,
			end);
	}

	/**
	* Returns all the association activities matching the UUID and company.
	*
	* @param uuid the UUID of the association activities
	* @param companyId the primary key of the company
	* @return the matching association activities, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.AssociationActivity> getAssociationActivitiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _associationActivityLocalService.getAssociationActivitiesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of association activities matching the UUID and company.
	*
	* @param uuid the UUID of the association activities
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching association activities, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.AssociationActivity> getAssociationActivitiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.AssociationActivity> orderByComparator) {
		return _associationActivityLocalService.getAssociationActivitiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _associationActivityLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne les activités d'une association
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.AssociationActivity> getByAssociation(
		long associationId) {
		return _associationActivityLocalService.getByAssociation(associationId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.AssociationActivity> getByGroupId(
		long groupId) {
		return _associationActivityLocalService.getByGroupId(groupId);
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
		return _associationActivityLocalService.dynamicQueryCount(dynamicQuery);
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
		return _associationActivityLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _associationActivityLocalService.findByKeywordCount(keyword,
			groupId);
	}

	@Override
	public AssociationActivityLocalService getWrappedService() {
		return _associationActivityLocalService;
	}

	@Override
	public void setWrappedService(
		AssociationActivityLocalService associationActivityLocalService) {
		_associationActivityLocalService = associationActivityLocalService;
	}

	private AssociationActivityLocalService _associationActivityLocalService;
}