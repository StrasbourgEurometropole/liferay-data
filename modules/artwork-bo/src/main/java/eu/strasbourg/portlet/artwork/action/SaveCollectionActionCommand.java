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
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_BO,
		"mvc.command.name=saveCollection" },
	service = MVCActionCommand.class)
public class SaveCollectionActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getSiteGroupIdOrLiveGroupId());
			long collectionId = ParamUtil.getLong(request, "collectionId");
			ArtworkCollection collection;
			if (collectionId == 0) {
				collection = _artworkCollectionLocalService
					.addArtworkCollection();
			} else {
				collection = _artworkCollectionLocalService
					.getArtworkCollection(collectionId);
			}

			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			collection.setTitleMap(title);

			String description = ParamUtil.getString(request, "description");
			collection.setDescription(description);
			
			Long imageId = ParamUtil.getLong(request, "imageId");
			collection.setImageId(imageId);

			Map<Locale, String> contributors = LocalizationUtil
				.getLocalizationMap(request, "contributors");
			collection.setContributorsMap(contributors);

			// Artworks
			List<Artwork> artworks = collection.getArtworks();
			for (Artwork artwork : artworks) {
				_artworkCollectionLocalService.deleteArtworkArtworkCollection(artwork.getArtworkId(), collection);
			}
			long[] artworksIds = ParamUtil.getLongValues(request, "artworksIds");
			for (long artworkId : artworksIds) {
				if (artworkId > 0) {
					_artworkCollectionLocalService.addArtworkArtworkCollection(artworkId, collection);
				}
			}
			
			// Status
			String forceStatus = ParamUtil.getString(request, "forceStatus");
			if (forceStatus.equals("publish")) {
				collection.setStatus(true);
			} else if (forceStatus.equals("unpublish")) {
				collection.setStatus(false);
			}

			_artworkCollectionLocalService.updateArtworkCollection(collection,
				sc);
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return true;
	}

	private ArtworkCollectionLocalService _artworkCollectionLocalService;

	@Reference(unbind = "-")
	protected void setArtworkCollectionLocalService(
		ArtworkCollectionLocalService artworkCollectionLocalService) {

		_artworkCollectionLocalService = artworkCollectionLocalService;
	}

}