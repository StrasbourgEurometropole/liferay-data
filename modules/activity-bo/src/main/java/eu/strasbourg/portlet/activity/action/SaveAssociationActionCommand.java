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
package eu.strasbourg.portlet.activity.action;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.service.AssociationLocalService;
import eu.strasbourg.service.activity.service.PracticeLocalService;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO,
		"mvc.command.name=saveAssociation" },
	service = MVCActionCommand.class)
public class SaveAssociationActionCommand extends BaseMVCActionCommand {

	private AssociationLocalService associationLocalService;

	@Reference(unbind = "-")
	protected void setAssociationLocalService(
			AssociationLocalService associationLocalService) {
		this.associationLocalService = associationLocalService;
	}

	@Override
	protected void doProcessAction(ActionRequest request,
		ActionResponse response) throws Exception {
		ServiceContext sc = ServiceContextFactory.getInstance(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

		long associationId = ParamUtil.getLong(request,
			"associationId");
		Association association;
		if (associationId == 0) {
			association = associationLocalService
				.createAssociation(sc);
		} else {
			association = associationLocalService
				.getAssociation(associationId);
		}

		// Validation
		boolean isValid = validate(request);

		if (!isValid) {
			// Si pas valide : on reste sur la page d'édition
			PortalUtil.copyRequestParameters(request, response);

			PortletURL returnURL = PortletURLFactoryUtil.create(request,
				portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
			returnURL.setParameter("tab", request.getParameter("tab"));

			response.setRenderParameter("returnURL", returnURL.toString());
			response.setRenderParameter("mvcPath",
				"/activity-bo-edit-association.jsp");
			return;
		}

		// Nom
		Map<Locale, String> name = LocalizationUtil.getLocalizationMap(request,
			"name");
		association.setNameMap(name);

		// Présentation
		Map<Locale, String> presentation = LocalizationUtil
			.getLocalizationMap(request, "presentation");
		association.setPresentationMap(presentation);

		String phone = ParamUtil.getString(request, "phone");
		association.setPhone(phone);

		String siteURL = ParamUtil.getString(request, "siteURL");
		association.setSiteURL(siteURL);

		String mail = ParamUtil.getString(request, "mail");
		association.setMail(mail);

		String facebookURL = ParamUtil.getString(request, "facebookURL");
		association.setFacebookURL(facebookURL);

		// Présentation
		Map<Locale, String> othersInformations = LocalizationUtil
				.getLocalizationMap(request, "othersInformations");
		association.setOthersInformationsMap(othersInformations);

		// Update de l'entité
		associationLocalService.updateAssociation(association,
				sc);

		// Pratiques
		// On récupère les pratiques de l'association pour suppression
		List<Long> practicesToKeep = new ArrayList<Long>();

		String practiceIndexes = ParamUtil.getString(request, "practiceIndexes");
		if (Validator.isNotNull(practiceIndexes)) {
			for (String practiceIndex : practiceIndexes.split(",")) {
				if (Validator.isNotNull(practiceIndex)) {
					long practiceId = ParamUtil.getLong(request, "practiceId" + practiceIndex);

					Practice practice;
					if(practiceId != 0){
						practice = _practiceLocalService.fetchPractice(practiceId);
					}else{
						practice = _practiceLocalService.createPractice(sc);
					}

					// on ajoute cette pratique à la liste des pratiques à garder
					practicesToKeep.add(practice.getPracticeId());
					AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();
					long groupId = themeDisplay.getLayout().getGroupId();

					String categoriesIdsString = "";
					AssetVocabulary practicesVocabulary = assetVocabularyAccessor.getPractice(groupId);
					if (practicesVocabulary != null) {
						categoriesIdsString += ParamUtil.getString(request, "practiceId-" + practiceIndex + "_" + practicesVocabulary.getVocabularyId());
					}

					AssetVocabulary publicsVocabulary = assetVocabularyAccessor.gePracticePublic(groupId);
					if (publicsVocabulary != null) {
						categoriesIdsString += "," + ParamUtil.getString(request, "practiceId-" + practiceIndex + "_" + publicsVocabulary.getVocabularyId());
					}

					AssetVocabulary territoriesVocabulary = AssetVocabularyHelper
							.getGlobalVocabulary(VocabularyNames.TERRITORY);
					if (territoriesVocabulary != null) {
						categoriesIdsString += "," + ParamUtil.getString(request, "practiceId-" + practiceIndex + "_" + territoriesVocabulary.getVocabularyId());
					}

					AssetVocabulary accessibiliestyVocabulary = assetVocabularyAccessor.getAccessibility(groupId);
					if (publicsVocabulary != null) {
						categoriesIdsString += "," + ParamUtil.getString(request, "practiceId-" + practiceIndex + "_" + accessibiliestyVocabulary.getVocabularyId());
					}

					List<Long> categoriesList = new ArrayList<Long>();
					for (String category: categoriesIdsString.split(",")) {
						if(Validator.isNotNull(category))
							categoriesList.add(Long.parseLong(category));
					}
					long[] categoriesIds = categoriesList.stream().mapToLong(l -> l).toArray();

					ServiceContext scPractice = ServiceContextFactory.getInstance(request);
					scPractice.setAssetCategoryIds(categoriesIds);

					practice.setAssociationId(association.getAssociationId());
					this._practiceLocalService.updatePractice(practice, scPractice);
				}

			}
		}

		// On supprime les anciennes activités qui n'existent plus
		for (Practice practice : association.getPractices()) {
			if(!practicesToKeep.contains(practice.getPracticeId())){
				_practiceLocalService.removePractice(practice.getPracticeId());
			}
		}

		// Post / Redirect / Get si tout est bon
		PortletURL renderURL = PortletURLFactoryUtil.create(request,
			portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		renderURL.setParameter("tab", request.getParameter("tab"));
		response.sendRedirect(renderURL.toString());
	}

	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		if (Validator.isNull(ParamUtil.getString(request, "name"))) {
			SessionErrors.add(request, "name-error");
			isValid = false;
		}

		if (Validator.isNull(ParamUtil.getString(request, "phone")) && Validator.isNull(ParamUtil.getString(request, "siteURL")) && Validator.isNull(ParamUtil.getString(request, "mail")) && Validator.isNull(ParamUtil.getString(request, "facebookURL"))) {
			SessionErrors.add(request, "info-error");
			isValid = false;
		}

		return isValid;
	}

	private PracticeLocalService _practiceLocalService;

	@Reference(unbind = "-")
	protected void setPracticeLocalService(PracticeLocalService practiceLocalService) {

		_practiceLocalService = practiceLocalService;
	}
}