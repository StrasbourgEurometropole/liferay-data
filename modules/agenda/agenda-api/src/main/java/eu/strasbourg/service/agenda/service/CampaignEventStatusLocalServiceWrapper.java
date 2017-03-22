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
 * Provides a wrapper for {@link CampaignEventStatusLocalService}.
 *
 * @author BenjaminBini
 * @see CampaignEventStatusLocalService
 * @generated
 */
@ProviderType
public class CampaignEventStatusLocalServiceWrapper
	implements CampaignEventStatusLocalService,
		ServiceWrapper<CampaignEventStatusLocalService> {
	public CampaignEventStatusLocalServiceWrapper(
		CampaignEventStatusLocalService campaignEventStatusLocalService) {
		_campaignEventStatusLocalService = campaignEventStatusLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _campaignEventStatusLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _campaignEventStatusLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _campaignEventStatusLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventStatusLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventStatusLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the campaign event status to the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventStatus the campaign event status
	* @return the campaign event status that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus addCampaignEventStatus(
		eu.strasbourg.service.agenda.model.CampaignEventStatus campaignEventStatus) {
		return _campaignEventStatusLocalService.addCampaignEventStatus(campaignEventStatus);
	}

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus createCampaignEventStatus()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventStatusLocalService.createCampaignEventStatus();
	}

	/**
	* Creates a new campaign event status with the primary key. Does not add the campaign event status to the database.
	*
	* @param statusId the primary key for the new campaign event status
	* @return the new campaign event status
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus createCampaignEventStatus(
		long statusId) {
		return _campaignEventStatusLocalService.createCampaignEventStatus(statusId);
	}

	/**
	* Deletes the campaign event status from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventStatus the campaign event status
	* @return the campaign event status that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus deleteCampaignEventStatus(
		eu.strasbourg.service.agenda.model.CampaignEventStatus campaignEventStatus) {
		return _campaignEventStatusLocalService.deleteCampaignEventStatus(campaignEventStatus);
	}

	/**
	* Deletes the campaign event status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status that was removed
	* @throws PortalException if a campaign event status with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus deleteCampaignEventStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventStatusLocalService.deleteCampaignEventStatus(statusId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus fetchCampaignEventStatus(
		long statusId) {
		return _campaignEventStatusLocalService.fetchCampaignEventStatus(statusId);
	}

	/**
	* Returns the campaign event status with the primary key.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status
	* @throws PortalException if a campaign event status with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus getCampaignEventStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEventStatusLocalService.getCampaignEventStatus(statusId);
	}

	/**
	* Updates the campaign event status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param campaignEventStatus the campaign event status
	* @return the campaign event status that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus updateCampaignEventStatus(
		eu.strasbourg.service.agenda.model.CampaignEventStatus campaignEventStatus) {
		return _campaignEventStatusLocalService.updateCampaignEventStatus(campaignEventStatus);
	}

	/**
	* Returns the number of campaign event statuses.
	*
	* @return the number of campaign event statuses
	*/
	@Override
	public int getCampaignEventStatusesCount() {
		return _campaignEventStatusLocalService.getCampaignEventStatusesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _campaignEventStatusLocalService.getOSGiServiceIdentifier();
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
		return _campaignEventStatusLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _campaignEventStatusLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _campaignEventStatusLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Retourne les statuts d'un événement
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEventStatus> getByCampaignEvent(
		long campaignEventId) {
		return _campaignEventStatusLocalService.getByCampaignEvent(campaignEventId);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEventStatus> getCampaignEventStatuses(
		int start, int end) {
		return _campaignEventStatusLocalService.getCampaignEventStatuses(start,
			end);
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
		return _campaignEventStatusLocalService.dynamicQueryCount(dynamicQuery);
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
		return _campaignEventStatusLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public CampaignEventStatusLocalService getWrappedService() {
		return _campaignEventStatusLocalService;
	}

	@Override
	public void setWrappedService(
		CampaignEventStatusLocalService campaignEventStatusLocalService) {
		_campaignEventStatusLocalService = campaignEventStatusLocalService;
	}

	private CampaignEventStatusLocalService _campaignEventStatusLocalService;
}