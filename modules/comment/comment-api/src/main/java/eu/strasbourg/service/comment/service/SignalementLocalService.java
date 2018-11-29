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

package eu.strasbourg.service.comment.service;

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

import eu.strasbourg.service.comment.model.Signalement;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Signalement. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Romain Vergnais
 * @see SignalementLocalServiceUtil
 * @see eu.strasbourg.service.comment.service.base.SignalementLocalServiceBaseImpl
 * @see eu.strasbourg.service.comment.service.impl.SignalementLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SignalementLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SignalementLocalServiceUtil} to access the signalement local service. Add custom service methods to {@link eu.strasbourg.service.comment.service.impl.SignalementLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the signalement to the database. Also notifies the appropriate model listeners.
	*
	* @param signalement the signalement
	* @return the signalement that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Signalement addSignalement(Signalement signalement);

	/**
	* Permet de creer un signalement sans le persister.
	*
	* @param sc le serviceContext
	* @return le signalement.
	* @throws PortalException l'exception.
	*/
	public Signalement createSignalement(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new signalement with the primary key. Does not add the signalement to the database.
	*
	* @param signalementId the primary key for the new signalement
	* @return the new signalement
	*/
	public Signalement createSignalement(long signalementId);

	/**
	* Deletes the signalement from the database. Also notifies the appropriate model listeners.
	*
	* @param signalement the signalement
	* @return the signalement that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Signalement deleteSignalement(Signalement signalement);

	/**
	* Deletes the signalement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signalementId the primary key of the signalement
	* @return the signalement that was removed
	* @throws PortalException if a signalement with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Signalement deleteSignalement(long signalementId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signalement fetchSignalement(long signalementId);

	/**
	* Returns the signalement matching the UUID and group.
	*
	* @param uuid the signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signalement fetchSignalementByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the signalement with the primary key.
	*
	* @param signalementId the primary key of the signalement
	* @return the signalement
	* @throws PortalException if a signalement with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signalement getSignalement(long signalementId)
		throws PortalException;

	/**
	* Returns the signalement matching the UUID and group.
	*
	* @param uuid the signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching signalement
	* @throws PortalException if a matching signalement could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signalement getSignalementByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the signalement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param signalement the signalement
	* @return the signalement that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Signalement updateSignalement(Signalement signalement);

	public Signalement updateSignalement(Signalement signalement,
		ServiceContext sc) throws PortalException;

	/**
	* Returns the number of signalements.
	*
	* @return the number of signalements
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSignalementsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.comment.model.impl.SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.comment.model.impl.SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<Signalement> findByCommentId(long commentId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signalement> getByGroupId(long groupId);

	/**
	* Returns a range of all the signalements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.comment.model.impl.SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @return the range of signalements
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signalement> getSignalements(int start, int end);

	/**
	* Returns all the signalements matching the UUID and company.
	*
	* @param uuid the UUID of the signalements
	* @param companyId the primary key of the company
	* @return the matching signalements, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signalement> getSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of signalements matching the UUID and company.
	*
	* @param uuid the UUID of the signalements
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching signalements, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signalement> getSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<Signalement> orderByComparator);

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
}