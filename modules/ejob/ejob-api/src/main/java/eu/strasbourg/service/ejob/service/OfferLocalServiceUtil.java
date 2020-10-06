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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Offer. This utility wraps
 * <code>eu.strasbourg.service.ejob.service.impl.OfferLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfferLocalService
 * @generated
 */
@ProviderType
public class OfferLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.ejob.service.impl.OfferLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the offer to the database. Also notifies the appropriate model listeners.
	 *
	 * @param offer the offer
	 * @return the offer that was added
	 */
	public static eu.strasbourg.service.ejob.model.Offer addOffer(
		eu.strasbourg.service.ejob.model.Offer offer) {

		return getService().addOffer(offer);
	}

	/**
	 * Duplique une offre et la retourne
	 */
	public static eu.strasbourg.service.ejob.model.Offer copyOffer(
			com.liferay.portal.kernel.service.ServiceContext sc, long offerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().copyOffer(sc, offerId);
	}

	/**
	 * Creates a new offer with the primary key. Does not add the offer to the database.
	 *
	 * @param offerId the primary key for the new offer
	 * @return the new offer
	 */
	public static eu.strasbourg.service.ejob.model.Offer createOffer(
		long offerId) {

		return getService().createOffer(offerId);
	}

	/**
	 * Crée une offre vide avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.ejob.model.Offer createOffer(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createOffer(sc);
	}

	/**
	 * Deletes the offer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer that was removed
	 * @throws PortalException if a offer with the primary key could not be found
	 */
	public static eu.strasbourg.service.ejob.model.Offer deleteOffer(
			long offerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteOffer(offerId);
	}

	/**
	 * Deletes the offer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offer the offer
	 * @return the offer that was removed
	 */
	public static eu.strasbourg.service.ejob.model.Offer deleteOffer(
		eu.strasbourg.service.ejob.model.Offer offer) {

		return getService().deleteOffer(offer);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.ejob.model.impl.OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.ejob.model.impl.OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static eu.strasbourg.service.ejob.model.Offer fetchOffer(
		long offerId) {

		return getService().fetchOffer(offerId);
	}

	/**
	 * Returns the offer matching the UUID and group.
	 *
	 * @param uuid the offer's UUID
	 * @param groupId the primary key of the group
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public static eu.strasbourg.service.ejob.model.Offer
		fetchOfferByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchOfferByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<eu.strasbourg.service.ejob.model.Offer>
		findByKeyword(String keyword, long groupId, int start, int end) {

		return getService().findByKeyword(keyword, groupId, start, end);
	}

	public static long findByKeywordCount(String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	/**
	 * Retourne une offre via son publicationId
	 */
	public static eu.strasbourg.service.ejob.model.Offer findByPublicationId(
		String publicationId) {

		return getService().findByPublicationId(publicationId);
	}

	/**
	 * Retourne les offres via sa date de début de publication
	 */
	public static java.util.List<eu.strasbourg.service.ejob.model.Offer>
		findByPublicationStartDate(java.util.Date date) {

		return getService().findByPublicationStartDate(date);
	}

	/**
	 * Retourne les offres qui n'ont pas été exportées
	 */
	public static java.util.List<eu.strasbourg.service.ejob.model.Offer>
		findOffersNotExported() {

		return getService().findOffersNotExported();
	}

	/**
	 * Retourne les offres qui n'ont pas été envoyées aux partenaires
	 */
	public static java.util.List<eu.strasbourg.service.ejob.model.Offer>
		findOffersNotSent() {

		return getService().findOffersNotSent();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	 */
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary>
		getAttachedVocabularies(long groupId) {

		return getService().getAttachedVocabularies(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the offer with the primary key.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer
	 * @throws PortalException if a offer with the primary key could not be found
	 */
	public static eu.strasbourg.service.ejob.model.Offer getOffer(long offerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOffer(offerId);
	}

	/**
	 * Returns the offer matching the UUID and group.
	 *
	 * @param uuid the offer's UUID
	 * @param groupId the primary key of the group
	 * @return the matching offer
	 * @throws PortalException if a matching offer could not be found
	 */
	public static eu.strasbourg.service.ejob.model.Offer
			getOfferByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOfferByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<eu.strasbourg.service.ejob.model.Offer>
		getOffers(int start, int end) {

		return getService().getOffers(start, end);
	}

	/**
	 * Returns all the offers matching the UUID and company.
	 *
	 * @param uuid the UUID of the offers
	 * @param companyId the primary key of the company
	 * @return the matching offers, or an empty list if no matches were found
	 */
	public static java.util.List<eu.strasbourg.service.ejob.model.Offer>
		getOffersByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getOffersByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<eu.strasbourg.service.ejob.model.Offer>
		getOffersByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.ejob.model.Offer> orderByComparator) {

		return getService().getOffersByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of offers.
	 *
	 * @return the number of offers
	 */
	public static int getOffersCount() {
		return getService().getOffersCount();
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
	 * Supprime une edition
	 */
	public static eu.strasbourg.service.ejob.model.Offer removeOffer(
			long offerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().removeOffer(offerId);
	}

	public static void setCategoriesForCopy(
		eu.strasbourg.service.ejob.model.Offer offerToCopy,
		eu.strasbourg.service.ejob.model.Offer offer,
		com.liferay.portal.kernel.service.ServiceContext sc) {

		getService().setCategoriesForCopy(offerToCopy, offer, sc);
	}

	/**
	 * Updates the offer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param offer the offer
	 * @return the offer that was updated
	 */
	public static eu.strasbourg.service.ejob.model.Offer updateOffer(
		eu.strasbourg.service.ejob.model.Offer offer) {

		return getService().updateOffer(offer);
	}

	/**
	 * Met à jour une offre et l'enregistre en base de données
	 */
	public static eu.strasbourg.service.ejob.model.Offer updateOffer(
			eu.strasbourg.service.ejob.model.Offer offer,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateOffer(offer, sc);
	}

	/**
	 * Met à jour le statut de l'edition par le framework workflow
	 */
	public static eu.strasbourg.service.ejob.model.Offer updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	/**
	 * Met à jour le statut de l'edition "manuellement" (pas via le workflow)
	 */
	public static void updateStatus(
			eu.strasbourg.service.ejob.model.Offer offer, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateStatus(offer, status);
	}

	public static OfferLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OfferLocalService, OfferLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OfferLocalService.class);

		ServiceTracker<OfferLocalService, OfferLocalService> serviceTracker =
			new ServiceTracker<OfferLocalService, OfferLocalService>(
				bundle.getBundleContext(), OfferLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}