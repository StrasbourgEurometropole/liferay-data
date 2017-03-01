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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SubPlace. This utility wraps
 * {@link eu.strasbourg.service.place.service.impl.SubPlaceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Angelique Zunino Champougny
 * @see SubPlaceLocalService
 * @see eu.strasbourg.service.place.service.base.SubPlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.impl.SubPlaceLocalServiceImpl
 * @generated
 */
@ProviderType
public class SubPlaceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.place.service.impl.SubPlaceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the sub place to the database. Also notifies the appropriate model listeners.
	*
	* @param subPlace the sub place
	* @return the sub place that was added
	*/
	public static eu.strasbourg.service.place.model.SubPlace addSubPlace(
		eu.strasbourg.service.place.model.SubPlace subPlace) {
		return getService().addSubPlace(subPlace);
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
	* Deletes the sub place from the database. Also notifies the appropriate model listeners.
	*
	* @param subPlace the sub place
	* @return the sub place that was removed
	*/
	public static eu.strasbourg.service.place.model.SubPlace deleteSubPlace(
		eu.strasbourg.service.place.model.SubPlace subPlace) {
		return getService().deleteSubPlace(subPlace);
	}

	/**
	* Deletes the sub place with the primary key from the database. Also notifies the appropriate model listeners.
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

	public static eu.strasbourg.service.place.model.SubPlace fetchSubPlace(
		long subPlaceId) {
		return getService().fetchSubPlace(subPlaceId);
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
	* Supprime un sous-lieu
	*/
	public static eu.strasbourg.service.place.model.SubPlace removeSubPlace(
		long subPlaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeSubPlace(subPlaceId);
	}

	/**
	* Updates the sub place in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param subPlace the sub place
	* @return the sub place that was updated
	*/
	public static eu.strasbourg.service.place.model.SubPlace updateSubPlace(
		eu.strasbourg.service.place.model.SubPlace subPlace) {
		return getService().updateSubPlace(subPlace);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne les SubPlace rattachés à un lieu
	*/
	public static java.util.List<eu.strasbourg.service.place.model.SubPlace> getByPlaceId(
		long placeId) {
		return getService().getByPlaceId(placeId);
	}

	/**
	* Returns a range of all the sub places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @return the range of sub places
	*/
	public static java.util.List<eu.strasbourg.service.place.model.SubPlace> getSubPlaces(
		int start, int end) {
		return getService().getSubPlaces(start, end);
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

	public static SubPlaceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SubPlaceLocalService, SubPlaceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(SubPlaceLocalService.class);
}