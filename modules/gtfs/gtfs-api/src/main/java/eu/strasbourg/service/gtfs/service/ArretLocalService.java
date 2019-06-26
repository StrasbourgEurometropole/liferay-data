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

package eu.strasbourg.service.gtfs.service;

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

import eu.strasbourg.service.gtfs.model.Arret;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Arret. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see ArretLocalServiceUtil
 * @see eu.strasbourg.service.gtfs.service.base.ArretLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.ArretLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ArretLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArretLocalServiceUtil} to access the arret local service. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.ArretLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the arret to the database. Also notifies the appropriate model listeners.
	*
	* @param arret the arret
	* @return the arret that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Arret addArret(Arret arret);

	/**
	* Crée une entree avec une PK, non ajouté à la base de donnée
	*/
	public Arret createArret(ServiceContext sc) throws PortalException;

	/**
	* Creates a new arret with the primary key. Does not add the arret to the database.
	*
	* @param arretId the primary key for the new arret
	* @return the new arret
	*/
	public Arret createArret(long arretId);

	/**
	* Deletes the arret from the database. Also notifies the appropriate model listeners.
	*
	* @param arret the arret
	* @return the arret that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Arret deleteArret(Arret arret);

	/**
	* Deletes the arret with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param arretId the primary key of the arret
	* @return the arret that was removed
	* @throws PortalException if a arret with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Arret deleteArret(long arretId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Arret fetchArret(long arretId);

	/**
	* Returns the arret matching the UUID and group.
	*
	* @param uuid the arret's UUID
	* @param groupId the primary key of the group
	* @return the matching arret, or <code>null</code> if a matching arret could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Arret fetchArretByUuidAndGroupId(java.lang.String uuid, long groupId);

	/**
	* Returns the arret with the primary key.
	*
	* @param arretId the primary key of the arret
	* @return the arret
	* @throws PortalException if a arret with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Arret getArret(long arretId) throws PortalException;

	/**
	* Returns the arret matching the UUID and group.
	*
	* @param uuid the arret's UUID
	* @param groupId the primary key of the group
	* @return the matching arret
	* @throws PortalException if a matching arret could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Arret getArretByUuidAndGroupId(java.lang.String uuid, long groupId)
		throws PortalException;

	/**
	* Retourne un arret via son stopId CTS
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Arret getByStopId(java.lang.String stopId);

	/**
	* Supprime l'entree
	*/
	public Arret removeArret(long arretId) throws PortalException;

	/**
	* Updates the arret in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param arret the arret
	* @return the arret that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Arret updateArret(Arret arret);

	/**
	* Met à jour une entree et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public Arret updateArret(Arret arret, ServiceContext sc)
		throws PortalException;

	/**
	* Met à jour le statut de l'entree par le framework workflow
	*/
	public Arret updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Returns the number of arrets.
	*
	* @return the number of arrets
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArretsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ArretModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ArretModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<Arret> findByKeyword(java.lang.String keyword, long groupId,
		int start, int end);

	/**
	* Returns a range of all the arrets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ArretModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of arrets
	* @param end the upper bound of the range of arrets (not inclusive)
	* @return the range of arrets
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Arret> getArrets(int start, int end);

	/**
	* Returns all the arrets matching the UUID and company.
	*
	* @param uuid the UUID of the arrets
	* @param companyId the primary key of the company
	* @return the matching arrets, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Arret> getArretsByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of arrets matching the UUID and company.
	*
	* @param uuid the UUID of the arrets
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of arrets
	* @param end the upper bound of the range of arrets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching arrets, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Arret> getArretsByUuidAndCompanyId(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Arret> orderByComparator);

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entree
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les entrees d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Arret> getByGroupId(long groupId);

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
}