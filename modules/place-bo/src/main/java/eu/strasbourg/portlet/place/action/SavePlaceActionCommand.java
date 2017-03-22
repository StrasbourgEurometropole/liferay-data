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
package eu.strasbourg.portlet.place.action;

import java.text.SimpleDateFormat;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.PeriodLocalService;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.service.place.service.ScheduleExceptionLocalService;
import eu.strasbourg.service.place.service.SlotLocalService;
import eu.strasbourg.service.place.service.SubPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=savePlace" }, service = MVCActionCommand.class)
public class SavePlaceActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			ThemeDisplay td = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
			sc.setScopeGroupId(td.getCompanyGroupId());

			long placeId = ParamUtil.getLong(request, "placeId");
			Place place;
			if (placeId == 0) {
				place = _placeLocalService.createPlace(sc);
			} else {
				place = _placeLocalService.getPlace(placeId);
			}

			// ----------------------------------------------------------------
			// -------------------- INFORMATIONS GENERALES --------------------
			// ----------------------------------------------------------------

			String SIGid = ParamUtil.getString(request, "SIGid2");
			place.setSIGid(SIGid);

			String name = ParamUtil.getString(request, "name2");
			place.setName(name);

			String addressStreet = ParamUtil.getString(request,
					"addressStreet2");
			place.setAddressStreet(addressStreet);

			String addressComplement = ParamUtil.getString(request,
					"addressComplement2");
			place.setAddressComplement(addressComplement);

			String addressZipCode = ParamUtil.getString(request,
					"addressZipCode2");
			place.setAddressZipCode(addressZipCode);

			String addressDistribution = ParamUtil.getString(request,
					"addressDistribution2");
			place.setAddressDistribution(addressDistribution);

			String addressCountry = ParamUtil.getString(request,
					"addressCountry2");
			place.setAddressCountry(addressCountry);

			String mercatorX = ParamUtil.getString(request, "mercatorX2");
			place.setMercatorX(mercatorX);

			String mercatorY = ParamUtil.getString(request, "mercatorY2");
			place.setMercatorY(mercatorY);

			String RGF93X = ParamUtil.getString(request, "RGF93X2");
			place.setRGF93X(RGF93X);

			String RGF93Y = ParamUtil.getString(request, "RGF93Y2");
			place.setRGF93Y(RGF93Y);

			// ---------------------------------------------------------------
			// ------------------------- DESCRIPTION -------------------------
			// ---------------------------------------------------------------

			Map<Locale, String> alias = LocalizationUtil
					.getLocalizationMap(request, "alias");
			place.setAliasMap(alias);

			Map<Locale, String> presentation = LocalizationUtil
					.getLocalizationMap(request, "presentation");
			place.setPresentationMap(presentation);

			Map<Locale, String> serviceAndActivities = LocalizationUtil
					.getLocalizationMap(request, "serviceAndActivities");
			place.setServiceAndActivitiesMap(serviceAndActivities);

			Map<Locale, String> characteristics = LocalizationUtil
					.getLocalizationMap(request, "characteristics");
			place.setCharacteristicsMap(characteristics);

			// ---------------------------------------------------------------
			// ---------------------------- MEDIA ----------------------------
			// ---------------------------------------------------------------

			Long imageId = ParamUtil.getLong(request, "imageId");
			place.setImageId(imageId);

			String imageIds = ParamUtil.getString(request, "imageIds");
			place.setImageIds(imageIds);

			String videosIds = ParamUtil.getString(request, "videosIds");
			place.setVideosIds(videosIds);

			// ---------------------------------------------------------------
			// --------------------------- CONTACT ---------------------------
			// ---------------------------------------------------------------

			String phone = ParamUtil.getString(request, "phone");
			place.setPhone(phone);

			String mail = ParamUtil.getString(request, "mail");
			place.setMail(mail);

			String siteLabel = ParamUtil.getString(request, "siteLabel");
			place.setSiteLabel(siteLabel);

			String siteURL = ParamUtil.getString(request, "siteURL");
			place.setSiteURL(siteURL);

			String facebookLabel = ParamUtil.getString(request,
					"facebookLabel");
			place.setFacebookLabel(facebookLabel);

			String facebookURL = ParamUtil.getString(request, "facebookURL");
			place.setFacebookURL(facebookURL);

			// ---------------------------------------------------------------
			// ---------------------------- ACCES ----------------------------
			// ---------------------------------------------------------------

			Map<Locale, String> access = LocalizationUtil
					.getLocalizationMap(request, "access");
			place.setAccessMap(access);

			Map<Locale, String> accesMap = LocalizationUtil
					.getLocalizationMap(request, "accesMap");
			place.setAccesMapMap(accesMap);

			Map<Locale, String> accessForDisabled = LocalizationUtil
					.getLocalizationMap(request, "accessForDisabled");
			place.setAccessForDisabledMap(accessForDisabled);

			boolean accessForBlind = ParamUtil.getBoolean(request,
					"accessForBlind");
			place.setAccessForBlind(accessForBlind);

			boolean accessForDeaf = ParamUtil.getBoolean(request,
					"accessForDeaf");
			place.setAccessForDeaf(accessForDeaf);

			boolean accessForWheelchair = ParamUtil.getBoolean(request,
					"accessForWheelchair");
			place.setAccessForWheelchair(accessForWheelchair);

			boolean accessForElder = ParamUtil.getBoolean(request,
					"accessForElder");
			place.setAccessForElder(accessForElder);

			boolean accessForDeficient = ParamUtil.getBoolean(request,
					"accessForDeficient");
			place.setAccessForDeficient(accessForDeficient);

			// ---------------------------------------------------------------
			// -------------------------- HORAIRES ---------------------------
			// ---------------------------------------------------------------

			// --------------------- Périodes & horaires ---------------------

			// Suppression des périodes liées au sous lieu
			List<Period> oldPeriods = place.getPeriods();
			for (Period period : oldPeriods) {
				_periodLocalService.removePeriod(period.getPeriodId());
			}

			// Ajout des période liées au sous lieu
			String periodsIndexes = ParamUtil.getString(request,
					"periodsIndexes");
			for (String periodsIndex : periodsIndexes.split(",")) {
				if (Validator.isNotNull(periodsIndex)
						&& Validator.isNotNull(ParamUtil.getString(request,
								"namePeriod" + periodsIndex))) {
					String namePeriod = ParamUtil.getString(request,
							"namePeriod" + periodsIndex);
					String periodLabel = ParamUtil.getString(request,
							"periodLabel" + periodsIndex);
					String periodURL = ParamUtil.getString(request,
							"periodURL" + periodsIndex);
					boolean defaultPeriod = ParamUtil.getBoolean(request,
							"defaultPeriod" + periodsIndex);
					Date startDatePeriod = ParamUtil.getDate(request,
							"startDatePeriod" + periodsIndex,
							new SimpleDateFormat("yyyy-MM-dd"));
					Date endDatePeriod = ParamUtil.getDate(request,
							"endDatePeriod" + periodsIndex,
							new SimpleDateFormat("yyyy-MM-dd"));
					boolean alwaysOpen = ParamUtil.getBoolean(request,
							"alwaysOpen" + periodsIndex);

					Period period = _periodLocalService.createPeriod(sc);
					period.setName(namePeriod);
					period.setLinkLabel(periodLabel);
					period.setLinkURL(periodURL);
					period.setDefaultPeriod(defaultPeriod);
					if(!period.getDefaultPeriod()){
						period.setStartDate(startDatePeriod);
						period.setEndDate(endDatePeriod);
					}
					period.setAlwaysOpen(alwaysOpen);
					period.setPlaceId(place.getPlaceId());
					this._periodLocalService.updatePeriod(period);

					if(!period.getAlwaysOpen()){
						// Ajout des slots liées à la période
						for (int jour = 0; jour < 7; jour++) {
							for (int slotIndex = 0; slotIndex < 3; slotIndex++) {
								if (Validator
										.isNotNull(ParamUtil.getString(request,
												"startHour" + periodsIndex + "-" + jour
														+ "-" + slotIndex))
										&& Validator.isNotNull(ParamUtil.getString(
												request, "endHour" + periodsIndex + "-"
														+ jour + "-" + slotIndex))) {
									String startHour = ParamUtil.getString(request,
											"startHour" + periodsIndex + "-" + jour
													+ "-" + slotIndex);
									String endHour = ParamUtil.getString(request,
											"endHour" + periodsIndex + "-" + jour
													+ "-" + slotIndex);

									Slot slot = _slotLocalService
											.createSlot(sc);
									slot.setDayOfWeek(jour);
									slot.setStartHour(startHour);
									slot.setEndHour(endHour);
									slot.setPeriodId(period.getPeriodId());
									this._slotLocalService.updateSlot(slot);
								}

							}
						}
					}
				}

			}

			// ----------------- Fermetures exceptionnelles ------------------

			// Suppression des fermetures exceptionnelles liées au lieu
			List<ScheduleException> oldSchedulesExceptions = place
					.getScheduleExceptions();
			for (ScheduleException scheduleException : oldSchedulesExceptions) {
				_scheduleExceptionLocalService
						.deleteScheduleException(scheduleException);
			}

			// Ajout des fermetures exceptionnelles liées au lieu
			String shedulesExceptionsIndexes = ParamUtil.getString(request,
					"shedulesExceptionsIndexes");
			for (String shedulesExceptionsIndex : shedulesExceptionsIndexes
					.split(",")) {
				if (Validator.isNotNull(shedulesExceptionsIndex)
						&& Validator.isNotNull(ParamUtil.getString(request,
								"scheduleExceptionDescription"
										+ shedulesExceptionsIndex))
						&& Validator.isNotNull(ParamUtil.getString(request,
								"dateScheduleException"
										+ shedulesExceptionsIndex))) {
					String startHour = ParamUtil.getString(request,
							"startHour" + shedulesExceptionsIndex);
					String endHour = ParamUtil.getString(request,
							"endHour" + shedulesExceptionsIndex);
					String comment = ParamUtil.getString(request,
							"scheduleExceptionDescription"
									+ shedulesExceptionsIndex);
					Date date = ParamUtil.getDate(request,
							"dateScheduleException" + shedulesExceptionsIndex,
							new SimpleDateFormat("yyyy-MM-dd"));
					boolean closed = ParamUtil.getBoolean(request,
							"closed" + shedulesExceptionsIndex);

					ScheduleException scheduleException = _scheduleExceptionLocalService
							.createScheduleException(sc);
					scheduleException.setComment(comment);
					scheduleException.setDate(date);
					scheduleException.setClosed(closed);
					if(!scheduleException.getClosed()){
						scheduleException.setStartHour(startHour);
						scheduleException.setEndHour(endHour);
					}
					scheduleException.setPlaceId(place.getPlaceId());
					this._scheduleExceptionLocalService
							.updateScheduleException(scheduleException);
				}

			}

			// ----------------------------------------------------------------
			// ----------------- INFORMATIONS COMPLEMENTAIRES -----------------
			// ----------------------------------------------------------------

			boolean displayEvents = ParamUtil.getBoolean(request,
					"displayEvents");
			place.setDisplayEvents(displayEvents);

			Map<Locale, String> additionalInformation = LocalizationUtil
					.getLocalizationMap(request, "additionalInformation");
			place.setAdditionalInformationMap(additionalInformation);

			// ----------------------------------------------------------------
			// ------------------- FREQUENTATION TEMPS REEL -------------------
			// ----------------------------------------------------------------

			String RTExternalId = ParamUtil.getString(request, "RTExternalId");
			place.setRTExternalId(RTExternalId);

			Long RTGreenThreshold = ParamUtil.getLong(request,
					"RTGreenThreshold");
			place.setRTGreenThreshold(RTGreenThreshold);

			Long RTOrangeThreshold = ParamUtil.getLong(request,
					"RTOrangeThreshold");
			place.setRTOrangeThreshold(RTOrangeThreshold);

			Long RTRedThreshold = ParamUtil.getLong(request, "RTRedThreshold");
			place.setRTRedThreshold(RTRedThreshold);

			Long RTMaxThreshold = ParamUtil.getLong(request, "RTMaxThreshold");
			place.setRTMaxThreshold(RTMaxThreshold);
			place.setRGF93Y(RGF93Y);

			// ----------------------------------------------------------------
			// -------------------------- SOUS LIEUX --------------------------
			// ----------------------------------------------------------------

			// Suppression des liens entre lieux et les sous lieux
			long[] subPlacesIds = ParamUtil.getLongValues(request,
					"suppression");
			for (Long subPlaceId : subPlacesIds) {
				SubPlace subPlace = _subPlaceLocalService
						.getSubPlace(subPlaceId);
				subPlace.setPlaceId(0);
				_subPlaceLocalService.updateSubPlace(subPlace);
			}

			boolean RTEnabled = false;
			// boolean RTEnabled = ParamUtil.getBoolean(request, "RTEnabled");
			place.setRTEnabled(RTEnabled);

			place.setSubjectToPublicHoliday(false);

			_placeLocalService.updatePlace(place, sc);
		} catch (

		PortalException e) {
			_log.error(e);
		}

		return true;
	}

	private PlaceLocalService _placeLocalService;

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {

		_placeLocalService = placeLocalService;
	}

	private SubPlaceLocalService _subPlaceLocalService;

	@Reference(unbind = "-")
	protected void setSubPlaceLocalService(
			SubPlaceLocalService subPlaceLocalService) {

		_subPlaceLocalService = subPlaceLocalService;
	}

	private ScheduleExceptionLocalService _scheduleExceptionLocalService;

	@Reference(unbind = "-")
	protected void setScheduleExceptionLocalService(
			ScheduleExceptionLocalService scheduleExceptionLocalService) {

		_scheduleExceptionLocalService = scheduleExceptionLocalService;
	}

	private PeriodLocalService _periodLocalService;

	@Reference(unbind = "-")
	protected void setPeriodLocalService(
			PeriodLocalService periodLocalService) {

		_periodLocalService = periodLocalService;
	}

	private SlotLocalService _slotLocalService;

	@Reference(unbind = "-")
	protected void setSlotLocalService(
			SlotLocalService slotLocalService) {

		_slotLocalService = slotLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}