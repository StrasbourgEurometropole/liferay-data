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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CampaignEventStatus. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.CampaignEventStatusLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see CampaignEventStatusLocalService
 * @see eu.strasbourg.service.agenda.service.base.CampaignEventStatusLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.CampaignEventStatusLocalServiceImpl
 * @generated
 */
@ProviderType
public class CampaignEventStatusLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.CampaignEventStatusLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the campaign event status to the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventStatus the campaign event status
	* @return the campaign event status that was added
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEventStatus addCampaignEventStatus(
		eu.strasbourg.service.agenda.model.CampaignEventStatus campaignEventStatus) {
		return getService().addCampaignEventStatus(campaignEventStatus);
	}

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEventStatus createCampaignEventStatus()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createCampaignEventStatus();
	}

	/**
	* Creates a new campaign event status with the primary key. Does not add the campaign event status to the database.
	*
	* @param statusId the primary key for the new campaign event status
	* @return the new campaign event status
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEventStatus createCampaignEventStatus(
		long statusId) {
		return getService().createCampaignEventStatus(statusId);
	}

	/**
	* Deletes the campaign event status from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventStatus the campaign event status
	* @return the campaign event status that was removed
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEventStatus deleteCampaignEventStatus(
		eu.strasbourg.service.agenda.model.CampaignEventStatus campaignEventStatus) {
		return getService().deleteCampaignEventStatus(campaignEventStatus);
	}

	/**
	* Deletes the campaign event status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status that was removed
	* @throws PortalException if a campaign event status with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEventStatus deleteCampaignEventStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCampaignEventStatus(statusId);
	}

	public static eu.strasbourg.service.agenda.model.CampaignEventStatus fetchCampaignEventStatus(
		long statusId) {
		return getService().fetchCampaignEventStatus(statusId);
	}

	/**
	* Returns the campaign event status with the primary key.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status
	* @throws PortalException if a campaign event status with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEventStatus getCampaignEventStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCampaignEventStatus(statusId);
	}

	/**
	* Updates the campaign event status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param campaignEventStatus the campaign event status
	* @return the campaign event status that was updated
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEventStatus updateCampaignEventStatus(
		eu.strasbourg.service.agenda.model.CampaignEventStatus campaignEventStatus) {
		return getService().updateCampaignEventStatus(campaignEventStatus);
	}

	/**
	* Returns the number of campaign event statuses.
	*
	* @return the number of campaign event statuses
	*/
	public static int getCampaignEventStatusesCount() {
		return getService().getCampaignEventStatusesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Retourne les statuts d'un événement
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.CampaignEventStatus> getByCampaignEvent(
		long campaignEventId) {
		return getService().getByCampaignEvent(campaignEventId);
	}

	/**
	* Returns a range of all the campaign event statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @return the range of campaign event statuses
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.CampaignEventStatus> getCampaignEventStatuses(
		int start, int end) {
		return getService().getCampaignEventStatuses(start, end);
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

	public static CampaignEventStatusLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CampaignEventStatusLocalService, CampaignEventStatusLocalService> _serviceTracker =
		ServiceTrackerFactory.open(CampaignEventStatusLocalService.class);
}