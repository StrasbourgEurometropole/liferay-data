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

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.service.ActivityLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO,
		"mvc.command.name=saveActivity" },
	service = MVCActionCommand.class)
public class SaveActivityActionCommand extends BaseMVCActionCommand {

	private ActivityLocalService activityLocalService;

	@Reference(unbind = "-")
	protected void setActivityLocalService(
		ActivityLocalService activityLocalService) {
		this.activityLocalService = activityLocalService;
	}

	@Override
	protected void doProcessAction(ActionRequest request,
		ActionResponse response) throws Exception {
		ServiceContext sc = ServiceContextFactory.getInstance(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

		long activityId = ParamUtil.getLong(request, "activityId");
		Activity activity;
		if (activityId == 0) {
			activity = activityLocalService.createActivity(sc);
		} else {
			activity = activityLocalService.getActivity(activityId);
		}

		// Validation
		boolean isValid = validate(request);
		if (!isValid) {
			// Si pas valide : on reste sur la page d'édition
			PortalUtil.copyRequestParameters(request, response);

			PortletURL returnURL = PortletURLFactoryUtil.create(request,
				portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
			returnURL.setParameter("tab", request.getParameter("tab"));

			response.setRenderParameter("returnURL", returnURL.toString());
			response.setRenderParameter("mvcPath",
				"/activity-bo-edit-activity.jsp");
			return;
		}

		// Titre
		Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request,
			"title");
		activity.setTitleMap(title);

		// Description
		Map<Locale, String> description = LocalizationUtil
			.getLocalizationMap(request, "description");
		activity.setDescriptionMap(description);

		// Image
		long imageId = ParamUtil.getLong(request, "imageId");
		activity.setImageId(imageId);

		// Visuels
		String imagesIds = ParamUtil.getString(request, "imagesIds");
		activity.setImagesIds(imagesIds);

		// Vidéos
		String videosIds = ParamUtil.getString(request, "videosIds");
		activity.setVideosIds(videosIds);

		// Documents
		String filesIds = ParamUtil.getString(request, "filesIds");
		activity.setFilesIds(filesIds);

		// Update de l'entité
		activityLocalService.updateActivity(activity, sc);

		// Post / Redirect / Get si tout est bon
		PortletURL renderURL = PortletURLFactoryUtil.create(request,
			portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		renderURL.setParameter("tab", request.getParameter("tab"));
		response.sendRedirect(renderURL.toString());
	}

	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		if (Validator.isNull(ParamUtil.getString(request, "title"))) {
			SessionErrors.add(request, "title-error");
			isValid = false;
		}

		return isValid;
	}
}