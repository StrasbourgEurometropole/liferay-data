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
 * Provides the local service utility for CampaignEvent. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.CampaignEventLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see CampaignEventLocalService
 * @see eu.strasbourg.service.agenda.service.base.CampaignEventLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.CampaignEventLocalServiceImpl
 * @generated
 */
@ProviderType
public class CampaignEventLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.CampaignEventLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	* Adds the campaign event to the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was added
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent addCampaignEvent(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {
		return getService().addCampaignEvent(campaignEvent);
	}

	/**
	* Crée une campaignEvent vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent createCampaignEvent(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createCampaignEvent(sc);
	}

	/**
	* Creates a new campaign event with the primary key. Does not add the campaign event to the database.
	*
	* @param campaignEventId the primary key for the new campaign event
	* @return the new campaign event
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent createCampaignEvent(
		long campaignEventId) {
		return getService().createCampaignEvent(campaignEventId);
	}

	/**
	* Deletes the campaign event from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was removed
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent deleteCampaignEvent(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {
		return getService().deleteCampaignEvent(campaignEvent);
	}

	/**
	* Deletes the campaign event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event that was removed
	* @throws PortalException if a campaign event with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent deleteCampaignEvent(
		long campaignEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCampaignEvent(campaignEventId);
	}

	public static eu.strasbourg.service.agenda.model.CampaignEvent fetchCampaignEvent(
		long campaignEventId) {
		return getService().fetchCampaignEvent(campaignEventId);
	}

	/**
	* Returns the campaign event matching the UUID and group.
	*
	* @param uuid the campaign event's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent fetchCampaignEventByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchCampaignEventByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the campaign event with the primary key.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event
	* @throws PortalException if a campaign event with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent getCampaignEvent(
		long campaignEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCampaignEvent(campaignEventId);
	}

	/**
	* Returns the campaign event matching the UUID and group.
	*
	* @param uuid the campaign event's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign event
	* @throws PortalException if a matching campaign event could not be found
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent getCampaignEventByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCampaignEventByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une campaignEvent
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent removeCampaignEvent(
		long campaignEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeCampaignEvent(campaignEventId);
	}

	/**
	* Updates the campaign event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was updated
	*/
	public static eu.strasbourg.service.agenda.model.CampaignEvent updateCampaignEvent(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {
		return getService().updateCampaignEvent(campaignEvent);
	}

	/**
	* Returns the number of campaign events.
	*
	* @return the number of campaign events
	*/
	public static int getCampaignEventsCount() {
		return getService().getCampaignEventsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne les événements d'une campagne
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> findByCampaignId(
		long campaignId) {
		return getService().findByCampaignId(campaignId);
	}

	/**
	* Lance une recherche par mots-clés, thème et status
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> findByKeywordThemeAndStatus(
		java.lang.String keyword, long themeId, int status, long userId,
		long groupId, int start, int end) {
		return getService()
				   .findByKeywordThemeAndStatus(keyword, themeId, status,
			userId, groupId, start, end);
	}

	/**
	* Returns a range of all the campaign events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @return the range of campaign events
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> getCampaignEvents(
		int start, int end) {
		return getService().getCampaignEvents(start, end);
	}

	/**
	* Returns all the campaign events matching the UUID and company.
	*
	* @param uuid the UUID of the campaign events
	* @param companyId the primary key of the company
	* @return the matching campaign events, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> getCampaignEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getCampaignEventsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of campaign events matching the UUID and company.
	*
	* @param uuid the UUID of the campaign events
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching campaign events, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> getCampaignEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.CampaignEvent> orderByComparator) {
		return getService()
				   .getCampaignEventsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	/**
	* Compte de la recherche par mots-clés, thème et status
	*/
	public static long findByKeywordThemeAndStatusCount(
		java.lang.String keyword, long themeId, int status, long userId,
		long groupId) {
		return getService()
				   .findByKeywordThemeAndStatusCount(keyword, themeId, status,
			userId, groupId);
	}

	public static CampaignEventLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CampaignEventLocalService, CampaignEventLocalService> _serviceTracker =
		ServiceTrackerFactory.open(CampaignEventLocalService.class);
}