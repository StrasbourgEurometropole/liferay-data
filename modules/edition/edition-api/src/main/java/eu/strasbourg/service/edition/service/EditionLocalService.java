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

import eu.strasbourg.service.edition.model.Edition;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Edition. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see EditionLocalServiceUtil
 * @see eu.strasbourg.service.edition.service.base.EditionLocalServiceBaseImpl
 * @see eu.strasbourg.service.edition.service.impl.EditionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EditionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EditionLocalServiceUtil} to access the edition local service. Add custom service methods to {@link eu.strasbourg.service.edition.service.impl.EditionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasEditionGalleryEdition(long galleryId, long editionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasEditionGalleryEditions(long galleryId);

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

	/**
	* Lance une recherche selon le searchContext
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

	/**
	* Adds the edition to the database. Also notifies the appropriate model listeners.
	*
	* @param edition the edition
	* @return the edition that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Edition addEdition(Edition edition);

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	public Edition createEdition(ServiceContext sc) throws PortalException;

	/**
	* Creates a new edition with the primary key. Does not add the edition to the database.
	*
	* @param editionId the primary key for the new edition
	* @return the new edition
	*/
	public Edition createEdition(long editionId);

	/**
	* Deletes the edition from the database. Also notifies the appropriate model listeners.
	*
	* @param edition the edition
	* @return the edition that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Edition deleteEdition(Edition edition);

	/**
	* Deletes the edition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param editionId the primary key of the edition
	* @return the edition that was removed
	* @throws PortalException if a edition with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Edition deleteEdition(long editionId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Edition fetchEdition(long editionId);

	/**
	* Returns the edition matching the UUID and group.
	*
	* @param uuid the edition's UUID
	* @param groupId the primary key of the group
	* @return the matching edition, or <code>null</code> if a matching edition could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Edition fetchEditionByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the edition with the primary key.
	*
	* @param editionId the primary key of the edition
	* @return the edition
	* @throws PortalException if a edition with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Edition getEdition(long editionId) throws PortalException;

	/**
	* Returns the edition matching the UUID and group.
	*
	* @param uuid the edition's UUID
	* @param groupId the primary key of the group
	* @return the matching edition
	* @throws PortalException if a matching edition could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Edition getEditionByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Delete an Edition
	*
	* @param editionId
	The ID of the edition to delete
	* @return The deleted Edition
	* @throws PortalException
	*/
	public Edition removeEdition(long editionId) throws PortalException;

	/**
	* Updates the edition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param edition the edition
	* @return the edition that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Edition updateEdition(Edition edition);

	/**
	* Met à jour une édition et l'enregistre en base de données
	*/
	public Edition updateEdition(Edition edition, ServiceContext sc)
		throws PortalException;

	/**
	* Met à jour le statut de l'édition par le framework workflow
	*/
	public Edition updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEditionGalleryEditionsCount(long galleryId);

	/**
	* Returns the number of editions.
	*
	* @return the number of editions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEditionsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.edition.model.impl.EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Lance une recherche par mots-clés
	*/
	public List<Edition> findByKeyword(java.lang.String keyword, long groupId,
		int start, int end);

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité Edition
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Edition> getByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Edition> getEditionGalleryEditions(long galleryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Edition> getEditionGalleryEditions(long galleryId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Edition> getEditionGalleryEditions(long galleryId, int start,
		int end, OrderByComparator<Edition> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Edition> getEditions(int start, int end);

	/**
	* Returns all the editions matching the UUID and company.
	*
	* @param uuid the UUID of the editions
	* @param companyId the primary key of the company
	* @return the matching editions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Edition> getEditionsByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Edition> getEditionsByUuidAndCompanyId(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Edition> orderByComparator);

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

	/**
	* Compte de la recherche par mots-clés
	*/
	public long findByKeywordCount(java.lang.String keyword, long groupId);

	/**
	* Returns the galleryIds of the edition galleries associated with the edition.
	*
	* @param editionId the editionId of the edition
	* @return long[] the galleryIds of edition galleries associated with the edition
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getEditionGalleryPrimaryKeys(long editionId);

	public void addEditionGalleryEdition(long galleryId, Edition edition);

	public void addEditionGalleryEdition(long galleryId, long editionId);

	public void addEditionGalleryEditions(long galleryId, List<Edition> editions);

	public void addEditionGalleryEditions(long galleryId, long[] editionIds);

	public void clearEditionGalleryEditions(long galleryId);

	public void deleteEditionGalleryEdition(long galleryId, Edition edition);

	public void deleteEditionGalleryEdition(long galleryId, long editionId);

	public void deleteEditionGalleryEditions(long galleryId,
		List<Edition> editions);

	public void deleteEditionGalleryEditions(long galleryId, long[] editionIds);

	public void setEditionGalleryEditions(long galleryId, long[] editionIds);

	/**
	* Met à jour le statut de l'édition "manuellement" (pas via le workflow)
	*/
	public void updateStatus(Edition edition, int status)
		throws PortalException;
}