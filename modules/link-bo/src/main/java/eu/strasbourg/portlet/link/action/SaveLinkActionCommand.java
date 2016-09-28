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
package eu.strasbourg.portlet.link.action;

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
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.LINK_BO,
		"mvc.command.name=saveLink" },
	service = MVCActionCommand.class)
public class SaveLinkActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getScopeGroupId());

			long linkId = ParamUtil.getLong(request, "linkId");
			Link link;
			if (linkId == 0) {
				link = _linkLocalService.addLink();
			} else {
				link = _linkLocalService.getLink(linkId);
			}

			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			link.setTitleMap(title);

			Map<Locale, String> url = LocalizationUtil
				.getLocalizationMap(request, "URL");
			link.setURLMap(url);

			Map<Locale, String> hoverText = LocalizationUtil
				.getLocalizationMap(request, "hoverText");
			link.setHoverTextMap(hoverText);
			
			_linkLocalService.updateLink(link, sc);
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return true;
	}

	private LinkLocalService _linkLocalService;

	@Reference(unbind = "-")
	protected void setLinkLocalService(
		LinkLocalService linkLocalService) {

		_linkLocalService = linkLocalService;
	}

}