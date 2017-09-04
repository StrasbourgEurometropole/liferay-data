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
			Interest interestInterest;
			if (interestId == 0) {
				interestInterest = _interestInterestLocalService.createInterest(sc);
			} else {
				interestInterest = _interestInterestLocalService.getInterest(interestId);
			}

			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request, "title");
			interestInterest.setTitleMap(title);
			Map<Locale, String> description = LocalizationUtil.getLocalizationMap(request, "description");
			interestInterest.setDescriptionMap(description);

			_interestInterestLocalService.updateInterest(interestInterest, sc);
		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}

	private InterestLocalService _interestInterestLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(InterestLocalService interestInterestLocalService) {

		_interestInterestLocalService = interestInterestLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}