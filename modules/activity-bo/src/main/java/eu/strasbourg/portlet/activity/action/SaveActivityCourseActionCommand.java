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
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.language.LanguageUtil;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalService;
import eu.strasbourg.service.activity.service.ActivityCourseScheduleLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO,
		"mvc.command.name=saveActivityCourse" },
	service = MVCActionCommand.class)
public class SaveActivityCourseActionCommand extends BaseMVCActionCommand {

	private ActivityCourseLocalService activityCourseLocalService;
	private ActivityCoursePlaceLocalService activityCoursePlaceLocalService;
	private ActivityCourseScheduleLocalService activityCourseScheduleLocalService;

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

	@Reference(unbind = "-")
	protected void setActivityCourseScheduleLocalService(
		ActivityCourseScheduleLocalService activityCourseSchedule) {
		this.activityCourseScheduleLocalService = activityCourseSchedule;
	}

	@Override
	protected void doProcessAction(ActionRequest request,
		ActionResponse response) throws Exception {
		ServiceContext sc = ServiceContextFactory.getInstance(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

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
			// Si pas valide : on reste sur la page d'édition
			PortalUtil.copyRequestParameters(request, response);

			PortletURL returnURL = PortletURLFactoryUtil.create(request,
				portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
			returnURL.setParameter("tab", request.getParameter("tab"));

			response.setRenderParameter("returnURL", returnURL.toString());
			response.setRenderParameter("mvcPath",
				"/activity-bo-edit-course.jsp");
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
		Map<Locale, String> arrangements = LocalizationUtil.getLocalizationMap(request,
			"arrangements");
		activityCourse.setArrangementsMap(arrangements);

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
			activityCourse.setOrganizerId(0);
			break;
		case "otherService":
			long organizerId = ParamUtil.getLong(request, "organizerId");
			activityCourse.setOrganizerId(organizerId);
			activityCourse.setServiceId(0);
			break;
		}

		// Lieux
		for (ActivityCoursePlace activityCoursePlace : activityCourse
			.getActivityCoursePlaces()) {
			// On supprime d'abord les lieux existants
			activityCoursePlaceLocalService.removeActivityCoursePlace(
				activityCoursePlace.getActivityCoursePlaceId());
		}
		String activityPlacesIndexesString = ParamUtil.getString(request,
			"placeIndexes");
		for (String activityPlaceIndex : activityPlacesIndexesString
			.split(",")) {
			// Puis on crée les nouveaux
			if (Validator.isNotNull(activityPlaceIndex)) {
				// Initialisation de l'entité
				ActivityCoursePlace activityCoursePlace = activityCoursePlaceLocalService
					.createActivityCoursePlace(sc);

				String placeSIGId = ParamUtil.getString(request,
					"placeSIGId" + activityPlaceIndex);
				if (Validator.isNotNull(placeSIGId)) {
					// Lieu SIG
					activityCoursePlace.setPlaceSIGId(placeSIGId);
				} else {
					// Nom du lieu
					String placeName = ParamUtil.getString(request,
						"placeName" + activityPlaceIndex);
					activityCoursePlace.setPlaceName(placeName);

					// Numéro de rue
					String placeStreetNumber = ParamUtil.getString(request,
						"placeStreetNumber" + activityPlaceIndex);
					activityCoursePlace.setPlaceStreetNumber(placeStreetNumber);

					// Nom de la rue
					String placeStreetName = ParamUtil.getString(request,
						"placeStreetName" + activityPlaceIndex);
					activityCoursePlace.setPlaceStreetName(placeStreetName);

					// Code postal
					String placeZipCode = ParamUtil.getString(request,
						"placeZipCode" + activityPlaceIndex);
					activityCoursePlace.setPlaceZipCode(placeZipCode);

					// Ville
					long placeCityId = ParamUtil.getLong(request,
						"placeCityId" + activityPlaceIndex);
					activityCoursePlace.setPlaceCityId(placeCityId);
				}

				// Rattachement au cours
				activityCoursePlace
					.setActivityCourseId(activityCourse.getActivityCourseId());

				// Mise à jour en base
				activityCoursePlaceLocalService
					.updateActivityCoursePlace(activityCoursePlace, sc);

				// Horaires
				for (ActivityCourseSchedule activityCourseSchedule : activityCoursePlace
					.getActivityCourseSchedules()) {
					// On supprime d'abord les horaires existants
					activityCourseScheduleLocalService
						.removeActivityCourseSchedule(activityCourseSchedule
							.getActivityCourseScheduleId());
				}
				int scheduleCount = ParamUtil.getInteger(request,
					"scheduleCount_" + activityPlaceIndex);
				for (int i = 0; i < scheduleCount; i++) {
					// Initialisation de l'entité
					ActivityCourseSchedule activityCourseSchedule = activityCourseScheduleLocalService
						.createActivityCourseSchedule(sc);

					// Périodes
					long[] periodIds = ParamUtil.getLongValues(request,
						"periods_" + activityPlaceIndex + "_" + i);
					activityCourseSchedule
						.setPeriodsIds(StringUtil.merge(periodIds));

					// Heures de début et de fin
					String startTime = ParamUtil.getString(request,
						"startTime_" + activityPlaceIndex + "_" + i);
					String endTime = ParamUtil.getString(request,
						"endTime_" + activityPlaceIndex + "_" + i);
					activityCourseSchedule.setStartTime(startTime);
					activityCourseSchedule.setEndTime(endTime);

					// Jours concernés
					boolean monday = ParamUtil.getBoolean(request,
						"monday_" + activityPlaceIndex + "_" + i);
					boolean tuesday = ParamUtil.getBoolean(request,
						"tuesday_" + activityPlaceIndex + "_" + i);
					boolean wednesday = ParamUtil.getBoolean(request,
						"wednesday_" + activityPlaceIndex + "_" + i);
					boolean thursday = ParamUtil.getBoolean(request,
						"thursday_" + activityPlaceIndex + "_" + i);
					boolean friday = ParamUtil.getBoolean(request,
						"friday_" + activityPlaceIndex + "_" + i);
					boolean saturday = ParamUtil.getBoolean(request,
						"saturday_" + activityPlaceIndex + "_" + i);
					boolean sunday = ParamUtil.getBoolean(request,
						"sunday_" + activityPlaceIndex + "_" + i);
					activityCourseSchedule.setMonday(monday);
					activityCourseSchedule.setTuesday(tuesday);
					activityCourseSchedule.setWednesday(wednesday);
					activityCourseSchedule.setThursday(thursday);
					activityCourseSchedule.setFriday(friday);
					activityCourseSchedule.setSaturday(saturday);
					activityCourseSchedule.setSunday(sunday);



					// Commentaires
					Set<Locale> availableLocalesSet = LanguageUtil
						.getAvailableLocales(themeDisplay.getScopeGroupId());
					for (Locale locale : availableLocalesSet) {
						String commentInLocale = ParamUtil.getString(request,
							"comments_" + locale + "_" + activityPlaceIndex
								+ "_" + i);
						activityCourseSchedule.setComments(commentInLocale, locale);
					}
					
					// Rattachement au lieu
					activityCourseSchedule.setActivityCoursePlaceId(
						activityCoursePlace.getActivityCoursePlaceId());

					// Mise à jour en base
					this.activityCourseScheduleLocalService
						.updateActivityCourseSchedule(activityCourseSchedule);

				}
			}
		}

		// Update de l'entité
		activityCourseLocalService.updateActivityCourse(activityCourse, sc);

		// Post / Redirect / Get si tout est bon
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

		// Lieux
		String activityPlacesIndexesString = ParamUtil.getString(request,
			"placeIndexes");
		for (String activityPlaceIndex : activityPlacesIndexesString
			.split(",")) {
			if (Validator.isNotNull(activityPlaceIndex)) {
				String placeSIGId = ParamUtil.getString(request,
					"placeSIGId" + activityPlaceIndex);
				String placeName = ParamUtil.getString(request,
					"placeName" + activityPlaceIndex);
				long placeCityId = ParamUtil.getLong(request,
					"placeCityId" + activityPlaceIndex);
				if (Validator.isNull(placeSIGId) && (Validator.isNull(placeName)
					|| Validator.isNull(placeCityId))) {
					SessionErrors.add(request, "place-error");
					isValid = false;
					break;
				}
			}
		}

		return isValid;
	}
}