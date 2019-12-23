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

package eu.strasbourg.service.place.service;

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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for GoogleMyBusinessHistoric. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Angelique Zunino Champougny
 * @see GoogleMyBusinessHistoricLocalServiceUtil
 * @see eu.strasbourg.service.place.service.base.GoogleMyBusinessHistoricLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.impl.GoogleMyBusinessHistoricLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface GoogleMyBusinessHistoricLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GoogleMyBusinessHistoricLocalServiceUtil} to access the google my business historic local service. Add custom service methods to {@link eu.strasbourg.service.place.service.impl.GoogleMyBusinessHistoricLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
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
	* Adds the google my business historic to the database. Also notifies the appropriate model listeners.
	*
	* @param googleMyBusinessHistoric the google my business historic
	* @return the google my business historic that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public GoogleMyBusinessHistoric addGoogleMyBusinessHistoric(
		GoogleMyBusinessHistoric googleMyBusinessHistoric);

	/**
	* Crée une entree google mybusiness vide avec une PK, non ajouté à la base de donnée
	*/
	public GoogleMyBusinessHistoric createGoogleMyBusinessHistoric(
		ServiceContext sc) throws PortalException;

	/**
	* Creates a new google my business historic with the primary key. Does not add the google my business historic to the database.
	*
	* @param googleMyBusinessHistoricId the primary key for the new google my business historic
	* @return the new google my business historic
	*/
	public GoogleMyBusinessHistoric createGoogleMyBusinessHistoric(
		long googleMyBusinessHistoricId);

	/**
	* Deletes the google my business historic from the database. Also notifies the appropriate model listeners.
	*
	* @param googleMyBusinessHistoric the google my business historic
	* @return the google my business historic that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public GoogleMyBusinessHistoric deleteGoogleMyBusinessHistoric(
		GoogleMyBusinessHistoric googleMyBusinessHistoric);

	/**
	* Deletes the google my business historic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param googleMyBusinessHistoricId the primary key of the google my business historic
	* @return the google my business historic that was removed
	* @throws PortalException if a google my business historic with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public GoogleMyBusinessHistoric deleteGoogleMyBusinessHistoric(
		long googleMyBusinessHistoricId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GoogleMyBusinessHistoric fetchGoogleMyBusinessHistoric(
		long googleMyBusinessHistoricId);

	/**
	* Returns the google my business historic matching the UUID and group.
	*
	* @param uuid the google my business historic's UUID
	* @param groupId the primary key of the group
	* @return the matching google my business historic, or <code>null</code> if a matching google my business historic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GoogleMyBusinessHistoric fetchGoogleMyBusinessHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the google my business historic with the primary key.
	*
	* @param googleMyBusinessHistoricId the primary key of the google my business historic
	* @return the google my business historic
	* @throws PortalException if a google my business historic with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GoogleMyBusinessHistoric getGoogleMyBusinessHistoric(
		long googleMyBusinessHistoricId) throws PortalException;

	/**
	* Returns the google my business historic matching the UUID and group.
	*
	* @param uuid the google my business historic's UUID
	* @param groupId the primary key of the group
	* @return the matching google my business historic
	* @throws PortalException if a matching google my business historic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GoogleMyBusinessHistoric getGoogleMyBusinessHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime l'entree google mybusiness
	*/
	public GoogleMyBusinessHistoric removeGoogleMyBusinessHistoric(
		long googleMyBusinessHistoricId) throws PortalException;

	/**
	* Updates the google my business historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param googleMyBusinessHistoric the google my business historic
	* @return the google my business historic that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public GoogleMyBusinessHistoric updateGoogleMyBusinessHistoric(
		GoogleMyBusinessHistoric googleMyBusinessHistoric);

	/**
	* Met à jour une entree google mybusiness et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public GoogleMyBusinessHistoric updateGoogleMyBusinessHistoric(
		GoogleMyBusinessHistoric googleMyBusinessHistoric, ServiceContext sc)
		throws PortalException;

	/**
	* Met à jour le statut de l'entree google mybusiness par le framework workflow
	*/
	public GoogleMyBusinessHistoric updateStatus(long userId, long entryId,
		int status, ServiceContext sc,
		Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Returns the number of google my business historics.
	*
	* @return the number of google my business historics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGoogleMyBusinessHistoricsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Recherche par mot clés
	*/
	public List<GoogleMyBusinessHistoric> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end);

	/**
	* Renvoie la liste des vocabulaires rattachés à google mybusiness
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne tous les google mybusiness d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GoogleMyBusinessHistoric> getByGroupId(long groupId);

	/**
	* Returns a range of all the google my business historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.GoogleMyBusinessHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of google my business historics
	* @param end the upper bound of the range of google my business historics (not inclusive)
	* @return the range of google my business historics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GoogleMyBusinessHistoric> getGoogleMyBusinessHistorics(
		int start, int end);

	/**
	* Returns all the google my business historics matching the UUID and company.
	*
	* @param uuid the UUID of the google my business historics
	* @param companyId the primary key of the company
	* @return the matching google my business historics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GoogleMyBusinessHistoric> getGoogleMyBusinessHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of google my business historics matching the UUID and company.
	*
	* @param uuid the UUID of the google my business historics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of google my business historics
	* @param end the upper bound of the range of google my business historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching google my business historics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GoogleMyBusinessHistoric> getGoogleMyBusinessHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<GoogleMyBusinessHistoric> orderByComparator);

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
	* Recherche par mot clés (compte)
	*/
	public long findByKeywordCount(java.lang.String keyword, long groupId);

	/**
	* Effectue la synchronisation
	*/
	public void doSynchronisation(ServiceContext sc,
		GoogleMyBusinessHistoric googleMyBusinessHistoric);

	/**
	* Met à jour le statut de l'entree google mybusiness "manuellement" (pas via le workflow)
	*/
	public void updateStatus(
		GoogleMyBusinessHistoric googleMyBusinessHistoric, int status)
		throws PortalException;
}