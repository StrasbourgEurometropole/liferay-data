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

package eu.strasbourg.service.ejob.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OfferLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfferLocalService
 * @generated
 */
@ProviderType
public class OfferLocalServiceWrapper
	implements OfferLocalService, ServiceWrapper<OfferLocalService> {

	public OfferLocalServiceWrapper(OfferLocalService offerLocalService) {
		_offerLocalService = offerLocalService;
	}

	/**
	 * Adds the offer to the database. Also notifies the appropriate model listeners.
	 *
	 * @param offer the offer
	 * @return the offer that was added
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer addOffer(
		eu.strasbourg.service.ejob.model.Offer offer) {

		return _offerLocalService.addOffer(offer);
	}

	/**
	 * Creates a new offer with the primary key. Does not add the offer to the database.
	 *
	 * @param offerId the primary key for the new offer
	 * @return the new offer
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer createOffer(long offerId) {
		return _offerLocalService.createOffer(offerId);
	}

	/**
	 * Crée une offre vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer createOffer(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.createOffer(sc);
	}

	/**
	 * Deletes the offer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer that was removed
	 * @throws PortalException if a offer with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer deleteOffer(long offerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.deleteOffer(offerId);
	}

	/**
	 * Deletes the offer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offer the offer
	 * @return the offer that was removed
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer deleteOffer(
		eu.strasbourg.service.ejob.model.Offer offer) {

		return _offerLocalService.deleteOffer(offer);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _offerLocalService.dynamicQuery();
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

		return _offerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.ejob.model.impl.OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _offerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.ejob.model.impl.OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _offerLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _offerLocalService.dynamicQueryCount(dynamicQuery);
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

		return _offerLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.ejob.model.Offer fetchOffer(long offerId) {
		return _offerLocalService.fetchOffer(offerId);
	}

	/**
	 * Returns the offer matching the UUID and group.
	 *
	 * @param uuid the offer's UUID
	 * @param groupId the primary key of the group
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer fetchOfferByUuidAndGroupId(
		String uuid, long groupId) {

		return _offerLocalService.fetchOfferByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.ejob.model.Offer> findByKeyword(
		String keyword, long groupId, int start, int end) {

		return _offerLocalService.findByKeyword(keyword, groupId, start, end);
	}

	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		return _offerLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	 * Retourne une offre via sa date de début de publication
	 */
	@Override
	public java.util.List<eu.strasbourg.service.ejob.model.Offer>
		findByPublicationStartDate(java.util.Date date) {

		return _offerLocalService.findByPublicationStartDate(date);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _offerLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary>
		getAttachedVocabularies(long groupId) {

		return _offerLocalService.getAttachedVocabularies(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _offerLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _offerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the offer with the primary key.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer
	 * @throws PortalException if a offer with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer getOffer(long offerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.getOffer(offerId);
	}

	/**
	 * Returns the offer matching the UUID and group.
	 *
	 * @param uuid the offer's UUID
	 * @param groupId the primary key of the group
	 * @return the matching offer
	 * @throws PortalException if a matching offer could not be found
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer getOfferByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.getOfferByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the offers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.ejob.model.impl.OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of offers
	 */
	@Override
	public java.util.List<eu.strasbourg.service.ejob.model.Offer> getOffers(
		int start, int end) {

		return _offerLocalService.getOffers(start, end);
	}

	/**
	 * Returns all the offers matching the UUID and company.
	 *
	 * @param uuid the UUID of the offers
	 * @param companyId the primary key of the company
	 * @return the matching offers, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.ejob.model.Offer>
		getOffersByUuidAndCompanyId(String uuid, long companyId) {

		return _offerLocalService.getOffersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of offers matching the UUID and company.
	 *
	 * @param uuid the UUID of the offers
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching offers, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.ejob.model.Offer>
		getOffersByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.ejob.model.Offer> orderByComparator) {

		return _offerLocalService.getOffersByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of offers.
	 *
	 * @return the number of offers
	 */
	@Override
	public int getOffersCount() {
		return _offerLocalService.getOffersCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _offerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprime une edition
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer removeOffer(long offerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.removeOffer(offerId);
	}

	/**
	 * Updates the offer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param offer the offer
	 * @return the offer that was updated
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer updateOffer(
		eu.strasbourg.service.ejob.model.Offer offer) {

		return _offerLocalService.updateOffer(offer);
	}

	/**
	 * Met à jour une offre et l'enregistre en base de données
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer updateOffer(
			eu.strasbourg.service.ejob.model.Offer offer,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.updateOffer(offer, sc);
	}

	/**
	 * Met à jour le statut de l'edition par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.ejob.model.Offer updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _offerLocalService.updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	/**
	 * Met à jour le statut de l'edition "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(
			eu.strasbourg.service.ejob.model.Offer offer, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		_offerLocalService.updateStatus(offer, status);
	}

	@Override
	public OfferLocalService getWrappedService() {
		return _offerLocalService;
	}

	@Override
	public void setWrappedService(OfferLocalService offerLocalService) {
		_offerLocalService = offerLocalService;
	}

	private OfferLocalService _offerLocalService;

}