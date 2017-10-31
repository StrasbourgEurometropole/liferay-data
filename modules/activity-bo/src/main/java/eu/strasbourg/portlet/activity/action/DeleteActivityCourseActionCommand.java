/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.strasbourg.portlet.activity.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO,
		"mvc.command.name=deleteActivityCourse" },
	service = MVCActionCommand.class)
public class DeleteActivityCourseActionCommand
	implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			long activityCourseId = ParamUtil.getLong(request, "activityCourseId");
			_activityCourseLocalService.removeActivityCourse(activityCourseId);
		} catch (PortalException e) {
			_log.error(e);
		}
		return true;
	}

	private ActivityCourseLocalService _activityCourseLocalService;

	@Reference(unbind = "-")
	protected void setActivityCourseLocalService(
		ActivityCourseLocalService activityCourseLocalService) {

		_activityCourseLocalService = activityCourseLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}