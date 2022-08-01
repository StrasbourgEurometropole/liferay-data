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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BaseNonceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BaseNonceLocalService
 * @generated
 */
public class BaseNonceLocalServiceWrapper
	implements BaseNonceLocalService, ServiceWrapper<BaseNonceLocalService> {

	public BaseNonceLocalServiceWrapper(
		BaseNonceLocalService baseNonceLocalService) {

		_baseNonceLocalService = baseNonceLocalService;
	}

	/**
	 * Adds the base nonce to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BaseNonceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param baseNonce the base nonce
	 * @return the base nonce that was added
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce addBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce) {

		return _baseNonceLocalService.addBaseNonce(baseNonce);
	}

	/**
	 * Creates a new base nonce with the primary key. Does not add the base nonce to the database.
	 *
	 * @param baseNonceId the primary key for the new base nonce
	 * @return the new base nonce
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce createBaseNonce(
		long baseNonceId) {

		return _baseNonceLocalService.createBaseNonce(baseNonceId);
	}

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce createBaseNonce(
		com.liferay.portal.kernel.service.ServiceContext sc) {

		return _baseNonceLocalService.createBaseNonce(sc);
	}

	/**
	 * Deletes the base nonce from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BaseNonceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param baseNonce the base nonce
	 * @return the base nonce that was removed
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce deleteBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce) {

		return _baseNonceLocalService.deleteBaseNonce(baseNonce);
	}

	/**
	 * Deletes the base nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BaseNonceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce that was removed
	 * @throws PortalException if a base nonce with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce deleteBaseNonce(
			long baseNonceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _baseNonceLocalService.deleteBaseNonce(baseNonceId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _baseNonceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _baseNonceLocalService.dynamicQuery();
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

		return _baseNonceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.BaseNonceModelImpl</code>.
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

		return _baseNonceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.BaseNonceModelImpl</code>.
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

		return _baseNonceLocalService.dynamicQuery(
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

		return _baseNonceLocalService.dynamicQueryCount(dynamicQuery);
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

		return _baseNonceLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce fetchBaseNonce(
		long baseNonceId) {

		return _baseNonceLocalService.fetchBaseNonce(baseNonceId);
	}

	/**
	 * Retrouve un baseNonce par sa valeur
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce fetchByValue(
		String value) {

		return _baseNonceLocalService.fetchByValue(value);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _baseNonceLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the base nonce with the primary key.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce
	 * @throws PortalException if a base nonce with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce getBaseNonce(
			long baseNonceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _baseNonceLocalService.getBaseNonce(baseNonceId);
	}

	/**
	 * Returns a range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.BaseNonceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @return the range of base nonces
	 */
	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.BaseNonce>
		getBaseNonces(int start, int end) {

		return _baseNonceLocalService.getBaseNonces(start, end);
	}

	/**
	 * Returns the number of base nonces.
	 *
	 * @return the number of base nonces
	 */
	@Override
	public int getBaseNoncesCount() {
		return _baseNonceLocalService.getBaseNoncesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _baseNonceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _baseNonceLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _baseNonceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the base nonce in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BaseNonceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param baseNonce the base nonce
	 * @return the base nonce that was updated
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce updateBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce) {

		return _baseNonceLocalService.updateBaseNonce(baseNonce);
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public eu.strasbourg.service.csmap.model.BaseNonce updateBaseNonce(
		eu.strasbourg.service.csmap.model.BaseNonce baseNonce,
		com.liferay.portal.kernel.service.ServiceContext sc) {

		return _baseNonceLocalService.updateBaseNonce(baseNonce, sc);
	}

	@Override
	public BaseNonceLocalService getWrappedService() {
		return _baseNonceLocalService;
	}

	@Override
	public void setWrappedService(BaseNonceLocalService baseNonceLocalService) {
		_baseNonceLocalService = baseNonceLocalService;
	}

	private BaseNonceLocalService _baseNonceLocalService;

}