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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.link.service.LinkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.LINK_BO,
		"mvc.command.name=deleteLink" },
	service = MVCActionCommand.class)
public class DeleteLinkActionCommand
	implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			long linkId = ParamUtil.getLong(request, "linkId");
			_linkLocalService.removeLink(linkId);
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