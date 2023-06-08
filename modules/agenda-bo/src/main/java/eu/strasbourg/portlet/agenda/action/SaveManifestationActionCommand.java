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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.*;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.utils.FileEntryHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.ManifestationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=saveManifestation" },
	service = MVCActionCommand.class)
public class SaveManifestationActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getCompanyGroupId());

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
						"/agenda-bo-edit-manifestation.jsp");
				return false;
			}

			long manifestationId = ParamUtil.getLong(request,
				"manifestationId");
			Manifestation eventManifestation;
			if (manifestationId == 0) {
				eventManifestation = _eventManifestationLocalService
					.createManifestation(sc);
			} else {
				eventManifestation = _eventManifestationLocalService
					.getManifestation(manifestationId);
			}

			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			eventManifestation.setTitleMap(title);

			Long imageId = ParamUtil.getLong(request, "imageId");
			if (imageId > 0) {
				eventManifestation.setImageId(imageId);
				eventManifestation.setExternalImageURL("");
				eventManifestation.setExternalImageCopyright("");
			} else {
				eventManifestation.setImageId((long) 0);
				String externalImageURL = ParamUtil.getString(request,
					"externalImageURL");
				eventManifestation.setExternalImageURL(externalImageURL);

				String externalImageCopyright = ParamUtil.getString(request,
					"externalImageCopyright");
				eventManifestation.setExternalImageCopyright(externalImageCopyright);
			}

			Map<Locale, String> description = LocalizationUtil
				.getLocalizationMap(request, "description");
			eventManifestation.setDescriptionMap(description);

			String startDateString = ParamUtil.getString(request, "startDate");
			Date startDate = DateUtil.parseDate(startDateString,
				request.getLocale());
			eventManifestation.setStartDate(startDate);

			String endDateString = ParamUtil.getString(request, "endDate");
			Date endDate = DateUtil.parseDate(endDateString,
				request.getLocale());
			eventManifestation.setEndDate(endDate);
			
			String publicationDateString = ParamUtil.getString(request,
				"publicationDate");
			String publicationDateTimeString = ParamUtil.getString(request,
				"publicationDateTime");
			Date publicationDate = GetterUtil.getDate(
				publicationDateString + " " + publicationDateTimeString,
				new SimpleDateFormat("dd/MM/yyyy hh:mm"));
			eventManifestation.setPublicationDate(publicationDate);

			// Events
			List<Event> oldEvents = eventManifestation.getEvents();
			for (Event event : oldEvents) {
				_eventManifestationLocalService.deleteEventManifestation(
					event.getEventId(), eventManifestation);
			}
			long[] eventsIds = ParamUtil.getLongValues(request, "eventsIds");
			for (long eventId : eventsIds) {
				if (eventId > 0) {
					_eventManifestationLocalService
						.addEventManifestation(eventId, eventManifestation);
				}
			}

			_eventManifestationLocalService
				.updateManifestation(eventManifestation, sc);
		} catch (PortalException e) {
			_log.error(e);
		} catch (ParseException e) {
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

		// Description
		if (Validator.isNull(ParamUtil.getString(request, "descriptionEditor"))) {
			SessionErrors.add(request, "description-error");
			isValid = false;
		}

		// Image
		long imageId = ParamUtil.getLong(request, "imageId");
		String imageURL = ParamUtil.getString(request, "externalImageURL");
		if (imageId == 0 && Validator.isNull(imageURL)) {
			SessionErrors.add(request, "image-error");
			isValid = false;
		}

		// Copyright de l'image
		String imageCopyright = ParamUtil.getString(request,
				"externalImageCopyright");
		boolean internalImageWithoutCopyright = imageId > 0 && Validator
				.isNull(FileEntryHelper.getImageCopyright(imageId, Locale.FRANCE));
		boolean externalImageWithoutCopyright = Validator.isNotNull(imageURL)
				&& Validator.isNull(imageCopyright);
		if (internalImageWithoutCopyright || externalImageWithoutCopyright) {
			SessionErrors.add(request, "image-copyright-error");
			isValid = false;
		}

		return isValid;
	}

	private ManifestationLocalService _eventManifestationLocalService;

	@Reference(unbind = "-")
	protected void setManifestationLocalService(
		ManifestationLocalService eventManifestationLocalService) {

		_eventManifestationLocalService = eventManifestationLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}