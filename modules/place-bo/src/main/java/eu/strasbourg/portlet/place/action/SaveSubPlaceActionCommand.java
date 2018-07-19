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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.PeriodLocalService;
import eu.strasbourg.service.place.service.ScheduleExceptionLocalService;
import eu.strasbourg.service.place.service.SlotLocalService;
import eu.strasbourg.service.place.service.SlotLocalServiceUtil;
import eu.strasbourg.service.place.service.SubPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=saveSubPlace" }, service = MVCActionCommand.class)
public class SaveSubPlaceActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);

			long subPlaceId = ParamUtil.getLong(request, "subPlaceId");
			SubPlace subPlace;
			if (subPlaceId == 0) {
				subPlace = _subPlaceLocalService.createSubPlace(sc);
			} else {
				subPlace = _subPlaceLocalService.getSubPlace(subPlaceId);
			}

			// ----------------------------------------------------------------
			// -------------------- INFORMATIONS GENERALES --------------------
			// ----------------------------------------------------------------

			Map<Locale, String> name = LocalizationUtil.getLocalizationMap(request, "name");
			subPlace.setNameMap(name);

			Map<Locale, String> description = LocalizationUtil.getLocalizationMap(request, "description");
			subPlace.setDescriptionMap(description);

			long placeId = ParamUtil.getLong(request, "placeId");
			subPlace.setPlaceId(placeId);

			// ---------------------------------------------------------------
			// -------------------------- HORAIRES ---------------------------
			// ---------------------------------------------------------------

			// --------------------- Périodes & horaires ---------------------
			
			// suppression des slots du sous lieu
			List<Period> periods = subPlace.getPeriods();
			for (Period period : periods) {
				List<Slot> slots = period.getSlots(subPlaceId);
				for (Slot slot : slots) {
					SlotLocalServiceUtil.deleteSlot(slot);
				}
			}

			// Mise à jour des périodes liées au sous lieu
			String periodsIndexes = ParamUtil.getString(request, "periodsIndexes");
			if (Validator.isNotNull(periodsIndexes)) {
				for (String periodIndex : periodsIndexes.split(",")) {
					if (Validator.isNotNull(periodIndex)) {
						
						long periodId = ParamUtil.getLong(request, "periodId" + periodIndex);
						Period period = _periodLocalService.fetchPeriod(periodId);

						if (!period.getAlwaysOpen()) {
							// Ajout des slots liées à la période
							for (int jour = 0; jour < 7; jour++) {
								String slotsIndexes = ParamUtil.getString(request,
										"slotsIndexes" + periodIndex + "-" + jour);
								for (String slotIndex : slotsIndexes.split(",")) {
									if (Validator
											.isNotNull(ParamUtil.getString(request,
													"startHour" + periodIndex + "-" + jour + "-" + slotIndex))
											&& Validator.isNotNull(ParamUtil.getString(request,
													"endHour" + periodIndex + "-" + jour + "-" + slotIndex))) {
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
										slot.setSubPlaceId(subPlace.getSubPlaceId());
										this._slotLocalService.updateSlot(slot);
									}

								}
							}
						}
					}
				}

			}

			// ----------------- Fermetures exceptionnelles ------------------

			// Suppression des fermetures exceptionnelles liées au sous lieu
			List<ScheduleException> oldSchedulesExceptions = subPlace.getScheduleExceptions();
			for (ScheduleException scheduleException : oldSchedulesExceptions) {
				_scheduleExceptionLocalService.deleteScheduleException(scheduleException);
			}

			// Ajout des fermetures exceptionnelles liées au sous lieu
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
					
					Map<Locale, String> firstComment = LocalizationUtil.getLocalizationMap(request,
							"firstComment" + shedulesExceptionsIndex);
					Map<Locale, String> secondComment = LocalizationUtil.getLocalizationMap(request,
							"secondComment" + shedulesExceptionsIndex);
					Map<Locale, String> thirdComment = LocalizationUtil.getLocalizationMap(request,
							"thirdComment" + shedulesExceptionsIndex);
					Map<Locale, String> fourthComment = LocalizationUtil.getLocalizationMap(request,
							"fourthComment" + shedulesExceptionsIndex);
					Map<Locale, String> fifthComment = LocalizationUtil.getLocalizationMap(request,
							"fifthComment" + shedulesExceptionsIndex);
					
					ScheduleException scheduleException = _scheduleExceptionLocalService.createScheduleException(sc);
					scheduleException.setCommentMap(comment);
					scheduleException.setFirstCommentMap(firstComment);
					scheduleException.setSecondCommentMap(secondComment);
					scheduleException.setThirdCommentMap(thirdComment);
					scheduleException.setFourthCommentMap(fourthComment);
					scheduleException.setFifthCommentMap(fifthComment);
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
					scheduleException.setSubPlaceId(subPlace.getSubPlaceId());
					this._scheduleExceptionLocalService.updateScheduleException(scheduleException);
				}

			}

			_subPlaceLocalService.updateSubPlace(subPlace, sc);
		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
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