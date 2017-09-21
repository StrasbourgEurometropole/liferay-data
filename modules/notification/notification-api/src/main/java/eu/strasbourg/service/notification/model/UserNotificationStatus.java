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
 * The extended model interface for the UserNotificationStatus service. Represents a row in the &quot;notification_UserNotificationStatus&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see UserNotificationStatusModel
 * @see eu.strasbourg.service.notification.model.impl.UserNotificationStatusImpl
 * @see eu.strasbourg.service.notification.model.impl.UserNotificationStatusModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.notification.model.impl.UserNotificationStatusImpl")
@ProviderType
public interface UserNotificationStatus extends UserNotificationStatusModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.notification.model.impl.UserNotificationStatusImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserNotificationStatus, Long> NOTIFICATION_ID_ACCESSOR =
		new Accessor<UserNotificationStatus, Long>() {
			@Override
			public Long get(UserNotificationStatus userNotificationStatus) {
				return userNotificationStatus.getNotificationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserNotificationStatus> getTypeClass() {
				return UserNotificationStatus.class;
			}
		};

	public static final Accessor<UserNotificationStatus, String> PUBLIK_USER_ID_ACCESSOR =
		new Accessor<UserNotificationStatus, String>() {
			@Override
			public String get(UserNotificationStatus userNotificationStatus) {
				return userNotificationStatus.getPublikUserId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<UserNotificationStatus> getTypeClass() {
				return UserNotificationStatus.class;
			}
		};

	/**
	* Retourne la version JSON de l'objet
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}