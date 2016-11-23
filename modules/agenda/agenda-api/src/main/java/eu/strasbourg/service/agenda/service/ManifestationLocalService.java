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

package eu.strasbourg.service.agenda.service;

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

import eu.strasbourg.service.agenda.model.Manifestation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Manifestation. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see ManifestationLocalServiceUtil
 * @see eu.strasbourg.service.agenda.service.base.ManifestationLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.ManifestationLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ManifestationLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ManifestationLocalServiceUtil} to access the manifestation local service. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.ManifestationLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasEventManifestation(long eventId, long manifestationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasEventManifestations(long eventId);

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
	* Adds the manifestation to the database. Also notifies the appropriate model listeners.
	*
	* @param manifestation the manifestation
	* @return the manifestation that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Manifestation addManifestation(Manifestation manifestation);

	/**
	* Crée un lien vide avec une PK, non ajouté à la base de donnée
	*/
	public Manifestation createManifestation(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new manifestation with the primary key. Does not add the manifestation to the database.
	*
	* @param manifestationId the primary key for the new manifestation
	* @return the new manifestation
	*/
	public Manifestation createManifestation(long manifestationId);

	/**
	* Deletes the manifestation from the database. Also notifies the appropriate model listeners.
	*
	* @param manifestation the manifestation
	* @return the manifestation that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Manifestation deleteManifestation(Manifestation manifestation);

	/**
	* Deletes the manifestation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param manifestationId the primary key of the manifestation
	* @return the manifestation that was removed
	* @throws PortalException if a manifestation with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Manifestation deleteManifestation(long manifestationId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Manifestation fetchManifestation(long manifestationId);

	/**
	* Returns the manifestation matching the UUID and group.
	*
	* @param uuid the manifestation's UUID
	* @param groupId the primary key of the group
	* @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Manifestation fetchManifestationByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the manifestation with the primary key.
	*
	* @param manifestationId the primary key of the manifestation
	* @return the manifestation
	* @throws PortalException if a manifestation with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Manifestation getManifestation(long manifestationId)
		throws PortalException;

	/**
	* Returns the manifestation matching the UUID and group.
	*
	* @param uuid the manifestation's UUID
	* @param groupId the primary key of the group
	* @return the matching manifestation
	* @throws PortalException if a matching manifestation could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Manifestation getManifestationByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Delete an Event Manifestation
	*
	* @param manifestationId
	The ID of the event manifestation to delete
	* @return The deleted Manifestation
	* @throws PortalException
	*/
	public Manifestation removeManifestation(long manifestationId)
		throws PortalException;

	/**
	* Updates the manifestation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param manifestation the manifestation
	* @return the manifestation that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Manifestation updateManifestation(Manifestation manifestation);

	/**
	* Met à jour un lien et l'enregistre en base de données
	*/
	public Manifestation updateManifestation(Manifestation manifestation,
		ServiceContext sc) throws PortalException;

	/**
	* Met à jour le statut de la galerie par le framework workflow
	*/
	public Manifestation updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEventManifestationsCount(long eventId);

	/**
	* Returns the number of manifestations.
	*
	* @return the number of manifestations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getManifestationsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<Manifestation> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les galeries éditions d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Manifestation> getByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Manifestation> getEventManifestations(long eventId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Manifestation> getEventManifestations(long eventId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Manifestation> getEventManifestations(long eventId, int start,
		int end, OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns a range of all the manifestations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of manifestations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Manifestation> getManifestations(int start, int end);

	/**
	* Returns all the manifestations matching the UUID and company.
	*
	* @param uuid the UUID of the manifestations
	* @param companyId the primary key of the company
	* @return the matching manifestations, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Manifestation> getManifestationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of manifestations matching the UUID and company.
	*
	* @param uuid the UUID of the manifestations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching manifestations, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Manifestation> getManifestationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<Manifestation> orderByComparator);

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
	* Returns the eventIds of the events associated with the manifestation.
	*
	* @param manifestationId the manifestationId of the manifestation
	* @return long[] the eventIds of events associated with the manifestation
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getEventPrimaryKeys(long manifestationId);

	public void addEventManifestation(long eventId, Manifestation manifestation);

	public void addEventManifestation(long eventId, long manifestationId);

	public void addEventManifestations(long eventId,
		List<Manifestation> manifestations);

	public void addEventManifestations(long eventId, long[] manifestationIds);

	public void clearEventManifestations(long eventId);

	public void deleteEventManifestation(long eventId,
		Manifestation manifestation);

	public void deleteEventManifestation(long eventId, long manifestationId);

	public void deleteEventManifestations(long eventId,
		List<Manifestation> manifestations);

	public void deleteEventManifestations(long eventId, long[] manifestationIds);

	public void setEventManifestations(long eventId, long[] manifestationIds);

	/**
	* Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	*/
	public void updateStatus(Manifestation manifestation, int status)
		throws PortalException;
}