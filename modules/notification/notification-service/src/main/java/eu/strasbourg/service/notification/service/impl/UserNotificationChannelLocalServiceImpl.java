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

import java.util.ArrayList;
import java.util.List;

import eu.strasbourg.service.notification.model.UserNotificationChannel;
import eu.strasbourg.service.notification.model.NotificationChannel;
import eu.strasbourg.service.notification.service.base.UserNotificationChannelLocalServiceBaseImpl;
import eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK;

/**
 * The implementation of the user notification channel local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.notification.service.UserNotificationChannelLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationChannelLocalServiceBaseImpl
 * @see eu.strasbourg.service.notification.service.UserNotificationChannelLocalServiceUtil
 */
public class UserNotificationChannelLocalServiceImpl
	extends UserNotificationChannelLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.notification.service.UserNotificationChannelLocalServiceUtil} to access the user notification channel local service.
	 */

	/**
	 * Retourne la liste des types de notifications auxquels l'utilisateur est
	 * abonné
	 */
	@Override
	public List<NotificationChannel> getUserNotificationChannels(String publikUserId) {
		List<NotificationChannel> channels = new ArrayList<NotificationChannel>();
		List<UserNotificationChannel> userNotificationChannels = this.userNotificationChannelPersistence
				.findByPublikUserId(publikUserId);
		for (UserNotificationChannel userNotificationChannel : userNotificationChannels) {
			NotificationChannel channel = NotificationChannel.get((int) userNotificationChannel.getChannelId());
			if (channel != null) {
				channels.add(channel);
			}
		}
		return channels;
	}


	/**
	 * Remplace les abonnement existant de l'utilisateur par des nouveaux
	 */
	@Override
	public void replaceUserChannels(String publikUserId, List<NotificationChannel> channels) {
		// On supprime les anciens
		List<UserNotificationChannel> userNotificationChannels = this.userNotificationChannelPersistence
				.findByPublikUserId(publikUserId);
		for (UserNotificationChannel userNotificationChannel : userNotificationChannels) {
			this.deleteUserNotificationChannel(userNotificationChannel);
		}

		// On enregistre les nouveaux
		for (NotificationChannel channel : channels) {
			UserNotificationChannel userNotificationChannel = this
					.createUserNotificationChannel(new UserNotificationChannelPK(publikUserId, channel.getId()));
			this.updateUserNotificationChannel(userNotificationChannel);
		}
	}
}