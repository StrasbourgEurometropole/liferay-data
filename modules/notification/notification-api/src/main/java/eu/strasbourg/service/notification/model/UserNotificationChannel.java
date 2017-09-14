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

package eu.strasbourg.service.notification.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the UserNotificationChannel service. Represents a row in the &quot;notification_UserNotificationChannel&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see UserNotificationChannelModel
 * @see eu.strasbourg.service.notification.model.impl.UserNotificationChannelImpl
 * @see eu.strasbourg.service.notification.model.impl.UserNotificationChannelModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.notification.model.impl.UserNotificationChannelImpl")
@ProviderType
public interface UserNotificationChannel extends UserNotificationChannelModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.notification.model.impl.UserNotificationChannelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserNotificationChannel, String> PUBLIK_USER_ID_ACCESSOR =
		new Accessor<UserNotificationChannel, String>() {
			@Override
			public String get(UserNotificationChannel userNotificationChannel) {
				return userNotificationChannel.getPublikUserId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<UserNotificationChannel> getTypeClass() {
				return UserNotificationChannel.class;
			}
		};

	public static final Accessor<UserNotificationChannel, Long> CHANNEL_ID_ACCESSOR =
		new Accessor<UserNotificationChannel, Long>() {
			@Override
			public Long get(UserNotificationChannel userNotificationChannel) {
				return userNotificationChannel.getChannelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserNotificationChannel> getTypeClass() {
				return UserNotificationChannel.class;
			}
		};
}