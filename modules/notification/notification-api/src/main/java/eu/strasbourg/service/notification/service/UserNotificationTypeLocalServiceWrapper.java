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
 * Provides a wrapper for {@link UserNotificationTypeLocalService}.
 *
 * @author BenjaminBini
 * @see UserNotificationTypeLocalService
 * @generated
 */
public class UserNotificationTypeLocalServiceWrapper
	implements ServiceWrapper<UserNotificationTypeLocalService>,
			   UserNotificationTypeLocalService {

	public UserNotificationTypeLocalServiceWrapper(
		UserNotificationTypeLocalService userNotificationTypeLocalService) {

		_userNotificationTypeLocalService = userNotificationTypeLocalService;
	}

	/**
	 * Adds the user notification type to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationType the user notification type
	 * @return the user notification type that was added
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType
		addUserNotificationType(
			eu.strasbourg.service.notification.model.UserNotificationType
				userNotificationType) {

		return _userNotificationTypeLocalService.addUserNotificationType(
			userNotificationType);
	}

	/**
	 * Creates a new user notification type with the primary key. Does not add the user notification type to the database.
	 *
	 * @param userNotificationTypePK the primary key for the new user notification type
	 * @return the new user notification type
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType
		createUserNotificationType(
			eu.strasbourg.service.notification.service.persistence.
				UserNotificationTypePK userNotificationTypePK) {

		return _userNotificationTypeLocalService.createUserNotificationType(
			userNotificationTypePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationTypeLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the user notification type from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationType the user notification type
	 * @return the user notification type that was removed
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType
		deleteUserNotificationType(
			eu.strasbourg.service.notification.model.UserNotificationType
				userNotificationType) {

		return _userNotificationTypeLocalService.deleteUserNotificationType(
			userNotificationType);
	}

	/**
	 * Deletes the user notification type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationTypePK the primary key of the user notification type
	 * @return the user notification type that was removed
	 * @throws PortalException if a user notification type with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType
			deleteUserNotificationType(
				eu.strasbourg.service.notification.service.persistence.
					UserNotificationTypePK userNotificationTypePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationTypeLocalService.deleteUserNotificationType(
			userNotificationTypePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userNotificationTypeLocalService.dynamicQuery();
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

		return _userNotificationTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationTypeModelImpl</code>.
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

		return _userNotificationTypeLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationTypeModelImpl</code>.
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

		return _userNotificationTypeLocalService.dynamicQuery(
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

		return _userNotificationTypeLocalService.dynamicQueryCount(
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

		return _userNotificationTypeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType
		fetchUserNotificationType(
			eu.strasbourg.service.notification.service.persistence.
				UserNotificationTypePK userNotificationTypePK) {

		return _userNotificationTypeLocalService.fetchUserNotificationType(
			userNotificationTypePK);
	}

	/**
	 * Retourne la liste des statuts de notification pour un utilisateur
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.notification.model.UserNotificationType>
			getByPublikUserId(String publikUserId) {

		return _userNotificationTypeLocalService.getByPublikUserId(
			publikUserId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userNotificationTypeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationTypeLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the user notification type with the primary key.
	 *
	 * @param userNotificationTypePK the primary key of the user notification type
	 * @return the user notification type
	 * @throws PortalException if a user notification type with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType
			getUserNotificationType(
				eu.strasbourg.service.notification.service.persistence.
					UserNotificationTypePK userNotificationTypePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userNotificationTypeLocalService.getUserNotificationType(
			userNotificationTypePK);
	}

	/**
	 * Returns a range of all the user notification types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification types
	 * @param end the upper bound of the range of user notification types (not inclusive)
	 * @return the range of user notification types
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.notification.model.UserNotificationType>
			getUserNotificationTypes(int start, int end) {

		return _userNotificationTypeLocalService.getUserNotificationTypes(
			start, end);
	}

	/**
	 * Retourne la liste des types de notifications auxquels l'utilisateur est
	 * abonné
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getUserNotificationTypes(String publikUserId) {

		return _userNotificationTypeLocalService.getUserNotificationTypes(
			publikUserId);
	}

	/**
	 * Returns the number of user notification types.
	 *
	 * @return the number of user notification types
	 */
	@Override
	public int getUserNotificationTypesCount() {
		return _userNotificationTypeLocalService.
			getUserNotificationTypesCount();
	}

	/**
	 * Retourne la liste des utilisateurs abonnés à un type de notification
	 */
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.PublikUser>
		getUsersSubscribedToType(long typeId) {

		return _userNotificationTypeLocalService.getUsersSubscribedToType(
			typeId);
	}

	/**
	 * Retourne true si l'utilisateur est abonné au type passé en paramètre
	 */
	@Override
	public boolean isUserSubscribedToType(String publikUserId, long typeId) {
		return _userNotificationTypeLocalService.isUserSubscribedToType(
			publikUserId, typeId);
	}

	/**
	 * Remplace les abonnement existant de l'utilisateur par des nouveaux
	 */
	@Override
	public void replaceUserSubscriptions(
		String publikUserId,
		java.util.List<com.liferay.asset.kernel.model.AssetCategory> types) {

		_userNotificationTypeLocalService.replaceUserSubscriptions(
			publikUserId, types);
	}

	/**
	 * Updates the user notification type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationType the user notification type
	 * @return the user notification type that was updated
	 */
	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType
		updateUserNotificationType(
			eu.strasbourg.service.notification.model.UserNotificationType
				userNotificationType) {

		return _userNotificationTypeLocalService.updateUserNotificationType(
			userNotificationType);
	}

	@Override
	public UserNotificationTypeLocalService getWrappedService() {
		return _userNotificationTypeLocalService;
	}

	@Override
	public void setWrappedService(
		UserNotificationTypeLocalService userNotificationTypeLocalService) {

		_userNotificationTypeLocalService = userNotificationTypeLocalService;
	}

	private UserNotificationTypeLocalService _userNotificationTypeLocalService;

}