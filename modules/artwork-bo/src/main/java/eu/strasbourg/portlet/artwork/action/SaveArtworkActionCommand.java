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
package eu.strasbourg.portlet.artwork.action;

import java.util.List;
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

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_BO,
		"mvc.command.name=saveArtwork" },
	service = MVCActionCommand.class)
public class SaveArtworkActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getScopeGroupId());

			long artworkId = ParamUtil.getLong(request, "artworkId");
			Artwork artwork;
			if (artworkId == 0) {
				artwork = _artworkLocalService.createArtwork(sc);
			} else {
				artwork = _artworkLocalService.getArtwork(artworkId);
			}

			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			artwork.setTitleMap(title);

			Map<Locale, String> description = LocalizationUtil
				.getLocalizationMap(request, "description");
			artwork.setDescriptionMap(description);

			Long imageId = ParamUtil.getLong(request, "imageId");
			artwork.setImageId(imageId);

			String imagesIds = ParamUtil.getString(request, "imagesIds");
			artwork.setImagesIds(imagesIds);

			Map<Locale, String> technicalInformation = LocalizationUtil
				.getLocalizationMap(request, "technicalInformation");
			artwork.setTechnicalInformationMap(technicalInformation);

			Map<Locale, String> noticeLink = LocalizationUtil
				.getLocalizationMap(request, "noticeLink");
			artwork.setNoticeLinkMap(noticeLink);

			Map<Locale, String> artistName = LocalizationUtil
				.getLocalizationMap(request, "artistName");
			artwork.setArtistNameMap(artistName);

			Map<Locale, String> creationYear = LocalizationUtil
				.getLocalizationMap(request, "creationYear");
			artwork.setCreationYearMap(creationYear);

			Map<Locale, String> origin = LocalizationUtil
				.getLocalizationMap(request, "origin");
			artwork.setOriginMap(origin);

			Map<Locale, String> exhibitionName = LocalizationUtil
				.getLocalizationMap(request, "exhibitionName");
			artwork.setExhibitionNameMap(exhibitionName);

			Map<Locale, String> exhibitionPlace = LocalizationUtil
				.getLocalizationMap(request, "exhibitionPlace");
			artwork.setExhibitionPlaceMap(exhibitionPlace);

			Map<Locale, String> loanPeriod = LocalizationUtil
				.getLocalizationMap(request, "loanPeriod");
			artwork.setLoanPeriodMap(loanPeriod);

			Map<Locale, String> linkName = LocalizationUtil
				.getLocalizationMap(request, "linkName");
			artwork.setLinkNameMap(linkName);

			Map<Locale, String> link = LocalizationUtil
				.getLocalizationMap(request, "link");
			artwork.setLinkMap(link);
			
			// Collections
			List<ArtworkCollection> artworkCollections = artwork.getArtworkCollections();
			for (ArtworkCollection artworkCollection : artworkCollections) {
				_artworkLocalService.deleteArtworkCollectionArtwork(artworkCollection.getCollectionId(), artwork);
			}
			long[] collectionsIds = ParamUtil.getLongValues(request, "collectionsIds");
			for (long collectionId : collectionsIds) {
				if (collectionId > 0) {
					_artworkLocalService.addArtworkCollectionArtwork(collectionId, artwork);
				}
			}
			
			_artworkLocalService.updateArtwork(artwork, sc);
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return true;
	}

	private ArtworkLocalService _artworkLocalService;

	@Reference(unbind = "-")
	protected void setArtworkLocalService(
		ArtworkLocalService artworkLocalService) {

		_artworkLocalService = artworkLocalService;
	}

}