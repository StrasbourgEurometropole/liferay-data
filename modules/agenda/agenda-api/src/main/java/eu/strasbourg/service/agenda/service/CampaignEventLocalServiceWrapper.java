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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CampaignEventLocalService}.
 *
 * @author BenjaminBini
 * @see CampaignEventLocalService
 * @generated
 */
@ProviderType
public class CampaignEventLocalServiceWrapper
	implements CampaignEventLocalService,
		ServiceWrapper<CampaignEventLocalService> {
	public CampaignEventLocalServiceWrapper(
		CampaignEventLocalService campaignEventLocalService) {
		_campaignEventLocalService = campaignEventLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _campaignEventLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _campaignEventLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _campaignEventLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _campaignEventLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the campaign event to the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent addCampaignEvent(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {
		return _campaignEventLocalService.addCampaignEvent(campaignEvent);
	}

	/**
	* Crée une campaignEvent vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent createCampaignEvent(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventLocalService.createCampaignEvent(sc);
	}

	/**
	* Creates a new campaign event with the primary key. Does not add the campaign event to the database.
	*
	* @param campaignEventId the primary key for the new campaign event
	* @return the new campaign event
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent createCampaignEvent(
		long campaignEventId) {
		return _campaignEventLocalService.createCampaignEvent(campaignEventId);
	}

	/**
	* Deletes the campaign event from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent deleteCampaignEvent(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {
		return _campaignEventLocalService.deleteCampaignEvent(campaignEvent);
	}

	/**
	* Deletes the campaign event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event that was removed
	* @throws PortalException if a campaign event with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent deleteCampaignEvent(
		long campaignEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventLocalService.deleteCampaignEvent(campaignEventId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent fetchCampaignEvent(
		long campaignEventId) {
		return _campaignEventLocalService.fetchCampaignEvent(campaignEventId);
	}

	/**
	* Returns the campaign event matching the UUID and group.
	*
	* @param uuid the campaign event's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent fetchCampaignEventByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _campaignEventLocalService.fetchCampaignEventByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the campaign event with the primary key.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event
	* @throws PortalException if a campaign event with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent getCampaignEvent(
		long campaignEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventLocalService.getCampaignEvent(campaignEventId);
	}

	/**
	* Returns the campaign event matching the UUID and group.
	*
	* @param uuid the campaign event's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign event
	* @throws PortalException if a matching campaign event could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent getCampaignEventByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventLocalService.getCampaignEventByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime une campaignEvent
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent removeCampaignEvent(
		long campaignEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventLocalService.removeCampaignEvent(campaignEventId);
	}

	/**
	* Updates the campaign event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent updateCampaignEvent(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {
		return _campaignEventLocalService.updateCampaignEvent(campaignEvent);
	}

	/**
	* Returns the number of campaign events.
	*
	* @return the number of campaign events
	*/
	@Override
	public int getCampaignEventsCount() {
		return _campaignEventLocalService.getCampaignEventsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _campaignEventLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _campaignEventLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _campaignEventLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _campaignEventLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Retourne les événements d'une campagne
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> findByCampaignId(
		long campaignId) {
		return _campaignEventLocalService.findByCampaignId(campaignId);
	}

	/**
	* Lance une recherche par mots-clés, thème et status
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> findByKeywordThemeAndStatus(
		java.lang.String keyword, long themeId, int status, long userId,
		long groupId, int start, int end) {
		return _campaignEventLocalService.findByKeywordThemeAndStatus(keyword,
			themeId, status, userId, groupId, start, end);
	}

	/**
	* Lance une recherche par mots-clés, thème, type, campagne et status
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> findByKeywordThemeTypeCampaignAndStatus(
		java.lang.String keyword, long themeId, long typeId, long campaignId,
		int status, long userId, long groupId, int start, int end) {
		return _campaignEventLocalService.findByKeywordThemeTypeCampaignAndStatus(keyword,
			themeId, typeId, campaignId, status, userId, groupId, start, end);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> getCampaignEvents(
		int start, int end) {
		return _campaignEventLocalService.getCampaignEvents(start, end);
	}

	/**
	* Returns all the campaign events matching the UUID and company.
	*
	* @param uuid the UUID of the campaign events
	* @param companyId the primary key of the company
	* @return the matching campaign events, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> getCampaignEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _campaignEventLocalService.getCampaignEventsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEvent> getCampaignEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.CampaignEvent> orderByComparator) {
		return _campaignEventLocalService.getCampaignEventsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _campaignEventLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _campaignEventLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Compte de la recherche par mots-clés, thème et status
	*/
	@Override
	public long findByKeywordThemeAndStatusCount(java.lang.String keyword,
		long themeId, int status, long userId, long groupId) {
		return _campaignEventLocalService.findByKeywordThemeAndStatusCount(keyword,
			themeId, status, userId, groupId);
	}

	/**
	* Compte de la recherche par mots-clés, thème, type, campagne et status
	*/
	@Override
	public long findByKeywordThemeTypeCampaignAndStatusCount(
		java.lang.String keyword, long themeId, long typeId, long campaignId,
		int status, long userId, long groupId) {
		return _campaignEventLocalService.findByKeywordThemeTypeCampaignAndStatusCount(keyword,
			themeId, typeId, campaignId, status, userId, groupId);
	}

	@Override
	public CampaignEventLocalService getWrappedService() {
		return _campaignEventLocalService;
	}

	@Override
	public void setWrappedService(
		CampaignEventLocalService campaignEventLocalService) {
		_campaignEventLocalService = campaignEventLocalService;
	}

	private CampaignEventLocalService _campaignEventLocalService;
}