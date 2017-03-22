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
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.PeriodLocalService;
import eu.strasbourg.service.place.service.ScheduleExceptionLocalService;
import eu.strasbourg.service.place.service.SlotLocalService;
import eu.strasbourg.service.place.service.SubPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=saveSubPlace" }, service = MVCActionCommand.class)
public class SaveSubPlaceActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

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

			Map<Locale, String> name = LocalizationUtil
					.getLocalizationMap(request, "name");
			subPlace.setNameMap(name);

			Map<Locale, String> description = LocalizationUtil
					.getLocalizationMap(request, "description");
			subPlace.setDescriptionMap(description);

			long placeId = ParamUtil.getLong(request, "placeId");
			subPlace.setPlaceId(placeId);

			// ---------------------------------------------------------------
			// -------------------------- HORAIRES ---------------------------
			// ---------------------------------------------------------------

			// --------------------- Périodes & horaires ---------------------

			// Suppression des périodes liées au sous lieu
			List<Period> oldPeriods = subPlace.getPeriods();
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
					period.setSubPlaceId(subPlace.getSubPlaceId());
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

			// Suppression des fermetures exceptionnelles liées au sous lieu
			List<ScheduleException> oldSchedulesExceptions = subPlace
					.getScheduleExceptions();
			for (ScheduleException scheduleException : oldSchedulesExceptions) {
				_scheduleExceptionLocalService
						.deleteScheduleException(scheduleException);
			}

			// Ajout des fermetures exceptionnelles liées au sous lieu
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
					scheduleException.setSubPlaceId(subPlace.getSubPlaceId());
					this._scheduleExceptionLocalService
							.updateScheduleException(scheduleException);
				}

			}

			_subPlaceLocalService.updateSubPlace(subPlace);
		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
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