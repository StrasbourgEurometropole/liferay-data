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
package eu.strasbourg.portlet.agenda.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.EventPeriodLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=saveEvent" },
	service = MVCActionCommand.class)
public class SaveEventActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getCompanyGroupId());
			long eventId = ParamUtil.getLong(request, "eventId");
			Event event;
			if (eventId == 0) {
				event = _eventLocalService.createEvent(sc);
			} else {
				event = _eventLocalService.getEvent(eventId);
			}

			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			event.setTitleMap(title);

			Map<Locale, String> subtitle = LocalizationUtil
				.getLocalizationMap(request, "subtitle");
			event.setSubtitleMap(subtitle);

			Map<Locale, String> description = LocalizationUtil
				.getLocalizationMap(request, "description");
			event.setDescriptionMap(description);

			Long imageId = ParamUtil.getLong(request, "imageId");
			if (imageId > 0) {
				event.setImageId(imageId);
				event.setExternalImageURL("");
				event.setExternalImageCopyright("");
			} else {
				event.setImageId(null);
				String externalImageURL = ParamUtil.getString(request,
					"externalImageURL");
				event.setExternalImageURL(externalImageURL);

				String externalImageCopyright = ParamUtil.getString(request,
					"externalImageCopyright");
				event.setExternalImageCopyright(externalImageCopyright);
			}

			String placeSIGId = ParamUtil.getString(request, "placeSIGId");
			if (Validator.isNotNull(placeSIGId)) {
				event.setPlaceSIGId(placeSIGId);
				event.setPlaceName("");
				event.setPlaceStreetNumber("");
				event.setPlaceStreetName("");
				event.setPlaceZipCode("");
				event.setPlaceCountry("");
			} else {
				event.setPlaceSIGId("");

				String placeName = ParamUtil.getString(request, "placeName");
				event.setPlaceName(placeName);

				String placeStreetNumber = ParamUtil.getString(request,
					"placeStreetNumber");
				event.setPlaceStreetNumber(placeStreetNumber);

				String placeStreetName = ParamUtil.getString(request,
					"placeStreetName");
				event.setPlaceStreetName(placeStreetName);

				String placeZipCode = ParamUtil.getString(request,
					"placeZipCode");
				event.setPlaceZipCode(placeZipCode);

				String placeCity = ParamUtil.getString(request, "placeCity");
				event.setPlaceCity(placeCity);

				String placeCountry = ParamUtil.getString(request,
					"placeCountry");
				event.setPlaceCountry(placeCountry);
			}

			Map<Locale, String> access = LocalizationUtil
				.getLocalizationMap(request, "access");
			event.setAccessMap(access);

			Map<Locale, String> accessForDisabled = LocalizationUtil
				.getLocalizationMap(request, "accessForDisabled");
			event.setAccessForDisabledMap(accessForDisabled);

			Boolean accessForBlind = ParamUtil.getBoolean(request,
				"accessForBlind");
			event.setAccessForBlind(accessForBlind);

			Boolean accessForWheelchair = ParamUtil.getBoolean(request,
				"accessForWheelchair");
			event.setAccessForWheelchair(accessForWheelchair);

			Boolean accessForDeaf = ParamUtil.getBoolean(request,
				"accessForDeaf");
			event.setAccessForDeaf(accessForDeaf);

			Boolean accessForElder = ParamUtil.getBoolean(request,
				"accessForElder");
			event.setAccessForElder(accessForElder);

			Boolean accessForDeficient = ParamUtil.getBoolean(request,
				"accessForDeficient");
			event.setAccessForDeficient(accessForDeficient);

			String promoter = ParamUtil.getString(request, "promoter");
			event.setPromoter(promoter);

			String phone = ParamUtil.getString(request, "phone");
			event.setPhone(phone);

			String email = ParamUtil.getString(request, "email");
			event.setEmail(email);

			Map<Locale, String> websiteName = LocalizationUtil
				.getLocalizationMap(request, "websiteName");
			event.setWebsiteNameMap(websiteName);

			Map<Locale, String> websiteURL = LocalizationUtil
				.getLocalizationMap(request, "websiteURL");
			event.setWebsiteURLMap(websiteURL);

			String source = ParamUtil.getString(request, "source");
			event.setSource(source);

			Map<Locale, String> scheduleComments = LocalizationUtil
				.getLocalizationMap(request, "scheduleComments");
			event.setScheduleCommentsMap(scheduleComments);

			Integer free = ParamUtil.getInteger(request, "free");
			event.setFree(free);

			Map<Locale, String> price = LocalizationUtil
				.getLocalizationMap(request, "price");
			event.setPriceMap(price);

			String displayDateString = ParamUtil.getString(request,
				"displayDate");
			Date displayDate = DateUtil.parseDate(displayDateString,
				request.getLocale());
			event.setDisplayDate(displayDate);

			List<Manifestation> oldManifestations = event.getManifestations();
			for (Manifestation manifestation : oldManifestations) {
				_eventLocalService.deleteManifestationEvent(
					manifestation.getManifestationId(), event);
			}
			long[] manifestationsIds = ParamUtil.getLongValues(request,
				"manifestationsIds");
			for (long manifestationId : manifestationsIds) {
				if (manifestationId > 0) {
					_eventLocalService.addManifestationEvent(manifestationId,
						event);
				}
			}

			/**
			 * Périodes de l'événement
			 */
			// Suppression des anciennes périodes
			List<EventPeriod> oldPeriods = event.getEventPeriods();
			for (EventPeriod eventPeriod : oldPeriods) {
				_eventPeriodLocalService.deleteEventPeriod(eventPeriod);
			}
			// Ajout des nouvelles
			String periodsIndexesString = ParamUtil.getString(request,
				"periodIndexes");
			for (String periodIndex : periodsIndexesString.split(",")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				if (Validator.isNotNull(periodIndex)
					&& Validator.isNotNull(
						ParamUtil.getString(request, "startDate" + periodIndex))
					&& Validator.isNotNull(ParamUtil.getString(request,
						"endDate" + periodIndex))) {

					Date startDate = ParamUtil.getDate(request,
						"startDate" + periodIndex, dateFormat);
					Date endDate = ParamUtil.getDate(request,
						"endDate" + periodIndex, dateFormat);
					Map<Locale, String> timeDetail = LocalizationUtil
						.getLocalizationMap(request,
							"timeDetail" + periodIndex);

					EventPeriod eventPeriod = _eventPeriodLocalService
						.createEventPeriod();
					eventPeriod.setStartDate(startDate);
					eventPeriod.setEndDate(endDate);
					eventPeriod.setTimeDetailMap(timeDetail);
					eventPeriod.setEventId(event.getEventId());
					this._eventPeriodLocalService
						.updateEventPeriod(eventPeriod);
				}
			}
			// On classe les périodes par date de début, ce qui va nous
			// permettre
			// de setter les champs "firstStartDate" et "lastEndDate" sur
			// l'événement
			if (event.getEventPeriods().size() > 0) {
				List<EventPeriod> periods = new ArrayList<EventPeriod>(
					event.getEventPeriods());
				periods.sort(
					(p1, p2) -> p1.getStartDate().compareTo(p2.getStartDate()));

				Date firstStartDate = periods.get(0).getStartDate();
				Date lastEndDate = periods.get(periods.size() - 1).getEndDate();
				event.setFirstStartDate(firstStartDate);
				event.setLastEndDate(lastEndDate);
			}

			_eventLocalService.updateEvent(event, sc);

		} catch (PortalException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;
	}

	private EventLocalService _eventLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {

		_eventLocalService = eventLocalService;
	}

	private EventPeriodLocalService _eventPeriodLocalService;

	@Reference(unbind = "-")
	protected void setEventPeriodLocalService(
		EventPeriodLocalService eventPeriodLocalService) {

		_eventPeriodLocalService = eventPeriodLocalService;
	}

}