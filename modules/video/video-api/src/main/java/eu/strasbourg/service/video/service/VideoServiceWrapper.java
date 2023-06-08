/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.video.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VideoService}.
 *
 * @author BenjaminBini
 * @see VideoService
 * @generated
 */
public class VideoServiceWrapper
	implements ServiceWrapper<VideoService>, VideoService {

	public VideoServiceWrapper(VideoService videoService) {
		_videoService = videoService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _videoService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getVideo(long id) {
		return _videoService.getVideo(id);
	}

	@Override
	public VideoService getWrappedService() {
		return _videoService;
	}

	@Override
	public void setWrappedService(VideoService videoService) {
		_videoService = videoService;
	}

	private VideoService _videoService;

}