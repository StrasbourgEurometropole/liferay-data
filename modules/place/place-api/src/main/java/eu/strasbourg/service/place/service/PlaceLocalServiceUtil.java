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

package eu.strasbourg.service.place.service;

import org.osgi.util.tracker.ServiceTracker;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.exception.NoSuchPlaceException;

/**
 * Provides the local service utility for Place. This utility wraps
 * {@link eu.strasbourg.service.place.service.impl.PlaceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Angelique Zunino Champougny
 * @see PlaceLocalService
 * @see eu.strasbourg.service.place.service.base.PlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.impl.PlaceLocalServiceImpl
 * @generated
 */
@ProviderType
public class PlaceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.place.service.impl.PlaceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Lance une recherche selon le searchContext
	*/
	public static Hits search(SearchContext searchContext)
		throws SearchException {
		return getService().search(searchContext);
	}

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
	* Adds the place to the database. Also notifies the appropriate model listeners.
	*
	* @param place the place
	* @return the place that was added
	*/
	public static eu.strasbourg.service.place.model.Place addPlace(
		eu.strasbourg.service.place.model.Place place) {
		return getService().addPlace(place);
	}

	/**
	* Crée un lieu vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.place.model.Place createPlace(
		ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createPlace(sc);
	}

	/**
	* Creates a new place with the primary key. Does not add the place to the database.
	*
	* @param placeId the primary key for the new place
	* @return the new place
	*/
	public static eu.strasbourg.service.place.model.Place createPlace(
		long placeId) {
		return getService().createPlace(placeId);
	}

	/**
	* Deletes the place from the database. Also notifies the appropriate model listeners.
	*
	* @param place the place
	* @return the place that was removed
	*/
	public static eu.strasbourg.service.place.model.Place deletePlace(
		eu.strasbourg.service.place.model.Place place) {
		return getService().deletePlace(place);
	}

	/**
	* Deletes the place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param placeId the primary key of the place
	* @return the place that was removed
	* @throws PortalException if a place with the primary key could not be found
	*/
	public static eu.strasbourg.service.place.model.Place deletePlace(
		long placeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePlace(placeId);
	}

	public static eu.strasbourg.service.place.model.Place fetchPlace(
		long placeId) {
		return getService().fetchPlace(placeId);
	}

	/**
	* Returns the place matching the UUID and group.
	*
	* @param uuid the place's UUID
	* @param groupId the primary key of the group
	* @return the matching place, or <code>null</code> if a matching place could not be found
	*/
	public static eu.strasbourg.service.place.model.Place fetchPlaceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPlaceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the place with the primary key.
	*
	* @param placeId the primary key of the place
	* @return the place
	* @throws PortalException if a place with the primary key could not be found
	*/
	public static eu.strasbourg.service.place.model.Place getPlace(long placeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPlace(placeId);
	}

	public static eu.strasbourg.service.place.model.Place getPlaceBySIGId(
		java.lang.String idSIG) {
		return getService().getPlaceBySIGId(idSIG);
	}

	/**
	* Returns the place matching the UUID and group.
	*
	* @param uuid the place's UUID
	* @param groupId the primary key of the group
	* @return the matching place
	* @throws PortalException if a matching place could not be found
	*/
	public static eu.strasbourg.service.place.model.Place getPlaceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPlaceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime un lieu
	*/
	public static eu.strasbourg.service.place.model.Place removePlace(
		long placeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removePlace(placeId);
	}

	/**
	* Updates the place in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param place the place
	* @return the place that was updated
	*/
	public static eu.strasbourg.service.place.model.Place updatePlace(
		eu.strasbourg.service.place.model.Place place) {
		return getService().updatePlace(place);
	}

	/**
	* Met à jour un lieu et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.place.model.Place updatePlace(
		eu.strasbourg.service.place.model.Place place, ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updatePlace(place, sc);
	}

	/**
	* Met à jour le statut du lieu par le framework workflow
	*/
	public static eu.strasbourg.service.place.model.Place updateStatus(
		long userId, long entryId, int status, ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of places.
	*
	* @return the number of places
	*/
	public static int getPlacesCount() {
		return getService().getPlacesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Lance une recherche par mots-clés
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Place> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Lance une recherche par nom
	*
	* @throws NoSuchPlaceException
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Place> findByName(
		java.lang.String name)
		throws eu.strasbourg.service.place.exception.NoSuchPlaceException {
		return getService().findByName(name);
	}

	/**
	* Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne les lieux d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Place> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Retourne les lieux rattachés à un tarif
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Place> getByPriceId(
		long priceId) {
		return getService().getByPriceId(priceId);
	}

	/**
	* Returns a range of all the places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @return the range of places
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Place> getPlaces(
		int start, int end) {
		return getService().getPlaces(start, end);
	}

	/**
	* Returns all the places matching the UUID and company.
	*
	* @param uuid the UUID of the places
	* @param companyId the primary key of the company
	* @return the matching places, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Place> getPlacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPlacesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of places matching the UUID and company.
	*
	* @param uuid the UUID of the places
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of places
	* @param end the upper bound of the range of places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching places, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Place> getPlacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.place.model.Place> orderByComparator) {
		return getService()
				   .getPlacesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
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
	* Compte de la recherche par mots-clés
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static void updateRealTime()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateRealTime();
	}

	public static void updateRealTime(
		eu.strasbourg.service.place.model.Place place, java.lang.String type,
		long occupation, long available, long capacity, java.lang.String status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateRealTime(place, type, occupation, available, capacity, status);
	}

	/**
	* Met à jour le statut du lieu "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.place.model.Place place, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(place, status);
	}

	public static PlaceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PlaceLocalService, PlaceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PlaceLocalService.class);
}