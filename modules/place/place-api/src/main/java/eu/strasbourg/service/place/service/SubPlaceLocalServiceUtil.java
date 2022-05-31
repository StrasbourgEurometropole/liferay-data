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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SubPlace. This utility wraps
 * <code>eu.strasbourg.service.place.service.impl.SubPlaceLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Angelique Zunino Champougny
 * @see SubPlaceLocalService
 * @generated
 */
public class SubPlaceLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.place.service.impl.SubPlaceLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the sub place to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubPlaceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subPlace the sub place
	 * @return the sub place that was added
	 */
	public static eu.strasbourg.service.place.model.SubPlace addSubPlace(
		eu.strasbourg.service.place.model.SubPlace subPlace) {

		return getService().addSubPlace(subPlace);
	}

	/**
	 * Creates a new sub place with the primary key. Does not add the sub place to the database.
	 *
	 * @param subPlaceId the primary key for the new sub place
	 * @return the new sub place
	 */
	public static eu.strasbourg.service.place.model.SubPlace createSubPlace(
		long subPlaceId) {

		return getService().createSubPlace(subPlaceId);
	}

	/**
	 * Crée un sous-lieu vide avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.place.model.SubPlace createSubPlace(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createSubPlace(sc);
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

	/**
	 * Deletes the sub place with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubPlaceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subPlaceId the primary key of the sub place
	 * @return the sub place that was removed
	 * @throws PortalException if a sub place with the primary key could not be found
	 */
	public static eu.strasbourg.service.place.model.SubPlace deleteSubPlace(
			long subPlaceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteSubPlace(subPlaceId);
	}

	/**
	 * Deletes the sub place from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubPlaceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subPlace the sub place
	 * @return the sub place that was removed
	 */
	public static eu.strasbourg.service.place.model.SubPlace deleteSubPlace(
		eu.strasbourg.service.place.model.SubPlace subPlace) {

		return getService().deleteSubPlace(subPlace);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.SubPlaceModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.SubPlaceModelImpl</code>.
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

	public static eu.strasbourg.service.place.model.SubPlace fetchSubPlace(
		long subPlaceId) {

		return getService().fetchSubPlace(subPlaceId);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	public static java.util.List<eu.strasbourg.service.place.model.SubPlace>
		findByKeyword(String keyword, int start, int end) {

		return getService().findByKeyword(keyword, start, end);
	}

	/**
	 * Compte de la recherche par mots-clés
	 */
	public static long findByKeywordCount(String keyword) {
		return getService().findByKeywordCount(keyword);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Retourne les SubPlace rattachés à un lieu
	 */
	public static java.util.List<eu.strasbourg.service.place.model.SubPlace>
		getByPlaceId(long placeId) {

		return getService().getByPlaceId(placeId);
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

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the sub place with the primary key.
	 *
	 * @param subPlaceId the primary key of the sub place
	 * @return the sub place
	 * @throws PortalException if a sub place with the primary key could not be found
	 */
	public static eu.strasbourg.service.place.model.SubPlace getSubPlace(
			long subPlaceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getSubPlace(subPlaceId);
	}

	/**
	 * Returns a range of all the sub places.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.SubPlaceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub places
	 * @param end the upper bound of the range of sub places (not inclusive)
	 * @return the range of sub places
	 */
	public static java.util.List<eu.strasbourg.service.place.model.SubPlace>
		getSubPlaces(int start, int end) {

		return getService().getSubPlaces(start, end);
	}

	/**
	 * Returns the number of sub places.
	 *
	 * @return the number of sub places
	 */
	public static int getSubPlacesCount() {
		return getService().getSubPlacesCount();
	}

	/**
	 * Supprime un sous-lieu
	 */
	public static eu.strasbourg.service.place.model.SubPlace removeSubPlace(
			long subPlaceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().removeSubPlace(subPlaceId);
	}

	/**
	 * Met à jour le statut du sous-lieu par le framework workflow
	 */
	public static eu.strasbourg.service.place.model.SubPlace updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	/**
	 * Met à jour le statut du sous-lieu "manuellement" (pas via le workflow)
	 */
	public static void updateStatus(
			long userId, eu.strasbourg.service.place.model.SubPlace subPlace,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateStatus(userId, subPlace, status);
	}

	/**
	 * Updates the sub place in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubPlaceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subPlace the sub place
	 * @return the sub place that was updated
	 */
	public static eu.strasbourg.service.place.model.SubPlace updateSubPlace(
		eu.strasbourg.service.place.model.SubPlace subPlace) {

		return getService().updateSubPlace(subPlace);
	}

	/**
	 * Met à jour un sous-lieu et l'enregistre en base de données
	 */
	public static eu.strasbourg.service.place.model.SubPlace updateSubPlace(
			eu.strasbourg.service.place.model.SubPlace subPlace,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateSubPlace(subPlace, sc);
	}

	public static SubPlaceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SubPlaceLocalService, SubPlaceLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SubPlaceLocalService.class);

		ServiceTracker<SubPlaceLocalService, SubPlaceLocalService>
			serviceTracker =
				new ServiceTracker<SubPlaceLocalService, SubPlaceLocalService>(
					bundle.getBundleContext(), SubPlaceLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}