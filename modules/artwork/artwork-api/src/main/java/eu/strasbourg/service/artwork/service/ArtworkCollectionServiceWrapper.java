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

package eu.strasbourg.service.artwork.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ArtworkCollectionService}.
 *
 * @author BenjaminBini
 * @see ArtworkCollectionService
 * @generated
 */
@ProviderType
public class ArtworkCollectionServiceWrapper implements ArtworkCollectionService,
	ServiceWrapper<ArtworkCollectionService> {
	public ArtworkCollectionServiceWrapper(
		ArtworkCollectionService artworkCollectionService) {
		_artworkCollectionService = artworkCollectionService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _artworkCollectionService.getOSGiServiceIdentifier();
	}

	@Override
	public ArtworkCollectionService getWrappedService() {
		return _artworkCollectionService;
	}

	@Override
	public void setWrappedService(
		ArtworkCollectionService artworkCollectionService) {
		_artworkCollectionService = artworkCollectionService;
	}

	private ArtworkCollectionService _artworkCollectionService;
}