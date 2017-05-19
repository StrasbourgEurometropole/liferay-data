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

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO,
		"mvc.command.name=saveActivityOrganizer" },
	service = MVCActionCommand.class)
public class SaveActivityOrganizerActionCommand extends BaseMVCActionCommand {

	private ActivityOrganizerLocalService activityOrganizerLocalService;

	@Reference(unbind = "-")
	protected void setActivityOrganizerLocalService(
		ActivityOrganizerLocalService activityOrganizerLocalService) {
		this.activityOrganizerLocalService = activityOrganizerLocalService;
	}

	@Override
	protected void doProcessAction(ActionRequest request,
		ActionResponse response) throws Exception {
		ServiceContext sc = ServiceContextFactory.getInstance(request);
		long activityOrganizerId = ParamUtil.getLong(request,
			"activityOrganizerId");
		ActivityOrganizer activityOrganizer;
		if (activityOrganizerId == 0) {
			activityOrganizer = activityOrganizerLocalService
				.createActivityOrganizer(sc);
		} else {
			activityOrganizer = activityOrganizerLocalService
				.getActivityOrganizer(activityOrganizerId);
		}

		// Validation
		boolean isValid = validate(request);

		if (!isValid) {
			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath",
				"/activity-bo-edit-organizer.jsp");
			return;
		}

		// Nom
		Map<Locale, String> name = LocalizationUtil.getLocalizationMap(request,
			"name");
		activityOrganizer.setNameMap(name);

		// Coordonnées
		Map<Locale, String> contactInformation = LocalizationUtil
			.getLocalizationMap(request, "contactInformation");
		activityOrganizer.setContactInformationMap(contactInformation);

		// Image
		long imageId = ParamUtil.getLong(request, "imageId");
		activityOrganizer.setImageId(imageId);

		// Update de l'entité
		activityOrganizerLocalService.updateActivityOrganizer(activityOrganizer,
			sc);

		// Post / Redirect / Get si tout est bon
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
		PortletURL renderURL = PortletURLFactoryUtil.create(request,
			portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		renderURL.setParameter("tab", request.getParameter("tab"));
		response.sendRedirect(renderURL.toString());
	}

	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		if (Validator.isNull(ParamUtil.getString(request, "name"))) {
			SessionErrors.add(request, "name-error");
			isValid = false;
		}

		return isValid;
	}
}