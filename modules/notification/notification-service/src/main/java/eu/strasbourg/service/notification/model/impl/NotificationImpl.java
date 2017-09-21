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

package eu.strasbourg.service.notification.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.notification.service.UserNotificationTypeLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;

/**
 * The extended model implementation for the Notification service. Represents a
 * row in the &quot;notification_Notification&quot; database table, with each
 * column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.notification.model.Notification}
 * interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class NotificationImpl extends NotificationBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a
	 * notification model instance should use the {@link
	 * eu.strasbourg.service.notification.model.Notification} interface instead.
	 */
	public NotificationImpl() {
	}

	/**
	 * Retourne le type de la notification
	 */
	@Override
	public AssetCategory getType() {
		return AssetCategoryLocalServiceUtil.fetchAssetCategory(this.getTypeId());
	}

	/**
	 * Retourne la liste des utilisateurs concernés par la notification
	 */
	@Override
	public List<PublikUser> getUsersToNotify() {
		List<PublikUser> users = new ArrayList<PublikUser>();

		// Si la notification ne concerne qu'un seul utilisateur
		if (this.isSingleUser()) {
			// On récupère l'utilisateur concerné
			PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(this.getSingleUserId());
			if (user != null) {
				// On vérifie qu'il est abonné au type
				if (UserNotificationTypeLocalServiceUtil.isUserSubscribedToType(this.getSingleUserId(), this.getTypeId())) {
					users.add(user);
				}
			}
		} else {
			// On récupère la liste des utilisateurs abonnés au type de la
			// notification
			users = UserNotificationTypeLocalServiceUtil.getUsersSubscribedToType(this.getTypeId());
		}
		return users;
	}

}