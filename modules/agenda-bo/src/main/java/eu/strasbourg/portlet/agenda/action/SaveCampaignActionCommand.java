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
package eu.strasbourg.portlet.agenda.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=saveCampaign" },
	service = MVCActionCommand.class)
public class SaveCampaignActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			long campaignId = ParamUtil.getLong(request,
				"campaignId");
			Campaign campaign;
			if (campaignId == 0) {
				campaign = _campaignLocalService
					.createCampaign(sc);
			} else {
				campaign = _campaignLocalService
					.getCampaign(campaignId);
			}

			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			Boolean exportEnabled = ParamUtil.getBoolean(request, "exportEnabled");
			long defaultImageId = ParamUtil.getLong(request, "defaultImageId");
			Map<Locale, String> defaultImageCopyright = LocalizationUtil
				.getLocalizationMap(request, "defaultImageCopyright");
			long[] managersIds = ParamUtil.getLongValues(request, "managersIds");
			long[] themesIds = ParamUtil.getLongValues(request, "themesIds");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = ParamUtil.getDate(request, "startDate", sdf);
			Date endDate = ParamUtil.getDate(request, "endDate",sdf );
			
			// Validation
			boolean isValid = true;
			if (Validator.isNull(ParamUtil.getString(request, "title"))) {
				SessionErrors.add(request, "title-error");
				isValid = false;
			}
			if (defaultImageId == 0) {
				SessionErrors.add(request, "image-error");
				isValid = false;
			}
			if (Validator.isNull(ParamUtil.getString(request, "defaultImageCopyright"))) {
				SessionErrors.add(request, "copyright-error");
				isValid = false;
			}
			if (themesIds.length == 0) {
				SessionErrors.add(request, "themes-error");
				isValid = false;
			}
			if (managersIds.length == 0) {
				SessionErrors.add(request, "managers-error");
				isValid = false;
			}
			if (Validator.isNull(startDate)) {
				SessionErrors.add(request, "start-date-error");
				isValid = false;
			}
			if (Validator.isNull(endDate)) {
				SessionErrors.add(request, "end-date-error");
				isValid = false;
			}
			if (managersIds.length == 0) {
				SessionErrors.add(request, "dates-error");
				isValid = false;
			}
			
			if (!isValid) {
				PortalUtil.copyRequestParameters(request, response);
				response.setRenderParameter("mvcPath", "/agenda-bo-edit-campaign.jsp");
				return false;
			}
			
			campaign.setTitleMap(title);
			campaign.setDefaultImageId(defaultImageId);
			campaign.setDefaultImageCopyrightMap(defaultImageCopyright);
			campaign.setExportEnabled(exportEnabled);
			campaign.setManagersIds(StringUtil.merge(managersIds));
			campaign.setStartDate(startDate);
			campaign.setEndDate(endDate);
			sc.setAssetCategoryIds(themesIds);
			
			_campaignLocalService
				.updateCampaign(campaign, sc);
		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}

	private CampaignLocalService _campaignLocalService;

	@Reference(unbind = "-")
	protected void setCampaignLocalService(
		CampaignLocalService campagnLocalService) {
		_campaignLocalService = campagnLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}