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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.models.LegacyPlace;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=importLegacyData" },
	service = MVCActionCommand.class)
public class ImportLegacyDataActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		List<Place> places = this.placeLocalService.getPlaces(-1, -1);
		int updatedPlacesCount = 0;
		int placesWithoutLegacyCount = 0;

		for (Place place : places) {
			LegacyPlace legacyPlace = LegacyPlace.fromSIGId(place.getSIGid(),
				Locale.FRANCE);
			if (legacyPlace != null) {
				place.setAlias(legacyPlace.getAlias(), Locale.FRANCE);
				place.setPresentation(legacyPlace.getPresentation(),
					Locale.FRANCE);
				place.setServiceAndActivities(
					legacyPlace.getServicesAndActivities(), Locale.FRANCE);
				place.setCharacteristics(legacyPlace.getFeatures(),
					Locale.FRANCE);
				place.setAccess(legacyPlace.getAccess(), Locale.FRANCE);
				place.setAdditionalInformation(legacyPlace.getMoreInformation(),
					Locale.FRANCE);
				place.setSiteURL(legacyPlace.getWebsite(), Locale.FRANCE);
				place.setSiteLabel(legacyPlace.getWebsiteName(), Locale.FRANCE);
				place.setFacebookLabel(legacyPlace.getFacebookName(),
					Locale.FRANCE);
				place.setFacebookURL(legacyPlace.getFacebookURL(),
					Locale.FRANCE);
				place.setAccessForBlind(legacyPlace.getAccessForBlind());
				place.setAccessForDeaf(legacyPlace.getAccessForDeaf());
				place.setAccessForWheelchair(
					legacyPlace.getAccessForWheelchair());
				place.setAccessForElder(legacyPlace.getAccessForElder());
				place
					.setAccessForDeficient(legacyPlace.getAccessForDeficient());
				place.setAccessForDisabled(legacyPlace.getAccessForDisabled());
				place.setPhone(legacyPlace.getPhone());
				place.setMail(legacyPlace.getEmail());
				updatedPlacesCount++;
				System.out
					.println("Mise à jour " + place.getAlias(Locale.FRANCE));
				placeLocalService.updatePlace(place);
			} else {
				System.out
					.println("Non-existant " + place.getAlias(Locale.FRANCE));
				placesWithoutLegacyCount++;
			}
			place.setDisplayEvents(true);
			place.setSubjectToPublicHoliday(true);
			placeLocalService.updatePlace(place);
		}
		System.out.println("Lieux mis à jour : " + updatedPlacesCount
			+ " - Lieux non existant dans LR6 " + placesWithoutLegacyCount);
		return true;
	}

	private PlaceLocalService placeLocalService;

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {
		this.placeLocalService = placeLocalService;
	}
}
