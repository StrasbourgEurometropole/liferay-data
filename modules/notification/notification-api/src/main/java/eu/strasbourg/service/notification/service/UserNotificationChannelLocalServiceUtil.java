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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for UserNotificationChannel. This utility wraps
 * <code>eu.strasbourg.service.notification.service.impl.UserNotificationChannelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see UserNotificationChannelLocalService
 * @generated
 */
public class UserNotificationChannelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.notification.service.impl.UserNotificationChannelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the user notification channel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationChannelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationChannel the user notification channel
	 * @return the user notification channel that was added
	 */
	public static
		eu.strasbourg.service.notification.model.UserNotificationChannel
			addUserNotificationChannel(
				eu.strasbourg.service.notification.model.UserNotificationChannel
					userNotificationChannel) {

		return getService().addUserNotificationChannel(userNotificationChannel);
	}

	/**
	 * Creates a new user notification channel with the primary key. Does not add the user notification channel to the database.
	 *
	 * @param userNotificationChannelPK the primary key for the new user notification channel
	 * @return the new user notification channel
	 */
	public static
		eu.strasbourg.service.notification.model.UserNotificationChannel
			createUserNotificationChannel(
				eu.strasbourg.service.notification.service.persistence.
					UserNotificationChannelPK userNotificationChannelPK) {

		return getService().createUserNotificationChannel(
			userNotificationChannelPK);
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
	 * Deletes the user notification channel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationChannelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationChannel the user notification channel
	 * @return the user notification channel that was removed
	 */
	public static
		eu.strasbourg.service.notification.model.UserNotificationChannel
			deleteUserNotificationChannel(
				eu.strasbourg.service.notification.model.UserNotificationChannel
					userNotificationChannel) {

		return getService().deleteUserNotificationChannel(
			userNotificationChannel);
	}

	/**
	 * Deletes the user notification channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationChannelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationChannelPK the primary key of the user notification channel
	 * @return the user notification channel that was removed
	 * @throws PortalException if a user notification channel with the primary key could not be found
	 */
	public static
		eu.strasbourg.service.notification.model.UserNotificationChannel
				deleteUserNotificationChannel(
					eu.strasbourg.service.notification.service.persistence.
						UserNotificationChannelPK userNotificationChannelPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteUserNotificationChannel(
			userNotificationChannelPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationChannelModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationChannelModelImpl</code>.
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

	public static
		eu.strasbourg.service.notification.model.UserNotificationChannel
			fetchUserNotificationChannel(
				eu.strasbourg.service.notification.service.persistence.
					UserNotificationChannelPK userNotificationChannelPK) {

		return getService().fetchUserNotificationChannel(
			userNotificationChannelPK);
	}

	/**
	 * Retourne la liste des statuts de notification pour un utilisateur
	 */
	public static java.util.List
		<eu.strasbourg.service.notification.model.UserNotificationChannel>
			getByPublikUserId(String publikUserId) {

		return getService().getByPublikUserId(publikUserId);
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
	 * Returns the user notification channel with the primary key.
	 *
	 * @param userNotificationChannelPK the primary key of the user notification channel
	 * @return the user notification channel
	 * @throws PortalException if a user notification channel with the primary key could not be found
	 */
	public static
		eu.strasbourg.service.notification.model.UserNotificationChannel
				getUserNotificationChannel(
					eu.strasbourg.service.notification.service.persistence.
						UserNotificationChannelPK userNotificationChannelPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getUserNotificationChannel(
			userNotificationChannelPK);
	}

	/**
	 * Returns a range of all the user notification channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notification.model.impl.UserNotificationChannelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification channels
	 * @param end the upper bound of the range of user notification channels (not inclusive)
	 * @return the range of user notification channels
	 */
	public static java.util.List
		<eu.strasbourg.service.notification.model.UserNotificationChannel>
			getUserNotificationChannels(int start, int end) {

		return getService().getUserNotificationChannels(start, end);
	}

	/**
	 * Retourne la liste des types de notifications auxquels l'utilisateur est
	 * abonné
	 */
	public static java.util.List
		<eu.strasbourg.service.notification.model.NotificationChannel>
			getUserNotificationChannels(String publikUserId) {

		return getService().getUserNotificationChannels(publikUserId);
	}

	/**
	 * Returns the number of user notification channels.
	 *
	 * @return the number of user notification channels
	 */
	public static int getUserNotificationChannelsCount() {
		return getService().getUserNotificationChannelsCount();
	}

	/**
	 * Remplace les abonnement existant de l'utilisateur par des nouveaux
	 */
	public static void replaceUserChannels(
		String publikUserId,
		java.util.List
			<eu.strasbourg.service.notification.model.NotificationChannel>
				channels) {

		getService().replaceUserChannels(publikUserId, channels);
	}

	/**
	 * Updates the user notification channel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserNotificationChannelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userNotificationChannel the user notification channel
	 * @return the user notification channel that was updated
	 */
	public static
		eu.strasbourg.service.notification.model.UserNotificationChannel
			updateUserNotificationChannel(
				eu.strasbourg.service.notification.model.UserNotificationChannel
					userNotificationChannel) {

		return getService().updateUserNotificationChannel(
			userNotificationChannel);
	}

	public static UserNotificationChannelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<UserNotificationChannelLocalService,
		 UserNotificationChannelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			UserNotificationChannelLocalService.class);

		ServiceTracker
			<UserNotificationChannelLocalService,
			 UserNotificationChannelLocalService> serviceTracker =
				new ServiceTracker
					<UserNotificationChannelLocalService,
					 UserNotificationChannelLocalService>(
						 bundle.getBundleContext(),
						 UserNotificationChannelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}