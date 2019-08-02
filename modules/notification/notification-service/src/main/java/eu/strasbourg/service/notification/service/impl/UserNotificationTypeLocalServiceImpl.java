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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import eu.strasbourg.service.notification.model.UserNotificationType;
import eu.strasbourg.service.notification.service.base.UserNotificationTypeLocalServiceBaseImpl;
import eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;

/**
 * The implementation of the user notification type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.notification.service.UserNotificationTypeLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationTypeLocalServiceBaseImpl
 * @see eu.strasbourg.service.notification.service.UserNotificationTypeLocalServiceUtil
 */
public class UserNotificationTypeLocalServiceImpl extends UserNotificationTypeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.notification.service.
	 * UserNotificationTypeLocalServiceUtil} to access the user notification
	 * type local service.
	 */

	/**
	 * Retourne la liste des utilisateurs abonnés à un type de notification
	 */
	@Override
	public List<PublikUser> getUsersSubscribedToType(long typeId) {
		List<PublikUser> allUsers = PublikUserLocalServiceUtil.getPublikUsers(-1, -1);
		List<PublikUser> usersSubscribedToType = new ArrayList<PublikUser>();
		for (PublikUser user : allUsers) {
			// On vérifie que le user a un publikId (car restriction en base)
			// Cela permet d'excluer le User Anonyme qui n'a pas de PublikId
			if(Validator.isNotNull(user.getPublikId())) {
				List<UserNotificationType> userNotificationTypes = this.userNotificationTypePersistence.findByPublikUserId(user.getPublikId());
				boolean userWantsNotification = true;
				for (UserNotificationType userNotificationType : userNotificationTypes) {
					if (userNotificationType.getTypeId() == typeId) {
						userWantsNotification  = false;
						break;
					}
				}
				if (userWantsNotification) {
					usersSubscribedToType.add(user);
				}
			}
		}
		
		return usersSubscribedToType;
	}

	/**
	 * Retourne la liste des types de notifications auxquels l'utilisateur est
	 * abonné
	 */
	@Override
	public List<AssetCategory> getUserNotificationTypes(String publikUserId) {
		List<AssetCategory> types = new ArrayList<AssetCategory>();
		List<UserNotificationType> userNotificationTypes = this.userNotificationTypePersistence
				.findByPublikUserId(publikUserId);
		for (UserNotificationType userNotificationType : userNotificationTypes) {
			AssetCategory type = AssetCategoryLocalServiceUtil.fetchAssetCategory(userNotificationType.getTypeId());
			if (type != null) {
				types.add(type);
			}
		}
		return types;
	}

	/**
	 * Retourne true si l'utilisateur est abonné au type passé en paramètre
	 */
	@Override
	public boolean isUserSubscribedToType(String publikUserId, long typeId) {
		UserNotificationType userNotificationType = this.userNotificationTypeLocalService
				.fetchUserNotificationType(new UserNotificationTypePK(publikUserId, typeId));
		return userNotificationType != null;
	}

	/**
	 * Remplace les abonnement existant de l'utilisateur par des nouveaux
	 */
	@Override
	public void replaceUserSubscriptions(String publikUserId, List<AssetCategory> types) {
		// On supprime les anciens
		List<UserNotificationType> userNotificationTypes = this.userNotificationTypePersistence
				.findByPublikUserId(publikUserId);
		for (UserNotificationType userNotificationType : userNotificationTypes) {
			this.deleteUserNotificationType(userNotificationType);
		}

		// On enregistre les nouveaux
		for (AssetCategory type : types) {
			UserNotificationType userNotificationType = this
					.createUserNotificationType(new UserNotificationTypePK(publikUserId, type.getCategoryId()));
			this.updateUserNotificationType(userNotificationType);
		}
	}
}