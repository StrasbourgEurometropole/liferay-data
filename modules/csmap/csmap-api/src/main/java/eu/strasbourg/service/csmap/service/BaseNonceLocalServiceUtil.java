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

package eu.strasbourg.service.csmap.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for BaseNonce. This utility wraps
 * <code>eu.strasbourg.service.csmap.service.impl.BaseNonceLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see BaseNonceLocalService
 * @generated
 */
@ProviderType
public class BaseNonceLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.csmap.service.impl.BaseNonceLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the base nonce to the database. Also notifies the appropriate model listeners.
	 *
	 * @param baseNonce the base nonce
	 * @return the base nonce that was added
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce addBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce) {

		return getService().addBaseNonce(baseNonce);
	}

	/**
	 * Creates a new base nonce with the primary key. Does not add the base nonce to the database.
	 *
	 * @param baseNonceId the primary key for the new base nonce
	 * @return the new base nonce
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce createBaseNonce(
		long baseNonceId) {

		return getService().createBaseNonce(baseNonceId);
	}

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce createBaseNonce(
		com.liferay.portal.kernel.service.ServiceContext sc) {

		return getService().createBaseNonce(sc);
	}

	/**
	 * Deletes the base nonce from the database. Also notifies the appropriate model listeners.
	 *
	 * @param baseNonce the base nonce
	 * @return the base nonce that was removed
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce deleteBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce) {

		return getService().deleteBaseNonce(baseNonce);
	}

	/**
	 * Deletes the base nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce that was removed
	 * @throws PortalException if a base nonce with the primary key could not be found
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce deleteBaseNonce(
			long baseNonceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteBaseNonce(baseNonceId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.BaseNonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.BaseNonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static eu.strasbourg.service.csmap.model.BaseNonce fetchBaseNonce(
		long baseNonceId) {

		return getService().fetchBaseNonce(baseNonceId);
	}

	/**
	 * Retrouve un baseNonce par sa valeur
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce fetchByValue(
		String value) {

		return getService().fetchByValue(value);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the base nonce with the primary key.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce
	 * @throws PortalException if a base nonce with the primary key could not be found
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce getBaseNonce(
			long baseNonceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getBaseNonce(baseNonceId);
	}

	/**
	 * Returns a range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.BaseNonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @return the range of base nonces
	 */
	public static java.util.List<eu.strasbourg.service.csmap.model.BaseNonce>
		getBaseNonces(int start, int end) {

		return getService().getBaseNonces(start, end);
	}

	/**
	 * Returns the number of base nonces.
	 *
	 * @return the number of base nonces
	 */
	public static int getBaseNoncesCount() {
		return getService().getBaseNoncesCount();
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

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the base nonce in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param baseNonce the base nonce
	 * @return the base nonce that was updated
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce updateBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce) {

		return getService().updateBaseNonce(baseNonce);
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	public static eu.strasbourg.service.csmap.model.BaseNonce updateBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce,
		com.liferay.portal.kernel.service.ServiceContext sc) {

		return getService().updateBaseNonce(baseNonce, sc);
	}

	public static BaseNonceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BaseNonceLocalService, BaseNonceLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BaseNonceLocalService.class);

		ServiceTracker<BaseNonceLocalService, BaseNonceLocalService>
			serviceTracker =
				new ServiceTracker
					<BaseNonceLocalService, BaseNonceLocalService>(
						bundle.getBundleContext(), BaseNonceLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}