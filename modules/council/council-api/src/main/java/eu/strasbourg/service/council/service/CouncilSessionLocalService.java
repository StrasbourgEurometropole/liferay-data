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

package eu.strasbourg.service.council.service;

import aQute.bnd.annotation.ProviderType;

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

import eu.strasbourg.service.council.model.CouncilSession;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for CouncilSession. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CouncilSessionLocalServiceUtil
 * @see eu.strasbourg.service.council.service.base.CouncilSessionLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.impl.CouncilSessionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CouncilSessionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CouncilSessionLocalServiceUtil} to access the council session local service. Add custom service methods to {@link eu.strasbourg.service.council.service.impl.CouncilSessionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Si la date avec l'ID donné est déjà utilisé par une autre session
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isDateAlreadyUsed(Date date, long councilSessionId);

	/**
	* Si le titre avec l'ID donné est déjà utilisé par une autre session du même type de conseil
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isTitleAlreadyUsedInCouncilTypeId(java.lang.String title,
		long councilSessionId, long typeId);

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
	* Adds the council session to the database. Also notifies the appropriate model listeners.
	*
	* @param councilSession the council session
	* @return the council session that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CouncilSession addCouncilSession(CouncilSession councilSession);

	/**
	* Crée une entité vide avec une PK, non ajouté à la base de donnée
	*/
	public CouncilSession createCouncilSession(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new council session with the primary key. Does not add the council session to the database.
	*
	* @param councilSessionId the primary key for the new council session
	* @return the new council session
	*/
	public CouncilSession createCouncilSession(long councilSessionId);

	/**
	* Deletes the council session from the database. Also notifies the appropriate model listeners.
	*
	* @param councilSession the council session
	* @return the council session that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CouncilSession deleteCouncilSession(CouncilSession councilSession);

	/**
	* Deletes the council session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session that was removed
	* @throws PortalException if a council session with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CouncilSession deleteCouncilSession(long councilSessionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CouncilSession fetchCouncilSession(long councilSessionId);

	/**
	* Returns the council session matching the UUID and group.
	*
	* @param uuid the council session's UUID
	* @param groupId the primary key of the group
	* @return the matching council session, or <code>null</code> if a matching council session could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CouncilSession fetchCouncilSessionByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the council session with the primary key.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session
	* @throws PortalException if a council session with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CouncilSession getCouncilSession(long councilSessionId)
		throws PortalException;

	/**
	* Returns the council session matching the UUID and group.
	*
	* @param uuid the council session's UUID
	* @param groupId the primary key of the group
	* @return the matching council session
	* @throws PortalException if a matching council session could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CouncilSession getCouncilSessionByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime une entité
	*/
	public CouncilSession removeCouncilSession(long councilSessionId)
		throws PortalException;

	/**
	* Updates the council session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param councilSession the council session
	* @return the council session that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CouncilSession updateCouncilSession(CouncilSession councilSession);

	/**
	* Met à jour une entité et l'enregistre en base de données
	*/
	public CouncilSession updateCouncilSession(CouncilSession councilSession,
		ServiceContext sc) throws PortalException;

	/**
	* Met à jour le statut de l'entité par le framework workflow
	*/
	public CouncilSession updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Returns the number of council sessions.
	*
	* @return the number of council sessions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCouncilSessionsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Recherche par Date de CouncilSession
	*/
	public List<CouncilSession> findByDate(Date date);

	/**
	* Recherche par titre de CouncilSession
	*/
	public List<CouncilSession> findByTitre(java.lang.String title);

	/**
	* Recherche par type de CouncilSession
	*/
	public List<CouncilSession> findByTypeId(long typeId);

	/**
	* Returns a range of all the council sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of council sessions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CouncilSession> getCouncilSessions(int start, int end);

	/**
	* Returns all the council sessions matching the UUID and company.
	*
	* @param uuid the UUID of the council sessions
	* @param companyId the primary key of the company
	* @return the matching council sessions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CouncilSession> getCouncilSessionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of council sessions matching the UUID and company.
	*
	* @param uuid the UUID of the council sessions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching council sessions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CouncilSession> getCouncilSessionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Retourne les conseils dont la date est égale ou supérieure à celle passée en paramètre
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CouncilSession> getFutureCouncilSessions(Date date);

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
	* Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	*/
	public void updateStatus(CouncilSession councilSession, int status)
		throws PortalException;
}