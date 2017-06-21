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
package eu.strasbourg.portlet.official.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.OFFICIAL_BO,
		"mvc.command.name=saveOfficial" }, service = MVCActionCommand.class)
public class SaveOfficialActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
					((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
							.getScopeGroupId());
			long officialId = ParamUtil.getLong(request, "officialId");
			Official official;
			if (officialId == 0) {
				official = _officialLocalService.createOfficial(sc);
			} else {
				official = _officialLocalService.getOfficial(officialId);
			}

			int gender = ParamUtil.getInteger(request, "gender");
			official.setGender(gender);

			String lastName = ParamUtil.getString(request, "lastName");
			official.setLastName(lastName);

			String firstName = ParamUtil.getString(request, "firstName");
			official.setFirstName(firstName);

			Long imageId = ParamUtil.getLong(request, "imageId");
			official.setImageId(imageId);

			Map<Locale, String> thematicDelegation = LocalizationUtil
					.getLocalizationMap(request, "thematicDelegation");
			official.setThematicDelegationMap(thematicDelegation);

			Map<Locale, String> missions = LocalizationUtil
					.getLocalizationMap(request, "missions");
			official.setMissionsMap(missions);

			boolean wasMinister = ParamUtil.getBoolean(request, "wasMinister");
			official.setWasMinister(wasMinister);

			Map<Locale, String> contact = LocalizationUtil
					.getLocalizationMap(request, "contact");
			official.setContactMap(contact);

			_officialLocalService.updateOfficial(official, sc);

			// Modifie les catégories de l'assetentry
			List<String> categories = new ArrayList<String>();

			Long fonctionCity = ParamUtil.getLong(request, "fonction-city");
			if (Validator.isNotNull(AssetCategoryLocalServiceUtil
					.fetchAssetCategory(fonctionCity))) {
				categories.add(fonctionCity.toString());

				// ajout de la catégorie Strasbourg
				AssetCategory category = AssetVocabularyHelper
						.getCategory("Strasbourg", sc.getThemeDisplay().getCompanyGroupId());
				categories.add(String.valueOf(category.getCategoryId()));

			}

			String[] districts = request.getParameterValues("districts2");
			if (Validator.isNotNull(districts)) {
				for (int i = 0; i < districts.length; i++) {
					if (Validator.isNotNull(districts[i])) {
						categories.add(districts[i]);
					}
				}
			}

			Long politicalGroupCity = ParamUtil.getLong(request,
					"political-group-city");
			if (Validator.isNotNull(AssetCategoryLocalServiceUtil
					.fetchAssetCategory(politicalGroupCity))) {
				categories.add(politicalGroupCity.toString());
			}

			Long fonctionEurometropole = ParamUtil.getLong(request,
					"fonction-eurometropole");
			if (Validator.isNotNull(AssetCategoryLocalServiceUtil
					.fetchAssetCategory(fonctionEurometropole))) {
				categories.add(fonctionEurometropole.toString());
			}

			Long fonctionTown = ParamUtil.getLong(request, "fonction-town");
			if (Validator.isNotNull(AssetCategoryLocalServiceUtil
					.fetchAssetCategory(fonctionTown))) {
				categories.add(fonctionTown.toString());
			}

			Long town = ParamUtil.getLong(request, "town2");
			if (Validator.isNotNull(
					AssetCategoryLocalServiceUtil.fetchAssetCategory(town))) {
				categories.add(town.toString());
			}

			Long politicalGroupEurometropole = ParamUtil.getLong(request,
					"political-group-eurometropole");
			if (Validator.isNotNull(AssetCategoryLocalServiceUtil
					.fetchAssetCategory(politicalGroupEurometropole))) {
				categories.add(politicalGroupEurometropole.toString());
			}

			String[] othersMandates = request
					.getParameterValues("others-mandates");
			if (Validator.isNotNull(othersMandates)) {
				for (int i = 0; i < othersMandates.length; i++) {
					categories.add(othersMandates[i]);
				}
			}

			long[] categoryIds = new long[categories.size()];
			for (int i = 0; i < categories.size(); i++) {
				if (Validator.isNotNull(categories.get(i))) {
					categoryIds[i] = Long.parseLong(categories.get(i));
				}
			}

			AssetEntryLocalServiceUtil.updateEntry(sc.getUserId(),
					official.getGroupId(), Official.class.getName(),
					official.getOfficialId(), categoryIds, null);

		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}

	private OfficialLocalService _officialLocalService;

	@Reference(unbind = "-")
	protected void setOfficialLocalService(
			OfficialLocalService officialLocalService) {

		_officialLocalService = officialLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}