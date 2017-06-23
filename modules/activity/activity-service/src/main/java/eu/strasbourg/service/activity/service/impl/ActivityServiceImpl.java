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

package eu.strasbourg.service.activity.service.impl;

import java.util.List;
import java.util.Locale;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.util.LocaleUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.service.activity.service.base.ActivityServiceBaseImpl;

/**
 * The implementation of the activity remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.activity.service.ActivityService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.ActivityServiceUtil
 */
@ProviderType
@AccessControlled(guestAccessEnabled = true)
public class ActivityServiceImpl extends ActivityServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.activity.service.ActivityServiceUtil} to access the
	 * activity remote service.
	 */

	@Override
	public JSONArray getActivities(long groupId, String name,
		String language) throws PortalException {
		Locale locale = LocaleUtil.fromLanguageId(language);
		List<Activity> activities = ActivityLocalServiceUtil
			.searchActivities(groupId, name, locale);
		return this.getApprovedJSONActivities(activities);
	}

	private JSONArray getApprovedJSONActivities(List<Activity> activities) {
		JSONArray jsonActivities = JSONFactoryUtil.createJSONArray();
		for (Activity activity : activities) {
			try {
				if (activity.isApproved()) {
					jsonActivities.put(activity.toJSON());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonActivities;
	}
}