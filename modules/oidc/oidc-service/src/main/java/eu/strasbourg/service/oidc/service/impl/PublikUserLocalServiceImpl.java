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

package eu.strasbourg.service.oidc.service.impl;

import java.io.IOException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import eu.strasbourg.service.interest.model.UserInterest;
import eu.strasbourg.service.interest.service.UserInterestLocalServiceUtil;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.base.PublikUserLocalServiceBaseImpl;

/**
 * The implementation of the publik user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.oidc.service.PublikUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PublikUserLocalServiceBaseImpl
 * @see eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil
 */
public class PublikUserLocalServiceImpl extends PublikUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil} to access
	 * the publik user local service.
	 */

	@Override
	public PublikUser createPublikUser() {
		long pk = this.counterLocalService.increment();
		return this.createPublikUser(pk);
	}
	
	/**
	 * Met à jour un projet et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public PublikUser updatePublikUser(PublikUser publikUser, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		publikUser.setUserName(user.getFullName());
		publikUser.setUserId(sc.getUserId());
		
		publikUser = this.publikUserLocalService.updatePublikUser(publikUser);
		
		return publikUser;
	}

	@Override
	public PublikUser getByPublikUserId(String publikUserId) {
		return this.publikUserPersistence.fetchByPublikId(publikUserId);
	}
	
	@Override
	public List<PublikUser> getAllPublikUsers() {
		return this.publikUserPersistence.findAll();
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public PublikUser removePublikUser(String publikUserId) {

		// Supprime le lien avec les intérêts
		List<UserInterest> userInterests = UserInterestLocalServiceUtil.getByPublikUserId(publikUserId);
		for (UserInterest userInterest : userInterests) {
			UserInterestLocalServiceUtil.deleteUserInterest(userInterest);
		}

		// Supprime le lien avec les notifications
		List<UserNotificationStatus> notifications = UserNotificationStatusLocalServiceUtil.getByPublikUserId(publikUserId);
		for (UserNotificationStatus notification : notifications) {
			UserNotificationStatusLocalServiceUtil.deleteUserNotificationStatus(notification);
		}

		// Supprimé l'entité
		PublikUser user = this.getByPublikUserId(publikUserId);
		this.publikUserPersistence.remove(user);


		return user;
	}

}