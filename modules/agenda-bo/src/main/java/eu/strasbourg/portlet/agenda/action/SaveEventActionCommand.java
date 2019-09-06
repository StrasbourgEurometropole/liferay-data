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

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.EventPeriodLocalService;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.FileEntryHelper;
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
			
			// Validation
			boolean isValid = validate(request);
			if (!isValid) {
				// Si pas valide : on reste sur la page d'édition
				PortalUtil.copyRequestParameters(request, response);

				ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
				String portletName = (String) request
					.getAttribute(WebKeys.PORTLET_ID);
				PortletURL returnURL = PortletURLFactoryUtil.create(request,
					portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);
				returnURL.setParameter("tab", request.getParameter("tab"));

				response.setRenderParameter("returnURL", returnURL.toString());
				response.setRenderParameter("mvcPath",
					"/agenda-bo-edit-event.jsp");
				return false;
			}

			// Edition de l'événement
			long eventId = ParamUtil.getLong(request, "eventId");
			Event event;
			if (eventId == 0) {
				event = _eventLocalService.createEvent(sc);
			} else {
				event = _eventLocalService.getEvent(eventId);
			}

			// Titre
			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			event.setTitleMap(title);

			// Sous-titre
			Map<Locale, String> subtitle = LocalizationUtil
				.getLocalizationMap(request, "subtitle");
			event.setSubtitleMap(subtitle);

			// Description
			Map<Locale, String> description = LocalizationUtil
				.getLocalizationMap(request, "description");
			event.setDescriptionMap(description);

			// Image
			Long imageId = ParamUtil.getLong(request, "imageId");
			if (imageId > 0) { // Image interne
				event.setImageId(imageId);
				event.setExternalImageURL("");
				event.setExternalImageCopyright("");
			} else { // I
				event.setImageId((long) 0);
				String externalImageURL = ParamUtil.getString(request,
					"externalImageURL");
				event.setExternalImageURL(externalImageURL);

				String externalImageCopyright = ParamUtil.getString(request,
					"externalImageCopyright");
				event.setExternalImageCopyright(externalImageCopyright);
			}

			// Lieu
			String placeSIGId = ParamUtil.getString(request, "placeSIGId");
			if (Validator.isNotNull(placeSIGId)) { // Lieu SIG
				event.setPlaceSIGId(placeSIGId);
				event.setPlaceName("");
				event.setPlaceStreetNumber("");
				event.setPlaceStreetName("");
				event.setPlaceZipCode("");
				event.setPlaceCountry("");

				// Dans le cas d'un lieu SIG, on ajoute automatiquement les
				// catégories territoires du lieu aux catégories à ajouter à
				// l'entité
				Place place = PlaceLocalServiceUtil.getPlaceBySIGId(placeSIGId);
				List<AssetCategory> territories = place.getTerritories();
				long[] newCategories = sc.getAssetCategoryIds();
				for (AssetCategory territory : territories) {
					if (!ArrayUtil.contains(newCategories,
						territory.getCategoryId())) {
						newCategories = ArrayUtil.append(newCategories,
							territory.getCategoryId());
					}
				}
				sc.setAssetCategoryIds(newCategories);
			} else {
				event.setPlaceSIGId("");

				Map<Locale, String> placeName = LocalizationUtil
					.getLocalizationMap(request, "placeName");
				event.setPlaceNameMap(placeName);

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

			// ---------------------------------------------------------------
			// -------------------------- CONCERT ----------------------------
			// ---------------------------------------------------------------
			
			event.setComposer(ParamUtil.getString(request, "composer"));
			event.setProgramMap(LocalizationUtil.getLocalizationMap(request, "program"));
			event.setDistributionMap(LocalizationUtil.getLocalizationMap(request, "distribution"));
			event.setConcertId(ParamUtil.getString(request, "concertId"));
			
			// ---------------------------------------------------------------
			// -------------------------- PRIX -------------------------------
			// ---------------------------------------------------------------
			
			Integer free = ParamUtil.getInteger(request, "free");
			event.setFree(free);

			Map<Locale, String> price = LocalizationUtil
				.getLocalizationMap(request, "price");
			event.setPriceMap(price);

			// ---------------------------------------------------------------
			// ----------------- RESERVATION DE BILLETS ----------------------
			// ---------------------------------------------------------------
			
			// Description de la reservation
			Map<Locale, String> bookingDescriptionMap = LocalizationUtil
				.getLocalizationMap(request, "bookingDescription");
			event.setBookingDescriptionMap(bookingDescriptionMap);
			
			// URL de la billeterie
			String bookingURL = ParamUtil.getString(request,
					"bookingURL");
			event.setBookingURL(bookingURL);
			
			// URL des abonnements
			String subscriptionURL = ParamUtil.getString(request,
					"subscriptionURL");
			event.setSubscriptionURL(subscriptionURL);
			
			String publicationDateString = ParamUtil.getString(request,
				"publicationDate");
			String publicationDateTimeString = ParamUtil.getString(request,
				"publicationDateTime");
			Date publicationDate = GetterUtil.getDate(
				publicationDateString + " " + publicationDateTimeString,
				new SimpleDateFormat("dd/MM/yyyy hh:mm"));
			event.setPublicationDate(publicationDate);

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
					eventPeriod.setCampaignEventId(0);
					this._eventPeriodLocalService
						.updateEventPeriod(eventPeriod);
				}
			}

			_eventLocalService.updateEvent(event, sc);

		} catch (PortalException e) {
			_log.error(e);
		} catch (IOException e) {
			_log.error(e);
			// Si on rentre dans l'IOException, c'est qu'on a pas réussi à charger l'image fournie
			// C'est sûrement une image externe dont l'URL n'est pas bonne ou plus valide
			SessionErrors.add(request, "image-load-error");
			// Si pas valide : on reste sur la page d'édition
			PortalUtil.copyRequestParameters(request, response);

			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) request
				.getAttribute(WebKeys.PORTLET_ID);
			PortletURL returnURL = PortletURLFactoryUtil.create(request,
				portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

			response.setRenderParameter("returnURL", returnURL.toString());
			response.setRenderParameter("mvcPath",
				"/agenda-bo-edit-event.jsp");
			return false;
		}

		return true;
	}

	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		// Titre
		if (Validator.isNull(ParamUtil.getString(request, "title"))) {
			SessionErrors.add(request, "title-error");
			isValid = false;
		}

		// Description
		if (Validator.isNull(ParamUtil.getString(request, "description"))) {
			SessionErrors.add(request, "description-error");
			isValid = false;
		}

		// Image
		long imageId = ParamUtil.getLong(request, "imageId");
		String imageURL = ParamUtil.getString(request, "externalImageURL");
		if (imageId == 0 && Validator.isNull(imageURL)) {
			SessionErrors.add(request, "image-error");
			isValid = false;
		}

		// Copyright de l'image
		String imageCopyright = ParamUtil.getString(request,
			"externalImageCopyright");
		boolean internalImageWithoutCopyright = imageId > 0 && Validator
			.isNull(FileEntryHelper.getImageCopyright(imageId, Locale.FRANCE));
		boolean externalImageWithoutCopyright = Validator.isNotNull(imageURL)
			&& Validator.isNull(imageCopyright);
		if (internalImageWithoutCopyright || externalImageWithoutCopyright) {
			SessionErrors.add(request, "image-copyright-error");
			isValid = false;
		}

		// Lieu
		String placeSIGId = ParamUtil.getString(request, "placeSIGId");
		boolean isManualPlace = Validator.isNull(placeSIGId);
		if (isManualPlace) {
			String placeName = ParamUtil.getString(request, "placeName");
			String placeCity = ParamUtil.getString(request, "placeCity");
			if (Validator.isNull(placeName)) {
				SessionErrors.add(request, "place-name-error");
				isValid = false;
			}
			if (Validator.isNull(placeCity)) {
				SessionErrors.add(request, "place-city-error");
				isValid = false;
			}
		}

		// Horaires
		String periodsIndexesString = ParamUtil.getString(request,
			"periodIndexes");
		int periodCount = 0;
		for (String periodIndex : periodsIndexesString.split(",")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if (Validator.isNotNull(periodIndex)
				&& Validator.isNotNull(
					ParamUtil.getString(request, "startDate" + periodIndex))
				&& Validator.isNotNull(
					ParamUtil.getString(request, "endDate" + periodIndex))) {

				Date startDate = ParamUtil.getDate(request,
					"startDate" + periodIndex, dateFormat);
				Date endDate = ParamUtil.getDate(request,
					"endDate" + periodIndex, dateFormat);
				if (endDate.before(startDate)) {
					SessionErrors.add(request, "period-date-error");
					isValid = false;
				}
				periodCount++;
			}
		}
		if (periodCount == 0) {
			SessionErrors.add(request, "period-error");
			isValid = false;
		}

		return isValid;
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

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}