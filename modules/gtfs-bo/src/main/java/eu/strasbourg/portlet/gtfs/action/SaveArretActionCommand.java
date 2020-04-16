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
package eu.strasbourg.portlet.gtfs.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.gtfs.model.Alert;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.AlertLocalService;
import eu.strasbourg.service.gtfs.service.ArretLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.GTFS_BO,
		"mvc.command.name=saveArret" }, service = MVCActionCommand.class)
public class SaveArretActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			sc.setScopeGroupId(td.getCompanyGroupId());

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
						"/gtfs-bo-edit-arret.jsp");
				return false;
			}

			long arretId = ParamUtil.getLong(request, "arretId");
			Arret arret = _arretLocalService.getArret(arretId);
			if(Validator.isNotNull(arret)) {

				// ---------------------------------------------------------------
				// ---------------------------- ALERTE ---------------------------
				// ---------------------------------------------------------------

				// Suppression des alertes liées à l'arrêt
				List<Alert> oldAlerts = arret.getAlerts();
				for (Alert alert : oldAlerts) {
					_alertLocalService.deleteAlert(alert);
				}

				// Ajout des alertes liées à l'arrêt
				for (int alertsIndex = 1; alertsIndex <= 2; alertsIndex++) {

					String startDateString = ParamUtil.getString(request,
							"startDateAlert" + alertsIndex);
					String endDateString = ParamUtil.getString(request,
							"endDateAlert" + alertsIndex);
					String alertLigneAndDirectionString = ParamUtil.getString(request,
							"alertLigneAndDirection" + alertsIndex);
					String alertPerturbationString = ParamUtil.getString(request,
							"alertPerturbation" + alertsIndex + "Editor");

					if (Validator.isNotNull(startDateString)|| Validator.isNotNull(endDateString)
							|| Validator.isNotNull(alertLigneAndDirectionString)
							|| Validator.isNotNull(alertPerturbationString)) {

						Date startDate = ParamUtil.getDate(request, "startDateAlert" + alertsIndex,
								new SimpleDateFormat("yyyy-MM-dd"));
						Date endDate = ParamUtil.getDate(request, "endDateAlert" + alertsIndex,
								new SimpleDateFormat("yyyy-MM-dd"));

						Map<Locale, String> alertLigneAndDirection = LocalizationUtil.getLocalizationMap(request,
								"alertLigneAndDirection" + alertsIndex);

						Map<Locale, String> alertPerturbation = LocalizationUtil.getLocalizationMap(request,
								"alertPerturbation" + alertsIndex);

						Alert alert = _alertLocalService.createAlert(sc);
						alert.setStartDate(startDate);
						alert.setEndDate(endDate);
						alert.setLigneAndDirectionMap(alertLigneAndDirection);
						alert.setPerturbationMap(alertPerturbation);
						alert.setArretId(arret.getArretId());
						this._alertLocalService.updateAlert(alert);
					}

				}

				this._arretLocalService.updateArret(arret, sc);
			}

		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}

	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		// Alertes
		for (int alertsIndex = 1 ; alertsIndex <= 2; alertsIndex++){

			String startDateString = ParamUtil.getString(request,
					"startDateAlert" + alertsIndex);
			String endDateString = ParamUtil.getString(request,
					"endDateAlert" + alertsIndex);
			String alertLigneAndDirection = ParamUtil.getString(request,
					"alertLigneAndDirection" + alertsIndex);
			String alertPerturbation = ParamUtil.getString(request,
					"alertPerturbation" + alertsIndex + "Editor");

			if (Validator.isNotNull(startDateString)|| Validator.isNotNull(endDateString)
					|| Validator.isNotNull(alertLigneAndDirection)
					|| Validator.isNotNull(alertPerturbation)) {

				if (Validator.isNotNull(startDateString) && Validator.isNotNull(startDateString)) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date startDate = dateFormat.parse(startDateString);
						Date endDate = dateFormat.parse(startDateString);
						if (endDate.before(startDate)) {
							SessionErrors.add(request, "period-date-error");
							isValid = false;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					if (Validator.isNull(startDateString)) {
						SessionErrors.add(request, "start-date-error");
						isValid = false;
					}

					if (Validator.isNull(endDateString)) {
						SessionErrors.add(request, "end-date-error");
						isValid = false;
					}
				}



				if (Validator.isNull(alertLigneAndDirection)) {
					SessionErrors.add(request, "ligne-error");
					isValid = false;
				}

				if (Validator.isNull(alertPerturbation)) {
					SessionErrors.add(request, "perturbation-error");
					isValid = false;
				}
			}
		}

		return isValid;
	}

	private ArretLocalService _arretLocalService;

	@Reference(unbind = "-")
	protected void setArretLocalService(ArretLocalService arretLocalService) {

		_arretLocalService = arretLocalService;
	}

	private AlertLocalService _alertLocalService;

	@Reference(unbind = "-")
	protected void setAlertLocalService(AlertLocalService alertLocalService) {

		_alertLocalService = alertLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}