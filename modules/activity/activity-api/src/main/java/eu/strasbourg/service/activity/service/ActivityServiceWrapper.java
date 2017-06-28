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

package eu.strasbourg.service.activity.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ActivityService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityService
 * @generated
 */
@ProviderType
public class ActivityServiceWrapper implements ActivityService,
	ServiceWrapper<ActivityService> {
	public ActivityServiceWrapper(ActivityService activityService) {
		_activityService = activityService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getActivities(
		long groupId, java.lang.String name, java.lang.String language)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _activityService.getActivities(groupId, name, language);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _activityService.getOSGiServiceIdentifier();
	}

	@Override
	public ActivityService getWrappedService() {
		return _activityService;
	}

	@Override
	public void setWrappedService(ActivityService activityService) {
		_activityService = activityService;
	}

	private ActivityService _activityService;
}