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

import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO,
		"mvc.command.name=saveActivityCourse" },
	service = MVCActionCommand.class)
public class SaveActivityCourseActionCommand extends BaseMVCActionCommand {

	private ActivityCourseLocalService activityCourseLocalService;
	private ActivityCoursePlaceLocalService activityCoursePlaceLocalService;

	@Reference(unbind = "-")
	protected void setActivityCourseLocalService(
		ActivityCourseLocalService activityCourseLocalService) {
		this.activityCourseLocalService = activityCourseLocalService;
	}

	@Reference(unbind = "-")
	protected void setActivityCoursePlaceLocalService(
		ActivityCoursePlaceLocalService activityCoursePlace) {
		this.activityCoursePlaceLocalService = activityCoursePlace;
	}

	@Override
	protected void doProcessAction(ActionRequest request,
		ActionResponse response) throws Exception {
		ServiceContext sc = ServiceContextFactory.getInstance(request);
		long activityCourseId = ParamUtil.getLong(request, "activityCourseId");
		ActivityCourse activityCourse;
		if (activityCourseId == 0) {
			activityCourse = activityCourseLocalService
				.createActivityCourse(sc);
		} else {
			activityCourse = activityCourseLocalService
				.getActivityCourse(activityCourseId);
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
		activityCourse.setNameMap(name);

		// Activité
		long activityId = ParamUtil.getLong(request, "activityId");
		activityCourse.setActivityId(activityId);

		// Modalités
		String arrangements = ParamUtil.getString(request, "arrangements");
		activityCourse.setArrangements(arrangements);

		// Tarif
		Map<Locale, String> price = LocalizationUtil.getLocalizationMap(request,
			"price");
		activityCourse.setPriceMap(price);

		// Organisateur
		String organizerType = ParamUtil.getString(request, "organizerType");
		switch (organizerType) {
		case "emsService":
			long serviceId = ParamUtil.getLong(request, "serviceId");
			activityCourse.setServiceId(serviceId);
			break;
		case "otherService":
			long organizerId = ParamUtil.getLong(request, "organizerId");
			activityCourse.setOrganizerId(organizerId);
			break;
		}

		// Lieux
		for (ActivityCoursePlace activityCoursePlace : activityCourse
			.getActivityCoursePlaces()) {
			activityCoursePlaceLocalService.removeActivityCoursePlace(
				activityCoursePlace.getActivityCoursePlaceId());
		}
		String activityPlacesIndexesString = ParamUtil.getString(request,
			"placeIndexes");
		for (String activityPlaceIndex : activityPlacesIndexesString
			.split(",")) {
			if (Validator.isNotNull(activityPlaceIndex)) {
				ActivityCoursePlace activityCoursePlace = activityCoursePlaceLocalService
					.createActivityCoursePlace(sc);

				String placeSIGId = ParamUtil.getString(request,
					"placeSIGId" + activityPlaceIndex);
				if (Validator.isNotNull(placeSIGId)) {
					activityCoursePlace.setPlaceSIGId(placeSIGId);
				} else {
					String placeName = ParamUtil.getString(request,
						"placeName" + activityPlaceIndex);
					activityCoursePlace.setPlaceName(placeName);

					String placeStreetNumber = ParamUtil.getString(request,
						"placeStreetNumber" + activityPlaceIndex);
					activityCoursePlace.setPlaceStreetNumber(placeStreetNumber);

					String placeStreetName = ParamUtil.getString(request,
						"placeStreetName" + activityPlaceIndex);
					activityCoursePlace.setPlaceStreetName(placeStreetName);

					String placeZipCode = ParamUtil.getString(request,
						"placeZipCode" + activityPlaceIndex);
					activityCoursePlace.setPlaceZipCode(placeZipCode);

					long placeCityId = ParamUtil.getLong(request,
						"placeCityId" + activityPlaceIndex);
					activityCoursePlace.setPlaceCityId(placeCityId);
				}
				activityCoursePlace
					.setActivityCourseId(activityCourse.getActivityCourseId());

				activityCoursePlaceLocalService
					.updateActivityCoursePlace(activityCoursePlace, sc);

				// Horaires
				// TODO
			}
		}

		// Update de l'entité
		activityCourseLocalService.updateActivityCourse(activityCourse, sc);

		// Post / Redirect / Get si tout est bon
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
		PortletURL renderURL = PortletURLFactoryUtil.create(request,
			portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		renderURL.setParameter("tab", request.getParameter("tab"));
		response.sendRedirect(renderURL.toString());
	}

	/**
	 * Validation de la requête
	 */
	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		// Nom
		if (Validator.isNull(ParamUtil.getString(request, "name"))) {
			SessionErrors.add(request, "name-error");
			isValid = false;
		}

		// Service / Organisateur
		if (Validator.isNull(ParamUtil.getLong(request, "serviceId"))
			&& Validator.isNull(ParamUtil.getLong(request, "organizerId"))) {
			SessionErrors.add(request, "service-error");
			isValid = false;
		}

		return isValid;
	}
}