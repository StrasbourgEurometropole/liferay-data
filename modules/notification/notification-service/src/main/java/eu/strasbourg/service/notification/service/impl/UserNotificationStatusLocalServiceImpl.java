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

package eu.strasbourg.service.notification.service.impl;

import java.util.List;

import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.base.UserNotificationStatusLocalServiceBaseImpl;

/**
 * The implementation of the user notification status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.notification.service.UserNotificationStatusLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationStatusLocalServiceBaseImpl
 * @see eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil
 */
public class UserNotificationStatusLocalServiceImpl extends UserNotificationStatusLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.notification.service.
	 * UserNotificationStatusLocalServiceUtil} to access the user notification
	 * status local service.
	 */
	
	/**
	 * Retourne la liste des statuts de notification pour une notification
	 */
	@Override
	public List<UserNotificationStatus> getByNotificationId(long notificationId) {
		return this.userNotificationStatusPersistence.findByNotificationId(notificationId);
	}
	
	/**
	 * Retourne la liste des statuts de notification pour un utilisateur
	 */
	@Override
	public List<UserNotificationStatus> getByUserId(long publikUserId) {
		return this.userNotificationStatusPersistence.findByPublikUserId(publikUserId);
	}
}