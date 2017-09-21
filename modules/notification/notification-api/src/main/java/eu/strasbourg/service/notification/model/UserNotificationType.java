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
 * The extended model interface for the UserNotificationType service. Represents a row in the &quot;notification_UserNotificationType&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see UserNotificationTypeModel
 * @see eu.strasbourg.service.notification.model.impl.UserNotificationTypeImpl
 * @see eu.strasbourg.service.notification.model.impl.UserNotificationTypeModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.notification.model.impl.UserNotificationTypeImpl")
@ProviderType
public interface UserNotificationType extends UserNotificationTypeModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.notification.model.impl.UserNotificationTypeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserNotificationType, String> PUBLIK_USER_ID_ACCESSOR =
		new Accessor<UserNotificationType, String>() {
			@Override
			public String get(UserNotificationType userNotificationType) {
				return userNotificationType.getPublikUserId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<UserNotificationType> getTypeClass() {
				return UserNotificationType.class;
			}
		};

	public static final Accessor<UserNotificationType, Long> TYPE_ID_ACCESSOR = new Accessor<UserNotificationType, Long>() {
			@Override
			public Long get(UserNotificationType userNotificationType) {
				return userNotificationType.getTypeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserNotificationType> getTypeClass() {
				return UserNotificationType.class;
			}
		};
}