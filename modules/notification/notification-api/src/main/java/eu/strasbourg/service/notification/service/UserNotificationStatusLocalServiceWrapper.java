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

package eu.strasbourg.service.notification.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserNotificationStatusLocalService}.
 *
 * @author BenjaminBini
 * @see UserNotificationStatusLocalService
 * @generated
 */
public class UserNotificationStatusLocalServiceWrapper
	implements ServiceWrapper<UserNotificationStatusLocalService>,
			   UserNotificationStatusLocalService {

	public UserNotificationStatusLocalServiceWrapper(
		UserNotificationStatusLocalService userNotificationStatusLocalService) {

		_userNotificationStatusLocalService =
			userNotificationStatusLocalService;
	}

	/**
	 * Adds the user notification status to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationStatus the user notification status
	 * @return the user notification status that was added
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus
		addUserNotificationStatus(
			eu.strasbourg.service.notification.model.UserNotificationStatus
				userNotificationStatus) {

		return _userNotificationStatusLocalService.addUserNotificationStatus(
			userNotificationStatus);
	}

	/**
	 * Creates a new user notification status with the primary key. Does not add the user notification status to the database.
	 *
	 * @param userNotificationStatusPK the primary key for the new user notification status
	 * @return the new user notification status
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus
		createUserNotificationStatus(
			eu.strasbourg.service.notification.service.persistence.
				UserNotificationStatusPK userNotificationStatusPK) {

		return _userNotificationStatusLocalService.createUserNotificationStatus(
			userNotificationStatusPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationStatusLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the user notification status from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationStatus the user notification status
	 * @return the user notification status that was removed
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus
		deleteUserNotificationStatus(
			eu.strasbourg.service.notification.model.UserNotificationStatus
				userNotificationStatus) {

		return _userNotificationStatusLocalService.deleteUserNotificationStatus(
			userNotificationStatus);
	}

	/**
	 * Deletes the user notification status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationStatusPK the primary key of the user notification status
	 * @return the user notification status that was removed
	 * @throws PortalException if a user notification status with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus
			deleteUserNotificationStatus(
				eu.strasbourg.service.notification.service.persistence.
					UserNotificationStatusPK userNotificationStatusPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationStatusLocalService.deleteUserNotificationStatus(
			userNotificationStatusPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userNotificationStatusLocalService.dynamicQuery();
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

		return _userNotificationStatusLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationStatusModelImpl</code>.
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

		return _userNotificationStatusLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationStatusModelImpl</code>.
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

		return _userNotificationStatusLocalService.dynamicQuery(
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

		return _userNotificationStatusLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _userNotificationStatusLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus
		fetchUserNotificationStatus(
			eu.strasbourg.service.notification.service.persistence.
				UserNotificationStatusPK userNotificationStatusPK) {

		return _userNotificationStatusLocalService.fetchUserNotificationStatus(
			userNotificationStatusPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userNotificationStatusLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retourne la liste des statuts de notification pour une notification
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.notification.model.UserNotificationStatus>
			getByNotificationId(long notificationId) {

		return _userNotificationStatusLocalService.getByNotificationId(
			notificationId);
	}

	/**
	 * Retourne la liste des statuts de notification pour un utilisateur
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.notification.model.UserNotificationStatus>
			getByPublikUserId(String publikUserId) {

		return _userNotificationStatusLocalService.getByPublikUserId(
			publikUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userNotificationStatusLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userNotificationStatusLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationStatusLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Retourne la liste des statuts de notification pour un utilisateur
	 */
	@Override
	public long getUnreadNotificationCount(String publikUserId) {
		return _userNotificationStatusLocalService.getUnreadNotificationCount(
			publikUserId);
	}

	/**
	 * Returns the user notification status with the primary key.
	 *
	 * @param userNotificationStatusPK the primary key of the user notification status
	 * @return the user notification status
	 * @throws PortalException if a user notification status with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus
			getUserNotificationStatus(
				eu.strasbourg.service.notification.service.persistence.
					UserNotificationStatusPK userNotificationStatusPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationStatusLocalService.getUserNotificationStatus(
			userNotificationStatusPK);
	}

	/**
	 * Returns a range of all the user notification statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification statuses
	 * @param end the upper bound of the range of user notification statuses (not inclusive)
	 * @return the range of user notification statuses
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.notification.model.UserNotificationStatus>
			getUserNotificationStatuses(int start, int end) {

		return _userNotificationStatusLocalService.getUserNotificationStatuses(
			start, end);
	}

	/**
	 * Returns the number of user notification statuses.
	 *
	 * @return the number of user notification statuses
	 */
	@Override
	public int getUserNotificationStatusesCount() {
		return _userNotificationStatusLocalService.
			getUserNotificationStatusesCount();
	}

	/**
	 * Updates the user notification status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationStatus the user notification status
	 * @return the user notification status that was updated
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus
		updateUserNotificationStatus(
			eu.strasbourg.service.notification.model.UserNotificationStatus
				userNotificationStatus) {

		return _userNotificationStatusLocalService.updateUserNotificationStatus(
			userNotificationStatus);
	}

	@Override
	public UserNotificationStatusLocalService getWrappedService() {
		return _userNotificationStatusLocalService;
	}

	@Override
	public void setWrappedService(
		UserNotificationStatusLocalService userNotificationStatusLocalService) {

		_userNotificationStatusLocalService =
			userNotificationStatusLocalService;
	}

	private UserNotificationStatusLocalService
		_userNotificationStatusLocalService;

}