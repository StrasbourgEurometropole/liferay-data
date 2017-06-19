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

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.EventPeriodLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=duplicateEvent" },
	service = MVCActionCommand.class)
public class DuplicateEventActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getCompanyGroupId());
			long eventId = ParamUtil.getLong(request, "eventId");
			Event oldEvent = _eventLocalService.getEvent(eventId);
			Event event = _eventLocalService.createEvent(sc);

			// Titre (ajout de " (copie)" après le titre existant)
			Map<Locale, String> titleMap = oldEvent.getTitleMap();
			for (Map.Entry<Locale, String> titleEntry : titleMap.entrySet()) {
				titleMap.put(titleEntry.getKey(), titleEntry.getValue() + " (copie)");
			}
			event.setTitleMap(titleMap);
			event.setSubtitle(oldEvent.getSubtitle());
			event.setDescription(oldEvent.getDescription());

			// Image
			if (oldEvent.getImageId() > 0) {
				event.setImageId(oldEvent.getImageId());
				event.setExternalImageURL("");
				event.setExternalImageCopyright("");
			} else {
				event.setImageId((long) 0);
				event.setExternalImageURL(oldEvent.getExternalImageURL());
				event.setExternalImageCopyright(
					oldEvent.getExternalImageCopyright());
			}

			// Lieu
			if (Validator.isNotNull(oldEvent.getPlaceSIGId())) {
				event.setPlaceSIGId(oldEvent.getPlaceSIGId());
				event.setPlaceName("");
				event.setPlaceStreetNumber("");
				event.setPlaceStreetName("");
				event.setPlaceZipCode("");
				event.setPlaceCountry("");
			} else {
				event.setPlaceSIGId("");
				event.setPlaceName(oldEvent.getPlaceName());
				event.setPlaceStreetNumber(oldEvent.getPlaceStreetNumber());
				event.setPlaceStreetName(oldEvent.getPlaceStreetNumber());
				event.setPlaceZipCode(oldEvent.getPlaceZipCode());
				event.setPlaceCity(oldEvent.getPlaceCity());
				event.setPlaceCountry(oldEvent.getPlaceCountry());
			}

			// Accès
			event.setAccess(oldEvent.getAccess());
			event.setAccessForDisabled(oldEvent.getAccessForDisabled());
			event.setAccessForBlind(oldEvent.getAccessForBlind());
			event.setAccessForWheelchair(oldEvent.getAccessForWheelchair());
			event.setAccessForDeaf(oldEvent.getAccessForDeaf());
			event.setAccessForElder(oldEvent.getAccessForElder());
			event.setAccessForDeficient(oldEvent.getAccessForDeficient());

			// Autres informations générales
			event.setPromoter(oldEvent.getPromoter());
			event.setPhone(oldEvent.getPhone());
			event.setEmail(oldEvent.getEmail());
			event.setWebsiteName(oldEvent.getWebsiteName());
			event.setWebsiteURL(oldEvent.getWebsiteURL());
			event.setFree(oldEvent.getFree());
			event.setPrice(oldEvent.getPrice());
			
			// Date de publication
			event.setPublicationDate(new Date());

			// Manifestations
			List<Manifestation> oldEventManifestations = oldEvent
				.getManifestations();
			for (Manifestation manifestation : oldEventManifestations) {
				_eventLocalService.addManifestationEvent(
					manifestation.getManifestationId(), event);
			}

			// Périodes
			List<EventPeriod> oldEventPeriods = event.getEventPeriods();
			for (EventPeriod period : oldEventPeriods) {
				EventPeriod eventPeriod = _eventPeriodLocalService
					.createEventPeriod();
				eventPeriod.setStartDate(period.getStartDate());
				eventPeriod.setEndDate(period.getEndDate());
				eventPeriod.setTimeDetail(period.getTimeDetail());
				eventPeriod.setEventId(event.getEventId());
				eventPeriod.setCampaignEventId(0);
				this._eventPeriodLocalService.updateEventPeriod(eventPeriod);
			}

			// Catégories
			long[] oldEventCategoryIds = oldEvent.getCategories().stream()
				.mapToLong(AssetCategory::getCategoryId).toArray();
			sc.setAssetCategoryIds(oldEventCategoryIds);
			
			// Statut
			sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

			// Enregistrement en base
			_eventLocalService.updateEvent(event, sc);
			
			// POST / REDIRECT / GET
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
			PortletURL renderURL = PortletURLFactoryUtil.create(request,
				portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			renderURL.setParameter("tab", request.getParameter("tab"));
			response.sendRedirect(renderURL.toString());

		} catch (Exception e) {
			_log.error(e);
		}

		return true;
	}

	private EventLocalService _eventLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {

		_eventLocalService = eventLocalService;
	}

	private EventPeriodLocalService _eventPeriodLocalService;

	@Reference(unbind = "-")
	protected void setEventPeriodLocalService(
		EventPeriodLocalService eventPeriodLocalService) {

		_eventPeriodLocalService = eventPeriodLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}