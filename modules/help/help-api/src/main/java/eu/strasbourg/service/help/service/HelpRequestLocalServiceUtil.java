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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for HelpRequest. This utility wraps
 * <code>eu.strasbourg.service.help.service.impl.HelpRequestLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequestLocalService
 * @generated
 */
@ProviderType
public class HelpRequestLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.help.service.impl.HelpRequestLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the help request to the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequest the help request
	 * @return the help request that was added
	 */
	public static eu.strasbourg.service.help.model.HelpRequest addHelpRequest(
		eu.strasbourg.service.help.model.HelpRequest helpRequest) {

		return getService().addHelpRequest(helpRequest);
	}

	/**
	 * Creates a new help request with the primary key. Does not add the help request to the database.
	 *
	 * @param helpRequestId the primary key for the new help request
	 * @return the new help request
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
		createHelpRequest(long helpRequestId) {

		return getService().createHelpRequest(helpRequestId);
	}

	/**
	 * Methode de creation d'une demande d'aide
	 *
	 * @param sc Le contexte de la requete.
	 * @return L'aide cree.
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
		createHelpRequest(com.liferay.portal.kernel.service.ServiceContext sc) {

		return getService().createHelpRequest(sc);
	}

	/**
	 * Deletes the help request from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequest the help request
	 * @return the help request that was removed
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
		deleteHelpRequest(
			eu.strasbourg.service.help.model.HelpRequest helpRequest) {

		return getService().deleteHelpRequest(helpRequest);
	}

	/**
	 * Deletes the help request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request that was removed
	 * @throws PortalException if a help request with the primary key could not be found
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
			deleteHelpRequest(long helpRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteHelpRequest(helpRequestId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static eu.strasbourg.service.help.model.HelpRequest fetchHelpRequest(
		long helpRequestId) {

		return getService().fetchHelpRequest(helpRequestId);
	}

	/**
	 * Returns the help request matching the UUID and group.
	 *
	 * @param uuid the help request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
		fetchHelpRequestByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchHelpRequestByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Retourne la liste des demands d'aides pour une proposition
	 */
	public static java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getByHelpProposalId(long helpRequestId) {

		return getService().getByHelpProposalId(helpRequestId);
	}

	/**
	 * Retourne les demandes d'aides pour un utilisateur
	 */
	public static java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getByPublikId(String publikId) {

		return getService().getByPublikId(publikId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	 * Returns the help request with the primary key.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request
	 * @throws PortalException if a help request with the primary key could not be found
	 */
	public static eu.strasbourg.service.help.model.HelpRequest getHelpRequest(
			long helpRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getHelpRequest(helpRequestId);
	}

	/**
	 * Returns the help request matching the UUID and group.
	 *
	 * @param uuid the help request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help request
	 * @throws PortalException if a matching help request could not be found
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
			getHelpRequestByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getHelpRequestByUuidAndGroupId(uuid, groupId);
	}

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
	public static java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getHelpRequests(int start, int end) {

		return getService().getHelpRequests(start, end);
	}

	/**
	 * Returns all the help requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the help requests
	 * @param companyId the primary key of the company
	 * @return the matching help requests, or an empty list if no matches were found
	 */
	public static java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getHelpRequestsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getHelpRequestsByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getHelpRequestsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.help.model.HelpRequest>
					orderByComparator) {

		return getService().getHelpRequestsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of help requests.
	 *
	 * @return the number of help requests
	 */
	public static int getHelpRequestsCount() {
		return getService().getHelpRequestsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprimer une demande d'aide
	 *
	 * @param helpRequestId Id de la demande d'aide
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
		removeHelpRequest(long helpRequestId) {

		return getService().removeHelpRequest(helpRequestId);
	}

	/**
	 * Updates the help request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequest the help request
	 * @return the help request that was updated
	 */
	public static eu.strasbourg.service.help.model.HelpRequest
		updateHelpRequest(
			eu.strasbourg.service.help.model.HelpRequest helpRequest) {

		return getService().updateHelpRequest(helpRequest);
	}

	public static HelpRequestLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<HelpRequestLocalService, HelpRequestLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HelpRequestLocalService.class);

		ServiceTracker<HelpRequestLocalService, HelpRequestLocalService>
			serviceTracker =
				new ServiceTracker
					<HelpRequestLocalService, HelpRequestLocalService>(
						bundle.getBundleContext(),
						HelpRequestLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}