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

package eu.strasbourg.service.help.service;

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

import eu.strasbourg.service.help.model.HelpRequest;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for HelpRequest. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequestLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface HelpRequestLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpRequestLocalServiceUtil} to access the help request local service. Add custom service methods to <code>eu.strasbourg.service.help.service.impl.HelpRequestLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the help request to the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequest the help request
	 * @return the help request that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public HelpRequest addHelpRequest(HelpRequest helpRequest);

	/**
	 * Creates a new help request with the primary key. Does not add the help request to the database.
	 *
	 * @param helpRequestId the primary key for the new help request
	 * @return the new help request
	 */
	@Transactional(enabled = false)
	public HelpRequest createHelpRequest(long helpRequestId);

	/**
	 * Methode de creation d'une demande d'aide
	 *
	 * @param sc Le contexte de la requete.
	 * @return L'aide cree.
	 */
	public HelpRequest createHelpRequest(ServiceContext sc);

	/**
	 * Deletes the help request from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequest the help request
	 * @return the help request that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public HelpRequest deleteHelpRequest(HelpRequest helpRequest);

	/**
	 * Deletes the help request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request that was removed
	 * @throws PortalException if a help request with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public HelpRequest deleteHelpRequest(long helpRequestId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HelpRequest fetchHelpRequest(long helpRequestId);

	/**
	 * Returns the help request matching the UUID and group.
	 *
	 * @param uuid the help request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help request, or <code>null</code> if a matching help request could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HelpRequest fetchHelpRequestByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Retourne la liste des demands d'aides pour une proposition
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HelpRequest> getByHelpProposalId(long helpRequestId);

	/**
	 * Retourne les demandes d'aides pour un utilisateur
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HelpRequest> getByPublikId(String publikId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	/**
	 * Returns the help request with the primary key.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request
	 * @throws PortalException if a help request with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HelpRequest getHelpRequest(long helpRequestId)
		throws PortalException;

	/**
	 * Returns the help request matching the UUID and group.
	 *
	 * @param uuid the help request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help request
	 * @throws PortalException if a matching help request could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HelpRequest getHelpRequestByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of help requests
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HelpRequest> getHelpRequests(int start, int end);

	/**
	 * Returns all the help requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the help requests
	 * @param companyId the primary key of the company
	 * @return the matching help requests, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HelpRequest> getHelpRequestsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of help requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the help requests
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching help requests, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HelpRequest> getHelpRequestsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator);

	/**
	 * Returns the number of help requests.
	 *
	 * @return the number of help requests
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getHelpRequestsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Supprimer une demande d'aide
	 *
	 * @param helpRequestId Id de la demande d'aide
	 */
	public HelpRequest removeHelpRequest(long helpRequestId);

	/**
	 * Updates the help request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequest the help request
	 * @return the help request that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public HelpRequest updateHelpRequest(HelpRequest helpRequest);

}