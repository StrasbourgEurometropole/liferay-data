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
package eu.strasbourg.portlet.interest.action;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.INTEREST_BO,
		"mvc.command.name=saveInterest" }, service = MVCActionCommand.class)
public class SaveInterestActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());
			long interestId = ParamUtil.getLong(request, "interestId");
			Interest interest;
			if (interestId == 0) {
				interest = _interestLocalService.createInterest(sc);
			} else {
				interest = _interestLocalService.getInterest(interestId);
			}

			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request, "title");
			interest.setTitleMap(title);
			Map<Locale, String> description = LocalizationUtil.getLocalizationMap(request, "description");
			interest.setDescriptionMap(description);
			
			long typeId = ParamUtil.getLong(request, "typeId");
			interest.setTypeId(typeId);

			_interestLocalService.updateInterest(interest, sc);
			
			// Redirection (évite double
			// requête POST si l'utilisateur actualise sa page)
			ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) request
				.getAttribute(WebKeys.PORTLET_ID);
			PortletURL renderUrl = PortletURLFactoryUtil.create(request,
				portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
			response.sendRedirect(renderUrl.toString());
		} catch (Exception e) {
			_log.error(e);
		}

		return true;
	}

	private InterestLocalService _interestLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(InterestLocalService interestLocalService) {

		_interestLocalService = interestLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}