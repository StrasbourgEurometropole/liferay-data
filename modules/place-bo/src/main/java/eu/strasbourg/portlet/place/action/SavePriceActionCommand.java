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
package eu.strasbourg.portlet.place.action;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.*;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.Price;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.service.place.service.PriceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=savePrice" }, service = MVCActionCommand.class)
public class SavePriceActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);

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
						"/place-bo-edit-price.jsp");
				return false;
			}

			long priceId = ParamUtil.getLong(request, "priceId");
			Price price;
			if (priceId == 0) {
				price = _priceLocalService.createPrice(sc);
			} else {
				price = _priceLocalService.getPrice(priceId);
			}

			Map<Locale, String> title = LocalizationUtil
					.getLocalizationMap(request, "title");
			price.setTitleMap(title);

			Map<Locale, String> tarif = LocalizationUtil
					.getLocalizationMap(request, "priceDescription");
			price.setPriceDescriptionMap(tarif);

			List<Place> oldplaces = price.getPlaces();
			for (Place place : oldplaces) {
				place.setPriceId(0);
				_placeLocalService.updatePlace(place);
			}
			long[] placesIds = ParamUtil.getLongValues(request,
				"placesIds");
			for (long placeId : placesIds) {
				Place place = _placeLocalService.getPlace(placeId);
				place.setPriceId(price.getPriceId());
				_placeLocalService.updatePlace(place);
			}

			_priceLocalService.updatePrice(price, sc);
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

		// Tarif
		if (Validator.isNull(ParamUtil.getString(request, "priceDescriptionEditor"))) {
			SessionErrors.add(request, "price-error");
			isValid = false;
		}

		return isValid;
	}

	private PriceLocalService _priceLocalService;

	@Reference(unbind = "-")
	protected void setPriceLocalService(
			PriceLocalService priceLocalService) {

		_priceLocalService = priceLocalService;
	}

	private PlaceLocalService _placeLocalService;

	@Reference(unbind = "-")
	protected void setPlaceLocalService(
			PlaceLocalService placeLocalService) {

		_placeLocalService = placeLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}