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

package eu.strasbourg.service.project.service.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.SessionParamUtil;
import eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.project.exception.NoSuchProjectFollowedException;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.service.base.ProjectFollowedServiceBaseImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * The implementation of the project followed remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.ProjectFollowedService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectFollowedServiceBaseImpl
 * @see eu.strasbourg.service.project.service.ProjectFollowedServiceUtil
 */
public class ProjectFollowedServiceImpl extends ProjectFollowedServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.ProjectFollowedServiceUtil} to access the project followed remote service.
	 */

	/**
	 * Ajoute un projet à un utilisateur
	 */
	@Override
	public JSONObject addFollowerLink(long projectId, long groupId) {
		HttpServletRequest  request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		if (isLoggedIn) {
			String id = SessionParamUtil.getString(request, "publik_internal_id");

			ProjectFollowed projectFollowedExist = null;

			try {
				projectFollowedExist = this.projectFollowedPersistence.findByPublikUserIdAndProjectId(
						id, projectId);
			} catch (NoSuchProjectFollowedException e) {
				// C'est ce qu'on espere
			}
			// Si il n'est pas nul, on le supprime
			if(projectFollowedExist != null) {
				this.projectFollowedLocalService.deleteProjectFollowed(projectFollowedExist);

				return success("follower deleted");
			}

			// Création de l'objet
			ProjectFollowed projectFollowed = this.projectFollowedLocalService.createProjectFollowed();
			projectFollowed.setCreateDate(new Date());
			projectFollowed.setPublikUserId(id);
			projectFollowed.setProjectId(projectId);
			projectFollowed.setGroupId(groupId);

			this.projectFollowedLocalService.updateProjectFollowed(projectFollowed);

			return success("follower added");

		} else {
			return error("notConnected");
		}
	}

	/**
	 * Verifie si l'utilisateur courant suit le projet
	 */
	@Override
	public JSONObject isFollower(long projectId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		if (isLoggedIn) {
			String id = SessionParamUtil.getString(request, "publik_internal_id");

			try {
				this.projectFollowedPersistence.findByPublikUserIdAndProjectId(id, projectId);
			} catch (NoSuchProjectFollowedException e) {
				// Non trouve, l'utilisateur ne suit pas
				return success("false");
			}
			// Element trouve, l'utilisateur participe
			return success("true");

		} else {
			return error("notConnected");
		}
	}

	private JSONObject success(String message) {
		return JSONFactoryUtil.createJSONObject().put("success", message);
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}
}