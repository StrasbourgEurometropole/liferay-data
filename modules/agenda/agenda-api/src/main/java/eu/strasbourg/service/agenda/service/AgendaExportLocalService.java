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

import eu.strasbourg.service.agenda.model.AgendaExport;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for AgendaExport. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see AgendaExportLocalServiceUtil
 * @see eu.strasbourg.service.agenda.service.base.AgendaExportLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.AgendaExportLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AgendaExportLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AgendaExportLocalServiceUtil} to access the agenda export local service. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.AgendaExportLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Lance une recherche selon le searchContext
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

	/**
	* Adds the agenda export to the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AgendaExport addAgendaExport(AgendaExport agendaExport);

	public AgendaExport cloneAgendaExport(ServiceContext sc,
		AgendaExport agendaToCopy) throws PortalException;

	/**
	* Crée un Agenda Export vide avec une PK, non ajouté à la base de donnée
	*/
	public AgendaExport createAgendaExport(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new agenda export with the primary key. Does not add the agenda export to the database.
	*
	* @param agendaExportId the primary key for the new agenda export
	* @return the new agenda export
	*/
	public AgendaExport createAgendaExport(long agendaExportId);

	/**
	* Deletes the agenda export from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public AgendaExport deleteAgendaExport(AgendaExport agendaExport);

	/**
	* Deletes the agenda export with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export that was removed
	* @throws PortalException if a agenda export with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AgendaExport deleteAgendaExport(long agendaExportId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AgendaExport fetchAgendaExport(long agendaExportId);

	/**
	* Returns the agenda export matching the UUID and group.
	*
	* @param uuid the agenda export's UUID
	* @param groupId the primary key of the group
	* @return the matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AgendaExport fetchAgendaExportByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the agenda export with the primary key.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export
	* @throws PortalException if a agenda export with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AgendaExport getAgendaExport(long agendaExportId)
		throws PortalException;

	/**
	* Returns the agenda export matching the UUID and group.
	*
	* @param uuid the agenda export's UUID
	* @param groupId the primary key of the group
	* @return the matching agenda export
	* @throws PortalException if a matching agenda export could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AgendaExport getAgendaExportByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Supprime un AgendaExport
	*/
	public AgendaExport removeAgendaExport(long agendaExportId)
		throws PortalException;

	/**
	* Updates the agenda export in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AgendaExport updateAgendaExport(AgendaExport agendaExport);

	/**
	* Met à jour un Agenda Export et l'enregistre en base de données
	*/
	public AgendaExport updateAgendaExport(AgendaExport agendaExport,
		ServiceContext sc) throws PortalException;

	/**
	* Met à jour le statut de l'AgendaExport par le framework workflow
	*/
	public AgendaExport updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Returns the number of agenda exports.
	*
	* @return the number of agenda exports
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAgendaExportsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<AgendaExport> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	/**
	* Returns a range of all the agenda exports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of agenda exports
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AgendaExport> getAgendaExports(int start, int end);

	/**
	* Returns all the agenda exports matching the UUID and company.
	*
	* @param uuid the UUID of the agenda exports
	* @param companyId the primary key of the company
	* @return the matching agenda exports, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AgendaExport> getAgendaExportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of agenda exports matching the UUID and company.
	*
	* @param uuid the UUID of the agenda exports
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching agenda exports, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AgendaExport> getAgendaExportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne tous les AGendaExport d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AgendaExport> getByGroupId(long groupId);

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
	* Met à jour le statut de l'AgendaExport "manuellement" (pas via le workflow)
	*/
	public void updateStatus(AgendaExport agendaExport, int status)
		throws PortalException;
}