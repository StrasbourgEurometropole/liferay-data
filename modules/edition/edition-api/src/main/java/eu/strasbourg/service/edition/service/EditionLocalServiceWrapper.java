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

package eu.strasbourg.service.edition.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EditionLocalService}.
 *
 * @author BenjaminBini
 * @see EditionLocalService
 * @generated
 */
@ProviderType
public class EditionLocalServiceWrapper implements EditionLocalService,
	ServiceWrapper<EditionLocalService> {
	public EditionLocalServiceWrapper(EditionLocalService editionLocalService) {
		_editionLocalService = editionLocalService;
	}

	@Override
	public boolean hasEditionGalleryEdition(long galleryId, long editionId) {
		return _editionLocalService.hasEditionGalleryEdition(galleryId,
			editionId);
	}

	@Override
	public boolean hasEditionGalleryEditions(long galleryId) {
		return _editionLocalService.hasEditionGalleryEditions(galleryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _editionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _editionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _editionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _editionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _editionLocalService.search(searchContext);
	}

	/**
	* Add an empty Edition
	*
	* @return The added Edition
	* @throws PortalException
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition addEdition()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.addEdition();
	}

	/**
	* Adds the edition to the database. Also notifies the appropriate model listeners.
	*
	* @param edition the edition
	* @return the edition that was added
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition addEdition(
		eu.strasbourg.service.edition.model.Edition edition) {
		return _editionLocalService.addEdition(edition);
	}

	/**
	* Creates a new edition with the primary key. Does not add the edition to the database.
	*
	* @param editionId the primary key for the new edition
	* @return the new edition
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition createEdition(
		long editionId) {
		return _editionLocalService.createEdition(editionId);
	}

	/**
	* Deletes the edition from the database. Also notifies the appropriate model listeners.
	*
	* @param edition the edition
	* @return the edition that was removed
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition deleteEdition(
		eu.strasbourg.service.edition.model.Edition edition) {
		return _editionLocalService.deleteEdition(edition);
	}

	/**
	* Deletes the edition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param editionId the primary key of the edition
	* @return the edition that was removed
	* @throws PortalException if a edition with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition deleteEdition(
		long editionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.deleteEdition(editionId);
	}

	@Override
	public eu.strasbourg.service.edition.model.Edition fetchEdition(
		long editionId) {
		return _editionLocalService.fetchEdition(editionId);
	}

	/**
	* Returns the edition matching the UUID and group.
	*
	* @param uuid the edition's UUID
	* @param groupId the primary key of the group
	* @return the matching edition, or <code>null</code> if a matching edition could not be found
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition fetchEditionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _editionLocalService.fetchEditionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the edition with the primary key.
	*
	* @param editionId the primary key of the edition
	* @return the edition
	* @throws PortalException if a edition with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition getEdition(
		long editionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.getEdition(editionId);
	}

	/**
	* Returns the edition matching the UUID and group.
	*
	* @param uuid the edition's UUID
	* @param groupId the primary key of the group
	* @return the matching edition
	* @throws PortalException if a matching edition could not be found
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition getEditionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.getEditionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Delete an Edition
	*
	* @param editionId
	The ID of the edition to delete
	* @return The deleted Edition
	* @throws PortalException
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition removeEdition(
		long editionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.removeEdition(editionId);
	}

	/**
	* Updates the edition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param edition the edition
	* @return the edition that was updated
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition updateEdition(
		eu.strasbourg.service.edition.model.Edition edition) {
		return _editionLocalService.updateEdition(edition);
	}

	/**
	* Update an Edition
	*
	* @param edition
	The updated Edition
	* @param sc
	Service Context
	* @return The updated Edition
	* @throws PortalException
	*/
	@Override
	public eu.strasbourg.service.edition.model.Edition updateEdition(
		eu.strasbourg.service.edition.model.Edition edition,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionLocalService.updateEdition(edition, sc);
	}

	@Override
	public int getEditionGalleryEditionsCount(long galleryId) {
		return _editionLocalService.getEditionGalleryEditionsCount(galleryId);
	}

	/**
	* Returns the number of editions.
	*
	* @return the number of editions
	*/
	@Override
	public int getEditionsCount() {
		return _editionLocalService.getEditionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _editionLocalService.getOSGiServiceIdentifier();
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
		return _editionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _editionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _editionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _editionLocalService.findByKeyword(keyword, groupId, start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _editionLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getByGroupId(
		long groupId) {
		return _editionLocalService.getByGroupId(groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditionGalleryEditions(
		long galleryId) {
		return _editionLocalService.getEditionGalleryEditions(galleryId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditionGalleryEditions(
		long galleryId, int start, int end) {
		return _editionLocalService.getEditionGalleryEditions(galleryId, start,
			end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditionGalleryEditions(
		long galleryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.edition.model.Edition> orderByComparator) {
		return _editionLocalService.getEditionGalleryEditions(galleryId, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the editions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of editions
	*/
	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditions(
		int start, int end) {
		return _editionLocalService.getEditions(start, end);
	}

	/**
	* Returns all the editions matching the UUID and company.
	*
	* @param uuid the UUID of the editions
	* @param companyId the primary key of the company
	* @return the matching editions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _editionLocalService.getEditionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of editions matching the UUID and company.
	*
	* @param uuid the UUID of the editions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching editions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.edition.model.Edition> orderByComparator) {
		return _editionLocalService.getEditionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _editionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _editionLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _editionLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the galleryIds of the edition galleries associated with the edition.
	*
	* @param editionId the editionId of the edition
	* @return long[] the galleryIds of edition galleries associated with the edition
	*/
	@Override
	public long[] getEditionGalleryPrimaryKeys(long editionId) {
		return _editionLocalService.getEditionGalleryPrimaryKeys(editionId);
	}

	@Override
	public void addEditionGalleryEdition(long galleryId,
		eu.strasbourg.service.edition.model.Edition edition) {
		_editionLocalService.addEditionGalleryEdition(galleryId, edition);
	}

	@Override
	public void addEditionGalleryEdition(long galleryId, long editionId) {
		_editionLocalService.addEditionGalleryEdition(galleryId, editionId);
	}

	@Override
	public void addEditionGalleryEditions(long galleryId,
		java.util.List<eu.strasbourg.service.edition.model.Edition> editions) {
		_editionLocalService.addEditionGalleryEditions(galleryId, editions);
	}

	@Override
	public void addEditionGalleryEditions(long galleryId, long[] editionIds) {
		_editionLocalService.addEditionGalleryEditions(galleryId, editionIds);
	}

	@Override
	public void changeStatus(
		eu.strasbourg.service.edition.model.Edition edition,
		boolean publicationStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		_editionLocalService.changeStatus(edition, publicationStatus);
	}

	@Override
	public void clearEditionGalleryEditions(long galleryId) {
		_editionLocalService.clearEditionGalleryEditions(galleryId);
	}

	@Override
	public void deleteEditionGalleryEdition(long galleryId,
		eu.strasbourg.service.edition.model.Edition edition) {
		_editionLocalService.deleteEditionGalleryEdition(galleryId, edition);
	}

	@Override
	public void deleteEditionGalleryEdition(long galleryId, long editionId) {
		_editionLocalService.deleteEditionGalleryEdition(galleryId, editionId);
	}

	@Override
	public void deleteEditionGalleryEditions(long galleryId,
		java.util.List<eu.strasbourg.service.edition.model.Edition> editions) {
		_editionLocalService.deleteEditionGalleryEditions(galleryId, editions);
	}

	@Override
	public void deleteEditionGalleryEditions(long galleryId, long[] editionIds) {
		_editionLocalService.deleteEditionGalleryEditions(galleryId, editionIds);
	}

	@Override
	public void setEditionGalleryEditions(long galleryId, long[] editionIds) {
		_editionLocalService.setEditionGalleryEditions(galleryId, editionIds);
	}

	@Override
	public EditionLocalService getWrappedService() {
		return _editionLocalService;
	}

	@Override
	public void setWrappedService(EditionLocalService editionLocalService) {
		_editionLocalService = editionLocalService;
	}

	private EditionLocalService _editionLocalService;
}