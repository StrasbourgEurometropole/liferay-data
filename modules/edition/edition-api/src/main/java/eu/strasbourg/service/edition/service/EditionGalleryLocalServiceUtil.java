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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for EditionGallery. This utility wraps
 * {@link eu.strasbourg.service.edition.service.impl.EditionGalleryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see EditionGalleryLocalService
 * @see eu.strasbourg.service.edition.service.base.EditionGalleryLocalServiceBaseImpl
 * @see eu.strasbourg.service.edition.service.impl.EditionGalleryLocalServiceImpl
 * @generated
 */
@ProviderType
public class EditionGalleryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.edition.service.impl.EditionGalleryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasEditionEditionGalleries(long editionId) {
		return getService().hasEditionEditionGalleries(editionId);
	}

	public static boolean hasEditionEditionGallery(long editionId,
		long galleryId) {
		return getService().hasEditionEditionGallery(editionId, galleryId);
	}

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

	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return getService().search(searchContext);
	}

	/**
	* Add an empty Edition Gallery
	*
	* @return The added Edition Gallery
	* @throws PortalException
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery addEditionGallery()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addEditionGallery();
	}

	/**
	* Adds the edition gallery to the database. Also notifies the appropriate model listeners.
	*
	* @param editionGallery the edition gallery
	* @return the edition gallery that was added
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery addEditionGallery(
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		return getService().addEditionGallery(editionGallery);
	}

	/**
	* Creates a new edition gallery with the primary key. Does not add the edition gallery to the database.
	*
	* @param galleryId the primary key for the new edition gallery
	* @return the new edition gallery
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery createEditionGallery(
		long galleryId) {
		return getService().createEditionGallery(galleryId);
	}

	/**
	* Deletes the edition gallery from the database. Also notifies the appropriate model listeners.
	*
	* @param editionGallery the edition gallery
	* @return the edition gallery that was removed
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery deleteEditionGallery(
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		return getService().deleteEditionGallery(editionGallery);
	}

	/**
	* Deletes the edition gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery that was removed
	* @throws PortalException if a edition gallery with the primary key could not be found
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery deleteEditionGallery(
		long galleryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEditionGallery(galleryId);
	}

	public static eu.strasbourg.service.edition.model.EditionGallery fetchEditionGallery(
		long galleryId) {
		return getService().fetchEditionGallery(galleryId);
	}

	/**
	* Returns the edition gallery matching the UUID and group.
	*
	* @param uuid the edition gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery fetchEditionGalleryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchEditionGalleryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the edition gallery with the primary key.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery
	* @throws PortalException if a edition gallery with the primary key could not be found
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery getEditionGallery(
		long galleryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEditionGallery(galleryId);
	}

	/**
	* Returns the edition gallery matching the UUID and group.
	*
	* @param uuid the edition gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching edition gallery
	* @throws PortalException if a matching edition gallery could not be found
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery getEditionGalleryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEditionGalleryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Delete an Edition Gallery
	*
	* @param galleryId
	The ID of the edition gallery to delete
	* @return The deleted EditionGallery
	* @throws PortalException
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery removeGallery(
		long galleryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeGallery(galleryId);
	}

	/**
	* Updates the edition gallery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param editionGallery the edition gallery
	* @return the edition gallery that was updated
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery updateEditionGallery(
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		return getService().updateEditionGallery(editionGallery);
	}

	/**
	* Update an Edition Gallery
	*
	* @param editionGallery
	The updated Edition Gallery
	* @param sc
	Service Context
	* @return The updated Edition
	* @throws PortalException
	*/
	public static eu.strasbourg.service.edition.model.EditionGallery updateEditionGallery(
		eu.strasbourg.service.edition.model.EditionGallery editionGallery,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateEditionGallery(editionGallery, sc);
	}

	public static int getEditionEditionGalleriesCount(long editionId) {
		return getService().getEditionEditionGalleriesCount(editionId);
	}

	/**
	* Returns the number of edition galleries.
	*
	* @return the number of edition galleries
	*/
	public static int getEditionGalleriesCount() {
		return getService().getEditionGalleriesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les galeries éditions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getEditionEditionGalleries(
		long editionId) {
		return getService().getEditionEditionGalleries(editionId);
	}

	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getEditionEditionGalleries(
		long editionId, int start, int end) {
		return getService().getEditionEditionGalleries(editionId, start, end);
	}

	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getEditionEditionGalleries(
		long editionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.edition.model.EditionGallery> orderByComparator) {
		return getService()
				   .getEditionEditionGalleries(editionId, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the edition galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @return the range of edition galleries
	*/
	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries(
		int start, int end) {
		return getService().getEditionGalleries(start, end);
	}

	/**
	* Returns all the edition galleries matching the UUID and company.
	*
	* @param uuid the UUID of the edition galleries
	* @param companyId the primary key of the company
	* @return the matching edition galleries, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getEditionGalleriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of edition galleries matching the UUID and company.
	*
	* @param uuid the UUID of the edition galleries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching edition galleries, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.edition.model.EditionGallery> orderByComparator) {
		return getService()
				   .getEditionGalleriesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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

	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the editionIds of the editions associated with the edition gallery.
	*
	* @param galleryId the galleryId of the edition gallery
	* @return long[] the editionIds of editions associated with the edition gallery
	*/
	public static long[] getEditionPrimaryKeys(long galleryId) {
		return getService().getEditionPrimaryKeys(galleryId);
	}

	public static void addEditionEditionGalleries(long editionId,
		java.util.List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		getService().addEditionEditionGalleries(editionId, editionGalleries);
	}

	public static void addEditionEditionGalleries(long editionId,
		long[] galleryIds) {
		getService().addEditionEditionGalleries(editionId, galleryIds);
	}

	public static void addEditionEditionGallery(long editionId,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		getService().addEditionEditionGallery(editionId, editionGallery);
	}

	public static void addEditionEditionGallery(long editionId, long galleryId) {
		getService().addEditionEditionGallery(editionId, galleryId);
	}

	public static void changeStatus(
		eu.strasbourg.service.edition.model.EditionGallery editionGallery,
		boolean status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().changeStatus(editionGallery, status);
	}

	public static void clearEditionEditionGalleries(long editionId) {
		getService().clearEditionEditionGalleries(editionId);
	}

	public static void deleteEditionEditionGalleries(long editionId,
		java.util.List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		getService().deleteEditionEditionGalleries(editionId, editionGalleries);
	}

	public static void deleteEditionEditionGalleries(long editionId,
		long[] galleryIds) {
		getService().deleteEditionEditionGalleries(editionId, galleryIds);
	}

	public static void deleteEditionEditionGallery(long editionId,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		getService().deleteEditionEditionGallery(editionId, editionGallery);
	}

	public static void deleteEditionEditionGallery(long editionId,
		long galleryId) {
		getService().deleteEditionEditionGallery(editionId, galleryId);
	}

	public static void setEditionEditionGalleries(long editionId,
		long[] galleryIds) {
		getService().setEditionEditionGalleries(editionId, galleryIds);
	}

	public static EditionGalleryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EditionGalleryLocalService, EditionGalleryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EditionGalleryLocalService.class);
}