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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PublicHolidayLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see PublicHolidayLocalService
 * @generated
 */
@ProviderType
public class PublicHolidayLocalServiceWrapper
	implements PublicHolidayLocalService,
		ServiceWrapper<PublicHolidayLocalService> {
	public PublicHolidayLocalServiceWrapper(
		PublicHolidayLocalService publicHolidayLocalService) {
		_publicHolidayLocalService = publicHolidayLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _publicHolidayLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _publicHolidayLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _publicHolidayLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publicHolidayLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publicHolidayLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the public holiday to the database. Also notifies the appropriate model listeners.
	*
	* @param publicHoliday the public holiday
	* @return the public holiday that was added
	*/
	@Override
	public eu.strasbourg.service.place.model.PublicHoliday addPublicHoliday(
		eu.strasbourg.service.place.model.PublicHoliday publicHoliday) {
		return _publicHolidayLocalService.addPublicHoliday(publicHoliday);
	}

	/**
	* Crée une période vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.place.model.PublicHoliday createPublicHoliday(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publicHolidayLocalService.createPublicHoliday(sc);
	}

	/**
	* Creates a new public holiday with the primary key. Does not add the public holiday to the database.
	*
	* @param publicHolidayId the primary key for the new public holiday
	* @return the new public holiday
	*/
	@Override
	public eu.strasbourg.service.place.model.PublicHoliday createPublicHoliday(
		long publicHolidayId) {
		return _publicHolidayLocalService.createPublicHoliday(publicHolidayId);
	}

	/**
	* Deletes the public holiday from the database. Also notifies the appropriate model listeners.
	*
	* @param publicHoliday the public holiday
	* @return the public holiday that was removed
	*/
	@Override
	public eu.strasbourg.service.place.model.PublicHoliday deletePublicHoliday(
		eu.strasbourg.service.place.model.PublicHoliday publicHoliday) {
		return _publicHolidayLocalService.deletePublicHoliday(publicHoliday);
	}

	/**
	* Deletes the public holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publicHolidayId the primary key of the public holiday
	* @return the public holiday that was removed
	* @throws PortalException if a public holiday with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.PublicHoliday deletePublicHoliday(
		long publicHolidayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publicHolidayLocalService.deletePublicHoliday(publicHolidayId);
	}

	@Override
	public eu.strasbourg.service.place.model.PublicHoliday fetchPublicHoliday(
		long publicHolidayId) {
		return _publicHolidayLocalService.fetchPublicHoliday(publicHolidayId);
	}

	/**
	* Returns the public holiday with the primary key.
	*
	* @param publicHolidayId the primary key of the public holiday
	* @return the public holiday
	* @throws PortalException if a public holiday with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.PublicHoliday getPublicHoliday(
		long publicHolidayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publicHolidayLocalService.getPublicHoliday(publicHolidayId);
	}

	/**
	* Updates the public holiday in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param publicHoliday the public holiday
	* @return the public holiday that was updated
	*/
	@Override
	public eu.strasbourg.service.place.model.PublicHoliday updatePublicHoliday(
		eu.strasbourg.service.place.model.PublicHoliday publicHoliday) {
		return _publicHolidayLocalService.updatePublicHoliday(publicHoliday);
	}

	/**
	* Returns the number of public holidaies.
	*
	* @return the number of public holidaies
	*/
	@Override
	public int getPublicHolidaiesCount() {
		return _publicHolidayLocalService.getPublicHolidaiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _publicHolidayLocalService.getOSGiServiceIdentifier();
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
		return _publicHolidayLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _publicHolidayLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _publicHolidayLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the public holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @return the range of public holidaies
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.PublicHoliday> getPublicHolidaies(
		int start, int end) {
		return _publicHolidayLocalService.getPublicHolidaies(start, end);
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
		return _publicHolidayLocalService.dynamicQueryCount(dynamicQuery);
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
		return _publicHolidayLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public PublicHolidayLocalService getWrappedService() {
		return _publicHolidayLocalService;
	}

	@Override
	public void setWrappedService(
		PublicHolidayLocalService publicHolidayLocalService) {
		_publicHolidayLocalService = publicHolidayLocalService;
	}

	private PublicHolidayLocalService _publicHolidayLocalService;
}