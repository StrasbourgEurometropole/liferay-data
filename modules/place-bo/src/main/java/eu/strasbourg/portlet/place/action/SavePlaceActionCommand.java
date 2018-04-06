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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
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
import eu.strasbourg.service.place.service.SlotLocalServiceUtil;
import eu.strasbourg.service.place.service.SubPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=savePlace" }, service = MVCActionCommand.class)
public class SavePlaceActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
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

			String addressStreet = ParamUtil.getString(request, "addressStreet2");
			place.setAddressStreet(addressStreet);

			String addressComplement = ParamUtil.getString(request, "addressComplement2");
			place.setAddressComplement(addressComplement);

			String addressZipCode = ParamUtil.getString(request, "addressZipCode2");
			place.setAddressZipCode(addressZipCode);

			String addressDistribution = ParamUtil.getString(request, "addressDistribution2");
			place.setAddressDistribution(addressDistribution);

			String addressCountry = ParamUtil.getString(request, "addressCountry2");
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

			Map<Locale, String> alias = LocalizationUtil.getLocalizationMap(request, "alias");
			place.setAliasMap(alias);

			Map<Locale, String> presentation = LocalizationUtil.getLocalizationMap(request, "presentation");
			place.setPresentationMap(presentation);

			Map<Locale, String> serviceAndActivities = LocalizationUtil.getLocalizationMap(request,
					"serviceAndActivities");
			place.setServiceAndActivitiesMap(serviceAndActivities);

			Map<Locale, String> characteristics = LocalizationUtil.getLocalizationMap(request, "characteristics");
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

			String documents = ParamUtil.getString(request, "documents");
			place.setDocumentsIds(documents);

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

			String facebookLabel = ParamUtil.getString(request, "facebookLabel");
			place.setFacebookLabel(facebookLabel);

			String facebookURL = ParamUtil.getString(request, "facebookURL");
			place.setFacebookURL(facebookURL);

			// ---------------------------------------------------------------
			// ---------------------------- ACCES ----------------------------
			// ---------------------------------------------------------------

			Map<Locale, String> access = LocalizationUtil.getLocalizationMap(request, "access");
			place.setAccessMap(access);

			Map<Locale, String> accesMap = LocalizationUtil.getLocalizationMap(request, "accesMap");
			place.setAccesMapMap(accesMap);

			Map<Locale, String> accessForDisabled = LocalizationUtil.getLocalizationMap(request, "accessForDisabled");
			place.setAccessForDisabledMap(accessForDisabled);

			boolean accessForBlind = ParamUtil.getBoolean(request, "accessForBlind");
			place.setAccessForBlind(accessForBlind);

			boolean accessForDeaf = ParamUtil.getBoolean(request, "accessForDeaf");
			place.setAccessForDeaf(accessForDeaf);

			boolean accessForWheelchair = ParamUtil.getBoolean(request, "accessForWheelchair");
			place.setAccessForWheelchair(accessForWheelchair);

			boolean accessForElder = ParamUtil.getBoolean(request, "accessForElder");
			place.setAccessForElder(accessForElder);

			boolean accessForDeficient = ParamUtil.getBoolean(request, "accessForDeficient");
			place.setAccessForDeficient(accessForDeficient);

			// ---------------------------------------------------------------
			// -------------------------- HORAIRES ---------------------------
			// ---------------------------------------------------------------

			boolean subjectPublicHolidays = ParamUtil.getBoolean(request, "subjectPublicHolidays");
			place.setSubjectToPublicHoliday(subjectPublicHolidays);
			
			// On récupère les périodes du lieu pour suppression
			List<Long> periodsToKeep = new ArrayList<Long>();
			
			
			// suppression des slots du lieu
			List<Period> periods = place.getPeriods();
			for (Period period : periods) {
				List<Slot> slots = period.getSlots();
				for (Slot slot : slots) {
					SlotLocalServiceUtil.deleteSlot(slot);
				}
			}

			// --------------------- Périodes & horaires ---------------------

			// Ajout des période liées au lieu
			String periodsIndexes = ParamUtil.getString(request, "periodsIndexes");
			if (Validator.isNotNull(periodsIndexes)) {
				for (String periodIndex : periodsIndexes.split(",")) {
					if (Validator.isNotNull(periodIndex)
							&& Validator.isNotNull(ParamUtil.getString(request, "namePeriod" + periodIndex))) {
						long periodId = ParamUtil.getLong(request, "periodId" + periodIndex);
						Map<Locale, String> namePeriod = LocalizationUtil.getLocalizationMap(request,
								"namePeriod" + periodIndex);
						Map<Locale, String> periodLabel = LocalizationUtil.getLocalizationMap(request,
								"periodLabel" + periodIndex);
						Map<Locale, String> periodURL = LocalizationUtil.getLocalizationMap(request,
								"periodURL" + periodIndex);
						boolean defaultPeriod = ParamUtil.getBoolean(request, "defaultPeriod" + periodIndex);
						Date startDatePeriod = ParamUtil.getDate(request, "startDatePeriod" + periodIndex,
								new SimpleDateFormat("yyyy-MM-dd"));
						Date endDatePeriod = ParamUtil.getDate(request, "endDatePeriod" + periodIndex,
								new SimpleDateFormat("yyyy-MM-dd"));
						boolean alwaysOpen = ParamUtil.getBoolean(request, "alwaysOpen" + periodIndex);
						Long RTGreenThreshold = ParamUtil.getLong(request, "RTGreenThreshold" + periodIndex);
						Long RTOrangeThreshold = ParamUtil.getLong(request, "RTOrangeThreshold" + periodIndex);
						Long RTRedThreshold = ParamUtil.getLong(request, "RTRedThreshold" + periodIndex);
						Long RTMaxThreshold = ParamUtil.getLong(request, "RTMaxThreshold" + periodIndex);
						
						// on ajoute cette période à la liste des périodes à garder
						periodsToKeep.add(periodId);

						Period period;
						if(periodId != 0){
							period = _periodLocalService.fetchPeriod(periodId);
						}else{
							period = _periodLocalService.createPeriod(sc);
						}
						period.setNameMap(namePeriod);
						period.setLinkLabelMap(periodLabel);
						period.setLinkURLMap(periodURL);
						period.setDefaultPeriod(defaultPeriod);
						if (!period.getDefaultPeriod()) {
							period.setStartDate(startDatePeriod);
							period.setEndDate(endDatePeriod);
						}
						period.setAlwaysOpen(alwaysOpen);
						period.setPlaceId(place.getPlaceId());

						// ---------------------- Fréquentation
						// ----------------------
						if (place.isEnabled()) {
							period.setRTGreenThreshold(RTGreenThreshold);
							period.setRTOrangeThreshold(RTOrangeThreshold);
							period.setRTRedThreshold(RTRedThreshold);
							period.setRTMaxThreshold(RTMaxThreshold);
						}
						this._periodLocalService.updatePeriod(period);

						if (!period.getAlwaysOpen()) {
							// Ajout des slots liées à la période
							for (int jour = 0; jour < 7; jour++) {
								String slotsIndexes = ParamUtil.getString(request,
										"slotsIndexes" + periodIndex + "-" + jour);
								if (Validator.isNotNull(slotsIndexes)) {
									for (String slotIndex : slotsIndexes.split(",")) {
										String startHour = ParamUtil.getString(request,
												"startHour" + periodIndex + "-" + jour + "-" + slotIndex);
										String endHour = ParamUtil.getString(request,
												"endHour" + periodIndex + "-" + jour + "-" + slotIndex);
										Map<Locale, String> comment = LocalizationUtil.getLocalizationMap(request,
												"comment" + periodIndex + "-" + jour + "-" + slotIndex);
										Slot slot = _slotLocalService.createSlot(sc);
										slot.setDayOfWeek(jour);
										slot.setStartHour(startHour);
										slot.setEndHour(endHour);
										slot.setCommentMap(comment);
										slot.setPeriodId(period.getPeriodId());
										slot.setSubPlaceId(0);
										this._slotLocalService.updateSlot(slot);

									}
								}
							}
						}
					}

				}
				
				// On supprime les anciennes périodes qui n'existent plus
				for (Period period : periods) {
					if(!periodsToKeep.contains(period.getPeriodId())){
						_periodLocalService.removePeriod(period.getPeriodId());
					}
				}
			}

			// -------------------- Horaires particuliers --------------------

			Map<Locale, String> exceptionalSchedule = LocalizationUtil.getLocalizationMap(request,
					"exceptionalSchedule");
			place.setExceptionalScheduleMap(exceptionalSchedule);

			// ----------------- Fermetures exceptionnelles ------------------

			// Suppression des fermetures exceptionnelles liées au lieu
			List<ScheduleException> oldSchedulesExceptions = place.getScheduleExceptions();
			for (ScheduleException scheduleException : oldSchedulesExceptions) {
				_scheduleExceptionLocalService.deleteScheduleException(scheduleException);
			}

			// Ajout des fermetures exceptionnelles liées au lieu
			String shedulesExceptionsIndexes = ParamUtil.getString(request, "shedulesExceptionsIndexes");
			for (String shedulesExceptionsIndex : shedulesExceptionsIndexes.split(",")) {
				if (Validator.isNotNull(shedulesExceptionsIndex)
						&& Validator.isNotNull(
								ParamUtil.getString(request, "scheduleExceptionDescription" + shedulesExceptionsIndex))
						&& Validator.isNotNull(
								ParamUtil.getString(request, "startDateScheduleException" + shedulesExceptionsIndex))
						&& Validator.isNotNull(
								ParamUtil.getString(request, "endDateScheduleException" + shedulesExceptionsIndex))) {
					String startTime1 = ParamUtil.getString(request, "startHour1_" + shedulesExceptionsIndex);
					String endTime1 = ParamUtil.getString(request, "endHour1_" + shedulesExceptionsIndex);
					String startTime2 = ParamUtil.getString(request, "startHour2_" + shedulesExceptionsIndex);
					String endTime2 = ParamUtil.getString(request, "endHour2_" + shedulesExceptionsIndex);
					String startTime3 = ParamUtil.getString(request, "startHour3_" + shedulesExceptionsIndex);
					String endTime3 = ParamUtil.getString(request, "endHour3_" + shedulesExceptionsIndex);
					String startTime4 = ParamUtil.getString(request, "startHour4_" + shedulesExceptionsIndex);
					String endTime4 = ParamUtil.getString(request, "endHour4_" + shedulesExceptionsIndex);
					String startTime5 = ParamUtil.getString(request, "startHour5_" + shedulesExceptionsIndex);
					String endTime5 = ParamUtil.getString(request, "endHour5_" + shedulesExceptionsIndex);
					Map<Locale, String> comment = LocalizationUtil.getLocalizationMap(request,
							"scheduleExceptionDescription" + shedulesExceptionsIndex);
					Date startDate = ParamUtil.getDate(request, "startDateScheduleException" + shedulesExceptionsIndex,
							new SimpleDateFormat("yyyy-MM-dd"));
					Date endDate = ParamUtil.getDate(request, "endDateScheduleException" + shedulesExceptionsIndex,
							new SimpleDateFormat("yyyy-MM-dd"));
					boolean closed = ParamUtil.getBoolean(request, "closed" + shedulesExceptionsIndex);

					ScheduleException scheduleException = _scheduleExceptionLocalService.createScheduleException(sc);
					scheduleException.setCommentMap(comment);
					scheduleException.setStartDate(startDate);
					scheduleException.setEndDate(endDate);
					scheduleException.setClosed(closed);
					if (!scheduleException.getClosed()) {
						String[] openingTimes = new String[0];
						if (startTime1.length() == 5 || endTime1.length() == 5) {
							openingTimes = ArrayUtil.append(openingTimes, startTime1 + "-" + endTime1);
						}
						if (startTime2.length() == 5 || endTime2.length() == 5) {
							openingTimes = ArrayUtil.append(openingTimes, startTime2 + "-" + endTime2);
						}
						if (startTime3.length() == 5 || endTime3.length() == 5) {
							openingTimes = ArrayUtil.append(openingTimes, startTime3 + "-" + endTime3);
						}
						if (startTime4.length() == 5 || endTime4.length() == 5) {
							openingTimes = ArrayUtil.append(openingTimes, startTime4 + "-" + endTime4);
						}
						if (startTime5.length() == 5 || endTime5.length() == 5) {
							openingTimes = ArrayUtil.append(openingTimes, startTime5 + "-" + endTime5);
						}
						String openingTimesString = StringUtil.merge(openingTimes, ";");
						scheduleException.setOpeningTimes(openingTimesString);
					}
					scheduleException.setPlaceId(place.getPlaceId());
					this._scheduleExceptionLocalService.updateScheduleException(scheduleException);
				}

			}

			// ----------------------------------------------------------------
			// ----------------- INFORMATIONS COMPLEMENTAIRES -----------------
			// ----------------------------------------------------------------

			boolean displayEvents = ParamUtil.getBoolean(request, "displayEvents");
			place.setDisplayEvents(displayEvents);

			Map<Locale, String> additionalInformation = LocalizationUtil.getLocalizationMap(request,
					"additionalInformation");
			place.setAdditionalInformationMap(additionalInformation);

			// ----------------------------------------------------------------
			// ------------------- FREQUENTATION TEMPS REEL -------------------
			// ----------------------------------------------------------------

			String RTExternalId = ParamUtil.getString(request, "RTExternalId");
			place.setRTExternalId(RTExternalId);

			// ----------------------------------------------------------------
			// -------------------------- SOUS LIEUX --------------------------
			// ----------------------------------------------------------------

			// Suppression des liens entre lieux et les sous lieux
			long[] subPlacesIds = ParamUtil.getLongValues(request, "suppression");
			for (Long subPlaceId : subPlacesIds) {
				SubPlace subPlace = _subPlaceLocalService.fetchSubPlace(subPlaceId);
				if (subPlace != null) {
					subPlace.setPlaceId(0);
					_subPlaceLocalService.updateSubPlace(subPlace);
				}
			}

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
	protected void setSubPlaceLocalService(SubPlaceLocalService subPlaceLocalService) {

		_subPlaceLocalService = subPlaceLocalService;
	}

	private ScheduleExceptionLocalService _scheduleExceptionLocalService;

	@Reference(unbind = "-")
	protected void setScheduleExceptionLocalService(ScheduleExceptionLocalService scheduleExceptionLocalService) {

		_scheduleExceptionLocalService = scheduleExceptionLocalService;
	}

	private PeriodLocalService _periodLocalService;

	@Reference(unbind = "-")
	protected void setPeriodLocalService(PeriodLocalService periodLocalService) {

		_periodLocalService = periodLocalService;
	}

	private SlotLocalService _slotLocalService;

	@Reference(unbind = "-")
	protected void setSlotLocalService(SlotLocalService slotLocalService) {

		_slotLocalService = slotLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}