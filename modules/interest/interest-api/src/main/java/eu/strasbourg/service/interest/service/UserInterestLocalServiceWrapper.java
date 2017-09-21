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

package eu.strasbourg.service.interest.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserInterestLocalService}.
 *
 * @author BenjaminBini
 * @see UserInterestLocalService
 * @generated
 */
@ProviderType
public class UserInterestLocalServiceWrapper implements UserInterestLocalService,
	ServiceWrapper<UserInterestLocalService> {
	public UserInterestLocalServiceWrapper(
		UserInterestLocalService userInterestLocalService) {
		_userInterestLocalService = userInterestLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userInterestLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userInterestLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userInterestLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInterestLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInterestLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the user interest to the database. Also notifies the appropriate model listeners.
	*
	* @param userInterest the user interest
	* @return the user interest that was added
	*/
	@Override
	public eu.strasbourg.service.interest.model.UserInterest addUserInterest(
		eu.strasbourg.service.interest.model.UserInterest userInterest) {
		return _userInterestLocalService.addUserInterest(userInterest);
	}

	/**
	* Creates a new user interest with the primary key. Does not add the user interest to the database.
	*
	* @param userInterestPK the primary key for the new user interest
	* @return the new user interest
	*/
	@Override
	public eu.strasbourg.service.interest.model.UserInterest createUserInterest(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK) {
		return _userInterestLocalService.createUserInterest(userInterestPK);
	}

	/**
	* Deletes the user interest from the database. Also notifies the appropriate model listeners.
	*
	* @param userInterest the user interest
	* @return the user interest that was removed
	*/
	@Override
	public eu.strasbourg.service.interest.model.UserInterest deleteUserInterest(
		eu.strasbourg.service.interest.model.UserInterest userInterest) {
		return _userInterestLocalService.deleteUserInterest(userInterest);
	}

	/**
	* Deletes the user interest with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest that was removed
	* @throws PortalException if a user interest with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.interest.model.UserInterest deleteUserInterest(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInterestLocalService.deleteUserInterest(userInterestPK);
	}

	@Override
	public eu.strasbourg.service.interest.model.UserInterest fetchUserInterest(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK) {
		return _userInterestLocalService.fetchUserInterest(userInterestPK);
	}

	/**
	* Returns the user interest with the primary key.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest
	* @throws PortalException if a user interest with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.interest.model.UserInterest getUserInterest(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userInterestLocalService.getUserInterest(userInterestPK);
	}

	/**
	* Updates the user interest in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userInterest the user interest
	* @return the user interest that was updated
	*/
	@Override
	public eu.strasbourg.service.interest.model.UserInterest updateUserInterest(
		eu.strasbourg.service.interest.model.UserInterest userInterest) {
		return _userInterestLocalService.updateUserInterest(userInterest);
	}

	/**
	* Returns the number of user interests.
	*
	* @return the number of user interests
	*/
	@Override
	public int getUserInterestsCount() {
		return _userInterestLocalService.getUserInterestsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userInterestLocalService.getOSGiServiceIdentifier();
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
		return _userInterestLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.interest.model.impl.UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userInterestLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.interest.model.impl.UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userInterestLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.interest.model.UserInterest> getByInterestId(
		long interestId) {
		return _userInterestLocalService.getByInterestId(interestId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.interest.model.UserInterest> getByPublikUserId(
		java.lang.String publikUserId) {
		return _userInterestLocalService.getByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the user interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.interest.model.impl.UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @return the range of user interests
	*/
	@Override
	public java.util.List<eu.strasbourg.service.interest.model.UserInterest> getUserInterests(
		int start, int end) {
		return _userInterestLocalService.getUserInterests(start, end);
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
		return _userInterestLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userInterestLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public UserInterestLocalService getWrappedService() {
		return _userInterestLocalService;
	}

	@Override
	public void setWrappedService(
		UserInterestLocalService userInterestLocalService) {
		_userInterestLocalService = userInterestLocalService;
	}

	private UserInterestLocalService _userInterestLocalService;
}