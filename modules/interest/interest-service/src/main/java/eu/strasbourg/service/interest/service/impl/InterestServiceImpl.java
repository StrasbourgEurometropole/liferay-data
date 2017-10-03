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

package eu.strasbourg.service.interest.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.model.UserInterest;
import eu.strasbourg.service.interest.service.base.InterestServiceBaseImpl;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * The implementation of the interest remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.interest.service.InterestService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author BenjaminBini
 * @see InterestServiceBaseImpl
 * @see eu.strasbourg.service.interest.service.InterestServiceUtil
 */
public class InterestServiceImpl extends InterestServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.interest.service.InterestServiceUtil} to access the
	 * interest remote service.
	 */
	private Log log = LogFactoryUtil.getLog(this.getClass());

	/**
	 * Retourne la liste de tous les centres d'intérêt
	 */
	@Override
	public JSONObject getInterests() {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Interest> interests = this.interestLocalService.getInterests(-1, -1);
		JSONArray jsonInterests = this.getApprovedJSONInterests(interests);
		result.put("interests", jsonInterests);
		return result;
	}

	/**
	 * Modifie les intérêts de l'utilisateur ayant l'id (publik) passé en
	 * paramètre
	 */
	@Override
	public JSONObject setUserInterests(String userId, String interestIds) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		try {
			if (Validator.isNull(userId)) {
				return error("wrong parameters");
			}
			List<Long> interestIdsList = new ArrayList<Long>();
			for (String interestIdStr : interestIds.split(",")) {
				if (Validator.isNull(interestIdStr)) {
					break;
				}
				long interestId = 0;
				try {
					interestId = Long.valueOf(interestIdStr);
				} catch (Exception ex) {
					return error("invalid format for id of interest (" + interestIdStr + ")");
				}
				Interest interest = this.interestLocalService.fetchInterest(interestId);
				if (interest == null) {
					return error("interest does not exist (" + interestId + ")");
				}
				interestIdsList.add(interestId);
			}
			this.interestLocalService.setUserInterests(userId,
					Arrays.stream(interestIdsList.toArray(new Long[0])).mapToLong(Long::longValue).toArray());
			return this.getUserInterests(userId);
		} catch (Exception ex) {
			log.error(ex);
			return error("unknown error");
		}
	}

	/**
	 * Retourne la liste des intérêts de l'utilisateur ayant l'id (publik) passé
	 * en paramètre
	 */
	@Override
	public JSONObject getUserInterests(String userId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		if (Validator.isNull(userId)) {
			return error("wrong parameters");
		}
		List<UserInterest> userInterests = this.userInterestLocalService.getByPublikUserId(userId);
		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put("userId", userId);
		JSONArray interestsArray = JSONFactoryUtil.createJSONArray();
		for (UserInterest userInterest : userInterests) {
			interestsArray.put(userInterest.getInterestId());
		}
		json.put("interests", interestsArray);
		return json;
	}

	private JSONArray getApprovedJSONInterests(List<Interest> interests) {
		JSONArray jsonEvents = JSONFactoryUtil.createJSONArray();
		for (Interest interest : interests) {
			if (interest.isApproved()) {
				jsonEvents.put(interest.toJSON());
			}
		}
		return jsonEvents;
	}

	private boolean isAuthorized() {
		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long globalGroupId = defaultCompany.getGroup().getGroupId();
			return this.getPermissionChecker().hasPermission(globalGroupId, StrasbourgPortletKeys.INTEREST_BO,
					StrasbourgPortletKeys.INTEREST_BO, "CONTRIBUTE");
		} catch (PortalException e) {
			log.error(e);
			return false;
		}
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}
}