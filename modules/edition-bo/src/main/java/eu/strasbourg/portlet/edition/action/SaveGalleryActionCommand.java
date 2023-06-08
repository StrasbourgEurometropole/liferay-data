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
package eu.strasbourg.portlet.edition.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.*;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.EDITION_BO,
		"mvc.command.name=saveGallery" },
	service = MVCActionCommand.class)
public class SaveGalleryActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getScopeGroupId());

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
						"/edition-bo-edit-gallery.jsp");
				return false;
			}

			long galleryId = ParamUtil.getLong(request, "galleryId");
			EditionGallery editionGallery;
			if (galleryId == 0) {
				editionGallery = _editionGalleryLocalService
					.createEditionGallery(sc);
			} else {
				editionGallery = _editionGalleryLocalService
					.getEditionGallery(galleryId);
			}

			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			editionGallery.setTitleMap(title);

			Long imageId = ParamUtil.getLong(request, "imageId");
			editionGallery.setImageId(imageId);

			Map<Locale, String> description = LocalizationUtil
				.getLocalizationMap(request, "description");
			editionGallery.setDescriptionMap(description);

			String publicationDateString = ParamUtil.getString(request,
				"publicationDate");
			String publicationDateTimeString = ParamUtil.getString(request,
				"publicationDateTime");
			Date publicationDate = GetterUtil.getDate(
				publicationDateString + " " + publicationDateTimeString,
				new SimpleDateFormat("dd/MM/yyyy hh:mm"));
			editionGallery.setPublicationDate(publicationDate);

			// Editions
			List<Edition> oldEditions = editionGallery.getEditions();
			for (Edition edition : oldEditions) {
				_editionGalleryLocalService.deleteEditionEditionGallery(
					edition.getEditionId(), editionGallery);
			}
			long[] editionsIds = ParamUtil.getLongValues(request,
				"editionsIds");
			for (long editionId : editionsIds) {
				if (editionId > 0) {
					_editionGalleryLocalService
						.addEditionEditionGallery(editionId, editionGallery);
				}
			}

			_editionGalleryLocalService.updateEditionGallery(editionGallery,
				sc);
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

		// Titre
		if (Validator.isNull(ParamUtil.getString(request, "title"))) {
			SessionErrors.add(request, "title-error");
			isValid = false;
		}

		// Image
		long imageId = ParamUtil.getLong(request, "imageId");
		if (imageId == 0) {
			SessionErrors.add(request, "image-error");
			isValid = false;
		}

		return isValid;
	}

	private EditionGalleryLocalService _editionGalleryLocalService;

	@Reference(unbind = "-")
	protected void setEditionGalleryLocalService(
		EditionGalleryLocalService editionGalleryLocalService) {

		_editionGalleryLocalService = editionGalleryLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}