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

import com.liferay.asset.kernel.model.AssetVocabulary;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.edition.model.EditionGallery;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for EditionGallery. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see EditionGalleryLocalServiceUtil
 * @see eu.strasbourg.service.edition.service.base.EditionGalleryLocalServiceBaseImpl
 * @see eu.strasbourg.service.edition.service.impl.EditionGalleryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EditionGalleryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EditionGalleryLocalServiceUtil} to access the edition gallery local service. Add custom service methods to {@link eu.strasbourg.service.edition.service.impl.EditionGalleryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasEditionEditionGalleries(long editionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasEditionEditionGallery(long editionId, long galleryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

	/**
	* Add an empty Edition Gallery
	*
	* @return The added Edition Gallery
	* @throws PortalException
	*/
	public EditionGallery addEditionGallery() throws PortalException;

	/**
	* Adds the edition gallery to the database. Also notifies the appropriate model listeners.
	*
	* @param editionGallery the edition gallery
	* @return the edition gallery that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public EditionGallery addEditionGallery(EditionGallery editionGallery);

	/**
	* Creates a new edition gallery with the primary key. Does not add the edition gallery to the database.
	*
	* @param galleryId the primary key for the new edition gallery
	* @return the new edition gallery
	*/
	public EditionGallery createEditionGallery(long galleryId);

	/**
	* Deletes the edition gallery from the database. Also notifies the appropriate model listeners.
	*
	* @param editionGallery the edition gallery
	* @return the edition gallery that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public EditionGallery deleteEditionGallery(EditionGallery editionGallery);

	/**
	* Deletes the edition gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery that was removed
	* @throws PortalException if a edition gallery with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public EditionGallery deleteEditionGallery(long galleryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EditionGallery fetchEditionGallery(long galleryId);

	/**
	* Returns the edition gallery matching the UUID and group.
	*
	* @param uuid the edition gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EditionGallery fetchEditionGalleryByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the edition gallery with the primary key.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery
	* @throws PortalException if a edition gallery with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EditionGallery getEditionGallery(long galleryId)
		throws PortalException;

	/**
	* Returns the edition gallery matching the UUID and group.
	*
	* @param uuid the edition gallery's UUID
	* @param groupId the primary key of the group
	* @return the matching edition gallery
	* @throws PortalException if a matching edition gallery could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EditionGallery getEditionGalleryByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Delete an Edition Gallery
	*
	* @param galleryId
	The ID of the edition gallery to delete
	* @return The deleted EditionGallery
	* @throws PortalException
	*/
	public EditionGallery removeGallery(long galleryId)
		throws PortalException;

	/**
	* Updates the edition gallery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param editionGallery the edition gallery
	* @return the edition gallery that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public EditionGallery updateEditionGallery(EditionGallery editionGallery);

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
	public EditionGallery updateEditionGallery(EditionGallery editionGallery,
		ServiceContext sc) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEditionEditionGalleriesCount(long editionId);

	/**
	* Returns the number of edition galleries.
	*
	* @return the number of edition galleries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEditionGalleriesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	public List<EditionGallery> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EditionGallery> getEditionEditionGalleries(long editionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EditionGallery> getEditionEditionGalleries(long editionId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EditionGallery> getEditionEditionGalleries(long editionId,
		int start, int end, OrderByComparator<EditionGallery> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EditionGallery> getEditionGalleries(int start, int end);

	/**
	* Returns all the edition galleries matching the UUID and company.
	*
	* @param uuid the UUID of the edition galleries
	* @param companyId the primary key of the company
	* @return the matching edition galleries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EditionGallery> getEditionGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EditionGallery> getEditionGalleriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public long findByKeywordCount(java.lang.String keyword, long groupId);

	/**
	* Returns the editionIds of the editions associated with the edition gallery.
	*
	* @param galleryId the galleryId of the edition gallery
	* @return long[] the editionIds of editions associated with the edition gallery
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getEditionPrimaryKeys(long galleryId);

	public void addEditionEditionGalleries(long editionId,
		List<EditionGallery> editionGalleries);

	public void addEditionEditionGalleries(long editionId, long[] galleryIds);

	public void addEditionEditionGallery(long editionId,
		EditionGallery editionGallery);

	public void addEditionEditionGallery(long editionId, long galleryId);

	public void changeStatus(EditionGallery editionGallery, boolean status)
		throws PortalException;

	public void clearEditionEditionGalleries(long editionId);

	public void deleteEditionEditionGalleries(long editionId,
		List<EditionGallery> editionGalleries);

	public void deleteEditionEditionGalleries(long editionId, long[] galleryIds);

	public void deleteEditionEditionGallery(long editionId,
		EditionGallery editionGallery);

	public void deleteEditionEditionGallery(long editionId, long galleryId);

	public void setEditionEditionGalleries(long editionId, long[] galleryIds);
}