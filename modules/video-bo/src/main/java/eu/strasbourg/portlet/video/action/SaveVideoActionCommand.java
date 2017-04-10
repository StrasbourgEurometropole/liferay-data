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
package eu.strasbourg.portlet.video.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.VIDEO_BO,
		"mvc.command.name=saveVideo" },
	service = MVCActionCommand.class)
public class SaveVideoActionCommand
	implements MVCActionCommand {
	
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getScopeGroupId());
			long videoId = ParamUtil.getLong(request, "videoId");
			Video video;
			if (videoId == 0) {
				video = _videoLocalService.createVideo(sc);
			} else {
				video = _videoLocalService.getVideo(videoId);
			}
			
			Map<Locale, String> title = LocalizationUtil
				.getLocalizationMap(request, "title");
			video.setTitleMap(title);

			Map<Locale, String> source = LocalizationUtil
				.getLocalizationMap(request, "source");
			video.setSourceMap(source);
			
			Long imageId = ParamUtil.getLong(request, "imageId");
			video.setImageId(imageId);

			Map<Locale, String> description = LocalizationUtil
				.getLocalizationMap(request, "description");
			video.setDescriptionMap(description);

			Map<Locale, String> copyright = LocalizationUtil
				.getLocalizationMap(request, "copyright");
			video.setCopyrightMap(copyright);

			Long transcriptionFileId = ParamUtil.getLong(request, "transcriptionFileId");
			video.setTranscriptionFileId(transcriptionFileId);
			
			String publicationDateString = ParamUtil.getString(request,
				"publicationDate");
			String publicationDateTimeString = ParamUtil.getString(request, "publicationDateTime");
			Date publicationDate = GetterUtil.getDate(publicationDateString + " " + publicationDateTimeString, new SimpleDateFormat("dd/MM/yyyy hh:mm"));			
			video.setPublicationDate(publicationDate);
			
			// Galeries
			List<VideoGallery> oldGalleries = video.getVideoGalleries();
			for (VideoGallery gallery : oldGalleries) {
				_videoLocalService.deleteVideoGalleryVideo(gallery.getGalleryId(), video);
			}
			long[] galleriesIds = ParamUtil.getLongValues(request, "galleriesIds");
			for (long galleryId : galleriesIds) {
				if (galleryId > 0) {
					_videoLocalService.addVideoGalleryVideo(galleryId, video);
				}
			}
			
			_videoLocalService.updateVideo(video, sc);
		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}

	private VideoLocalService _videoLocalService;

	@Reference(unbind = "-")
	protected void setVideoLocalService(
		VideoLocalService videoLocalService) {

		_videoLocalService = videoLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}