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
 * Provides a wrapper for {@link CampaignLocalService}.
 *
 * @author BenjaminBini
 * @see CampaignLocalService
 * @generated
 */
@ProviderType
public class CampaignLocalServiceWrapper implements CampaignLocalService,
	ServiceWrapper<CampaignLocalService> {
	public CampaignLocalServiceWrapper(
		CampaignLocalService campaignLocalService) {
		_campaignLocalService = campaignLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _campaignLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _campaignLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _campaignLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _campaignLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _campaignLocalService.search(searchContext);
	}

	/**
	* Adds the campaign to the database. Also notifies the appropriate model listeners.
	*
	* @param campaign the campaign
	* @return the campaign that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign addCampaign(
		eu.strasbourg.service.agenda.model.Campaign campaign) {
		return _campaignLocalService.addCampaign(campaign);
	}

	/**
	* Crée une campaign vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign createCampaign(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.createCampaign(sc);
	}

	/**
	* Creates a new campaign with the primary key. Does not add the campaign to the database.
	*
	* @param campaignId the primary key for the new campaign
	* @return the new campaign
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign createCampaign(
		long campaignId) {
		return _campaignLocalService.createCampaign(campaignId);
	}

	/**
	* Deletes the campaign from the database. Also notifies the appropriate model listeners.
	*
	* @param campaign the campaign
	* @return the campaign that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign deleteCampaign(
		eu.strasbourg.service.agenda.model.Campaign campaign) {
		return _campaignLocalService.deleteCampaign(campaign);
	}

	/**
	* Deletes the campaign with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignId the primary key of the campaign
	* @return the campaign that was removed
	* @throws PortalException if a campaign with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign deleteCampaign(
		long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.deleteCampaign(campaignId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.Campaign fetchCampaign(
		long campaignId) {
		return _campaignLocalService.fetchCampaign(campaignId);
	}

	/**
	* Returns the campaign matching the UUID and group.
	*
	* @param uuid the campaign's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign, or <code>null</code> if a matching campaign could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign fetchCampaignByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _campaignLocalService.fetchCampaignByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the campaign with the primary key.
	*
	* @param campaignId the primary key of the campaign
	* @return the campaign
	* @throws PortalException if a campaign with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign getCampaign(
		long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.getCampaign(campaignId);
	}

	/**
	* Returns the campaign matching the UUID and group.
	*
	* @param uuid the campaign's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign
	* @throws PortalException if a matching campaign could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign getCampaignByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.getCampaignByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une campaign
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign removeCampaign(
		long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.removeCampaign(campaignId);
	}

	/**
	* Updates the campaign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param campaign the campaign
	* @return the campaign that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign updateCampaign(
		eu.strasbourg.service.agenda.model.Campaign campaign) {
		return _campaignLocalService.updateCampaign(campaign);
	}

	/**
	* Met à jour une campaign et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign updateCampaign(
		eu.strasbourg.service.agenda.model.Campaign campaign,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.updateCampaign(campaign, sc);
	}

	/**
	* Met à jour le statut de l'campaign par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Campaign updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignLocalService.updateStatus(userId, entryId, status, sc,
			workflowContext);
	}

	/**
	* Returns the number of campaigns.
	*
	* @return the number of campaigns
	*/
	@Override
	public int getCampaignsCount() {
		return _campaignLocalService.getCampaignsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _campaignLocalService.getOSGiServiceIdentifier();
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
		return _campaignLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _campaignLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _campaignLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Campaign> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _campaignLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _campaignLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les campaigns d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Campaign> getByGroupId(
		long groupId) {
		return _campaignLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the campaigns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaigns
	* @param end the upper bound of the range of campaigns (not inclusive)
	* @return the range of campaigns
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Campaign> getCampaigns(
		int start, int end) {
		return _campaignLocalService.getCampaigns(start, end);
	}

	/**
	* Returns all the campaigns matching the UUID and company.
	*
	* @param uuid the UUID of the campaigns
	* @param companyId the primary key of the company
	* @return the matching campaigns, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Campaign> getCampaignsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _campaignLocalService.getCampaignsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of campaigns matching the UUID and company.
	*
	* @param uuid the UUID of the campaigns
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of campaigns
	* @param end the upper bound of the range of campaigns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching campaigns, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Campaign> getCampaignsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Campaign> orderByComparator) {
		return _campaignLocalService.getCampaignsByUuidAndCompanyId(uuid,
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
		return _campaignLocalService.dynamicQueryCount(dynamicQuery);
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
		return _campaignLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _campaignLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Exporte toutes les campagne exportables dans le dossier d'import des événements
	*/
	@Override
	public void exportCampaigns() {
		_campaignLocalService.exportCampaigns();
	}

	/**
	* Met à jour le statut de l'campaign "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.agenda.model.Campaign campaign, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		_campaignLocalService.updateStatus(campaign, status);
	}

	@Override
	public CampaignLocalService getWrappedService() {
		return _campaignLocalService;
	}

	@Override
	public void setWrappedService(CampaignLocalService campaignLocalService) {
		_campaignLocalService = campaignLocalService;
	}

	private CampaignLocalService _campaignLocalService;
}