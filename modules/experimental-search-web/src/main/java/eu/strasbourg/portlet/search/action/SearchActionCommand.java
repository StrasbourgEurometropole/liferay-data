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
package eu.strasbourg.portlet.search.action;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search.configuration.ExperimentalSearchConfiguration;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.EXPERIMENTAL_SEARCH_WEB,
		"mvc.command.name=search" }, service = MVCActionCommand.class)
public class SearchActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			ExperimentalSearchConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ExperimentalSearchConfiguration.class);

			// Récupérations des catégories
			// Construction d'un string contenant les identifiants des
			// catégories du critère 1 et du critère 2
			// Les catégories d'un critère sont séparés par des virgules
			//
			String categories = "";

			int criteria1UserChoice = ParamUtil.getInteger(request, "criteria1");
			String[] criteria1OptionCategories = configuration.criteria1OptionCategories();
			if (criteria1UserChoice >= 0 && criteria1OptionCategories.length > criteria1UserChoice) {
				categories = criteria1OptionCategories[criteria1UserChoice];
			}

			int criteria2UserChoice = ParamUtil.getInteger(request, "criteria2");
			String[] criteria2OptionCategories = configuration.criteria2OptionCategories();
			if (criteria2UserChoice >= 0 && criteria2OptionCategories.length > criteria2UserChoice) {
				if (Validator.isNotNull(categories)) {
					categories += ";";
				}
				categories += criteria2OptionCategories[criteria2UserChoice];
			}

			// Si un paramètre de date est passé, on le traite
			String period = ParamUtil.getString(request, "period");
			LocalDate start = null;
			LocalDate end = null;
			switch (period) {
			case "today":
				start = LocalDate.now();
				end = start;
				break;
			case "tomorrow":
				start = LocalDate.now().plusDays(1);
				end = start;
				break;
			case "weekend":
				start = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
				end = start.plusDays(2);
				break;
			case "later":
				start = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).plusDays(3);
				end = start.plusYears(1);
				break;
			}

			// Redirection (évite double
			// requête POST si l'utilisateur actualise sa page)
			Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false,
					configuration.agendaFriendlyURL());
			PortletURL renderUrl = PortletURLFactoryUtil.create(request, StrasbourgPortletKeys.SEARCH_ASSET_WEB,
					layout.getPlid(), PortletRequest.RENDER_PHASE);
			renderUrl.setParameter("categoriesIds", categories);
			if (start != null && end != null) {
				renderUrl.setParameter("fromDay", String.valueOf(start.getDayOfMonth()));
				renderUrl.setParameter("fromMonth", String.valueOf(start.getMonthValue() - 1));
				renderUrl.setParameter("fromYear", String.valueOf(start.getYear()));

				renderUrl.setParameter("toDay", String.valueOf(end.getDayOfMonth()));
				renderUrl.setParameter("toMonth", String.valueOf(end.getMonthValue() - 1));
				renderUrl.setParameter("toYear", String.valueOf(end.getYear()));
			}
			response.sendRedirect(renderUrl.toString());
		} catch (Exception e) {
			_log.error(e);
		}
		return true;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}